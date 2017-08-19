package cn.jxh.ssm.service;

import cn.jxh.ssm.entity.SeUser;

import java.util.Map;

public interface ISeUserService extends IBaseService<SeUser> {

    void deleteUser(Map<String, Object> param);

}