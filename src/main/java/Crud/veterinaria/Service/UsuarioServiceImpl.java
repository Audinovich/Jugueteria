package Crud.veterinaria.Service;

import Crud.veterinaria.Model.Mascota;
import Crud.veterinaria.Model.Usuario;
import Crud.veterinaria.Repository.MascotaRepository;
import Crud.veterinaria.Repository.UsuarioRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;


@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    UsuarioRepository usuarioRepository;
    @Autowired
    MascotaRepository mascotaRepository;

    @Override
    public ArrayList<Usuario> getAllUsuario() {
        return (ArrayList<Usuario>) usuarioRepository.findAll();
    }

    @Override
    public Optional<Usuario> getUsuarioById(long id) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);

        if (usuarioOptional.isPresent()) {
            Usuario usuarioEncontrado = usuarioOptional.get();

            List<Mascota> listaDeMascotas = (List<Mascota>) mascotaRepository.findAllMascotaByUsuario(usuarioEncontrado.getId());

            usuarioEncontrado.setMascotas(listaDeMascotas);

            return Optional.of(usuarioEncontrado);
        }

        return Optional.empty();
    }


    @Override
    public Usuario saveUsuario(Usuario u) {

        Usuario usuarioGuardado = usuarioRepository.save(u);

        return usuarioGuardado;
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
    public Usuario authenticate(String name, String password) {
        Optional<Usuario> usuario = usuarioRepository.findByNameAndPassword(name, password);
        return usuario.orElse(null);

    }

    @Override
    public boolean getAuthenticatedUser(String clienteId) {
        Optional<Usuario> usuarioLogin = usuarioRepository.findByName(clienteId);
        return usuarioLogin.isPresent();
    }


    //TODO AGREGAR MAS METODOS DE LA CLASE
    @Override
    public Optional<Usuario> updateUsuario(Usuario u, long id) {
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

        try {
            usuarioRepository.deleteAll();
            return true;
        } catch (Exception e) {
            return false;
        }


    }

}
