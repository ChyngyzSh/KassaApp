package kg.megacom.kassaapp.db;

import kg.megacom.kassaapp.db.impl.OperationProductDbImpl;
import kg.megacom.kassaapp.models.OperationProducts;

import java.util.List;

public interface OperationProductDb {
    OperationProductDb INSTANCE = new OperationProductDbImpl();

    boolean saveOperationProducts(int operationId, List<OperationProducts> operationProductsList);

}
