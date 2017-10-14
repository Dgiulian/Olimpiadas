package transaccion;

import bd.Categoria;
import bd.Delegacion;
import bd.Equipo;
import java.util.List;

public class TEquipo extends TransaccionBase<Equipo> {

    @Override
    public List<Equipo> getList() {
        return super.getList("select * from equipo ");
    }

    public Boolean actualizar(Equipo equipo) {
        return super.actualizar(equipo, "id");
    }

    public Equipo getById(Integer id) {
        String query = String.format(
                "select * from equipo where equipo.id = %d ", id);
        return super.getById(query);
    }

    public List<Equipo> getById_grupo(Integer id_grupo) {
        String query = String.format("select equipo.* from equipo,grupo_detalle "
                + " where grupo_detalle.id_equipo = equipo.id "
                + "   and grupo_detalle.id_grupo = %d;", id_grupo);
        return this.getList(query);
    }

    public List<Equipo> getById_categoria(Integer id_grupo) {
        String query = String.format("select equipo.* from equipo "
                + " where equipo.id_categoria = %d;", id_grupo);
        return this.getList(query);
    }

    public List<Equipo> getById_delegacion(Integer id_categoria) {
        String query = String.format("select DISTINCT equipo.* "
                + "from equipo inner join equipo_detalle on equipo.id=equipo_detalle.id_equipo "
                + "where equipo.id_delegacion=%d order by equipo.id_categoria ", id_categoria);
        return this.getList(query);
    }

    public List<Equipo> getById_prueba(Integer id_prueba) {
        String query = String.format("select equipo.* from equipo,prueba_deportiva_detalle "
                + " where prueba_deportiva_detalle.id_equipo = equipo.id "
                + "   and prueba_deportiva_detalle.id_prueba = %d;", id_prueba);
        System.out.println(query);
        return this.getList(query);
    }

    public void crearPorCategoria(Categoria categoria) {
        String categoria_nombre = categoria.getNombre_corto();
        List<Delegacion> lstDelegacion = new TDelegacion().getList();
        for (Delegacion delegacion : lstDelegacion) {
            String nombre_equipo = delegacion.getNombre_corto() + " " + categoria_nombre;
            Equipo equipo = new Equipo();
            equipo.setId_delegacion(delegacion.getId());
            equipo.setId_categoria(categoria.getId());
            equipo.setNombre(nombre_equipo);
            this.alta(equipo);
        }

    }

    public Equipo getByDelegacionCategoria(Integer id_delegacion, Integer id_categoria) {
        String query = String.format("select * from equipo where id_delegacion = %d and id_categoria = %d", id_delegacion, id_categoria);
        return this.getById(query);
    }
}
