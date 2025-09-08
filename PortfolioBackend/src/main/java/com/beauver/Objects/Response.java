package com.beauver.Objects;

import com.google.gson.Gson;

public class Response<T>{
    public int Status;
    public String Message;
    public T Data;

    public Response(int status, String message, T data) {
        Status = status;
        Message = message;
        Data = data;
    }

    public Response(int status, String message) {
        Status = status;
        Message = message;
        Data = null;
    }

    public String toJSON(){
        return new Gson().toJson(this, this.getClass());
    }
}
