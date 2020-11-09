package com.cursoMc.SpringFast.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cursoMc.SpringFast.domain.City;

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {

}
