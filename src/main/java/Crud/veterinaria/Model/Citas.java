package Crud.veterinaria.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Citas {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    //TODO ELIMINAR 2 COLUMNAS FECHA Y HORA

    //RELACION MANY TO ONE ENTRE MUCHAS CITAS A UNA MASCOTA

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "mascotaId")
        private Mascota mascota;
    private LocalDateTime fechaHora;


    //RELACION ENTRE UNA CITA y UNA PRACTICA
    //CASCADE PERMITE AFECTAR A LOS HIJOS TAMBIEN EN LAS MODIFICACIONES EN ESTE CASO PRACTICA
    @OneToOne(cascade = CascadeType.ALL)
    // @JsonManagedReference
    @JoinColumn(name = "practica")

    private Practica practica;



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


    public Practica getPractica() {
        return practica;
    }

    public void setPractica(Practica practica) {
        this.practica = practica;
    }


}

