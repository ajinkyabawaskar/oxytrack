package tech.ajinkyabuilds.oxytrack.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * This class acts as a POJO class to store the model of an user
 * 
 * @author Ajinkya
 */

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Entity
@Table(name = "cylinders")
public class Cylinder {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String identifier;
	private String status;
	private String type;
	private Integer capacity;
	private LocalDateTime addedAt = LocalDateTime.now();
	private LocalDateTime lastUpdated = LocalDateTime.now();

	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Patient> patients = new HashSet<Patient>();

}
