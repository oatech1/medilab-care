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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.Data;

// TODO: Auto-generated Javadoc
/**
 * The Class Notification.
 *
 * @author Narsi
 */
@Entity
@Table(name = "Notification")
@Data
public class Notification implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The notification id. */
	@Id
	@GeneratedValue
	private int notificationId;

	/** The title. */
	private String title;

	/** The body. */
	private String body;

	/** The notification date. */
	@Temporal(TemporalType.TIMESTAMP)
	private Date notificationDate;

	/** The expiry date. */
	@Temporal(TemporalType.TIMESTAMP)
	private Date expiryDate;

	/** The created by. */
	private String createdBy;

	/** The is specific to user. */
	private boolean isSpecificToUser;

	
	/** The last modified by. */
	private String lastModifiedBy;

	/** The is active. */
	private boolean isActive = true;

	/** The user id. */
	private String userId;

	/** The create time. */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createTime")
	private Date createTime;

	/** The last modified time. */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "lastModifiedTime")
	private Date lastModifiedTime;

	/** The notification type. */
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "typeId")
	private NotificationType notificationType;
}
