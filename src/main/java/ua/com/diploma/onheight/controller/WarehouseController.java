package ua.com.diploma.onheight.controller;

import jakarta.validation.Valid;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ua.com.diploma.onheight.model.user.User;
import ua.com.diploma.onheight.model.warehouse.Address;
import ua.com.diploma.onheight.model.warehouse.Warehouse;
import ua.com.diploma.onheight.model.warehouse.WarehouseClassification;
import ua.com.diploma.onheight.service.AddressService;
import ua.com.diploma.onheight.service.UserService;
import ua.com.diploma.onheight.service.WarehouseService;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Objects;

@Controller
@RequestMapping(path = "/warehouse")
public class WarehouseController {

    private final WarehouseService warehouseService;

    private final UserService userService;
    private final AddressService addressService;


    public WarehouseController(WarehouseService warehouseService, UserService userService, AddressService addressService) {
        this.warehouseService = warehouseService;
        this.userService = userService;
        this.addressService = addressService;
    }


    @GetMapping("/create")
    public String userWarehouseForm(Warehouse warehouse, Authentication authentication, Model model) {
        User user = userService.findByUsername(authentication.getName());
        model.addAttribute("companyId", user.getCompany().getId());
        model.addAttribute("classifications", Arrays.asList(WarehouseClassification.values()));
        return "warehouse/create-warehouse";
    }

    @GetMapping("/list")
    public String warehousesList(@RequestParam(required = false) String searchParam,
                                 @RequestParam(required = false) String searchValue,
                                 Authentication authentication,
                                 Model model) {

        User user = userService.findByUsername(authentication.getName());

        if (Objects.equals(searchParam, "classification") && searchValue != null) {
            model.addAttribute(
                    "warehouses",
                    warehouseService.findAllByCompanyIdAndClassification(
                            user.getCompany().getId(),
                            WarehouseClassification.getByValue(searchValue)
                    )
            );
        } else {
            model.addAttribute("warehouses",
                    warehouseService.findAllByCompanyId(user.getCompany().getId()));
        }
        return "warehouse/warehouses-list";
    }


    @PostMapping("/create")
    public String createWarehouse(@Valid Warehouse warehouse, Authentication authentication, BindingResult result, Model model) {
        if (result.hasErrors() || !saveWarehouseOnCreate(warehouse)) {
            User user = userService.findByUsername(authentication.getName());
            model.addAttribute("companyId", user.getCompany().getId());
            model.addAttribute("classifications", Arrays.asList(WarehouseClassification.values()));
            return "warehouse/create-warehouse";
        }

        return "redirect:list";
    }


    @GetMapping("/edit/{id}")
    public String updateForm(@PathVariable("id") long id, Authentication authentication, Model model) {
        User user = userService.findByUsername(authentication.getName());
        Warehouse warehouse = warehouseService.findById(id);

        model.addAttribute("companyId", user.getCompany().getId());
        model.addAttribute("warehouse", warehouse);
        model.addAttribute("classifications", Arrays.asList(WarehouseClassification.values()));

        return "warehouse/update-warehouse";
    }

    @PostMapping("/update/{id}")
    public String updateWarehouse(@PathVariable("id") long id, @Valid Warehouse warehouse, BindingResult result, Model model) {
        if (result.hasErrors()) {
            warehouse.setId(id);
            return "warehouse/update-warehouse";
        }
        saveWarehouseOnUpdate(warehouse);
        return "redirect:/warehouse/list";
    }

    @GetMapping("/delete/{id}")
    public String deleteWarehouse(@PathVariable("id") long id, Model model) {

        warehouseService.deleteById(id);

        return "redirect:/warehouse/list";
    }

    private boolean saveWarehouseOnCreate(Warehouse warehouse) {
        Address address;
        try {
            address = addressService.findByCountryAndCityAndAddressAndZipCode(
                    warehouse.getAddress().getCountry(),
                    warehouse.getAddress().getCity(),
                    warehouse.getAddress().getAddress(),
                    warehouse.getAddress().getZipCode()
            );
        } catch (EmptyResultDataAccessException ex) {
            warehouse.getAddress().setLastUpdate(LocalDate.now());
            address = addressService.save(warehouse.getAddress());
        }
        warehouse.setAddress(address);
        try {
            warehouseService.save(warehouse);
        } catch (EmptyResultDataAccessException ex) {
            return false;
        }
        return true;
    }

    private void saveWarehouseOnUpdate(Warehouse warehouse) {
        Address address = addressService.findById(warehouse.getAddress().getId());
        boolean isPresent = !addressService.findAll().stream().filter(
                        addr ->
                                addr.getCountry().equals(warehouse.getAddress().getCountry())
                                        && addr.getCity().equals(warehouse.getAddress().getCity())
                                        && addr.getAddress().equals(warehouse.getAddress().getAddress())
                                        && addr.getZipCode().equals(warehouse.getAddress().getZipCode())
                                        && addr.getLastUpdate().equals(warehouse.getAddress().getLastUpdate()))
                .toList().isEmpty();
        if (!warehouse.getAddress().equals(address) && !isPresent) {
            warehouse.getAddress().setId(null);
            warehouse.getAddress().setLastUpdate(LocalDate.now());
            address = addressService.save(warehouse.getAddress());
            warehouse.setAddress(address);
        } else if (!warehouse.getAddress().equals(address) && isPresent) {
            address = addressService.findByCountryAndCityAndAddressAndZipCode(
                    warehouse.getAddress().getCountry(),
                    warehouse.getAddress().getCity(),
                    warehouse.getAddress().getAddress(),
                    warehouse.getAddress().getZipCode()
            );
            warehouse.setAddress(address);
        }
        warehouseService.save(warehouse);
    }
}
