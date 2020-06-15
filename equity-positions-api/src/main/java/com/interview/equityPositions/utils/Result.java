package com.interview.equityPositions.utils;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class Result<T> implements Serializable {
    private static final long serialVersionUID = -1802122468331526708L;

    private int status = 200;
    private String message = "ok";
    private T data;

    private Result(T data){
        this.data = data;
    }

    public Result(int status, String message){
        this.status = status;
        this.message = message;
    }

    public static <T> Result<T> ok(T data){
        return new Result<T>(data);
    }

    public static Result err(){
        return new Result(500, "system error");
    }

    public static Result info(String message){
        return new Result(400, message);
    }
}
