package fi.aalto.lounaspaikka;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;

import com.google.gson.Gson;

import fi.aalto.lounaspaikka.objectfiles.ObjectsContainer;
import android.app.Activity;
import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.Toast;

public class ReviewsActivity extends TabActivity{
	TabHost.TabSpec spec1, spec2, spec3;
	Intent myIntent1, myIntent2, myIntent3;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.reviews);
		Resources res = getResources();
		TabHost tabHost = getTabHost();
		myIntent1 = new Intent().setClass(this, ReviewFilteredMenuActivity.class);
		spec1 = tabHost.newTabSpec("filteredMenu")
				.setIndicator("Filtered Menu",res.getDrawable(R.drawable.ic_tab_equalizer))
				.setContent(myIntent1);
	    tabHost.addTab(spec1);

	    myIntent2 = new Intent().setClass(this, ReviewFiltterActivity.class);
		spec2 = tabHost.newTabSpec("filter")
				.setIndicator("Filter Settings",res.getDrawable(R.drawable.ic_tab_filter))
				.setContent(myIntent2);
		tabHost.addTab(spec2);
	    
		myIntent3 = new Intent().setClass(this, ReviewshowfiltersActivity.class);
		spec3 = tabHost.newTabSpec("showFilter")
				.setIndicator("Show Filters",res.getDrawable(R.drawable.ic_tab_tiles))
				.setContent(myIntent3);
		tabHost.addTab(spec3);
		tabHost.setCurrentTab(1);
		
		tabHost.setOnTabChangedListener(new OnTabChangeListener(){
			@Override
			public void onTabChanged(String tabId) {
			    if(spec1.getTag().equals(tabId)) {
			    	ReviewFilteredMenuActivity.showFilteredMenu();
			    }
			    else if(spec3.getTag().equals(tabId)) {
			    	ReviewshowfiltersActivity.showFilter();
			    }
			}});
	}
}
