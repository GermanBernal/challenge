package com.myhotel.challenge.domain.usecase.dto.consultas;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class EmployeeDto {

    @Schema(description = "Id empleado")
    private Integer employeeId;

    @Schema(description = "nombre")
    private String firstName;

    @Schema(description = "apellido")
    private String lastName;

    @Schema(description = "email")
    private String email;

    @Schema(description = "numero de telefono")
    private String phoneNumber;

    @Schema(description = "fecha de contratacion")
    private Date hireDate;

    @Schema(description = "job id")
    private String jobId;

    @Schema(description = "salario")
    private Double salary;

    @Schema(description = "commissionpct")
    private Double commissionPct;

    @Schema(description = "manager id")
    private Double managerId;

    @Schema(description = "department id")
    private Integer departmentId;
}
