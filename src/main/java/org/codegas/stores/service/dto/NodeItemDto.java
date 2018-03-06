package org.codegas.stores.service.dto;

import org.codegas.common.lang.value.AbstractDto;
import org.codegas.common.lang.value.CodeName;

public class NodeItemDto extends AbstractDto {

    private CodeName item;

    public CodeName getItem() {
        return item;
    }

    public void setItem(CodeName item) {
        this.item = item;
    }
}
