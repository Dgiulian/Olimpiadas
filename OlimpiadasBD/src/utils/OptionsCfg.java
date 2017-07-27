/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author Diego
 */
public class OptionsCfg {
    
    public static final String  REGLAMENTO_PATH = "reglamento_path";
    public static final String  NOVEDAD_PATH    = "novedad_path";
    public static final String  LOGO_IMAGE           = "logo_image";
    
    public static ArrayList<Option> getTipoRemitos(){
        ArrayList<Option> lista = new ArrayList();
        
        return lista;
    }
   
   public static HashMap<Integer,Option> getMap(List<Option> lista){
        HashMap<Integer,Option> mapa = new HashMap<>();
        if (lista!=null){
            for(Option o:lista){
                mapa.put(o.getId(), o);
            }
        }
        return mapa;
   } 
   
    public static class Option{
        Integer id ;
        String codigo;
        String descripcion;    
        public Option(Integer id,String codigo,String descripcion) {
            this.id = id;
            this.codigo = codigo;
            this.descripcion = descripcion;
        }
        public Integer getId(){
            return this.id;
        }
        public String getCodigo(){
            return this.codigo;
        }
        public String getDescripcion(){
            return this.descripcion;
        }
                
    }
}
