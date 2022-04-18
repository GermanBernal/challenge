package com.myhotel.challenge.domain.repository;

import com.myhotel.challenge.domain.model.Auto;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutoRepository extends PagingAndSortingRepository<Auto,Integer> {
}
