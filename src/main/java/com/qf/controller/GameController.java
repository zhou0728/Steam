package com.qf.controller;


import com.qf.common.ResultResp;
import com.qf.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/game")
public class GameController {

    @Autowired
    GameService gameService;

    @RequestMapping(value = "/findAll/{page}/{limit}",method = RequestMethod.GET)
    public ResultResp findAll(@PathVariable("page") Integer page, @PathVariable("limit")Integer limit){

        return gameService.findAll(page,limit);
    }

}
