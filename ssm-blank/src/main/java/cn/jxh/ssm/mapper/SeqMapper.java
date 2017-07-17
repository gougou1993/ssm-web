package cn.jxh.ssm.mapper;

import cn.jxh.ssm.entity.Seq;

public interface SeqMapper extends BaseMapper<Seq> {


	long getSeqPurNextVal();

}
