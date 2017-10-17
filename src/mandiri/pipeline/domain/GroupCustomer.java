package mandiri.pipeline.domain;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class GroupCustomer implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -4343031635975098122L;
	private Long id;
	private String groupName;
	private String alamat;
	private List<CycleImplementator> cycleImplementators;
	private List<CycleSales> cycleSaleses;
	private List<CycleSolution> cycleSolutions;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
	@Column
	public String getAlamat() {
		return alamat;
	}
	public void setAlamat(String alamat) {
		this.alamat = alamat;
	}
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="groupCustomer",targetEntity=CycleImplementator.class)
	public List<CycleImplementator> getCycleImplementators() {
		return cycleImplementators;
	}
	public void setCycleImplementators(List<CycleImplementator> cycleImplementators) {
		this.cycleImplementators = cycleImplementators;
	}

	@OneToMany(fetch=FetchType.LAZY,mappedBy="groupCustomer",targetEntity=CycleSales.class)
	public List<CycleSales> getCycleSaleses() {
		return cycleSaleses;
	}
	public void setCycleSaleses(List<CycleSales> cycleSaleses) {
		this.cycleSaleses = cycleSaleses;
	}
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="groupCustomer",targetEntity=CycleSolution.class)
	public List<CycleSolution> getCycleSolutions() {
		return cycleSolutions;
	}
	public void setCycleSolutions(List<CycleSolution> cycleSolutions) {
		this.cycleSolutions = cycleSolutions;
	}
	
	
}
