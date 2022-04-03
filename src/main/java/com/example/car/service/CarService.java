package com.example.car.service;

import com.example.car.entity.Car;
import com.example.car.entity.CarInfo;
import com.example.car.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

@Service
public class CarService {

    @Autowired
    CarRepository carRepository;

    public List<CarInfo> getCarInfoList() {
        List<CarInfo> carInfoList = new ArrayList<>();
        List<Car> carList = carRepository.findAll();
        Map<String, List<Car>> carMap = carList.stream().filter(s -> !s.getIsReserved()).collect(groupingBy(Car::getModel));
        for (Map.Entry<String, List<Car>> entry : carMap.entrySet()) {
            String model = entry.getKey();
            List<Car> subCarList =  entry.getValue();
            CarInfo carInfo = new CarInfo();
            carInfo.setModel(model);
            carInfo.setStock(subCarList.size());
            carInfoList.add(carInfo);
        }
        return carInfoList;
    }

    public ResponseEntity<Car> reserveCar(Car car) {
        if (car.getModel() == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        List<Car> carList = carRepository.findByModel(car.getModel());
        List<Car> validCarList = carList.stream().filter(s -> !s.getIsReserved()).limit(1).collect(Collectors.toList());
        if (validCarList != null && validCarList.size() > 0) {
            Car reserveCar = validCarList.get(0);
            reserveCar.setIsReserved(Boolean.TRUE);
            reserveCar.setCustomerId(car.getCustomerId());
            reserveCar.setStartTime(car.getStartTime());
            reserveCar.setEndTime(car.getEndTime());
            return new ResponseEntity<>(carRepository.save(reserveCar), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Car> returnCar(Integer id) {
        Optional<Car> carData = carRepository.findById(id);
        if (carData.isPresent()) {
            Car returnCar = carData.get();
            returnCar.setIsReserved(Boolean.FALSE);
            returnCar.setCustomerId(null);
            returnCar.setStartTime(null);
            returnCar.setEndTime(null);
            return new ResponseEntity<>(carRepository.save(returnCar), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

