package com.portal.exception.handler;

import com.portal.exception.ServiceException;
import com.portal.webResult.JsonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 後續待完善
 *
 * @author Josh
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * ServiceException 自定義異常統一處理
     * @return  拋出JsonResult
     */
    @ExceptionHandler(value = ServiceException.class)
    @ResponseBody
    public JsonResult handlerServiceException(ServiceException e){
        return JsonResult.fail(e);
    }




}
