package cn.jxh.ssm.service;

import cn.jxh.ssm.entity.Seq;

public interface ISeqService extends IBaseService<Seq> {

	/**
	 * 获取一般ID
	 * 
	 * @return
	 */
    long getSeqPurNextVal();

}
