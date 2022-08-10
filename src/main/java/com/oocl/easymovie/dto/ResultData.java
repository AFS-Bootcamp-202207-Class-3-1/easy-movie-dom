package com.oocl.easymovie.dto;

import lombok.Data;

@Data
public class ResultData<T> {
  private String status;
  private T data;
  private long timestamp ;

  public ResultData (){
    this.timestamp = System.currentTimeMillis();
  }

  public static <T> ResultData<T> success(T data) {
    ResultData<T> resultData = new ResultData<>();
    resultData.setStatus("success");
    resultData.setData(data);
    return resultData;
  }
  public static ResultData<Object> success(){
    ResultData<Object> resultData=new ResultData<>();
    resultData.setStatus("success");
    resultData.setData(null);
    resultData.setTimestamp(System.currentTimeMillis());
    return resultData;
  }

}

