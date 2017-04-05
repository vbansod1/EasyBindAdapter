# Under Development#

This README would normally document whatever steps are necessary to get your application up and running.

### Quick summary###
* Version 1.0
* Adapter for recyclerview that bind model class to the corresponding view. Hides complexity and easy to use.


### How To Use


* Configuration
	

* How to run 
* Create viewholder extends from RecyclerView.ViewHolder and initialize views.
	```	
	@SerializedViews(resourceId = R.layout.inner_layout)
	class ViewHolder extends RecyclerView.ViewHolder {
		@EasyField(mappingId = "name")
        	public TextView title;
	    	public ViewHolder(View itemView) {
		        super(itemView);
        		title = (TextView) itemView.findViewById(R.id.title);
    		}
	}


* Create model extends from EasyModel
		
	```
	public class SampleModel extends EasyModel{
	   @EasyField(mappingId = "name")
    	   String name;
	   @EasyField(mappingId = "desc")
           String desc;
	}
	
		
* mappingId denotes the unique mapping id between model class object and view. This should be same in model class  field and its corrosponding viewholder class field.

	Activity
	```
	EasyAdapter<SampleModel, ViewHolder> adapter = new EasyAdapter<SampleModel, ViewHolder>(this, SampleModel.class, ViewHolder.class);
        recyclerView.setAdapter(adapter);
        adapter.setList(list);



