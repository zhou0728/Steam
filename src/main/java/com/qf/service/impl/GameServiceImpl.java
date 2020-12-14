package com.qf.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qf.common.ResultResp;
import com.qf.dao.GameMapper;
import com.qf.pojo.Game;
import com.qf.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameServiceImpl implements GameService {

    @Autowired
    GameMapper gameMapper;


    @Override
    public ResultResp findAll(Integer page, Integer limit) {
        PageHelper.startPage(page,limit);
        List<Game> all = gameMapper.findAll();
        PageInfo<Game> gamePageInfo = new PageInfo<>(all);
        ResultResp resultResp = new ResultResp();
        resultResp.setCode(200);
        resultResp.setData(all);
        resultResp.setTotal(gamePageInfo.getTotal());
        resultResp.setMessage("findAll查询全部成功！");
        return resultResp;
    }
}
