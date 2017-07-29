package cn.jxh.ssm.common.page;

import java.io.Serializable;
import java.util.HashMap;

public class PageProperty implements Serializable {

    private int startRow;// 查询起点
    private int pageSize;// 查询数量，0表示全部
    private String orderColumn;// 排序列
    private String orderDir;// 排序顺序
    private HashMap<String, Object> paramMap; // 参数map
    private int draw;

    public PageProperty() {
        this.startRow = 0;
        this.pageSize = 10;
        this.orderColumn = "";
        this.orderDir = "";
        this.draw =0;
    }

    public int getStartRow() {
        return startRow;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getOrderColumn() {
        return orderColumn;
    }

    public void setOrderColumn(String orderColumn) {
        this.orderColumn = orderColumn;
    }

    public String getOrderDir() {
        return orderDir;
    }

    public void setOrderDir(String orderDir) {
        this.orderDir = orderDir;
    }

    public HashMap<String, Object> getParamMap() {
        initParamMap();
        return paramMap;
    }

    public void setParamMap(HashMap<String, Object> paramMap) {
        this.paramMap = paramMap;
    }

    public void putParamMap(String name, Object o) { // 增加参数
        initParamMap();
        paramMap.put(name, o);
    }

    public void clearParamMap() { // 增加参数
        initParamMap();
        paramMap.clear();
    }

    public void initParamMap() {// 初始化参数列表
        if (paramMap == null) {
            paramMap = new HashMap();
        }
    }

    public int getDraw() {
        return draw;
    }

    public void setDraw(int draw) {
        this.draw = draw;
    }
}
