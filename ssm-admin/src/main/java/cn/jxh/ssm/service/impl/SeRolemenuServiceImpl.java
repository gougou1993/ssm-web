package cn.jxh.ssm.service.impl;


import cn.jxh.ssm.entity.SeMenu;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.jxh.ssm.mapper.SeRolemenuMapper;
import cn.jxh.ssm.entity.SeRolemenu;
import cn.jxh.ssm.service.ISeRolemenuService;

import java.util.List;
import java.util.Map;

@Service(value="seRolemenuService")
public class SeRolemenuServiceImpl extends BaseServiceImpl<SeRolemenu,SeRolemenuMapper> implements ISeRolemenuService,InitializingBean {

	@Autowired
	private SeRolemenuMapper seRolemenuMapper;
	
	public void afterPropertiesSet() throws Exception {
		this.setBaseMapper(seRolemenuMapper);
	}

	@Override
	public List<SeMenu> listSeMenuByUsercode(Map<String, Object> param) {
		return seRolemenuMapper.listSeMenuByUsercode(param);
	}

}
