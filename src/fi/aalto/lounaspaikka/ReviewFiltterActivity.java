package fi.aalto.lounaspaikka;




import fi.aalto.lounaspaikka.filters.filterArrayObject;
import fi.aalto.lounaspaikka.objectfiles.ObjectsContainer;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ReviewFiltterActivity extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.reviewfilteradd);

	
	
		
		Button show = (Button) findViewById(R.id.addweight);
	
		show.setOnClickListener(new View.OnClickListener() 
		{
			public void onClick(View view)
			{ 
				EditText weightnameE = (EditText) findViewById(R.id.WeightnameField);
				EditText weightE = (EditText) findViewById(R.id.weightField);
			String  weightname= weightnameE.getText().toString();
			String weights = weightE.getText().toString();
			int jee=0;
			jee=jee;
			try
			{

			int weight=	Integer.parseInt(weights);
			if (weight>=(-100) && weight<=100 && weightname!="") {
				filterArrayObject filterr= new filterArrayObject();
				filterr.filter=weightname;
				filterr.filterweight=weight;
				ObjectsContainer.filter.add(filterr);
				Toast.makeText(getApplicationContext(), "Weight has been added", Toast.LENGTH_LONG).show();
			}
			Toast.makeText(getApplicationContext(), "Wrong input", Toast.LENGTH_LONG).show();
			
			
			
			}
			catch(NumberFormatException explanation){

				Toast.makeText(getApplicationContext(), "Weight wasn't integer", Toast.LENGTH_LONG).show();
			}
			
			
			}
		});
	
	
	
	
	}
}


