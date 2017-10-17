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
public class UserImplementator implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3045609503245959288L;
	private Long id;
	private String nama;
	private String username;
	private String password;
	private String alamat;
	private int role;
	private List<CycleImplementator> cycleImplementators;
	
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	@Column
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Column
	public String getAlamat() {
		return alamat;
	}
	public void setAlamat(String alamat) {
		this.alamat = alamat;
	}
	
	@Column
	public int getRole() {
		return role;
	}
	public void setRole(int role) {
		this.role = role;
	}
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="userImplementator",targetEntity=CycleImplementator.class)
	public List<CycleImplementator> getCycleImplementators() {
		return cycleImplementators;
	}
	public void setCycleImplementators(List<CycleImplementator> cycleImplementators) {
		this.cycleImplementators = cycleImplementators;
	}
	
	
}
