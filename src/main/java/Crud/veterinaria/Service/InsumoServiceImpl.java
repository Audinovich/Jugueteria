package Crud.veterinaria.Service;

import Crud.veterinaria.Model.Insumo;
import Crud.veterinaria.Repository.InsumoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class InsumoServiceImpl implements InsumoService {

    @Autowired
    InsumoRepository insumoRepository;

    @Override
    public ArrayList<Insumo> getAllInsumo() {
        // el método findAll() de insumoRepository devuelve un tipo Iterable<Insumo>, no necesariamente un ArrayList<Insumo>. por eso se aclara
        return (ArrayList<Insumo>) insumoRepository.findAll();

    }

    @Override
    public Optional<Insumo> getInsumoById(long id) {
        //no es necesario aclarar nuevamente el tipo de retorno (Optional<Insumo>) después del return. La razón es que el compilador ya sabe qué tipo de valor se espera
        return insumoRepository.findById(id);

    }

    @Override
    public Optional<Insumo> updateInsumo(Insumo i, long id) {
        Optional<Insumo> insumoEncontrado = insumoRepository.findById(id);

        if (insumoEncontrado.isPresent()) {
            Insumo insumoActualizado = insumoEncontrado.get();
            insumoActualizado.setId(i.getId());
            insumoActualizado.setNombre(i.getNombre());
            insumoActualizado.setDescripcion(i.getDescripcion());
            insumoActualizado.setCantidad(i.getCantidad());

            return Optional.of(insumoRepository.save(insumoActualizado));
        } else {


            throw new EntityNotFoundException("Insumo no encontrado con ID: " + id);
        }


    }

    @Override
    public Insumo saveInsumo(Insumo i) {
        return insumoRepository.save(i);
    }

    @Override
    public Optional<Insumo> deleteInsumo(long id) {
        return Optional.empty();
    }

    @Override
    public boolean deleteAllInsumo() {

        try {
            insumoRepository.deleteAll();
            return true;
        } catch (Exception e) {
            return false;
        }

    }
}
