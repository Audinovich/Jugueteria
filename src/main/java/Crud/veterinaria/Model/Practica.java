package Crud.veterinaria.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Practica {

    // Relación One-to-Many con Insumo
    @OneToMany(mappedBy = "practica", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Insumo> insumos = new ArrayList<>();

    public List<Insumo> getInsumos() {
        return insumos;
    }

    public void setInsumo(List<Insumo> insumo) {
        this.insumos = insumo;
    }

    // Relación One-to-One con Citas
    @OneToOne
    @JoinColumn(name = "citasId")
    @JsonBackReference
    private Citas citas;

    public Citas getCitas() {
        return citas;
    }

    public void setCitas(Citas citas) {
        this.citas = citas;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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