package com.wcc.hanems.customized.dto;

import com.wcc.hanems.service.dto.PersonDTO;

public class Wcc103ReqDTO extends PersonDTO {

    private Long reasonId;

    public Long getReasonId() {
        return reasonId;
    }

    public void setReasonId(Long reasonId) {
        this.reasonId = reasonId;
    }
}
