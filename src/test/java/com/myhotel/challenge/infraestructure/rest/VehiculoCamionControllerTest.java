package com.myhotel.challenge.infraestructure.rest;
import com.myhotel.challenge.domain.model.Camion;
import com.myhotel.challenge.domain.usecase.VehiculoService;
import com.myhotel.challenge.domain.usecase.dto.CamionDto;
import com.myhotel.challenge.exceptions.MyHotelHttpException;
import com.myhotel.challenge.infraestructure.interceptors.ExceptionAdvice;
import com.myhotel.challenge.utils.TestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class VehiculoCamionControllerTest {

    @Mock
    private VehiculoService<CamionDto, Camion> vehiculoService;

    private MockMvc mockMvc;

    @InjectMocks
    private VehiculoCamionController controller;

    public VehiculoCamionControllerTest() {
    }


    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(ExceptionAdvice.class)
                .build();
    }

    @Test
    @DisplayName("GET /vehiculo/camion/{vehiculo-id}")
    void testGetCamionById() throws Exception, MyHotelHttpException {
        CamionDto camionDto = CamionDto.builder()
                .marca("marca test")
                .modelo("modelo test")
                .patente("PAT 123")
                .anio(1995)
                .kilometraje(1000.0)
                .cilindrada(150.0)
                .tipo("tipo test")
                .capacidad(5.0)
                .ejes(4)
                .build();
        when(vehiculoService.getById(1)).thenReturn(camionDto);

        String expected = "{\n" +
                "    \"_data\": {\n" +
                "        \"marca\": \"marca test\",\n" +
                "        \"modelo\": \"modelo test\",\n" +
                "        \"patente\": \"PAT 123\",\n" +
                "        \"anio\": 1995,\n" +
                "        \"kilometraje\": 1000.0,\n" +
                "        \"cilindrada\": 150.0,\n" +
                "        \"tipo\": \"tipo test\",\n" +
                "        \"capacidad\": 5.0,\n" +
                "        \"ejes\": 4\n" +
                "    },\n" +
                "    \"_errors\": [],\n" +
                "    \"_status\": \"OK\"\n" +
                "}";

        assertTrue(
                TestUtils.matchJson(
                        mockMvc.perform(get("/vehiculo/camion/{vehiculo-id}", 1))
                                .andExpect(status().isOk())
                                .andReturn()
                                .getResponse()
                                .getContentAsString(),
                        expected
                )
        );

    }

}
