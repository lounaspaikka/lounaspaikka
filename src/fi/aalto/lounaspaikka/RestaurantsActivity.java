package fi.aalto.lounaspaikka;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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


public class RestaurantsActivity extends ListActivity {
    
	private LayoutInflater mInflater;
	private Vector<RowData> data;
	RowData rd;
	Random generator ;
	
	ArrayList<String> title = new ArrayList<String>();
	ArrayList<String> detail = new ArrayList<String>();

	private Integer[] imgid = {
			 R.drawable.res_icon_1,R.drawable.res_icon_2,R.drawable.res_icon_3,
			  R.drawable.res_icon_4,  R.drawable.res_icon_5,  R.drawable.res_icon_6,  R.drawable.res_icon_7
	};
	private Integer[] imgid2 = {
			 R.drawable.res_1,R.drawable.res_2,R.drawable.res_3,
			  R.drawable.res_4,  R.drawable.res_5,  R.drawable.res_6,  R.drawable.res_7
	};
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.restaurants);
        generator = new Random();
        
        for (int i=0;i<ObjectsContainer.restaurants.size();i++){
        	if (ObjectsContainer.restaurants.get(i).campus.equals(CampusActivity.nowCampus)){
        		title.add(ObjectsContainer.restaurants.get(i).name);
        		detail.add(ObjectsContainer.restaurants.get(i).location.address);
        	}
        }
        
        mInflater = (LayoutInflater) getSystemService(
        		Activity.LAYOUT_INFLATER_SERVICE);
        		data = new Vector<RowData>();
        		int j = 0;
        		for(int i=0;i<title.size();i++){
        			try {
        			 	rd = new RowData(i, title.get(i),detail.get(i), j);
        			    }
        			catch (ParseException e) {
        			    e.printStackTrace();
        			   }
        			   data.add(rd);
        			   j++;
	       			   if(j >= (imgid.length- 1))
	       			 		j = 0;
        		}
        	   CustomAdapter adapter = new CustomAdapter(this, R.layout.list, R.id.title, data);
               setListAdapter(adapter);
        	   getListView().setTextFilterEnabled(true);
    }
    public void onListItemClick(ListView parent, View v, int position,long id) {        	
		        Intent intent = new Intent(this, RestaurantActivity.class);
		        intent.putExtra("restName", data.get(position).mTitle);
		        
		        int pos = data.get(position).imageNextId;
		        int picId = imgid2[pos];
		        intent.putExtra("resImageId", picId);
		        this.startActivity(intent);
	}
    private int getRandPosition(){
    	int rnd = generator.nextInt(imgid.length);
    	return rnd;
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
		       TextView title = null;
		       TextView detail = null;
		       ImageView i11=null;
		       ImageView i22=null;
		       RowData rowData= getItem(position);
		       
		       if(null == convertView){
		            convertView = mInflater.inflate(R.layout.list, null);
		       }
	           title = (TextView) convertView.findViewById(R.id.title);
	           title.setText(rowData.mTitle);
	           
	           detail = (TextView) convertView.findViewById(R.id.detail);
	           detail.setText(rowData.mDetail);                                                     

	           i11 = (ImageView) convertView.findViewById(R.id.img);
	          // i11.setImageResource(imgid[rowData.imageNextId]);
	           i11.setImageBitmap(DecodeBitmaps.decodeSampledBitmapFromResource(getResources(), imgid[rowData.imageNextId], 80, 80));
	           
	           i22 = (ImageView) convertView.findViewById(R.id.img_arrow);
	          // i22.setImageResource(R.drawable.arrow);
	           i22.setImageBitmap(DecodeBitmaps.decodeSampledBitmapFromResource(getResources(), R.drawable.arrow, 30, 30));
//	           
	           return convertView;
	      	}
	   }
}