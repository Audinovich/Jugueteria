package Crud.veterinaria.Repository;

import Crud.veterinaria.Model.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//metodos por default para busquedas + adicionados
// CAPA DE PERSISTENCIA - GUARDADO
//TRABAJA CON EL SERVIDOR DIRECTAMENTE , MEDIANTE JPA , TRANSFORMA LOS OBJETOS EN FILAS  DE TABLAS
@Repository
public interface UsuarioRepository extends CrudRepository<Usuario,Long> {
    Optional<Usuario> findByNameAndPassword(String name, String password);

}
