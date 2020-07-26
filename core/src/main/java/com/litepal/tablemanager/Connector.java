package com.litepal.tablemanager;

import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.text.TextUtils;

import com.litepal.exceptions.InvalidAttributesException;
import com.litepal.parser.LitePalAttr;
import com.litepal.LitePalApplication;

import java.io.File;

/**
 * The connector to connect database provided by LitePal. Users can use this
 * class to get the instance of SQLiteDatabase. But users still need to write
 * their own CRUD logic by the returned SQLiteDatabase. It will be improved in
 * the future.
 *
 * @author Tony Green
 * @since 1.0
 */
public class Connector {
    /**
     * The quote of LitePalHelper.
     */
    private static LitePalOpenHelper mLitePalHelper;

    /**
     * Get a writable SQLiteDatabase.
     * <p>
     * There're a lot of ways to operate database in android. But LitePal
     * doesn't support using ContentProvider currently. The best way to use
     * LitePal well is get the SQLiteDatabase instance and use the methods like
     * SQLiteDatabase#save, SQLiteDatabase#update, SQLiteDatabase#delete,
     * SQLiteDatabase#query in the SQLiteDatabase class to do the database
     * operation. It will be improved in the future.
     *
     * @return A writable SQLiteDatabase instance
     */
    public synchronized static SQLiteDatabase getWritableDatabase() {
        LitePalOpenHelper litePalHelper = buildConnection();
        return litePalHelper.getWritableDatabase();
    }

    /**
     * Call getDatabase directly will invoke the getWritableDatabase method by
     * default.
     * <p>
     * This is method is alias of getWritableDatabase.
     *
     * @return A writable SQLiteDatabase instance
     */
    public static SQLiteDatabase getDatabase() {
        return getWritableDatabase();
    }

    /**
     * Build a connection to the database. This progress will analysis the
     * litepal.xml file, and will check if the fields in LitePalAttr are valid,
     * and it will open a SQLiteOpenHelper to decide to create tables or update
     * tables or doing nothing depends on the version attributes.
     * <p>
     * After all the stuffs above are finished. This method will return a
     * LitePalHelper object.Notes this method could throw a lot of exceptions.
     *
     * @return LitePalHelper object.
     * @throws InvalidAttributesException
     */
    private static LitePalOpenHelper buildConnection() {
        LitePalAttr litePalAttr = LitePalAttr.getInstance();
        litePalAttr.checkSelfValid();
        if (mLitePalHelper == null) {
            String dbName = litePalAttr.getDbName();
            if ("external".equalsIgnoreCase(litePalAttr.getStorage())) {
                dbName = LitePalApplication.getContext().getExternalFilesDir("") + "/databases/" + dbName;
            } else if (!"internal".equalsIgnoreCase(litePalAttr.getStorage()) && !TextUtils.isEmpty(litePalAttr.getStorage())) {
                // internal or empty means internal storage, neither or them means sdcard storage
                String dbPath = Environment.getExternalStorageDirectory().getPath() + "/" + litePalAttr.getStorage();
                dbPath = dbPath.replace("//", "/");
                File path = new File(dbPath);
                if (!path.exists()) {
                    path.mkdirs();
                }
                dbName = dbPath + "/" + dbName;
            }
            mLitePalHelper = new LitePalOpenHelper(dbName, litePalAttr.getVersion());
        }
        return mLitePalHelper;
    }

    /**
     * Never call this method. This is only used by internal.
     */
    public static void clearLitePalOpenHelperInstance() {
        if (mLitePalHelper != null) {
            mLitePalHelper.getWritableDatabase().close();
            mLitePalHelper = null;
        }
    }
}
