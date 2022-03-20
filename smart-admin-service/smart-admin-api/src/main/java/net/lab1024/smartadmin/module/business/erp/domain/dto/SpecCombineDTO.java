package net.lab1024.smartadmin.module.business.erp.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SpecCombineDTO {
    private List<String> ids;

    private List<String> formatAttrs;

    private List<SpecDTO> source;
}
