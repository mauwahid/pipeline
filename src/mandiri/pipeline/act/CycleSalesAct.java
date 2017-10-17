package mandiri.pipeline.act;

import java.util.List;

import mandiri.pipeline.act.CycleHeadSales.ProductRenderer;
import mandiri.pipeline.act.CycleSalesAct.CycleSalesRenderer;
import mandiri.pipeline.dao.AnchorDao;
import mandiri.pipeline.dao.BUCDao;
import mandiri.pipeline.dao.CategoryDao;
import mandiri.pipeline.dao.CustomerDao;
import mandiri.pipeline.dao.CycleSalesDao;
import mandiri.pipeline.dao.CycleSolutionDao;
import mandiri.pipeline.dao.GroupCustomerDao;
import mandiri.pipeline.dao.LogSalesDao;
import mandiri.pipeline.dao.ProductDao;
import mandiri.pipeline.dao.AdminWebDao;
import mandiri.pipeline.domain.Anchor;
import mandiri.pipeline.domain.BUC;
import mandiri.pipeline.domain.Category;
import mandiri.pipeline.domain.Customer;
import mandiri.pipeline.domain.CycleSales;
import mandiri.pipeline.domain.CycleSolution;
import mandiri.pipeline.domain.GroupCustomer;
import mandiri.pipeline.domain.LogSales;
import mandiri.pipeline.domain.Product;
import mandiri.pipeline.domain.AdminWeb;
import mandiri.pipeline.util.Common;
import mandiri.pipeline.util.HibernateUtil;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Borderlayout;
import org.zkoss.zul.Box;
import org.zkoss.zul.Button;
import org.zkoss.zul.Column;
import org.zkoss.zul.Columns;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.ComboitemRenderer;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.East;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listhead;
import org.zkoss.zul.Listheader;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Row;
import org.zkoss.zul.Rows;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.West;
import org.zkoss.zul.Window;

public class CycleSalesAct extends GenericForwardComposer<Component> implements Act<CycleSales>{

	CycleSalesDao cycleSalesDao;
	CycleSales cycleSales;
	
	private Textbox tbCategory;
	private Combobox cbGroupCustomer;
	private Combobox cbCustomer;
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
	private Combobox cbBUC;
	private Combobox cbProduct;
	private Button btnSave;
	private Button btnCancel;
	
	private Window window;
	private Grid grid;
	private Columns columns;
	private Rows rows;
	private Row row;
	private Column column;
	private Comboitem cbItem;
	private AdminWeb adminWeb;
	
	Listbox list_cycleSales;
	
	private GroupCustomerDao groupCustomerDao;
	private CustomerDao customerDao;
	private AnchorDao anchorDao;
	private BUCDao bucDao;
	private ProductDao productDao;
	private LogSalesDao logSalesDao;
	
	Listbox listLogStatus;
	Listhead listHead;
	Listitem listItem;
	Listhead listHeadLog;
	
	Borderlayout borderLayout;
	West west;
	East east;
	
	
	private ListModelList listModel;
	
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		loadData();
		adminWeb = (AdminWeb) session.getAttribute("pengguna");
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
				
		
			new Listcell(index+"").setParent(item);
			new Listcell(dataTemp.getCategory()).setParent(item);
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
					
					detail(dataTemp);
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
								deleteData(dataTemp);
								
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
			this.cycleSales = entity;
		
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
		column.setWidth("50%");
		column.setParent(columns);
		
		column = new Column();
		column.setParent(columns);
		
		row = new Row();
		row.setParent(rows);
		row.appendChild(new Label("Category"));
		tbCategory = new Textbox();
		tbCategory.setValue(entity!=null?entity.getCategory():"");
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
		cbCustomer.setReadonly(false);
		cbCustomer.setAutocomplete(true);
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
		tbRelationshipManager = new Textbox(entity!=null?entity.getRelationshipManager():"");
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
		cbItem.setLabel(Common.SALES_NO_CYCLE_1 + ". "+Common.SALES_CYCLE_1);
		cbItem.setValue(Common.SALES_NO_CYCLE_1);
		cbItem.setParent(cbSalesCycle);
		cbItem = new Comboitem();
		cbItem.setLabel(Common.SALES_NO_CYCLE_2 + ". "+Common.SALES_CYCLE_2);
		cbItem.setValue(Common.SALES_NO_CYCLE_2);
		cbItem.setParent(cbSalesCycle);
		cbItem = new Comboitem();
		cbItem.setLabel(Common.SALES_NO_CYCLE_3 + ". "+Common.SALES_CYCLE_3);
		cbItem.setValue(Common.SALES_NO_CYCLE_3);
		cbItem.setParent(cbSalesCycle);
		cbItem = new Comboitem();
		cbItem.setLabel(Common.SALES_NO_CYCLE_4 + ". "+Common.SALES_CYCLE_4);
		cbItem.setValue(Common.SALES_NO_CYCLE_4);
		cbItem.setParent(cbSalesCycle);
		cbItem = new Comboitem();
		cbItem.setLabel(Common.SALES_NO_CYCLE_5 + ". "+Common.SALES_CYCLE_5);
		cbItem.setValue(Common.SALES_NO_CYCLE_5);
		cbItem.setParent(cbSalesCycle);
		cbItem = new Comboitem();
		cbItem.setLabel(Common.SALES_NO_CYCLE_6 + ". "+Common.SALES_CYCLE_6);
		cbItem.setValue(Common.SALES_NO_CYCLE_6);
		cbItem.setParent(cbSalesCycle);
		cbItem = new Comboitem();
		cbItem.setLabel(Common.SALES_NO_CYCLE_7 + ". "+Common.SALES_CYCLE_7);
		cbItem.setValue(Common.SALES_NO_CYCLE_7);
		cbItem.setParent(cbSalesCycle);
		cbItem = new Comboitem();
		cbItem.setLabel(Common.SALES_NO_CYCLE_8 + ". "+Common.SALES_CYCLE_8);
		cbItem.setValue(Common.SALES_NO_CYCLE_8);
		cbItem.setParent(cbSalesCycle);
		if(entity!=null){
			if(entity.getSalesCycle()!=0){
				 int idx = entity.getSalesCycle() - 1;
				cbSalesCycle.setSelectedIndex(idx);
			}
		}
			
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
		tbComments.setMultiline(true);
		tbComments.setWidth("20");
		tbComments.setHeight("5");
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
				logSalesDao = HibernateUtil.getLogSalesDao();
				LogSales logSales = new LogSales();
				
				CycleSales cs = null;
				if(cycleSales==null){
					cs = new CycleSales();
						
				}else{
					cs = cycleSales;
					System.out.println("Not Null");
				}
					
				cs.setComments(tbComments.getText());
				cs.setCloseImplDate(dBoxCloseImplDate.getValue());
				cs.setEstAddAvg(tbEstAddAvg.getText());
				cs.setEstAddFBI(tbEstAddFBI.getText());
				cs.setExpCloseDate(dBoxExpCloseDate.getValue());
				cs.setWonDate(dBoxWonDate.getValue());
				if(cbGroupCustomer.getSelectedItem()!=null)
					cs.setGroupCustomer((GroupCustomer)cbGroupCustomer.getSelectedItem().getValue());
				if(cbCustomer.getSelectedItem()!=null)
					cs.setCustomer((Customer)cbCustomer.getSelectedItem().getValue());
				if(cbGroupCustomer.getSelectedItem()!=null)
					cs.setGroupCustomer((GroupCustomer)cbGroupCustomer.getSelectedItem().getValue());
				if(cbAnchor.getSelectedItem()!=null)
					cs.setAnchor((Anchor)cbAnchor.getSelectedItem().getValue());
					cs.setCategory(tbCategory.getValue());
				if(cbBUC.getSelectedItem()!=null)
					cs.setBuc((BUC)cbBUC.getSelectedItem().getValue());
				if(cbProduct.getSelectedItem()!=null)
					cs.setProduct((Product)cbProduct.getSelectedItem().getValue());
				
				cs.setNextAction(tbNextAction.getValue());
				cs.setOpenDate(dBoxOpenDate.getValue());
				//cs.setProduct(tbProduct.getValue());
				cs.setRealDate(dBoxDealDate.getValue());
				cs.setRelationshipManager(tbRelationshipManager.getValue());
				if(cbSalesCycle.getSelectedItem()!=null)
					cs.setSalesCycle((int)cbSalesCycle.getSelectedItem().getValue());
				
				logSales.setUserSales(adminWeb.getNama());
				logSales.setComments(tbComments.getText());
				logSales.setCloseImplDate(dBoxCloseImplDate.getValue().toLocaleString());
				logSales.setEstAddAvg(tbEstAddAvg.getText());
				logSales.setEstAddFBI(tbEstAddFBI.getText());
				logSales.setExpCloseDate(dBoxExpCloseDate.getValue().toLocaleString());
				logSales.setWonDate(dBoxWonDate.getValue().toLocaleString());
				if(cbGroupCustomer.getSelectedItem()!=null)
					{
						GroupCustomer gc = (GroupCustomer)cbGroupCustomer.getSelectedItem().getValue();
						logSales.setGroupCustomer(gc.getGroupName());
					}
				
				if(cbCustomer.getSelectedItem()!=null){
					Customer customer = (Customer)cbCustomer.getSelectedItem().getValue();
					logSales.setCustomer(customer.getName());
				}
			
				if(cbAnchor.getSelectedItem()!=null){
					Anchor anc = (Anchor)cbAnchor.getSelectedItem().getValue();
					logSales.setAnchor(anc.getNama());
				}
				
				logSales.setCategory(tbCategory.getValue());
				
				if(cbBUC.getSelectedItem()!=null){
					BUC buc = (BUC)cbBUC.getSelectedItem().getValue();
					logSales.setBuc(buc.getNama());
				}
					
				if(cbProduct.getSelectedItem()!=null){
					Product prod = (Product)cbProduct.getSelectedItem().getValue();
					logSales.setProduct(prod.getNama());
					
				}
					
				logSales.setNextAction(tbNextAction.getValue());
				logSales.setOpenDate(dBoxOpenDate.getValue().toLocaleString());
				//cs.setProduct(tbProduct.getValue());
				logSales.setRealDate(dBoxDealDate.getValue().toLocaleString());
				logSales.setRelationshipManager(tbRelationshipManager.getValue());
				
				if(cbSalesCycle.getSelectedItem()!=null){
					int value = (int)cbSalesCycle.getSelectedItem().getValue();
					if(value==1)
						logSales.setSalesCycle(Common.SALES_NO_CYCLE_1 + ". "+Common.SALES_CYCLE_1);
					else if(value==2)
						logSales.setSalesCycle(Common.SALES_NO_CYCLE_2 + ". "+Common.SALES_CYCLE_2);
					else if(value==3)
						logSales.setSalesCycle(Common.SALES_NO_CYCLE_3 + ". "+Common.SALES_CYCLE_3);
					else if(value==4)
						logSales.setSalesCycle(Common.SALES_NO_CYCLE_4 + ". "+Common.SALES_CYCLE_4);
					else if(value==5)
						logSales.setSalesCycle(Common.SALES_NO_CYCLE_5 + ". "+Common.SALES_CYCLE_5);
					else if(value==6)
						logSales.setSalesCycle(Common.SALES_NO_CYCLE_6 + ". "+Common.SALES_CYCLE_6);
					else if(value==7)
						logSales.setSalesCycle(Common.SALES_NO_CYCLE_7 + ". "+Common.SALES_CYCLE_7);
					else if(value==8)
						logSales.setSalesCycle(Common.SALES_NO_CYCLE_8 + ". "+Common.SALES_CYCLE_8);
					 
						
				}
					
				
				if((int)cbSalesCycle.getSelectedItem().getValue()==Common.SALES_NO_CYCLE_2)
				{
					CycleSolution cycleSol = new CycleSolution();
					CycleSolutionDao cycleSolDao = HibernateUtil.getCycleSolutionDao();
					
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
					if(cbBUC.getSelectedItem()!=null)
						cycleSol.setBuc((BUC)cbBUC.getSelectedItem().getValue());
					cycleSol.setNextAction(tbNextAction.getValue());
					cycleSol.setOpenDate(dBoxOpenDate.getValue());
					//cycleSol.setProduct(tbProduct.getValue());
					cycleSol.setRealDate(dBoxDealDate.getValue());
					cycleSol.setRelationshipManager(tbRelationshipManager.getValue());
					cycleSol.setSolutionCycle(1);
					
					cycleSolDao.insert(cycleSol);
					
				}
				
				if(cycleSales==null)
					cycleSalesDao.insert(cs);
				else
					cycleSalesDao.update(cs);
				
				logSalesDao.insert(logSales);
				
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
	
	public void detail(CycleSales entity) {
		
		System.out.println("Masuk detail");
		cycleSales = entity;
		// TODO Auto-generated method stub
		window = new Window();
		window.setContentStyle("overflow:auto");
		
		window.setParent(self);
		window.setTitle("Detail Cycle Sales");
		borderLayout = new Borderlayout();
		window.setMode("popup");
		window.setPosition("center,top");
		window.setClosable(true);
		window.setWidth("1200px");
		window.setHeight("500px");
		window.setVisible(true);
		west = new West();
		west.setSize("30%");
		west.setAutoscroll(true);
		east = new East();
		east.setSize("70%");
		
		grid = new Grid();
		columns = new Columns();
		rows = new Rows();
		rows.setParent(grid);
		
	//	grid.setParent(window);
		grid.setParent(west);
		west.setParent(borderLayout);
		borderLayout.setParent(window);
		
		columns.setParent(grid);
	
		column = new Column();
		column.setWidth("35%");
		column.setParent(columns);
		
		column = new Column();
		column.setParent(columns);
		
		row = new Row();
		row.setParent(rows);
		row.appendChild(new Label("Category"));
		row.appendChild(new Label(entity!=null?entity.getCategory():""));
		row.setParent(rows);
		
		row = new Row();
		row.setParent(rows);
		row.appendChild(new Label("Customer"));
		row.appendChild(new Label(entity.getCustomer()!=null?entity.getCustomer().getName():""));
		row.setParent(rows);
		
		row = new Row();
		row.setParent(rows);
		row.appendChild(new Label("Group of Customer"));
		row.appendChild(new Label(entity!=null?entity.getGroupCustomer().getGroupName():""));
		row.setParent(rows);
		
		row = new Row();
		row.setParent(rows);
		row.appendChild(new Label("BUC"));
		row.appendChild(new Label(entity.getBuc()!=null?entity.getBuc().getNama():""));
		row.setParent(rows);
	

		row = new Row();
		row.setParent(rows);
		row.appendChild(new Label("Relationship Manager"));
		row.appendChild(new Label(entity!=null?entity.getRelationshipManager():""));
		row.setParent(rows);
	
		row = new Row();
		row.setParent(rows);
		row.appendChild(new Label("Product"));
		row.appendChild(new Label(entity.getProduct()!=null?entity.getProduct().getNama():""));
		row.setParent(rows);
		
	
		row = new Row();
		row.setParent(rows);
		row.appendChild(new Label("Est Add FBI"));
		row.appendChild(new Label(entity!=null?entity.getEstAddFBI():""));
		row.setParent(rows);
		
		row = new Row();
		row.setParent(rows);
		row.appendChild(new Label("Est Add Avg"));
		row.appendChild(new Label(entity!=null?entity.getEstAddAvg():""));
		row.setParent(rows);
		
		row = new Row();
		row.setParent(rows);
		row.appendChild(new Label("Sales Cycle"));
		if(entity!=null){
			if(entity.getSalesCycle()!=0){
				if(entity.getSalesCycle()==1)
					row.appendChild(new Label(Common.SALES_NO_CYCLE_1 + ". "+Common.SALES_CYCLE_1));
				else if(entity.getSalesCycle()==2)
					row.appendChild(new Label(Common.SALES_NO_CYCLE_2 + ". "+Common.SALES_CYCLE_2));
				else if(entity.getSalesCycle()==3)
					row.appendChild(new Label(Common.SALES_NO_CYCLE_3 + ". "+Common.SALES_CYCLE_3));
				else if(entity.getSalesCycle()==4)
					row.appendChild(new Label(Common.SALES_NO_CYCLE_4 + ". "+Common.SALES_CYCLE_4));
				else if(entity.getSalesCycle()==5)
					row.appendChild(new Label(Common.SALES_NO_CYCLE_5 + ". "+Common.SALES_CYCLE_5));
				else if(entity.getSalesCycle()==6)
					row.appendChild(new Label(Common.SALES_NO_CYCLE_6 + ". "+Common.SALES_CYCLE_6));
				else if(entity.getSalesCycle()==7)
					row.appendChild(new Label(Common.SALES_NO_CYCLE_7 + ". "+Common.SALES_CYCLE_7));
				else if(entity.getSalesCycle()==8)
					row.appendChild(new Label(Common.SALES_NO_CYCLE_8 + ". "+Common.SALES_CYCLE_8));
				else
					row.appendChild(new Label(""));
			}
		}
		row.setParent(rows);
	
		row = new Row();
		row.setParent(rows);
		row.appendChild(new Label("Open Date"));
		row.appendChild(new Label(cycleSales!=null?cycleSales.getOpenDate().toLocaleString():""));
		row.setParent(rows);
		
		row = new Row();
		row.setParent(rows);
		row.appendChild(new Label("Expected Close Date"));
		row.appendChild(new Label(cycleSales!=null?cycleSales.getExpCloseDate().toLocaleString():""));
		row.setParent(rows);
		
		row = new Row();
		row.setParent(rows);
		row.appendChild(new Label("Won Date"));
		row.appendChild(new Label(cycleSales!=null?cycleSales.getWonDate().toLocaleString():""));
		row.setParent(rows);
		
		row = new Row();
		row.setParent(rows);
		row.appendChild(new Label("Close Implementation Date"));
		row.appendChild(new Label(cycleSales!=null?cycleSales.getCloseImplDate().toLocaleString():""));
		row.setParent(rows);
		
		row = new Row();
		row.setParent(rows);
		row.appendChild(new Label("Deal Date"));
		row.appendChild(new Label(cycleSales!=null?cycleSales.getRealDate().toLocaleString():""));
		row.setParent(rows);
		
		row = new Row();
		row.setParent(rows);
		row.appendChild(new Label("Comments"));
		row.appendChild(new Label(cycleSales!=null?cycleSales.getComments():""));
		row.setParent(rows);
		
		row = new Row();
		row.setParent(rows);
		row.appendChild(new Label("Next Action"));
		row.appendChild(new Label(cycleSales!=null?cycleSales.getNextAction():""));
		row.setParent(rows);
		
		row = new Row();
		row.setParent(rows);
		row.appendChild(new Label("Anchor"));
		row.appendChild(new Label(entity!=null?entity.getAnchor().getNama():""));
		row.setParent(rows);
	
		
		row = new Row();
		row.setParent(rows);
		row.appendChild(new Label(""));
		Box box = new Hbox();
		btnCancel = new Button("Close");
		row.setParent(rows);
		
		btnCancel.addEventListener("onClick", new EventListener<Event>() {
			@Override
			public void onEvent(Event event) throws Exception {
				// TODO Auto-generated method stub
				window.onClose();
			}
		
		});
		
		btnCancel.setParent(box);
		btnCancel.setMold("trendy");
		box.setParent(row);
		row.setParent(rows);
		
		showLog(entity);
		window.onModal();
		
		
	}

void showLog(CycleSales entity) {
	
	cycleSales = entity;
	// TODO Auto-generated method stub
	east.setVisible(true);
	listLogStatus = new Listbox();
	listLogStatus.setParent(east);
	east.setParent(borderLayout);
	
	listLogStatus.setVisible(true);
	listLogStatus.setStyle("z");
	listLogStatus.setMold("paging");
	listLogStatus.setPageSize(8);
	
//	listLogStatus.setParent(window);
	
	listHeadLog = new Listhead();
	listHeadLog.setParent(listLogStatus);
	
	listItem = new Listitem();
	Listheader header;
	header =  new Listheader("Tanggal Perubahan");
	header.setStyle("font-size:10px");
	header.setParent(listHeadLog);
	
	header =  new Listheader("Diubah oleh");
	header.setStyle("font-size:10px");
	header.setParent(listHeadLog);
	
	header = new Listheader("Customer");
	header.setStyle("font-size:10px");
	header.setParent(listHeadLog);

	
	header = new Listheader("Est Add Avg");
	header.setStyle("font-size:10px");
	header.setParent(listHeadLog);

	
	
	header = new Listheader("Est Add FBI");
	header.setStyle("font-size:10px");
	header.setParent(listHeadLog);

	header = new Listheader("Next Action");
	header.setStyle("font-size:10px");
	header.setParent(listHeadLog);
	
	header = new Listheader("Comments");
	header.setStyle("font-size:10px");
	header.setParent(listHeadLog);


	header = new Listheader("Aprrove By");
	header.setStyle("font-size:10px");
	header.setParent(listHeadLog);

	logSalesDao = HibernateUtil.getLogSalesDao();
	List<LogSales> logSaleses = logSalesDao.getLogSales(entity);
	listModel = new ListModelList<LogSales>(logSaleses);
	listLogStatus.setModel(listModel);
	
	listLogStatus.setItemRenderer(new LogSalesRenderer());
	
			
	window.onModal();
	
}

	class LogSalesRenderer implements ListitemRenderer<LogSales>{

	
	@Override
	public void render(Listitem item, LogSales entity, int index)
			throws Exception {
		// TODO Auto-generated method stub
		final LogSales dataTemp = entity;
		index = index + 1;
	
		new Listcell(dataTemp!=null?dataTemp.getTglperubahanAkhir():"").setParent(item);
		new Listcell(dataTemp!=null?dataTemp.getUserSales():"").setParent(item);
		new Listcell(dataTemp!=null?dataTemp.getCustomer():"").setParent(item);
		new Listcell(dataTemp!=null?dataTemp.getEstAddAvg():"").setParent(item);
		new Listcell(dataTemp!=null?dataTemp.getEstAddFBI():"").setParent(item);
		new Listcell(dataTemp!=null?dataTemp.getNextAction():"").setParent(item);
		new Listcell(dataTemp!=null?dataTemp.getComments():"").setParent(item);
		new Listcell(dataTemp!=null?dataTemp.getHeadSales():"").setParent(item);
		
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

	private void deleteData(CycleSales dataTemp) {
		// TODO Auto-generated method stub
		cycleSalesDao = HibernateUtil.getCycleSalesDao();
		cycleSalesDao.delete(dataTemp);
		loadData();
		
		
	}

}

