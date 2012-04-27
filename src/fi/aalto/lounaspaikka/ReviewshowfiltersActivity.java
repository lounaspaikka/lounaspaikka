package fi.aalto.lounaspaikka;

import fi.aalto.lounaspaikka.objectfiles.ObjectsContainer;
import android.app.Activity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;

public class ReviewshowfiltersActivity extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.showfiltters);
		int filtersize=ObjectsContainer.filter.size();
		int counter=0;
		String filtertxt="";
		while (filtersize>counter) 
		{
			filtertxt=filtertxt+ "Weight name: " +ObjectsContainer.filter.get(counter).filter + "\n";
			filtertxt=filtertxt+ "Weight: " +ObjectsContainer.filter.get(counter).filterweight+ "\n";
			filtertxt=filtertxt+ "\n";
			counter++;
		}
		
		
		TextView tv;
		tv = (TextView)findViewById(R.id.textView1);
		tv.setMovementMethod(new ScrollingMovementMethod());
		tv.setText(filtertxt); 
	}
}