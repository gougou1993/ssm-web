package cn.jxh.ssm.service;

import cn.jxh.ssm.entity.SeMenu;
import cn.jxh.ssm.entity.SeRolemenu;

import java.util.List;
import java.util.Map;

public interface ISeRolemenuService extends IBaseService<SeRolemenu> {

    List<SeMenu> listSeMenuByUsercode(Map<String, Object> param);

}