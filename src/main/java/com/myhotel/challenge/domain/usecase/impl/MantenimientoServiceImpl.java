package com.myhotel.challenge.domain.usecase.impl;

import com.myhotel.challenge.domain.model.Camion;
import com.myhotel.challenge.domain.model.Mantenimiento;
import com.myhotel.challenge.domain.model.Vehiculo;
import com.myhotel.challenge.domain.repository.MantenimientoRepository;
import com.myhotel.challenge.domain.repository.VehiculoRepository;
import com.myhotel.challenge.domain.usecase.MantenimientoService;
import com.myhotel.challenge.domain.usecase.VehiculoService;
import com.myhotel.challenge.domain.usecase.dto.*;
import com.myhotel.challenge.exceptions.MyHotelHttpException;
import com.myhotel.challenge.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MantenimientoServiceImpl implements MantenimientoService {

    private final ModelMapper modelMapper;

    private final VehiculoRepository vehiculoRepository;

    private final MantenimientoRepository mantenimientoRepository;

    @Override
    public Integer create(MantenimientoDto mantenimientoDto) throws MyHotelHttpException {


        Vehiculo vehiculo = vehiculoRepository.findById(mantenimientoDto.getVehiculo())
                .orElseThrow(ResourceNotFoundException::new);

        Mantenimiento mantenimiento = this.toEntity(mantenimientoDto);
        mantenimiento.setVehiculo(vehiculo);
        this.mantenimientoRepository.save(mantenimiento);

        return mantenimiento.getId();
    }

    @Override
    public void update(Integer id, MantenimientoDto mantenimientoDto) throws MyHotelHttpException {
        if(this.mantenimientoRepository.findById(id)
                .filter(Predicate.not(Mantenimiento::isBorrado))
                .stream()
                .findAny()
                .isEmpty()){
            throw new ResourceNotFoundException();
        } else {
            Mantenimiento mantenimiento = toEntity(mantenimientoDto);
            mantenimiento.setId(id);
            mantenimientoRepository.save(mantenimiento);
        }
    }

    @Override
    public List<MantenimientoFullResponseDto> getAll(Pageable pageable) {
        return mantenimientoRepository.findAll(pageable).stream()
                .filter(Predicate.not(Mantenimiento::isBorrado))
                .map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) throws ResourceNotFoundException {
        Mantenimiento mantenimiento = this.mantenimientoRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);
        mantenimiento.setBorrado(true);
        this.mantenimientoRepository.save(mantenimiento);
    }

    @Override
    public void finaizarMantenimiento(Integer id) throws MyHotelHttpException {
        Mantenimiento mantenimiento = this.mantenimientoRepository.findById(id)
                .filter(Predicate.not(Mantenimiento::isBorrado))
                .orElseThrow(ResourceNotFoundException::new);
        mantenimiento.setFechaFinalizacion(new Date());
        this.mantenimientoRepository.save(mantenimiento);
    }

    @Override
    public MantenimientoFullResponseDto getById(Integer id) throws MyHotelHttpException {
        return mantenimientoRepository.findById(id).map(this::toDto)
                .orElseThrow(ResourceNotFoundException::new);
    }


    private Mantenimiento toEntity(MantenimientoDto mantenimientoDto){
        return modelMapper.map(mantenimientoDto, Mantenimiento.class);
    }

    private MantenimientoFullResponseDto toDto(Mantenimiento mantenimiento){
        return modelMapper.map(mantenimiento,MantenimientoFullResponseDto.class);
    }
}
