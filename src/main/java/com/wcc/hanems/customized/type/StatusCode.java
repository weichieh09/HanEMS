package com.wcc.hanems.customized.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum StatusCode implements BasicStatusCode {
    FAIL("-1", "失敗"),
    SUCCESS("0", "成功");

    @Getter
    private final String code;

    @Getter
    private final String msg;
}
