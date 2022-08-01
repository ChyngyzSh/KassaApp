package kg.megacom.kassaapp.services;

import kg.megacom.kassaapp.models.Product;
import kg.megacom.kassaapp.services.impl.ProductServiceImpl;

import java.util.List;

public interface ProductService {

    ProductService INSTANCE = new ProductServiceImpl();
    void save(Product product);

    List<Product> getProduct();

    Product findProductByBarcode(String barcode);

    void delete(Integer id);
}
