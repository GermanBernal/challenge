package com.myhotel.challenge.domain.repository;

import com.myhotel.challenge.domain.model.Camion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CamionRepository extends PagingAndSortingRepository<Camion,Integer> {
}
