package Crud.veterinaria.Repository;

import Crud.veterinaria.Model.Insumo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InsumoRepository extends CrudRepository<Insumo,Long> {
}
