package com.myhotel.challenge.domain.usecase.impl;

import com.myhotel.challenge.domain.model.Camion;
import com.myhotel.challenge.domain.repository.CamionRepository;
import com.myhotel.challenge.domain.usecase.VehiculoService;
import com.myhotel.challenge.domain.usecase.dto.CamionDto;
import com.myhotel.challenge.exceptions.MyHotelHttpException;
import com.myhotel.challenge.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CamionServiceImpl implements VehiculoService<CamionDto,Camion> {

    private final CamionRepository repository;

    private final ModelMapper modelMapper;

    @Override
    public List<CamionDto> getAll(Pageable pageable) {
        return repository.findAll(pageable).stream()
                .map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public CamionDto getById(Integer id) throws MyHotelHttpException {
        return repository.findById(id).map(this::toDto)
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public Integer create(CamionDto canionDto) {
        Camion c = toEntity(canionDto);
        Camion camion = this.repository.save(c);
        return camion.getVechiculoId();
    }

    @Override
    public void update(Integer id, CamionDto camionDto) throws MyHotelHttpException {
        if(this.repository.findById(id)
                .filter(Predicate.not(Camion::getBorrado))
                .stream()
                .findAny()
                .isEmpty()){
            throw new ResourceNotFoundException();
        } else {
            Camion camion = toEntity(camionDto);
            camion.setVechiculoId(id);
            repository.save(camion);
        }
    }

    @Override
    public void delete(Integer id) throws MyHotelHttpException {
        Camion camion = this.repository.findById(id).orElseThrow(ResourceNotFoundException::new);
        camion.setBorrado(true);
        this.repository.save(camion);
    }

    @Override
    public Camion toEntity(CamionDto camionDto){
        return modelMapper.map(camionDto, Camion.class);
    }
    @Override
    public CamionDto toDto(Camion camion){
        return modelMapper.map(camion,CamionDto.class);
    }
}
