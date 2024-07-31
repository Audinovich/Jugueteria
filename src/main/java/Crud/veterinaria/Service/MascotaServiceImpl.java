package Crud.veterinaria.Service;


import Crud.veterinaria.Model.Mascota;
import Crud.veterinaria.Repository.MascotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;


@Service
public class MascotaServiceImpl implements MascotaService {

    @Autowired
    MascotaRepository mascotaRepository;

    @Override
    public ArrayList<Mascota> getAllMascota() {
        return (ArrayList<Mascota>) mascotaRepository.findAll();
    }

    @Override
    public Optional<Mascota> getMascotaById(long id) {
        return mascotaRepository.findById(id);
    }

    @Override
    public ArrayList<Mascota> findAllMascotaByUsuario(long usuarioId) {
        return (ArrayList<Mascota>) mascotaRepository.findAllMascotaByUsuario(usuarioId);
    }

    @Override
    public Optional<Mascota> updateMascota(Mascota m, long id) {
        Optional<Mascota> mascotaEncontrada = mascotaRepository.findById(id);

        if (mascotaEncontrada.isPresent()) {
            Mascota nuevaMascota = mascotaEncontrada.get();
            nuevaMascota.setNombre(m.getNombre());
            nuevaMascota.setEdad(m.getEdad());
            nuevaMascota.setColor(m.getColor());
            nuevaMascota.setEspecie(m.getEspecie());
            nuevaMascota.setRaza(m.getRaza());
            return Optional.of(mascotaRepository.save(nuevaMascota));
        }
        return null;
    }

    @Override
    public boolean deleteAllMascota() {
        try {
            mascotaRepository.deleteAll();
            return true;
        } catch (Exception e) {

            return false;
        }

    }

    @Override
    public Mascota saveMascota(Mascota m) {
        return mascotaRepository.save(m);
    }

    @Override
    public boolean deleteMascotaById(long id) {

        try {
            Optional<Mascota> m = getMascotaById(id);
            mascotaRepository.delete(m.get());
            return true;

        } catch (Exception e) {
            return false;
        }

    }


}
