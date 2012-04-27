package fi.aalto.lounaspaikka;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Vector;

import fi.aalto.lounaspaikka.objectfiles.ObjectsContainer;

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
import android.widget.Toast;

public class FavoritesActivity extends ListActivity{
	private LayoutInflater mInflater;
	private Vector<RowData> data;
	RowData rd;
	ArrayList<String> title = new ArrayList<String>();
	ArrayList<String> detail = new ArrayList<String>();
	ArrayList<String> fav = new ArrayList<String>();
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		 super.onCreate(savedInstanceState);
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
			LoadFav();
			for (int i=0;i<ObjectsContainer.restaurants.size();i++){
				for(int k=0; k<fav.size();k++){
					if (ObjectsContainer.restaurants.get(i).name.equals(fav.get(k))){
					//if (ObjectsContainer.restaurants.get(i).campus.equals(CampusActivity.nowCampus)){
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
	        	   CustomAdapter adapter = new CustomAdapter(this, R.layout.menu_list, R.id.title, data);
	               setListAdapter(adapter);
	        	   getListView().setTextFilterEnabled(true);
			
			
		
		
		}
		
		 public void onListItemClick(ListView parent, View v, int position,long id) {        	
			 LoadFav();
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
			            convertView = mInflater.inflate(R.layout.menu_list, null);
			            holder = new ViewHolder(convertView);
			            convertView.setTag(holder);
			       }
		           holder = (ViewHolder) convertView.getTag();
		           title = holder.gettitle();
		           title.setText(rowData.mTitle);
		           
		           detail = holder.getdetail();
		           detail.setText(rowData.mDetail);                                                     

//		           i11=holder.getImage();
//		           //i11.setImageResource(imgid[rowData.mId]);
//		           i11.setImageResource(R.drawable.respic);
		           
//		           i22=holder.getImageNext();
//		           //i22.setImageResource(imgid2[rowData.imageNextId]);
//		           i22.setImageResource(R.drawable.arrow);
		           
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
//			         public ImageView getImage() {
//			            if(null == i11){
//			                  i11 = (ImageView) mRow.findViewById(R.id.img);
//			             }
//			                return i11;
//			        }
//			         public ImageView getImageNext() {
//			             if(null == i22){
//			                  i22 = (ImageView) mRow.findViewById(R.id.img_arrow);
//			             }
//			                return i22;
//			        }
		        }
		   }
		public void LoadFav(){
			FileInputStream fIn = null;

	        InputStreamReader isr = null;

	        try{
	        fIn = openFileInput("fav.txt");
	        isr = new InputStreamReader(fIn);
	        BufferedReader reader = new BufferedReader(isr);
	        String line = null;
	       
	        while ((line = reader.readLine()) != null) {
	        	fav.add(line);       	
	        }
	        isr.close();
	        Toast.makeText(getBaseContext(), "Favourite Loaded",Toast.LENGTH_SHORT).show();
	        fIn.close();
	        
	        
	        }catch(IOException e){

	        e.printStackTrace(System.err);

	        }
		
		}
}
