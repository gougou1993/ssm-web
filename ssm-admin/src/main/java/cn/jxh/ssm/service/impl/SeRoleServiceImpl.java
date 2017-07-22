package cn.jxh.ssm.service.impl;


import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.jxh.ssm.mapper.SeRoleMapper;
import cn.jxh.ssm.entity.SeRole;
import cn.jxh.ssm.service.ISeRoleService;

@Service(value="seRoleService")
public class SeRoleServiceImpl extends BaseServiceImpl<SeRole,SeRoleMapper> implements ISeRoleService,InitializingBean {

	@Autowired
	private SeRoleMapper seRoleMapper;
	
	public void afterPropertiesSet() throws Exception {
		this.setBaseMapper(seRoleMapper);
	}

}
