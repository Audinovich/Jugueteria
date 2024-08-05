package Crud.veterinaria.Service;

import Crud.veterinaria.Model.Citas;
import Crud.veterinaria.Model.Mascota;
import Crud.veterinaria.Repository.CitasRepository;
import Crud.veterinaria.Repository.MascotaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CitasServiceImpl implements CitasService {

    @Autowired
    CitasRepository citasRepository;

    @Autowired
    MascotaRepository mascotaRepository;


    @Override
    public ArrayList<Citas> getAllCitas() {
        return (ArrayList<Citas>) citasRepository.findAll();
    }

    @Override
    public Optional<Citas> getCitasById(long id) {
        Optional<Citas> citaOptional = citasRepository.findById(id);

        if (citaOptional.isPresent()) {
            Citas citaEncontrada = citaOptional.get();

            List<Mascota> listaDeMascotas = (List<Mascota>) mascotaRepository.findAllMascotaByUsuario(citaEncontrada.getId());

            citaEncontrada.setMascotas(listaDeMascotas);

            return Optional.of(citaEncontrada);

        }
        return Optional.empty();
    }

    @Override
    public Optional<Citas> updateCitas(Citas c, long id) {
        Optional<Citas> citaEncontrada = citasRepository.findById(id);

        if (citaEncontrada.isPresent()) {
            Citas citaActualizada = citaEncontrada.get();
            citaActualizada.setMascota(c.getMascota());
            citaActualizada.setFechaHora(c.getFechaHora());
            citasRepository.save(citaActualizada);
            return Optional.of(citaActualizada);
        }
        return Optional.empty();
    }

    @Override
    public Citas saveCitas(Citas c) {
        Citas citaGuardada = citasRepository.save(c);
        return citaGuardada;
    }

    @Override
    public boolean deleteCitas(long id) {
        try {
            Optional<Citas> c = getCitasById(id);
            citasRepository.deleteAll();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean deleteAllCitas() {
        try {
            citasRepository.deleteAll();
            return true;

        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public ArrayList<Citas> findAllCitasByMascota(long mascotaId) {
        return (ArrayList<Citas>) citasRepository.findAllCitasByMascota(mascotaId);
    }

}
