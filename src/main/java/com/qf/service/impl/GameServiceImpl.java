package com.qf.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qf.common.ResultResp;
import com.qf.dao.GameMapper;
import com.qf.pojo.Game;
import com.qf.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
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

    @Override
    public ResultResp findOne(Integer id) {
        ResultResp resultResp = new ResultResp();
        if (id != null && !(id.toString().equals(""))){
            Game game = gameMapper.findOne(id);
            if (game != null){
                resultResp.setCode(200);
                resultResp.setData(game);
                resultResp.setMessage("查询一个成功！！");
                return resultResp;
            }
            resultResp.setCode(202);
            resultResp.setMessage("没有找到id="+id);
            return resultResp;
        }
        resultResp.setCode(201);
        return resultResp;
    }

    @Override
    public ResultResp addShopCart(Integer id, HttpServletRequest request) {
        String key = "";

        return null;
    }


    public String getToken(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.equals("token")){
                return cookie.getValue();
            }
        }
        return null;
    }
}
