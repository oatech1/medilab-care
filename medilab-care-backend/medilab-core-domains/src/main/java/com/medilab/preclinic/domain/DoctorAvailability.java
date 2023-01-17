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
import javax.persistence.ManyToOne;
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
@Table(name="DoctorAvailability")
@Data
public class DoctorAvailability implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The document id. */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	/** The title. */
	@ManyToOne
	@JoinColumn(name = "shceduleId")
	private DoctorSchedule doctorSchedule;
	
	@ManyToOne
	@JoinColumn(name = "daysAvailable")
	private AvailableDays daysAvailable;
	
	@ManyToOne
	@JoinColumn(name = "timingsAvailable")
	private AvailableTime timingsAvailable;
}
