package com.myhotel.challenge.infraestructure.rest;

import com.myhotel.challenge.domain.usecase.EmployeeService;
import com.myhotel.challenge.exceptions.ResourceNotFoundException;
import com.myhotel.challenge.utils.ResponseEntityBuilder;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consultas")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EmployeesController {

    private final EmployeeService service;

    @Operation(summary = "Obtiene cantidad de empleados por segmentos de sueldo", tags = { "Consultas controler" })
    @GetMapping(value = "empleados/segmentos", produces = "application/json")
    public ResponseEntity<?> getEmpleadosPorSegmento() throws ResourceNotFoundException {
        return ResponseEntityBuilder.builder()
                .data(service.getCantidadEmpleadosPorSegmento())
                .status(HttpStatus.OK)
                .build();
    }

    @Operation(summary = "Obtiene cantidad de departamentos por segmentos de sueldo", tags = { "Consultas controler" })
    @GetMapping(value = "departamentos/segmentos", produces = "application/json")
    public ResponseEntity<?> getDepartamentosPorSegmento() throws ResourceNotFoundException {
        return ResponseEntityBuilder.builder()
                .data(service.getCantidadDepartamentosPorSegmento())
                .status(HttpStatus.OK)
                .build();
    }

    @Operation(summary = "Obtiene gerentes contratados hace mas de 15 años", tags = { "Consultas controler" })
    @GetMapping(value = "gerentes", produces = "application/json")
    public ResponseEntity<?> getGerentesContratadosConAntiguedad(){
        return ResponseEntityBuilder.builder()
                .data(service.getGerentesContratadosConAntiguedad())
                .status(HttpStatus.OK)
                .build();
    }

    @Operation(summary = "Empleado con mayor sueldo por departamento", tags = { "Consultas controler" })
    @GetMapping(value = "departamentos/empleado-mayor-sueldo", produces = "application/json")
    public ResponseEntity<?> getEmpleadoConMayorSueldoPorDepartamento(){
        return ResponseEntityBuilder.builder()
                .data(service.getGerentesContratadosConAntiguedad())
                .status(HttpStatus.OK)
                .build();
    }

    @Operation(summary = "Obtiene el salario promedio de todos los departamentos que tengan más de 10 empleados", tags = { "Consultas controler" })
    @GetMapping(value = "departamentos/salario-promedio", produces = "application/json")
    public ResponseEntity<?> getSalarioPromedioDepartamentos() throws ResourceNotFoundException {
        return ResponseEntityBuilder.builder()
                .data(service.getSalarioPromedioDepartamentos())
                .status(HttpStatus.OK)
                .build();
    }

    @Operation(summary = "Obtiene por pais la cantidad empleados, salario promedio, salario más alto, salario más bajo y promedio años de antigüedad", tags = { "Consultas controler" })
    @GetMapping(value = "pais/datos-promedios", produces = "application/json")
    public ResponseEntity<?> getPromediosPorPais() throws ResourceNotFoundException {
        return ResponseEntityBuilder.builder()
                .data(service.getPromediosPorPais())
                .status(HttpStatus.OK)
                .build();
    }
}
