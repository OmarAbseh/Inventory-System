package sde.group.iii.inventorysystem.service;

import sde.group.iii.inventorysystem.dao.ItemDAO;
import sde.group.iii.inventorysystem.model.Inventory;

import java.util.List;

public class InventoryService {
    private final ItemDAO itemDao;

    public InventoryService() {
        this.itemDao = new ItemDAO();
    }

    public List<Inventory> getAllItems() {
        return itemDao.getAllItems();
    }

    public Inventory getItemById(int id) {
        return itemDao.getItemById(id);
    }

    public void addItem(Inventory item) {
        itemDao.addItem(item);
    }

    public void updateItem(Inventory item) {
        itemDao.updateItem(item);
    }

    // Added removeItem method
    public void removeItem(Inventory item) {
        if (item != null) {
            itemDao.deleteItem(item.getId()); // Call deleteItem using the item's ID
        }
    }

    public void deleteItem(int id) {
        itemDao.deleteItem(id);
    }
}
