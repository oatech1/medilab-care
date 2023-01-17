/**
 * 
 */
package com.medilab.preclinic.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@Table(name="DoctorSchedule")
@Data
public class DoctorSchedule implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The document id. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	/** The title. */
	@OneToOne
	@JoinColumn(name = "doctorId")
	private MedilabUser doctor;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL,mappedBy = "doctorSchedule")
	//@JoinColumn(name = "daysAvailable")
	private Set<DoctorAvailability> doctorsAvailaibility;
	
	/** The startTime. */
	private String startTime;
	
	private String endTime;
	
	private String message;
	
	private String scheduledStatus;
	
	
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
