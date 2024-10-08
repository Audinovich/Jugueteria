package Crud.veterinaria.Service;

import Crud.veterinaria.Model.Citas;
import Crud.veterinaria.Repository.CitasRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CitasServiceTest {

    @Autowired
    private CitasService citasService;

    @MockBean
    private CitasRepository citasRepository;

    private Citas citaDeReferencia;

    @BeforeEach
    void setUp() {
        LocalDateTime hora = LocalDateTime.now();
        Citas citaDeReferencia = Citas.builder()
                .id(1)
                .fechaHora(hora)
                .build();
        Mockito.when(citasRepository.findById(1L)).thenReturn(Optional.of(citaDeReferencia));
    }
    @Test
    public void findNameFound(){
        Long id = 1L;
        Citas citas = citasService.getCitasById(id).get();
        assertEquals(id,citas.getId());
    }
}