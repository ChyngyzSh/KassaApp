package kg.megacom.kassaapp.services.impl;

import kg.megacom.kassaapp.db.ProductDB;
import kg.megacom.kassaapp.db.UserDB;
import kg.megacom.kassaapp.db.impl.ProductDBImpl;
import kg.megacom.kassaapp.models.Product;
import kg.megacom.kassaapp.services.ProductService;

import java.util.List;

public class ProductServiceImpl implements ProductService {

//    private static ProductServiceImpl INSTANCE;
//
//    public static ProductServiceImpl getINSTANCE(){
//        if(INSTANCE == null)
//            INSTANCE = new ProductServiceImpl();
//
//        return INSTANCE;
//    }

    public void save(Product product) {
        if(product.getId() == null){
            ProductDB.INSTANCE.insert(product);
        }
        else
        ProductDB.INSTANCE.update(product);
    }

    public List<Product> getProduct(){
        return ProductDB.INSTANCE.selectProduct();
    }

    public Product findProductByBarcode(String barcode){
        return ProductDB.INSTANCE.findProductByBarcode(barcode);
    }

    @Override
    public void delete(Integer id) {
        try {
            ProductDB.INSTANCE.delete(id);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
