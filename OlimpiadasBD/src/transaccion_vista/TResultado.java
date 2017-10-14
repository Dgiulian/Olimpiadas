/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package transaccion_vista;

import bd.Categoria;
import bd.Equipo;
import bd.Equipo_detalle;
import bd.Prueba_deportiva;
import bd.Prueba_deportiva_detalle;
import bd.Usuario_app;
import bd.detalle.Equipo_detalleDet;
import bd.detalle.JugadorDet;
import bd.detalle.Prueba_deportiva_DetalleDet;
import bd.vista.ResultadoVista;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import transaccion.TCategoria;
import transaccion.TEquipo;
import transaccion.TEquipo_detalle;
import transaccion.TPrueba_deportiva;
import transaccion.TPrueba_deportiva_detalle;
import transaccion.TUsuario_app;
import utils.JsonRespuesta;

/**
 *
 * @author JuanMa
 */
public class TResultado {

    public JsonRespuesta procesar_respuesta(int idprueba, String respuestas, String detalle) {

        Prueba_deportiva prueba = new TPrueba_deportiva().getById(idprueba);
        Categoria categoria = new TCategoria().getById(prueba.getId_categoria());
        Gson gs = new Gson();
        Type collectionType = new TypeToken<List<ResultadoVista>>() {
        }.getType();
        List<ResultadoVista> lista_entradas = gs.fromJson(respuestas, collectionType);
        Iterator it_entradas = lista_entradas.iterator();
        while (it_entradas.hasNext()) {
            ResultadoVista resultado = (ResultadoVista) it_entradas.next();

            Prueba_deportiva_detalle pd = new TPrueba_deportiva_detalle().getById(resultado.getId());
            if (pd != null) {
                pd.setResultado(resultado.getResultado());
                pd.setMedalla(resultado.getMedalla());
                if (resultado.getMedalla() != null && !resultado.getMedalla().equalsIgnoreCase("")) {
                    this.notificacion_equipo(pd, resultado.getMedalla());
                }
                //Si la categoria es personalizada
                if (categoria.getTipo_modalidad() == 3) {
                    pd.setPuntos(resultado.getPuntos());
                }
                new TPrueba_deportiva_detalle().actualizar(pd);
            }

        }

        prueba.setDetalle_resultado(detalle);

        JsonRespuesta respuesta = this.actualizar_puntaje(prueba);
        if (respuesta.getResult().equalsIgnoreCase("OK")) {
            prueba.setId_estado(3);
        }
        new TPrueba_deportiva().actualizar(prueba);
        return respuesta;

    }

    public JsonRespuesta actualizar_puntaje(Prueba_deportiva prueba) {

        JsonRespuesta respuesta = new JsonRespuesta();

        Categoria categoria = new TCategoria().getById(prueba.getId_categoria());
        HashMap<String, String> filtro = new HashMap<>();
        filtro.put("id_prueba", String.valueOf(prueba.getId()));

        TPrueba_deportiva_detalle tdetalle = new TPrueba_deportiva_detalle();
        if (categoria.getOrden_puntaje() == 1) {
            if (categoria.getTipo_puntaje() == 2) {
                //tdetalle.setOrderBy(" TIME_TO_SEC(CAST(resultado AS TIME(2))) asc");
                tdetalle.setOrderBy(" resultado desc ");
            } else {
                tdetalle.setOrderBy(" CAST(resultado AS UNSIGNED) desc");
            }

        } else {
            if (categoria.getTipo_puntaje() == 2) {
                //tdetalle.setOrderBy(" TIME_TO_SEC(CAST(resultado AS TIME(2))) desc");
                tdetalle.setOrderBy(" resultado ");
            } else {
                tdetalle.setOrderBy(" CAST(resultado AS UNSIGNED) ");
            }
        }
        List<Prueba_deportiva_detalle> lista = tdetalle.getListFiltro(filtro);
        //Si la categoria es modalidad partido siempre de 2 equipos
        if (categoria.getTipo_modalidad() == 1) {

            if (lista.size() == 2) {
                Prueba_deportiva_detalle equipo1 = lista.get(0);
                Prueba_deportiva_detalle equipo2 = lista.get(1);
                int resultado1 = Integer.parseInt(equipo1.getResultado());
                int resultado2 = Integer.parseInt(equipo2.getResultado());
                equipo1.setResultado_encontra(resultado2);
                equipo2.setResultado_encontra(resultado1);
                if (resultado1 > resultado2) {
                    equipo1.setPuntos(categoria.getPartido_ganado());
                    equipo2.setPuntos(categoria.getPartido_perdido());
                } else {
                    if (resultado1 == resultado2) {
                        equipo1.setPuntos(categoria.getPartido_empatado());
                        equipo2.setPuntos(categoria.getPartido_empatado());
                    } else {
                        equipo1.setPuntos(categoria.getPartido_perdido());
                        equipo2.setPuntos(categoria.getPartido_ganado());
                    }
                }
                new TPrueba_deportiva_detalle().actualizar(equipo2);
                new TPrueba_deportiva_detalle().actualizar(equipo1);
                respuesta.setResult("OK");
            } else {
                respuesta.setResult("fail");
                respuesta.setMessage("La cantidad de equipos de la prueba no se corresponde con la modalidad partido");
            }

        }
        //Si la categoria es modalidad general
        if (categoria.getTipo_modalidad() == 2) {
            int total = lista.size();
            int valor = 1;
            int sumador = 0;
            sumador = 1;

            Iterator it = lista.iterator();
            while (it.hasNext()) {
                Prueba_deportiva_detalle detalle = (Prueba_deportiva_detalle) it.next();
                detalle.setPuntos(valor);
                new TPrueba_deportiva_detalle().actualizar(detalle);
                valor = valor + sumador;
            }
            respuesta.setResult("OK");
        }

        if (categoria.getTipo_modalidad() == 3) {
            respuesta.setResult("OK");
        }

        return respuesta;

    }

    public void notificacion_equipo(Prueba_deportiva_detalle prueba_detalle, String medalla) {

        
        TNotificacion tn = new TNotificacion();

        Prueba_deportiva_DetalleDet pd = new Prueba_deportiva_DetalleDet(prueba_detalle);
        pd.setearPrueba();
        String deporte = pd.getPrueba().getDeporte().getNombre() + " - " + pd.getPrueba().getCategoria().getNombre();
        List<Equipo_detalle> jugadores = new TEquipo_detalle().getById_equipo(pd.getId_equipo());
        for (Equipo_detalle equipo_jugador : jugadores) {
            Equipo_detalleDet jugador = new Equipo_detalleDet(equipo_jugador);
            Usuario_app usuario = new TUsuario_app().getByDoc(jugador.getJugador().getDni());
            if (usuario != null) {
                tn.notificar_medalla(usuario, medalla, pd.getPrueba().getId_categoria(), deporte);
            }
        }

    }

}
