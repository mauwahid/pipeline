package mandiri.pipeline.domain;

import java.io.Serializable;

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
public class LogSales implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9213975938356150510L;
	private Long id;
	private String tglperubahanAkhir;
	private CycleSales cycleSales;
	private String customer;
	private String relationshipManager;
	private String estAddFBI;
	private String estAddAvg;
	private String salesCycle;
	private String openDate;
	private String expCloseDate;
	private String wonDate;
	private String closeImplDate;
	private String realDate;
	private String comments;
	private String nextAction;
	private String anchor;
	private String groupCustomer;
	private String category;
	private String headSales;
	private String userSales;
	private String isAprroved;
	private String approvedBy;
	private String buc;
	private String product;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column
	public String getTglperubahanAkhir() {
		return tglperubahanAkhir;
	}
	public void setTglperubahanAkhir(String tglperubahanAkhir) {
		this.tglperubahanAkhir = tglperubahanAkhir;
	}
	
	@ManyToOne
	@JoinColumn
	public CycleSales getCycleSales() {
		return cycleSales;
	}
	public void setCycleSales(CycleSales cycleSales) {
		this.cycleSales = cycleSales;
	}
	
	@Column
	public String getCustomer() {
		return customer;
	}
	public void setCustomer(String customer) {
		this.customer = customer;
	}
	
	@Column
	public String getRelationshipManager() {
		return relationshipManager;
	}
	public void setRelationshipManager(String relationshipManager) {
		this.relationshipManager = relationshipManager;
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
	public String getOpenDate() {
		return openDate;
	}
	public void setOpenDate(String openDate) {
		this.openDate = openDate;
	}
	
	@Column
	public String getExpCloseDate() {
		return expCloseDate;
	}
	public void setExpCloseDate(String expCloseDate) {
		this.expCloseDate = expCloseDate;
	}
	
	@Column
	public String getWonDate() {
		return wonDate;
	}
	public void setWonDate(String wonDate) {
		this.wonDate = wonDate;
	}
	
	@Column
	public String getCloseImplDate() {
		return closeImplDate;
	}
	public void setCloseImplDate(String closeImplDate) {
		this.closeImplDate = closeImplDate;
	}
	
	@Column
	public String getRealDate() {
		return realDate;
	}
	public void setRealDate(String realDate) {
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
	
	@Column
	public String getAnchor() {
		return anchor;
	}
	public void setAnchor(String anchor) {
		this.anchor = anchor;
	}
	
	@Column
	public String getGroupCustomer() {
		return groupCustomer;
	}
	public void setGroupCustomer(String groupCustomer) {
		this.groupCustomer = groupCustomer;
	}
	
	@Column
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	
	@Column
	public String getHeadSales() {
		return headSales;
	}
	public void setHeadSales(String headSales) {
		this.headSales = headSales;
	}
	
	@Column
	public String getUserSales() {
		return userSales;
	}
	
	public void setUserSales(String userSales) {
		this.userSales = userSales;
	}
	
	@Column
	public String getIsAprroved() {
		return isAprroved;
	}
	public void setIsAprroved(String isAprroved) {
		this.isAprroved = isAprroved;
	}
	
	@Column
	public String getApprovedBy() {
		return approvedBy;
	}
	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}
	
	@Column
	public String getBuc() {
		return buc;
	}
	public void setBuc(String buc) {
		this.buc = buc;
	}
	
	@Column
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	
	
	
}
