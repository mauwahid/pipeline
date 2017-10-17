package mandiri.pipeline.act;

import java.util.Date;
import java.util.List;

import mandiri.pipeline.dao.PipelineDao;
import mandiri.pipeline.domain.Anchor;
import mandiri.pipeline.domain.Category;
import mandiri.pipeline.domain.Pipeline;
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

public class PipelineAct extends GenericForwardComposer<Component> implements Act<Pipeline> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1444449813273650170L;
	
	PipelineDao pipelineDao;
	Pipeline pipeline;
	
	private Combobox cbCategory;
	private Textbox tbGrupCustomer;
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
	
	private Window window;
	private Grid grid;
	private Columns columns;
	private Rows rows;
	private Row row;
	private Column column;
	private Comboitem cbItem;
	
	Listbox list_pipeline;
	
	private ListModelList<Pipeline> listModel;
	
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		loadData();
	}

	@Override
	public void loadData() {
		// TODO Auto-generated method stub
			
			pipelineDao = HibernateUtil.getPipelineDao();
			List<Pipeline> list = pipelineDao.getAll();
			
			listModel = new ListModelList(list);
		//	listModel = new SimpleListModel<DistributionFinance>(list);
			//UI Renderer
			listModel.setMultiple(true);
			list_pipeline.setModel(listModel);
			list_pipeline.setItemRenderer(new PipelineRenderer());
			list_pipeline.renderAll();
			//setCheckBox();
			
			
	}
	
	class PipelineRenderer implements ListitemRenderer<Pipeline>{

		
		@Override
		public void render(Listitem item, Pipeline entity, int index)
				throws Exception {
			// TODO Auto-generated method stub
			final Pipeline dataTemp = entity;
			index = index + 1;
				
		
			new Listcell(dataTemp.getId()+"").setParent(item);
			new Listcell(dataTemp.getCatString()).setParent(item);
			new Listcell(dataTemp.getGrupCustomer()).setParent(item);
		/*	lc = new Listcell(dataTemp.getDistributor()!=null?dataTemp.getDistributor().getNama():"");
			lc.setStyle("font-size:10px");
			lc.setParent(item);*/
			new Listcell(dataTemp.getCustomer()).setParent(item);
			new Listcell(dataTemp.getProduct()).setParent(item);
			new Listcell(dataTemp.getDepartment()).setParent(item);
			new Listcell(dataTemp.getRelationshipManager()).setParent(item);
			new Listcell(dataTemp.getSalesCycle()).setParent(item);
			new Listcell(dataTemp.getRealDate().toLocaleString()).setParent(item);
			new Listcell(dataTemp.getAnchorString()).setParent(item);
			
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
	public void showForm(Pipeline entity) {
		// TODO Auto-generated method stub
		if(entity!=null)
			pipeline = entity;
		else
			pipeline = new Pipeline();
		
		window = new Window();
		window.setContentStyle("overflow:auto");
		
		window.setParent(self);
		window.setTitle("Form Value Chain");
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
		cbCategory = new Combobox();
		cbItem = new Comboitem();
		cbItem.setLabel("Category 1");
		cbItem.setValue("Category 1");
		cbItem.setParent(cbCategory);
		cbItem = new Comboitem();
		cbItem.setLabel("Category 2");
		cbItem.setValue("Category 2");
		cbItem.setParent(cbCategory);
		cbItem = new Comboitem();
		cbItem.setLabel("Category 3");
		cbItem.setValue("Category 3");
		cbItem.setParent(cbCategory);
		cbCategory.setParent(row);
		row.setParent(rows);
	
		row = new Row();
		row.setParent(rows);
		row.appendChild(new Label("Group of Customer"));
		tbGrupCustomer = new Textbox(pipeline!=null?pipeline.getGrupCustomer():"");
		tbGrupCustomer.setParent(row);
		row.setParent(rows);
		
	
		row = new Row();
		row.setParent(rows);
		row.appendChild(new Label("Department"));
		tbDepartment = new Textbox(pipeline!=null?pipeline.getDepartment():"");
		tbDepartment.setParent(row);
		row.setParent(rows);
	
		row = new Row();
		row.setParent(rows);
		row.appendChild(new Label("Relationship Manager"));
		tbRelationshipManager = new Textbox(pipeline!=null?pipeline.getRelationshipManager():"");
		tbRelationshipManager.setParent(row);
		row.setParent(rows);
	
		row = new Row();
		row.setParent(rows);
		row.appendChild(new Label("Product"));
		tbProduct = new Textbox(pipeline!=null?pipeline.getProduct():"");
		tbProduct.setParent(row);
		row.setParent(rows);
	
		row = new Row();
		row.setParent(rows);
		row.appendChild(new Label("Est Add FBI"));
		tbEstAddFBI = new Textbox(pipeline!=null?pipeline.getEstAddFBI():"");
		tbEstAddFBI.setParent(row);
		row.setParent(rows);
		
		row = new Row();
		row.setParent(rows);
		row.appendChild(new Label("Est Add Avg"));
		tbEstAddAvg = new Textbox(pipeline!=null?pipeline.getEstAddAvg():"");
		tbEstAddAvg.setParent(row);
		row.setParent(rows);
		
		row = new Row();
		row.setParent(rows);
		row.appendChild(new Label("Sales Cycle"));
		cbSalesCycle = new Combobox();
		cbItem = new Comboitem();
		cbItem.setLabel("1. Cycle 1");
		cbItem.setValue("1. Cycle 1");
		cbItem.setParent(cbSalesCycle);
		cbItem = new Comboitem();
		cbItem.setLabel("2. Cycle 2");
		cbItem.setValue("2. Cycle 2");
		cbItem.setParent(cbSalesCycle);
		cbItem = new Comboitem();
		cbItem.setLabel("3. Cycle 3");
		cbItem.setValue("3. Cycle 3");
		cbItem.setParent(cbSalesCycle);
		cbItem = new Comboitem();
		cbItem.setLabel("4. Cycle 4");
		cbItem.setValue("4. Cycle 4");
		cbItem.setParent(cbSalesCycle);
		cbItem = new Comboitem();
		cbItem.setLabel("5. Cycle 5");
		cbItem.setValue("5. Cycle 5");
		cbItem.setParent(cbSalesCycle);
		
		cbItem = new Comboitem();
		cbItem.setLabel("6. Cycle 6");
		cbItem.setValue("6. Cycle 6");
		cbItem.setParent(cbSalesCycle);
		
		cbItem = new Comboitem();
		cbItem.setLabel("7. Cycle 7");
		cbItem.setValue("7. Cycle 7");
		cbItem.setParent(cbSalesCycle);
		cbSalesCycle.setParent(row);
		row.setParent(rows);
	
		row = new Row();
		row.setParent(rows);
		row.appendChild(new Label("Open Date"));
		dBoxOpenDate = new Datebox(pipeline!=null?pipeline.getOpenDate():null);
		dBoxOpenDate.setParent(row);
		row.setParent(rows);
		
		row = new Row();
		row.setParent(rows);
		row.appendChild(new Label("Expected Close Date"));
		dBoxExpCloseDate = new Datebox(pipeline!=null?pipeline.getExpCloseDate():null);
		dBoxExpCloseDate.setParent(row);
		row.setParent(rows);
		
		row = new Row();
		row.setParent(rows);
		row.appendChild(new Label("Won Date"));
		dBoxWonDate = new Datebox(pipeline!=null?pipeline.getWonDate():null);
		dBoxWonDate.setParent(row);
		row.setParent(rows);
		
		row = new Row();
		row.setParent(rows);
		row.appendChild(new Label("Close Implementation Date"));
		dBoxCloseImplDate = new Datebox(pipeline!=null?pipeline.getCloseImplDate():null);
		dBoxCloseImplDate.setParent(row);
		row.setParent(rows);
		
		row = new Row();
		row.setParent(rows);
		row.appendChild(new Label("Deal Date"));
		dBoxDealDate = new Datebox(pipeline!=null?pipeline.getRealDate():null);
		dBoxDealDate.setParent(row);
		row.setParent(rows);
		
		row = new Row();
		row.setParent(rows);
		row.appendChild(new Label("Comments"));
		tbComments = new Textbox(pipeline!=null?pipeline.getComments():"");
		tbComments.setParent(row);
		row.setParent(rows);
		
		row = new Row();
		row.setParent(rows);
		row.appendChild(new Label("Next Action"));
		tbNextAction = new Textbox(pipeline!=null?pipeline.getNextAction():"");
		tbNextAction.setParent(row);
		row.setParent(rows);
		
		row = new Row();
		row.setParent(rows);
		row.appendChild(new Label("Anchor"));
		cbAnchor = new Combobox();
		cbItem = new Comboitem("Anchor 1");
		cbItem.setParent(cbAnchor);
		cbItem = new Comboitem("Anchor 2");
		cbItem.setParent(cbAnchor);
		cbItem = new Comboitem("Anchor 3");
		cbItem.setParent(cbAnchor);
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
				pipelineDao = HibernateUtil.getPipelineDao();
				
				
				
				if(pipeline==null){
					pipeline = new Pipeline();
					
						
				}
				
				pipeline.setComments(tbComments.getText());
				pipeline.setCloseImplDate(dBoxCloseImplDate.getValue());
				pipeline.setDepartment(tbDepartment.getText());
				pipeline.setEstAddAvg(tbEstAddAvg.getText());
				pipeline.setEstAddFBI(tbEstAddFBI.getText());
				pipeline.setExpCloseDate(dBoxExpCloseDate.getValue());
				pipeline.setWonDate(dBoxWonDate.getValue());
				pipeline.setGrupCustomer(tbGrupCustomer.getValue());
				pipeline.setNextAction(tbNextAction.getValue());
				pipeline.setOpenDate(dBoxOpenDate.getValue());
				pipeline.setProduct(tbProduct.getValue());
				pipeline.setRealDate(dBoxDealDate.getValue());
				pipeline.setRelationshipManager(tbRelationshipManager.getValue());
				pipeline.setSalesCycle((String)cbSalesCycle.getSelectedItem().getValue());
				
				pipelineDao.insert(pipeline);		
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
	
	

}
	