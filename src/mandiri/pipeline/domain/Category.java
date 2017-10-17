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
public class Category implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8461245214021027885L;
	private Long id;
	private String nama;
	private String dekripsi;
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
	public String getNama() {
		return nama;
	}
	public void setNama(String nama) {
		this.nama = nama;
	}
	
	@Column
	public String getDekripsi() {
		return dekripsi;
	}
	public void setDekripsi(String dekripsi) {
		this.dekripsi = dekripsi;
	}
	
	@OneToMany(fetch=FetchType.LAZY,targetEntity=CycleImplementator.class,mappedBy="category")
	public List<CycleImplementator> getCycleImplementators() {
		return cycleImplementators;
	}
	public void setCycleImplementators(List<CycleImplementator> cycleImplementators) {
		this.cycleImplementators = cycleImplementators;
	}
	
	@OneToMany(fetch=FetchType.LAZY,targetEntity=CycleSales.class,mappedBy="category")
	public List<CycleSales> getCycleSaleses() {
		return cycleSaleses;
	}
	public void setCycleSaleses(List<CycleSales> cycleSaleses) {
		this.cycleSaleses = cycleSaleses;
	}
	
	@OneToMany(fetch=FetchType.LAZY,targetEntity=CycleSolution.class,mappedBy="category")
	public List<CycleSolution> getCycleSolutions() {
		return cycleSolutions;
	}
	public void setCycleSolutions(List<CycleSolution> cycleSolutions) {
		this.cycleSolutions = cycleSolutions;
	}
	
	
}
