package com.jc.school.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;

import com.jc.school.base.App;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Set;

/**
 * 保存本地参数
 *
 * @author jiangchao
 *         created at 2018/3/28 上午10:45
 */

public class SPUtils {

    private static SharedPreferences sp;
    private static Context context;

    static {
        context = App.getInstance();
    }

    private static void init(final String tag) {
        sp = context.getSharedPreferences(tag, Context.MODE_PRIVATE);
    }

    public static String get(final String tag, final String key) {
        init(tag);
        return sp.getString(key, "");
    }

    public static int getInt(final String tag, final String key) {
        init(tag);
        return sp.getInt(key, 0);
    }

    public static boolean getBoolean(final String tag, final String key) {
        init(tag);
        return sp.getBoolean(key, false);
    }

    public static void put(final String tag, final String key, final String value) {
        init(tag);
        sp.edit().putString(key, value).commit();
    }

    public static void put(final String tag, final String key, final int value) {
        init(tag);
        sp.edit().putInt(key, value).commit();
    }

    public static void put(final String tag, final String key, final boolean value) {
        init(tag);
        sp.edit().putBoolean(key, value).commit();
    }

    public static void remove(final String tag, final String key) {
        init(tag);
        if (sp.contains(key)) {
            sp.edit().remove(key).commit();
        }
    }

    public static void putSet(final String tag, final String key, final Set<String> value) {
        init(tag);
        sp.edit().putStringSet(key, value).commit();
    }


    public static Set<String> getSet(final String tag, final String key) {
        init(tag);
        return sp.getStringSet(key, null);

    }


    public static void putBean(final String tag, String key, Object obj) {
        init(tag);
        if (obj instanceof Serializable) {
            try {
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(baos);
                oos.writeObject(obj);
                String string64 = new String(Base64.encode(baos.toByteArray(),
                        0));
                sp.edit().putString(key, string64).commit();
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else {
            throw new IllegalArgumentException(
                    "the obj must implement Serializable");
        }

    }

    public static Object getBean(final String tag, String key) {
        init(tag);
        Object obj = null;
        try {
            String base64 = sp.getString(key, "");
            if (base64.equals("")) {
                return null;
            }
            byte[] base64Bytes = Base64.decode(base64.getBytes(), 1);
            ByteArrayInputStream bais = new ByteArrayInputStream(base64Bytes);
            ObjectInputStream ois = new ObjectInputStream(bais);
            obj = ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }


}
