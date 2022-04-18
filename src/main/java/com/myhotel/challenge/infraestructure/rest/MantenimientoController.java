package com.myhotel.challenge.infraestructure.rest;

import com.myhotel.challenge.domain.usecase.MantenimientoService;
import com.myhotel.challenge.domain.usecase.dto.CamionDto;
import com.myhotel.challenge.domain.usecase.dto.MantenimientoDto;
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
@RequestMapping("/mantenimiento")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MantenimientoController {

    private final MantenimientoService mantenimientoService;

    @Operation(summary = "Ingresar un auto a mantenimiento", tags = { "Mantenimiento controler" })
    @PostMapping("/ingresar")
    public ResponseEntity<?> create(@RequestBody MantenimientoDto mantenimientoDto) throws MyHotelHttpException {
        return ResponseEntityBuilder.builder()
                .data(mantenimientoService.create(mantenimientoDto))
                .status(HttpStatus.CREATED)
                .build();
    }

    @Operation(summary = "Listar todos los mantenimientos", tags = { "Mantenimiento controler" })
    @GetMapping(value = "listar/all", produces = "application/json")
    public ResponseEntity<?> getAll(@ParameterObject @PageableDefault(size = 20, sort = "fechaIngreso") Pageable pageable){
        return ResponseEntityBuilder.builder()
                .data(mantenimientoService.getAll(pageable))
                .status(HttpStatus.OK)
                .build();
    }

    @Operation(summary = "Borrar un mantenimiento por ID", tags = { "Mantenimiento controler" })
    @DeleteMapping("/{mantenimiento-id}")
    public ResponseEntity<?> delete(@PathVariable("mantenimiento-id") Integer id) throws MyHotelHttpException {
        mantenimientoService.delete(id);
        return ResponseEntityBuilder.builder()
                .data("mantenimiento borrado exitosamente")
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    @Operation(summary = "Finaliza el mantenimiento por ID, seteando fecha de finalizacion", tags = { "Mantenimiento controler" })
    @PatchMapping("finalizar/{mantenimiento-id}")
    public ResponseEntity<?> finalizarMantenimiento(@PathVariable("mantenimiento-id") Integer id) throws MyHotelHttpException {
        mantenimientoService.finaizarMantenimiento(id);
        return ResponseEntityBuilder.builder()
                .data("mantenimiento finalizado exitosamente")
                .status(HttpStatus.NO_CONTENT)
                .build();
    }

    @Operation(summary = "Obtiener un mantenimiento por ID", tags = { "Mantenimiento controler" })
    @GetMapping(value = "{vehiculo-id}", produces = "application/json")
    public ResponseEntity<?> get(@PathVariable("vehiculo-id") Integer id) throws MyHotelHttpException {
        return ResponseEntityBuilder.builder()
                .data(mantenimientoService.getById(id))
                .status(HttpStatus.OK)
                .build();
    }

    @Operation(summary = "Updatea un mantenimiento por ID", tags = { "Mantenimiento controler" })
    @PutMapping("/{mantenimiento-id}")
    public ResponseEntity<?> update(@PathVariable("mantenimiento-id") Integer id,@RequestBody MantenimientoDto mantenimientoDto) throws MyHotelHttpException {
        mantenimientoService.update(id,mantenimientoDto);
        return ResponseEntityBuilder.builder()
                .status(HttpStatus.NO_CONTENT)
                .build();
    }
}
