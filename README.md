# README #

This README would normally document whatever steps are necessary to get your application up and running.

### Quick summary###
* Version 1.0
* Adapter for recyclerview that bind model class to the corresponding view. Hides complexity and easy to use.


### How To Use###


* Configuration
	Add VbAdapter.java to the project

* How to run 
* Create viewholder extends from RecyclerView.ViewHolder and initialize views.
	```
	@VbAnnotation(resourceId = R.layout.inner_layout)
	class ViewHolder extends RecyclerView.ViewHolder {
    		@VbField(mappingId = "name")
    		public TextView title;
    		public ViewHolder(View itemView) {
        		super(itemView);
        		title = (TextView) itemView.findViewById(R.id.title);
    			}
	}```

* Create model extends from VbModel
		
	```
	public class SampleModel extends VbModel {
	   @VbField(mappingId = "name")
    	   String name;
	   @VbField(mappingId = "desc")
           String desc;
	}```
	
		
* mappingId denotes the unique mapping id between model class object and view. This should be same in model class  field and its corrosponding viewholder class field.

	Activity
	```
	VbAdapter<SampleModel, ViewHolder> adapter = new VbAdapter<SampleModel, ViewHolder>(this, SampleModel.class, ViewHolder.class);
        recyclerView.setAdapter(adapter);
        adapter.setList(list);```



