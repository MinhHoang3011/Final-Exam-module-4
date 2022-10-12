package com.example.finalexample4country.controller;

import com.example.finalexample4country.model.City;
import com.example.finalexample4country.model.Country;
import com.example.finalexample4country.service.CityService;
import com.example.finalexample4country.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/cities")
@CrossOrigin("*")
public class CityController {

    @Autowired
    private CityService cityService;

    @Autowired
    private CountryService countryService;

    @GetMapping
    public ResponseEntity<?> showCities() {
        Iterable<City> cities = cityService.findAllCity();
        if (!cities.iterator().hasNext()) {
            return new ResponseEntity<>("Chưa có thành phố nào", HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(cities, HttpStatus.OK);
    }

    @GetMapping("/countries")
    public ResponseEntity<?> showCountries() {
        Iterable<Country> countries = countryService.findAllCountries();
        if (!countries.iterator().hasNext()) {
            return new ResponseEntity<>("Chưa có đất nước nào", HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(countries, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Optional<City> cityOptional = cityService.findCityById(id);
        return cityOptional.map(city -> new ResponseEntity<>(city, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<?> createCities(@Valid @RequestBody City city) {
        return new ResponseEntity<>(cityService.save(city), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateCities(@Valid @RequestBody City city, @PathVariable("id") Long id) {
        Optional<City> cityFound = cityService.findCityById(id);
        if (!cityFound.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        city.setId(cityFound.get().getId());
        return new ResponseEntity<>(cityService.save(city), HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteCities(@PathVariable("id") Long id) {
        Optional<City> cityOptional = cityService.findCityById(id);
        if (!cityOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        cityService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
