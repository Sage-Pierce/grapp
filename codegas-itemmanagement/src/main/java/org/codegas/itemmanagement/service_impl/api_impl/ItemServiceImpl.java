package org.codegas.itemmanagement.service_impl.api_impl;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

import org.codegas.commons.lang.annotation.ApplicationService;
import org.codegas.commons.lang.annotation.Transactional;
import org.codegas.itemmanagement.domain.entity.Item;
import org.codegas.itemmanagement.domain.repository.ItemRepository;
import org.codegas.itemmanagement.domain.service.ItemCreationService;
import org.codegas.itemmanagement.domain.service.ItemUpdateService;
import org.codegas.itemmanagement.domain.value.Code;
import org.codegas.itemmanagement.domain.value.CodeType;
import org.codegas.itemmanagement.service.api.ItemService;
import org.codegas.itemmanagement.service.dto.ItemDto;
import org.codegas.itemmanagement.service.dto.ItemLineageDto;
import org.codegas.itemmanagement.service_impl.factory.ItemDtoFactory;
import org.codegas.itemmanagement.service_impl.factory.ItemLineageDtoFactory;

@Named
@Singleton
@Transactional
@ApplicationService
public class ItemServiceImpl implements ItemService {

    private final ItemCreationService itemCreationService;

    private final ItemUpdateService itemUpdateService;

    private final ItemRepository itemRepository;

    @Inject
    public ItemServiceImpl(ItemCreationService itemCreationService, ItemUpdateService itemUpdateService, ItemRepository itemRepository) {
        this.itemCreationService = itemCreationService;
        this.itemUpdateService = itemUpdateService;
        this.itemRepository = itemRepository;
    }

    @Override
    public ItemDto createGeneralItem(String codeType, String code, String name) {
        return ItemDtoFactory.createDto(itemCreationService.createGeneralItem(createItemCode(codeType, code), name));
    }

    @Override
    public ItemDto createSubItem(String superItemCode, String codeType, String code, String name) {
        return ItemDtoFactory.createDto(itemCreationService.createSubItem(Code.fromString(superItemCode), createItemCode(codeType, code), name));
    }

    @Override
    public List<ItemLineageDto> get() {
        return itemRepository.get().map(ItemLineageDtoFactory::createDto).collect(Collectors.toList());
    }

    @Override
    public List<ItemDto> getGeneralItems() {
        return itemRepository.getGeneralItems().stream().map(ItemDtoFactory::createDto).collect(Collectors.toList());
    }

    @Override
    public ItemDto get(String primaryCode) {
        return ItemDtoFactory.createDto(itemRepository.get(Code.fromString(primaryCode)));
    }

    @Override
    public ItemDto update(String primaryCode, String name) {
        Item item = itemRepository.get(Code.fromString(primaryCode));
        itemUpdateService.changeItemName(item, name);
        return ItemDtoFactory.createDto(item);
    }

    @Override
    public ItemDto makeGeneral(String primaryCode) {
        Item item = itemRepository.get(Code.fromString(primaryCode));
        item.makeGeneral();
        return ItemDtoFactory.createDto(item);
    }

    @Override
    public ItemDto move(String primaryCode, String superItemCode) {
        Item item = itemRepository.get(Code.fromString(primaryCode));
        Item superItem = itemRepository.get(Code.fromString(superItemCode));
        superItem.acceptSubItem(item);
        return ItemDtoFactory.createDto(item);
    }

    @Override
    public void delete(String primaryCode) {
        itemRepository.remove(Code.fromString(primaryCode));
    }

    private Code createItemCode(String codeType, String code) {
        CodeType type = CodeType.valueOf(codeType);
        return type == CodeType.RANDOM ? Code.random() : new Code(type, code);
    }
}
