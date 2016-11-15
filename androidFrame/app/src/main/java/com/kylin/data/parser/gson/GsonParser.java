package com.kylin.data.parser.gson;

import com.google.gson.Gson;
import com.google.gson.internal.Primitives;
import com.kylin.data.entity.ResponseEntity.BaseResponseEntity;
import com.kylin.data.parser.IParserManager;

/**
 * Created by kylinhuang on 15/11/2016.
 */

public class GsonParser implements IParserManager {
    private final Gson mGson;

    public GsonParser() {
         mGson= new Gson();
    }

    @Override
    public  <T extends BaseResponseEntity> T parserJson(Class<T>  ClaZZ, String msg) {
        Object user = mGson.fromJson(msg, ClaZZ);
        return Primitives.wrap(ClaZZ).cast(user);
    }

    @Override
    public <T extends BaseResponseEntity> String parserXML(Class<T> ClaZZ, String msg) {
        return null;
    }
}
