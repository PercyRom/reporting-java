package pe.com.vendemas.standalone.controller.bean;

import java.io.Serializable;

public class Filtro implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private String fullSerialNumber;
	private String activationCode;
	
	public String getFullSerialNumber() {
		return fullSerialNumber;
	}
	public void setFullSerialNumber(String fullSerialNumber) {
		this.fullSerialNumber = fullSerialNumber;
	}
	public String getActivationCode() {
		return activationCode;
	}
	public void setActivationCode(String activationCode) {
		this.activationCode = activationCode;
	}
	
	
	
}
