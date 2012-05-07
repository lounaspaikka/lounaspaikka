package fi.aalto.lounaspaikka;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Vector;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Spinner;
import android.widget.Toast;
import fi.aalto.lounaspaikka.objectfiles.ObjectsContainer;

public class CampusActivity extends Activity{
	private RadioGroup radGroup;
    private RadioButton radButton;
	
	public static String nowCampus="Otaniemi";
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.campus);
		
		//Spinner spinner = (Spinner) findViewById(R.id.campusSpinner);
		radGroup = (RadioGroup) findViewById(R.id.radGroup);
		
		Vector<String> campusName = new Vector<String>();
		String temp;
		for (int i=0;i<ObjectsContainer.restaurants.size();i++){
			temp=ObjectsContainer.restaurants.get(i).campus;
			if (!campusName.contains(temp))
			{
				campusName.add(temp);
				radButton = new RadioButton(getApplicationContext());
				radGroup.addView(radButton);
				radButton.setText(temp);
				radButton.setTextColor(0xFF000000);			
			}
		}
		String match;
		//LoadCampus();
		for(int j=0; j<radGroup.getChildCount(); j++) {
            RadioButton loadbtn = (RadioButton)radGroup.getChildAt(j);
            match = (String)loadbtn.getText();
            
            if(match.equals(nowCampus)) {
        		radGroup.check(loadbtn.getId());
        		//Toast.makeText(getApplicationContext(), nowCampus, Toast.LENGTH_LONG).show();
            }
        }
		
		      
	    radGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() 
	    {
	        public void onCheckedChanged(RadioGroup group, int checkedId) {
	            // checkedId is the RadioButton selected
	        	for(int i=0; i<group.getChildCount(); i++) {
	                   RadioButton btn = (RadioButton)group.getChildAt(i);
	                   if(btn.getId() == checkedId) {
	                        
	                        nowCampus = (String)btn.getText();
	        				Toast.makeText(getApplicationContext(), "Campus changed to "+nowCampus, Toast.LENGTH_LONG).show();
	        				FileOutputStream fOut = null;
		    		        OutputStreamWriter osw = null;

		    		        try{

		    		        fOut = openFileOutput("campus.txt", Context.MODE_PRIVATE);

		    		        osw = new OutputStreamWriter(fOut);
		    		        
		    		        osw.write(nowCampus);
		    		        
		    		        osw.close();
		    		        fOut.close();

		    		        }catch(Exception e){

		    		        e.printStackTrace(System.err);

		    		        }
	        				
	        				return;
	                 }
	            }
	        	
	        }
	    });
		//ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,campusName);
		//adapter.setDropDownViewResource(android.R.layout.simple_list_item_checked);
	
		
		//spinner.setAdapter(adapter);
		//spinner.setSelection(campusName.indexOf(nowCampus));
		//spinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
			//@Override
			//public void onItemSelected(AdapterView arg0, View arg1,
			//		int arg2, long arg3) {
				// TODO Auto-generated method stub
			//	if (!nowCampus.equals(arg0.getSelectedItem().toString())){
			//		nowCampus = arg0.getSelectedItem().toString();
			//		Toast.makeText(getApplicationContext(), "Campus changed to "+nowCampus, Toast.LENGTH_LONG).show();
			//	}
			//}

			//@Override
			//public void onNothingSelected(AdapterView arg0) {
				// TODO Auto-generated method stub
			//	Toast.makeText(getApplicationContext(), "Please select a campus", Toast.LENGTH_LONG).show();
			//}
		//});
	}
	public void LoadCampus(){
		FileInputStream fIn = null;

        InputStreamReader isr = null;

        try{
        fIn = openFileInput("campus.txt");
        isr = new InputStreamReader(fIn);
        BufferedReader reader = new BufferedReader(isr);
        String line = null;
       
        line = reader.readLine();
        nowCampus = line;
        Toast.makeText(getApplicationContext(), "Preference Loaded", Toast.LENGTH_LONG).show();
        isr.close();
        fIn.close();
                
        }catch(IOException e){

        e.printStackTrace(System.err);

        }
	}
}
