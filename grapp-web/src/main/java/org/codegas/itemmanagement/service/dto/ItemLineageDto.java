package org.codegas.itemmanagement.service.dto;

import java.util.List;

import org.codegas.commons.lang.value.CodeName;

public class ItemLineageDto {

    private String primaryCode;

    private String name;

    private List<CodeName> lineage;

    public String getPrimaryCode() {
        return primaryCode;
    }

    public void setPrimaryCode(String primaryCode) {
        this.primaryCode = primaryCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CodeName> getLineage() {
        return lineage;
    }

    public void setLineage(List<CodeName> lineage) {
        this.lineage = lineage;
    }
}
