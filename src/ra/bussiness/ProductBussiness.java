package ra.bussiness;

import ra.entity.Product;

import java.util.List;

public class ProductBussiness implements IBussiness<Product, String, String>{

    @Override
    public List<Product> getAll() {
        return null;
    }

    @Override
    public boolean create(Product product) {
        return false;
    }

    @Override
    public boolean update(Product product) {
        return false;
    }

    @Override
    public Product findById(String s) {
        return null;
    }

    @Override
    public Product findByName(String s) {
        return null;
    }
}
