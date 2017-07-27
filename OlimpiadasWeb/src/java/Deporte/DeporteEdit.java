/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Deporte;

import bd.Deporte;
import bd.Parametro;
import com.google.gson.Gson;
import java.io.File;
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
import org.apache.commons.io.FilenameUtils;
import transaccion.TDeporte;
import transaccion.TParametro;
import utils.BaseException;
import utils.JsonRespuesta;
import utils.OptionsCfg;
import utils.Parser;

/**
 *
 * @author Diego
 */
public class DeporteEdit extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet DeporteEdit</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet DeporteEdit at " + request.getContextPath() + "</h1>");
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
    private static final String DATA_DIRECTORY  = "data";
    private static final int   MAX_MEMORY_SIZE  = 1024 * 1024 * 2;
    private static final int   MAX_REQUEST_SIZE = 1024 * 1024 * 2;
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
        Integer page = (pagNro!=null)?Integer.parseInt(pagNro):0;
        Integer id = Parser.parseInt(request.getParameter("id"));
        Integer tipo = Parser.parseInt(request.getParameter("tipo"));
        String nombre = request.getParameter("nombre");
        Integer cantidad =Parser.parseInt( request.getParameter("cantidad_jugadores"));
        String reglamento = "";
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        FileItem archivo1 = null;
        FileItem archivo2 = null;
        TDeporte tdeporte = new TDeporte();
        Deporte deporte;
        boolean nuevo = false;
        JsonRespuesta jr = new JsonRespuesta();
        
        try{
            if (!isMultipart) 
                throw new BaseException("ERROR","El formulario no es multiparte. <br> No se pueden subir archivos");
        
            // Create a factory for disk-based file items
            DiskFileItemFactory factory = new DiskFileItemFactory();

            // Sets the size threshold beyond which files are written directly to disk.
            factory.setSizeThreshold(MAX_MEMORY_SIZE);

            // Sets the directory used to temporarily store files that are larger
            // than the configured size threshold. We use temporary directory for
            // java
            factory.setRepository(new File(System.getProperty("java.io.tmpdir")));            
            Parametro reglamento_path = new TParametro().getByCodigo(OptionsCfg.REGLAMENTO_PATH);

            // constructs the folder where uploaded file will be stored
            String uploadFolder = null;
            if (reglamento_path!=null) uploadFolder = reglamento_path.getValor();
            else uploadFolder = getServletContext().getRealPath("") + File.separator + DATA_DIRECTORY;

            System.out.println(uploadFolder);

            // Create a new file upload handler
            ServletFileUpload upload = new ServletFileUpload(factory);

            // Set overall request size constraint
            upload.setSizeMax(MAX_REQUEST_SIZE);            
                                
            try {
                List<FileItem> items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
                for (FileItem item : items) {
                    String fieldName = item.getFieldName();
                    if (item.isFormField()) {
                    // Process regular form field (input type="text|radio|checkbox|etc", select, etc).                        
                        String fieldValue = item.getString();
                         if(fieldName.equalsIgnoreCase("id"))                       id = Parser.parseInt(fieldValue);
                         if(fieldName.equalsIgnoreCase("tipo"))                   tipo = Parser.parseInt(fieldValue);
                         if(fieldName.equalsIgnoreCase("nombre"))               nombre = fieldValue;
                         if(fieldName.equalsIgnoreCase("cantidad_jugadores")) cantidad = Parser.parseInt(fieldValue);
                    }else {
                        // Process form file field (input type="file").                  
                        if(fieldName.equalsIgnoreCase("reglamento")) {
                            archivo1 = item;                    
                            reglamento = FilenameUtils.getName(item.getName());
                        }
                    }
                };           
            } catch (FileUploadException ex) {
                System.out.println(ex.getMessage());
                throw new BaseException("ERROR","Ocurrió un error al subir el archivo: " );
                
            }

        
            deporte = tdeporte.getById(id);
            if(deporte==null){
                deporte = new Deporte();
                nuevo = true;
            }
            deporte.setNombre(nombre);
            deporte.setCantidad_jugadores(cantidad);
            deporte.setTipo(tipo);
            
            if(archivo1!=null && !"".equals(reglamento)) {
                String filePath = uploadFolder + File.separator + reglamento;
                //String fileUrl =  activo_url.getValor() + File.separator + cetificado_fabricacion ;
                File uploadedFile = new File(filePath);
                try {
                    archivo1.write(uploadedFile);
                    deporte.setReglamento(reglamento);
                } catch (Exception ex) {
                    throw new BaseException("ERROR","Ocurri&oacute; un error al cargar el reglamento");
                }                            
            }

            boolean todoOk;
            if(nuevo) {
                id = tdeporte.alta(deporte);
                todoOk = id!=0; 
            } else todoOk = tdeporte.actualizar(deporte);
            
            
            if(!todoOk) throw new BaseException("ERROR","Ocurri&oacute; un error al guardar la categor&iacute;a");
            jr.setResult("OK");
            jr.setRecord(deporte);
        } catch(BaseException ex){
            jr.setResult(ex.getResult());
            jr.setMessage(ex.getMessage());
        }finally {
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
