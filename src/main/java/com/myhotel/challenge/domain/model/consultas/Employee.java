package com.myhotel.challenge.domain.model.consultas;

import com.myhotel.challenge.domain.usecase.dto.consultas.PromedioPaisDto;
import com.myhotel.challenge.domain.usecase.dto.consultas.SalarioPromedioDepartamentoDto;
import com.myhotel.challenge.domain.usecase.dto.consultas.SegmentoCantidadDepartamentosDto;
import com.myhotel.challenge.domain.usecase.dto.consultas.SegmentoCantidadEmpleadoDto;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(schema = "myhotel-test",name = "employees")
@NamedNativeQuery(
        name = "getCantidadEmpleadosPorSegmento",
        query =
                "SELECT \t\n" +
                        "\t(\n" +
                        "\tCASE WHEN empl.SALARY < 3500 THEN 'SEGMENTO A'\n" +
                        "\tWHEN empl.SALARY BETWEEN 3500 AND 7999 THEN 'SEGMENTO B'\n" +
                        "\tELSE 'SEGMENTO C' END) AS SEGMENTO, count(*) AS CANTIDAD_EMPLEADOS\n" +
                        "FROM employees empl\n" +
                        "WHERE empl.EMPLOYEE_ID NOT IN ( SELECT jh.EMPLOYEE_ID  FROM job_history jh) -- remove fired employees\n" +
                        "GROUP BY (\n" +
                        "\tCASE WHEN empl.SALARY < 3500 THEN 'SEGMENTO A'\n" +
                        "\tWHEN empl.SALARY BETWEEN 3500 AND 7999 THEN 'SEGMENTO B'\n" +
                        "\tELSE 'SEGMENTO C' END\n" +
                        ")",
        resultSetMapping = "segmento_cantidad_empleado_dto"
)
@SqlResultSetMapping(
        name = "segmento_cantidad_empleado_dto",
        classes = @ConstructorResult(
                targetClass = SegmentoCantidadEmpleadoDto.class,
                columns = {
                        @ColumnResult(name = "SEGMENTO", type = String.class),
                        @ColumnResult(name = "CANTIDAD_EMPLEADOS", type = Integer.class)
                }
        )
)

@NamedNativeQuery(
        name = "getCantidadDepartamentosPorSegmento",
        query =
                "SELECT \n" +
                        "SEGMENTO\n" +
                        ",COUNT(*) AS CANTIDAD_DEPARTAMENTOS\n" +
                        "FROM\n" +
                        "(\n" +
                        "\tSELECT \n" +
                        "\t\tCASE \n" +
                        "\t\t\tWHEN empl.SALARY < 3500 THEN 'SEGMENTO A'\n" +
                        "\t\t\tWHEN empl.SALARY BETWEEN 3500 AND 7999 THEN 'SEGMENTO B'\n" +
                        "\t\t\tELSE 'SEGMENTO C' \n" +
                        "\t\tEND AS SEGMENTO\n" +
                        "\t\t,empl.DEPARTMENT_ID \n" +
                        "\tFROM employees empl\n" +
                        "\tINNER JOIN departments d on empl.DEPARTMENT_ID = d.DEPARTMENT_ID \n" +
                        ") TMP \n" +
                        "GROUP BY (TMP.SEGMENTO)\n" +
                        "ORDER BY TMP.SEGMENTO",
        resultSetMapping = "segmento_cantidad_departamento_dto"
)
@SqlResultSetMapping(
        name = "segmento_cantidad_departamento_dto",
        classes = @ConstructorResult(
                targetClass = SegmentoCantidadDepartamentosDto.class,
                columns = {
                        @ColumnResult(name = "SEGMENTO", type = String.class),
                        @ColumnResult(name = "CANTIDAD_DEPARTAMENTOS", type = Integer.class)
                }
        )
)

@NamedNativeQuery(
        name = "getSalarioPromedioDepartamentos",
        query =
                "SELECT \n" +
                        "dep.DEPARTMENT_ID  \n" +
                        ",dep.DEPARTMENT_NAME \n" +
                        ",ROUND(AVG(empl.SALARY),2) AS AVERAGE_SALARY \n" +
                        "FROM employees empl \n" +
                        "INNER JOIN departments dep on empl.DEPARTMENT_ID  = dep.DEPARTMENT_ID \n" +
                        "WHERE empl.DEPARTMENT_ID IN (\n" +
                        "\tSELECT e.DEPARTMENT_ID \n" +
                        "\tFROM employees e \n" +
                        "\tWHERE e.EMPLOYEE_ID NOT IN ( SELECT jh.EMPLOYEE_ID  FROM job_history jh) -- remove fired employees\n" +
                        "\tGROUP BY e.DEPARTMENT_ID \n" +
                        "\tHAVING COUNT(*) > 10\n" +
                        ") GROUP BY empl.DEPARTMENT_ID  ",
        resultSetMapping = "salario_promedio_departamento"
)
@SqlResultSetMapping(
        name = "salario_promedio_departamento",
        classes = @ConstructorResult(
                targetClass = SalarioPromedioDepartamentoDto.class,
                columns = {
                        @ColumnResult(name = "DEPARTMENT_ID", type = Integer.class),
                        @ColumnResult(name = "DEPARTMENT_NAME", type = String.class),
                        @ColumnResult(name = "AVERAGE_SALARY", type = Double.class),
                }
        )
)

@NamedNativeQuery(
        name = "getPromediosPorPais",
        query =
                "SELECT \n" +
                        "countries.COUNTRY_NAME \n" +
                        ", COUNT(*) AS EMPLOYEES_NUMBER\n" +
                        ", ROUND(AVG(empl.SALARY),2) AS AVERAGE_SALARY\n" +
                        ", MAX(empl.SALARY) AS MAX_SALARY\n" +
                        ", MIN(empl.SALARY) AS MIN_SALARY\n" +
                        ", AVG(TIMESTAMPDIFF(YEAR,empl.HIRE_DATE,CURRENT_DATE)) AS ANTIQUITY_AVERAGE\n" +
                        "FROM employees empl\n" +
                        "INNER JOIN departments dep ON dep.DEPARTMENT_ID = empl.DEPARTMENT_ID \n" +
                        "INNER JOIN locations loc ON loc.LOCATION_ID  = dep.LOCATION_ID \n" +
                        "INNER JOIN countries countries ON countries.COUNTRY_ID = loc.COUNTRY_ID \n" +
                        "WHERE empl.EMPLOYEE_ID NOT IN ( SELECT jh.EMPLOYEE_ID  FROM job_history jh) -- remove fired employees\n" +
                        "GROUP BY(countries.COUNTRY_ID)",
        resultSetMapping = "promedios_pais"
)
@SqlResultSetMapping(
        name = "promedios_pais",
        classes = @ConstructorResult(
                targetClass = PromedioPaisDto.class,
                columns = {
                        @ColumnResult(name = "COUNTRY_NAME", type = String.class),
                        @ColumnResult(name = "EMPLOYEES_NUMBER", type = Integer.class),
                        @ColumnResult(name = "AVERAGE_SALARY", type = Double.class),
                        @ColumnResult(name = "MAX_SALARY", type = Double.class),
                        @ColumnResult(name = "MIN_SALARY", type = Double.class),
                        @ColumnResult(name = "ANTIQUITY_AVERAGE", type = Double.class)
                }
        )
)


public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "EMPLOYEE_ID")
    private Integer employeeId;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    @Column(name = "HIRE_DATE")
    private Date hireDate;

    @Column(name = "JOB_ID")
    private String jobId;

    @Column(name = "SALARY")
    private Double salary;

    @Column(name = "COMMISSION_PCT")
    private Double commissionPct;

    @Column(name = "MANAGER_ID")
    private Double managerId;

    @Column(name = "DEPARTMENT_ID")
    private Integer departmentId;

}

