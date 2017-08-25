package cn.jxh.ssm.service.impl;


import cn.jxh.ssm.common.utils.Utils;
import cn.jxh.ssm.entity.SeMenu;
import cn.jxh.ssm.mapper.SeqMapper;
import cn.jxh.ssm.service.ISeRolemenuService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.jxh.ssm.mapper.SeRolemenuMapper;
import cn.jxh.ssm.entity.SeRolemenu;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service(value = "seRolemenuService")
public class SeRolemenuServiceImpl extends BaseServiceImpl<SeRolemenu, SeRolemenuMapper> implements ISeRolemenuService, InitializingBean {

    @Autowired
    private SeqMapper seqMapper;

    @Autowired
    private SeRolemenuMapper seRolemenuMapper;

    public void afterPropertiesSet() throws Exception {
        this.setBaseMapper(seRolemenuMapper);
    }

    @Override
    public List<SeMenu> listSeMenuByUsercode(Map<String, Object> param) {
        return seRolemenuMapper.listSeMenuByUsercode(param);
    }

    @Override
    @Transactional
    public void relationRoleMenu(Map<String, Object> param) {

        String[] menuIds = String.valueOf(param.get("menuIds")).split(",");

        seRolemenuMapper.delete(param);

        for (String menuId : menuIds) {
            if(!Utils.strIsNull(menuId)){
                SeRolemenu seRolemenu = new SeRolemenu();

                Long seqid = seqMapper.getSeqPurNextVal();
                Date nowDate = new Date();

                seRolemenu.setId(seqid);
                seRolemenu.setRolecode(String.valueOf(param.get("rolecode")));
                seRolemenu.setMenucode(menuId);
                seRolemenu.setEntrytime(nowDate);
                seRolemenu.setUpdatetime(nowDate);
                seRolemenu.setUpdateid(seqid);

                seRolemenuMapper.insert(seRolemenu);
            }
        }
    }

}
