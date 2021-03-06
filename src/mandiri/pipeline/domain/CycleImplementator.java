package mandiri.pipeline.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
public class CycleImplementator implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4691393622774395175L;
	private Long id;
	private String catString;
	private String department;
	private Customer customer;
	private String relationshipManager;
	private String estAddFBI;
	private String estAddAvg;
	private int implCycle;
	private Date openDate;
	private Date expCloseDate;
	private Date wonDate;
	private Date closeImplDate;
	private Date realDate;
	private String comments;
	private String nextAction;
	private Anchor anchor;
	private GroupCustomer groupCustomer;
	private Category category;
	private HeadImplementator headImplementator;
	private UserImplementator userImplementator;
	private BUC buc;
	private Product product;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column
	public String getCatString() {
		return catString;
	}
	public void setCatString(String catString) {
		this.catString = catString;
	}
	
	
	@Column
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	
	@ManyToOne(fetch=FetchType.LAZY,targetEntity=Customer.class)
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
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
	
	@ManyToOne
	@JoinColumn
	public Anchor getAnchor() {
		return anchor;
	}
	public void setAnchor(Anchor anchor) {
		this.anchor = anchor;
	}
	
	@ManyToOne
	@JoinColumn
	public GroupCustomer getGroupCustomer() {
		return groupCustomer;
	}
	public void setGroupCustomer(GroupCustomer groupCustomer) {
		this.groupCustomer = groupCustomer;
	}
	
	@ManyToOne
	@JoinColumn
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	
	@Column
	public int getImplCycle() {
		return implCycle;
	}
	public void setImplCycle(int implCycle) {
		this.implCycle = implCycle;
	}
	
	@ManyToOne
	@JoinColumn
	public HeadImplementator getHeadImplementator() {
		return headImplementator;
	}
	public void setHeadImplementator(HeadImplementator headImplementator) {
		this.headImplementator = headImplementator;
	}
	
	@ManyToOne
	@JoinColumn
	public UserImplementator getUserImplementator() {
		return userImplementator;
	}
	public void setUserImplementator(UserImplementator userImplementator) {
		this.userImplementator = userImplementator;
	}
	
	@ManyToOne
	@JoinColumn
	public BUC getBuc() {
		return buc;
	}
	public void setBuc(BUC buc) {
		this.buc = buc;
	}
	
	@ManyToOne
	@JoinColumn
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
		
	
}
