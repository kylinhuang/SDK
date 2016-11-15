package com.kylin.data.db;


/**
 * Created by kylinhuang on 03/11/2016.
 * 数据操作
 */

public interface IDBManager {

    /**
     * 查询
     */
    void query ();

    /**
     * 插入
     */
    void insert ();

    /**
     * 删除
     */
    void delete ();

    /**
     * 更新
     */
    void update ();


}
