package com.qf.service.impl;

import com.qf.common.ResultResp;
import com.qf.dao.GameDetailsMapper;
import com.qf.pojo.GameDetails;
import com.qf.service.GameDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class GameDetailsServiceImpl implements GameDetailsService {

    @Autowired
    GameDetailsMapper gameDetailsMapper;

    @Override
    public ResultResp findOne(Integer id) {
        ResultResp resultResp = new ResultResp();
        if (id != null && !(id.toString().equals(""))){
            GameDetails one = gameDetailsMapper.findOne(id);
            if (one != null){
                resultResp.setCode(200);
                resultResp.setData(one);
                resultResp.setMessage("查询一个成功！");
                return resultResp;
            }
            resultResp.setCode(202);
            resultResp.setMessage("不存在id="+id);
            return resultResp;
        }
        resultResp.setCode(201);
        resultResp.setMessage("id不是一个数字");
        return null;
    }
}
