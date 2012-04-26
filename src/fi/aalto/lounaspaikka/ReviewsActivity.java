package fi.aalto.lounaspaikka;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
	}
}
