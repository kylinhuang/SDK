package com.kylin.data.entity.ResponseEntity;

/**
 * Created by gaozhongkui on_normal 2016/3/16.
 */
public class GetUserInfoResponseEntity extends BaseResponseEntity {

    /**
     * ret : 0
     * msg : success
     * nick_name : alabei看看
     * userId : 614
     * user : zlr@sengled.com
     * phone :
     * other_contact :
     * profile_path : http://jx1.amazon-storage.test.cloud.sengled.cn:8000/amazon-storage/download?bucketName=sengled-test-persistence-cn-north-1&filename=614_profile_1474884154530.jpg
     * is_email_auth_suc : 0
     * is_phone_auth_suc : 0
     * areaInfo : unknown
     * purchaseUrl : https://payment.cloud.sengled.com/payment/
     * serviceShow : {"security":1,"moments":0}
     */

    private int ret;
    private String msg;
    private String nick_name;
    private int userId;
    private String user;
    private String phone;
    private String other_contact;
    private String profile_path;
    private int is_email_auth_suc;
    private int is_phone_auth_suc;
    private String areaInfo;
    private String purchaseUrl;
    private ServiceShowBean serviceShow;

    public int getRet() {
        return ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getOther_contact() {
        return other_contact;
    }

    public void setOther_contact(String other_contact) {
        this.other_contact = other_contact;
    }

    public String getProfile_path() {
        return profile_path;
    }

    public void setProfile_path(String profile_path) {
        this.profile_path = profile_path;
    }

    public int getIs_email_auth_suc() {
        return is_email_auth_suc;
    }

    public void setIs_email_auth_suc(int is_email_auth_suc) {
        this.is_email_auth_suc = is_email_auth_suc;
    }

    public int getIs_phone_auth_suc() {
        return is_phone_auth_suc;
    }

    public void setIs_phone_auth_suc(int is_phone_auth_suc) {
        this.is_phone_auth_suc = is_phone_auth_suc;
    }

    public String getAreaInfo() {
        return areaInfo;
    }

    public void setAreaInfo(String areaInfo) {
        this.areaInfo = areaInfo;
    }

    public String getPurchaseUrl() {
        return purchaseUrl;
    }

    public void setPurchaseUrl(String purchaseUrl) {
        this.purchaseUrl = purchaseUrl;
    }

    public ServiceShowBean getServiceShow() {
        return serviceShow;
    }

    public void setServiceShow(ServiceShowBean serviceShow) {
        this.serviceShow = serviceShow;
    }

    public static class ServiceShowBean {
        /**
         * security : 1
         * moments : 0
         */

        private int security;
        private int moments;

        public int getSecurity() {
            return security;
        }

        public void setSecurity(int security) {
            this.security = security;
        }

        public int getMoments() {
            return moments;
        }

        public void setMoments(int moments) {
            this.moments = moments;
        }
    }

    @Override
    public String toString() {
        return "GetUserInfoResponseEntity{" +
                "ret=" + ret +
                ", msg='" + msg + '\'' +
                ", nick_name='" + nick_name + '\'' +
                ", userId=" + userId +
                ", user='" + user + '\'' +
                ", phone='" + phone + '\'' +
                ", other_contact='" + other_contact + '\'' +
                ", profile_path='" + profile_path + '\'' +
                ", is_email_auth_suc=" + is_email_auth_suc +
                ", is_phone_auth_suc=" + is_phone_auth_suc +
                ", areaInfo='" + areaInfo + '\'' +
                ", purchaseUrl='" + purchaseUrl + '\'' +
                ", serviceShow=" + serviceShow +
                '}';
    }
}
