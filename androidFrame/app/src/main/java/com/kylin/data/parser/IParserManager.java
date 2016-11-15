package com.kylin.data.parser;


import com.kylin.data.entity.ResponseEntity.BaseResponseEntity;

/**
 * Created by kylinhuang on 03/11/2016.
 * 解析
 */

public interface IParserManager {

    /**
     * json 解析  成实体 BaseResponseEntity
     * @param msg
     * @param <T>
     * @param ClaZZ
     * @return
     */
    <T extends BaseResponseEntity> T parserJson(Class<T> ClaZZ , String msg);

    /**
     * XML 解析  成实体 BaseResponseEntity
     * @param msg
     * @param <T>
     * @param ClaZZ
     * @return
     */
    <T extends BaseResponseEntity> String parserXML(Class<T> ClaZZ ,String msg);
}
