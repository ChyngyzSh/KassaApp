package kg.megacom.kassaapp.controllers;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import kg.megacom.kassaapp.models.Position;
import kg.megacom.kassaapp.models.User;
import kg.megacom.kassaapp.services.UserService;
import kg.megacom.kassaapp.services.impl.UserServiceImpl;

public class UserEditController {
    private User user;
    //private UserServiceImpl userService = UserServiceImpl.getINSTANCE();
    @FXML
    private ComboBox<Position> cmbPositions;

    @FXML
    private TextField txtLogin;

    @FXML
    private TextField txtName;

    @FXML
    private PasswordField txtPassword;

    @FXML
    void onCancelClicked(ActionEvent event) {

    }

    @FXML
    void onSaveClicked(ActionEvent event) {
        String name = txtName.getText();
        //String name = txtName.getText().trim(); -- trim - убирает пробелы перед символами
        String login = txtLogin.getText();
        String password = txtPassword.getText();
        Position position = cmbPositions.getSelectionModel().getSelectedItem();

        user.setName(name);
        user.setLogin(login);
        user.setPassword(password);
        user.setPosition(position);

        UserService.INSTANCE.save(user);

        /*
        if (isResult) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Пользователь сохранен!");
            alert.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING, "Пользователь не сохранен!");
            alert.show();
        }
         */
    }

    @FXML
    void initialize() {
        cmbPositions.setItems(FXCollections.observableList(UserService.INSTANCE.getPositions()));
    }

    public void setUser(User user){
        this.user = user;
        if (this.user.getId() != null){
            txtName.setText(user.getName());
            txtLogin.setText(String.valueOf(user.getLogin()));
            txtPassword.setText(String.valueOf(user.getPassword()));

            cmbPositions.getSelectionModel().select(this.user.getPosition());
        }
    }
}
