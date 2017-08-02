package transaccion;

import bd.Categoria;
import java.util.List;

public class TCategoria extends TransaccionBase<Categoria> {

    @Override
    public List<Categoria> getList() {
        return super.getList("select * from categoria ");
    }

    public Boolean actualizar(Categoria categoria) {
        return super.actualizar(categoria, "id");
    }

    public Categoria getById(Integer id) {
        String query = String.format(
                "select * from categoria where categoria.id = %d ", id);
        return super.getById(query);
    }

    public List<Categoria> getById_deporte(Integer id_deporte) {
        String query = String.format("select * from categoria where categoria.id_deporte = %d ", id_deporte);
        return super.getList(query);
    }
    public List<Categoria> getByJugador(Integer id_jugador){
        String query = String.format("select categoria.`*`\n" +
                                     "  from categoria,equipo,equipo_detalle\n" +
                                     " where categoria.id = equipo.id_categoria\n" +
                                     "   and equipo.id = equipo_detalle.id_equipo\n" +
                                     "   and equipo_detalle.id_jugador = %d ",id_jugador);
        
        return this.getList(query);
    }
    public static void main(String[] args) {
//        List<Categoria> lstCategoria = new TCategoria().getList();
//        TEquipo tEquipo = new TEquipo();
//        for (Categoria categoria:lstCategoria){
//            if(!"".equals(categoria.getNombre())) {
//                tEquipo.crearPorCategoria(categoria);
//            }
//        }
        List<Categoria> byJugador = new TCategoria().getByJugador(9);
        System.out.println(byJugador.size());
    }
}
