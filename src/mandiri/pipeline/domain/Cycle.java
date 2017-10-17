package mandiri.pipeline.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Cycle implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -2652254514237319500L;
	
	private Long id;
	private int cycleOrder;
	private String cycleName;
	private String description;
	private CycleImplementator cycleImpl;
	private CycleSales cycleSales;
	private CycleSolution cycleSolution;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column
	public String getCycleName() {
		return cycleName;
	}
	public void setCycleName(String cycleName) {
		this.cycleName = cycleName;
	}
	
	@Column
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
