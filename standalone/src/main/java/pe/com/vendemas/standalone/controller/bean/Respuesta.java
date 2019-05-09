package pe.com.vendemas.standalone.controller.bean;

import java.io.Serializable;

public class Respuesta implements Serializable {

	private static final long serialVersionUID = 1L;

	private String state;
	private String seqSms;
	private String idSms;
	private String desState;

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getSeqSms() {
		return seqSms;
	}

	public void setSeqSms(String seqSms) {
		this.seqSms = seqSms;
	}

	public String getIdSms() {
		return idSms;
	}

	public void setIdSms(String idSms) {
		this.idSms = idSms;
	}

	public String getDesState() {
		return desState;
	}

	public void setDesState(String desState) {
		this.desState = desState;
	}
}
