package com.beauver.Services;

import com.beauver.Objects.Item;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import java.util.List;

public class ItemService implements PanacheRepository<Item> {

    public Item getById(Integer id) {
        return find("id", id).firstResult();
    }

    public List<Item> getAllItems() {
        return listAll();
    }

    public void createItem(Item item) {
        persist(item);
    }

}
