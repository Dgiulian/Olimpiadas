package transaccion;

import bd.Usuario;
import bd.Usuario_app;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.PasswordHash;
import utils.SendMail;

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

    public Usuario_app getByDoc(String doc) {
        String query = String.format(
                "select * from usuario_app where usuario_app.documento = %s ", doc);
        return super.getById(query);
    }

    public String registrarUsuario(String dni, String email, String password, String repass, String token) {
        String mensaje = "";
        Usuario_app usuario = this.getByEmail(email);
        if (usuario == null) {
            if (password.equals(repass)) {
                if (this.altaUsuario(email, dni, password, token) > 0) {
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

    public int altaUsuario(String email, String documento, String password, String token) {
        Usuario_app u = new Usuario_app();
        int id = 0;
        try {

            String password_hash = PasswordHash.createHash(password);
            //String pass_activa = TUsuario.createPassActiva(password);
            u.setUsername(email);
            u.setEmail(email);
            u.setDocumento(documento);
            u.setPassword(password_hash);
            u.setActivo(1);
            u.setGcm_id(token);

            java.util.Date dt = new java.util.Date();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
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
        if (usuario != null && !email.equalsIgnoreCase("undefined") && !email.equalsIgnoreCase("")) {
            return validaPassword(usuario, password);
        } else {
            return false;
        }
    }

    public boolean cambiarPassword(Usuario_app u, String newPassword) {
        boolean todoOk = false;
        try {
            String password_hash = PasswordHash.createHash(newPassword);
            u.setPassword(password_hash);
            todoOk = this.actualizar(u);
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(Usuario_app.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidKeySpecException ex) {
            Logger.getLogger(Usuario_app.class.getName()).log(Level.SEVERE, null, ex);
        }
        return todoOk;
    }

    public final static String createPassNueva() {
        SecureRandom random = new SecureRandom();
        return new BigInteger(130, random).toString(5);
    }

    public boolean resetear_clave(String email) {

        Usuario_app u = this.getByEmail(email);

        if (u != null) {
            TUsuario_app tu = new TUsuario_app();
            String pass_old = u.getPassword();
            String nuevaPass = TUsuario.createPassNueva();
            tu.cambiarPassword(u, nuevaPass);

            String cuerpoMensaje = "<h4>OKN 2017 - Olvido de clave </h4>";
            cuerpoMensaje += "<h3>Olvido de contrase&ntilde;a</h3>";

            cuerpoMensaje += "Hemos recibido una solicitud para cambio de contrase&ntilde;a:<br>";
            cuerpoMensaje += "Su nueva contrase&ntilde;a es: %s<br>";
            cuerpoMensaje += "Atentamente,<br>" + " OKN 20117.- ";
            cuerpoMensaje += "<br><br><small>Esta direcci&oacute;n de correo electr&oacute;nico no admite respuestas.</small>";

            cuerpoMensaje = String.format(cuerpoMensaje, nuevaPass);

            if (new SendMail().send(email, "OKN 2017 - Olvido de clave", cuerpoMensaje)) {
                return true;
            } else {
                u.setPassword(pass_old);
                tu.actualizar(u);
                return false;
            }
        } else {

            return false;

        }
    }

}
