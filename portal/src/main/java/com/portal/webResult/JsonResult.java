package com.portal.webResult;

import com.portal.exception.ServiceException;
import lombok.Data;
import lombok.Getter;
import java.io.Serializable;


@Data
public class JsonResult<T> implements Serializable {

    //狀態碼
      Integer Code ;

    //狀態訊息
     String message ;

    //泛型對象
     T data;


     /**
      * 以下為成功傳遞
      */
    /**
     * 傳遞data建構子
     */
    public JsonResult (T data){
        this.data=data;
    }

    /**
     * 傳遞message 建構子
     */
    public JsonResult(String message){
        this.message=message;
    }


    /**
     * data message 建構子
     * @param data
     * @param message
     */
    public JsonResult(T data , String message){
        this.data=data;
        this.message=message;
    }

    public JsonResult() {

    }

    /**
     *
     * @return
     */
    public static JsonResult<Void> ok() {
        return ok(null);
    }

    /**
     * 返回前端通訊用
     * @param data 泛型對象
     * @param <T>
     * @return 返回泛型對象
     */
    public static <T> JsonResult ok(T data) {
        JsonResult jsonResult = new JsonResult(data);
        jsonResult.Code=ServiceCode.OK.getValue();
        return jsonResult;
    }

    /**
     *以下為傳遞失敗
     */

    /**
     * 返回拋出的統一處理異常對象
     * @param e 自訂義異常對象
     * @return
     */
    public static JsonResult<Void> fail(ServiceException e) {
        return fail(e.getServiceCode(), e.getMessage());
    }

    /**
     *
     * @param serviceCode
     * @param message
     * @return
     */
    public static JsonResult<Void> fail(Integer serviceCode, String message) {
        JsonResult jsonResult = new JsonResult(message);
        jsonResult.Code = serviceCode;
        return jsonResult;
    }
}
