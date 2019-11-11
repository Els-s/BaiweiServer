package com.els.baiwei.service.emp;

import com.els.baiwei.mapper.JObLevelMapper;
import com.els.baiwei.model.JObLevel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Despt:
 * @Author: Els-s
 * @Time: 2019/11/9 9:17
 */
@Service
public class JobLevelService {

    @Autowired
    JObLevelMapper jObLevelMapper;


    public List<JObLevel> getJobLevels() {
        return jObLevelMapper.getJobLevels();
    }
}
