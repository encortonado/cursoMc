package com.cursoMc.SpringFast.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cursoMc.SpringFast.domain.City;

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {

	@Transactional(readOnly = true)
	@Query("SELECT obj FROM City obj WHERE obj.estado.id = :estadoId ORDER BY obj.nome")
	public List<City> findByEstado(@Param("estadoId") Integer estado_id);
}
