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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

// TODO: Auto-generated Javadoc
/**
 * The Class Document.
 *
 * @author Narsi
 */
@Entity
@Table(name="Department")
@Data
public class Department implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The document id. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int deptId;

	/** The title. */
	private String name;
	
	/** The description. */
	private String description;
	
	private String status;
	
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
