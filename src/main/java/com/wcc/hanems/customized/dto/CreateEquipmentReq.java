package com.wcc.hanems.customized.dto;

import lombok.Data;

@Data
public class CreateEquipmentReq {

    private Long id;

    private Long eqItemId;

    private String eqName;

    private String eqDescription;
}
