package net.lab1024.smartadmin.module.business.erp.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SpecDTO {
    private String id;
    private String attr;
    private String attrValue;

    public String format() {
        return attr + ":" + attrValue;
    }
}
