package kg.megacom.kassaapp.services.impl;

import kg.megacom.kassaapp.db.UnitDB;
import kg.megacom.kassaapp.db.impl.UnitDBImpl;
import kg.megacom.kassaapp.models.Unit;
import kg.megacom.kassaapp.services.UnitService;

import java.util.List;

public class UnitServiceImpl implements UnitService {

//    private static UnitServiceImpl INSTANCE;
//
//    public static UnitServiceImpl getINSTANCE(){
//        if(INSTANCE == null){
//            INSTANCE = new UnitServiceImpl();
//        }
//        return INSTANCE;
//    }
    public List<Unit> getUnits(){
        return UnitDB.INSTANCE.getUnits();
    }
}
