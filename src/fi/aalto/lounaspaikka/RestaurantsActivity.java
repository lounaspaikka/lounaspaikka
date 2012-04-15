package fi.aalto.lounaspaikka;


import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.net.ParseException;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;



import fi.aalto.lounaspaikka.R;
import fi.aalto.lounaspaikka.objectfiles.ObjectsContainer;


public class RestaurantsActivity extends ListActivity {
    
	private LayoutInflater mInflater;
	private Vector<RowData> data;
	RowData rd;

	ArrayList<String> title = new ArrayList<String>();
	ArrayList<String> detail = new ArrayList<String>();
	/*static final String[] title = new String[] { "Tuas-talo", 
		"Alvari","Elissa ",
		"Cantina","Elissa ",
		"Elissa","Alvari ",
		"Alvari"};

	static final String[] detail = new String[] { "1h 37m Shipping: $10.007m Shipping: $10.00","1h 39m Shipping: Free",
		"58m 6s Shipping: $10.00","59m 30s Shipping: $10.95",
		"58m 6s Shipping: $10.00","59m 30s Shipping: $10.95",
		"58m 6s Shipping: $10.00","59m 30s Shipping: $10.95"};*/

	/*private Integer[] imgid = {
			 R.drawable.respic,R.drawable.respic,R.drawable.respic,
			  R.drawable.respic,  R.drawable.respic,  R.drawable.respic,  R.drawable.respic,  R.drawable.respic
	};
	private Integer[] imgid2 = {
			 R.drawable.arrow, R.drawable.arrow, R.drawable.arrow, R.drawable.arrow,
			 R.drawable.arrow, R.drawable.arrow, R.drawable.arrow, R.drawable.arrow
	};*/
	
	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restaurants);

        for (int i=0;i<ObjectsContainer.restaurants.size();i++){
        	if (ObjectsContainer.restaurants.get(i).campus.equals(CampusActivity.nowCampus)){
        		title.add(ObjectsContainer.restaurants.get(i).name);
        		detail.add(ObjectsContainer.restaurants.get(i).location.address);
        	}
        }
        
        mInflater = (LayoutInflater) getSystemService(
        		Activity.LAYOUT_INFLATER_SERVICE);
        		data = new Vector<RowData>();
        		
        		for(int i=0;i<title.size();i++){
        			try {
        			 	rd = new RowData(i, title.get(i),detail.get(i), i);
        			    }
        			catch (ParseException e) {
        			    e.printStackTrace();
        			   }
        			   data.add(rd);
        		}
        	   CustomAdapter adapter = new CustomAdapter(this, R.layout.list, R.id.title, data);
               setListAdapter(adapter);
        	   getListView().setTextFilterEnabled(true);
    }
    public void onListItemClick(ListView parent, View v, int position,long id) {        	
//		   Toast.makeText(getApplicationContext(), "You have selected "
//	                    +(position+1)+"th item",  Toast.LENGTH_SHORT).show();
		   
		        Intent intent = new Intent(this, RestaurantActivity.class);
		        this.startActivity(intent);
		     
	}
	
	private class RowData {
	       protected int mId;
	       protected int imageNextId;
	       protected String mTitle;
	       protected String mDetail;
	       
	       RowData(int id, String title,String detail, int nextId){
		       mId=id;
		       imageNextId=nextId;
		       mTitle = title;
		       mDetail=detail;
	       }
	       
	       @Override
	       public String toString() {
	               return mId+" "+mTitle+" "+mDetail+" "+imageNextId;
	       }
	}
	
	
	private class CustomAdapter extends ArrayAdapter<RowData> {
		  public CustomAdapter(Context context, int resource, int textViewResourceId, List<RowData> objects) {               
			  super(context, resource, textViewResourceId, objects);
		  }
	  
	      @Override
	       public View getView(int position, View convertView, ViewGroup parent) {   
		       ViewHolder holder = null;
		       TextView title = null;
		       TextView detail = null;
		       ImageView i11=null;
		       ImageView i22=null;
		       RowData rowData= getItem(position);
		       if(null == convertView){
		            convertView = mInflater.inflate(R.layout.list, null);
		            holder = new ViewHolder(convertView);
		            convertView.setTag(holder);
		       }
	           holder = (ViewHolder) convertView.getTag();
	           title = holder.gettitle();
	           title.setText(rowData.mTitle);
	           
	           detail = holder.getdetail();
	           detail.setText(rowData.mDetail);                                                     

	           i11=holder.getImage();
	           //i11.setImageResource(imgid[rowData.mId]);
	           i11.setImageResource(R.drawable.respic);
	           
	           i22=holder.getImageNext();
	           //i22.setImageResource(imgid2[rowData.imageNextId]);
	           i22.setImageResource(R.drawable.arrow);
	           
	           return convertView;
	      	}
	      
	        private class ViewHolder {
	            private View mRow;
	            private TextView title = null;
	            private TextView detail = null;
	            private ImageView i11=null; 
	            private ImageView i22=null; 

	            public ViewHolder(View row) {
	            mRow = row;
	            }
	            
		        public TextView gettitle() {
		             if(null == title){
		                 title = (TextView) mRow.findViewById(R.id.title);
		                }
		            return title;
		         }     
		         public TextView getdetail() {
		             if(null == detail){
		                  detail = (TextView) mRow.findViewById(R.id.detail);
		                    }
		           return detail;
		         }
		         public ImageView getImage() {
		             if(null == i11){
		                  i11 = (ImageView) mRow.findViewById(R.id.img);
		             }
		                return i11;
		        }
		         public ImageView getImageNext() {
		             if(null == i22){
		                  i22 = (ImageView) mRow.findViewById(R.id.img_arrow);
		             }
		                return i22;
		        }
	        }
	   }
	
}