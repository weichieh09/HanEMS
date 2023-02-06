package com.wcc.hanems.customized.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.wcc.hanems.customized.type.BasicStatusCode;
import com.wcc.hanems.customized.type.StatusCode;
import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseDTO<T> implements Serializable {

    private static final long serialVersionUID = 3937533115408118803L;

    @Setter
    @Getter
    private String code;

    @Setter
    @Getter
    private String msg;

    @Setter
    @Getter
    T content;

    public ResponseDTO() {
        this.setStatusCode(StatusCode.FAIL);
    }

    public ResponseDTO(BasicStatusCode statusCode) {
        this.setStatusCode(statusCode);
    }

    public ResponseDTO(T content) {
        this.content = content;
        this.setStatusCode(StatusCode.SUCCESS);
    }

    public ResponseDTO(T content, BasicStatusCode statusCode) {
        this.content = content;
        this.setStatusCode(statusCode);
    }

    public ResponseDTO(String msg, String code) {
        this.msg = msg;
        this.code = code;
    }

    public void setStatusCode(BasicStatusCode statusCode) {
        if (statusCode != null) {
            this.msg = statusCode.getMsg();
            this.code = statusCode.getCode();
        }
    }
}
