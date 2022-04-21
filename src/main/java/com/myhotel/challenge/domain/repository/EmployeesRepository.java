package com.myhotel.challenge.domain.repository;

import com.myhotel.challenge.domain.model.consultas.Employee;
import com.myhotel.challenge.domain.usecase.dto.consultas.PromedioPaisDto;
import com.myhotel.challenge.domain.usecase.dto.consultas.SalarioPromedioDepartamentoDto;
import com.myhotel.challenge.domain.usecase.dto.consultas.SegmentoCantidadDepartamentosDto;
import com.myhotel.challenge.domain.usecase.dto.consultas.SegmentoCantidadEmpleadoDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface EmployeesRepository extends JpaRepository<Employee, Integer> {
    @Query(name = "getCantidadEmpleadosPorSegmento", nativeQuery = true)
    Optional<List<SegmentoCantidadEmpleadoDto>> getCantidadEmpleadosPorSegmento();

    @Query(name = "getCantidadDepartamentosPorSegmento", nativeQuery = true)
    Optional<List<SegmentoCantidadDepartamentosDto>> getCantidadDepartamentosPorSegmento();

    @Query(name = "getSalarioPromedioDepartamentos", nativeQuery = true)
    Optional<List<SalarioPromedioDepartamentoDto>> getSalarioPromedioDepartamentos();

    @Query(name = "getPromediosPorPais", nativeQuery = true)
    Optional<List<PromedioPaisDto>> getPromediosPorPais();

    @Query(value = "SELECT  empl.* \n" +
            "FROM employees empl\n" +
            "INNER JOIN (\n" +
            "\tSELECT max(e.SALARY) AS MAX_SALARY, DEPARTMENT_ID   \n" +
            "\tFROM employees e \n" +
            "\tWHERE e.EMPLOYEE_ID NOT IN ( SELECT jh.EMPLOYEE_ID  FROM job_history jh) -- remove fired employees\n" +
            "\tGROUP BY e.DEPARTMENT_ID \n" +
            ") dep_max_salary ON dep_max_salary.MAX_SALARY = empl.SALARY AND dep_max_salary.DEPARTMENT_ID = empl.DEPARTMENT_ID \n" +
            "ORDER BY DEPARTMENT_ID ",nativeQuery = true)
    List<Employee> getEmpleadoConMayorSueldoPorSegmento();

    @Query(value = "SELECT manager.* \n" +
            "FROM employees manager\n" +
            "INNER JOIN departments dep on dep.MANAGER_ID  = manager.EMPLOYEE_ID  \n" +
            "WHERE TIMESTAMPDIFF(YEAR,manager.HIRE_DATE,CURRENT_DATE) > 15\n", nativeQuery = true)
    List<Employee> getGerentesContratadosConAntiguedad();

}
