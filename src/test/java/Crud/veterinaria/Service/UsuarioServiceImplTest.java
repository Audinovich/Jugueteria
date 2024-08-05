package Crud.veterinaria.Service;

import Crud.veterinaria.Model.Usuario;
import Crud.veterinaria.Repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@SpringBootTest
class UsuarioServiceImplTest {

    @Autowired
    private UsuarioServiceImpl usuarioService;

    @MockBean
    private UsuarioRepository usuarioRepository;



    @Test
    void saveUsuario() {

        Usuario usuario1 = new Usuario();
        usuario1.setName("ariel");
        usuario1.setPassword("pass");


        Mockito.when(usuarioRepository.save(any())).thenReturn(usuario1);

        Usuario usuario2 = usuarioService.saveUsuario(new Usuario());

        assertNotNull(usuario2);
        assertTrue(usuario2.getName().equals("ariel"));
        assertTrue(usuario2.getPassword().equals("pass"));
        assertEquals("ariel", usuario2.getName());
        assertEquals("pass", usuario2.getPassword());


    }
}