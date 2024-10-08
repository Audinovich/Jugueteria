package Crud.veterinaria.Model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity// se va a transformar en registro de BD
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Mascota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String nombre;
    private String especie;
    private String sexo;
    private String edad;
    private String raza;
    private String color;




    // RELACION ONE TO MANY DE MASCOTAS A PRACTICAS

    @OneToMany(mappedBy = "mascota", cascade = CascadeType.ALL)
    @JsonManagedReference

    private List<Citas> citas = new ArrayList<>();

    public List<Citas> getCitas() {
        return citas;
    }

    public void setCita(List<Citas> citas) {
        this.citas = citas;
    }


    //RELACION MANY TO ONE ENTRE MUCHAS MASCOTAS Y  UN USUARIOS

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "usuarioId", nullable = true)
    private Usuario usuario;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }




    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getRaza() {
        return raza;
    }

    public void setRaza(String raza) {
        this.raza = raza;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }


}

// TODO : Test Merge

