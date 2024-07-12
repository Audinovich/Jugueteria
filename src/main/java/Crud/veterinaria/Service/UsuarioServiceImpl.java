package Crud.veterinaria.Service;

import Crud.veterinaria.Model.Usuario;
import Crud.veterinaria.Repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;


@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public ArrayList<Usuario> getAllUsuario() {
        return (ArrayList<Usuario>) usuarioRepository.findAll();
    }

    @Override
    public Optional<Usuario> getUsuarioById(long id) {
        return usuarioRepository.findById(id);
    }

    @Override
    public Usuario saveUsuario(Usuario u) {
        return usuarioRepository.save(u);
    }

    @Override
    public boolean deleteUsuarioById(long id) {

        try {
            Optional<Usuario> u = getUsuarioById(id);
            usuarioRepository.delete(u.get());
            return true;

        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean authenticate(String name, String password) {
        Optional<Usuario> usuario = usuarioRepository.findByNameAndPassword(name, password);
        return usuario.isPresent();

    }

    @Override
    public Optional <Usuario> updateUsuario(Usuario u, long id) {
        Optional<Usuario> usuarioEncontrado = usuarioRepository.findById(id);

        if (usuarioEncontrado.isPresent()) {
            Usuario usuarioActualizado = usuarioEncontrado.get();
            usuarioActualizado.setName(u.getName());
            usuarioActualizado.setPassword(u.getPassword());
            usuarioActualizado.setEmail(u.getEmail());
            return Optional.of(usuarioRepository.save(usuarioActualizado));

        }
        return null;


    }

    @Override
    public boolean deleteAllUsuario() {

        try{
            usuarioRepository.deleteAll();
            return true;
        }catch (Exception e) {
            return false;
        }


    }

}
