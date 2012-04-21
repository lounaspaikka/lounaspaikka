package fi.aalto.lounaspaikka;

import java.util.Vector;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;
import fi.aalto.lounaspaikka.objectfiles.ObjectsContainer;

public class CampusActivity extends Activity{
	static String nowCampus = "Otaniemi";
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.campus);
		Spinner spinner = (Spinner) findViewById(R.id.campusSpinner);
		Vector<String> campusName = new Vector<String>();
		String temp;
		for (int i=0;i<ObjectsContainer.restaurants.size();i++){
			temp=ObjectsContainer.restaurants.get(i).campus;
			if (!campusName.contains(temp))
				campusName.add(temp);
		}
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,campusName);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);
		spinner.setSelection(campusName.indexOf(nowCampus));
		spinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){
			@Override
			public void onItemSelected(AdapterView arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				if (!nowCampus.equals(arg0.getSelectedItem().toString())){
					nowCampus = arg0.getSelectedItem().toString();
					Toast.makeText(getApplicationContext(), "Campus changed to "+nowCampus, Toast.LENGTH_LONG).show();
				}
			}

			@Override
			public void onNothingSelected(AdapterView arg0) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), "Please select a campus", Toast.LENGTH_LONG).show();
			}
		});
	}
}
