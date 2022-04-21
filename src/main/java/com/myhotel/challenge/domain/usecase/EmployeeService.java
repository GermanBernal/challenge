package com.myhotel.challenge.domain.usecase;

import com.myhotel.challenge.domain.usecase.dto.consultas.*;
import com.myhotel.challenge.exceptions.ResourceNotFoundException;

import java.util.List;

public interface EmployeeService {

    List<SegmentoCantidadEmpleadoDto> getCantidadEmpleadosPorSegmento() throws ResourceNotFoundException;

    List<SegmentoCantidadDepartamentosDto> getCantidadDepartamentosPorSegmento() throws ResourceNotFoundException;

    List<EmployeeDto> getEmpleadoConMayorSueldoPorSegmento();

    List<EmployeeDto> getGerentesContratadosConAntiguedad();

    List<SalarioPromedioDepartamentoDto> getSalarioPromedioDepartamentos() throws ResourceNotFoundException;

    List<PromedioPaisDto>getPromediosPorPais() throws ResourceNotFoundException;
}
