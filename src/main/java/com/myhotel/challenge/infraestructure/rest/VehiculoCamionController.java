package com.myhotel.challenge.infraestructure.rest;


import com.myhotel.challenge.domain.model.Camion;
import com.myhotel.challenge.domain.usecase.VehiculoService;
import com.myhotel.challenge.domain.usecase.dto.CamionDto;
import com.myhotel.challenge.exceptions.MyHotelHttpException;
import com.myhotel.challenge.utils.ResponseEntityBuilder;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/vehiculo")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class VehiculoCamionController {

    private final VehiculoService<CamionDto, Camion> vehiculoService;


    @Operation(summary = "Listar todos los Camiones", tags = { "Camion controler" })
    @GetMapping(value = "camion/all", produces = "application/json")
    public ResponseEntity<?> getAll(@ParameterObject @PageableDefault(size = 20, sort = "marca") Pageable pageable){
        return ResponseEntityBuilder.builder()
                .data(vehiculoService.getAll(pageable))
                .status(HttpStatus.OK)
                .build();
    }
    @Operation(summary = "Obtiene un camion por ID", tags = { "Camion controler" })
    @GetMapping("camion/{vehiculo-id}")
    public ResponseEntity<?> get(@PathVariable("vehiculo-id") Integer id) throws MyHotelHttpException {
        return ResponseEntityBuilder.builder()
                .data(vehiculoService.getById(id))
                .status(HttpStatus.OK)
                .build();
    }

    @Operation(summary = "Crea un camion", tags = { "Camion controler" })
    @PostMapping("/camion")
    public ResponseEntity<?> create(@RequestBody CamionDto camionDto){
        return ResponseEntityBuilder.builder()
                .data(vehiculoService.create(camionDto))
                .status(HttpStatus.CREATED)
                .build();
    }

    @Operation(summary = "Borra un auto por ID", tags = { "Camion controler" })
    @DeleteMapping("/camion/{vehiculo-id}")
    public ResponseEntity<?> delete(@PathVariable("vehiculo-id") Integer id) throws MyHotelHttpException {
        vehiculoService.delete(id);
        return ResponseEntityBuilder.builder()
                .data("Vehiculo borrado exitosamente")
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    @Operation(summary = "Updatea un camion por ID", tags = { "Camion controler" })
    @PutMapping("/camion/{vehiculo-id}")
    public ResponseEntity<?> update(@PathVariable("vehiculo-id") Integer id,@RequestBody CamionDto camionDto) throws MyHotelHttpException {
        vehiculoService.update(id,camionDto);
        return ResponseEntityBuilder.builder()
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}
