package com.medilab.preclinic.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

@Entity
@Table(name = "AvailableDays")
@Data
public class AvailableDays implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	private String day;
	
	
	/** The created by. */
	@Column(nullable = false)
	private String createdBy;

	/** The last modified by. */
	@Column(nullable = false)
	private String lastModifiedBy;

	/** The create time. */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createTime")
	private Date createTime;

	/** The last modified time. */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "lastModifiedTime")
	private Date lastModifiedTime;
	
	

}
