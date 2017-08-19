package cn.jxh.ssm.service;

import cn.jxh.ssm.entity.SeRole;

import java.util.Map;

public interface ISeRoleService extends IBaseService<SeRole> {

    void deleteRole(Map<String, Object> param);

}