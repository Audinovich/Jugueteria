package Crud.veterinaria.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Citas {

    //RELACION MANY TO ONE ENTRE MUCHAS CITAS A UNA MASCOTA

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "mascotaId")
    private Mascota mascota;

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

    //RELACION ENTRE UNA CITA y UNA O MAS PRACTICAS
    @OneToMany(mappedBy = "citas", cascade = CascadeType.ALL)
    @JsonManagedReference

    private List<Practica> practicas = new ArrayList<>();

    public List<Practica> getPracticas() {
        return practicas;
    }

    public void setPracticas(List<Practica> practicas) {
        this.practicas = practicas;
    }

    public Citas() {
    }

    public Citas(Mascota mascota, List<Practica> practicas, long id, LocalDateTime fechaHora) {
        this.mascota = mascota;
        this.id = id;
        this.fechaHora = fechaHora;
        this.practicas=new ArrayList<>();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id;
    private LocalDateTime fechaHora;

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

