package id.ac.ui.cs.advprogid.eshop.repository;

import id.ac.ui.cs.advprogid.eshop.model.Car;
import java.util.Iterator;

public interface ICarRepository {
    Car create(Car car);
    Iterator<Car> findAll();
    Car findById(String id);
    Car update(String id, Car car);
    void delete(String id);
}
