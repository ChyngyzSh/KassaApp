package kg.megacom.kassaapp.controllers;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import kg.megacom.kassaapp.Main;
import kg.megacom.kassaapp.models.Product;
import kg.megacom.kassaapp.models.User;
import kg.megacom.kassaapp.services.ProductService;
import kg.megacom.kassaapp.services.UserService;

import java.io.IOException;

public class ProductsController {

    @FXML
    private TableView<Product> tbProducts;

    @FXML
    private TableColumn<String, Product> colmBarcode;

    @FXML
    private TableColumn<String, Product> colmCategory;

    @FXML
    private TableColumn<String, Product> colmDiscount;

    @FXML
    private TableColumn<String, Product> colmName;

    @FXML
    private TableColumn<String, Product>colmPrice;

    @FXML
    private TableColumn<String, Product> colmUnit;

    @FXML
    private TableColumn<String, Product> colmAmount;

    @FXML
    private MenuItem mnItemAdd;

    @FXML
    private MenuItem mnItemDelete;

    @FXML
    private MenuItem mnItemEdit;

    @FXML
    void onItemClicked(ActionEvent event) {
        if(event.getSource().equals(mnItemAdd)){
            addProduct();
        } else if (event.getSource().equals(mnItemEdit)) {
            editProduct();
        } else if (event.getSource().equals(mnItemDelete)) {
            deleteProduct();
        }
    }

    private void deleteProduct() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Вы уверены?", ButtonType.YES, ButtonType.NO);
        ButtonType result = alert.showAndWait().get();

        if(result.equals(ButtonType.YES)){
            Product product = tbProducts.getSelectionModel().getSelectedItem();
            ProductService.INSTANCE.delete(product.getId());
            refreshTable();
        }
    }

    private void editProduct() {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("productEditForm.fxml"));
        try {
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.setResizable(false);

            ProductEditController controller = loader.getController();
            controller.setProduct(tbProducts.getSelectionModel().getSelectedItem());

            stage.showAndWait();

            refreshTable();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void addProduct() {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("productEditForm.fxml"));
        try {
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.setResizable(false);

            ProductEditController controller = loader.getController();
            controller.setProduct(new Product());

            stage.showAndWait();
            refreshTable();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() {

        colmName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colmPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colmBarcode.setCellValueFactory(new PropertyValueFactory<>("barcode"));
        colmAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        colmDiscount.setCellValueFactory(new PropertyValueFactory<>("discount"));
        colmCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colmUnit.setCellValueFactory(new PropertyValueFactory<>("unit"));

        refreshTable();

    }

    private void refreshTable() {
        tbProducts.setItems(FXCollections.observableList(ProductService.INSTANCE.getProduct()));
    }

}
