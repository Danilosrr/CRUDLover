package com.carsapi.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carsapi.api.dto.CarDTO;
import com.carsapi.api.model.Car;
import com.carsapi.api.repository.CarsRepository;

@RestController
@RequestMapping("/api/cars")
public class CarsController {

    @Autowired
    private CarsRepository repository;

    @GetMapping
    public List<Car> getCars() {
        return repository.findAll();
    }

    @PostMapping
    public ResponseEntity<Car> createCar(@RequestBody CarDTO req) {
        Car car = repository.save(new Car(req));
        return ResponseEntity.status(HttpStatus.OK).body(car);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody CarDTO req) {
        Optional<Car> car = repository.findById(id);
        if (car.isPresent()) {
            car.get().setAnoModelo(req.anoModelo());
            car.get().setDataFabricacao(req.dataFabricacao());
            car.get().setFabricante(req.fabricante());
            car.get().setModelo(req.modelo());
            car.get().setValor(req.valor());
            repository.save(car.get());
            return ResponseEntity.status(HttpStatus.OK).body("OK");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Carro não encontrado!");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        Optional<Car> car = repository.findById(id);
        if (car.isPresent()) {
            repository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("OK");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Carro não encontrado!");
        }
    }
}
