package kg.megacom.kassaapp.services.impl;

import kg.megacom.kassaapp.db.OperationDB;
import kg.megacom.kassaapp.models.Operation;
import kg.megacom.kassaapp.models.OperationProducts;
import kg.megacom.kassaapp.services.OperationProductService;
import kg.megacom.kassaapp.services.OperationService;

import java.time.LocalDateTime;
import java.util.List;

public class OperationServiceImpl implements OperationService {

    public boolean closeAndSaveOperation(double totalPrice, int userId, List<OperationProducts> operationProductsList) {
        System.out.println(operationProductsList);
        Operation operation = OperationDB.INSTANCE.saveOperation(new Operation(LocalDateTime.now(),totalPrice), userId);
        return OperationProductService.INSTANCE.saveOperationProducts(operation.getId(),operationProductsList);
    }
}
