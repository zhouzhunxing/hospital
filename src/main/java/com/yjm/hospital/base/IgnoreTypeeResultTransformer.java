package com.yjm.hospital.base;

import cn.hutool.core.convert.Convert;
import org.hibernate.transform.ResultTransformer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.beans.BeanMap;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class IgnoreTypeeResultTransformer implements ResultTransformer {

    private static Logger logger = LoggerFactory.getLogger(IgnoreTypeeResultTransformer.class);

    private static final long serialVersionUID = -3779317531110592988L;

    public static ConcurrentMap<String, BeanMap> beanMapCache = new ConcurrentHashMap<String, BeanMap>();
    private final Class<?> resultClass;

    public IgnoreTypeeResultTransformer(final Class<?> resultClass) {
        this.resultClass = resultClass;
    }

    /**
     * aliases为每条记录的数据库字段名,
     * tupe为与aliases对应的字段的值
     */
    @Override
    public Object transformTuple(Object[] tuple, String[] aliases) {
        if (Map.class.isAssignableFrom(this.resultClass)) {
            Map result = new LinkedHashMap(tuple.length);
            for (int i = 0; i < tuple.length; i++) {
                String alias = aliases[i];
                if (alias != null) {
                    Object value = tuple[i];
                    if (tuple[i] instanceof Timestamp) {
//                        value = DateUtil.timeToDate((Timestamp) tuple[i], DateUtil.DATE_TIME_FORMAT_YYYY_MM_DD_HH_MI_SS);
                    }
                }
            }
            return result;
        } else {
            Object result = null;
            try {
                result = this.resultClass.newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < tuple.length; i++) {
                String alias = aliases[i];
                if (alias != null) {
                    setFieldValue(result, alias.toLowerCase(), tuple[i]);
                }
            }
            return result;
        }


    }

    /**
     * 功能描述：设置实体属性
     */
    private <T> void setFieldValue(T obj, String fieldName, Object fieldValue) {
        // 将属性名的首字母变为大写，为执行set/get方法做准备
        String methodName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
        Method setMethod = null;
        Method getMethod = null;
        try {
            getMethod = obj.getClass().getMethod("get" + methodName);
            setMethod = obj.getClass().getMethod("set" + methodName, getMethod.getReturnType());
            if (setMethod == null) {
                setMethod = obj.getClass().getSuperclass().getMethod("set" + methodName, getMethod.getReturnType());
            }
            if (setMethod != null) {
                if (Long.class.isAssignableFrom(getMethod.getReturnType())) {
                    setMethod.invoke(obj, Convert.toLong(fieldValue));
                } else if (BigDecimal.class.isAssignableFrom(getMethod.getReturnType())) {
                    setMethod.invoke(obj, Convert.toBigDecimal(fieldValue));
                } else if (String.class.isAssignableFrom(getMethod.getReturnType())) {
                    setMethod.invoke(obj, fieldValue.toString());
                } else if (Integer.class.isAssignableFrom(getMethod.getReturnType()) ||
                        int.class.isAssignableFrom(getMethod.getReturnType())) {
                    setMethod.invoke(obj, Convert.toInt(fieldValue));
                } else {
                    setMethod.invoke(obj, fieldValue);
                }

            }
        } catch (Exception e) {
//            e.printStackTrace();
//            logger.error("数据列名：" + fieldName + "绑定数据失败！");
        }
    }

    @Override
    public List transformList(List collection) {
        return collection;
    }

}
