package com.example.car.controller;

import com.example.car.entity.Car;
import com.example.car.entity.CarInfo;
import com.example.car.repository.CarRepository;
import com.example.car.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class CarController {

    @Autowired
    CarRepository carRepository;

    @Autowired
    CarService carService;

    @GetMapping("/cars")
    public ResponseEntity<List<CarInfo>> getCarInfo() {
        List<CarInfo> carInfoList = carService.getCarInfoList();
        return new ResponseEntity<>(carInfoList, HttpStatus.OK);
    }

    @PutMapping("/cars")
    public ResponseEntity<Car> reserveCar(@RequestBody Car car) {
        return carService.reserveCar(car);
    }

    @PutMapping("/cars/{id}")
    public ResponseEntity<Car> returnCar(@PathVariable("id") Integer id) {
        return carService.returnCar(id);
    }
}
