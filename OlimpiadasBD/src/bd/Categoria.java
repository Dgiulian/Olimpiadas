package bd;

import bd.base.CategoriaBase;
public class Categoria extends CategoriaBase {

	public Categoria() {
	}

	public Categoria(Categoria categoria) {
		super(categoria);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof bd.Categoria))
			return false;
		return ((bd.Categoria) obj).getId().equals(this.getId());
	}

	@Override
	public int hashCode() {
		return (int) this.id;
	}
}