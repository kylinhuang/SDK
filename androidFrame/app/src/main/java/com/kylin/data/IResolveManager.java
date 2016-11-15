package com.kylin.data;


import com.kylin.data.entity.ResponseEntity.BaseResponseEntity;

/**
 * Created by kylinhuang on 03/11/2016.
 * 解析
 */

public interface IResolveManager {

    /**
     * json 解析  成实体 BaseResponseEntity
     * @param msg
     * @param <T>
     * @param ClaZZ
     * @return
     */
    <T extends BaseResponseEntity> T resolveJson(Class ClaZZ , String msg);

    /**
     * XML 解析  成实体 BaseResponseEntity
     * @param msg
     * @param <T>
     * @param ClaZZ
     * @return
     */
    <T extends BaseResponseEntity> String resolveXML(Class ClaZZ ,String msg);
}
