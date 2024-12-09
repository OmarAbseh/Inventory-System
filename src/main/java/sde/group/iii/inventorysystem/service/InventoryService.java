package sde.group.iii.inventorysystem.service;

import sde.group.iii.inventorysystem.dao.InventoryDAO;
import sde.group.iii.inventorysystem.model.Inventory;

import java.util.List;

public class InventoryService {
    private final InventoryDAO inventoryDAO;

    public InventoryService() {
        this.inventoryDAO = new InventoryDAO();
    }

    public List<Inventory> getAllItems() {
        return inventoryDAO.getAllItems();
    }

    public Inventory getItemById(int id) {
        return inventoryDAO.getItemById(id);
    }

    public void addItem(Inventory item) {
        inventoryDAO.addItem(item);
    }

    public void updateItem(Inventory item) {
        inventoryDAO.updateItem(item);
    }

    public void removeItem(Inventory item) {
        if (item != null) {
            inventoryDAO.removeItem(item);
        }
    }

    public void deleteItem(int id) {
        Inventory item = new Inventory(); // Create an empty Inventory object
        item.setId(id); // Set the ID
        inventoryDAO.removeItem(item);
    }

}
