package com.qf.dao;


import com.qf.pojo.GameDetails;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface GameDetailsMapper {

    GameDetails findOne(@Param("id")Integer id);
}
