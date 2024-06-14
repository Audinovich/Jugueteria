package jugueteria.demo.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Articulo {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String articulo;
    private String precio;

    public Articulo() {

    }

    public Articulo (long id, String articulo, String precio) {
        this.id = id;
        this.articulo = articulo;
        this.precio = precio;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getArticulo() {
        return articulo;
    }

    public void setArticulo(String articulo) {
        this.articulo = articulo;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }
}


