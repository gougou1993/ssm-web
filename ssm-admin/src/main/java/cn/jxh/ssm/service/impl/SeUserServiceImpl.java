package cn.jxh.ssm.service.impl;


import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.jxh.ssm.mapper.SeUserMapper;
import cn.jxh.ssm.entity.SeUser;
import cn.jxh.ssm.service.ISeUserService;

@Service(value="seUserService")
public class SeUserServiceImpl extends BaseServiceImpl<SeUser,SeUserMapper> implements ISeUserService,InitializingBean {

	@Autowired
	private SeUserMapper seUserMapper;
	
	public void afterPropertiesSet() throws Exception {
		this.setBaseMapper(seUserMapper);
	}

}
