package pe.com.vendemas.standalone.util;

public class Constantes {

	public static String C2C_WS_RUTA = "";
	public static String C2C_WS_USER = "";

	public static final void inicializarC2C(String ruta, String users) {
		Constantes.C2C_WS_RUTA = ruta;
		Constantes.C2C_WS_USER = users;
	}
	
	public enum MESSAGES {

		ERROR_1000(1000), 
		ERROR_1001(1001),
		ERROR_1010(1010), 
		ERROR_1100(1100),
		ERROR_1200(1200), 
		ERROR_1400(1400),
		ERROR_1500(1500), 
		ERROR_1600(1600),
		ERROR_2000(2000);

		private int code;

		public int getCode() {
			return this.code;
		}

		private MESSAGES(int code) {
			this.code = code;
		}

		public String getMessage() {
			String msg = "";
			switch (this.code) {
			case 1000:
				msg = "Error general";
				break;
			case 1001:
				msg = "No se pasó todos los parámetros requeridos";
				break;
			case 1010:
				msg = "Teléfono en formato incorrecto 51XXXXXXXXX";
				break;
			case 1100:
				msg = "IP de origen bloqueada";
				break;
			case 1200:
				msg = "Usuario o password inválido";
				break;
			case 1400:
				msg = "No tiene créditos disponibles";
				break;
			case 1500:
				msg = "Error inesperado";
				break;
			case 1600:
				msg = "Error por caracteres no permitidos en el mensaje";
				break;
			case 2000:
				msg = "Enviado";
				break;
			default:
				break;
			}
			return msg;
		}
	}
}
