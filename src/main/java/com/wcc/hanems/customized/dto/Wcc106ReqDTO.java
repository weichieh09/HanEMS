package com.wcc.hanems.customized.dto;

import com.wcc.hanems.service.dto.BlackListDTO;

public class Wcc106ReqDTO extends BlackListDTO {

    private String personIdno;

    private String personName;

    public String getPersonIdno() {
        return personIdno;
    }

    public void setPersonIdno(String personIdno) {
        this.personIdno = personIdno;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }
}
