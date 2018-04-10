package com.jc.school.utils;

import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;

/**
 * Gson解析、转化工具
 * Created by Jaycee on 16/1/20.
 * E-mail：jayceeok@foxmail.com
 */
public class GsonUtils {
    private static final String TAG ="GsonUtils" ;

    /**
     * json字符串解析为对象
     *
     * @param jsonResult
     * @param clz
     * @param <T>
     *
     * @return
     */
    public static <T> T jsonToBean(String jsonResult, Class<T> clz) {
        Gson gson = new Gson();
        T t = gson.fromJson(jsonResult, clz);
        return t;
    }

    /**
     * map数据组装成json字符串
     *
     * @param map
     * @param <T>
     *
     * @return
     */
    public static <T> String mapToJson(Map<String, T> map) {
        Gson gson = new Gson();
        String jsonStr = gson.toJson(map);
        return jsonStr;
    }

    /**
     * 将java对象转换成json字符串
     *
     * @param bean
     *
     * @return
     */
    public static String beanToJson(Object bean) {
        Gson gson = new Gson();
        String jsonStr = gson.toJson(bean);
        return jsonStr.toString();
    }


    public static String transMapToString(Map map) {
        Map.Entry entry;
        StringBuffer sb = new StringBuffer();
        for (Iterator iterator = map.entrySet().iterator(); iterator.hasNext(); ) {
            entry = (Map.Entry) iterator.next();
            sb.append(entry.getKey().toString()).append("=").append(
                    null == entry.getValue() ? "" : entry.getValue().toString()
            ).append(iterator.hasNext() ? "&" : "");
        }
        return sb.toString();
    }

    /**
     * 转加密后的字符串给服务器解析,否则服务器那边request接收的值所有的“+”变成空格
     *
     * @param paramString
     *
     * @return
     */
    public static String toURLEncoded(String paramString) {
        if (paramString == null || paramString.equals("")) {
            Logger.i(TAG, "toURLEncoded error:" + paramString);
            return "";
        }
        try {
            String str = new String(paramString.getBytes(), "UTF-8");
            str = URLEncoder.encode(str, "UTF-8");
            return str;
        } catch (Exception localException) {
        }
        return "";
    }


}
