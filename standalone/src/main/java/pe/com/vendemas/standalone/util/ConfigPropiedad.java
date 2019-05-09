package pe.com.vendemas.standalone.util;

public class ConfigPropiedad {

	public enum URI {
		SERVICE_PAX_ACTIVATE("config.url.pax.activate"),
		SERVICE_PAX_DEACTIVATE("config.url.pax.deactivate");

		private final String uri;

		public String getUri() {
			return uri;
		}

		URI(String valor) {
			this.uri = valor;
		}
	}
	
	public enum URL_BASE {
		SERVICES_WEB("config.instancia.services.web");

		private final String url;

		URL_BASE(String url) {
			this.url = url;

		}

		public String getUrl() {
			return url;
		}
	}

}
