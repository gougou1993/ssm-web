package cn.jxh.ssm.service.impl;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.jxh.ssm.entity.Seq;
import cn.jxh.ssm.mapper.SeqMapper;
import cn.jxh.ssm.service.ISeqService;

@Service(value = "seqService")
public class SeqServiceImpl extends BaseServiceImpl<Seq, SeqMapper> implements ISeqService, InitializingBean {

	@Autowired
	private SeqMapper seqMapper;

	public void afterPropertiesSet() throws Exception {
		this.setBaseMapper(seqMapper);
	}

	@Override
	public long getSeqPurNextVal() {
		return seqMapper.getSeqPurNextVal();
	}

}
