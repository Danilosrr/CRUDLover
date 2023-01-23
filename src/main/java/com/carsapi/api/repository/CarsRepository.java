package com.carsapi.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carsapi.api.model.Car;

public interface CarsRepository extends JpaRepository<Car, Long>{
    
}
