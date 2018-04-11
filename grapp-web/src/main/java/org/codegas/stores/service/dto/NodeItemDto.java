package org.codegas.stores.service.dto;

import org.codegas.commons.lang.value.AbstractDto;
import org.codegas.commons.lang.value.CodeName;

public class NodeItemDto extends AbstractDto {

    private CodeName item;

    public CodeName getItem() {
        return item;
    }

    public void setItem(CodeName item) {
        this.item = item;
    }
}
