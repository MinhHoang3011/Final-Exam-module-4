package com.example.finalexample4country.service;

import com.example.finalexample4country.model.City;

import java.util.Optional;

public interface ICityService {
    Iterable<City> findAllCity();

    Optional<City> findCityById(Long id);

    City save(City city);

    void remove(Long id);
}
