package cn.jxh.ssm.mapper;

import java.util.List;
import java.util.Map;

public interface BaseMapper<T> {
	
	/**
     * @Description:创建数据对象
     * @param po 实体对象
     */
    void insert(T po);
    
    /**
     * @Description:删除数据对象
     * @param param map参数
     * @return 所影响的行数
     */
    int delete(Map<String, Object> param);
    
    /**
     * @Description:单条修改数据对象
     * @param po 实体对象
     * @return 所影响的行数
     */
    int update(T po);
	
	/**
     * @Description:得到数据对象
     * @param param map参数
     * @return 实体对象
     */
    T get(Map<String, Object> param);
    
    /**
     * @Description:得到数据对象列表
     * @param param map参数
     * @return 实体列表
     */
    List<T> list(Map<String, Object> param);
    
    /**
     * @Description:得到数据数量按分页条件
     * @param pp PageProperty对象
     * @return 数据条数
     */
    int getCount(Map<String, Object> param);
 
    /**
     * @Description:得到数据对象列表按分页条件 当pp.getNpageSize=0时返回所有
     * @param pp PageProperty对象
     * @return 实体列表
     */
    List<T> getSplitList(Map<String, Object> param);
    
    /**
     * @Description:得到数据数量按分页条件
     * @param pp PageProperty对象
     * @return 数据条数
     */
    int getMapCount(Map<String, Object> param);
 
    
    /**
     * @Description:得到数据对象列表按分页条件 当pp.getNpageSize=0时返回所有 返回的为PageList<Map<String, Object>>类型
     * @param pp PageProperty对象
     * @return 实体列表
     */
    List<Map<String, Object>> getSplitMapList(Map<String, Object> param);


}
