package mandiri.pipeline.act;

public interface Act<T> {

  void loadData();
  void showForm(T entity);
  void onFilter();
  void onReset();
  void onAdd();
 
}
