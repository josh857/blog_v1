package com.portal.webResult;

public enum ServiceCode {

    OK(200),
    CREATE(201),
    ACCEPTED(202),
    NO_CONTENT(204),
    NOTFOUND(404),
    Internal_Server_Error(500)
    ;



    private Integer value;
    ServiceCode(Integer value) {
        this.value=value;
    }

    public Integer getValue(){
        return value;
    }
}
