package com.kylin.data.http.okhttp;


import android.util.Log;

import com.kylin.data.DataManager;
import com.kylin.data.entity.RequestEntity.BaseRequestEntity;
import com.kylin.data.http.IHttpManager;

import java.io.IOException;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Cookie;
import okhttp3.CookieJar;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by kylinhuang on 03/11/2016.
 */

public class OKHttpManager implements IHttpManager {
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private final OkHttpClient.Builder builder;

    private OkHttpClient okHttpClient;
    private String TAG = "OKHttpManager";

    public OKHttpManager() {

        builder = new OkHttpClient.Builder();
        builder.cookieJar(new CookieJar() {
            private final HashMap<String, List<Cookie>> cookieStore = new HashMap<String, List<Cookie>>();

            @Override
            public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
                cookieStore.put(url.host(), cookies);
            }

            @Override
            public List<Cookie> loadForRequest(HttpUrl url) {
                List<Cookie> cookies = cookieStore.get(url.host());
                return cookies != null ? cookies : new ArrayList<Cookie>();
            }
        });
        okHttpClient = builder.build();
    }

    @Override
    public <T extends BaseRequestEntity> String login(T requestEntity) {
        if (requestEntity instanceof BaseRequestEntity) {
            BaseRequestEntity baseRequestEntity = (BaseRequestEntity) requestEntity;
            String responseMessage = postString(baseRequestEntity.getRequestURL(), baseRequestEntity.getBody());
            return responseMessage;
        }
        return null;
    }


    @Override
    public <T extends BaseRequestEntity> String getCameraList(T requestEntity) {
        if (requestEntity instanceof BaseRequestEntity) {
            BaseRequestEntity baseRequestEntity = (BaseRequestEntity) requestEntity;
            String responseMessage = postString(baseRequestEntity.getRequestURL(), baseRequestEntity.getBody());
            return responseMessage;
        }
        return null;
    }

    @Override
    public <T extends BaseRequestEntity> String getUserInfo(T requestEntity) {
        if (requestEntity instanceof BaseRequestEntity) {
            BaseRequestEntity baseRequestEntity = (BaseRequestEntity) requestEntity;
            String responseMessage = postString(baseRequestEntity.getRequestURL(), baseRequestEntity.getBody());
            return responseMessage;
        }
        return null;
    }



    /**
     * okHttp post 请求
     *
     * @param url
     * @param jsonBody
     */
    private String postString(String url, String jsonBody) {
        boolean isHttps = url.startsWith("https://");
        if (isHttps) httpsIgnoreAuthenticate();

        RequestBody body = RequestBody.create(JSON, jsonBody);
//        RequestBody formBody = new FormEncodingBuilder()
//                .add("","")
//                .build();

        Log.e(TAG," url " + url + " body " + body  );
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                //取出本地保存的sessionId
//        "JSESSIONID=" + cookie
                .addHeader("Cookie", "JSESSIONID=" + DataManager.getInstance().getCookie())
                .build();

        Response response = null;
        try {
            response = okHttpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                return response.body().string();
            } else {
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 这是忽略 https 证书认证
     */
    private void httpsIgnoreAuthenticate() {
        try {
            // Create a trust manager that does not validate certificate chains
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
                    return new java.security.cert.X509Certificate[0];
                }
            }};

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            HostnameVerifier hostnameVerifier = new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            };
            okHttpClient = okHttpClient.newBuilder().sslSocketFactory(sslSocketFactory)
                    .hostnameVerifier(hostnameVerifier).build();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setCookie(String url , String cookie) {
        ArrayList<Cookie> list=   new ArrayList<Cookie>();
//        okHttpClient.cookieJar().saveFromResponse(url,list);
    }
}
