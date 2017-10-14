/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Jugador;

import bd.Jugador;
import com.google.gson.Gson;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import transaccion.TJugador;
import utils.BaseException;
import utils.JsonRespuesta;
import utils.Parser;
import utils.TFecha;

/**
 *
 * @author Diego
 */
public class JugadorEdit extends HttpServlet {

    private int MAX_MEMORY_SIZE;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet JugadorEdit</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet JugadorEdit at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");

        PrintWriter out = response.getWriter();
        String pagNro = request.getParameter("pagNro");
        Integer page = (pagNro != null) ? Integer.parseInt(pagNro) : 0;
        Integer id = Parser.parseInt(request.getParameter("id"));
        Integer id_delegacion = Parser.parseInt(request.getParameter("id_delegacion"));
        String nombre_apellido = request.getParameter("nombre_apellido");
        String matricula = request.getParameter("matricula");
        String dni = request.getParameter("dni");
        System.out.println("fecha: " + request.getParameter("fecha_nacimiento"));
        String fecha_nacimiento = TFecha.formatearFechaVistaBd(request.getParameter("fecha_nacimiento"));
        TJugador tjugadores = new TJugador();
        Jugador jugador;
        boolean nuevo = false;
        JsonRespuesta jr = new JsonRespuesta();
        try {

            boolean isMultipart = ServletFileUpload.isMultipartContent(request);

            try {
                List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
                for (FileItem item : items) {
                    String fieldName = item.getFieldName();
                    if (item.isFormField()) {
                        // Process regular form field (input type="text|radio|checkbox|etc", select, etc).                        
                        String fieldValue = item.getString();
                        if (fieldName.equalsIgnoreCase("id")) {
                            id = Parser.parseInt(fieldValue);
                        }
                        if (fieldName.equalsIgnoreCase("nombre_apellido")) {
                            nombre_apellido = fieldValue;
                        }
                        if (fieldName.equalsIgnoreCase("dni")) {
                            dni = fieldValue;
                        }
                        if (fieldName.equalsIgnoreCase("fecha_nacimiento")) {
                            fecha_nacimiento = TFecha.formatearFechaVistaBd(fieldValue);
                        }
                        if (fieldName.equalsIgnoreCase("matricula")) {
                            matricula = fieldValue;
                        }
                        if (fieldName.equalsIgnoreCase("id_delegacion")) {
                            id_delegacion = Parser.parseInt(fieldValue);
                        }

                    }
                };
            } catch (FileUploadException ex) {
                System.out.println(ex.getMessage());
                throw new BaseException("ERROR", "Ocurrió un error al subir el archivo: ");

            }
            jugador = tjugadores.getById(id);
            if (jugador == null) {
                jugador = new Jugador();
                nuevo = true;
            }
            jugador.setNombre_apellido(nombre_apellido);
            jugador.setMatricula(matricula);
            jugador.setFecha_nacimiento(fecha_nacimiento);
            jugador.setDni(dni);
            jugador.setId_delegacion(id_delegacion);

            boolean todoOk;
            if (nuevo) {
                id = tjugadores.alta(jugador);
                todoOk = id != 0;
            } else {
                todoOk = tjugadores.actualizar(jugador);
            }

            if (!todoOk) {
                throw new BaseException("ERROR", "Ocurri&oacute; un error al guardar la categor&iacute;a");
            }
            jr.setResult("OK");
            jr.setRecord(jugador);

        } catch (BaseException ex) {
            jr.setResult(ex.getResult());
            jr.setMessage(ex.getMessage());
        } finally {
            String jsonResult = new Gson().toJson(jr);
            out.print(jsonResult);
            out.close();
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
