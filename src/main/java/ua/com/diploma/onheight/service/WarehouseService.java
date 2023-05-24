package ua.com.diploma.onheight.service;

import ua.com.diploma.onheight.model.warehouse.Warehouse;
import ua.com.diploma.onheight.model.warehouse.WarehouseClassification;

import java.util.List;

public interface WarehouseService extends CrudService<Warehouse, Long> {

    List<Warehouse> findAllByClassification(WarehouseClassification classification);

    List<Warehouse> findAllByCompanyId(Long companyId);

    List<Warehouse> findAllByCompanyIdAndClassification(Long companyId, WarehouseClassification classification);
}
