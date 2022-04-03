package com.example.car.service;

import com.example.car.entity.Car;
import com.example.car.entity.CarInfo;
import com.example.car.repository.CarRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.annotation.Resource;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;


@SpringBootTest
class CarServiceTest {

    @MockBean
    private CarRepository carRepository;

    @Resource
    private CarService carService;

    List<Car> carList = new ArrayList<>();

    @BeforeEach
    public void setData() {
        Car car_1 = new Car();
        car_1.setId(1);
        car_1.setModel("Toyota Camry");
        car_1.setIsReserved(false);
        Car car_2 = new Car();
        car_2.setId(2);
        car_2.setModel("BMW 650");
        car_2.setIsReserved(true);
        carList.add(car_1);
        carList.add(car_2);
    }


    @Test
    void getCarInfoList() {
        Mockito.when(carRepository.findAll()).thenReturn(carList);
        List<CarInfo> carInfoList = carService.getCarInfoList();
        assertThat(carInfoList.size(),equalTo(1));
    }

    @Test
    void reserveCar() {
        Mockito.when(carRepository.findByModel("Toyota Camry")).thenReturn(carList);
        Car mockCar = new Car();
        Mockito.when(carRepository.save(mockCar)).thenReturn(mockCar);
        Car reserveCar = new Car();
        reserveCar.setModel("Toyota Camry");
        ResponseEntity<Car> responseEntity =  carService.reserveCar(reserveCar);
        assertThat(responseEntity.getStatusCode(),equalTo(HttpStatus.OK));
    }

    @Test
    void returnCar() {
        Optional<Car> optionalCarCar = Optional.of(new Car());
        Car mockCar = optionalCarCar.get();
        mockCar.setId(1);
        Mockito.when(carRepository.findById(1)).thenReturn(optionalCarCar);
        Mockito.when(carRepository.save(mockCar)).thenReturn(mockCar);
        ResponseEntity<Car> responseEntity =  carService.returnCar(1);
        assertThat(responseEntity.getStatusCode(),equalTo(HttpStatus.OK));
    }
}