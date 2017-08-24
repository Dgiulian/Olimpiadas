/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import bd.Deporte;
import bd.Novedad;
import bd.Parametro;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import transaccion.TDeporte;
import transaccion.TNovedad;
import transaccion.TParametro;

/**
 *
 * @author Diego
 */
public class DownloadServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet DownloadServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DownloadServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    
    /**
     * Handles the HTTP
     * <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {            
            
            String tipo = request.getParameter("type");
            String filePath = "";
            Parametro parametro;
            TParametro tp = new TParametro();
            
            if (tipo==null|| tipo.equals(""))
                throw new BaseException("Error","Debe seleccionar el tipo de documento a descargar");
            
            if (tipo.equalsIgnoreCase("reglamento")) {
                parametro = tp.getByCodigo(OptionsCfg.REGLAMENTO_PATH);
                Integer id_deporte = Parser.parseInt(request.getParameter("id"));
                Deporte deporte = new TDeporte().getById(id_deporte);
                if(deporte == null) throw new BaseException("ERROR","No se encontr&oacute; el deporte");
                
                String fileName = deporte.getReglamento().trim();
                filePath = parametro.getValor() + File.separator + fileName; 
                        
                if (filePath==null || filePath.equals("")) throw new BaseException("ERROR", "El deporte no tiene ning&uacute;n archivo asociado");
                
                //filePath = "c:\\Users\\Diego\\Documents\\NetBeansProjects\\ActiSoft\\ActiSoftWeb\\data\\HaxLogs.log";
            }
            else  if (tipo.equalsIgnoreCase("novedad")) {
                parametro = tp.getByCodigo(OptionsCfg.NOVEDAD_PATH);
                Integer id_novedad = Parser.parseInt(request.getParameter("id"));                
                Novedad novedad = new TNovedad().getById(id_novedad);
                if(novedad == null) throw new BaseException("ERROR","No se encontr&oacute; la novedad");
                
                String fileName = novedad.getImagen().trim();
                filePath = parametro.getValor() + File.separator + fileName; 
                if (filePath==null || filePath.equals("")) throw new BaseException("ERROR", "La novedad no tiene ning&uacute;na  imagen asociada");
                
                //filePath = "c:\\Users\\Diego\\Documents\\NetBeansProjects\\ActiSoft\\ActiSoftWeb\\data\\HaxLogs.log";
            }
            else  if (tipo.equalsIgnoreCase("imagen")) {
                
                String fileName = "Imagen.jpg";
                filePath = "c:" + File.separator + fileName; 
                
                File file = new File(filePath);
                if(!file.exists()){
                    //LLamo al WS para crear la imagen
                }
                
                if (filePath==null || filePath.equals("")) throw new BaseException("ERROR", "La novedad no tiene ning&uacute;na  imagen asociada");
                
                //filePath = "c:\\Users\\Diego\\Documents\\NetBeansProjects\\ActiSoft\\ActiSoftWeb\\data\\HaxLogs.log";
            }
            else   throw new BaseException("Error","El tipo de documento a descargar no es v&aacute;lido");
            
        // reads input file from an absolute path        
            File downloadFile = new File(filePath);
            OutputStream outStream;
            try (FileInputStream inStream = new FileInputStream(downloadFile)) {
                String relativePath = getServletContext().getRealPath("");
                System.out.println("relativePath = " + relativePath);
                ServletContext context = getServletContext();
                String mimeType = context.getMimeType(filePath);
                if (mimeType == null) {
                    // set to binary type if MIME mapping not found
                    mimeType = "application/octet-stream";
                }   response.setContentType(mimeType);
            response.setContentLength((int) downloadFile.length());
                String headerKey = "Content-Disposition";
                String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
                response.setHeader(headerKey, headerValue);
                outStream = response.getOutputStream();
                byte[] buffer = new byte[4096];
                int bytesRead = -1;
                while ((bytesRead = inStream.read(buffer)) != -1) {
                    outStream.write(buffer, 0, bytesRead);
                }
            }
            outStream.close();// obtains response's output stream
        } catch (BaseException ex) {
            request.setAttribute("titulo", ex.getResult());
            request.setAttribute("mensaje", ex.getMessage());
            request.getRequestDispatcher("message.jsp").forward(request, response);
        }
            
    }

    /**
     * Handles the HTTP
     * <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
