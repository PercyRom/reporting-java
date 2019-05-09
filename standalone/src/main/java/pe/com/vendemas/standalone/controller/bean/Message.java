
package pe.com.vendemas.standalone.controller.bean;

import pe.com.vendemas.standalone.util.BeanBase;

public class Message extends BeanBase {

	private static final long serialVersionUID = 1L;
	
	private Integer messageid;
	private Integer status;

	public Integer getMessageid() {
		return messageid;
	}

	public void setMessageid(Integer messageid) {
		this.messageid = messageid;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
