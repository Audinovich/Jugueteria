package Crud.veterinaria.Model;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UsuarioTest {

    @Test
    void getters() {

        Usuario usuario = new Usuario();

        usuario.setPassword("pass");
        usuario.setId(1);
        usuario.setName("name");
        usuario.setMascotas(List.of(new Mascota()));
        usuario.setPhone(123456);


        assertAll("testeo de setters",
                ()-> assertEquals("pass", usuario.getPassword()),
                ()-> assertEquals("name", usuario.getName()),
                ()-> assertNotNull(usuario.getMascotas()),
                ()-> assertEquals(1,usuario.getMascotas().size())
        );

    }
}