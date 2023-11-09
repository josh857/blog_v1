package com.portal.exception;

import com.portal.webResult.ServiceCode;
import lombok.Data;

@Data
public class ServiceException extends RuntimeException{

    private Integer serviceCode;

    private String message;

    /**
     * 建構子
     * @param serviceCode
     * @param message
     */
    public ServiceException(Integer serviceCode,String message) {
        this.message=message;
        this.serviceCode = serviceCode;
    }

    /**
     * 建構子
     * @param message
     */
    public ServiceException(String message) {
        this.message=message;
        this.serviceCode = ServiceCode.OK.getValue();
    }

    /**
     * 自訂義方法 表數據庫連接問題
     * @return
     */
   public static ServiceException busy(){
        return new ServiceException(ServiceCode.NO_CONTENT.getValue(), "數據庫繁忙中請稍後再試");
   }

    /**
     * 自訂義方法 連接成功但找不到資料
     * @return
     */
   public static ServiceException no_data(){
        return new ServiceException(ServiceCode.NO_CONTENT.getValue(), "找不到資料");
   }
}
