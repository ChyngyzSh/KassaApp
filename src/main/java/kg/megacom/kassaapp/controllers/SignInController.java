package kg.megacom.kassaapp.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import kg.megacom.kassaapp.Main;
import kg.megacom.kassaapp.models.User;
import kg.megacom.kassaapp.services.UnitService;
import kg.megacom.kassaapp.services.UserService;

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
        String login = txtLogin.getText().trim();
        String password = txtPassword.getText().trim();
        if(login.isEmpty() || password.isEmpty()) {
            showErrorAlert("Заполните логин и пароль");
            return;
        }

        User user = UserService.INSTANCE.findUserByLoginAndPassword(login,password);
//        if (user.getLogin().equals(login) && !user.getPassword().equals(password)) {
//            showErrorAlert("Неверный пароль");
//        }
            if(user == null) {
                showErrorAlert("Пользователь не найден");
                return;
            }

            if (user.getLogin().equals(login) && user.getPassword().equals(password)) {
                Stage stage = new Stage();
                FXMLLoader loader = new FXMLLoader(Main.class.getResource("mainForm.fxml"));
                try {
                    Scene scene = new Scene(loader.load());
                    stage.setScene(scene);
                    stage.initModality(Modality.APPLICATION_MODAL);
                    MainController controller = loader.getController();
                    controller.setData(user);
                    stage.showAndWait();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                showErrorAlert( "Неверные данные!");
                return;
            }
//        if(!login.equals(user.getLogin())){
//            showErrorAlert("Пользователь не найден");
//            return;
//        }
//        if(login.equals(user.getLogin()) && !password.equals(user.getPassword())){
//            showErrorAlert("Неверный пароль");
//            return;
//        }

    }

    private void showErrorAlert(String text) {
        Alert alert = new Alert(Alert.AlertType.ERROR, text, ButtonType.OK);
        alert.showAndWait();
    }
    @FXML
    void initialize() {

    }

}
