package com.yjm.hospital.base;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@NoRepositoryBean
public interface BaseRepository<T, ID extends Serializable>{

    <T> List<T> findAll(String sql, Map<String, Object> whereParam, T inEntity);
    <T> Page<T> findAll(String sql, Map<String, Object> whereParam, Pageable pageable, T inEntity);
    List<Map> findAll(String sql, Map<String, Object> whereParam);
    <T> T findOne(String sql, Map<String, Object> whereParam, T inEntity);
    Map<String, Object> findOneMap(String sql, Map<String, Object> whereParam);

}
