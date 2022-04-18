package com.myhotel.challenge.domain.usecase;

import com.myhotel.challenge.domain.model.Auto;
import com.myhotel.challenge.domain.model.Camion;
import com.myhotel.challenge.domain.model.Vehiculo;
import com.myhotel.challenge.domain.usecase.dto.AutoDto;
import com.myhotel.challenge.domain.usecase.dto.CamionDto;
import com.myhotel.challenge.domain.usecase.dto.VehiculoDto;
import com.myhotel.challenge.exceptions.MyHotelHttpException;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface VehiculoService<T extends VehiculoDto, U extends  Vehiculo> {
    List<T> getAll(Pageable pageable);
    T getById(Integer id) throws MyHotelHttpException;
    Integer create(T data);
    void update(Integer id, T data) throws MyHotelHttpException;
    void delete(Integer id) throws MyHotelHttpException;
    U toEntity(T dto);
    T toDto(U entity);

}
