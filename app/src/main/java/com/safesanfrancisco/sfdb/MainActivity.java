package com.safesanfrancisco.sfdb;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.widget.TextView;

import android.util.Log;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import android.widget.Toast;

import com.safesanfrancisco.sfdb.mapPoint;

public class MainActivity extends AppCompatActivity implements LocationListener {
    //public class MainActivity extends AppCompatActivity  {
    //protected LocationManager locationManager;
    protected LocationListener locationListener;
    protected Context context;
    TextView txtLat;
    //String lat;
    String provider;
    protected String latitude, longitude;
    protected boolean gps_enabled, network_enabled;

    //mapPoint testmp = new mapPoint(0,0);

    String SFD0 = "nearby SF district";

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;





    private LocationManager locationManager;
    public TextView textView;
    public Double lat;
    public Double lon;
    //public mapPoint x;
    public Double metersInMile = 1609.34;

    //public FloatStringString[] targetDistrictsArray = new FloatStringString[151];
    public District[] targetDistricts = new District[151];

    public float shortestDistance1 = 999999999;
    public float shortestDistance2 = 999999999;
    public float shortestDistance3 = 999999999;
    public float shortestDistance4 = 999999999;
    public float shortestDistance5 = 999999999;
    public float shortestDistance6 = 999999999;
    public float shortestDistance7 = 999999999;
    public float shortestDistance8 = 999999999;
    public float shortestDistance9 = 999999999;
    public float shortestDistance10 = 999999999;

    public String closestDistrict1 = "";
    public String closestDistrict2 = "";
    public String closestDistrict3 = "";
    public String closestDistrict4 = "";
    public String closestDistrict5 = "";
    public String closestDistrict6 = "";
    public String closestDistrict7 = "";
    public String closestDistrict8 = "";
    public String closestDistrict9 = "";
    public String closestDistrict10 = "";

    public String targetZoomLevel1 = "z";
    public String targetZoomLevel2 = "z";
    public String targetZoomLevel3 = "z";
    public String targetZoomLevel4 = "z";
    public String targetZoomLevel5 = "z";
    public String targetZoomLevel6 = "z";
    public String targetZoomLevel7 = "z";
    public String targetZoomLevel8 = "z";
    public String targetZoomLevel9 = "z";
    public String targetZoomLevel10 = "z";

    public Location targetLocation = new Location("");//provider name is unecessary
    public String targetDistrict = "";
    public float targetDistance = 999999999;

    public String targetZoomLevel = "0z";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtLat = (TextView) findViewById(R.id.textview1);


        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
//        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, this);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void onLocationChanged(Location currentLocation) {


        String district = "Not San Francisco";
        lat = currentLocation.getLatitude();
        lon = currentLocation.getLongitude();
        txtLat = (TextView) findViewById(R.id.textview1);

        txtLat.setText("Latitude:" + currentLocation.getLatitude() + ", Longitude:" + currentLocation.getLongitude() + "\n" + "\n" + SFD0 + "\n");








        //Location.distanceBetween(double startLatitude, double startLongitude, double endLatitude, double endLongitude, float[] results)


        //if (37.747900 < lat < 37.765476) and ( -122.496239 < long < -122.476327) then central sunset

        if (37.734122 < lat && lat < 37.766222 && -122.496239 < lon && lon < -122.457572)  {district = "Inner Sunset";}
        if (37.747900 < lat && lat < 37.765476 && -122.496239 < lon && lon < -122.476327)  {district = "Central Sunset";}
        if (37.734122 < lat && lat < 37.764594 && -122.510230 < lon && lon < -122.493964)  {district = "Outer Sunset";}
        if (37.764119 < lat && lat < 37.774093 && -122.510959 < lon && lon < -122.453195)  {district = "Golden Gate Park";}




        String str = "Latitude: "+currentLocation.getLatitude()+" \nLongitude: "+currentLocation.getLongitude();
        str += "\n" + "Altitude"+currentLocation.getAltitude();
        //str += "\n" + "Nearest Address"+location.getAddress();
        str += "\n" + district;

        str += "\n\n\n";
        str += "targetDistrict=\n";
     /////44   for (int i=0;i<125; i++)
     /////44   {
     /////44       str += targetDistricts[i].citySection +" - ";
     /////44       str += targetDistricts[i].district+" - "+targetDistricts[i].distance+" miles\n";
      /////44  }

        str += "\n\n\n";
        str += "targetDistrict=\n";
        //str += targetDistricts;
/////44        str += targetDistricts[0].district+" - "+targetDistricts[0].distance+" miles\n";

        str += "\n";
        str += "\n" ;




        //@strings/see_alert_message_for_logitude_and_latitude_ = "xxx";
        ////	R.strings.main_activity.see_alert_message_for_logitude_and_latitude_ = "xxx";

        ////textView.setText("yyy");
        txtLat.setText(str);//textView.setText(str);

        //Toast.makeText(getBaseContext(), str, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onProviderDisabled(String provider) {


        /******** Called when User off Gps *********/

        Toast.makeText(getBaseContext(), "Gps turned off ", Toast.LENGTH_LONG).show();

        Log.d("Latitude", "disable");
    }

    @Override
    public void onProviderEnabled(String provider) {


        /******** Called when User on Gps  *********/

        Toast.makeText(getBaseContext(), "Gps turned on ", Toast.LENGTH_LONG).show();
        Log.d("Latitude", "enable");
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Log.d("Latitude", "status");
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.safesanfrancisco.sfdb/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Main Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://com.safesanfrancisco.sfdb/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}