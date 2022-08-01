package kg.megacom.kassaapp.controllers;

import java.net.URL;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import kg.megacom.kassaapp.models.OperationProducts;

public class ChequeController {

    @FXML
    private ListView<OperationProducts> chequeLV;

    @FXML
    private TextField txtChange;

    NumberFormat formatter = new DecimalFormat("#0,00");


    public void setData(double change, List<OperationProducts> operationProductsList){
        txtChange.cancelEdit();
        txtChange.setText(String.valueOf(change));
        //txtChange.setText(formatter.format(String.valueOf(change)));
        chequeLV.setItems(FXCollections.observableList(operationProductsList));

    }
    @FXML
    void initialize() {


    }

}
