package com.oocl.easymovie.dto;

import lombok.Data;

@Data
public class ResultData<T> {
    private String stats;
    private T date;
    private String message;
    private long timestamp;

    public ResultData() {
        this.timestamp = System.currentTimeMillis();
    }

    public static <T> ResultData<T> success(T data) {
        ResultData<T> resultData = new ResultData<>();
        resultData.setStats("success");
        resultData.setDate(data);
        return resultData;
    }
}
