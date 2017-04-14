# Under Development

This README would normally document whatever steps are necessary to get your application up and running.

### Quick summary
* Version 1.0
* Adapter for recyclerview that bind model class to the corresponding view. Hides complexity and easy to use.


### How To Use


* Configuration
	

* How to run 

* Create model extends from EasyModel
		
	```
	public class SampleModel extends EasyModel{
	   @EasyField(mappingId = "name")
    	   String name;
	   @EasyField(mappingId = "desc")
           String desc;
	}


* In recyclerview item view you have to set tag that should be same as mappingid decleared above
	```
	  <TextView
                android:id="@+id/title"
                android:layout_width="wrap_content"
                android:layout_height="wrap_content"
               android:tag="title"  //should be same as mappingId declared in model class
	      />
	



	
		
* mappingId denotes the unique mapping id between model class object and view. This should be same in model class  field and its corrosponding viewholder class field.

	Activity
	```
	EasyAdapter.with(this)
                .resource(R.layout.inner_layout)
                .items(list, SampleModel.class)
                .into(recyclerView);



