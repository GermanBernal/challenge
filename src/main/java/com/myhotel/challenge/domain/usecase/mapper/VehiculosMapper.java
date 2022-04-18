/*
package com.myhotel.challenge.domain.usecase.mapper;

import com.myhotel.challenge.domain.model.Auto;
import com.myhotel.challenge.domain.usecase.dto.AutoDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface VehiculosMapper {
    VehiculosMapper INSTANCE = Mappers.getMapper(VehiculosMapper.class);

    Auto fromVehiculoDto2(AutoDto autoDto);

    default Auto fromVehiculoDto(AutoDto autoDto){
        Auto auto = new Auto();
        auto.setMarca( autoDto.getMarca() );
        auto.setModelo( autoDto.getModelo() );
        auto.setPatente( autoDto.getPatente() );
        auto.setAnio( autoDto.getAnio() );
        auto.setKilometraje( autoDto.getKilometraje() );
        auto.setCilindrada( autoDto.getCilindrada() );
        auto.setTipo(auto.getTipo());
        auto.setNumeroPuertas(autoDto.getNumeroPuertas());
        auto.setCapacidadMaletero(autoDto.getCapacidadMaletero());
        auto.setCapacidadPasajero(autoDto.getCapacidadPasajero());
        return  auto;
    }


}
*/