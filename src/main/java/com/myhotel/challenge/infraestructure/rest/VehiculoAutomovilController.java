package com.myhotel.challenge.infraestructure.rest;

import com.myhotel.challenge.domain.model.Auto;
import com.myhotel.challenge.domain.usecase.VehiculoService;
import com.myhotel.challenge.domain.usecase.dto.AutoDto;
import com.myhotel.challenge.exceptions.MyHotelHttpException;
import com.myhotel.challenge.utils.ResponseEntityBuilder;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
public class VehiculoAutomovilController {

    private final VehiculoService<AutoDto, Auto> vehiculoService;

    @Operation(summary = "Listar todos los autos", tags = { "Auto controler" })
    @GetMapping(value = "automovil/all", produces = "application/json")
    public ResponseEntity<?> getAll(@ParameterObject @PageableDefault(size = 20, sort = "marca") Pageable pageable){
        return ResponseEntityBuilder.builder()
                .data(vehiculoService.getAll(pageable))
                .status(HttpStatus.OK)
                .build();
    }

    @Operation(summary = "Obtiener un auto por ID", tags = { "Auto controler" })
    @GetMapping(value = "automovil/{vehiculo-id}", produces = "application/json")
    public ResponseEntity<?> get(@PathVariable("vehiculo-id") Integer id) throws MyHotelHttpException {
        return ResponseEntityBuilder.builder()
                .data(vehiculoService.getById(id))
                .status(HttpStatus.OK)
                .build();
    }

    @Operation(summary = "Crear un auto", tags = { "Auto controler" })
    @PostMapping("/vehiculo")
    public ResponseEntity<?> create(@RequestBody AutoDto autoDto){
        return ResponseEntityBuilder.builder()
                .data(vehiculoService.create(autoDto))
                .status(HttpStatus.CREATED)
                .build();
    }

    @Operation(summary = "Borrar un auto por ID", tags = { "Auto controler" })
    @DeleteMapping("/automovil/{vehiculo-id}")
    public ResponseEntity<?> delete(@PathVariable("vehiculo-id") Integer id) throws MyHotelHttpException {
        vehiculoService.delete(id);
        return ResponseEntityBuilder.builder()
                .data("Vehiculo borrado exitosamente")
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    @Operation(summary = "Updatea un auto por ID", tags = { "Auto controler" })
    @PutMapping("/automovil/{vehiculo-id}")
    public ResponseEntity<?> update(@PathVariable("vehiculo-id") Integer id,@RequestBody AutoDto autoDto) throws MyHotelHttpException {
        vehiculoService.update(id,autoDto);
        return ResponseEntityBuilder.builder()
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

}
