package mandiri.pipeline.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;

@Entity
@Table
public class CycleSales implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8442389469272940689L;
	private Long id;
	private Customer customer;
	private String relationshipManager;
	private String estAddFBI;
	private String estAddAvg;
	private int salesCycle;
	private Date openDate;
	private Date expCloseDate;
	private Date wonDate;
	private Date closeImplDate;
	private Date realDate;
	private String comments;
	private String nextAction;
	private Anchor anchor;
	private GroupCustomer groupCustomer;
//	private Category category;
	private HeadSales headSales;
	private UserSales userSales;
	private boolean isAprroved;
	private HeadSolution approvedBy;
	private BUC buc;
	private Product product;
	private String category;
	private List<LogSales> logSaleses;	
	
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@ManyToOne(fetch=FetchType.EAGER,targetEntity=Customer.class)
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
	public int getSalesCycle() {
		return salesCycle;
	}
	public void setSalesCycle(int salesCycle) {
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
	public HeadSales getHeadSales() {
		return headSales;
	}
	public void setHeadSales(HeadSales headSales) {
		this.headSales = headSales;
	}
	
	@ManyToOne
	@JoinColumn
	public UserSales getUserSales() {
		return userSales;
	}
	public void setUserSales(UserSales userSales) {
		this.userSales = userSales;
	}
	public HeadSolution getApprovedBy() {
		return approvedBy;
	}
	public void setApprovedBy(HeadSolution approvedBy) {
		this.approvedBy = approvedBy;
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
	
	@Column
	public boolean isAprroved() {
		return isAprroved;
	}
	public void setAprroved(boolean isAprroved) {
		this.isAprroved = isAprroved;
	}
	
	@OneToMany(mappedBy="cycleSales",targetEntity=LogSales.class,fetch=FetchType.EAGER,cascade=CascadeType.ALL)
	public List<LogSales> getLogSaleses() {
		return logSaleses;
	}
	public void setLogSaleses(List<LogSales> logSaleses) {
		this.logSaleses = logSaleses;
	}
	
	@Column
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}

}
