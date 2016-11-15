package com.kylin.data.entity.RequestEntity;

import com.kylin.data.DataManager;

import java.io.Serializable;

/**
 * Created by kylinhuang on 02/11/2016.
 */

public abstract class BaseRequestEntity implements Serializable {
//    /owa/#path=/mail:1 Document was loaded from Application Cache with manifest https://mail.sengled.com/owa/manifests/appCacheManifestHandler.ashx?owamanifest=1
//            /owa/#path=/mail:1 Application Cache Checking event
//    /owa/#path=/mail:1 Application Cache Error event: Manifest fetch failed (440) https://mail.sengled.com/owa/manifests/appCacheManifestHandler.ashx?owamanifest=1
//            /owa/#path=/mail:1 Document was loaded from Application Cache with manifest https://mail.sengled.com/owa/manifests/appCacheManifestHandler.ashx?owamanifest=1
//            2/owa/#path=/mail:1 Application Cache Checking event
//    2/owa/#path=/mail:1 Application Cache NoUpdate event

    public String getRequestURL(){
        return getHost() + getUrl();
    }


    abstract public String getBody();

    abstract public String getUrl() ;

    public String getHost() {
        return DataManager.getInstance().getHost();
    }
}