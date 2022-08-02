package kg.megacom.kassaapp.db;

import kg.megacom.kassaapp.db.impl.OperationDBImpl;
import kg.megacom.kassaapp.models.Operation;

public interface OperationDB {

    OperationDB INSTANCE = new OperationDBImpl();

    Operation saveOperation(Operation operation, int userId);
    Operation findOperationByTotalPriceAndOperDate(double totalPrice, String operDate);
}
