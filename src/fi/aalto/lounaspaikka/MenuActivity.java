package fi.aalto.lounaspaikka;

import java.util.ArrayList;
import java.util.Calendar;
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
import fi.aalto.lounaspaikka.objectfiles.ObjectsContainer;
 


public class MenuActivity extends ListActivity
{
	
	private LayoutInflater mInflater;
	private Vector<RowData> data;
	RowData rd;
	ArrayList<String> title = new ArrayList<String>();
	ArrayList<String> detail = new ArrayList<String>();
	
	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.menu);
		setContentView(R.layout.restaurants);
		//String todaysmenu=""; 
		Calendar today = Calendar.getInstance();
		int day = today.get(Calendar.DAY_OF_WEEK);
		//sunday is first day saturday 7th
		//int restaurantcount= ObjectsContainer.restaurants.size();
		//int rcounter=0;
		int openday=day-2;
		if (openday==-1 ) {
			openday =6;
			}
	
		for (int i=0;i<ObjectsContainer.restaurants.size();i++){
        	if (ObjectsContainer.restaurants.get(i).campus.equals(CampusActivity.nowCampus)){
        		if (day==1)
				{
        			for (int j=0;j<ObjectsContainer.restaurants.get(i).weeksmenu.sunday.daysmenu.size();j++){
        				title.add(ObjectsContainer.restaurants.get(i).weeksmenu.sunday.daysmenu.get(j).meal);
        				detail.add(ObjectsContainer.restaurants.get(i).name);
        			}
				}
				else if (day==2) 
				{
					for (int j=0;j<ObjectsContainer.restaurants.get(i).weeksmenu.monday.daysmenu.size();j++){
        				title.add(ObjectsContainer.restaurants.get(i).weeksmenu.monday.daysmenu.get(j).meal);
						detail.add(ObjectsContainer.restaurants.get(i).name);
					}
				}
				else if (day==3) 
				{
					for (int j=0;j<ObjectsContainer.restaurants.get(i).weeksmenu.tuesday.daysmenu.size();j++){
        				title.add(ObjectsContainer.restaurants.get(i).weeksmenu.tuesday.daysmenu.get(j).meal);
						detail.add(ObjectsContainer.restaurants.get(i).name);
					}
				}
				else if (day==4) 
				{
					for (int j=0;j<ObjectsContainer.restaurants.get(i).weeksmenu.wednesday.daysmenu.size();j++){
        				title.add(ObjectsContainer.restaurants.get(i).weeksmenu.wednesday.daysmenu.get(j).meal);
						detail.add(ObjectsContainer.restaurants.get(i).name);
					}
				}
				else if (day==5) 
				{
					for (int j=0;j<ObjectsContainer.restaurants.get(i).weeksmenu.thursday.daysmenu.size();j++){
        				title.add(ObjectsContainer.restaurants.get(i).weeksmenu.thursday.daysmenu.get(j).meal);
						detail.add(ObjectsContainer.restaurants.get(i).name);
					}
				}
				else if (day==6) 
				{
					for (int j=0;j<ObjectsContainer.restaurants.get(i).weeksmenu.friday.daysmenu.size();j++){
        				title.add(ObjectsContainer.restaurants.get(i).weeksmenu.friday.daysmenu.get(j).meal);
						detail.add(ObjectsContainer.restaurants.get(i).name);
					}
				}
				else if (day==7) 
				{
					for (int j=0;j<ObjectsContainer.restaurants.get(i).weeksmenu.saturday.daysmenu.size();j++){
        				title.add(ObjectsContainer.restaurants.get(i).weeksmenu.saturday.daysmenu.get(j).meal);
						detail.add(ObjectsContainer.restaurants.get(i).name);
					}
				}
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
		
		
		
		/*while (restaurantcount>rcounter) 
		{
			String opens="closed";
			String closes="";
			try {
			 opens  = ObjectsContainer.restaurants.get(rcounter).isopen.listOfDays.get(openday).opens;
			 closes = ObjectsContainer.restaurants.get(rcounter).isopen.listOfDays.get(openday).closes;
			} catch (Throwable t) {
			// maybe warning later on
			}
			
			
			/*
			todaysmenu =todaysmenu + '\n'+ '\n'+ ObjectsContainer.restaurants.get(rcounter).name + " " + opens + " - " + closes  + '\n' + '\n';
			int mcounter=0;
			int menucount=0;
			if (day==1)
			{
				menucount =ObjectsContainer.restaurants.get(rcounter).weeksmenu.sunday.daysmenu.size();
			}
			else if (day==2) 
			{
				menucount =ObjectsContainer.restaurants.get(rcounter).weeksmenu.monday.daysmenu.size();	
			}
			else if (day==3) 
			{
				menucount =ObjectsContainer.restaurants.get(rcounter).weeksmenu.tuesday.daysmenu.size();	
			}
			else if (day==4) 
			{
				menucount = ObjectsContainer.restaurants.get(rcounter).weeksmenu.wednesday.daysmenu.size();	
			}
			else if (day==5) 
			{
				menucount = ObjectsContainer.restaurants.get(rcounter).weeksmenu.thursday.daysmenu.size();	
			}
			else if (day==6) 
			{
				menucount = ObjectsContainer.restaurants.get(rcounter).weeksmenu.friday.daysmenu.size();	
			}
			else if (day==7) 
			{
				menucount = ObjectsContainer.restaurants.get(rcounter).weeksmenu.saturday.daysmenu.size();	
			}
			while (menucount>mcounter)
			{
				if (day==1)
				{
					todaysmenu = todaysmenu +ObjectsContainer.restaurants.get(rcounter).weeksmenu.sunday.daysmenu.get(mcounter).meal + '\n' ;
				}
				else if (day==2) 
				{
					todaysmenu = todaysmenu +ObjectsContainer.restaurants.get(rcounter).weeksmenu.monday.daysmenu.get(mcounter).meal+ '\n' ;	
				}
				else if (day==3) 
				{
					todaysmenu = todaysmenu +ObjectsContainer.restaurants.get(rcounter).weeksmenu.tuesday.daysmenu.get(mcounter).meal + '\n' ;	
				}
				else if (day==4) 
				{
					todaysmenu = todaysmenu +ObjectsContainer.restaurants.get(rcounter).weeksmenu.wednesday.daysmenu.get(mcounter).meal+'\n' ;	
				}
				else if (day==5) 
				{
					todaysmenu = todaysmenu +ObjectsContainer.restaurants.get(rcounter).weeksmenu.thursday.daysmenu.get(mcounter).meal+ '\n' ;	
				}
				else if (day==6) 
				{
					todaysmenu = todaysmenu +ObjectsContainer.restaurants.get(rcounter).weeksmenu.friday.daysmenu.get(mcounter).meal+ '\n';	
				}
				else if (day==7) 
				{
					todaysmenu = todaysmenu +ObjectsContainer.restaurants.get(rcounter).weeksmenu.saturday.daysmenu.get(mcounter).meal+ '\n' ;	
				}
				mcounter++;
			}
			rcounter++;
		}
		TextView tv;
		tv = (TextView)findViewById(R.id.textView1);

		tv.setMovementMethod(new ScrollingMovementMethod());
		tv.setText(todaysmenu); */
	
	}
	
	 public void onListItemClick(ListView parent, View v, int position,long id) {        	
//		   Toast.makeText(getApplicationContext(), "You have selected "
//	                    +(position+1)+"th item",  Toast.LENGTH_SHORT).show();
		   
		        Intent intent = new Intent(this, RestaurantActivity.class);
		        intent.putExtra("restName", data.get(position).mTitle);
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
