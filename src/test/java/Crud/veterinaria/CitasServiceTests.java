package Crud.veterinaria;

import Crud.veterinaria.Model.Citas;
import Crud.veterinaria.Repository.CitasRepository;
import Crud.veterinaria.Service.CitasService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import lombok.Builder;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class CitasServiceTest {

	@Autowired
	private CitasService citasService;

	@MockBean
	private CitasRepository citasRepository;


	@BeforeEach
	void contextLoads() {
         Citas citas = Citas.builder().id(1L)
				 .build();

		 Mockito.when(citasRepository.findById(1L)).thenReturn(Optional.of(citas));
	}

	@Test
	public void getCitasByIdFound(){
		Long citaEncontrada = 1L;
		Citas citas = citasService.getCitasById(citaEncontrada).get();
		assertEquals(citaEncontrada, citas.getId());

	}
}
