package Crud.veterinaria.Service;

import Crud.veterinaria.Model.Usuario;

import java.util.ArrayList;
import java.util.Optional;

//no mismos que repositorio
public interface UsuarioService {

    ArrayList<Usuario> getAllUsuario();

    Optional<Usuario> getUsuarioById(long id);

    Usuario saveUsuario(Usuario a);

    boolean deleteUsuarioById(long id);

    Usuario authenticate(String name, String password);

    boolean getAuthenticatedUser (String clienteId);

    Optional <Usuario> updateUsuario(Usuario u, long id);

    boolean deleteAllUsuario();

}
