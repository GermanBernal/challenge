package com.myhotel.challenge.domain.usecase;

import com.myhotel.challenge.domain.usecase.dto.MantenimientoDto;
import com.myhotel.challenge.domain.usecase.dto.MantenimientoFullResponseDto;
import com.myhotel.challenge.exceptions.MyHotelHttpException;
import com.myhotel.challenge.exceptions.ResourceNotFoundException;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MantenimientoService {

    Integer create(MantenimientoDto mantenimientoDto) throws MyHotelHttpException;
    void update(Integer id, MantenimientoDto data) throws MyHotelHttpException;

    List<MantenimientoFullResponseDto> getAll(Pageable pageable);

    void delete(Integer id) throws MyHotelHttpException;

    void finaizarMantenimiento(Integer id) throws MyHotelHttpException;

    MantenimientoFullResponseDto getById(Integer id) throws MyHotelHttpException;
}
