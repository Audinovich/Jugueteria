package Usuarios;

public abstract class Usuario {

    //ATRIBUTOS USUARIO Y CONTRASEÑA
    protected String Usuario;
    protected String Password;

    //CONSTRUCTOR
    public Usuario() {
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String usuario) {
        Usuario = usuario;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public Usuario(String usuario, String password) {
        Usuario = usuario;
        Password = password;
    }


}
