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
    public List<Categoria> getById_deporte(Integer id_deporte){
        String query = String.format("select * from categoria where categoria.id_deporte = %d ", id_deporte);
        return super.getList(query);
    }
}