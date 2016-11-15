package com.kylin.data.http.retrofit;

import android.text.TextUtils;

import com.google.gson.Gson;

import java.io.IOException;
import java.nio.charset.Charset;
import java.security.cert.CertificateException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by gaozhongkui on_normal 2016/3/15.
 */
public class RetrofitService {
    private static Retrofit.Builder mRetrofitBuilder = new Retrofit.Builder().
            addCallAdapterFactory(RxJavaCallAdapterFactory.create()).
            addConverterFactory(GsonConverterFactory.create());
    private static OkHttpClient.Builder mOkHttpClientBuild = getUnsafeOkHttpClient();
    private static final String HEADER_PREFIX = "JSESSIONID=";
    private static int TIME_OUT = 30;

    public static <S> S createHttpService(Class<S> createservice, String baseurl) {
//        LogUtils.i("--createHttpService--isNativeJson:" + ElementApplication.isNativeJson());
        Retrofit retrofit = mRetrofitBuilder.client(mOkHttpClientBuild.build()).baseUrl(baseurl).build();
        return retrofit.create(createservice);
    }

    public static String getHttpSessionId() {
//        if (mUserCenterManager == null) {
//            mUserCenterManager = UserCenterManager.getInstance();
//        }
//        if (!TextUtils.isEmpty(MyApplication.getApplication().getJsessionid())) {
//            return (HEADER_PREFIX + MyApplication.getApplication().getJsessionid());
//        }
        return "";
    }

    private static OkHttpClient.Builder getUnsafeOkHttpClient() {
        OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
        try {
            final TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager() {
                @Override
                public void checkClientTrusted(
                        java.security.cert.X509Certificate[] chain,
                        String authType) throws CertificateException {
                }

                @Override
                public void checkServerTrusted(
                        java.security.cert.X509Certificate[] chain,
                        String authType) throws CertificateException {
                }

                @Override
                public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                    return new java.security.cert.X509Certificate[]{};
                }
            }};
            okHttpClient.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Chain chain) throws IOException {
                    Request mRequest = chain.request();
                    String token = getHttpSessionId();
                    if (!TextUtils.isEmpty(token)) {
                        Response mResponse = chain.proceed(mRequest.newBuilder().header("Cookie", token).build());
                        return mResponse;
                    }
                    return chain.proceed(mRequest);
                }
            });
            //okHttpClient.addInterceptor(new TokenInterceptor()); /*Token拦截去掉*/
            okHttpClient.readTimeout(TIME_OUT, TimeUnit.SECONDS);//设置读取超时时间
            okHttpClient.writeTimeout(TIME_OUT, TimeUnit.SECONDS);//设置写的超时时间
            okHttpClient.connectTimeout(TIME_OUT, TimeUnit.SECONDS);//设置连接超时时间
//            okHttpClient.retryOnConnectionFailure(false);
//            if (BuildConfig.DEBUG) {
//                HttpLoggingInterceptor mLoggingInterceptor = new HttpLoggingInterceptor();
//                mLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//                okHttpClient.addInterceptor(mLoggingInterceptor);
//            }
//            File cacheFile = new File(MyApplication.getApplication().getApplicationContext().getCacheDir(), "datacache");
//            Cache cache = new Cache(cacheFile, 1024 * 1024 * 20);
//            okHttpClient.cache(cache);
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();
            okHttpClient.sslSocketFactory(sslSocketFactory);
            okHttpClient.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });
            return okHttpClient;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Token过期的拦截器
     * 每次请求都会去检验Token是否过期,若是过期则跳转到登录界面
     */
    static class TokenInterceptor implements Interceptor {
        private static final Charset UTF8 = Charset.forName("UTF-8");

        @Override
        public Response intercept(Chain chain) {
            Request request = chain.request();
            Response originalResponse = null;
//            BaseResponseEntity bean = null;
            try {
                originalResponse = chain.proceed(request);
                ResponseBody responseBody = originalResponse.body();
                BufferedSource source = responseBody.source();
                source.request(Long.MAX_VALUE); // Buffer the entire body.
                Buffer buffer = source.buffer();
                Charset charset = UTF8;
                MediaType contentType = responseBody.contentType();
                if (contentType != null) {
                    charset = contentType.charset(UTF8);
                }
                String bodyString = buffer.clone().readString(charset);
                Gson gson = new Gson();
//                bean = gson.fromJson(bodyString, BaseResponseEntity.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
            /**
             * ret字段值为100时表示Token过期
             *
             * messageCode  这个字段留待后续考虑
             *  200    正常
             *  10001  系统错误
             *  20005  Session过期
             */
//            if (bean.getRet() == 100) {
//                ElementBaseActivity baseActivity = ElementBaseActivity.getForegroundActivity();
//                if ((baseActivity instanceof ElementLoginActivity) ||
//                        (baseActivity instanceof ElementRegistActivity)) {
//                    LogUtils.i("已经在登录页面或注册页面进行登录操作了!!!");
//                } else {
//                    ElementActivityFactory.jumpActivityLogin();
//                }
//            }
            return originalResponse;
        }
    }
}
