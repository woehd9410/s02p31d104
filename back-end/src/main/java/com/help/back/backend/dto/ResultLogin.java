package com.help.back.backend.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.springframework.http.HttpStatus;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class ResultLogin {
    @Override
    public String toString() {
        return "Result [statusCode=" + statusCode + ", message=" + message + ", data=" + data + ", totalCount="
                + totalCount + "]";
    }

    public static final String SUCCESS_MESSAGE = "성공";
    public static final String SERVER_ERROR_MESSAGE = "실패";

    private HttpStatus statusCode;
    private String message;
    private Object data;
    private int totalCount;
    private String token;

    public ResultLogin() {}

    public ResultLogin(HttpStatus statusCode, String message, Object data, int totalCount) {
        super();
        this.statusCode = statusCode;
        this.message = message;
        this.data = data;
        this.totalCount = totalCount;
    }

    public static ResultLogin successInstance(){
        return new ResultLogin().success();
    }

    public ResultLogin success(){
        statusCode = HttpStatus.OK;
        message = SUCCESS_MESSAGE;
        return this;
    }


    public String getToken() {
        return token;
    }

    public ResultLogin setToken(String token) {
        this.token = token;
        return this;
    }

    public ResultLogin fail(){
        statusCode = HttpStatus.INTERNAL_SERVER_ERROR;
        message = SERVER_ERROR_MESSAGE;
        return this;
    }

    public int getStatusCode() {
        return statusCode.value();
    }

    public ResultLogin setStatusCode(HttpStatus statusCode) {
        this.statusCode = statusCode;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ResultLogin setMessage(String message) {
        this.message = message;
        return this;
    }

    public Object getData() {
        return data;
    }

    public ResultLogin setData(Object data) {
        this.data = data;
        return this;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public ResultLogin setTotalCount(int totalCount) {
        this.totalCount = totalCount;
        return this;
    }



}
