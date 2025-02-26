package id.ac.ui.cs.advprogid.eshop.controller;

import id.ac.ui.cs.advprogid.eshop.model.Car;
import id.ac.ui.cs.advprogid.eshop.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/car")
public class CarController extends AbstractCrudController<Car> {

    @Autowired
    private CarService carService;

    @Override
    protected List<Car> getAll() {
        return carService.findAll();
    }

    @Override
    protected Car getById(String id) {
        return carService.findById(id);
    }

    @Override
    protected Car createEntity(Car entity) {
        // Jika carId belum di-set, generate UUID baru
        if (entity.getCarId() == null) {
            entity.setCarId(UUID.randomUUID().toString());
        }
        return carService.create(entity);
    }

    @Override
    protected Car updateEntity(Car entity) {
        // Memanggil update di service; karena metode update di CarService bertipe void,
        // kita kembalikan entitas yang sama setelah update.
        carService.update(entity.getCarId(), entity);
        return entity;
    }

    @Override
    protected void deleteEntity(String id) {
        carService.deleteCarById(id);
    }

    @Override
    protected String getListView() {
        return "CarList";  // Pastikan nama file view persis sesuai (case-sensitive)
    }

    @Override
    protected String getCreateView() {
        return "CreateCar";
    }

    @Override
    protected String getEditView() {
        return "EditCar";
    }

    @Override
    protected Car createNewInstance() {
        return new Car();
    }
}
