package Crud.veterinaria.Service;


import Crud.veterinaria.Model.Practica;
import Crud.veterinaria.Repository.PracticaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;


@Service
public class PracticaServiceImpl implements PracticaService {

    @Autowired
    PracticaRepository practicaRepository;

    @Override
    public ArrayList<Practica> getAllPractica() {
        return (ArrayList<Practica>) practicaRepository.findAll();
    }

    @Override
    public ArrayList<Practica> getAllPracticaByPractica(String practica) {
        return practicaRepository.findAllByPractica(practica);
    }

    @Override
    public Optional<Practica> getPracticaById(long id) {
        return practicaRepository.findById(id);
    }

    @Override
    public Optional<Practica> updatePractica(Practica p, long id) {
        Optional<Practica> practicaEncontrada = practicaRepository.findById(id);

        if (practicaEncontrada.isPresent()) {
            Practica nuevaPractica = practicaEncontrada.get();
            nuevaPractica.setPractica(p.getPractica());
            nuevaPractica.setId(p.getId());
            nuevaPractica.setDescripcion(p.getDescripcion());
            nuevaPractica.setPrecio(p.getPrecio());
            return Optional.of(practicaRepository.save(nuevaPractica));

        }
        return null;
    }

    @Override
    public Practica savePractica(Practica p) {
        return practicaRepository.save(p);
    }

    @Override
    public boolean deletePracticaById(Long id) {

        try {
            Optional<Practica> p = practicaRepository.findById(id);
            practicaRepository.delete(p.get());
            return true;

        } catch (Exception e) {
            return false;
        }

    }

    @Override
    public boolean deleteAllPractica() {
        try {
            practicaRepository.deleteAll();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
