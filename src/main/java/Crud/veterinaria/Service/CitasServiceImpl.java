package Crud.veterinaria.Service;

import Crud.veterinaria.Model.Citas;
import Crud.veterinaria.Model.Mascota;
import Crud.veterinaria.Model.Practica;
import Crud.veterinaria.Repository.CitasRepository;
import Crud.veterinaria.Repository.MascotaRepository;
import Crud.veterinaria.Repository.PracticaRepository;
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

    @Autowired
    PracticaRepository practicaRepository;

    @Override
    public ArrayList<Citas> getAllCitas() {
        return (ArrayList<Citas>) citasRepository.findAll();
    }

    @Override
    public Optional<Citas> getCitasById(long id) {
        Optional<Citas> citaOptional = citasRepository.findById(id);

        if (citaOptional.isPresent()) {
            Citas citaEncontrada = citaOptional.get();
            return Optional.of(citaEncontrada);

        }
        return Optional.empty();
    }

    @Override
    public Optional<Citas> updateCitas(Citas c,   long id) {
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
    public Citas saveCitas(Citas c,Integer idPractica) {
        Optional <Practica> practicaEncontrada=  practicaRepository.findById(idPractica.longValue());
       if(practicaEncontrada.isPresent()) {
           c.setPractica(practicaEncontrada.get());
       }else{
           throw new RuntimeException("Práctica no encontrada");
         }
        return citasRepository.save(c);
    }

    @Override
    public boolean deleteCitas(long id) {
        try {
            Optional<Citas> c = getCitasById(id);
            citasRepository.deleteById(id);
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
