package com.wcc.hanems.customized.service;

import com.wcc.hanems.customized.dto.CreateEquipmentReq;
import com.wcc.hanems.customized.dto.EqItemDropdownsDTO;
import com.wcc.hanems.customized.dto.UpdateEquipmentReq;
import com.wcc.hanems.service.dto.EqItemDTO;
import com.wcc.hanems.service.dto.EquipmentDTO;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class Wcc106Service {

    private final String CLASS_NAME = this.getClass().getSimpleName();

    public EquipmentDTO copyProperties(CreateEquipmentReq source) {
        EquipmentDTO target = new EquipmentDTO();
        target.setId(source.getId());
        target.setEqItemId(source.getEqItemId());
        target.setName(source.getEqName());
        target.setDescription(source.getEqDescription());
        target.setCreateDate(Instant.now());
        target.setModifyDate(Instant.now());
        return target;
    }

    public EquipmentDTO copyProperties(UpdateEquipmentReq source) {
        EquipmentDTO target = new EquipmentDTO();
        target.setId(source.getId());
        target.setEqItemId(source.getEqItemId());
        target.setName(source.getEqName());
        target.setDescription(source.getEqDescription());
        target.setCreateDate(Instant.now());
        target.setModifyDate(Instant.now());
        return target;
    }

    public List<EqItemDropdownsDTO> copyProperties(List<EqItemDTO> byCriteria) {
        List<EqItemDropdownsDTO> result = new ArrayList<>();

        EqItemDropdownsDTO nDto = new EqItemDropdownsDTO();
        nDto.setValue(null);
        nDto.setText("請選擇");
        result.add(nDto);

        for (EqItemDTO oDto : byCriteria) {
            nDto = new EqItemDropdownsDTO();
            nDto.setValue(oDto.getId());
            nDto.setText(oDto.getName());
            result.add(nDto);
        }

        return result;
    }
}
