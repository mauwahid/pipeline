package mandiri.pipeline.act.admin;

import java.util.List;

import mandiri.pipeline.act.Act;
import mandiri.pipeline.dao.GroupCustomerDao;
import mandiri.pipeline.domain.GroupCustomer;
import mandiri.pipeline.domain.GroupCustomer;
import mandiri.pipeline.util.HibernateUtil;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Box;
import org.zkoss.zul.Button;
import org.zkoss.zul.Column;
import org.zkoss.zul.Columns;
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

public class GroupCustomerAct extends GenericForwardComposer<Component> implements Act<GroupCustomer> {

	GroupCustomerDao groupCustomerDao;
	GroupCustomer groupCustomer;
	
	private Textbox tbNama;
	private Textbox tbDeskripsi;
	private Button btnSave;
	private Button btnDelete;
	private Button btnCancel;
	
	private Window window;
	private Grid grid;
	private Columns columns;
	private Rows rows;
	private Row row;
	private Column column;
	Listbox list_groupCustomer;
	
	private ListModelList<GroupCustomer> listModel;
	
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		loadData();
	}

	@Override
	public void loadData() {
		// TODO Auto-generated method stub
			
			groupCustomerDao = HibernateUtil.getGroupCustomerDao();
			List<GroupCustomer> list = groupCustomerDao.getAll();
			
			listModel = new ListModelList(list);
		//	listModel = new SimpleListModel<DistributionFinance>(list);
			//UI Renderer
			listModel.setMultiple(true);
			list_groupCustomer.setModel(listModel);
			list_groupCustomer.setItemRenderer(new GroupCustomerRenderer());
			list_groupCustomer.renderAll();
			//setCheckBox();
			
			
	}
	
	class GroupCustomerRenderer implements ListitemRenderer<GroupCustomer>{

		
		@Override
		public void render(Listitem item, GroupCustomer entity, int index)
				throws Exception {
			// TODO Auto-generated method stub
			final GroupCustomer dataTemp = entity;
			index = index + 1;
				
		
			new Listcell(dataTemp.getId()+"").setParent(item);
			new Listcell(dataTemp.getGroupName()).setParent(item);
			new Listcell(dataTemp.getAlamat()).setParent(item);
						
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
	public void showForm(GroupCustomer entity) {
		// TODO Auto-generated method stub
		if(entity!=null)
			groupCustomer = entity;
		else
			groupCustomer = new GroupCustomer();
		
		window = new Window();
		window.setContentStyle("overflow:auto");
		
		window.setParent(self);
		window.setTitle("Form Group Customer");
		window.setMode("popup");
		window.setPosition("center,top");
		window.setClosable(true);
		window.setWidth("400px");
		window.setHeight("150px");
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
		row.appendChild(new Label("Nama"));
		tbNama = new Textbox(groupCustomer!=null?groupCustomer.getGroupName():"");
		tbNama.setParent(row);
		row.setParent(rows);
	
		row = new Row();
		row.setParent(rows);
		row.appendChild(new Label("Deskripsi"));
		tbDeskripsi = new Textbox(groupCustomer!=null?groupCustomer.getAlamat():"");
		tbDeskripsi.setParent(row);
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
				groupCustomerDao = HibernateUtil.getGroupCustomerDao();
				
				
				
				if(groupCustomer==null){
					groupCustomer = new GroupCustomer();
					
						
				}
				
				groupCustomer.setGroupName(tbNama.getText());
				groupCustomer.setAlamat(tbDeskripsi.getValue());
				
				groupCustomerDao.insert(groupCustomer);		
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
