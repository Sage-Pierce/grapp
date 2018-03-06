package org.codegas.stores.service.dto;

import java.util.List;

import org.codegas.commons.lang.value.CodeName;

public class ItemLineageDto {

    private CodeName item;

    private List<CodeName> lineage;

    public CodeName getItem() {
        return item;
    }

    public void setItem(CodeName item) {
        this.item = item;
    }

    public List<CodeName> getLineage() {
        return lineage;
    }

    public void setLineage(List<CodeName> lineage) {
        this.lineage = lineage;
    }
}
