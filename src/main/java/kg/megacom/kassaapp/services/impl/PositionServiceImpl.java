package kg.megacom.kassaapp.services.impl;

import kg.megacom.kassaapp.db.PositionDB;
import kg.megacom.kassaapp.db.impl.PositionDBImpl;
import kg.megacom.kassaapp.models.Position;
import kg.megacom.kassaapp.services.PositionService;

import java.util.List;

public class PositionServiceImpl implements PositionService {
//    private static PositionServiceImpl INSTANCE;
//
//    public static PositionServiceImpl getINSTANCE(){
//        if(INSTANCE == null)
//            INSTANCE = new PositionServiceImpl();
//
//        return INSTANCE;
//    }

    public void save(Position position) {
        if(position.getId() == null){
            PositionDB.INSTANCE.insert(position);
        }
        else
            PositionDB.INSTANCE.update(position);
    }

    public Position findPositionById(int positionId){
        return null;
    }

//    public List<Position> findAllPostions() {
//        return PositionDB.INSTANCE.finAllPostions();
//    }
}
