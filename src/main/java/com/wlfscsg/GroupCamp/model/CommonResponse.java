package com.wlfscsg.GroupCamp.model;

public enum CommonResponse {
    SUXS(0),
    ALREADY_HAS(1),
    FAIL(99),
    ABNORMAL(100),
    DB_ERROR(101),
    NOT_EXIST(102),
    NOT_MATCHED(103);
    private int code;
    CommonResponse(int code) {
        this.code = code;
    }
}
