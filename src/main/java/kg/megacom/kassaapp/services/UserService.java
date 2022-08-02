package kg.megacom.kassaapp.services;

import kg.megacom.kassaapp.models.Position;
import kg.megacom.kassaapp.models.User;
import kg.megacom.kassaapp.services.impl.UserServiceImpl;

import java.util.List;

public interface UserService {

    UserService INSTANCE = new UserServiceImpl();

    void save(User user);

    List<User> findAllUsers();

    void delete(Integer id);

    List<Position> getPositions();

    User findUserByLoginAndPassword(String login, String password);

}
