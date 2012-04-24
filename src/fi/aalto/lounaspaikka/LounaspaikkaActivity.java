package fi.aalto.lounaspaikka;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import fi.aalto.lounaspaikka.JsonRetrievalAndTransform.RetrivedDataAndGenerateObject;
import fi.aalto.lounaspaikka.objectfiles.db.RestaurantDbEntity;
import fi.aalto.lounaspaikka.objectfiles.db.RestaurantsDataSource;
 


public class LounaspaikkaActivity extends Activity {
	 private RestaurantsDataSource dataSource;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		
		
		dataSource = new RestaurantsDataSource(this);
		
		//run data loading in different thread to avoid UI locking
		 new Thread(new Runnable() {
			    public void run() {    
			    	loadData();
			    }
			  }).start();
	
 	 
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {

		   public void run() {
			   Intent intent = new Intent(LounaspaikkaActivity.this, DashboardActivity.class);
			  // intent.putExtra("parse", new MyParcelable("1","Mike","6"));
			   LounaspaikkaActivity.this.startActivity(intent);
		   }

		}, 2000);
 
	}
	
	/**
	 * download data  from the Internet
	 */
	private void getDataFromWeb(){
		RetrivedDataAndGenerateObject data = new  RetrivedDataAndGenerateObject();
		data.fechData();
		data.generateObjects();
		data.Storetomem();
		saveDataToLocalStorage(data.getJsonData());
		Log.w("log1","loaded from web");
	}
	
	/**
	 * 
	 * @param data
	 */
	private void saveDataToLocalStorage(String data){
		
		 	dataSource = new RestaurantsDataSource(this);
	        dataSource.open();
	        
	        String date;
	        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	        date = dateFormat.format(new Date());
	        dataSource.SaveData(data, date);
	        dataSource.close();
	}
	
	/**
	 * Tries to load data from local storage first. If not data available gets it from the Internet
	 */
	private void  loadData(){
		
		RestaurantDbEntity restDbEntity = loadDataFromLocalStorage();
		//incorrect data in local storage
		if (restDbEntity == null)
			{getDataFromWeb();
			return;
			}
		
		//Check if data up to date
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(restDbEntity.getDate());
		Calendar todayCalendar = Calendar.getInstance();
		todayCalendar.setTime(new Date());
		if( calendar.get(Calendar.WEEK_OF_YEAR) != todayCalendar.get(Calendar.WEEK_OF_YEAR)){ 
			getDataFromWeb();
			return;
		}
		
		//Pass json from local storage to generate objects
		RetrivedDataAndGenerateObject data = new  RetrivedDataAndGenerateObject();
		data.setJsonData(restDbEntity.getRestaurantData());
		data.generateObjects();
		data.Storetomem();
		Log.w("log1","loaded from local storage");
		
	}
	
	/**
	 * 
	 * @return
	 */
	private RestaurantDbEntity loadDataFromLocalStorage(){
		RestaurantDbEntity restDbEntity = new RestaurantDbEntity();
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		String date;
	        
		dataSource = new RestaurantsDataSource(this);
        dataSource.open();
        
        dataSource.getAllData();
        List<String> values = dataSource.getDataList();
        List<String> dates = dataSource.getDates();
        Log.w("log1", values.size() + " size");
        dataSource.close();
        
        if(values.isEmpty() || dates.isEmpty())
        	return null;
        
        try {
			restDbEntity.setDate(dateFormat.parse(dates.get(0)));
			
		} catch (ParseException e) {
			return null;
		}
        
        restDbEntity.setRestaurantData(values.get(0));
        
        return restDbEntity;
        
        
	}
		
}


