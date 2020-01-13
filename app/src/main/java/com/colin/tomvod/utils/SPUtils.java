package com.colin.tomvod.utils;

import android.content.Context;
import android.content.SharedPreferences;


import com.colin.tomvod.AppContext;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;


public class SPUtils {


    /**
     * 保存数据的方法，我们需要拿到保存数据的具体类型，然后根据类型调用不同的保存方法
     *
     * @param key
     * @param object
     */
    public static void put(String key, Object object) {

        SharedPreferences sp = AppContext.applicationContext.getSharedPreferences(Constant.SP_FILE_NAME,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        if (object instanceof String) {
            editor.putString(key, (String) object);
        } else if (object instanceof Integer) {
            editor.putInt(key, (Integer) object);
        } else if (object instanceof Boolean) {
            editor.putBoolean(key, (Boolean) object);
        } else if (object instanceof Float) {
            editor.putFloat(key, (Float) object);
        } else if (object instanceof Long) {
            editor.putLong(key, (Long) object);
        } else {
            editor.putString(key, object.toString());
        }

        SharedPreferencesCompat.apply(editor);
    }


    /**
     * 得到保存数据的方法，我们根据默认值得到保存的数据的具体类型，然后调用相对于的方法获取值
     *
     * @param key
     * @param defaultObject
     * @return
     */
    public static Object get(String key, Object defaultObject) {
        SharedPreferences sp = AppContext.applicationContext.getSharedPreferences(Constant.SP_FILE_NAME,
                Context.MODE_PRIVATE);

        if (defaultObject instanceof String) {
            return sp.getString(key, (String) defaultObject);
        } else if (defaultObject instanceof Integer) {
            return sp.getInt(key, (Integer) defaultObject);
        } else if (defaultObject instanceof Boolean) {
            return sp.getBoolean(key, (Boolean) defaultObject);
        } else if (defaultObject instanceof Float) {
            return sp.getFloat(key, (Float) defaultObject);
        } else if (defaultObject instanceof Long) {
            return sp.getLong(key, (Long) defaultObject);
        }

        return null;
    }

    /**
     * 移除某个key值已经对应的值
     *
     * @param key
     */
    public static void remove(String key) {
        SharedPreferences sp = AppContext.applicationContext.getSharedPreferences(Constant.SP_FILE_NAME,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.remove(key);
        SharedPreferencesCompat.apply(editor);
    }

    /**
     * 清除所有数据
     */
    public static void clear() {
        SharedPreferences sp = AppContext.applicationContext.getSharedPreferences(Constant.SP_FILE_NAME,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.clear();
        SharedPreferencesCompat.apply(editor);
    }

    /**
     * 查询某个key是否已经存在
     *
     * @param key
     * @return
     */
    public static boolean contains(String key) {
        SharedPreferences sp = AppContext.applicationContext.getSharedPreferences(Constant.SP_FILE_NAME,
                Context.MODE_PRIVATE);
        return sp.contains(key);
    }

    /**
     * 返回所有的键值对
     *
     * @return
     */
    public static Map<String, ?> getAll() {
        SharedPreferences sp = AppContext.applicationContext.getSharedPreferences(Constant.SP_FILE_NAME,
                Context.MODE_PRIVATE);
        return sp.getAll();
    }

    /**
     * 创建一个解决SharedPreferencesCompat.apply方法的一个兼容类
     *
     * @author zhy
     */
    private static class SharedPreferencesCompat {
        private static final Method sApplyMethod = findApplyMethod();

        /**
         * 反射查找apply的方法
         *
         * @return
         */
        @SuppressWarnings({"unchecked", "rawtypes"})
        private static Method findApplyMethod() {
            try {
                Class clz = SharedPreferences.Editor.class;
                return clz.getMethod("apply");
            } catch (NoSuchMethodException e) {
            }
            return null;
        }

        /**
         * 如果找到则使用apply执行，否则使用commit
         *
         * @param editor
         */
        public static void apply(SharedPreferences.Editor editor) {
            try {
                if (sApplyMethod != null) {
                    sApplyMethod.invoke(editor);
                    return;
                }
            } catch (IllegalArgumentException e) {
            } catch (IllegalAccessException e) {
            } catch (InvocationTargetException e) {

            }
            editor.commit();
        }
    }

//    public static long getMbrId() {
//        long mbrId = (long) SPUtils.get (Constant.MBR_ID, (long) -1L);
//        return mbrId;
//    }

    public static boolean isFirstEnter() {
        return (boolean) SPUtils.get(Constant.SP_FIRST_ENTER, true);
    }
    public static boolean isFirstInstall() {
        return (boolean) SPUtils.get(Constant.SP_FIRST_INSTALL, true);
    }

    public static String getLoginToken() {
        return (String) SPUtils.get(Constant.SP_LOGIN_TOKEN, "");
    }
    public static String getDefaultToken() {
        return (String) SPUtils.get(Constant.SP_DEFAULT_TOKEN, "");
    }

    public static String getSearchHistory() {
        return (String) SPUtils.get(Constant.SP_HISTORY_SEARCH,"");
    }

    /**
     * 将lgTkn和mbrId置空并且将登陆状态置为lgOut
     */
    public static void putLogEmpty() {
        SPUtils.put(Constant.SP_LOGIN_TOKEN, "");
        //登出状态
        SPUtils.put(Constant.LOGIN_STATE, Constant.LOGIN_STATE_OUT);
    }


}