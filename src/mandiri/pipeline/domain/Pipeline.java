package mandiri.pipeline.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class Pipeline implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -576119517272000852L;
	private Long id;
//	private Category category;
	private String catString;
	private String grupCustomer;
	private String department;
	private String customer;
	private String relationshipManager;
	private String product;
	private String estAddFBI;
	private String estAddAvg;
	private String salesCycle;
	private Date openDate;
	private Date expCloseDate;
	private Date wonDate;
	private Date closeImplDate;
	private Date realDate;
	private String comments;
	private String nextAction;
//	private Anchor anchor;
	private String anchorString;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
/*	@ManyToOne
	@JoinColumn
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}*/
	
	@Column
	public String getGrupCustomer() {
		return grupCustomer;
	}
	public void setGrupCustomer(String grupCustomer) {
		this.grupCustomer = grupCustomer;
	}
	
	@Column
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	
	@Column
	public String getRelationshipManager() {
		return relationshipManager;
	}
	public void setRelationshipManager(String relationshipManager) {
		this.relationshipManager = relationshipManager;
	}
	
	@Column
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	
	@Column
	public String getEstAddFBI() {
		return estAddFBI;
	}
	public void setEstAddFBI(String estAddFBI) {
		this.estAddFBI = estAddFBI;
	}
	
	@Column
	public String getEstAddAvg() {
		return estAddAvg;
	}
	public void setEstAddAvg(String estAddAvg) {
		this.estAddAvg = estAddAvg;
	}
	
	@Column
	public String getSalesCycle() {
		return salesCycle;
	}
	public void setSalesCycle(String salesCycle) {
		this.salesCycle = salesCycle;
	}
	
	@Column
	public Date getOpenDate() {
		return openDate;
	}
	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}
	
	@Column
	public Date getExpCloseDate() {
		return expCloseDate;
	}
	public void setExpCloseDate(Date expCloseDate) {
		this.expCloseDate = expCloseDate;
	}
	
	@Column
	public Date getWonDate() {
		return wonDate;
	}
	public void setWonDate(Date wonDate) {
		this.wonDate = wonDate;
	}
	
	@Column
	public Date getCloseImplDate() {
		return closeImplDate;
	}
	public void setCloseImplDate(Date closeImplDate) {
		this.closeImplDate = closeImplDate;
	}
	
	
	@Column
	public Date getRealDate() {
		return realDate;
	}
	public void setRealDate(Date realDate) {
		this.realDate = realDate;
	}
	
	@Column
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	
	@Column
	public String getNextAction() {
		return nextAction;
	}
	public void setNextAction(String nextAction) {
		this.nextAction = nextAction;
	}
	
/*	@Column
	public Anchor getAnchor() {
		return anchor;
	}
	public void setAnchor(Anchor anchor) {
		this.anchor = anchor;
	}*/
	
	@Column
	public String getCatString() {
		return catString;
	}
	public void setCatString(String catString) {
		this.catString = catString;
	}
	
	@Column
	public String getAnchorString() {
		return anchorString;
	}
	public void setAnchorString(String anchorString) {
		this.anchorString = anchorString;
	}
	
	@Column
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	
	
}
