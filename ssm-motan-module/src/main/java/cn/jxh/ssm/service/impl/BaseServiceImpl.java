package cn.jxh.ssm.service.impl;

import java.util.List;
import java.util.Map;

import cn.jxh.ssm.common.page.PageList;
import cn.jxh.ssm.common.page.PageProperty;
import cn.jxh.ssm.common.utils.Utils;
import cn.jxh.ssm.mapper.BaseMapper;
import cn.jxh.ssm.service.IBaseService;

public class BaseServiceImpl<T, I extends BaseMapper<T>> implements IBaseService<T> {

    private I baseMapper;

    public I getBaseMapper() {
        return baseMapper;
    }

    public void setBaseMapper(I baseMapper) {
        this.baseMapper = baseMapper;
    }

    public void insert(T po) {
        this.baseMapper.insert(po);
    }

    public int delete(Map<String, Object> param) {
        return baseMapper.delete(param);
    }

    public int update(T po) {
        return baseMapper.update(po);
    }

    public T get(Map<String, Object> param) {
        return baseMapper.get(param);
    }

    public List<T> list(Map<String, Object> param) {
        return baseMapper.list(param);
    }

    public int getCount(Map<String, Object> param) {
        return baseMapper.getCount(param);
    }

    public PageList<T> getPageList(PageProperty pp) {
        int count = baseMapper.getCount(pp.getParamMap());
        pp.putParamMap("startrow", pp.getStartRow());
        pp.putParamMap("pagesize", pp.getPageSize());
        pp.putParamMap("orderdir", pp.getOrderDir());
        pp.putParamMap("ordercolumn", pp.getOrderColumn());
        PageList<T> pageList = new PageList<T>(pp, count, baseMapper.getSplitList(pp.getParamMap()));
        return pageList;
    }

    public int getMapCount(Map<String, Object> param) {
        return baseMapper.getMapCount(param);
    }

    public PageList<Map<String, Object>> getPageMapList(PageProperty pp) {
        int count = baseMapper.getMapCount(pp.getParamMap());
        pp.putParamMap("startrow", pp.getStartRow());
        pp.putParamMap("pagesize", pp.getPageSize());
        pp.putParamMap("orderdir", pp.getOrderDir());
        pp.putParamMap("ordercolumn", pp.getOrderColumn());
        PageList<Map<String, Object>> pageList = new PageList<Map<String, Object>>(pp, count, baseMapper.getSplitMapList(pp.getParamMap()));
        return pageList;
    }


}
