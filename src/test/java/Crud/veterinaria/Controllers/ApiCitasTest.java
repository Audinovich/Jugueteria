package Crud.veterinaria.Controllers;

import Crud.veterinaria.Model.Citas;
import Crud.veterinaria.Model.Practica;
import Crud.veterinaria.Service.CitasService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ApiCitas.class)
class ApiCitasTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CitasService citasService;

    private Citas citaCreadaDeAntes;

    @BeforeEach
    void setUp() {

        this.citaCreadaDeAntes = Citas.builder()
                .id(1L)
                .fechaHora(LocalDateTime.now())
                .practica(new Practica(1,"practuca","descip","carisima"))
                .build();
    }

    @Test
    void test_get() throws Exception {
////
//        Citas postCitas = Citas.builder()
//
//                .fechaHora(LocalDateTime.now())
//                .build();

//        String jsonContent = "{ \"id\": 1, \"fechaHora\": \"" + LocalDateTime.now().toString() + "\" }";

        Mockito.when(citasService.getCitasById(1L)).thenReturn(Optional.of(citaCreadaDeAntes));

        mockMvc.perform(
                get("/Citas/find/1")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(jsonContent)
                        )
                .andExpect(status().isOk());
    }

    @Test
    void test_get_2() throws Exception {
////
//        Citas postCitas = Citas.builder()
//
//                .fechaHora(LocalDateTime.now())
//                .build();

//        String jsonContent = "{ \"id\": 1, \"fechaHora\": \"" + LocalDateTime.now().toString() + "\" }";

        Mockito.when(citasService.getCitasById(anyInt())).thenReturn(Optional.of(citaCreadaDeAntes));

        mockMvc.perform(
                        get("/Citas/find/12")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(jsonContent)
                )
                .andExpect(status().isOk());
    }

    @Test
    void test_saveCita() throws Exception {

        LocalDateTime hora = LocalDateTime.now();

        Citas unaCitaEspecifica = Citas.builder()
                .id(1)
                .fechaHora(hora)
                .build();

        Mockito.when(citasService.saveCitas(unaCitaEspecifica,3)).thenReturn(citaCreadaDeAntes);
//        Mockito.when(citasService.saveCitas(any(),anyInt())).thenReturn(citaCreadaDeAntes);

        String jsonContent = "{ \"id\": 1, \"fechaHora\": \"" + hora.toString() + "\" }";
        mockMvc.perform(
                        post("/Citas/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonContent)
                                .param("idPractica","3")
                )
                .andExpect(status().isOk());
    }


}