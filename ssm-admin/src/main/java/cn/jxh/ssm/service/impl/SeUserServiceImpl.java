package cn.jxh.ssm.service.impl;


import cn.jxh.ssm.mapper.SeRoleuserMapper;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.jxh.ssm.mapper.SeUserMapper;
import cn.jxh.ssm.entity.SeUser;
import cn.jxh.ssm.service.ISeUserService;

import java.util.Map;

@Service(value="seUserService")
public class SeUserServiceImpl extends BaseServiceImpl<SeUser,SeUserMapper> implements ISeUserService,InitializingBean {

	@Autowired
	private SeUserMapper seUserMapper;

	@Autowired
	private SeRoleuserMapper seRoleuserMapper;
	
	public void afterPropertiesSet() throws Exception {
		this.setBaseMapper(seUserMapper);
	}

	@Override
	public void deleteUser(Map<String, Object> param) {
		//删除用户表中的数据
		seUserMapper.delete(param);
		//删除关联角色的数据
		seRoleuserMapper.delete(param);
	}
}
