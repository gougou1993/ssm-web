package cn.jxh.ssm.service.impl;


import cn.jxh.ssm.mapper.SeRolemenuMapper;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.jxh.ssm.mapper.SeMenuMapper;
import cn.jxh.ssm.entity.SeMenu;
import cn.jxh.ssm.service.ISeMenuService;

import java.util.Map;

@Service(value="seMenuService")
public class SeMenuServiceImpl extends BaseServiceImpl<SeMenu,SeMenuMapper> implements ISeMenuService,InitializingBean {

	@Autowired
	private SeMenuMapper seMenuMapper;

	@Autowired
	private SeRolemenuMapper seRoleMenuMapper;
	
	public void afterPropertiesSet() throws Exception {
		this.setBaseMapper(seMenuMapper);
	}

	@Override
	public void deleteMenu(Map<String, Object> param) {
		seMenuMapper.delete(param);
		seRoleMenuMapper.delete(param);
	}
}
