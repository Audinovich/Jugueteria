package Crud.veterinaria.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity// se va a trasnformar en BD
public class Practica {

@Id // primary key
//genera valor Id automaticamente
@GeneratedValue(strategy = GenerationType.IDENTITY)//mediante la siguiente estrategia
    private long id;
    private String practica;
    private String descripcion;
    private String precio;

    public Practica() {

    }

    public Practica(long id, String practica, String descripcion, String precio) {
        this.id = id;
        this.practica = practica;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPractica() {
        return practica;
    }

    public void setPractica(String practica) {
        this.practica = practica;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }
}


