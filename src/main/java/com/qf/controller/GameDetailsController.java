package com.qf.controller;

import com.qf.common.ResultResp;
import com.qf.service.GameDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/gameDetails")
public class GameDetailsController {

    @Autowired
    GameDetailsService gameDetailsService;


    @RequestMapping(value = "/findOne",method = RequestMethod.POST)
    public ResultResp findOne(@RequestBody Map map){
        return gameDetailsService.findOne((Integer) map.get("id"));
    }

}
