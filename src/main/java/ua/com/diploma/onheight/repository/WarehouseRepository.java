package ua.com.diploma.onheight.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.diploma.onheight.model.warehouse.Warehouse;
import ua.com.diploma.onheight.model.warehouse.WarehouseClassification;

import java.util.List;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {

    List<Warehouse> findAllByClassification(WarehouseClassification classification);

    List<Warehouse> findAllByCompanyId(Long companyId);

    List<Warehouse> findAllByCompanyIdAndClassification(Long companyId, WarehouseClassification classification);
}
