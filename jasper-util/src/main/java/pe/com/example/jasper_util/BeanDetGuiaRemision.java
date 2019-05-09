package pe.com.example.jasper_util;

public class BeanDetGuiaRemision {

	private String item;
	private String codigo;
	private String uniMedida;
	private String cantidad;
	private String descripcion;

	public BeanDetGuiaRemision(String item, String codigo, String uniMedida, String cantidad, String descripcion) {
		super();
		this.item = item;
		this.codigo = codigo;
		this.uniMedida = uniMedida;
		this.cantidad = cantidad;
		this.descripcion = descripcion;
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getUniMedida() {
		return uniMedida;
	}

	public void setUniMedida(String uniMedida) {
		this.uniMedida = uniMedida;
	}

	public String getCantidad() {
		return cantidad;
	}

	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
