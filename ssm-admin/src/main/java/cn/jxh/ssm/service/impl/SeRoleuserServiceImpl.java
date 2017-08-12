package cn.jxh.ssm.service.impl;


import cn.jxh.ssm.entity.SeRole;
import cn.jxh.ssm.entity.SeUser;
import cn.jxh.ssm.mapper.SeRoleMapper;
import cn.jxh.ssm.mapper.SeqMapper;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.jxh.ssm.mapper.SeRoleuserMapper;
import cn.jxh.ssm.entity.SeRoleuser;
import cn.jxh.ssm.service.ISeRoleuserService;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service(value="seRoleuserService")
public class SeRoleuserServiceImpl extends BaseServiceImpl<SeRoleuser,SeRoleuserMapper> implements ISeRoleuserService,InitializingBean {

	@Autowired
	private SeqMapper seqMapper;

	@Autowired
	private SeRoleuserMapper seRoleuserMapper;
	
	public void afterPropertiesSet() throws Exception {
		this.setBaseMapper(seRoleuserMapper);
	}


	@Override
	public List<SeRole> listSeRolesByUsercode(Map<String, Object> param) {
		return seRoleuserMapper.listSeRolesByUsercode(param);
	}

	@Override
	public List<SeUser> listSeUserByRolecode(Map<String, Object> param) {
		return seRoleuserMapper.listSeUserByRolecode(param);
	}

	@Override
	public List<SeUser> listSeUserByName(Map<String, Object> param) {
		return seRoleuserMapper.listSeUserByName(param);
	}

	@Override
	@Transactional
	public void relationRoleUser(Map<String, Object> param) {

		String[] userList = String.valueOf(param.get("userList")).split(",");

		seRoleuserMapper.delete(param);

		for(int i=0;i<userList.length;i++){
			SeRoleuser seRoleuser = new SeRoleuser();
			//获取squ顺序编号
			Long seqid = seqMapper.getSeqPurNextVal();
			Date nowDate = new Date ();

			seRoleuser.setId(seqid);
			seRoleuser.setRolecode(String.valueOf(param.get("rolecode")));
			seRoleuser.setUsercode(userList[i]);
			seRoleuser.setEntrytime(nowDate);
			seRoleuser.setUpdatetime(nowDate);
			seRoleuser.setUpdateid(seqid);

			seRoleuserMapper.insert(seRoleuser);
		}
	}


}
