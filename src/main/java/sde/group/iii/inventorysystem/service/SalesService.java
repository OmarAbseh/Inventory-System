package sde.group.iii.inventorysystem.service;

import sde.group.iii.inventorysystem.dao.SalesDAO;
import sde.group.iii.inventorysystem.model.Sales;

import java.util.List;

public class SalesService {
    private final SalesDAO salesDao;

    public SalesService() {
        this.salesDao = new SalesDAO();
    }

    public List<Sales> getAllSales() {
        return salesDao.getAllSales();
    }

    public Sales getSaleById(int id) {
        return salesDao.getSaleById(id);
    }

    public void addSale(Sales sale) {
        salesDao.addSale(sale);
    }

    public void updateSale(Sales sale) {
        salesDao.updateSale(sale);
    }

    public void deleteSale(int id) {
        salesDao.deleteSale(id);
    }
}
