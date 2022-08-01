package kg.megacom.kassaapp.db.impl;

import kg.megacom.kassaapp.db.ConnectionDB;
import kg.megacom.kassaapp.db.UserDB;
import kg.megacom.kassaapp.models.Position;
import kg.megacom.kassaapp.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDBImpl implements UserDB {
//    private static UserDBImpl INSTANCE;
//
//    public static UserDBImpl getINSTANCE(){
//        if(INSTANCE == null)
//            INSTANCE = new UserDBImpl();
//        return INSTANCE;
//    }

    public void delete(Integer id) throws SQLException {
        Connection connection = null;
        try {
            connection= ConnectionDB.INSTANCE.getConnection();
            PreparedStatement ps = connection.prepareStatement("delete from users where id =?");
            ps.setInt(1,id);
            ps.executeUpdate();
        }catch (Exception e){
            System.out.println("error when push 'Delete user'");
        }
        finally {
            ConnectionDB.INSTANCE.close(connection);
        }
    }

    public boolean insert(User user) {
        Connection connection = null;
        try {
            connection = ConnectionDB.INSTANCE.getConnection();
            String sql = "insert into users (name, login, password, position_id) values (?,?,?,?)";
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, user.getName());
            ps.setString(2, user.getLogin());
            ps.setString(3, user.getPassword());
            ps.setInt(4, user.getPosition().getId());

            int countUpdRows = ps.executeUpdate();
            ConnectionDB.INSTANCE.close(connection);
            return countUpdRows >=1;

        } catch (SQLException e) {

            e.printStackTrace();
        } finally {
            ConnectionDB.INSTANCE.close(connection);
        }return false;
    }

    public void update(User user) {
        Connection connection = null;
        try {
            connection = ConnectionDB.INSTANCE.getConnection();
            String sql = "update users set name = ?, login= ?, password= ?, position_id= ?" +
                    "where id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, user.getName());
            ps.setString(2, user.getLogin());
            ps.setString(3, user.getPassword());
            ps.setInt(4, user.getPosition().getId());
            ps.setInt(5, user.getId());

            ps.executeUpdate();
            ConnectionDB.INSTANCE.close(connection);

            //int countUpdRows = ps.executeUpdate();

            //return countUpdRows >=1;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.INSTANCE.close(connection);
        }
        //return false;
    }

    public List<User>getAllUsersFromFB(){
        Connection connection = null;
        List<User> userList = new ArrayList<>();

        try {
            connection= ConnectionDB.INSTANCE.getConnection();

            String query ="select us.id, us.name, us.login, us.password, p.id, p.name from users us inner join position p " +
                    "on us.position_id = p.id";
            PreparedStatement ps = connection.prepareStatement(query);
            ResultSet resultSet= ps.executeQuery();
            while (resultSet.next()){
                User user = new User();
                user.setId(resultSet.getInt(1));
                user.setName(resultSet.getString(2));
                user.setLogin(resultSet.getString(3));
                user.setPassword(resultSet.getString(4));
                int positionID =resultSet.getInt(5);
                // вытащить позицию по positionID
                String posName = resultSet.getString(6);
                user.setPosition(new Position(positionID,posName));
                userList.add(user);
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            ConnectionDB.INSTANCE.close(connection);
        }return userList;
    }
}
