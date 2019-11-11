package com.els.baiwei.service.emp;

import com.els.baiwei.mapper.NationMapper;
import com.els.baiwei.model.Nation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Despt:
 * @Author: Els-s
 * @Time: 2019/11/8 22:41
 */
@Service
public class NationService {

    @Autowired
    NationMapper nationMapper;


    public List<Nation> getAllNation() {
       return nationMapper.getAllNation();
    }
}
