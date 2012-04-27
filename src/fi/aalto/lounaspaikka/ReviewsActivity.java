package fi.aalto.lounaspaikka;

import fi.aalto.lounaspaikka.objectfiles.ObjectsContainer;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ReviewsActivity extends Activity{
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.reviews);


		Button review = (Button) findViewById(R.id.button1);
		review.setOnClickListener(new View.OnClickListener() {

			public void onClick(View view)
			{
				Intent myIntent = new Intent(view.getContext(), ReviewFilteredMenuActivity.class);
				startActivityForResult(myIntent,0);
			}
		});

		Button settings = (Button) findViewById(R.id.button2);
		settings.setOnClickListener(new View.OnClickListener() {

			public void onClick(View view)
			{
				Intent myIntent = new Intent(view.getContext(), ReviewFiltterActivity.class);
				startActivityForResult(myIntent,0);
			}
		});

		Button showfilters = (Button) findViewById(R.id.button3);
		showfilters.setOnClickListener(new View.OnClickListener() {

			public void onClick(View view)
			{
				Intent myIntent = new Intent(view.getContext(), ReviewshowfiltersActivity.class);
				startActivityForResult(myIntent,0);
			}
		});

		Button clearfilters = (Button) findViewById(R.id.button4);
		clearfilters.setOnClickListener(new View.OnClickListener() {

			public void onClick(View view)
			{
				int filtersize= ObjectsContainer.filter.size();
				int counter=0;
				while (filtersize>counter) {
					ObjectsContainer.filter.remove(0);
					counter++;
				}
				Toast.makeText(getApplicationContext(), "Filters cleared ", Toast.LENGTH_LONG).show();
			}
		});



	}
}
