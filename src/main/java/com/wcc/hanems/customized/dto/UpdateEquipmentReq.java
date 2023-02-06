package com.wcc.hanems.customized.dto;

import lombok.Data;

@Data
public class UpdateEquipmentReq {

    private Long id;

    private Long eqItemId;

    private String eqName;

    private String eqDescription;
}
