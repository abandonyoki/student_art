package com.xn.util;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class BeanToMapUtil {
    private static  Log logger = LogFactory.getLog(BeanToMapUtil.class);

    /**
     * 将map转化为bean
     * @param type
     * @param map
     * @return
     */
    public static Object convertMap(Class<?> type,Map<String,Object> map) throws IntrospectionException, IllegalAccessException, InstantiationException, InvocationTargetException {
        BeanInfo beanInfo = Introspector.getBeanInfo(type);
        Object o = type.newInstance();
        //给JavaBean对象的属性赋值
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (int i = 0; i < propertyDescriptors.length; i++) {
            PropertyDescriptor descriptor = propertyDescriptors[i];
            String propertyName = descriptor.getName();

            if (map.containsKey(propertyName)) {
                // 下面一句可以 try 起来，这样当一个属性赋值失败的时候就不会影响其他属性赋值。
                Object value = map.get(propertyName);

                Object[] args = new Object[1];
                args[0] = value;

                descriptor.getWriteMethod().invoke(o, args);
            }
        }
        return o;
    }

    /**
     * 将JavaBean转化为map
     * @param bean
     * @return
     */
    public static Map<String,Object> convertBean(Object bean) {
        Class<?> type = bean.getClass();
        Map<String,Object> returnMap = new HashMap<>();
        BeanInfo beanInfo;
        //调用 Introspector.getBeanInfo() 方法，得到的 BeanInfo 对象封装了把这个类当做 JavaBean 看的结果信息，即属性的信息。
            try {
                beanInfo = Introspector.getBeanInfo(type);
                PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
                for (int i = 0; i < propertyDescriptors.length; i++) {
                    PropertyDescriptor descriptor = propertyDescriptors[i];
                    String propertyName = descriptor.getName();
                    if (!propertyName.equals("class")) {
                        Method readMethod = descriptor.getReadMethod();
                        Object result = readMethod.invoke(bean, new Object[0]);
                        returnMap.put(propertyName, result);
                    }
                }
        } catch (IllegalAccessException e) {
            logger.error("[BeanToMapUtil.convertBean] IllegalAccessException:" +e.getMessage());
        } catch (InvocationTargetException e) {
            logger.error("[BeanToMapUtil.convertBean] InvocationTargetException:" +e.getMessage());
        } catch (IntrospectionException e) {
            e.printStackTrace();
        }
         return returnMap;
    }
}
