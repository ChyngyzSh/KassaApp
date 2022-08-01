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
import kg.megacom.kassaapp.models.Position;
import kg.megacom.kassaapp.models.User;
import kg.megacom.kassaapp.services.UserService;
import kg.megacom.kassaapp.services.impl.UserServiceImpl;

import java.io.IOException;

public class UserController {

    //private UserServiceImpl userService = UserServiceImpl.getINSTANCE();
    @FXML
    private TableColumn<String, User> colmLogin;

    @FXML
    private TableColumn<String, User> colmName;

    @FXML
    private TableColumn<String, Position> colmPosition;

    @FXML
    private MenuItem mnItemAdd;

    @FXML
    private MenuItem mnItemDelete;

    @FXML
    private MenuItem mnItemEdit;

    @FXML
    private TableView<User> tbUsers;

    @FXML
    void onItemClicked(ActionEvent event) {
        if(event.getSource().equals(mnItemAdd)){
            userAdd();
            System.out.println("Окно добавления");
        } else if (event.getSource().equals(mnItemEdit)) {
            System.out.println("edit");
            userEdit();
        } else if (event.getSource().equals(mnItemDelete)) {
            deleteUser();

        }else {
            throw new RuntimeException("Ошибка при загрузке окна");
        }
    }

    private void deleteUser() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"Вы уверены?", ButtonType.YES, ButtonType.NO);
        ButtonType result = alert.showAndWait().get();

        if(result.equals(ButtonType.YES)){
            User user = tbUsers.getSelectionModel().getSelectedItem();
            UserService.INSTANCE.delete(user.getId());
            refreshTableUsers();
        }
    }

    private void userEdit() {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("userEditForm.fxml"));
        try {
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.setResizable(false);

            UserEditController controller = loader.getController();
            controller.setUser(tbUsers.getSelectionModel().getSelectedItem());

            stage.showAndWait();
            refreshTableUsers();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void userAdd() {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("userEditForm.fxml"));
        try {
            Scene scene = new Scene(loader.load());
            stage.setScene(scene);
            stage.initModality(Modality.WINDOW_MODAL);
            stage.setResizable(false);

            UserEditController controller = loader.getController();
            controller.setUser(new User());

            stage.showAndWait();
            refreshTableUsers();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void initialize() {
        colmName.setCellValueFactory(new PropertyValueFactory<>("name"));
        colmLogin.setCellValueFactory(new PropertyValueFactory<>("login"));
        colmPosition.setCellValueFactory(new PropertyValueFactory<>("position"));

        refreshTableUsers();
    }

    private void refreshTableUsers(){
        tbUsers.setItems(FXCollections.observableList(UserService.INSTANCE.findAllUsers()));
    }

}
