package cn.jxh.ssm.service.impl;


import cn.jxh.ssm.entity.SeRole;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.jxh.ssm.mapper.SeRoleuserMapper;
import cn.jxh.ssm.entity.SeRoleuser;
import cn.jxh.ssm.service.ISeRoleuserService;

import java.util.List;
import java.util.Map;

@Service(value="seRoleuserService")
public class SeRoleuserServiceImpl extends BaseServiceImpl<SeRoleuser,SeRoleuserMapper> implements ISeRoleuserService,InitializingBean {

	@Autowired
	private SeRoleuserMapper seRoleuserMapper;
	
	public void afterPropertiesSet() throws Exception {
		this.setBaseMapper(seRoleuserMapper);
	}


	@Override
	public List<SeRole> listSeRolesByUsercode(Map<String, Object> param) {
		return seRoleuserMapper.listSeRolesByUsercode(param);
	}

}
