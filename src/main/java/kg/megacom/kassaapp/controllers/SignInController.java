package kg.megacom.kassaapp.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class SignInController {

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnEnter;

    @FXML
    private TextField txtLogin;

    @FXML
    private PasswordField txtPassword;

    @FXML
    void onCancelClicked(ActionEvent event) {
        txtLogin.clear();
        txtPassword.clear();
    }

    @FXML
    void onEnterClicked(ActionEvent event) {

    }

    @FXML
    void initialize() {
    }

}
