package mandiri.pipeline.act.headsolution;

import java.util.List;

import mandiri.pipeline.act.Act;
import mandiri.pipeline.dao.AnchorDao;
import mandiri.pipeline.dao.BUCDao;
import mandiri.pipeline.dao.CategoryDao;
import mandiri.pipeline.dao.CustomerDao;
import mandiri.pipeline.dao.CycleImplementatorDao;
import mandiri.pipeline.dao.CycleSolutionDao;
import mandiri.pipeline.dao.CycleSolutionDao;
import mandiri.pipeline.dao.GroupCustomerDao;
import mandiri.pipeline.dao.ProductDao;
import mandiri.pipeline.dao.UserSolutionDao;
import mandiri.pipeline.domain.Anchor;
import mandiri.pipeline.domain.BUC;
import mandiri.pipeline.domain.Category;
import mandiri.pipeline.domain.Customer;
import mandiri.pipeline.domain.CycleImplementator;
import mandiri.pipeline.domain.CycleSolution;
import mandiri.pipeline.domain.CycleSolution;
import mandiri.pipeline.domain.GroupCustomer;
import mandiri.pipeline.domain.Product;
import mandiri.pipeline.domain.UserSolution;
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

public class CycleHeadSolutionAct extends GenericForwardComposer<Component> implements Act<CycleSolution> {

	CycleSolutionDao cycleSolutionDao;
	CycleSolution cycleSolution;
	
	private Textbox tbCategory;
	private Combobox cbGroupCustomer;
	private Combobox cbCustomer;
	private Combobox cbUser;
	private Textbox tbDepartment;
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
	private Combobox cbAssign;
	private Combobox cbProduct;
	private Combobox cbBUC;
	
	private Window window;
	private Grid grid;
	private Columns columns;
	private Rows rows;
	private Row row;
	private Column column;
	private Comboitem cbItem;
	
	
	Listbox list_cycleSolution;
	
	private CategoryDao categoryDao;
	private GroupCustomerDao groupCustomerDao;
	private CustomerDao customerDao;
	private AnchorDao anchorDao;
	private UserSolution userSolution;
	private UserSolutionDao userSolutionDao;
	private ProductDao productDao;
	private BUCDao bucDao;
	
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
			
			cycleSolutionDao = HibernateUtil.getCycleSolutionDao();
			List<CycleSolution> list = cycleSolutionDao.getAll();
			
			listModel = new ListModelList(list);
		//	listModel = new SimpleListModel<DistributionFinance>(list);
			//UI Renderer
			listModel.setMultiple(true);
			list_cycleSolution.setModel(listModel);
			list_cycleSolution.setItemRenderer(new CycleSolutionRenderer());
			list_cycleSolution.renderAll();
			//setCheckBox();
			
			
	}
	
	class CycleSolutionRenderer implements ListitemRenderer<CycleSolution>{

		
		@Override
		public void render(Listitem item, CycleSolution entity, int index)
				throws Exception {
			// TODO Auto-generated method stub
			final CycleSolution dataTemp = entity;
			index = index + 1;
				
		
			new Listcell(dataTemp.getId()+"").setParent(item);
			new Listcell(dataTemp!=null?dataTemp.getCategory():"").setParent(item);
			new Listcell(dataTemp.getGroupCustomer()!=null?dataTemp.getGroupCustomer().getGroupName():"").setParent(item);
		/*	lc = new Listcell(dataTemp.getDistributor()!=null?dataTemp.getDistributor().getNama():"");
			lc.setStyle("font-size:10px");
			lc.setParent(item);*/
			new Listcell(dataTemp.getCustomer()!=null?dataTemp.getCustomer().getName():"").setParent(item);
			new Listcell(dataTemp.getProduct()!=null?dataTemp.getProduct().getNama():"").setParent(item);
			new Listcell(dataTemp.getDepartment()).setParent(item);
			new Listcell(dataTemp.getRelationshipManager()).setParent(item);
			if(dataTemp.getSolutionCycle()==Common.SOLUTION_NO_CYCLE_1)
				new Listcell(Common.SOLUTION_CYCLE_1).setParent(item);
			else if(dataTemp.getSolutionCycle()==Common.SOLUTION_NO_CYCLE_2)
				new Listcell(Common.SOLUTION_CYCLE_2).setParent(item);
			else if(dataTemp.getSolutionCycle()==Common.SOLUTION_NO_CYCLE_3)
				new Listcell(Common.SOLUTION_CYCLE_3).setParent(item);
			else if(dataTemp.getSolutionCycle()==Common.SOLUTION_NO_CYCLE_4)
				new Listcell(Common.SOLUTION_CYCLE_4).setParent(item);
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
	public void showForm(CycleSolution entity) {
		// TODO Auto-generated method stub
		if(entity!=null)
			cycleSolution = entity;
		
		window = new Window();
		window.setContentStyle("overflow:auto");
		
		window.setParent(self);
		window.setTitle("Form Solution Cycle");
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
		row.appendChild(new Label("Group of Customer"));
		initComboGroupCustomer();
		if(entity!=null)
			if(entity.getGroupCustomer()!=null)
			{
				Comboitem item = new Comboitem();
				item.setValue(entity.getGroupCustomer());
				item.setLabel(entity.getGroupCustomer().getGroupName());
				item.setParent(cbGroupCustomer);
				cbGroupCustomer.setSelectedItem(item);
				
			}
		cbGroupCustomer.setReadonly(true);
		cbGroupCustomer.setParent(row);
		row.setParent(rows);
		
		row = new Row();
		row.setParent(rows);
		row.appendChild(new Label("Assign To"));
		initComboUser();
		if(entity!=null)
			if(entity.getUserSolution()!=null)
			{
				Comboitem item = new Comboitem();
				item.setValue(entity.getUserSolution());
				item.setLabel(entity.getUserSolution().getNama()!=null?entity.getUserSolution().getNama():"");
				item.setParent(cbUser);
				cbUser.setSelectedItem(item);
				
			}
		cbUser.setReadonly(true);
		cbUser.setParent(row);
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
		tbRelationshipManager = new Textbox(cycleSolution!=null?cycleSolution.getRelationshipManager():"");
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
		tbEstAddFBI = new Textbox(cycleSolution!=null?cycleSolution.getEstAddFBI():"");
		tbEstAddFBI.setParent(row);
		row.setParent(rows);
		
		row = new Row();
		row.setParent(rows);
		row.appendChild(new Label("Est Add Avg"));
		tbEstAddAvg = new Textbox(cycleSolution!=null?cycleSolution.getEstAddAvg():"");
		tbEstAddAvg.setParent(row);
		row.setParent(rows);
		
		row = new Row();
		row.setParent(rows);
		row.appendChild(new Label("Solution Cycle"));
		cbSalesCycle = new Combobox();
		cbItem = new Comboitem();
		cbItem.setLabel("1 " + Common.SOLUTION_CYCLE_1);
		cbItem.setValue(Common.SOLUTION_NO_CYCLE_1);
		cbItem.setParent(cbSalesCycle);
		cbItem = new Comboitem();
		cbItem.setLabel("2 " + Common.SOLUTION_CYCLE_2);
		cbItem.setValue(Common.SOLUTION_NO_CYCLE_2);
		cbItem.setParent(cbSalesCycle);
		cbItem = new Comboitem();
		cbItem.setLabel("3 " + Common.SOLUTION_CYCLE_3);
		cbItem.setValue(Common.SOLUTION_NO_CYCLE_3);
		cbItem.setParent(cbSalesCycle);
		cbItem = new Comboitem();
		cbItem.setLabel("4 " + Common.SOLUTION_CYCLE_4);
		cbItem.setValue(Common.SOLUTION_NO_CYCLE_4);
		cbItem.setParent(cbSalesCycle);
		
		cbSalesCycle.setParent(row);
		row.setParent(rows);
	
		row = new Row();
		row.setParent(rows);
		row.appendChild(new Label("Open Date"));
		dBoxOpenDate = new Datebox(cycleSolution!=null?cycleSolution.getOpenDate():null);
		dBoxOpenDate.setParent(row);
		row.setParent(rows);
		
		row = new Row();
		row.setParent(rows);
		row.appendChild(new Label("Expected Close Date"));
		dBoxExpCloseDate = new Datebox(cycleSolution!=null?cycleSolution.getExpCloseDate():null);
		dBoxExpCloseDate.setParent(row);
		row.setParent(rows);
		
		row = new Row();
		row.setParent(rows);
		row.appendChild(new Label("Won Date"));
		dBoxWonDate = new Datebox(cycleSolution!=null?cycleSolution.getWonDate():null);
		dBoxWonDate.setParent(row);
		row.setParent(rows);
		
		row = new Row();
		row.setParent(rows);
		row.appendChild(new Label("Close Implementation Date"));
		dBoxCloseImplDate = new Datebox(cycleSolution!=null?cycleSolution.getCloseImplDate():null);
		dBoxCloseImplDate.setParent(row);
		row.setParent(rows);
		
		row = new Row();
		row.setParent(rows);
		row.appendChild(new Label("Deal Date"));
		dBoxDealDate = new Datebox(cycleSolution!=null?cycleSolution.getRealDate():null);
		dBoxDealDate.setParent(row);
		row.setParent(rows);
		
		
		row = new Row();
		row.setParent(rows);
		row.appendChild(new Label("Cycle "));
		initComboGroupCustomer();
		if(entity!=null)
			if(entity.getGroupCustomer()!=null)
			{
				Comboitem item = new Comboitem();
				item.setValue(entity.getGroupCustomer());
				item.setLabel(entity.getGroupCustomer().getGroupName());
				item.setParent(cbGroupCustomer);
				cbGroupCustomer.setSelectedItem(item);
				
			}
		cbGroupCustomer.setReadonly(true);
		cbGroupCustomer.setParent(row);
		row.setParent(rows);
	
		row = new Row();
		row.setParent(rows);
		row.appendChild(new Label("Comments"));
		tbComments = new Textbox(cycleSolution!=null?cycleSolution.getComments():"");
		tbComments.setParent(row);
		row.setParent(rows);
		
		row = new Row();
		row.setParent(rows);
		row.appendChild(new Label("Next Action"));
		tbNextAction = new Textbox(cycleSolution!=null?cycleSolution.getNextAction():"");
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
				cycleSolutionDao = HibernateUtil.getCycleSolutionDao();
				CycleSolution cycleSol;
				
				
				if(cycleSolution==null){
					cycleSol = new CycleSolution();
						
				}else
					cycleSol = cycleSolution;
				
				cycleSol.setComments(tbComments.getText());
				cycleSol.setCloseImplDate(dBoxCloseImplDate.getValue());
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
				if(cbProduct.getSelectedItem()!=null)
					cycleSol.setProduct((Product)cbProduct.getSelectedItem().getValue());
				if(cbBUC.getSelectedItem()!=null)
					cycleSol.setBuc((BUC)cbBUC.getSelectedItem().getValue());
				if(cbUser.getSelectedItem()!=null)
					cycleSol.setUserSolution((UserSolution)cbUser.getSelectedItem().getValue());
				
				cycleSol.setNextAction(tbNextAction.getValue());
				cycleSol.setOpenDate(dBoxOpenDate.getValue());
				cycleSol.setRealDate(dBoxDealDate.getValue());
				cycleSol.setRelationshipManager(tbRelationshipManager.getValue());
				cycleSol.setSolutionCycle((int)cbSalesCycle.getSelectedItem().getValue());
				if((int)cbSalesCycle.getSelectedItem().getValue()==Common.SOLUTION_NO_CYCLE_4)
				{
					CycleImplementator cycleImp = new CycleImplementator();
					CycleImplementatorDao cycleImpDao = HibernateUtil.getCycleImplementatorDao();
					
					cycleImp.setComments(tbComments.getText());
					cycleImp.setCloseImplDate(dBoxCloseImplDate.getValue());

					cycleImp.setEstAddAvg(tbEstAddAvg.getText());
					cycleImp.setEstAddFBI(tbEstAddFBI.getText());
					cycleImp.setExpCloseDate(dBoxExpCloseDate.getValue());
					cycleImp.setWonDate(dBoxWonDate.getValue());
					if(cbGroupCustomer.getSelectedItem()!=null)
						cycleImp.setGroupCustomer((GroupCustomer)cbGroupCustomer.getSelectedItem().getValue());
					if(cbCustomer.getSelectedItem()!=null)
						cycleImp.setCustomer((Customer)cbCustomer.getSelectedItem().getValue());
					if(cbGroupCustomer.getSelectedItem()!=null)
						cycleImp.setGroupCustomer((GroupCustomer)cbGroupCustomer.getSelectedItem().getValue());
					if(cbAnchor.getSelectedItem()!=null)
						cycleImp.setAnchor((Anchor)cbAnchor.getSelectedItem().getValue());
			//		cycleImp.setCategory(tbCategory.getValue());
					if(cbProduct.getSelectedItem()!=null)
						cycleImp.setProduct((Product)cbProduct.getSelectedItem().getValue());
					
					cycleImp.setNextAction(tbNextAction.getValue());
					cycleImp.setOpenDate(dBoxOpenDate.getValue());
					cycleImp.setRealDate(dBoxDealDate.getValue());
					cycleImp.setRelationshipManager(tbRelationshipManager.getValue());
					cycleImp.setImplCycle(1);
					
					cycleImpDao.insert(cycleImp);
				}
				
				if(cycleSolution==null)
					cycleSolutionDao.insert(cycleSol);	
				else
					cycleSolutionDao.update(cycleSol);
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
		List<Product> productList = productDao.getAll();
		listModel = new ListModelList<>(productList);
		cbProduct.setModel(listModel);
		cbProduct.setItemRenderer(new ProductRenderer());
		
	}
	

	class ProductRenderer implements ComboitemRenderer<Product>{

		@Override
		public void render(Comboitem item, Product entity, int idx)
				throws Exception {
			// TODO Auto-generated method stub
			
			item.setValue(entity);
			item.setLabel(entity.getNama());
			
		}
		
	}

	void initComboBUC(){
		cbBUC = new Combobox();
		bucDao = HibernateUtil.getBUCDao();
		List<BUC> bucs= bucDao.getAll();
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


	void initComboUser(){
		cbUser = new Combobox();
		userSolutionDao = HibernateUtil.getUserSolutionDao();
		List<UserSolution> userSolutionList = userSolutionDao.getAll();
		listModel = new ListModelList<>(userSolutionList);
		cbUser.setModel(listModel);
		cbUser.setItemRenderer(new UserSolutionRenderer());
		
	}
	

	class UserSolutionRenderer implements ComboitemRenderer<UserSolution>{

		@Override
		public void render(Comboitem item, UserSolution entity, int idx)
				throws Exception {
			// TODO Auto-generated method stub
			
			item.setValue(entity);
			item.setLabel(entity.getNama());
			
		}
		
	}
	

}
