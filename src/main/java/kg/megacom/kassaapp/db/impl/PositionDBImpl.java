package kg.megacom.kassaapp.db.impl;

import kg.megacom.kassaapp.db.ConnectionDB;
import kg.megacom.kassaapp.db.PositionDB;
import kg.megacom.kassaapp.models.Position;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PositionDBImpl implements PositionDB {
//    private static PositionDBImpl INSTANCE;
//
//    public static PositionDBImpl getINSTANCE() {
//        if (INSTANCE == null) {
//            INSTANCE = new PositionDBImpl();
//        }
//        return INSTANCE;
//    }

    public void insert(Position position) {
        Connection connection = null;
        try {
            connection = ConnectionDB.INSTANCE.getConnection();
            String sql = "insert into position (name) values (?)";
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, position.getName());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.INSTANCE.close(connection);
        }
    }

    public void update(Position position) {
        Connection connection = null;
        try {
            connection = ConnectionDB.INSTANCE.getConnection();
            String sql = "UPDATE position set name = ? where id=?";
            PreparedStatement ps = connection.prepareStatement(sql);

            ps.setString(1, position.getName());
            ps.setInt(2, position.getId());

            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.INSTANCE.close(connection);
        }
    }

    public List<Position> findPositions() {
        Connection connection = null;
        List<Position> positions = new ArrayList<>();
        try {
            connection = ConnectionDB.INSTANCE.getConnection();
            PreparedStatement ps = connection.prepareStatement("select * from position");
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()){
                Position position = new Position();
                position.setId(resultSet.getInt(1));
                position.setName(resultSet.getString(2));
                positions.add(position);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            ConnectionDB.INSTANCE.close(connection);
            return positions;
        }
    }

//    public Position getPositionById(int positionId) {
//        Connection connection = null;
//        try {
//            connection = ConnectionDBImpl.getConnection();
//            String query = "select * from position where id=?";
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        } finally {
//            ConnectionDBImpl.close(connection);
//        }return
}




