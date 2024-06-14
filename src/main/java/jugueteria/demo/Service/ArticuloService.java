package jugueteria.demo.Service;

import jugueteria.demo.Model.Articulo;
import java.util.ArrayList;
import java.util.Optional;

public interface ArticuloService {

    ArrayList<Articulo> getAllArticulo();
    Optional<Articulo> getArticuloById(Long id);
    Articulo saveArticulo(Articulo a);
    Boolean deleteArticuloById(Long id);

}
