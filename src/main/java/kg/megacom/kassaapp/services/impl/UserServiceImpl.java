package kg.megacom.kassaapp.services.impl;

import kg.megacom.kassaapp.db.PositionDB;
import kg.megacom.kassaapp.db.UserDB;
import kg.megacom.kassaapp.db.impl.PositionDBImpl;
import kg.megacom.kassaapp.db.impl.UserDBImpl;
import kg.megacom.kassaapp.models.Position;
import kg.megacom.kassaapp.models.User;
import kg.megacom.kassaapp.services.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
//    private static UserServiceImpl INSTANCE;
//
//    public static UserServiceImpl getINSTANCE() {
//        if (INSTANCE == null) {
//            INSTANCE = new UserServiceImpl();
//        }
//        return INSTANCE;
//    }

    /*
    1. Создать позицию, далее создать User
    2. Сделать редактировать

    */
//    public boolean addOrEditUser(User user) {
//        return save(user);
//    }

    //private PositionDBImpl positionDB = PositionDBImpl.getINSTANCE();

    public void save(User user) {
        if(user.getId() == null){
            UserDB.INSTANCE.insert(user);
        }
        else
            UserDB.INSTANCE.update(user);
    }

    public List<User> findAllUsers(){
            return UserDB.INSTANCE.getAllUsersFromFB();
    }

    public void delete(Integer id) {
        try {
            UserDB.INSTANCE.delete(id);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public List<Position> getPositions() {
        return PositionDB.INSTANCE.findPositions();
    }

//    public User findOneUserById(int id){
//        return
//    }
}
