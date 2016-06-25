package com.example.administrator.httplib;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/6/23 0023.
 */
public class SQLiteDataBaseHelper {
    /** 日志打印 */
    private static final String TAG = "SQLiteDataBaseHelper";
    /** 用于管理和操作SQLite数据库 */
    private SQLiteDatabase database = null;
    /** 由SQLiteOpenHelper继承过来，用于实现数据库的建立与更新 */
    private MySQLiteOpen mySQLiteOpen = null;
    // ================================
    /** SD卡的根目录 */
    private final String SDCARD_ROOT = Environment
            .getExternalStorageDirectory().getAbsolutePath();
    /** 打开默认数据库路径 */
    private final String PATH = SDCARD_ROOT + File.separator
            + File.separator + "cbk.db";
    // ==============================
    /** 要创建的数据库名字 */
    private static final String DB_NAME = "words.db";
    /** 数据库版本 */
    private static final int VERSION = 1;
    /** 创建表名 */
//	private static final String SQL_CREATE_TABLE = "CREATE TABLE tb_words(_id INTEGER PRIMARY KEY AUTOINCREMENT , english , chinese)";
    private static final String SQL_CREATE_TABLE = "CREATE TABLE tb_teacontents(_id INTEGER PRIMARY KEY, title,count,time,type)";

    /**
     * 继承SQLiteOpenHelper类，在构造方法中分别需要传入Context,数据库名称,CursorFactory(一般传入null
     *
     * 为默认数据库),数据库版本号(不能为负数)。在SQLiteOpenHelper中首先执行的是onCreate方法
     *
     * 在构造函数时并没有真正创建数据库
     *
     * 而在调用getWritableDatabase或者getReadableDatabase方法时才真正去创建数据库
     *
     * 返回一个SQLiteDatabase对象。
     *
     * 数据存储到了data/data/应用包名/databases
     *
     *
     *
     */
    private class MySQLiteOpen extends SQLiteOpenHelper {
        /**
         * 构造方法
         *
         * @param context
         * @param name
         * @param factory
         * @param version
         */
        public MySQLiteOpen(Context context, String name,
                            SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
            Log.i(TAG, "==MySQLiteOpen()");
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.i(TAG, "==数据库没有表时创建一个");
            db.execSQL(SQL_CREATE_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.i(TAG, "==升级数据库");
            if (newVersion > oldVersion) {
                db.execSQL("DROP TABLE IF EXISTS tb_words");
                onCreate(db);
            }
        }

    }

    /**
     * 默认的构造方法自动去创建一个默认的数据库路径(SD卡的数据，需要到这个文来修改)
     */
    public SQLiteDataBaseHelper() {
        database = SQLiteDatabase.openDatabase(PATH, null,
                SQLiteDatabase.OPEN_READWRITE);
    }

    /**
     * 创建数据库，返回数据库对象
     *
     * @param context
     */
    public SQLiteDataBaseHelper(Context context, String name) {
        mySQLiteOpen = new MySQLiteOpen(context, DB_NAME, null, VERSION);
        database = mySQLiteOpen.getReadableDatabase();
        // database = mySQLiteOpen.getWritableDatabase();
    }

    /**
     * @作用 查询数据返回Cursor
     * @param sql
     * @param selectionArgs
     * @return
     */
    public Cursor selectCursor(String sql, String[] selectionArgs) {
        return database.rawQuery(sql, selectionArgs);
    }

    /**
     * @作用 执行带占位符的select语句，返回list集合
     * @param sql
     * @param selectionArgs
     * @return
     */
    public List<Map<String, String>> SelectData(String sql,
                                                String[] selectionArgs) {
        Cursor cursor = selectCursor(sql, selectionArgs);
        return cursorToList(cursor);
    }

    /**
     * @作用 已知一个cursor得到List集合
     * @param cursor
     * @return
     */
    private List<Map<String, String>> cursorToList(Cursor cursor) {
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();
        String[] arrColumnName = cursor.getColumnNames();
        while (cursor.moveToNext()) {
            Map<String, String> map = new HashMap<String, String>();
            for (int i = 0; i < arrColumnName.length; i++) {
                String cols_value = cursor.getString(i);
                map.put(arrColumnName[i], cols_value);
            }
            list.add(map);
        }
        if (cursor != null) {
            cursor.close();
        }
        return list;
    }

    /**
     * @作用 执行带占位符的update、insert、delete语句，更新数据库，返回true或false
     * @param sql
     * @param bindArgs
     *            问号中的参数值
     * @return boolean
     */
    public boolean updataData(String sql, Object[] bindArgs) {
        try {
            if (bindArgs == null) {
                database.execSQL(sql);
            } else {
                database.execSQL(sql, bindArgs);
            }
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * @作用 执行带占位符的select语句，返回结果集的个数。如果已经查询过了不推荐继续使用，占内存
     * @param sql
     * @param selectionArgs
     * @return int
     */
    public int selectCount(String sql, String[] selectionArgs) {
        Cursor cursor = database.rawQuery(sql, selectionArgs);
        int count = 0;
        if (cursor != null) {
            count = cursor.getCount();
            cursor.close();
        }
        return count;
    }

    /**
     * @作用 关闭数据库操作类
     */
    public void destroy() {
        if (mySQLiteOpen != null) {
            mySQLiteOpen.close();
            mySQLiteOpen = null;
        }
        if (database != null) {
            database.close();
            database = null;
        }
    }
}

