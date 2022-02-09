package com.yjm.hospital.base;

import cn.hutool.core.convert.Convert;
import org.hibernate.query.internal.NativeQueryImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Service
public class BaseRepositoryImpl<T, TD extends Serializable> implements BaseRepository<T, TD> {
    @PersistenceContext
    private final EntityManager entityManager;//父类没有不带参数的构造方法，这里手动构造父类
    // 全局日志
    private static final Logger logger = LoggerFactory.getLogger(BaseRepositoryImpl.class);

    public BaseRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public <T> List<T> findAll(String sql, Map<String, Object> whereParam, T inEntity) {
        Query query = entityManager.createNativeQuery(sql);
        //转换为clzss
        query.unwrap(NativeQueryImpl.class).setResultTransformer(new IgnoreTypeeResultTransformer(inEntity.getClass()));
        // 条件值设定
        setParameter(query, whereParam);
        return query.getResultList();
    }

    public <T> Page<T> findAll(String sql, Map<String, Object> whereParam, Pageable pageable, T inEntity) {
        Query query = entityManager.createNativeQuery(sql);
        //转换为clzss
        query.unwrap(NativeQueryImpl.class).setResultTransformer(new IgnoreTypeeResultTransformer(inEntity.getClass()));
        // 条件值设定
        setParameter(query, whereParam);
        query.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());//起始数
        query.setMaxResults(pageable.getPageSize());
        List<T> resultList = query.getResultList();
        Long total = getTotalCount(sql, whereParam);
        Page<T> pageResult = new PageImpl<>(resultList, pageable, total);
        return pageResult;
    }

    @Override
    public List<Map> findAll(String sql, Map<String, Object> whereParam) {
        Query query = null;
        query = entityManager.createNativeQuery(sql);
        //转换为Map集合
        query.unwrap(NativeQueryImpl.class).setResultTransformer(new IgnoreTypeeResultTransformer(Map.class));
        // 条件值设定
        setParameter(query, whereParam);
        List<Map> mapList = query.getResultList();
        return mapList;
    }

    @Override
    public <T> T findOne(String sql, Map<String, Object> whereParam, T inEntity) {
        Query query = entityManager.createNativeQuery(sql);
        //转换为clzss
        query.unwrap(NativeQueryImpl.class).setResultTransformer(new IgnoreTypeeResultTransformer(inEntity.getClass()));
        // 条件值设定
        setParameter(query, whereParam);
        T t = null;
        t = (T) query.getSingleResult();
        return t;
    }

    @Override
    public Map<String, Object> findOneMap(String sql, Map<String, Object> whereParam) {
        Query query = null;
        query = entityManager.createNativeQuery(sql);
        //转换为Map集合
        query.unwrap(NativeQueryImpl.class).setResultTransformer(new IgnoreTypeeResultTransformer(Map.class));
        // 条件值设定
        setParameter(query, whereParam);
        Map<String, Object> map = (Map<String, Object>) query.getSingleResult();
        return map;
    }


    private Long getTotalCount(String inSql, Map<String, Object> whereParam) {
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT COUNT(1) FROM (");
        sql.append(inSql);
        sql.append(" ) TOTALCOUNT ");
        Query query = entityManager.createNativeQuery(sql.toString());
        // 条件值设定
        setParameter(query, whereParam);
        Object totalcount = query.getSingleResult();
        BigDecimal total = Convert.toBigDecimal(totalcount);
        return total.longValue();
    }

    private void setParameter(Query query, Map<String, Object> whereParam) {
        if (null == whereParam || whereParam.size() == 0) {
            return;
        }
        for (Map.Entry<String, Object> entry : whereParam.entrySet()) {
            if (!ObjectUtils.isEmpty(entry.getKey()) && !ObjectUtils.isEmpty(entry.getValue())) {
                query.setParameter(entry.getKey(), entry.getValue());
            }
        }
    }
}
