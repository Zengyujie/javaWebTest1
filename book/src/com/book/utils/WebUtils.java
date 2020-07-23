package com.book.utils;

import com.book.pojo.User;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class WebUtils {

    public static <T> T copyParamToBean(Map map, Class<T> clazz){
        T bean = null;
        try {
            bean = clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        System.out.println("注入之前" + bean);
        try {
            BeanUtils.populate(bean, map);
            System.out.println("注入之后" + bean);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return bean;
    }

    public static int parseInt(String strInt, int defaultValue){
        try {
            if(strInt == null || "".equals(strInt))
                return defaultValue;
            else
                return Integer.parseInt(strInt);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
        return defaultValue;
    }

}
