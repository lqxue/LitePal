package com.litepal.litepalsample.db;import com.litepal.LitePalDB;
import com.litepal.Operator;
import com.litepal.crud.LitePalSupport;import java.util.List;/**
 * 
 *
 * @author lqx Email:herolqx@126.com
 */
public class BaseDBDao<T extends LitePalSupport> implements IDBDao<T> {public BaseDBDao(String daName, int version, String tableName) {
        this(daName, version, false, tableName);
    }public BaseDBDao(String daName, int version, boolean isExternalStorage, String tableName) {
        //初始化数据库
        LitePalDB litePalDB = new LitePalDB(daName, version);
        litePalDB.setExternalStorage(isExternalStorage);
        List<String> classNames = litePalDB.getClassNames();
        classNames.clear();
        classNames.add(tableName);
        //切换表
        Operator.use(litePalDB);
        //创建表
        Operator.getDatabase();
    }@Override
    public T insert(T t) throws Exception {
        boolean save = t.save();
        if (save){
            return t;
        }else {
            return null;
        }
    }@Override
    public T insertOrUpdate(T t) throws Exception {
        return null;
    }@Override
    public List<T> insert(Iterable<T> objects) throws Exception {
        return null;
    }@Override
    public List<T> insertOrUpdate(Iterable<T> objects) throws Exception {
        return null;
    }@Override
    public void delete(T t) throws Exception {}
}
