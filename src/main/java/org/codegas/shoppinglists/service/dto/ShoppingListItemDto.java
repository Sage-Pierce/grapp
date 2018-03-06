package org.codegas.shoppinglists.service.dto;

import org.codegas.common.lang.value.AbstractDto;
import org.codegas.common.lang.value.CodeName;

public class ShoppingListItemDto extends AbstractDto {

    private CodeName item;

    private boolean obtained;

    public CodeName getItem() {
        return item;
    }

    public void setItem(CodeName item) {
        this.item = item;
    }

    public boolean isObtained() {
        return obtained;
    }

    public void setObtained(boolean obtained) {
        this.obtained = obtained;
    }
}
