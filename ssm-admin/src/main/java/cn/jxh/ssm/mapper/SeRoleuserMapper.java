package cn.jxh.ssm.mapper;

import cn.jxh.ssm.entity.SeRole;
import cn.jxh.ssm.entity.SeRoleuser;

import java.util.List;
import java.util.Map;

public interface SeRoleuserMapper extends BaseMapper<SeRoleuser> {

    List<SeRole> listSeRolesByUsercode(Map<String, Object> param);

}
