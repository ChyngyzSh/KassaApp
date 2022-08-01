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
import kg.megacom.kassaapp.models.OperationProducts;
import kg.megacom.kassaapp.models.Product;
import kg.megacom.kassaapp.services.OperationService;
import kg.megacom.kassaapp.services.ProductService;
import kg.megacom.kassaapp.services.impl.ProductServiceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainController {

    private List<OperationProducts> operationProductsList = new ArrayList<>();

    @FXML
    private MenuItem mnItemCategories;

    @FXML
    private MenuItem mnItemProducts;

    @FXML
    private MenuItem mnItemUsers;

    @FXML
    private TableColumn<String, OperationProducts> colmAmountPrice;

    @FXML
    private TableColumn<String, OperationProducts> colmAmount;

    @FXML
    private TableColumn<String, OperationProducts> colmDiscount;

    @FXML
    private TableColumn<String, OperationProducts> colmPrice;

    @FXML
    private TableColumn<String, OperationProducts> colmProduct;

    @FXML
    private TableColumn<String, OperationProducts> colmUnit;

    @FXML
    private TableView<OperationProducts> tbOperations;

    @FXML
    private TextField txtBarcode;

    @FXML
    private TextField txtTotal;

    @FXML
    private Button btnEnter;

    @FXML
    private Button btnClose;

    @FXML
    void onCloseClicked(ActionEvent event) {

        boolean isSaveResult = OperationService
                .INSTANCE
                .closeAndSaveOperation(Double.parseDouble(txtTotal.getText()), 0, operationProductsList);
        Alert alert;
        if(isSaveResult){
            alert = new Alert(Alert.AlertType.INFORMATION,"Операция завершена");
        }else {
            alert = new Alert(Alert.AlertType.INFORMATION,"Ошибка при завершении операции");
        }alert.show();
    }

    @FXML
    void onEnterClicked(ActionEvent event) {

        if (txtBarcode.getText().trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Введите штрих-код товара!");
            alert.show();
            return;
        }

        Product product = ProductService.INSTANCE.findProductByBarcode(txtBarcode.getText());

        addProductToList(product);

        refreshList();
    }

    private void addProductToList(Product product){

        for(int i=0; i< operationProductsList.size(); i++){
            if(operationProductsList.get(i).getProduct().getId().equals(product.getId())){
                operationProductsList.get(i).setAmount(operationProductsList.get(i).getAmount()+1);
                operationProductsList.get(i).setTotal(operationProductsList.get(i).getAmount() * operationProductsList.get(i).getPriceWithDiscount());

                return;
            }
        }

        OperationProducts operationProducts = new OperationProducts();
        operationProducts.setAmount(1);
        operationProducts.setPriceWithDiscount(product.getPrice() * (100 - product.getDiscount())/100);
        operationProducts.setTotal(operationProducts.getAmount() * operationProducts.getPriceWithDiscount());
        operationProducts.setProduct(product);

        operationProductsList.add(operationProducts);
    }
    private void refreshList() {
        tbOperations.setItems(FXCollections.observableList(operationProductsList));
        tbOperations.refresh();

        double total = 0;
        for (OperationProducts item: operationProductsList) {
            total += item.getTotal();
        }
        txtTotal.setText(String.valueOf(total));
    }


    @FXML
    void onMenuItemClicked(ActionEvent event) {
        if (event.getSource().equals(mnItemCategories)){
            showCategoriesForm();
        } else if (event.getSource().equals(mnItemProducts)) {
            showProductsForm();
        } else if(event.getSource().equals(mnItemUsers)){
            showUsersForm();
        }
    }

    private void showUsersForm() {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("usersForm.fxml"));
        try {
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void showProductsForm() {

        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("productsForm.fxml"));
        try {
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showCategoriesForm() {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("categoriesForm.fxml"));
        try {
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            stage.setResizable(false);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() {
        colmAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        colmPrice.setCellValueFactory(new PropertyValueFactory<>("priceWithDiscount"));
        colmAmountPrice.setCellValueFactory(new PropertyValueFactory<>("total"));//total_price
        colmProduct.setCellValueFactory(new PropertyValueFactory<>("product"));

//        colmDiscount.setCellValueFactory(new PropertyValueFactory<>("discount"));
//        colmUnit.setCellValueFactory(new PropertyValueFactory<>("unit"));



    }

}

