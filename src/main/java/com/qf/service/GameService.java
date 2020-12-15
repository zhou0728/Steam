package com.qf.service;

import com.qf.common.ResultResp;

import javax.servlet.http.HttpServletRequest;

public interface GameService {

    ResultResp findAll(Integer page, Integer limit);


    ResultResp findOne(Integer id);

    ResultResp addShopCart(Integer id, HttpServletRequest request);
}
