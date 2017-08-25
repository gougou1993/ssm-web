package cn.jxh.ssm.service.impl;


import cn.jxh.ssm.mapper.SeRolemenuMapper;
import cn.jxh.ssm.mapper.SeRoleuserMapper;
import cn.jxh.ssm.service.ISeRoleService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.jxh.ssm.mapper.SeRoleMapper;
import cn.jxh.ssm.entity.SeRole;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service(value="seRoleService")
public class SeRoleServiceImpl extends BaseServiceImpl<SeRole,SeRoleMapper> implements ISeRoleService,InitializingBean {

	@Autowired
	private SeRoleMapper seRoleMapper;

	@Autowired
	private SeRoleuserMapper seRoleUserMapper;

	@Autowired
	private SeRolemenuMapper seRoleMenuMapper;
	
	public void afterPropertiesSet() throws Exception {
		this.setBaseMapper(seRoleMapper);
	}

	@Override
	@Transactional
	public void deleteRole(Map<String, Object> param) {
		//删除角色表中的数据
		seRoleMapper.delete(param);
		//删除关联菜单中的数据
		seRoleUserMapper.delete(param);
		//删除关联角色的数据
		seRoleMenuMapper.delete(param);
	}
}
