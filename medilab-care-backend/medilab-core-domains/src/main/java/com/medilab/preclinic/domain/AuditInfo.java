package com.medilab.preclinic.domain;

import java.io.Serializable;
import java.util.Date;


import lombok.Data;

@Data
public class AuditInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Audit info
	 */
	private String createdBy;
	private Date createdDate;
	
	private String modifiedBy;
	private Date modifiedDate;
}
