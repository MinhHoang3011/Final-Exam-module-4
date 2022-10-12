package com.example.finalexample4country.service;

import com.example.finalexample4country.model.City;
import com.example.finalexample4country.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CityService implements ICityService{

    @Autowired
    private CityRepository cityRepository;

    @Override
    public Iterable<City> findAllCity() {
        return cityRepository.findAll();
    }

    @Override
    public Optional<City> findCityById(Long id) {
        return cityRepository.findById(id);
    }

    @Override
    public City save(City city) {
        return cityRepository.save(city);
    }

    @Override
    public void remove(Long id) {
        cityRepository.deleteById(id);
    }
}
