package ua.com.diploma.onheight.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.EmptyResultDataAccessException;
import ua.com.diploma.onheight.model.company.Company;
import ua.com.diploma.onheight.model.warehouse.Address;
import ua.com.diploma.onheight.model.warehouse.Warehouse;
import ua.com.diploma.onheight.model.warehouse.WarehouseClassification;
import ua.com.diploma.onheight.repository.WarehouseRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@SpringBootTest(classes = {WarehouseServiceImpl.class})
class WarehouseServiceImplTest {
    @Autowired
    WarehouseServiceImpl warehouseService;
    @MockBean
    WarehouseRepository warehouseRepository;

    @Test
    void shouldThrowNotFindException() {
        assertThrows(EmptyResultDataAccessException.class, () -> warehouseService.findById(6L));
    }

    @Test
    void shouldVerifyFindAllByClassification() {
        WarehouseClassification classification = WarehouseClassification.FINISHED_GOODS;
        Warehouse warehouse1 = new Warehouse(1L, classification, "Description 1", new Company(), new Address(), "123456789");
        Warehouse warehouse2 = new Warehouse(2L, classification, "Description 2", new Company(), new Address(), "987654321");
        List<Warehouse> expected = List.of(warehouse1, warehouse2);

        when(warehouseRepository.findAllByClassification(classification)).thenReturn(expected);
        List<Warehouse> actual = warehouseService.findAllByClassification(classification);

        assertEquals(expected, actual);
    }

    @Test
    void shouldVerifyFindAllByCompanyId() {
        Long companyId = 1L;
        Warehouse warehouse1 = new Warehouse(1L, WarehouseClassification.FINISHED_GOODS, "Description 1", new Company(), new Address(), "123456789");
        Warehouse warehouse2 = new Warehouse(2L, WarehouseClassification.FINISHED_GOODS, "Description 2", new Company(), new Address(), "987654321");
        List<Warehouse> expected = List.of(warehouse1, warehouse2);

        when(warehouseRepository.findAllByCompanyId(companyId)).thenReturn(expected);
        List<Warehouse> actual = warehouseService.findAllByCompanyId(companyId);

        assertEquals(expected, actual);
    }

    @Test
    void shouldVerifyFindAllByCompanyIdAndClassification() {
        Long companyId = 1L;
        WarehouseClassification classification = WarehouseClassification.FINISHED_GOODS;
        Warehouse warehouse1 = new Warehouse(1L, classification, "Description 1", new Company(), new Address(), "123456789");
        List<Warehouse> expected = List.of(warehouse1);

        when(warehouseRepository.findAllByCompanyIdAndClassification(companyId, classification)).thenReturn(expected);
        List<Warehouse> actual = warehouseService.findAllByCompanyIdAndClassification(companyId, classification);

        assertEquals(expected, actual);
    }
}

