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











    //	protected void setVars(Bundle savedInstanceState) {
    protected void setVars() {





        targetDistance = (float)Math.round(targetDistance * 1000) / 1000;





        if(targetDistance<shortestDistance1)
        {
            //move old answers down before setting the closest

            shortestDistance10 = shortestDistance9;
            closestDistrict10 = closestDistrict9;
            targetZoomLevel10 = targetZoomLevel9;

            shortestDistance9 = shortestDistance8;
            closestDistrict9 = closestDistrict8;
            targetZoomLevel9 = targetZoomLevel8;

            shortestDistance8 = shortestDistance7;
            closestDistrict8 = closestDistrict7;
            targetZoomLevel8 = targetZoomLevel7;

            shortestDistance7 = shortestDistance6;
            closestDistrict7 = closestDistrict6;
            targetZoomLevel7 = targetZoomLevel6;


            shortestDistance6 = shortestDistance5;
            closestDistrict6 = closestDistrict5;
            targetZoomLevel6 = targetZoomLevel5;

            shortestDistance5 = shortestDistance4;
            closestDistrict5 = closestDistrict4;
            targetZoomLevel5 = targetZoomLevel4;

            shortestDistance4 = shortestDistance3;
            closestDistrict4 = closestDistrict3;
            targetZoomLevel4 = targetZoomLevel3;

            shortestDistance3 = shortestDistance2;
            closestDistrict3 = closestDistrict2;
            targetZoomLevel3 = targetZoomLevel2;

            shortestDistance2 = shortestDistance1;
            closestDistrict2 = closestDistrict1;
            targetZoomLevel2 = targetZoomLevel1;


            shortestDistance1=targetDistance;
            closestDistrict1 = targetDistrict;
            targetZoomLevel1 = targetZoomLevel;
        }






    }













    //public class MainActivity extends AppCompatActivity  {
    //protected LocationManager locationManager;
    protected LocationListener locationListener;
    protected Context context;
    //TextView txtLat;
    TextView outputText;
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
        //txtLat = (TextView) findViewById(R.id.textview1);
        outputText = (TextView) findViewById(R.id.textview1);


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
        outputText = (TextView) findViewById(R.id.textview1);

        outputText.setText("Latitude:" + currentLocation.getLatitude() + ", Longitude:" + currentLocation.getLongitude() + "\n" + "\n" + SFD0 + "\n");








        //Location.distanceBetween(double startLatitude, double startLongitude, double endLatitude, double endLongitude, float[] results)


        //if (37.747900 < lat < 37.765476) and ( -122.496239 < long < -122.476327) then central sunset

        if (37.734122 < lat && lat < 37.766222 && -122.496239 < lon && lon < -122.457572)  {district = "Inner Sunset";}
        if (37.747900 < lat && lat < 37.765476 && -122.496239 < lon && lon < -122.476327)  {district = "Central Sunset";}
        if (37.734122 < lat && lat < 37.764594 && -122.510230 < lon && lon < -122.493964)  {district = "Outer Sunset";}
        if (37.764119 < lat && lat < 37.774093 && -122.510959 < lon && lon < -122.453195)  {district = "Golden Gate Park";}

//////////////////
        //////////////
        ////////////////


		/*
		 *  Districts of San Francisco
		 *
		 *		 *
		 *
		 *
		 *
1 Alamo Square					37.7777446,-122.4333534,16z	37.776456, -122.434673
2 Anza Vista 					37.7810089,-122.4432158,16z	37.780687, -122.443591
3 ***Ashbury Heights			37.7699298,-122.4469157,17z	37.7630153,-122.4502351 37.763321, -122.449688
4 Balboa Park					37.7217182,-122.4413936,15z	37.7217182,-122.4413936
5 Balboa Terrace				37.7308285,-122.4678955,16z	37.7308285,-122.4678955
6 Bayview						37.727865,-122.3728739,13z
7 Belden Place					37.791307,-122.4033648,17z
8 Bernal Heights				37.7404204,-122.4195741,14z
9 Buena Vista					37.7645846,-122.4425598,15z
10 ***Old Butchertown 			37.738125,-122.380713,17z
   ***New Butchertown				37.738125,-122.380713,17z
   ***Butchertown					37.7668295,-122.401143,12z
11 Castro						37.7625247,-122.4361676,14z
12 Cathedral Hill				37.7843439,-122.4245498,16z
13 Cayuga Terrace				37.7242799,-122.4402671,15z
14 China Basin					37.7772776,-122.3865022,17z
15 Chinatown					37.7940866,-122.4071315,15z
16 Civic Center					37.7778532,-122.4178577,15z
17 Clarendon Heights			37.75518,-122.4512277,15z
18 Cole Valley					37.7648287,-122.452712,15z
19 Corona Heights				37.765312,-122.438585,17z
20 Cow Hollow					37.79995,-122.437515,17z
21 Crocker-Amazon				37.7122324,-122.4387984,15z
22 Design District				37.7689239,-122.3995323,16z
23 Diamond Heights				37.7410467,-122.4435918,15z
24 Dogpatch						37.7572244,-122.3895594,14z
25 Dolores Heights				37.7569472,-122.4334385,15z
26 Duboce Triangle				37.7661594,-122.4311157,16z
27 Embarcadero					37.7946761,-122.3969865,13z
28 Eureka Valley				7.7577874,-122.4418521,15z
29 Excelsior					37.724134,-122.4209454,14z
30 Fillmore						37.7862924,-122.435776,15z
31 *Financial District			37.7927731,-122.4010922,15z
32 *Financial District South	37.7927731,-122.4010922,15z
33 Fisherman's Wharf			37.8081103,-122.416631,15z
34 Forest Hill					37.7480526,-122.4646272,15z
35 Forest Knolls				37.7574369,-122.4573887,15z
36 Glen Park					37.7379098,-122.4321038,15z
37 Golden Gate Heights			37.754067,-122.4700568,15z
38 Haight-Ashbury				37.7699298,-122.4469157,17z
39 Hayes Valley					37.7746071,-122.4260718,15z
40 Hunters Point				37.727865,-122.3728739,13z
41 India Basin					37.7409911,-122.3812691,15z
42 *Ingleside					37.7207279,-122.4549376,15z
43 *Ingleside Terraces			37.7207279,-122.4549376,15z
   Inner Richmond				37.7809555,-122.462596,14z
44 **Inner Sunset				37.763721,-122.465405,17z
45 **Irish Hill					37.742655,-122.4904,17z
46 Islais Creek					37.748333,-122.375556,17z
47 Jackson Square				37.796689,-122.4035183,17z
48 Japantown					37.785614,-122.4306374,17z
49 **Jordan Park				37.7846509,-122.4566383,14z
   **La Playa 					37.77391,-122.509892,17z
50 **Laguna Honda				37.747292,-122.459044,17z
51 Lake Street					37.7864845,-122.4705415,15z
52 Lakeside						37.725884,-122.4734583,14z
53 Lakeshore					37.7193379,-122.4896083,13z
54 Laurel Heights				37.7842282,-122.4529064,16z
55 Little Hollywood				37.7119633,-122.3983217,15z
56 Little Russia				37.7802377,-122.4815463,16z
57 Little Saigon				37.7842346,-122.4177669,17z
58 Lone Mountain				37.7783446,-122.4526678,15z
59 Lower Haight					37.7717213,-122.433711,16z
60 Lower Pacific Heights		37.7862924,-122.4357759,15z
61 Lower Nob Hill				37.7890843,-122.4136579,16z
62 Marina District				37.8019775,-122.4371031,14z
63 Merced Heights				37.7193656,-122.4675014,16z
64 Merced Manor					37.7328756,-122.4784722,16z
65 Midtown Terrace				37.7511432,-122.4532204,15z
66 Mid-Market					37.7801532,-122.4114597,15z
67 **Miraloma Park				37.741423,-122.446653,17z
68 Mission Bay					37.7738181,-122.39407,14z
69 Mission District				37.7599046,-122.4168468,14z
70 **Mission Dolores			37.764387,-122.426897,17z
71 *Mission Terrace				37.7217182,-122.4413936,15z (same as Balboa Park)
72 Monterey Heights				37.733519,-122.4625056,16z
73 Mount Davidson				37.7390371,-122.4540034,16z
74 Nob Hill						37.7929437,-122.416112,15z
75 Noe Valley					37.7490555,-122.4344773,14z
76 North Beach					37.8047122,-122.4081963,15z
77 North of Panhandle			37.7763168,-122.4423515,16z
78 Oceanview					37.7196153,-122.4600507,14z
79 Outer Mission				37.7220888,-122.4436351,14z
   Outer Richmond 				37.7794924,-122.4948635,14z
80 Outer Sunset					37.7553517,-122.4936706,14z
81 Pacific Heights				37.7924282,-122.4349945,15z
82 Parkmerced					37.7184207,-122.4784131,15z
83 Parkside						37.7328756,-122.4784722,16z
84 **Parnassus					37.7582631,-122.4571935,14z
85 **Polk Gulch					37.791526,-122.4209171,17z
86 Portola						37.7270714,-122.4120226,14z
87 Portola Place				37.7280593,-122.3978989,16z
88 Potrero Hill					37.758266,-122.392634,14z
89 Presidio						37.7989746,-122.4661867,14z
90 Presidio Heights				37.7886932,-122.4531634,16z
91 Richmond District			37.7801608,-122.4791776,14z
92 Rincon Hill					37.7868715,-122.3917865,16z
93 Russian Hill					37.8008489,-122.4159543,15z
94 Saint Francis Wood			37.7366769,-122.4660212,15z
95 Sea Cliff					37.7828624,-122.4993276,14z
96 Sherwood Forest				37.737581,-122.45763,16z
97 Silver Terrace				37.7339535,-122.3993016,15z
98 Somisspo						37.7691584,-122.4072762,16z
99 South Beach					37.7864283,-122.3954732,14z
100 **South End					37.7689385,-122.3876133,17z
101 South of Market(SoMa)		37.7808157,-122.4024182,13z
102 South Park					37.7815754,-122.3940443,17z
103 Sunnydale					37.7105412,-122.4198183,16z
104 Sunnyside					7.7293949,-122.4447906,15z
105 Sunset District				37.7500392,-122.4840656,13z
106 Telegraph Hill				37.8016668,-122.4061985,15z
107 Tenderloin					37.7839296,-122.4130524,15z
108 Treasure Island				37.8201487,-122.3689871,14z
109 **Twin Peaks				37.752569,-122.447546,17z
110 **Union Square				37.787994,-122.407437,17z	37.787892, -122.407539 37.787994,-122.407437
111 University Mound			37.7259385,-122.4183454,16z
112 Upper Market				37.7529277,-122.4450824,15z
113 Visitacion Valley			37.7162374,-122.414285,14z
114 Vista del Mar				37.7781413,-122.5080427,16z
115 West Portal					37.7393017,-122.4654826,15z
116 Western Addition			37.779694,-122.4343415,14z
117 Westwood Highlands			37.7332045,-122.4561859,16z
118 Westwood Park				37.7273164,-122.4566644,15z
119 ***Yerba Buena				37.785779,-122.402156,17z
		 *
		 *
		 *
		 *
		 *
		 *
		 *
		 *
		 *
		 *  Castro District		37.7625247,-122.4361676,14z
		 *  Chinatown			37.7940866,-122.4071315,15z
		 *  Cole Valley			37.7648287,-122.452712,15z
		 *  Financial District	37.7927731,-122.4010922,15z
		 *  Fisherman's Wharf	37.8081103,-122.416631,15z
		 *  Haight-Ashbury		37.7699298,-122.4469157,17z
		 *  Hayes Valley		37.7746071,-122.4260718,15z
		 *  Japantown 			37.785614,-122.4306374,17z
		 *  Lower Haight		37.7717213,-122.4337111,16z
		 *  Marina				37.8019775,-122.4371031,14z
		 *  Mission District	37.7599046,-122.4168468,14z
		 *  Nob Hill			37.7929437,-122.416112,15z
		 *  Noe Valley			37.7490555,-122.4344773,14z
		 *  North Beach			37.8047122,-122.4081963,15z
		 *  Pacific Heights		37.7924282,-122.4349945,15z
		 *  Panhandle/NoPa		37.7763168,-122.4423515,16z
		 *  Potrero Hill		37.758266,-122.3926339,14z
		 *  Presidio			37.7989746,-122.4661867,14z
		 *  Richmond			37.7801608,-122.4791776,14z
		 *  Russian Hill		37.8008489,-122.4159543,15z
		 *  Sea Cliff			37.7828624,-122.4993276,14z
		 *  ***Sixth Street		37.7760457,-122.4025347,17z
		 *  SOMA				37.7808157,-122.4024182,13z
		 *  Sunset				37.7500392,-122.4840656,13z
		 *  Tenderloin			37.7839296,-122.4130524,15z
		 *  Union Square		37.787994,-122.407437,17z
		 *  Upper Market		37.7529277,-122.4450824,15z
		 *
		 *
		 *
		 *
		 */



        targetDistrict = "Alamo Square";
        targetLocation.setLatitude(37.7777446);
        targetLocation.setLongitude(-122.4333534);
        targetZoomLevel = "16z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d0 = new District(targetDistrict,targetDistance,targetZoomLevel,
                37.7777446,-122.4333534);
        targetDistricts[0] = d0;
        d0.addMapPoint(37.780416, -122.432270);
        d0.addMapPoint(37.779585, -122.438621);
        d0.addMapPoint(37.775158, -122.437763);
        d0.addMapPoint(37.776481, -122.428128);
        d0.addMapPoint(37.778957, -122.428601);
        d0.addMapPoint(37.778499, -122.431712);
        d0.getDistance(currentLocation);
        d0.citySection = "Western Addition - ";









        //////////////////
        /////////////////
        ////////////////////////

  ///////////////
        ///////////
        //////////


        String str = "Latitude: "+currentLocation.getLatitude()+" \nLongitude: "+currentLocation.getLongitude();
        str += "\n" + "Altitude"+currentLocation.getAltitude();
        //str += "\n" + "Nearest Address"+location.getAddress();
        str += "\n" + district;

        str += "\n\n\n";
        str += "targetDistrict=\n";
     /////44   for (int i=0;i<125; i++)
         for (int i=0;i<0; i++)
        {
            str += targetDistricts[i].citySection +" - ";
            str += targetDistricts[i].district+" - "+targetDistricts[i].distance+" miles\n";
        }

        str += "\n\n\n";
        str += "targetDistrict=\n";
        //str += targetDistricts;
        str += targetDistricts[0].district+" - "+targetDistricts[0].distance+" miles\n";

        str += "\n";
        str += "\n" ;




        //@strings/see_alert_message_for_logitude_and_latitude_ = "xxx";
        ////	R.strings.main_activity.see_alert_message_for_logitude_and_latitude_ = "xxx";

        ////textView.setText("yyy");
        outputText.setText(str);//textView.setText(str);

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