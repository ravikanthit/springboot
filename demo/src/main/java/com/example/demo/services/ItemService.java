package com.example.demo.services;

import com.example.demo.dao.ItemRepository;
import com.example.demo.entity.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ItemService {
    private final ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }


    public Item createItem(Item item) {
        validateItem(item);
        return itemRepository.save(item);
    }


    public Optional<Item> getItemById(int id) {
        Optional<Item> data= itemRepository.findById(id);
        if(data.isEmpty())
            throw new NoSuchElementException("Data not found with id: " + id);
        else return data;
    }


    public List<Item> getAllItems() {
        List<Item> data= itemRepository.findAll();
        if(data.isEmpty())
            throw new NoSuchElementException("There is no Data try to add the data");
        else return  data;
    }

    public Item updateItem(Item item) {
        validateItem(item);
        return itemRepository.save(item);
    }

    public void deleteItem(int id) {
        if(!itemRepository.existsById(id))
            throw new NoSuchElementException("Oops The Items is not present with id : " + id);
        else itemRepository.deleteById(id);
    }

    private void validateItem(Item item) {
        if (item.getItemName() == null) {
            throw new IllegalArgumentException("ItemsName properties is missing or cannot be null");
        } else if (item.getDescription() == null) {
            throw new IllegalArgumentException("Description properties is missing or cannot be null");
        } else if (item.getItemName().length() == 0) {
            throw new IllegalArgumentException("ItemName cannot be empty");
        } else if (item.getDescription().length() == 0) {
            throw new IllegalArgumentException("Description cannot be empty");
        }
    }
}
