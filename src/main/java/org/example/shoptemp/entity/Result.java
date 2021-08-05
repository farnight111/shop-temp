package org.example.shoptemp.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Result<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 0成功 -1失败
     */
    private int code;
    private String msg;
    private T data;

    public Result(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> Result<T> success() {

        return new Result<>(0, "success", null);
    }

    public static <T> Result<T> success(T data) {

        return new Result<>(0, "success", data);
    }

    public static <T> Result<T> success(T data, String msg) {
        return new Result<>(0, msg, data);
    }

    public static <T> Result<T> fail() {
        return new Result<>(-1, "fail", null);
    }

    public static <T> Result<T> fail(String msg) {
        return new Result<>(-1, msg, null);
    }

    public static <T> Result<T> fail(T data) {
        return new Result<>(-1, "fail", data);
    }

    public static <T> Result<T> fail(T data, String msg) {
        return new Result<>(-1, msg, data);
    }

    public static <T> Result<T> fail(int code, String msg) {
        return new Result<>(code, msg, null);
    }

}