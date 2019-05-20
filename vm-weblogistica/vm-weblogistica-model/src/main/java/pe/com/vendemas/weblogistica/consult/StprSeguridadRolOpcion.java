package pe.com.vendemas.weblogistica.consult;

public class StprSeguridadRolOpcion {

	private Integer idRolAsignadoInicial;
	private Integer idRolAsignado;
	private Integer idOpcion;
	private Integer idPadre;
	private String nombreOpcion;
	private String esMenu;
	private String vigente;
	private String vigenteInicial;
	private String orden;

	public StprSeguridadRolOpcion() {
	}

	public Integer getIdRolAsignadoInicial() {
		return idRolAsignadoInicial;
	}
	public void setIdRolAsignadoInicial(Integer idRolAsignadoInicial) {
		this.idRolAsignadoInicial = idRolAsignadoInicial;
	}
	public Integer getIdRolAsignado() {
		return idRolAsignado;
	}
	public void setIdRolAsignado(Integer idRolAsignado) {
		this.idRolAsignado = idRolAsignado;
	}
	public Integer getIdOpcion() {
		return idOpcion;
	}
	public void setIdOpcion(Integer idOpcion) {
		this.idOpcion = idOpcion;
	}
	public Integer getIdPadre() {
		return idPadre;
	}
	public void setIdPadre(Integer idPadre) {
		this.idPadre = idPadre;
	}
	public String getNombreOpcion() {
		return nombreOpcion;
	}
	public void setNombreOpcion(String nombreOpcion) {
		this.nombreOpcion = nombreOpcion;
	}
	public String getEsMenu() {
		return esMenu;
	}
	public void setEsMenu(String esMenu) {
		this.esMenu = esMenu;
	}
	public String getVigente() {
		return vigente;
	}
	public void setVigente(String vigente) {
		this.vigente = vigente;
	}
	public String getOrden() {
		return orden;
	}
	public void setOrden(String orden) {
		this.orden = orden;
	}
	public String getVigenteInicial() {
		return vigenteInicial;
	}
	public void setVigenteInicial(String vigenteInicial) {
		this.vigenteInicial = vigenteInicial;
	}
}