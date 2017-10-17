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
public class Anchor implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7066012858691754093L;
	private Long id;
	private String nama;
	private String keterangan;
	private List<CycleSales> cyclesaleses;
	private List<CycleImplementator> cycleimplementators;
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
	public String getKeterangan() {
		return keterangan;
	}
	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan;
	}
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="anchor",targetEntity=CycleSales.class)
	public List<CycleSales> getCyclesaleses() {
		return cyclesaleses;
	}
	public void setCyclesaleses(List<CycleSales> cyclesaleses) {
		this.cyclesaleses = cyclesaleses;
	}
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="anchor",targetEntity=CycleImplementator.class)
	public List<CycleImplementator> getCycleimplementators() {
		return cycleimplementators;
	}
	public void setCycleimplementators(List<CycleImplementator> cycleimplementators) {
		this.cycleimplementators = cycleimplementators;
	}
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="anchor",targetEntity=CycleSolution.class)
	public List<CycleSolution> getCycleSolutions() {
		return cycleSolutions;
	}
	public void setCycleSolutions(List<CycleSolution> cycleSolutions) {
		this.cycleSolutions = cycleSolutions;
	}
	
	
}
