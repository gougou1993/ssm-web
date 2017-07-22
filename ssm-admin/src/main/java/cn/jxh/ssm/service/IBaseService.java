package cn.jxh.ssm.service;

import java.util.List;
import java.util.Map;

import cn.jxh.ssm.common.page.PageList;
import cn.jxh.ssm.common.page.PageProperty;

public interface IBaseService<T> {

    void insert(T po);

    int delete(Map<String, Object> param);

    int update(T po);

    T get(Map<String, Object> param);

    List<T> list(Map<String, Object> param);

    int getCount(Map<String, Object> param);

    PageList<T> getPageList(PageProperty pp);

    int getMapCount(Map<String, Object> param);

    PageList<Map<String, Object>> getPageMapList(PageProperty pp);


}
