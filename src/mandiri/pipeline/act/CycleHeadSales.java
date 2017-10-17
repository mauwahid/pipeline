package mandiri.pipeline.act;

import java.util.List;

import mandiri.pipeline.act.CycleSalesAct.AnchorRenderer;
import mandiri.pipeline.act.CycleSalesAct.CustomerRenderer;
import mandiri.pipeline.act.CycleSalesAct.CycleSalesRenderer;
import mandiri.pipeline.act.CycleSalesAct.GroupCustomerRenderer;
import mandiri.pipeline.dao.AnchorDao;
import mandiri.pipeline.dao.BUCDao;
import mandiri.pipeline.dao.CategoryDao;
import mandiri.pipeline.dao.CustomerDao;
import mandiri.pipeline.dao.CycleSalesDao;
import mandiri.pipeline.dao.CycleSolutionDao;
import mandiri.pipeline.dao.GroupCustomerDao;
import mandiri.pipeline.dao.ProductDao;
import mandiri.pipeline.domain.Anchor;
import mandiri.pipeline.domain.BUC;
import mandiri.pipeline.domain.Category;
import mandiri.pipeline.domain.Customer;
import mandiri.pipeline.domain.CycleSales;
import mandiri.pipeline.domain.CycleSolution;
import mandiri.pipeline.domain.GroupCustomer;
import mandiri.pipeline.domain.Product;
import mandiri.pipeline.util.Common;
import mandiri.pipeline.util.HibernateUtil;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Box;
import org.zkoss.zul.Button;
import org.zkoss.zul.Column;
import org.zkoss.zul.Columns;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.ComboitemRenderer;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Row;
import org.zkoss.zul.Rows;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

public class CycleHeadSales extends GenericForwardComposer<Component> implements Act<CycleSales> {
	CycleSalesDao cycleSalesDao;
	CycleSales cycleSales;
	private Combobox cbDepartment;
	//private Combobox cbCategory;
	private Combobox cbGroupCustomer;
	private Combobox cbCustomer;
	private Combobox cbProduct;
	private Combobox cbBUC;
	private Textbox tbDepartment;
	private Textbox tbCategory;
	private Textbox tbRelationshipManager;
	private Textbox tbProduct;
	private Textbox tbEstAddFBI;
	private Textbox tbEstAddAvg;
	private Combobox cbSalesCycle;
	private Datebox dBoxOpenDate;
	private Datebox dBoxExpCloseDate;
	private Datebox dBoxWonDate;
	private Datebox dBoxCloseImplDate;
	private Datebox dBoxDealDate;
	private Textbox tbComments;
	private Textbox tbNextAction;
	private Combobox cbAnchor;
	private Button btnSave;
	private Button btnCancel;
	private Anchor anchor;
	
	private Window window;
	private Grid grid;
	private Columns columns;
	private Rows rows;
	private Row row;
	private Column column;
	private Comboitem cbItem;
	
	Listbox list_cycleSales;
	
	private CategoryDao categoryDao;
	private GroupCustomerDao groupCustomerDao;
	private CustomerDao customerDao;
	private AnchorDao anchorDao;
	private BUCDao bucDao;
	private ProductDao productDao;
	
	private ListModelList listModel;
	
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		loadData();
	}

	@Override
	public void loadData() {
		// TODO Auto-generated method stub
			
			cycleSalesDao = HibernateUtil.getCycleSalesDao();
			List<CycleSales> list = cycleSalesDao.getAll();
			
			listModel = new ListModelList(list);
		//	listModel = new SimpleListModel<DistributionFinance>(list);
			//UI Renderer
			listModel.setMultiple(true);
			list_cycleSales.setModel(listModel);
			list_cycleSales.setItemRenderer(new CycleSalesRenderer());
			list_cycleSales.renderAll();
			//setCheckBox();
			
			
	}
	
	class CycleSalesRenderer implements ListitemRenderer<CycleSales>{

		
		@Override
		public void render(Listitem item, CycleSales entity, int index)
				throws Exception {
			// TODO Auto-generated method stub
			final CycleSales dataTemp = entity;
			index = index + 1;
				
		
			new Listcell(dataTemp.getId()+"").setParent(item);
			new Listcell(dataTemp!=null?dataTemp.getCategory():"").setParent(item);
			new Listcell(dataTemp.getGroupCustomer()!=null?dataTemp.getGroupCustomer().getGroupName():"").setParent(item);
		/*	lc = new Listcell(dataTemp.getDistributor()!=null?dataTemp.getDistributor().getNama():"");
			lc.setStyle("font-size:10px");
			lc.setParent(item);*/
			new Listcell(dataTemp.getCustomer()!=null?dataTemp.getCustomer().getName():"").setParent(item);
			new Listcell(dataTemp.getProduct()!=null?dataTemp.getProduct().getNama():"").setParent(item);
			new Listcell(dataTemp.getBuc()!=null?dataTemp.getBuc().getNama():"").setParent(item);
			new Listcell(dataTemp.getRelationshipManager()).setParent(item);
			if(dataTemp.getSalesCycle()==Common.SALES_NO_CYCLE_1)
				new Listcell(Common.SALES_CYCLE_1).setParent(item);
			else if(dataTemp.getSalesCycle()==Common.SALES_NO_CYCLE_2)
				new Listcell(Common.SALES_CYCLE_2).setParent(item);
			else if(dataTemp.getSalesCycle()==Common.SALES_NO_CYCLE_3)
				new Listcell(Common.SALES_CYCLE_3).setParent(item);
			else if(dataTemp.getSalesCycle()==Common.SALES_NO_CYCLE_4)
				new Listcell(Common.SALES_CYCLE_4).setParent(item);
			else if(dataTemp.getSalesCycle()==Common.SALES_NO_CYCLE_5)
				new Listcell(Common.SALES_CYCLE_5).setParent(item);
			else if(dataTemp.getSalesCycle()==Common.SALES_NO_CYCLE_6)
				new Listcell(Common.SALES_CYCLE_6).setParent(item);
			else if(dataTemp.getSalesCycle()==Common.SALES_NO_CYCLE_7)
				new Listcell(Common.SALES_CYCLE_7).setParent(item);
			else if(dataTemp.getSalesCycle()==Common.SALES_NO_CYCLE_8)
				new Listcell(Common.SALES_CYCLE_8).setParent(item);
			else 
				new Listcell("").setParent(item);
			
			new Listcell(dataTemp.getRealDate().toLocaleString()).setParent(item);
			new Listcell(dataTemp.getAnchor()!=null?dataTemp.getAnchor().getNama():"").setParent(item);
			
			item.setValue(entity);
		
			
			Box box = new Hbox();
			//box.setWidth("100%");
			Button btnUpdate = new Button("Update");
			btnUpdate.setMold("trendy");
			btnUpdate.addEventListener("onClick", new EventListener<Event>() {

				@Override
				public void onEvent(Event arg0) throws Exception {
					// TODO Auto-generated method stub
					showForm(dataTemp);
				}
			
			});
			

			item.addEventListener("onDoubleClick", new EventListener<Event>() {
				
				@Override
				public void onEvent(Event arg0) throws Exception {
					// TODO Auto-generated method stub
					
			//		detail(dataTemp);
				}
			});
			
			
		
			
			btnUpdate.setParent(box);
			Button btnDelete = new Button("Delete");
			btnDelete.setMold("trendy");
			btnDelete.addEventListener("onClick", new EventListener<Event>() {

				@Override
				public void onEvent(Event arg0) throws Exception {
					// TODO Auto-generated method stub
					
					Messagebox.show("Yakin Hapus Data?", "Konfirmasi Hapus", Messagebox.OK | Messagebox.NO, Messagebox.QUESTION, new EventListener<Event>() {
						
						@Override
						public void onEvent(Event event) throws Exception {
							// TODO Auto-generated method stub
							int statusHapus = (Integer)event.getData();
							
							if(statusHapus==Messagebox.OK){
							//	deleteData(dataTemp);
								
							}
							else
								return;
							
						}
					});
				
				}
			
			});

			btnDelete.setParent(box);
		
			Listcell listCell = new Listcell();
			box.setParent(listCell);
			listCell.setParent(item);
			
		//	setCheckBox();
		}

				
	}



	@Override
	public void showForm(CycleSales entity) {
		// TODO Auto-generated method stub
		if(entity!=null)
			cycleSales = entity;
		else
			cycleSales = new CycleSales();
		
		window = new Window();
		window.setContentStyle("overflow:auto");
		
		window.setParent(self);
		window.setTitle("Form Sales Cycle");
		window.setMode("popup");
		window.setPosition("center,top");
		window.setClosable(true);
		window.setWidth("400px");
		window.setHeight("470px");
		window.setVisible(true);
	
		grid = new Grid();
		columns = new Columns();
		rows = new Rows();
		rows.setParent(grid);
		
		grid.setParent(window);
		columns.setParent(grid);
	
		column = new Column();
		column.setWidth("35%");
		column.setParent(columns);
		
		column = new Column();
		column.setParent(columns);
		
		row = new Row();
		row.setParent(rows);
		row.appendChild(new Label("Category"));
		tbCategory = new Textbox(entity!=null?entity.getCategory():"");
		tbCategory.setParent(row);
		row.setParent(rows);
		
		row = new Row();
		row.setParent(rows);
		row.appendChild(new Label("Customer"));
		initComboCustomer();
		if(entity!=null)
			if(entity.getCustomer()!=null)
			{
				Comboitem item = new Comboitem();
				item.setValue(entity.getCustomer());
				item.setLabel(entity.getCustomer().getName());
				item.setParent(cbCustomer);
				cbCustomer.setSelectedItem(item);
				
			}
		cbCustomer.setReadonly(true);
		cbCustomer.setParent(row);
		row.setParent(rows);
		
		
	
		row = new Row();
		row.setParent(rows);
		row.appendChild(new Label("BUC"));
		initComboBUC();
		if(entity!=null)
			if(entity.getBuc()!=null)
			{
				Comboitem item = new Comboitem();
				item.setValue(entity.getCustomer());
				item.setLabel(entity.getCustomer().getName());
				item.setParent(cbBUC);
				cbBUC.setSelectedItem(item);
				
			}
		cbBUC.setReadonly(true);
		cbBUC.setParent(row);
		row.setParent(rows);
	
		row = new Row();
		row.setParent(rows);
		row.appendChild(new Label("Relationship Manager"));
		tbRelationshipManager = new Textbox(cycleSales!=null?cycleSales.getRelationshipManager():"");
		tbRelationshipManager.setParent(row);
		row.setParent(rows);
	
		row = new Row();
		row.setParent(rows);
		row.appendChild(new Label("Product"));
		initComboProduct();
		if(entity!=null)
			if(entity.getProduct()!=null)
			{
				Comboitem item = new Comboitem();
				item.setValue(entity.getProduct());
				item.setLabel(entity.getProduct().getNama());
				item.setParent(cbProduct);
				cbProduct.setSelectedItem(item);
				
			}
		cbProduct.setReadonly(true);
		cbProduct.setParent(row);
		row.setParent(rows);
		
		row = new Row();
		row.setParent(rows);
		row.appendChild(new Label("Est Add FBI"));
		tbEstAddFBI = new Textbox(cycleSales!=null?cycleSales.getEstAddFBI():"");
		tbEstAddFBI.setParent(row);
		row.setParent(rows);
		
		row = new Row();
		row.setParent(rows);
		row.appendChild(new Label("Est Add Avg"));
		tbEstAddAvg = new Textbox(cycleSales!=null?cycleSales.getEstAddAvg():"");
		tbEstAddAvg.setParent(row);
		row.setParent(rows);
		
		row = new Row();
		row.setParent(rows);
		row.appendChild(new Label("Sales Cycle"));
		cbSalesCycle = new Combobox();
		cbItem = new Comboitem();
		cbItem.setLabel(Common.SALES_CYCLE_1);
		cbItem.setValue(Common.SALES_NO_CYCLE_1);
		cbItem.setParent(cbSalesCycle);
		cbItem = new Comboitem();
		cbItem.setLabel(Common.SALES_CYCLE_2);
		cbItem.setValue(Common.SALES_NO_CYCLE_2);
		cbItem.setParent(cbSalesCycle);
		cbItem = new Comboitem();
		cbItem.setLabel(Common.SALES_CYCLE_3);
		cbItem.setValue(Common.SALES_NO_CYCLE_3);
		cbItem.setParent(cbSalesCycle);
		cbItem = new Comboitem();
		cbItem.setLabel(Common.SALES_CYCLE_4);
		cbItem.setValue(Common.SALES_NO_CYCLE_4);
		cbItem.setParent(cbSalesCycle);
		cbItem = new Comboitem();
		cbItem.setLabel(Common.SALES_CYCLE_5);
		cbItem.setValue(Common.SALES_NO_CYCLE_5);
		cbItem.setParent(cbSalesCycle);
		cbItem = new Comboitem();
		cbItem.setLabel(Common.SALES_CYCLE_6);
		cbItem.setValue(Common.SALES_NO_CYCLE_6);
		cbItem.setParent(cbSalesCycle);
		cbItem = new Comboitem();
		cbItem.setLabel(Common.SALES_CYCLE_7);
		cbItem.setValue(Common.SALES_NO_CYCLE_7);
		cbItem.setParent(cbSalesCycle);
		cbItem = new Comboitem();
		cbItem.setLabel(Common.SALES_CYCLE_8);
		cbItem.setValue(Common.SALES_NO_CYCLE_8);
		cbItem.setParent(cbSalesCycle);
		cbSalesCycle.setParent(row);
		row.setParent(rows);
	
		row = new Row();
		row.setParent(rows);
		row.appendChild(new Label("Open Date"));
		dBoxOpenDate = new Datebox(cycleSales!=null?cycleSales.getOpenDate():null);
		dBoxOpenDate.setParent(row);
		row.setParent(rows);
		
		row = new Row();
		row.setParent(rows);
		row.appendChild(new Label("Expected Close Date"));
		dBoxExpCloseDate = new Datebox(cycleSales!=null?cycleSales.getExpCloseDate():null);
		dBoxExpCloseDate.setParent(row);
		row.setParent(rows);
		
		row = new Row();
		row.setParent(rows);
		row.appendChild(new Label("Won Date"));
		dBoxWonDate = new Datebox(cycleSales!=null?cycleSales.getWonDate():null);
		dBoxWonDate.setParent(row);
		row.setParent(rows);
		
		row = new Row();
		row.setParent(rows);
		row.appendChild(new Label("Close Implementation Date"));
		dBoxCloseImplDate = new Datebox(cycleSales!=null?cycleSales.getCloseImplDate():null);
		dBoxCloseImplDate.setParent(row);
		row.setParent(rows);
		
		row = new Row();
		row.setParent(rows);
		row.appendChild(new Label("Deal Date"));
		dBoxDealDate = new Datebox(cycleSales!=null?cycleSales.getRealDate():null);
		dBoxDealDate.setParent(row);
		row.setParent(rows);
		
		row = new Row();
		row.setParent(rows);
		row.appendChild(new Label("Comments"));
		tbComments = new Textbox(cycleSales!=null?cycleSales.getComments():"");
		tbComments.setParent(row);
		row.setParent(rows);
		
		row = new Row();
		row.setParent(rows);
		row.appendChild(new Label("Next Action"));
		tbNextAction = new Textbox(cycleSales!=null?cycleSales.getNextAction():"");
		tbNextAction.setParent(row);
		row.setParent(rows);
		
		row = new Row();
		row.setParent(rows);
		row.appendChild(new Label("Anchor"));
		initComboAnchor();
		if(entity!=null)
			if(entity.getAnchor()!=null)
			{
				Comboitem item = new Comboitem();
				item.setValue(entity.getAnchor());
				item.setLabel(entity.getAnchor().getNama());
				item.setParent(cbAnchor);
				cbAnchor.setSelectedItem(item);
				
			}
		cbAnchor.setReadonly(true);
		cbAnchor.setParent(row);
		row.setParent(rows);
		
		row = new Row();
		row.setParent(rows);
		row.appendChild(new Label(""));
		Box box = new Hbox();
		btnSave = new Button("Simpan");
		btnSave.setMold("trendy");
		btnCancel = new Button("Batal");
		btnCancel.setMold("trendy");
		row.setParent(rows);
		
		btnSave.addEventListener("onClick", new EventListener<Event>() {
			@Override
			public void onEvent(Event event) throws Exception {
				// TODO Auto-generated method stub
				cycleSalesDao = HibernateUtil.getCycleSalesDao();
				
				
				
				if(cycleSales==null){
					cycleSales = new CycleSales();
						
				}
				
				cycleSales.setComments(tbComments.getText());
				cycleSales.setCloseImplDate(dBoxCloseImplDate.getValue());
				cycleSales.setEstAddAvg(tbEstAddAvg.getText());
				cycleSales.setEstAddFBI(tbEstAddFBI.getText());
				cycleSales.setExpCloseDate(dBoxExpCloseDate.getValue());
				cycleSales.setWonDate(dBoxWonDate.getValue());
				if(cbGroupCustomer.getSelectedItem()!=null)
					cycleSales.setGroupCustomer((GroupCustomer)cbGroupCustomer.getSelectedItem().getValue());
				if(cbCustomer.getSelectedItem()!=null)
					cycleSales.setCustomer((Customer)cbCustomer.getSelectedItem().getValue());
				if(cbGroupCustomer.getSelectedItem()!=null)
					cycleSales.setGroupCustomer((GroupCustomer)cbGroupCustomer.getSelectedItem().getValue());
				if(cbAnchor.getSelectedItem()!=null)
					cycleSales.setAnchor((Anchor)cbAnchor.getSelectedItem().getValue());
				cycleSales.setCategory(tbCategory.getValue());
				if(cbProduct.getSelectedItem()!=null)
					cycleSales.setProduct((Product)cbProduct.getSelectedItem().getValue());
				if(cbBUC.getSelectedItem()!=null)
					cycleSales.setBuc((BUC)cbBUC.getSelectedItem().getValue());
				
				
				cycleSales.setNextAction(tbNextAction.getValue());
				cycleSales.setOpenDate(dBoxOpenDate.getValue());

				cycleSales.setRealDate(dBoxDealDate.getValue());
				cycleSales.setRelationshipManager(tbRelationshipManager.getValue());
				cycleSales.setSalesCycle((int)cbSalesCycle.getSelectedItem().getValue());
				if((int)cbSalesCycle.getSelectedItem().getValue()==Common.SALES_NO_CYCLE_2)
				{
					CycleSolution cycleSol = new CycleSolution();
					CycleSolutionDao cycleSolDao = HibernateUtil.getCycleSolutionDao();
					
					cycleSol.setComments(tbComments.getText());
					cycleSol.setCloseImplDate(dBoxCloseImplDate.getValue());
					cycleSol.setDepartment(tbDepartment.getText());
					cycleSol.setEstAddAvg(tbEstAddAvg.getText());
					cycleSol.setEstAddFBI(tbEstAddFBI.getText());
					cycleSol.setExpCloseDate(dBoxExpCloseDate.getValue());
					cycleSol.setWonDate(dBoxWonDate.getValue());
					if(cbGroupCustomer.getSelectedItem()!=null)
						cycleSol.setGroupCustomer((GroupCustomer)cbGroupCustomer.getSelectedItem().getValue());
					if(cbCustomer.getSelectedItem()!=null)
						cycleSol.setCustomer((Customer)cbCustomer.getSelectedItem().getValue());
					if(cbGroupCustomer.getSelectedItem()!=null)
						cycleSol.setGroupCustomer((GroupCustomer)cbGroupCustomer.getSelectedItem().getValue());
					if(cbAnchor.getSelectedItem()!=null)
						cycleSol.setAnchor((Anchor)cbAnchor.getSelectedItem().getValue());
					cycleSol.setCategory(tbCategory.getValue());
					cycleSol.setNextAction(tbNextAction.getValue());
					cycleSol.setOpenDate(dBoxOpenDate.getValue());
					if(cbBUC.getSelectedItem()!=null)
						cycleSol.setBuc((BUC)cbBUC.getSelectedItem().getValue());
					cycleSol.setRealDate(dBoxDealDate.getValue());
					cycleSol.setRelationshipManager(tbRelationshipManager.getValue());
					cycleSol.setSolutionCycle(1);
					
					cycleSolDao.insert(cycleSol);
				}
				
				cycleSalesDao.insert(cycleSales);		
				Messagebox.show("Berhasil disimpan", "Simpan data", Messagebox.OK, null, new EventListener<Event>() {
					
					@Override
					public void onEvent(Event event) throws Exception {
						// TODO Auto-generated method stub
						window.onClose();
					}
				});
				
				loadData();
			}
		
		});
		
		btnCancel.addEventListener("onClick", new EventListener<Event>() {
			@Override
			public void onEvent(Event event) throws Exception {
				// TODO Auto-generated method stub
				window.onClose();
			}
		
		});
		
		btnSave.setParent(box);
		btnCancel.setParent(box);
		box.setParent(row);
		row.setParent(rows);
		
		window.onModal();
	
	}

	@Override
	public void onFilter() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onReset() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onAdd() {
		// TODO Auto-generated method stub
		showForm(null);
	}

	@Override
	public void onDelete() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onUpdate() {
		// TODO Auto-generated method stub
		
	}
	
	
	class CategoryRenderer implements ComboitemRenderer<Category>{

		@Override
		public void render(Comboitem item, Category entity, int idx)
				throws Exception {
			// TODO Auto-generated method stub
			
			item.setValue(entity);
			item.setLabel(entity.getNama());
			
		}
		
	}


	void initComboCustomer(){
		cbCustomer = new Combobox();
		customerDao = HibernateUtil.getCustomerDao();
		List<Customer> customerList = customerDao.getAll();
		listModel = new ListModelList<>(customerList);
		cbCustomer.setModel(listModel);
		cbCustomer.setItemRenderer(new CustomerRenderer());
		
	}
	

	class CustomerRenderer implements ComboitemRenderer<Customer>{

		@Override
		public void render(Comboitem item, Customer entity, int idx)
				throws Exception {
			// TODO Auto-generated method stub
			
			item.setValue(entity);
			item.setLabel(entity.getName());
			
		}
		
	}


	void initComboProduct(){
		cbProduct = new Combobox();
		productDao = HibernateUtil.getProductDao();
		List<Product> products = productDao.getAll();
		listModel = new ListModelList<>(products);
		cbProduct.setModel(listModel);
		cbProduct.setItemRenderer(new ProductRenderer());
		
	}
	

	class ProductRenderer implements ComboitemRenderer<Product>{

		@Override
		public void render(Comboitem item,Product entity, int idx)
				throws Exception {
			// TODO Auto-generated method stub
			
			item.setValue(entity);
			item.setLabel(entity.getNama());
			
		}
		
	}

	void initComboBUC(){
		cbBUC = new Combobox();
		bucDao = HibernateUtil.getBUCDao();
		List<BUC> bucs = bucDao.getAll();
		listModel = new ListModelList<>(bucs);
		cbBUC.setModel(listModel);
		cbBUC.setItemRenderer(new BUCRenderer());
		
	}
	

	class BUCRenderer implements ComboitemRenderer<BUC>{

		@Override
		public void render(Comboitem item, BUC entity, int idx)
				throws Exception {
			// TODO Auto-generated method stub
			
			item.setValue(entity);
			item.setLabel(entity.getNama());
			
		}
		
	}

	void initComboGroupCustomer(){
		cbGroupCustomer = new Combobox();
		groupCustomerDao = HibernateUtil.getGroupCustomerDao();
		List<GroupCustomer> groupCustomerList = groupCustomerDao.getAll();
		listModel = new ListModelList<>(groupCustomerList);
		cbGroupCustomer.setModel(listModel);
		cbGroupCustomer.setItemRenderer(new GroupCustomerRenderer());
		
	}
	

	class GroupCustomerRenderer implements ComboitemRenderer<GroupCustomer>{

		@Override
		public void render(Comboitem item, GroupCustomer entity, int idx)
				throws Exception {
			// TODO Auto-generated method stub
			
			item.setValue(entity);
			item.setLabel(entity.getGroupName());
			
		}
		
	}
	
	void initComboAnchor(){
		cbAnchor = new Combobox();
		anchorDao = HibernateUtil.getAnchorDao();
		List<Anchor> anchorList = anchorDao.getAll();
		listModel = new ListModelList<>(anchorList);
		cbAnchor.setModel(listModel);
		cbAnchor.setItemRenderer(new AnchorRenderer());
		
	}
	

	class AnchorRenderer implements ComboitemRenderer<Anchor>{

		@Override
		public void render(Comboitem item, Anchor entity, int idx)
				throws Exception {
			// TODO Auto-generated method stub
			
			item.setValue(entity);
			item.setLabel(entity.getNama());
			
		}
		
	}


	
}
