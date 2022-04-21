package com.myhotel.challenge.domain.repository;

import com.myhotel.challenge.domain.model.Vehiculo;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface VehiculoRepository extends PagingAndSortingRepository<Vehiculo, Integer> {
}
