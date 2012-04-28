package fi.aalto.lounaspaikka;


//import java.util.Calendar;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

import fi.aalto.lounaspaikka.objectfiles.ObjectsContainer;
import fi.aalto.lounaspaikka.objectfiles.Restaurant;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class RestaurantActivity extends Activity{
	
	static boolean Toggle=true;
	int rnumber=0;
	ArrayList<String> fav = new ArrayList<String>();
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.restaurant);
		
		LoadFav();
		
		ImageView restIcon = (ImageView) findViewById(R.id.restIcon);
		TextView restInfo = (TextView) findViewById(R.id.restInfo);
		Button button = (Button) findViewById(R.id.favButton);
		
		Intent myIntent = getIntent();
		String restName = myIntent.getStringExtra("restName");
		//TO BE CHANGED TO SPECIFIC ICON
		restIcon.setImageResource(R.drawable.respic);
		Restaurant nowRest = new Restaurant();
		
		
		for (int i=0;i<ObjectsContainer.restaurants.size();i++)
			if (ObjectsContainer.restaurants.get(i).name.equals(restName)){
				nowRest = ObjectsContainer.restaurants.get(i);
				rnumber=i;
				break;
			}
		for (int k=0;k<fav.size();k++)
		{
			if (fav.get(k).equals(ObjectsContainer.restaurants.get(rnumber).name))
			{
				Toggle = false;
				button.setText("Remove Favourite");
			}
		}
		if (nowRest.name!=null){
			restInfo.append("Name: "+nowRest.name+"\n");
			restInfo.append("Campus: "+nowRest.campus+"\n");
			restInfo.append("Address: "+nowRest.location.address+"\n");
			restInfo.append("Opening hours: \n");
			int opensc=0;
			int openlistsize=nowRest.isopen.listOfDays.size();
			while (openlistsize>opensc) {
				restInfo.append(opens(opensc,rnumber));
				opensc++;
			}

			/*	if (nowRest.isopen.listOfDays.get(0).opens.equals(""))
				restInfo.append("Mon: no data\n");
			else
				restInfo.append("Mon: "+nowRest.isopen.listOfDays.get(0).opens+" - "+nowRest.isopen.listOfDays.get(0).closes+"\n");
			if (nowRest.isopen.listOfDays.get(0).opens.equals(""))
				restInfo.append("Tue: no data\n");
			else
				restInfo.append("Tue: "+nowRest.isopen.listOfDays.get(1).opens+" - "+nowRest.isopen.listOfDays.get(1).closes+"\n");
			if (nowRest.isopen.listOfDays.get(0).opens.equals(""))
				restInfo.append("Wed: no data\n");
			else
				restInfo.append("Wed: "+nowRest.isopen.listOfDays.get(2).opens+" - "+nowRest.isopen.listOfDays.get(2).closes+"\n");
			if (nowRest.isopen.listOfDays.get(0).opens.equals(""))
				restInfo.append("Thu: no data\n");
			else
				restInfo.append("Thu: "+nowRest.isopen.listOfDays.get(3).opens+" - "+nowRest.isopen.listOfDays.get(3).closes+"\n");
			if (nowRest.isopen.listOfDays.get(0).opens.equals(""))
				restInfo.append("Fri: no data\n");
			else
				restInfo.append("Fri: "+nowRest.isopen.listOfDays.get(4).opens+" - "+nowRest.isopen.listOfDays.get(4).closes+"\n");
			if (nowRest.isopen.listOfDays.get(0).opens.equals(""))
				restInfo.append("Sat: no data\n");
			else
				restInfo.append("Sat: "+nowRest.isopen.listOfDays.get(5).opens+" - "+nowRest.isopen.listOfDays.get(5).closes+"\n");
			if (nowRest.isopen.listOfDays.get(0).opens.equals(""))
				restInfo.append("Sun: no data\n");
			else
				restInfo.append("Sun: "+nowRest.isopen.listOfDays.get(6).opens+" - "+nowRest.isopen.listOfDays.get(6).closes+"\n");
		}*/
		button.setOnClickListener(new View.OnClickListener() {
	    public void onClick(View v) {
	                 // Perform action on click
			
	    	Button temp = (Button) findViewById(R.id.favButton);
	    	if (Toggle)
	    	{
	    		SaveFav(ObjectsContainer.restaurants.get(rnumber).name);
	    		
	    		temp.setText("Remove Favourite");
	    		Toggle = false;
	    	}
	    	else
	    	{
	    	
	    		for (int j=0;j<fav.size();j++)
	    		{
	    			if (ObjectsContainer.restaurants.get(rnumber).name.equals(fav.get(j)))
	    			{
	    				fav.remove(j);
	    				FileOutputStream fOut = null;
	    		        OutputStreamWriter osw = null;

	    		        try{

	    		        fOut = openFileOutput("fav.txt", Context.MODE_PRIVATE);

	    		        osw = new OutputStreamWriter(fOut);
	    		        
	    		        for (int k=0; k<fav.size();k++)
	    		        {
	    		        	osw.write(fav.get(k) + "\n");
	    		        //osw.append("Alvar" + "\n");
	    		        //osw.append("Main building" + "\n");
	    		        }
	    		        osw.close();
	    		        Toast.makeText(getBaseContext(), "Favourite removed",Toast.LENGTH_SHORT).show();
	    		        fOut.close();

	    		        }catch(Exception e){

	    		        e.printStackTrace(System.err);

	    		        }	
	    			}
	    		}
	    		temp.setText("Add Favourite");
	    		Toggle = true;
	    	}
	    	//LoadFav();
	    	//TextView test = (TextView) findViewById(R.id.restInfo);
	    	//test.setText(fav.get(2));
	    }
		});
		
	  }
	}

	private String opens(int day, int rnumber) //rnumber is restaurant number
	{
		String opens="";
		String closes="";
		String openclose="";
		if (ObjectsContainer.restaurants.get(rnumber).isopen.listOfDays.get(day).opens.equals("") || ObjectsContainer.restaurants.get(rnumber).isopen.listOfDays.get(day).closes.equals("")  ) {
			openclose= "Hours can't be retrived" + System.getProperty("line.separator");
		} 
		else 
		{
			opens=ObjectsContainer.restaurants.get(rnumber).isopen.listOfDays.get(day).opens;
			closes= ObjectsContainer.restaurants.get(rnumber).isopen.listOfDays.get(day).closes;
			openclose = opens + "-" + closes;
		}
		if (day==0) {
			openclose= "Monday: " +openclose+System.getProperty("line.separator"); 	
		}
		 else if (day==1) {		
			 openclose= "Tuesday: " +openclose+System.getProperty("line.separator"); 	
		 }
		else if (day==2) {
			openclose= "Wednesday: " +openclose+System.getProperty("line.separator"); 		
		}
		else if (day==3) {
			openclose= "Thursday: " +openclose+System.getProperty("line.separator"); 	
		}
		else if (day==4) {
			openclose= "Friday: " +openclose+System.getProperty("line.separator"); 	
		}
		else if (day==5) {
			openclose= "Saturday: " +openclose+System.getProperty("line.separator"); 		
		}
		else if (day==6) {
			openclose= "Sunday: " +openclose+System.getProperty("line.separator"); 	
		}
		return openclose;
	}

	public void SaveFav(String Fav){
		FileOutputStream fOut = null;
        OutputStreamWriter osw = null;

        try{

        fOut = openFileOutput("fav.txt", Context.MODE_APPEND);

        osw = new OutputStreamWriter(fOut);
        
        osw.write(Fav + "\n");
        //osw.append("Alvar" + "\n");
        //osw.append("Main building" + "\n");
        osw.close();
        Toast.makeText(getBaseContext(), "Favourite added",Toast.LENGTH_SHORT).show();
        fOut.close();

        }catch(Exception e){

        e.printStackTrace(System.err);

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
        //Toast.makeText(getBaseContext(), "Favourite Loaded",Toast.LENGTH_SHORT).show();
        fIn.close();
        
        
        }catch(IOException e){

        e.printStackTrace(System.err);

        }
	
	}
	
}
