package jugueteria.demo.Service;



import jugueteria.demo.Model.Articulo;
import jugueteria.demo.Repository.ArticuloRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;


@Service
public class ArticuloServiceImpl implements ArticuloService {

    @Autowired
    ArticuloRepository articuloRepository;

    @Override
    public ArrayList<Articulo> getAllArticulo() {
        return (ArrayList<Articulo>) articuloRepository.findAll();
    }

    @Override
    public Optional<Articulo> getArticuloById(Long id) {
        return articuloRepository.findById(id);
    }

    @Override
    public Articulo saveArticulo(Articulo a) {
        return null;
    }

    @Override
    public Boolean deleteArticuloById(Long id) {

        try{
            Optional<Articulo> a = getArticuloById(id);
            articuloRepository.delete(a.get());
            return true;

        }catch (Exception e){
            return false;
        }
    }
}
