/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transaccion_vista;

import bd.Categoria;
import bd.Equipo_detalle;
import bd.Jugador;
import bd.Novedad;
import bd.Prueba_deportiva;
import bd.Prueba_deportiva_detalle;
import bd.Usuario_app;
import bd.detalle.EquipoDet;
import bd.detalle.Equipo_detalleDet;
import bd.detalle.Prueba_deportivaDet;
import bd.detalle.Prueba_deportiva_DetalleDet;
import cliente_firebase.Cliente_Firebase;
import cliente_firebase.ObjetoMensaje;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import transaccion.TCategoria;
import transaccion.TEquipo_detalle;
import transaccion.TJugador;
import transaccion.TNovedad;
import transaccion.TUsuario_app;
import utils.Parser;
import utils.TFecha;

/**
 *
 * @author JuanMa
 */
public class TNotificacion {

    String tema = "/topics/olimpiadas";

    public List<String> notificar_particular(String titulo, String texto_mensaje, int to, String[] destinatarios) {

        List<String> errores = new ArrayList<String>();

        if (to == 2) {
            for (String destino : destinatarios) {
                int id = Parser.parseInt(destino);
                if (id > 0) {
                    Usuario_app usuario_app = new TUsuario_app().getById(id);
                    if (usuario_app != null && !usuario_app.getGcm_id().equalsIgnoreCase("")) {
                        ObjetoMensaje mensaje = new ObjetoMensaje();
                        mensaje.setTitulo(titulo);
                        mensaje.setTexto(texto_mensaje);
                        mensaje.setTo(usuario_app.getGcm_id());
                        mensaje.setTipo("particular");
                        System.out.println(new Gson().toJson(mensaje));
                        Cliente_Firebase cf = new Cliente_Firebase();
                        cf.enviar(mensaje);
                    } else {
                        errores.add(destino);
                    }

                }
            }
        } else {
            ObjetoMensaje mensaje = new ObjetoMensaje();
            mensaje.setTitulo(titulo);
            mensaje.setTexto(texto_mensaje);
            mensaje.setTo(tema);
            mensaje.setTipo("particular");
            System.out.println(new Gson().toJson(mensaje));
            Cliente_Firebase cf = new Cliente_Firebase();
            cf.enviar(mensaje);
        }

        return errores;

    }

    public void notificacion_recordatorio(Prueba_deportiva pd) {

        Prueba_deportivaDet prueba = new Prueba_deportivaDet(pd);
        prueba.setearDetalle(true);

        String deporte = prueba.getDeporte().getNombre() + " - " + prueba.getCategoria().getNombre();

        List<Prueba_deportiva_DetalleDet> lista_detalle = prueba.getDetalle_prueba();
        for (Prueba_deportiva_DetalleDet prueba_detalle : lista_detalle) {
            EquipoDet equipoDet = prueba_detalle.getEquipo();
            List<Equipo_detalleDet> jugadores = equipoDet.getJugadores();
            for (Equipo_detalleDet jugador : jugadores) {
                Usuario_app usuario = new TUsuario_app().getByDoc(jugador.getJugador().getDni());
                if (usuario != null) {
                    ObjetoMensaje mensaje = new ObjetoMensaje();
                    mensaje.setTitulo("SE APROXIMA SU PRUEBA - " + jugador.getJugador().getNombre_apellido());
                    mensaje.setTexto(deporte + " - HORARIO: " + TFecha.formatearFechaBdVista(prueba.getFecha()) + " " + prueba.getHora() + " - Lugar: " + prueba.getSede().getNombre() + " - Para mas detalle ingrese en el menú a MI AGENDA. ");
                    mensaje.setTo(usuario.getGcm_id());
                    mensaje.setTipo("particular");

                    System.out.println("***************************");
                    System.out.println("TEXTO: " + deporte + " - HORARIO: " + TFecha.formatearFechaBdVista(prueba.getFecha()) + " " + prueba.getHora() + " - Lugar: " + prueba.getSede().getNombre() + " - Para mas detalle ingrese en el menú a MI AGENDA. ");
                    System.out.println("Envio de mensaje recordatorio a : " + jugador.getJugador().getNombre_apellido());
                    System.out.println("***************************");

                    Cliente_Firebase cf = new Cliente_Firebase();
                    cf.enviar(mensaje);
                }
            }

        }

    }

    public void notificar_novedad(int id_novedad) {

        Novedad novedad = new TNovedad().getById(id_novedad);

        ObjetoMensaje mensaje = new ObjetoMensaje();
        mensaje.setTitulo(novedad.getTitulo());
        mensaje.setTexto(novedad.getSubtitulo());
        mensaje.setTo(tema);
        mensaje.setTipo("novedad");
        mensaje.setId(id_novedad);
        System.out.println(new Gson().toJson(mensaje));
        Cliente_Firebase cf = new Cliente_Firebase();
        cf.enviar(mensaje);

    }

    public void notificar_medalla(Usuario_app usuario, String medalla, int id_categoria, String deporte) {

        Jugador jugador = new TJugador().getByDni(usuario.getDocumento());
        if (jugador != null) {

            ObjetoMensaje mensaje = new ObjetoMensaje();
            mensaje.setTitulo("¡¡¡FELICIACIONES " + jugador.getNombre_apellido() + " !!!");
            mensaje.setTexto("Medalla " + medalla.toUpperCase() + " - " + deporte);
            mensaje.setTo(usuario.getGcm_id());
            mensaje.setTipo("medalla");
            mensaje.setId(id_categoria);

            Cliente_Firebase cf = new Cliente_Firebase();
            cf.enviar(mensaje);
        }

    }

}
