package Crud.veterinaria.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import org.hibernate.mapping.ToOne;

import java.util.ArrayList;
import java.util.List;

@Entity// se va a trasnformar en BD
public class Practica {


    //RELACION UNA PRACTICA VARIOS INSUMOS
    @OneToMany (mappedBy = "practica", cascade = CascadeType.ALL)
    @JsonManagedReference

    private List<Insumo> insumo = new ArrayList<>();

    public List<Insumo> getInsumos(){return insumo;}

    public void setInsumo(List<Insumo> insumo) {this.insumo=insumo;}

    //RELACION CITAS Y PRACTICAS
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "citasId")
    private Citas citas;

    public Citas getCitas() {
        return citas;
    }

    public void setCitas(Citas citas) {
        this.citas = citas;
    }

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


