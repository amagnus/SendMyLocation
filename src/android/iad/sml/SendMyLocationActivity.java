package android.iad.sml;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SendMyLocationActivity extends Activity implements OnClickListener {
	
	public static String tag = SendMyLocationActivity.class.getName();
	private EditText numberEditText;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Button btn = (Button) findViewById(R.id.button1);
        btn.setOnClickListener(this);
        numberEditText = (EditText) findViewById(R.id.editText1);
    }
    
    public void onClick (View v) {
    	String phoneNumber = numberEditText.getText().toString();
    	Log.i(tag, "Number:" + phoneNumber);
    	
    	if(phoneNumber.length() == 0) {
    		Toast.makeText(this, "Enter a real number", Toast.LENGTH_SHORT).show();
    	}
    	
    	else {
    		// Get current location
    		Location location = getCurrentLocation();

    		// Send SMS with location
        	if (location != null) {
            	String s = location.toString();
            	Log.i(tag, s);
            		
            	int lat = (int) location.getLatitude();
            	int lng = (int) location.getLongitude();
            		
            	String Message = "geo: " + lat + "," + lng;
            	Log.i(tag, Message);
            		
            	SmsManager manager = SmsManager.getDefault();
            	manager.sendTextMessage(phoneNumber, null, Message, null, null);
        	}
    	}
    }
    
    protected Location getCurrentLocation() {
    	LocationManager manager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
    	Location result = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
    	// or NETWORK_PROVIDER
    	
    	return result;
    }
    
}