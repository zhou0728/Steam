package com.qf.common;


import lombok.Data;

@Data
public class ResultResp {

    private Integer code;

    private Object data;

    private  String message;

    private Long total;


}
