package com.qf;


import com.qf.common.ResultResp;
import com.qf.dao.GameMapper;
import com.qf.pojo.Game;
import com.qf.service.impl.GameServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestGame {

    @Autowired
    GameServiceImpl gameServiceImpl;

    @Autowired
    GameMapper gameMapper;

    @Test
    public void testFindAll(){

        ResultResp all = gameServiceImpl.findAll(1, 3);

        List<Game> data =(List<Game>) all.getData();

        System.out.println("data = " + data);
    }

    @Test
    public void testFindOne(){
        Game one = gameMapper.findOne(8);
        System.out.println("one = " + one);
    }

}
