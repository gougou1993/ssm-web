package cn.jxh.ssm.service;

import cn.jxh.ssm.entity.SeRole;
import cn.jxh.ssm.entity.SeRoleuser;

import java.util.List;
import java.util.Map;

public interface ISeRoleuserService extends IBaseService<SeRoleuser> {

    List<SeRole> listSeRolesByUsercode(Map<String, Object> param);

}