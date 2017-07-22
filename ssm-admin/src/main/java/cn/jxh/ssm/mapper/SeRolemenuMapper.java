package cn.jxh.ssm.mapper;

import cn.jxh.ssm.entity.SeMenu;
import cn.jxh.ssm.entity.SeRolemenu;

import java.util.List;
import java.util.Map;

public interface SeRolemenuMapper extends BaseMapper<SeRolemenu>{

    List<SeMenu> listSeMenuByUsercode(Map<String, Object> param);

}
