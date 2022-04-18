package com.myhotel.challenge.domain.usecase.impl;

import com.myhotel.challenge.domain.model.Auto;
import com.myhotel.challenge.domain.model.Camion;
import com.myhotel.challenge.domain.repository.AutoRepository;
import com.myhotel.challenge.domain.usecase.VehiculoService;
import com.myhotel.challenge.domain.usecase.dto.AutoDto;
import com.myhotel.challenge.exceptions.MyHotelHttpException;
import com.myhotel.challenge.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class AutomovilServiceImpl implements VehiculoService<AutoDto, Auto> {

    private final ModelMapper modelMapper;
    private final AutoRepository repository;

    @Override
    public List<AutoDto> getAll(Pageable pageable) {
        return repository.findAll(pageable).stream()
                .filter(Predicate.not(Auto::getBorrado))
                .map(this::toDto).collect(Collectors.toList());
    }

    @Override
    public AutoDto getById(Integer id) throws MyHotelHttpException {
        return repository.findById(id).map(this::toDto)
                .orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public Integer create(AutoDto autoDto) {
        return this.repository.save(toEntity(autoDto)).getVechiculoId();
    }

    @Override
    public void update(Integer id, AutoDto autoDto) throws MyHotelHttpException {
        if(this.repository.findById(id)
                .filter(Predicate.not(Auto::getBorrado))
                .stream()
                .findAny()
                .isEmpty()){
            throw new ResourceNotFoundException();
        } else {
            Auto auto = toEntity(autoDto);
            auto.setVechiculoId(id);
            repository.save(auto);
        }
    }

    @Override
    public void delete(Integer id) throws MyHotelHttpException {
        Auto auto = this.repository.findById(id).orElseThrow(ResourceNotFoundException::new);
        auto.setBorrado(true);
        this.repository.save(auto);
    }
    @Override
    public Auto toEntity(AutoDto autoDto){
        return modelMapper.map(autoDto, Auto.class);
    }
    @Override
    public AutoDto toDto(Auto auto){
        return modelMapper.map(auto,AutoDto.class);
    }
}
