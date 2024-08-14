package Crud.veterinaria.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Citas {

    //TODO ELIMINAR 2 COLUMNAS FECHA Y HORA

    //RELACION MANY TO ONE ENTRE MUCHAS CITAS A UNA MASCOTA

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "mascotaId")
    private Mascota mascota;


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id;
    private LocalDateTime fechaHora;




    public Mascota getMascota() {
        return mascota;
    }

    public void setMascota(Mascota mascota) {
        this.mascota = mascota;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    //RELACION ENTRE UNA CITA y UNA PRACTICAS
    @OneToOne (cascade = CascadeType.ALL)
    @JsonManagedReference
    @JoinColumn (name = "practica")

    private Practica practica;

    public Citas() {
    }

    public Citas(Mascota mascota, Practica practica, long id, LocalDateTime fechaHora) {
        this.mascota = mascota;
        this.id = id;
        this.fechaHora = fechaHora;
        this.practica   = practica;
    }


    public Practica getPractica() {
        return practica;
    }

    public void setPractica(Practica practica) {
        this.practica = practica;
    }

    public Citas(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    @Override
    public String toString() {
        return "Cita el: " + fechaHora.toString();
    }

    public void setMascotas(List<Mascota> listaDeMascotas) {
    }
}

