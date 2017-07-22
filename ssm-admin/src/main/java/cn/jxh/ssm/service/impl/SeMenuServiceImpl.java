package cn.jxh.ssm.service.impl;


import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.jxh.ssm.mapper.SeMenuMapper;
import cn.jxh.ssm.entity.SeMenu;
import cn.jxh.ssm.service.ISeMenuService;

@Service(value="seMenuService")
public class SeMenuServiceImpl extends BaseServiceImpl<SeMenu,SeMenuMapper> implements ISeMenuService,InitializingBean {

	@Autowired
	private SeMenuMapper seMenuMapper;
	
	public void afterPropertiesSet() throws Exception {
		this.setBaseMapper(seMenuMapper);
	}

}
