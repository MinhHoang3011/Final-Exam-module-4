package com.example.finalexample4country.service;

import com.example.finalexample4country.model.Country;

import java.util.Optional;

public interface ICountryService {
    Iterable<Country> findAllCountries();

    Optional<Country> findById(Long id);

}
