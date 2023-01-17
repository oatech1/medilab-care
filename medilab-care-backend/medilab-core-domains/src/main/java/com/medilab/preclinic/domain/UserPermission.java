/**
 * 
 */
package com.medilab.preclinic.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * @author bprayaga
 *
 */
@Entity
@Table(name = "UserPermission")
@Data
public class UserPermission implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(nullable = false,unique = true, updatable = true)
	private String name;
	
	/**
	 * Audit info
	 */
	private String createdBy;
	private Date createdDate;
	
	private String modifiedBy;
	private Date modifiedDate;
	

}
