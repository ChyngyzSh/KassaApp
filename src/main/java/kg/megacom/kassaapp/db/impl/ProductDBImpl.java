package kg.megacom.kassaapp.db.impl;

import kg.megacom.kassaapp.db.ConnectionDB;
import kg.megacom.kassaapp.db.ProductDB;
import kg.megacom.kassaapp.models.Category;
import kg.megacom.kassaapp.models.Product;
import kg.megacom.kassaapp.models.Unit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDBImpl implements ProductDB {

//    private static ProductDBImpl INSTANCE;
//
//    public static ProductDBImpl getINSTANCE() {
//        if (INSTANCE == null) {
//            INSTANCE = new ProductDBImpl();
//        }
//        return INSTANCE;
//    }

    public void insert(Product product) {
        Connection connection = null;
        try {
            connection = ConnectionDB.INSTANCE.getConnection();
            String sql = "insert into products (name, price, barcode, discount, amount, category_id, unit_id)" +
                    "values(?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, product.getName());
            ps.setDouble(2, product.getPrice());
            ps.setString(3, product.getBarcode());
            ps.setInt(4, product.getDiscount());
            ps.setDouble(5, product.getAmount());
            ps.setInt(6, product.getCategory().getId());
            ps.setInt(7, product.getUnit().getId());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.INSTANCE.close(connection);
        }

    }

    public List<Product> selectProduct() {

        Connection connection = null;
        List<Product> products = new ArrayList<>();
        try {
            connection = ConnectionDB.INSTANCE.getConnection();
            PreparedStatement ps = connection.prepareStatement("select p.id, p.name, category_id, price, unit_id, barcode, amount, discount, c.name, u.unit_name  from products p\n" +
                    "join categories c\n" +
                    "on p.category_id = c.id\n" +
                    "join units u\n" +
                    "on p.unit_id = u.id");
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                Product product = new Product();
                product.setId(resultSet.getInt(1));
                product.setName(resultSet.getString(2));
                product.setPrice(resultSet.getDouble(4));
                product.setBarcode(resultSet.getString(6));
                product.setAmount(resultSet.getDouble(7));
                product.setDiscount(resultSet.getInt(8));

                Category category = new Category();
                category.setId(resultSet.getInt(3));
                category.setName(resultSet.getString(9));

                product.setCategory(category);

                Unit unit = new Unit();
                unit.setId(resultSet.getInt(5));
                unit.setName(resultSet.getString(10));

                product.setUnit(unit);

                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            ConnectionDB.INSTANCE.close(connection);
            return products;
        }
    }

    public void update(Product product) {
        Connection connection = null;
        try {
            connection = ConnectionDB.INSTANCE.getConnection();
            String sql = "update products set name = ?, price= ?, barcode= ?, discount= ?, amount= ?, category_id= ?, unit_id= ?" +
                    "where id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, product.getName());
            ps.setDouble(2, product.getPrice());
            ps.setString(3, product.getBarcode());
            ps.setInt(4, product.getDiscount());
            ps.setDouble(5, product.getAmount());
            ps.setInt(6, product.getCategory().getId());
            ps.setInt(7, product.getUnit().getId());
            ps.setInt(8, product.getId());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.INSTANCE.close(connection);
        }
    }

    public void delete(Integer id) throws SQLException {
        Connection connection = null;
        try {
            connection = ConnectionDB.INSTANCE.getConnection();
            PreparedStatement ps = connection.prepareStatement("delete from products where id =?");
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println("error when push 'Delete product'");
        } finally {
            ConnectionDB.INSTANCE.close(connection);
        }
    }
    public Product findProductByBarcode(String barcode) {
        Connection connection = null;
        Product product = null;
        try {
            connection = ConnectionDB.INSTANCE.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT p.id, p.name, p.category_id, p.price, p.unit_id, p.barcode, p.amount, \n" +
                    "p.discount, c.name, u.unit_name \n" +
                    "from products p join categories c\n" +
                    "on p.category_id = c.id\n" +
                    "join units u\n" +
                    "on p.unit_id = u.id\n" +
                    "where barcode = ?");
            ps.setString(1,barcode);

            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                product = new Product();
                product.setId(rs.getInt(1));
                product.setName(rs.getString(2));
                product.setBarcode(barcode);
                product.setDiscount(rs.getInt(8));
                product.setAmount(rs.getDouble(7));
                product.setPrice(rs.getDouble(4));

                Category category = new Category();
                category.setId(rs.getInt(3));
                category.setName(rs.getString(9));

                product.setCategory(category);

                Unit unit = new Unit();
                unit.setId(rs.getInt(5));
                unit.setName(rs.getString(10));

                product.setUnit(unit);
            }else {
                return null;
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            ConnectionDB.INSTANCE.close(connection);
            return product;
        }

    }
}
