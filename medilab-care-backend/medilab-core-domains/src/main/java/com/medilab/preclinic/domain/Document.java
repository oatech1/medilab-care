/**
 * 
 */
package com.medilab.preclinic.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import lombok.Data;

// TODO: Auto-generated Javadoc
/**
 * The Class Document.
 *
 * @author Narsi
 */
@Entity
@Table(name="Document")
@Data
public class Document implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The document id. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int documentId;

	/** The title. */
	private String title;
	
	/** The description. */
	private String description;
	
	/** The document path. */
	private String documentPath;
	
	/** The doc key. */
	private String docKey;
	
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

	/** The logged in user id. */
	@Transient
	private String loggedInUserId;

	/** The status. */
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "statusID")
	private DocumentStatus status;	
}
