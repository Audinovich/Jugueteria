package Crud.veterinaria.Repository;

import Crud.veterinaria.Model.Citas;
import Crud.veterinaria.Model.Mascota;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CitasRepositoryTest {

    @MockBean
    private CitasRepository citasRepository;

    private Citas citas;

    @BeforeEach
    void setUp() {
        Mascota mascota = Mascota.builder()
                .id(1L)
                .nombre("Antonio")
                .build();

        citas = Citas.builder()
                .id(1L)
                .fechaHora(LocalDateTime.now())
                .mascota(mascota)
                .build();

        Mockito.when(citasRepository.findById(1L)).thenReturn(Optional.of(citas));
    }

    @Test
    public void testCitaExiste() {
        Optional<Citas> citaEncontrada = citasRepository.findById(1L);
        assertTrue(citaEncontrada.isPresent());
    }

    @Test
    public void testIdCita() {
        Optional<Citas> citaEncontrada = citasRepository.findById(1L);
        assertEquals(1L, citaEncontrada.get().getId());
    }

    @Test
    public void testNombreMascota() {
        Optional<Citas> citaEncontrada = citasRepository.findById(1L);
        assertEquals("Antonio", citaEncontrada.get().getMascota().getNombre());
    }
}
