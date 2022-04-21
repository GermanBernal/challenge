package com.myhotel.challenge.domain.usecase.impl;

import com.myhotel.challenge.domain.model.consultas.Employee;
import com.myhotel.challenge.domain.repository.EmployeesRepository;
import com.myhotel.challenge.domain.usecase.EmployeeService;
import com.myhotel.challenge.domain.usecase.dto.consultas.*;
import com.myhotel.challenge.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeesRepository repository;
    private final ModelMapper modelMapper;

    @Override
    public List<SegmentoCantidadEmpleadoDto> getCantidadEmpleadosPorSegmento() throws ResourceNotFoundException {
        return this.repository.getCantidadEmpleadosPorSegmento().orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public List<SegmentoCantidadDepartamentosDto> getCantidadDepartamentosPorSegmento() throws ResourceNotFoundException {
        return this.repository.getCantidadDepartamentosPorSegmento().orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public List<EmployeeDto> getEmpleadoConMayorSueldoPorSegmento() {
        return this.repository.getEmpleadoConMayorSueldoPorSegmento()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<EmployeeDto> getGerentesContratadosConAntiguedad() {
        return this.repository.getGerentesContratadosConAntiguedad()
                .stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<SalarioPromedioDepartamentoDto> getSalarioPromedioDepartamentos() throws ResourceNotFoundException {
        return repository.getSalarioPromedioDepartamentos().orElseThrow(ResourceNotFoundException::new);
    }

    @Override
    public List<PromedioPaisDto> getPromediosPorPais() throws ResourceNotFoundException {
        return repository.getPromediosPorPais().orElseThrow(ResourceNotFoundException::new);
    }


    public Employee toEntity(EmployeeDto employeeDto){
        return modelMapper.map(employeeDto, Employee.class);
    }

    public EmployeeDto toDto(Employee employee){
        return modelMapper.map(employee,EmployeeDto.class);
    }
}
