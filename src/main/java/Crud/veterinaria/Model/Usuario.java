package Crud.veterinaria.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity // se va a transformar en BD
public class Usuario {

    @Id
//genera valor id automaticamente- solo impacta al primer atributo

//solo impacta al primer atributo
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long id;
    private String name;
    private String password;
    private String email;
    private String address;
    private long phone;


    public Usuario() {

    }

    public Usuario(long id, String name, String password, String email, String address, String name1, long phone) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.address = address;
        this.phone = phone;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}


