package com.myhotel.challenge.domain.repository;

import com.myhotel.challenge.domain.model.Camion;
import com.myhotel.challenge.domain.model.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehiculoRepository extends PagingAndSortingRepository<Vehiculo, Integer> {
}
