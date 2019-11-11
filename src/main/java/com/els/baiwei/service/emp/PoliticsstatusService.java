package com.els.baiwei.service.emp;

import com.els.baiwei.mapper.PoliticsstatusMapper;
import com.els.baiwei.mapper.PositionMapper;
import com.els.baiwei.model.Politicsstatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Despt:
 * @Author: Els-s
 * @Time: 2019/11/9 9:17
 */
@Service
public class PoliticsstatusService {

    @Autowired
    PoliticsstatusMapper politicsstatusMapper;

    public List<Politicsstatus> getPoliticsstatus() {
        return politicsstatusMapper.getPoliticsstatus();
    }
}
