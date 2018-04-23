package org.codegas.itemmanagement.domain_impl.repository;

import java.util.List;
import java.util.Optional;

import javax.inject.Named;
import javax.inject.Singleton;

import org.codegas.service.jpa.RepositoryImpl;
import org.codegas.itemmanagement.domain.entity.Item;
import org.codegas.itemmanagement.domain.repository.ItemRepository;
import org.codegas.itemmanagement.domain.value.Code;

@Named
@Singleton
public class ItemRepositoryImpl extends RepositoryImpl<Item> implements ItemRepository {

    @Override
    public List<Item> getGeneralItems() {
        return entityManager.createQuery(" SELECT item" +
                " FROM Item item" +
                " WHERE item.superItem IS NULL",
            Item.class)
            .getResultList();
    }

    @Override
    public Optional<Item> findByName(String name) {
        try {
            return Optional.of(entityManager.createQuery(" SELECT item" +
                    " FROM Item item" +
                    " WHERE item.name = :name",
                Item.class)
                .setParameter("name", name)
                .getSingleResult());
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Item> findByCode(Code code) {
        try {
            return Optional.of(entityManager.createQuery(" SELECT item" +
                    " FROM Item item" +
                    " WHERE item.primaryCode = :code",
                Item.class)
                .setParameter("code", code)
                .getSingleResult());
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
