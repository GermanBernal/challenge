package com.myhotel.challenge.domain.repository;

import com.myhotel.challenge.domain.model.Mantenimiento;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface MantenimientoRepository extends PagingAndSortingRepository<Mantenimiento,Integer> {
}
