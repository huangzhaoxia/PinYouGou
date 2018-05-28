package com.pinyougou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pinyougou.service.BaseService;
import com.pinyougou.vo.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.common.Mapper;

import java.io.Serializable;
import java.util.List;

public abstract class BaseServiceImpl<T> implements BaseService<T> {

    @Autowired
    private Mapper<T> mapper;


    @Override
    public List<T> findAll() {
        return mapper.selectAll();
    }

    @Override
    public List<T> findByWhere(T t) {
        return mapper.select(t);
    }

    @Override
    public PageResult findPage(Integer page, Integer rows) {

        PageHelper.startPage(page, rows);
        List<T> tList = mapper.selectAll();
        PageInfo<T> pageInfo = new PageInfo<>(tList);

        return new PageResult(pageInfo.getTotal(),pageInfo.getList());
    }

    @Override
    public void add(T t) {
        mapper.insertSelective(t);

    }

    @Override
    public void update(T t) {
    mapper.updateByPrimaryKeySelective(t);
    }

    @Override
    public void deleteByIds(Serializable[] ids) {
        if(ids.length>0 && ids != null){
            for (Serializable id : ids) {
                mapper.deleteByPrimaryKey(id);
            }
        }

    }

    @Override
    public T findOne(Serializable id) {
        return mapper.selectByPrimaryKey(id);
    }
}