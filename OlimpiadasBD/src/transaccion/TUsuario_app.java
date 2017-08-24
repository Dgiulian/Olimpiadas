package transaccion;

import bd.Usuario;
import bd.Usuario_app;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.PasswordHash;

public class TUsuario_app extends TransaccionBase<Usuario_app> {

    @Override
    public List<Usuario_app> getList() {
        return super.getList("select * from usuario_app ");
    }

    public Boolean actualizar(Usuario_app usuario_app) {
        return super.actualizar(usuario_app, "id");
    }

    public Usuario_app getById(Integer id) {
        String query = String.format(
                "select * from usuario_app where usuario_app.id = %d ", id);
        return super.getById(query);
    }

    public String registrarUsuario(String email, String password, String repass, String token) {
        String mensaje = "";
        Usuario_app usuario = this.getByEmail(email);
        if (usuario == null) {
            if (password.equals(repass)) {
                if (this.altaUsuario(email, password, token) > 0) {
                    mensaje = "ok";
                } else {
                    mensaje = "No se pudo crear el usuario";
                }
            } else {
                mensaje = "No coinciden las contraseñas";
            }
        } else {
            mensaje = "El email ingresado ya existe";
        }
        return mensaje;
    }

    public Usuario_app getByEmail(String email) {
        Usuario_app u = new Usuario_app();
        String query = String.format("SELECT * from usuario_app where usuario_app.email = '%s'", email);
        System.out.println(query);
        List<Usuario_app> listaUsuario = this.getList(query);
        if (listaUsuario.size() > 0) {
            return (Usuario_app) listaUsuario.get(0);
        } else {
            return null;
        }
    }

    public int altaUsuario(String email, String password, String token) {
        Usuario_app u = new Usuario_app();
        int id = 0;
        try {

            String password_hash = PasswordHash.createHash(password);
            //String pass_activa = TUsuario.createPassActiva(password);
            u.setUsername(email);
            u.setEmail(email);
            u.setPassword(password_hash);
            u.setActivo(1);
            u.setGcm_id(token);

            java.util.Date dt = new java.util.Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            u.setUltimoacceso(sdf.format(dt));
            u.setFcreacion(sdf.format(dt));
            id = this.alta(u);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
            Logger.getLogger(TUsuario_app.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }

    public boolean validaPassword(Usuario_app u, String password) {

        String hash = u.getPassword();
        boolean valida = false;
        try {
            valida = PasswordHash.validatePassword(password, hash);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
            Logger.getLogger(TUsuario_app.class.getName()).log(Level.SEVERE, null, ex);
        }

        return valida;
    }
    
    public boolean validaPassword(String email, String password) {
        Usuario_app usuario = this.getByEmail(email);
        if (usuario != null) {
            return validaPassword(usuario, password);
        } else {
            return false;
        }
    }

}
