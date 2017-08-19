package cn.jxh.ssm.service;

import cn.jxh.ssm.entity.SeMenu;

import java.util.Map;

public interface ISeMenuService extends IBaseService<SeMenu> {

    void deleteMenu(Map<String, Object> param);

}