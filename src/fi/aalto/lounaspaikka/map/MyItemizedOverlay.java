package fi.aalto.lounaspaikka.map;

import java.util.ArrayList;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.widget.Toast;

import com.google.android.maps.ItemizedOverlay;
import com.google.android.maps.OverlayItem;

import fi.aalto.lounaspaikka.MyMapActivity;
import fi.aalto.lounaspaikka.RestaurantActivity;

public class MyItemizedOverlay extends ItemizedOverlay {
	 private Context ctx;
	 private MyMapActivity mapActivity;

public void setMapActivity(MyMapActivity mapActivity) {
		this.mapActivity = mapActivity;
	}


private ArrayList<OverlayItem> myOverlays = new ArrayList();

public MyItemizedOverlay(Drawable defaultMarker, Context cont) {
	super(boundCenterBottom(defaultMarker));
	this.ctx = cont;
}


@Override
protected OverlayItem createItem(int i) {
// TODO Auto-generated method stub
return myOverlays.get(i);
}

public void addOverlay(OverlayItem overlay) {
myOverlays.add(overlay);
populate();
}

	@Override
	public int size() {
	// TODO Auto-generated method stub
		return myOverlays.size();
	}


@Override
protected boolean onTap(int index){
	String name = myOverlays.get(index).getTitle().toString();
	mapActivity.showRestaurantInfo(name);
//	 Toast.makeText(this.ctx,
//			 myOverlays.get(index).getTitle().toString()+
//			 ", Latitude: "+myOverlays.get(index).getPoint().getLatitudeE6(),
//			 Toast.LENGTH_SHORT).show();
     return super.onTap(index);   
}



}