package com.safesanfrancisco.sfdb;

import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
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

import java.util.Arrays;


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
    //commented version below is from SFDistricts3 and may be less resource intenseive since
    //it only checks every 3 seconds
   // locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
   //         3000,   // 3 sec
   //         10, this);
    public String targetDistrict = "";
    public float targetDistance = 999999999;

    public String targetZoomLevel = "0z";

    String district = "Not San Francisco";

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

        // Set the text view as the activity layout
        //setContentView(textView);

        //yourTextView.setMovementMethod(new ScrollingMovementMethod())
        //textView.setMovementMethod(new ScrollingMovementMethod());
        outputText.setMovementMethod(new ScrollingMovementMethod());

    }

    @Override
    public void onLocationChanged(Location currentLocation) {



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




        //repurposed
        targetDistrict = "**Yerba Buena Island";
        targetLocation.setLatitude(37.8114588);
        targetLocation.setLongitude(-122.3658067);
        targetZoomLevel = "15z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d2 = new District(targetDistrict,targetDistance,targetZoomLevel,
                37.8114588,-122.3658067);
        targetDistricts[2] = d2;
        d2.addMapPoint(37.814324, -122.359048);
        d2.addMapPoint(37.813239, -122.370849);
        d2.addMapPoint(37.808390, -122.368532);
        d2.addMapPoint(37.808390, -122.362309);
        d2.getDistance(currentLocation);
        d2.citySection = "North of Downtown - ";


	/*

			targetDistrict = "Richmond";
		    targetLocation.setLatitude(37.7801608);
		    targetLocation.setLongitude(-122.4791776);
		    targetZoomLevel = "14z";
		    targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
		    setVars();
		 */


        targetDistrict = "Richmond District";
        targetLocation.setLatitude(37.7801608);
        targetLocation.setLongitude(-122.4791776);
        targetZoomLevel = "14z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d19 = new District(targetDistrict,targetDistance,targetZoomLevel,
                targetLocation.getLatitude(),targetLocation.getLongitude());
        targetDistricts[19] = d19;
        d19.addMapPoint(37.776057, -122.446691);
        d19.addMapPoint(37.782230, -122.447978);
        d19.addMapPoint(37.781144, -122.458793);
        d19.addMapPoint(37.786639, -122.459479);
        d19.addMapPoint(37.788742, -122.462827);
        d19.addMapPoint(37.787182, -122.487031);
        d19.addMapPoint(37.783112, -122.491752);
        d19.addMapPoint(37.781348, -122.492095);
        d19.addMapPoint(37.780805, -122.509605);
        d19.addMapPoint(37.775378, -122.508832);
        d19.addMapPoint(37.775107, -122.511836);
        d19.addMapPoint(37.771647, -122.511493);
        d19.addMapPoint(37.776192, -122.446776);
        d19.addMapPoint(37.772257, -122.495700);
        d19.addMapPoint(37.773139, -122.479735);
        d19.addMapPoint(37.774428, -122.460252);
        d19.getDistance(currentLocation);
        d19.citySection = "Outside Lands - ";




//			targetDistrict = "***Sixth Street";
//		    targetLocation.setLatitude(37.7760457);
//		    targetLocation.setLongitude(-122.4025347);
//		    targetZoomLevel = "17z";
//		    targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
//		    setVars();




        targetDistrict = "Anza Vista";
        targetLocation.setLatitude(37.7810089);
        targetLocation.setLongitude(-122.4432158);
        targetZoomLevel = "16z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d27 = new District(targetDistrict,targetDistance,targetZoomLevel,
                targetLocation.getLatitude(),targetLocation.getLongitude());
        targetDistricts[27] = d27;
        d27.addMapPoint(37.781933, -122.446853);
        d27.addMapPoint(37.782900, -122.440029);
        d27.addMapPoint(37.779745, -122.438999);
        d27.addMapPoint(37.778813, -122.447024);
        d27.addMapPoint(37.780068, -122.447153);
        d27.addMapPoint(37.782713, -122.443420);
        d27.addMapPoint(37.781374, -122.439257);
        d27.addMapPoint(37.779237, -122.443248);
        d27.getDistance(currentLocation);
        d27.citySection = "Western Addition - ";

        //might be the same as Parnasus
        //http://zephyrsf.com/buy/neighborhood/buena-vista-ashbury-heights
        //http://www.city-data.com/neighborhood/Parnassus-San-Francisco-CA.html
        //Mentioned that Asbury Heights is West of Buena Vista Park, but looks like it is a bit South as well
        targetDistrict = "Ashbury Heights";
        //wrong - google maps returned Haight-Ashbury! not Ashbury Heights!
        //targetLocation.setLatitude(37.7699937);
        //targetLocation.setLongitude(-122.4448403);

        //hand selected
        targetLocation.setLatitude(37.762852);
        targetLocation.setLongitude(-122.448467);

        targetZoomLevel = "15z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d28 = new District(targetDistrict,targetDistance,targetZoomLevel,
                targetLocation.getLatitude(),targetLocation.getLongitude());
        targetDistricts[28] = d28;
        d28.addMapPoint(37.766177, -122.444776);
        d28.addMapPoint(37.762352, -122.446267);//Upper Terrace
        d28.getDistance(currentLocation);
        d28.citySection = "";


		    /*
		     *
		     * Balboa Park is a neighborhood and public park in San Francisco, California. It was created in 1909 and parts of it were built over time. The neighborhood (sometimes referred to as Mission Terrace, Cayuga, or Ingleside) is located between Mission Street and Interstate 280 north of Geneva Avenue and the park is located on San Jose Avenue, north of Ocean Avenue. Inside of the park there is a public swimming pool, a children's playground, a stadium, baseball diamonds, tennis courts and the Ingleside police station. The only public soccer-specific stadium, Boxer Stadium, is located within Balboa Park.
Balboa High School is situated on Cayuga Avenue, and City College of San Francisco is on the other side of Interstate 280. Public transportation in Balboa Park is centered on Balboa Park Station in the southwest corner of the neighborhood, a Bay Area Rapid Transit Station that also serves as the terminal of the J, K and M Muni Metro lines.
		     *
		     *
		     */
        targetDistrict = "Balboa Park";
        targetLocation.setLatitude(37.7217182);
        targetLocation.setLongitude(-122.4413936);
        targetZoomLevel = "15z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d29 = new District(targetDistrict,targetDistance,targetZoomLevel,
                targetLocation.getLatitude(),targetLocation.getLongitude());
        targetDistricts[29] = d29;
        d29.addMapPoint(37.724043, -122.435579);
        d29.addMapPoint(37.726861, -122.444505);
        d29.addMapPoint(37.721022, -122.447337);
        d29.addMapPoint(37.716983, -122.440943);
        d29.addMapPoint(37.720887, -122.438068);
        d29.addMapPoint(37.725910, -122.439956);
        d29.addMapPoint(37.725435, -122.445878);
        d29.addMapPoint(37.719597, -122.444290);
        d29.addMapPoint(37.723568, -122.438454);
        d29.addMapPoint(37.722278, -122.443861);
        d29.getDistance(currentLocation);
        d29.citySection = "";




        targetDistrict = "Balboa Terrace";
        targetLocation.setLatitude(37.7308285);
        targetLocation.setLongitude(-122.4678955);
        targetZoomLevel = "16z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d30 = new District(targetDistrict,targetDistance,targetZoomLevel,
                targetLocation.getLatitude(),targetLocation.getLongitude());
        targetDistricts[30] = d30;
        d30.addMapPoint(37.733111, -122.471318);
        d30.addMapPoint(37.733060, -122.466769);
        d30.addMapPoint(37.732008, -122.464902);
        d30.addMapPoint(37.730192, -122.465095);
        d30.addMapPoint(37.730447, -122.466211);
        d30.addMapPoint(37.728648, -122.467692);
        d30.addMapPoint(37.729700, -122.468464);
        d30.addMapPoint(37.730684, -122.470546);
        d30.addMapPoint(37.731109, -122.471597);
        d30.addMapPoint(37.732296, -122.471425);
        d30.getDistance(currentLocation);
        d30.citySection = "";




        targetDistrict = "Bayview";
        targetLocation.setLatitude(37.727865);
        targetLocation.setLongitude(-122.3728739);
        targetZoomLevel = "13z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d31 = new District(targetDistrict,targetDistance,targetZoomLevel,
                targetLocation.getLatitude(),targetLocation.getLongitude());
        targetDistricts[31] = d31;
        d31.addMapPoint(37.723317, -122.395447);
        d31.addMapPoint(37.727526, -122.393387);
        d31.addMapPoint(37.737165, -122.390126);
        d31.addMapPoint(37.749653, -122.397851);
        d31.addMapPoint(37.744902, -122.400426);
        d31.addMapPoint(37.739744, -122.404545);
        d31.addMapPoint(37.734042, -122.385148);
        d31.addMapPoint(37.728204, -122.383259);
        d31.addMapPoint(37.722366, -122.384804);
        d31.addMapPoint(37.720737, -122.392701);
        d31.getDistance(currentLocation);
        d31.citySection = "Southern - ";





        //Often dubbed the French Quarter of San Francisco,
        //Belden Place (aka Belden Alley, Belden Lane, or
        //Belden Street) offers an American repesentation of European dining.
        targetDistrict = "Belden Place";
        targetDistrict = "French Quarter";
        targetLocation.setLatitude(37.791307);
        targetLocation.setLongitude(-122.4033648);
        targetZoomLevel = "17z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d32 = new District(targetDistrict,targetDistance,targetZoomLevel,
                targetLocation.getLatitude(),targetLocation.getLongitude());
        targetDistricts[32] = d32;
        d32.addMapPoint(37.791693, -122.403896);
        d32.addMapPoint(37.791226, -122.403778);
        d32.addMapPoint(37.790989, -122.403735);
        d32.addMapPoint(37.791307, -122.4033648);
        d32.addMapPoint(37.791811, -122.402716);
        d32.addMapPoint(37.791650, -122.404164);
        d32.addMapPoint(37.790819, -122.40401);
        d32.addMapPoint(37.791040, -122.402566);
        d32.getDistance(currentLocation);
        d32.citySection = "Downtown - ";





        targetDistrict = "Bernal Heights";
        targetLocation.setLatitude(37.7404204);
        targetLocation.setLongitude(-122.4195741);
        targetZoomLevel = "14z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d33 = new District(targetDistrict,targetDistance,targetZoomLevel,
                targetLocation.getLatitude(),targetLocation.getLongitude());
        targetDistricts[33] = d33;
        d33.addMapPoint(37.747241,-122.423651);
        d33.addMapPoint(37.732988,-122.431891);
        d33.addMapPoint(37.747513, -122.424080);
        d33.addMapPoint(37.744527, -122.422879);
        d33.addMapPoint(37.740522, -122.423479);
        d33.addMapPoint(37.738825, -122.423222);
        d33.addMapPoint(37.733124, -122.428715);
        d33.addMapPoint(37.733056, -122.422364);
        d33.addMapPoint(37.733395, -122.415926);
        d33.addMapPoint(37.735364, -122.410776);
        d33.addMapPoint(37.735635, -122.407772);
        d33.addMapPoint(37.737468, -122.408802);
        d33.addMapPoint(37.741608, -122.408545);
        d33.addMapPoint(37.744527, -122.407000);
        d33.addMapPoint(37.747241, -122.405283);
        d33.addMapPoint(37.748599, -122.405369);
        d33.addMapPoint(37.747988, -122.408459);
        d33.addMapPoint(37.747852, -122.415926);
        d33.addMapPoint(37.732445, -122.433178);
        d33.addMapPoint(37.734753, -122.424338);
        d33.addMapPoint(37.736993, -122.418330);
        d33.addMapPoint(37.742898, -122.411635);
        d33.getDistance(currentLocation);
        d33.citySection = "Southern - ";





        targetDistrict = "Buena Vista";
        targetLocation.setLatitude(37.7645846);
        targetLocation.setLongitude(-122.4425598);
        targetZoomLevel = "15z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d34 = new District(targetDistrict,targetDistance,targetZoomLevel,
                targetLocation.getLatitude(),targetLocation.getLongitude());
        targetDistricts[34] = d34;
        d34.addMapPoint(37.770318, -122.440972);
        d34.addMapPoint(37.767265, -122.439856);
        d34.addMapPoint(37.765772, -122.441659);
        d34.addMapPoint(37.766383, -122.443719);
        d34.addMapPoint(37.764211, -122.443719);
        d34.addMapPoint(37.761633, -122.445864);
        d34.addMapPoint(37.759394, -122.446122);
        d34.addMapPoint(37.760547, -122.444491);
        d34.addMapPoint(37.761565, -122.441744);
        d34.addMapPoint(37.761837, -122.439856);
        d34.addMapPoint(37.763126, -122.439856);
        d34.addMapPoint(37.764890, -122.441315);
        d34.getDistance(currentLocation);
        d34.citySection = "";





        targetDistrict = "***Old Butchertown";
        targetLocation.setLatitude(37.738125);
        targetLocation.setLongitude(-122.380713);
        targetZoomLevel = "17z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d35 = new District(targetDistrict,targetDistance,targetZoomLevel,
                targetLocation.getLatitude(),targetLocation.getLongitude());
        targetDistricts[35] = d35;
        d35.addMapPoint(0,0);
        d35.getDistance(currentLocation);
        d35.citySection = "";






        targetDistrict = "-obsolete-***New Butchertown";
        targetLocation.setLatitude(7.738125);
        targetLocation.setLongitude(-122.380713);
        targetZoomLevel = "17z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d36 = new District(targetDistrict,targetDistance,targetZoomLevel,
                targetLocation.getLatitude(),targetLocation.getLongitude());
        targetDistricts[36] = d36;
        d36.getDistance(currentLocation);
        d36.citySection = "";




        targetDistrict = "-obsolete-***Butchertown";
        targetLocation.setLatitude(7.7668295);
        targetLocation.setLongitude(-122.401143);
        targetZoomLevel = "12z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d37 = new District(targetDistrict,targetDistance,targetZoomLevel,
                targetLocation.getLatitude(),targetLocation.getLongitude());
        targetDistricts[37] = d37;
        d37.getDistance(currentLocation);
        d37.citySection = "";






        targetDistrict = "Castro";
        targetLocation.setLatitude(37.7625247);
        targetLocation.setLongitude(-122.4361676);
        targetZoomLevel = "14z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d38 = new District(targetDistrict,targetDistance,targetZoomLevel,
                targetLocation.getLatitude(),targetLocation.getLongitude());
        targetDistricts[38] = d38;
        d38.addMapPoint(37.769446, -122.427198);
        d38.addMapPoint(37.767139, -122.438614);
        d38.addMapPoint(37.765171, -122.441446);
        d38.addMapPoint(37.763543, -122.443935);
        d38.addMapPoint(37.759471, -122.443420);
        d38.addMapPoint(37.757843, -122.440159);
        d38.addMapPoint(37.756689, -122.438185);
        d38.addMapPoint(37.757232, -122.434322);
        d38.addMapPoint(37.757571, -122.426855);
        d38.addMapPoint(37.761846, -122.427885);
        d38.addMapPoint(37.764425, -122.434065);
        d38.addMapPoint(37.762796, -122.439472);
        d38.addMapPoint(37.759336, -122.438185);
        d38.addMapPoint(37.758386, -122.432606);
        d38.getDistance(currentLocation);
        d38.citySection = "Southern - ";




        targetDistrict = "Cathedral Hill";
        targetLocation.setLatitude(37.7843439);
        targetLocation.setLongitude(-122.4245498);
        targetZoomLevel = "16z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d39 = new District(targetDistrict,targetDistance,targetZoomLevel,
                targetLocation.getLatitude(),targetLocation.getLongitude());
        targetDistricts[39] = d39;
        d39.addMapPoint(37.785497, -122.427608);
        d39.addMapPoint(37.785802, -122.425741);
        d39.addMapPoint(37.786277, -122.422522);
        d39.addMapPoint(37.785022, -122.421835);
        d39.addMapPoint(37.783242, -122.421492);
        d39.addMapPoint(37.782886, -122.423531);
        d39.addMapPoint(37.782495, -122.426470);
        d39.addMapPoint(37.783326, -122.426942);
        d39.addMapPoint(37.784514, -122.427157);
        d39.addMapPoint(37.785311, -122.427307);
        d39.addMapPoint(37.785751, -122.425376);
        d39.getDistance(currentLocation);
        d39.citySection = "Western Addition - ";





        targetDistrict = "Cayuga Terrace";
        targetLocation.setLatitude(37.7242799);
        targetLocation.setLongitude(-122.4402671);
        targetZoomLevel = "15z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d40 = new District(targetDistrict,targetDistance,targetZoomLevel,
                targetLocation.getLatitude(),targetLocation.getLongitude());
        targetDistricts[40] = d40;
        d40.addMapPoint(37.730186, -122.436019);
        d40.addMapPoint(37.729507, -122.435504);
        d40.addMapPoint(37.727403, -122.436619);
        d40.addMapPoint(37.724416, -122.437907);
        d40.addMapPoint(37.720750, -122.440138);
        d40.addMapPoint(37.718849, -122.442542);
        d40.addMapPoint(37.719663, -122.443572);
        d40.addMapPoint(37.720885, -122.445117);
        d40.addMapPoint(37.725570, -122.441083);
        d40.addMapPoint(37.727267, -122.440310);
        d40.getDistance(currentLocation);
        d40.citySection = "";


		    /* This hood seems to be enclosed within other hoods like South Beach and Mission Bay
		     * Tiny place that includes the Caltrain, AT&T Park and restaurants north of the actual body of water China Basin
		     *
		     * China Basin is a neighborhood built on landfill along the San Francisco Bay. It lies north of Mission Creek and the Mission Bay neighborhood, and includes AT&T Park, home of the San Francisco Giants, numerous restaurants, and the Caltrain railroad station. It borders on the South-of-Market (SOMA) neighborhood to the northwest and the South Beach neighborhood to the north. The term China Basin also refers to the body of water from which the neighborhood takes its name. This inlet where Mission Creek flows into the bay, home to a number of houseboats, was once an active industrial waterfront, though in recent decades the shore has been developed with residential condominiums. The portion of the waterway adjacent to the ballpark is often referred to as McCovey Cove, its unofficial name, which has a small ballfield, open to the public, named after Barry Bonds.
		     *
		     *
		     */

        targetDistrict = "China Basin";
        targetLocation.setLatitude(37.779338);
        targetLocation.setLongitude(-122.388938);
        targetZoomLevel = "17z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d41 = new District(targetDistrict,targetDistance,targetZoomLevel,
                targetLocation.getLatitude(),targetLocation.getLongitude());
        targetDistricts[41] = d41;
        d41.addMapPoint(37.775810, -122.394087);
        d41.addMapPoint(37.776735, -122.391624);
        d41.getDistance(currentLocation);
        d41.citySection = "";





        targetDistrict = "Chinatown";
        targetLocation.setLatitude(37.7940866);
        targetLocation.setLongitude(-122.4071315);
        targetZoomLevel = "15z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d42 = new District(targetDistrict,targetDistance,targetZoomLevel,
                targetLocation.getLatitude(),targetLocation.getLongitude());
        targetDistricts[42] = d42;
        d42.addMapPoint(37.797613, -122.405823);
        d42.addMapPoint(37.797071, -122.407711);
        d42.addMapPoint(37.797257, -122.409739);
        d42.addMapPoint(37.796240, -122.409653);
        d42.addMapPoint(37.794833, -122.409481);
        d42.addMapPoint(37.792967, -122.409009);
        d42.addMapPoint(37.790475, -122.408558);
        d42.addMapPoint(37.790746, -122.406649);
        d42.addMapPoint(37.790831, -122.405490);
        d42.addMapPoint(37.791034, -122.404439);
        d42.addMapPoint(37.792866, -122.405104);
        d42.addMapPoint(37.794443, -122.405254);
        d42.addMapPoint(37.797156, -122.405855);
        d42.addMapPoint(37.796579, -122.407056);
        d42.addMapPoint(37.797817, -122.405919);
        d42.addMapPoint(37.795647, -122.408451);
        d42.addMapPoint(37.793612, -122.407250);
        d42.addMapPoint(37.791662, -122.407035);
        d42.addMapPoint(37.792493, -122.405962);
        d42.addMapPoint(37.794527, -122.406391);
        d42.getDistance(currentLocation);
        d42.citySection = "Downtown - ";





        targetDistrict = "Civic Center";
        targetLocation.setLatitude(37.7778532);
        targetLocation.setLongitude(-122.4178577);
        targetZoomLevel = "15z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d43 = new District(targetDistrict,targetDistance,targetZoomLevel,
                targetLocation.getLatitude(),targetLocation.getLongitude());
        targetDistricts[43] = d43;
        d43.addMapPoint(37.782432, -122.414542);
        d43.addMapPoint(37.781177, -122.414156);
        d43.addMapPoint(37.780499, -122.413126);
        d43.addMapPoint(37.779770, -122.413641);
        d43.addMapPoint(37.778752, -122.415294);
        d43.addMapPoint(37.777853, -122.416678);
        d43.addMapPoint(37.776123, -122.418995);
        d43.addMapPoint(37.776937, -122.417836);
        d43.addMapPoint(37.778599, -122.419210);
        d43.addMapPoint(37.778396, -122.420712);
        d43.addMapPoint(37.779753, -122.421312);
        d43.addMapPoint(37.781517, -122.421784);
        d43.addMapPoint(37.781856, -122.419252);
        d43.addMapPoint(37.782110, -122.417439);
        d43.addMapPoint(37.780397, -122.416763);
        d43.addMapPoint(37.779719, -122.419682);
        d43.addMapPoint(37.778125, -122.419210);
        d43.addMapPoint(37.776293, -122.420583);
        d43.addMapPoint(37.776666, -122.421613);
        d43.addMapPoint(37.780906, -122.415905);
        d43.getDistance(currentLocation);
        d43.citySection = "Downtown - ";






        targetDistrict = "Clarendon Heights";
        targetLocation.setLatitude(37.75518);
        targetLocation.setLongitude(-122.4512277);
        targetZoomLevel = "15z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d44 = new District(targetDistrict,targetDistance,targetZoomLevel,
                targetLocation.getLatitude(),targetLocation.getLongitude());
        targetDistricts[44] = d44;
        d44.addMapPoint(37.758505, -122.447215);
        d44.addMapPoint(37.758200, -122.451035);
        d44.addMapPoint(37.755893, -122.453738);
        d44.addMapPoint(37.752941, -122.454296);
        d44.addMapPoint(37.751821, -122.451550);
        d44.addMapPoint(37.752228, -122.449275);
        d44.addMapPoint(37.754196, -122.449533);
        d44.addMapPoint(37.756028, -122.449618);
        d44.addMapPoint(37.757928, -122.447988);
        d44.addMapPoint(37.754060, -122.452279);
        d44.getDistance(currentLocation);
        d44.citySection = "";




        targetDistrict = "Cole Valley";
        targetLocation.setLatitude(37.7648287);
        targetLocation.setLongitude(-122.452712);
        targetZoomLevel = "15z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d45 = new District(targetDistrict,targetDistance,targetZoomLevel,
                targetLocation.getLatitude(),targetLocation.getLongitude());
        targetDistricts[45] = d45;
        d45.addMapPoint(37.766384,-122.452819);
        d45.addMapPoint(37.768204,-122.453120);
        d45.addMapPoint(37.768713,-122.448528);
        d45.addMapPoint(37.765321,-122.447884);
        d45.addMapPoint(37.761114,-122.447841);
        d45.addMapPoint(37.761724,-122.451832);
        d45.addMapPoint(37.764676,-122.452519);
        d45.addMapPoint(37.764099,-122.456596);
        d45.getDistance(currentLocation);
        d45.citySection = "Western Addition - ";



        targetDistrict = "Corona Heights";
        targetLocation.setLatitude(37.765312);
        targetLocation.setLongitude(-122.438585);
        targetZoomLevel = "17z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d46 = new District(targetDistrict,targetDistance,targetZoomLevel,
                targetLocation.getLatitude(),targetLocation.getLongitude());
        targetDistricts[46] = d46;
        d46.addMapPoint(37.763472, -122.437260);
        d46.addMapPoint(37.763819, -122.438751);
        d46.addMapPoint(37.764438, -122.437271);
        d46.addMapPoint(37.764532, -122.439363);
        d46.addMapPoint(37.765108, -122.440275);
        d46.addMapPoint(37.765838, -122.439202);
        d46.addMapPoint(37.765685, -122.437829);
        d46.addMapPoint(37.765185, -122.437378);
        d46.addMapPoint(37.764930, -122.438612);
        d46.addMapPoint(37.764676, -122.437668);
        d46.getDistance(currentLocation);
        d46.citySection = "Western Addition";



        //https://www.airbnb.com/locations/san-francisco/cow-hollow
        //targetDistrict = "**Cow Hollow";
        //Asterisks because a Motor Inn was chosen, not a district
        //targetLocation.setLatitude(37.79995);
        //targetLocation.setLongitude(-122.437515);
        //targetZoomLevel = "17z";
        //my research shows the corners to be
        //Lyon and Lombard,
        //Van Ness and Lombard,
        //Lyon and Union,
        //Van Ness and Union
        //no asterisk because I fixed the issue and selected my own centroid
        targetDistrict = "Cow Hollow";
        targetLocation.setLatitude(37.798464);
        targetLocation.setLongitude(-122.435820);
        targetZoomLevel = "15z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d47 = new District(targetDistrict,targetDistance,targetZoomLevel,
                targetLocation.getLatitude(),targetLocation.getLongitude());
        targetDistricts[47] = d47;
        d47.addMapPoint(37.798336, -122.446662);
        d47.addMapPoint(37.800931, -122.424963);
        d47.addMapPoint(37.799608, -122.424576);
        d47.addMapPoint(37.798591, -122.424727);
        d47.addMapPoint(37.797896, -122.430434);
        d47.addMapPoint(37.797455, -122.434275);
        d47.addMapPoint(37.796810, -122.440198);
        d47.addMapPoint(37.795878, -122.446614);
        d47.addMapPoint(37.797116, -122.445390);
        d47.addMapPoint(37.798794, -122.442365);
        d47.addMapPoint(37.799371, -122.437473);
        d47.addMapPoint(37.799930, -122.432473);
        d47.addMapPoint(37.800405, -122.429168);
        d47.addMapPoint(37.799218, -122.427452);
        d47.addMapPoint(37.798387, -122.431872);
        d47.addMapPoint(37.798421, -122.434576);
        d47.addMapPoint(37.797675, -122.437494);
        d47.addMapPoint(37.797523, -122.440906);
        d47.addMapPoint(37.796878, -122.444232);
        d47.addMapPoint(37.797573, -122.445519);
        d47.getDistance(currentLocation);
        d47.citySection = "North of Downtown";






        targetDistrict = "Crocker-Amazon";
        targetLocation.setLatitude(37.7122324);
        targetLocation.setLongitude(-122.4387984);
        targetZoomLevel = "15z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d48 = new District(targetDistrict,targetDistance,targetZoomLevel,
                targetLocation.getLatitude(),targetLocation.getLongitude());
        targetDistricts[48] = d48;
        d48.addMapPoint(37.715424, -122.440558);
        d48.addMapPoint(37.709448, -122.424679);
        d48.addMapPoint(37.708905, -122.451287);
        d48.addMapPoint(37.712097, -122.443390);
        d48.addMapPoint(37.712029, -122.431632);
        d48.addMapPoint(37.708905, -122.439013);
        d48.addMapPoint(37.712097, -122.444334);
        d48.addMapPoint(37.713183, -122.440129);
        d48.addMapPoint(37.711146, -122.433091);
        d48.addMapPoint(37.709652, -122.437382);
        d48.addMapPoint(37.709856, -122.444935);
        d48.addMapPoint(37.710874, -122.440472);
        d48.addMapPoint(37.711010, -122.428456);
        d48.addMapPoint(37.709109, -122.429743);
        d48.addMapPoint(37.708769, -122.446051);
        d48.addMapPoint(37.714473, -122.441588);
        d48.addMapPoint(37.714269, -122.436953);
        d48.addMapPoint(37.709924, -122.437554);
        d48.addMapPoint(37.715152, -122.440558);
        d48.getDistance(currentLocation);
        d48.citySection = "Southern";






        targetDistrict = "Design District";
        targetLocation.setLatitude(37.7689239);
        targetLocation.setLongitude(-122.3995323);
        targetZoomLevel = "16z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d49 = new District(targetDistrict,targetDistance,targetZoomLevel,
                targetLocation.getLatitude(),targetLocation.getLongitude());
        targetDistricts[49] = d49;
        d49.addMapPoint(37.771400, -122.401678);
        d49.addMapPoint(37.769976, -122.403738);
        d49.addMapPoint(37.766651, -122.403051);
        d49.addMapPoint(37.766922, -122.399447);
        d49.addMapPoint(37.768958, -122.400648);
        d49.addMapPoint(37.769772, -122.399704);
        d49.addMapPoint(37.768008, -122.397644);
        d49.addMapPoint(37.766855, -122.396013);
        d49.addMapPoint(37.768211, -122.403395);
        d49.addMapPoint(37.767737, -122.399790);
        d49.addMapPoint(37.770247, -122.401678);
        d49.getDistance(currentLocation);
        d49.citySection = "";





        targetDistrict = "Diamond Heights";
        targetLocation.setLatitude(37.7410467);
        targetLocation.setLongitude(-122.4435918);
        targetZoomLevel = "15z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d50 = new District(targetDistrict,targetDistance,targetZoomLevel,
                targetLocation.getLatitude(),targetLocation.getLongitude());
        targetDistricts[50] = d50;
        d50.addMapPoint(37.737347, -122.441360);
        d50.addMapPoint(37.745390, -122.450115);
        d50.addMapPoint(37.746069, -122.441875);
        d50.addMapPoint(37.741861, -122.437412);
        d50.addMapPoint(37.740402, -122.443506);
        d50.addMapPoint(37.736295, -122.442261);
        d50.addMapPoint(37.743660, -122.438399);
        d50.addMapPoint(37.745933, -122.443420);
        d50.addMapPoint(37.742709, -122.446939);
        d50.addMapPoint(37.742268, -122.440716);
        d50.getDistance(currentLocation);
        d50.citySection = "Southern";





        targetDistrict = "Dogpatch";
        targetLocation.setLatitude(37.7572244);
        targetLocation.setLongitude(-122.3895594);
        targetZoomLevel = "14z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d51 = new District(targetDistrict,targetDistance,targetZoomLevel,
                targetLocation.getLatitude(),targetLocation.getLongitude());
        targetDistricts[51] = d51;
        d51.addMapPoint(37.763908, -122.391791);
        d51.addMapPoint(37.764180, -122.387070);
        d51.addMapPoint(37.760363, -122.391834);
        d51.addMapPoint(37.758938, -122.387800);
        d51.addMapPoint(37.757038, -122.391662);
        d51.addMapPoint(37.755884, -122.387457);
        d51.addMapPoint(37.754595, -122.390890);
        d51.addMapPoint(37.753441, -122.387113);
        d51.addMapPoint(37.750794, -122.390976);
        d51.addMapPoint(37.750727, -122.387113);
        d51.addMapPoint(37.751609, -122.388916);
        d51.addMapPoint(37.754255, -122.389087);
        d51.addMapPoint(37.755579, -122.389516);
        d51.addMapPoint(37.757207, -122.389860);
        d51.addMapPoint(37.763722, -122.389860);
        d51.addMapPoint(37.761957, -122.390375);
        d51.addMapPoint(37.760600, -122.389345);
        d51.addMapPoint(37.758056, -122.389688);
        d51.addMapPoint(37.756427, -122.387886);
        d51.addMapPoint(37.754323, -122.387542);
        d51.getDistance(currentLocation);
        d51.citySection = "Southern";


        targetDistrict = "Dolores Heights";
        targetLocation.setLatitude(37.7569472);
        targetLocation.setLongitude(-122.4334385);
        targetZoomLevel = "15z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d52 = new District(targetDistrict,targetDistance,targetZoomLevel,
                targetLocation.getLatitude(),targetLocation.getLongitude());
        targetDistricts[52] = d52;
        d52.addMapPoint(37.760187, -122.438588);
        d52.addMapPoint(37.760629, -122.435198);
        d52.addMapPoint(37.761070, -122.428804);
        d52.addMapPoint(37.758321, -122.428374);
        d52.addMapPoint(37.757880, -122.434039);
        d52.addMapPoint(37.757643, -122.438417);
        d52.addMapPoint(37.755030, -122.429147);
        d52.addMapPoint(37.753843, -122.434168);
        d52.addMapPoint(37.752960, -122.438116);
        d52.addMapPoint(37.753266, -122.434511);
        d52.addMapPoint(37.753435, -122.430091);
        d52.addMapPoint(37.753537, -122.427988);
        d52.addMapPoint(37.755709, -122.437129);
        d52.addMapPoint(37.758220, -122.430477);
        d52.addMapPoint(37.757982, -122.435456);
        d52.addMapPoint(37.759373, -122.435756);
        d52.addMapPoint(37.758898, -122.432022);
        d52.addMapPoint(37.757151, -122.432838);
        d52.addMapPoint(37.755251, -122.436099);
        d52.addMapPoint(37.755183, -122.429490);
        d52.getDistance(currentLocation);
        d52.citySection = "";

        targetDistrict = "Duboce Triangle";
        targetLocation.setLatitude(37.7661594);
        targetLocation.setLongitude(-122.4311157);
        targetZoomLevel = "16z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d53 = new District(targetDistrict,targetDistance,targetZoomLevel,
                targetLocation.getLatitude(),targetLocation.getLongitude());
        targetDistricts[53] = d53;
        d53.addMapPoint(37.769637, -122.434549);
        d53.addMapPoint(37.769976, -122.432618);
        d53.addMapPoint(37.768941, -122.435257);
        d53.addMapPoint(37.769331, -122.427382);
        d53.addMapPoint(37.763242, -122.434914);
        d53.addMapPoint(37.765142, -122.434978);
        d53.addMapPoint(37.766702, -122.435257);
        d53.addMapPoint(37.769060, -122.431674);
        d53.addMapPoint(37.768806, -122.428884);
        d53.addMapPoint(37.767160, -122.430043);
        d53.addMapPoint(37.765430, -122.432189);
        d53.addMapPoint(37.764056, -122.434120);
        d53.addMapPoint(37.765854, -122.433304);
        d53.addMapPoint(37.767567, -122.431266);
        d53.addMapPoint(37.768483, -122.430257);
        d53.addMapPoint(37.769399, -122.429699);
        d53.addMapPoint(37.769179, -122.433326);
        d53.addMapPoint(37.769128, -122.434763);
        d53.addMapPoint(37.764989, -122.434420);
        d53.addMapPoint(37.766855, -122.430193);
        d53.getDistance(currentLocation);
        d53.citySection = "Western Addition";




        targetDistrict = "Embarcadero";
        targetLocation.setLatitude(37.7946761);
        targetLocation.setLongitude(-122.3969865);
        targetZoomLevel = "13z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d54 = new District(targetDistrict,targetDistance,targetZoomLevel,
                targetLocation.getLatitude(),targetLocation.getLongitude());
        targetDistricts[54] = d54;
        d54.addMapPoint(37.808308, -122.408745);
        d54.addMapPoint(37.805866, -122.403939);
        d54.addMapPoint(37.804544, -122.402265);
        d54.addMapPoint(37.802713, -122.400806);
        d54.addMapPoint(37.801153, -122.399261);
        d54.addMapPoint(37.799525, -122.397888);
        d54.addMapPoint(37.799932, -122.397974);
        d54.addMapPoint(37.798576, -122.396858);
        d54.addMapPoint(37.780296, -122.388918);
        d54.addMapPoint(37.782331, -122.388403);
        d54.addMapPoint(37.785180, -122.388232);
        d54.addMapPoint(37.787351, -122.388060);
        d54.addMapPoint(37.789928, -122.389090);
        d54.addMapPoint(37.792641, -122.391322);
        d54.addMapPoint(37.792641, -122.391322);
        d54.addMapPoint(37.796880, -122.395656);
        d54.addMapPoint(37.795999, -122.395055);
        d54.addMapPoint(37.784061, -122.388103);
        d54.addMapPoint(37.779584, -122.388017);
        d54.addMapPoint(37.803120, -122.400549);

        d54.getDistance(currentLocation);
        d54.citySection = "";




        targetDistrict = "Eureka Valley";
        targetLocation.setLatitude(37.7577874);
        targetLocation.setLongitude(-122.4418521);
        targetZoomLevel = "15z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d55 = new District(targetDistrict,targetDistance,targetZoomLevel,
                targetLocation.getLatitude(),targetLocation.getLongitude());
        targetDistricts[55] = d55;
        d55.addMapPoint(37.760671, -122.439985);
        d55.addMapPoint(37.760604, -122.441015);
        d55.addMapPoint(37.760230, -122.443461);
        d55.addMapPoint(37.759111, -122.443848);
        d55.addMapPoint(37.757618, -122.442431);
        d55.addMapPoint(37.756803, -122.441702);
        d55.addMapPoint(37.754428, -122.441562);
        d55.addMapPoint(37.754276, -122.439910);
        d55.addMapPoint(37.754310, -122.438859);
        d55.addMapPoint(37.756481, -122.439159);
        d55.addMapPoint(37.757906, -122.439352);
        d55.addMapPoint(37.759772, -122.439588);
        d55.addMapPoint(37.761265, -122.439674);
        d55.addMapPoint(37.759670, -122.441477);
        d55.addMapPoint(37.758229, -122.441047);
        d55.addMapPoint(37.756498, -122.440232);
        d55.addMapPoint(37.755328, -122.439975);
        d55.addMapPoint(37.757618, -122.442378);
        d55.addMapPoint(37.760502, -122.442013);
        d55.addMapPoint(37.758992, -122.444695);
        d55.getDistance(currentLocation);
        d55.citySection = "Southern";



        targetDistrict = "Excelsior";
        targetLocation.setLatitude(37.724134);
        targetLocation.setLongitude(-122.4209454);
        targetZoomLevel = "14z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d56 = new District(targetDistrict,targetDistance,targetZoomLevel,
                targetLocation.getLatitude(),targetLocation.getLongitude());
        targetDistricts[56] = d56;
        d56.addMapPoint(37.734385, -122.407170);
        d56.addMapPoint(37.729497, -122.404251);
        d56.addMapPoint(37.724338, -122.402363);
        d56.addMapPoint(37.722301, -122.411032);
        d56.addMapPoint(37.720604, -122.424078);
        d56.addMapPoint(37.716802, -122.427683);
        d56.addMapPoint(37.715104, -122.434120);
        d56.addMapPoint(37.717073, -122.439013);
        d56.addMapPoint(37.722640, -122.435322);
        d56.addMapPoint(37.727528, -122.431975);
        d56.addMapPoint(37.730855, -122.427597);
        d56.addMapPoint(37.730651, -122.421761);
        d56.addMapPoint(37.731941, -122.413006);
        d56.addMapPoint(37.730040, -122.409444);
        d56.addMapPoint(37.725424, -122.407212);
        d56.addMapPoint(37.725763, -122.419658);
        d56.addMapPoint(37.727121, -122.428241);
        d56.addMapPoint(37.718703, -122.435966);
        d56.addMapPoint(37.719382, -122.430644);
        d56.addMapPoint(37.725628, -122.424808);
        d56.getDistance(currentLocation);
        d56.citySection = "Southern";



        targetDistrict = "Fillmore";
        targetLocation.setLatitude(37.7862924);
        targetLocation.setLongitude(-122.435776);
        targetZoomLevel = "15z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d57 = new District(targetDistrict,targetDistance,targetZoomLevel,
                targetLocation.getLatitude(),targetLocation.getLongitude());
        targetDistricts[57] = d57;
        d57.addMapPoint(37.778284, -122.420271);
        d57.addMapPoint(37.777877, -122.423275);
        d57.addMapPoint(37.777063, -122.429798);
        d57.addMapPoint(37.776791, -122.431558);
        d57.addMapPoint(37.777843, -122.438210);
        d57.addMapPoint(37.779776, -122.437909);
        d57.addMapPoint(37.780692, -122.438253);
        d57.addMapPoint(37.783202, -122.439197);
        d57.addMapPoint(37.783948, -122.434562);
        d57.addMapPoint(37.784082, -122.432951);
        d57.addMapPoint(37.784578, -122.429592);
        d57.addMapPoint(37.784781, -122.426352);
        d57.addMapPoint(37.785120, -122.422489);
        d57.addMapPoint(37.783374, -122.421331);
        d57.addMapPoint(37.780033, -122.420966);
        d57.addMapPoint(37.781695, -122.435160);
        d57.addMapPoint(37.780338, -122.429409);
        d57.addMapPoint(37.779388, -122.425290);
        d57.addMapPoint(37.782577, -122.427178);
        d57.addMapPoint(37.782984, -122.434216);
        d57.getDistance(currentLocation);
        d57.citySection = "Western Addition";


        targetDistrict = "Financial District";
        targetLocation.setLatitude(37.7927731);
        targetLocation.setLongitude(-122.4010922);
        targetZoomLevel = "15z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d58 = new District(targetDistrict,targetDistance,targetZoomLevel,
                targetLocation.getLatitude(),targetLocation.getLongitude());
        targetDistricts[58] = d58;
        d58.addMapPoint(37.793587, -122.401307);
        d58.addMapPoint(37.798335, -122.398646);
        d58.addMapPoint(37.798131, -122.401822);
        d58.addMapPoint(37.797385, -122.406199);
        d58.addMapPoint(37.795418, -122.405942);
        d58.addMapPoint(37.792841, -122.405598);
        d58.addMapPoint(37.789653, -122.404740);
        d58.addMapPoint(37.787550, -122.404483);
        d58.addMapPoint(37.789043, -122.402594);
        d58.addMapPoint(37.790535, -122.400706);
        d58.addMapPoint(37.792637, -122.398045);
        d58.addMapPoint(37.794333, -122.395899);
        d58.addMapPoint(37.795079, -122.396844);
        d58.addMapPoint(37.797860, -122.398131);
        d58.addMapPoint(37.795825, -122.400792);
        d58.addMapPoint(37.793316, -122.401736);
        d58.addMapPoint(37.791077, -122.402508);
        d58.addMapPoint(37.789450, -122.403281);
        d58.addMapPoint(37.795757, -122.403710);
        d58.addMapPoint(37.793791, -122.403796);

        d58.getDistance(currentLocation);
        d58.citySection = "Downtown";


        targetDistrict = "*Financial District South	";
        targetLocation.setLatitude(37.7927731);
        targetLocation.setLongitude(-122.4010922);
        targetZoomLevel = "15z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d59 = new District(targetDistrict,targetDistance,targetZoomLevel,
                targetLocation.getLatitude(),targetLocation.getLongitude());
        targetDistricts[59] = d59;
        d59.addMapPoint(37.789169, -122.399116);
        d59.addMapPoint(37.787948, -122.390919);
        d59.addMapPoint(37.789406, -122.395683);
        d59.addMapPoint(37.786625, -122.396927);
        d59.addMapPoint(37.783522, -122.393119);
        d59.addMapPoint(37.785692, -122.396852);
        d59.addMapPoint(37.785574, -122.398934);
        d59.addMapPoint(37.787354, -122.402024);
        d59.addMapPoint(37.789694, -122.398193);
        d59.addMapPoint(37.790746, -122.397121);
        d59.addMapPoint(37.787388, -122.390125);
        d59.addMapPoint(37.786133, -122.394631);
        d59.addMapPoint(37.782572, -122.394374);
        d59.addMapPoint(37.787490, -122.402592);
        d59.addMapPoint(37.785251, -122.396927);
        d59.addMapPoint(37.782945, -122.392979);
        d59.addMapPoint(37.788507, -122.400704);
        d59.addMapPoint(37.787829, -122.392808);
        d59.addMapPoint(37.784743, -122.389514);
        d59.addMapPoint(37.792814, -122.396488);



        d59.getDistance(currentLocation);
        d59.citySection = "";




        targetDistrict = "Fisherman's Wharf";
        targetLocation.setLatitude(37.8081103);
        targetLocation.setLongitude(-122.416631);
        targetZoomLevel = "15z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d60 = new District(targetDistrict,targetDistance,targetZoomLevel,
                targetLocation.getLatitude(),targetLocation.getLongitude());
        targetDistricts[60] = d60;
        d60.addMapPoint(37.807025, -122.407404);
        d60.addMapPoint(37.806618, -122.410494);
        d60.addMapPoint(37.806076, -122.415644);
        d60.addMapPoint(37.805601, -122.418734);
        d60.addMapPoint(37.805059, -122.423712);
        d60.addMapPoint(37.806618, -122.419764);
        d60.addMapPoint(37.806822, -122.418648);
        d60.addMapPoint(37.807297, -122.414528);
        d60.addMapPoint(37.807839, -122.411953);
        d60.addMapPoint(37.807839, -122.408263);
        d60.addMapPoint(37.808382, -122.410666);
        d60.addMapPoint(37.809263, -122.416502);
        d60.addMapPoint(37.810755, -122.418734);
        d60.addMapPoint(37.808382, -122.418734);
        d60.addMapPoint(37.808110, -122.421137);
        d60.addMapPoint(37.806754, -122.425429);
        d60.addMapPoint(37.805737, -122.418133);
        d60.addMapPoint(37.806347, -122.415987);
        d60.addMapPoint(37.810823, -122.410837);
        d60.addMapPoint(37.807636, -122.409035);
        d60.getDistance(currentLocation);
        d60.citySection = "North of Downtown";






        targetDistrict = "Forest Hill";
        targetLocation.setLatitude(37.7480526);
        targetLocation.setLongitude(-122.4646272);
        targetZoomLevel = "15z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d61 = new District(targetDistrict,targetDistance,targetZoomLevel,
                targetLocation.getLatitude(),targetLocation.getLongitude());
        targetDistricts[61] = d61;
        d61.addMapPoint(37.751853, -122.464262);
        d61.addMapPoint(37.750801, -122.464262);
        d61.addMapPoint(37.749783, -122.462031);
        d61.addMapPoint(37.748494, -122.463876);
        d61.addMapPoint(37.748053, -122.460915);
        d61.addMapPoint(37.747204, -122.467266);
        d61.addMapPoint(37.745983, -122.463490);
        d61.addMapPoint(37.745677, -122.467052);
        d61.addMapPoint(37.744252, -122.469198);
        d61.addMapPoint(37.744048, -122.466108);
        d61.addMapPoint(37.748256, -122.468425);
        d61.addMapPoint(37.747917, -122.459542);
        d61.addMapPoint(37.750598, -122.461258);
        d61.addMapPoint(37.752667, -122.464477);
        d61.addMapPoint(37.750971, -122.466322);
        d61.addMapPoint(37.745304, -122.461902);
        d61.addMapPoint(37.745168, -122.464734);
        d61.addMapPoint(37.746526, -122.466880);
        d61.addMapPoint(37.745440, -122.468082);
        d61.addMapPoint(37.748188, -122.462675);
        d61.getDistance(currentLocation);
        d61.citySection = "Outside Lands";






        targetDistrict = "Forest Knolls";
        targetLocation.setLatitude(37.7574369);
        targetLocation.setLongitude(-122.4573887);
        targetZoomLevel = "15z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d62 = new District(targetDistrict,targetDistance,targetZoomLevel,
                targetLocation.getLatitude(),targetLocation.getLongitude());
        targetDistricts[62] = d62;
        d62.addMapPoint(37.762594, -122.457861);
        d62.addMapPoint(37.760423, -122.457861);
        d62.addMapPoint(37.759133, -122.457947);
        d62.addMapPoint(37.757233, -122.457517);
        d62.addMapPoint(37.755130, -122.458032);
        d62.addMapPoint(37.753433, -122.458032);
        d62.addMapPoint(37.752144, -122.458032);
        d62.addMapPoint(37.753637, -122.461294);
        d62.addMapPoint(37.757573, -122.461895);
        d62.addMapPoint(37.759812, -122.460693);
        d62.addMapPoint(37.762255, -122.459577);
        d62.addMapPoint(37.762662, -122.457346);
        d62.addMapPoint(37.758251, -122.454599);
        d62.addMapPoint(37.756962, -122.454771);
        d62.addMapPoint(37.755265, -122.455286);
        d62.addMapPoint(37.754112, -122.455458);
        d62.addMapPoint(37.752890, -122.456573);
        d62.addMapPoint(37.751804, -122.456316);
        d62.addMapPoint(37.754790, -122.458204);
        d62.addMapPoint(37.756351, -122.463697);
        d62.addMapPoint(37.755130, -122.460650);

        d62.getDistance(currentLocation);
        d62.citySection = "";





        targetDistrict = "Glen Park";
        targetLocation.setLatitude(37.7379098);
        targetLocation.setLongitude(-122.4321038);
        targetZoomLevel = "15z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d63 = new District(targetDistrict,targetDistance,targetZoomLevel,
                targetLocation.getLatitude(),targetLocation.getLongitude());
        targetDistricts[63] = d63;
        d63.addMapPoint(37.741728,-122.424744);
        d63.addMapPoint(37.741253,-122.434357);
        d63.addMapPoint(37.735110,-122.439678);
        d63.addMapPoint(37.733684,-122.432512);
        d63.getDistance(currentLocation);
        d63.citySection = "Southern";





        targetDistrict = "Golden Gate Heights";
        targetLocation.setLatitude(37.754067);
        targetLocation.setLongitude(-122.4700568);
        targetZoomLevel = "15z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d64 = new District(targetDistrict,targetDistance,targetZoomLevel,
                targetLocation.getLatitude(),targetLocation.getLongitude());
        targetDistricts[64] = d64;
        d64.addMapPoint(37.758885, -122.471730);
        d64.addMapPoint(37.758003, -122.471645);
        d64.addMapPoint(37.756510, -122.470786);
        d64.addMapPoint(37.754814, -122.470357);
        d64.addMapPoint(37.754678, -122.470100);
        d64.addMapPoint(37.752913, -122.469671);
        d64.addMapPoint(37.751149, -122.469327);
        d64.addMapPoint(37.749249, -122.468726);
        d64.addMapPoint(37.749181, -122.471816);
        d64.addMapPoint(37.750810, -122.472589);
        d64.addMapPoint(37.754101, -122.472911);
        d64.addMapPoint(37.756306, -122.472653);
        d64.addMapPoint(37.758817, -122.473039);
        d64.addMapPoint(37.757867, -122.469349);
        d64.addMapPoint(37.756408, -122.469263);
        d64.addMapPoint(37.752846, -122.46797);
        d64.addMapPoint(37.750945, -122.466731);
        d64.addMapPoint(37.749215, -122.467332);
        d64.addMapPoint(37.750911, -122.467932);
        d64.addMapPoint(37.753863, -122.469885);
        d64.getDistance(currentLocation);
        d64.citySection = "";


        targetDistrict = "Haight-Ashbury";
        targetLocation.setLatitude(37.7699298);
        targetLocation.setLongitude(-122.4469157);
        targetZoomLevel = "17z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d65 = new District(targetDistrict,targetDistance,targetZoomLevel,
                targetLocation.getLatitude(),targetLocation.getLongitude());
        targetDistricts[65] = d65;
        d65.addMapPoint(37.773759, -122.437974);
        d65.addMapPoint(37.772809, -122.440484);
        d65.addMapPoint(37.771317, -122.443896);
        d65.addMapPoint(37.770435, -122.444754);
        d65.addMapPoint(37.768671, -122.448274);
        d65.addMapPoint(37.767653, -122.451449);
        d65.addMapPoint(37.766771, -122.452372);
        d65.addMapPoint(37.768467, -122.452801);
        d65.addMapPoint(37.770231, -122.453316);
        d65.addMapPoint(37.770604, -122.448746);
        d65.addMapPoint(37.769926, -122.446718);
        d65.addMapPoint(37.769587, -122.443446);
        d65.addMapPoint(37.769383, -122.440356);
        d65.addMapPoint(37.769417, -122.436751);
        d65.addMapPoint(37.771961, -122.438982);
        d65.addMapPoint(37.771215, -122.445505);
        d65.addMapPoint(37.770469, -122.452029);
        d65.addMapPoint(37.767619, -122.450355);
        d65.addMapPoint(37.767958, -122.447308);
        d65.addMapPoint(37.768433, -122.444948);

        d65.getDistance(currentLocation);
        d65.citySection = "Western Addition";


        targetDistrict = "Hayes Valley";
        targetLocation.setLatitude(37.7746071);
        targetLocation.setLongitude(-122.4260718);
        targetZoomLevel = "15z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d66 = new District(targetDistrict,targetDistance,targetZoomLevel,
                targetLocation.getLatitude(),targetLocation.getLongitude());
        targetDistricts[66] = d66;
        d66.addMapPoint(37.775557, -122.424978);
        d66.addMapPoint(37.778474, -122.423733);
        d66.addMapPoint(37.776914, -122.425020);
        d66.addMapPoint(37.774743, -122.426179);
        d66.addMapPoint(37.773182, -122.426952);
        d66.addMapPoint(37.772063, -122.427252);
        d66.addMapPoint(37.771554, -122.427467);
        d66.addMapPoint(37.772606, -122.423347);
        d66.addMapPoint(37.773657, -122.425664);
        d66.addMapPoint(37.774302, -122.427381);
        d66.addMapPoint(37.775727, -122.427681);
        d66.addMapPoint(37.777049, -122.428153);
        d66.addMapPoint(37.777694, -122.429398);
        d66.addMapPoint(37.775286, -122.428239);
        d66.addMapPoint(37.773420, -122.427982);
        d66.addMapPoint(37.771927, -122.427853);
        d66.addMapPoint(37.773047, -122.422446);
        d66.addMapPoint(37.774471, -122.423046);
        d66.addMapPoint(37.776201, -122.423433);
        d66.addMapPoint(37.777287, -122.424162);
        d66.getDistance(currentLocation);
        d66.citySection = "Western Addition";


        targetDistrict = "Hunters Point";
        targetLocation.setLatitude(37.727865);
        targetLocation.setLongitude(-122.3728739);
        targetZoomLevel = "13z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d67 = new District(targetDistrict,targetDistance,targetZoomLevel,
                targetLocation.getLatitude(),targetLocation.getLongitude());
        targetDistricts[67] = d67;
        d67.addMapPoint(37.746396, -122.386349);
        d67.addMapPoint(37.740694, -122.388409);
        d67.addMapPoint(37.734042, -122.382229);
        d67.addMapPoint(37.729969, -122.380684);
        d67.addMapPoint(37.725625, -122.376736);
        d67.addMapPoint(37.720329, -122.374505);
        d67.addMapPoint(37.720329, -122.383603);
        d67.addMapPoint(37.717614, -122.386178);
        d67.addMapPoint(37.715169, -122.382573);
        d67.addMapPoint(37.709737, -122.382744);
        d67.addMapPoint(37.719786, -122.373646);
        d67.addMapPoint(37.719107, -122.369355);
        d67.addMapPoint(37.722773, -122.367810);
        d67.addMapPoint(37.727254, -122.364033);
        d67.addMapPoint(37.731327, -122.365235);
        d67.addMapPoint(37.727390, -122.358712);
        d67.addMapPoint(37.720737, -122.363003);
        d67.addMapPoint(37.718293, -122.365063);
        d67.addMapPoint(37.745988, -122.379655);
        d67.addMapPoint(37.741645, -122.378281);
        d67.addMapPoint(37.736079, -122.377251);
        d67.getDistance(currentLocation);
        d67.citySection = "Southern";



        targetDistrict = "India Basin";
        targetLocation.setLatitude(37.7409911);
        targetLocation.setLongitude(-122.3812691);
        targetZoomLevel = "15z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d68 = new District(targetDistrict,targetDistance,targetZoomLevel,
                targetLocation.getLatitude(),targetLocation.getLongitude());
        targetDistricts[68] = d68;
        d68.addMapPoint(37.745878, -122.386977);
        d68.addMapPoint(37.743774, -122.384659);
        d68.addMapPoint(37.740516, -122.382685);
        d68.addMapPoint(37.738955, -122.381312);
        d68.addMapPoint(37.737190, -122.378222);
        d68.addMapPoint(37.736647, -122.377364);
        d68.addMapPoint(37.737054, -122.381140);
        d68.addMapPoint(37.737326, -122.383029);
        d68.addMapPoint(37.738683, -122.385003);
        d68.addMapPoint(37.740991, -122.388093);
        d68.addMapPoint(37.742620, -122.387749);
        d68.addMapPoint(37.743910, -122.384831);
        d68.addMapPoint(37.744453, -122.383715);
        d68.addMapPoint(37.741602, -122.379424);
        d68.addMapPoint(37.737326, -122.382771);
        d68.addMapPoint(37.739905, -122.387492);
        d68.addMapPoint(37.739362, -122.381999);
        d68.addMapPoint(37.741398, -122.387063);
        d68.addMapPoint(37.737326, -122.381055);
        d68.addMapPoint(37.737190, -122.379510);
        d68.getDistance(currentLocation);
        d68.citySection = "";




		    /*
		     * The Ingleside neighborhood is located in the southwestern part of
		     * San Francisco, near City College.
		     * It is bordered by
		     * Ocean Avenue to the north,
		     * Ashton Avenue to the west,
		     * Lakeview Avenue to the south and
		     * Interstate 280 to the east.
		     * The Ingleside Library is located on Ocean Avenue at Plymouth, and the Bay Area Rapid Transit Balboa Park Station is at the edge of the neighborhood on Geneva Avenue at I-280 toward San Jose Ave. The neighborhood is served by 3 Muni rail lines and several bus lines. The commercial center of the neighborhood runs along Ocean Avenue, which offers a range of shopping and dining institutions. The Lakeview and Ashton Mini Park is capped by a rocky outcropping providing views of the ocean and all directions.
		     */

		    /*
		     * my border points:
		     * 37.725269,-122.4622138 (Ocean and Ashton
		     *  37.718223, -122.462203 (Lakeview and almost Ashton)
		     *  37.718337, -122.448819 (not great border point)
		     *  37.722945, -122.448358 probabbly ok border piint of Ocean and 280
		     */
        //targetDistrict = "Outside Lands - Ingleside";
        //targetLocation.setLatitude(37.7207279);
        //targetLocation.setLongitude(-122.4549376);
        //targetZoomLevel = "16z";
        //actually this is a well defined neighborhood by google
        //It is the Ingleside Terraces that need to be perfected
        targetDistrict = "Ingleside";
        targetLocation.setLatitude(37.7207279);
        targetLocation.setLongitude(-122.4549376);
        targetZoomLevel = "15z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d69 = new District(targetDistrict,targetDistance,targetZoomLevel,
                targetLocation.getLatitude(),targetLocation.getLongitude());
        targetDistricts[69] = d69;
        d69.addMapPoint(37.724530, -122.461353);
        d69.addMapPoint(37.722222, -122.458221);
        d69.addMapPoint(37.721780, -122.456182);
        d69.addMapPoint(37.720151, -122.453264);
        d69.addMapPoint(37.718386, -122.450861);
        d69.addMapPoint(37.716756, -122.450603);
        d69.addMapPoint(37.718318, -122.449659);
        d69.addMapPoint(37.720898, -122.448801);
        d69.addMapPoint(37.720626, -122.450431);
        d69.addMapPoint(37.720083, -122.453264);
        d69.addMapPoint(37.719404, -122.457212);
        d69.addMapPoint(37.719065, -122.461246);
        d69.addMapPoint(37.721237, -122.459358);
        d69.addMapPoint(37.723070, -122.456611);
        d69.addMapPoint(37.722323, -122.453092);
        d69.addMapPoint(37.721780, -122.450861);
        d69.addMapPoint(37.720151, -122.450260);
        d69.addMapPoint(37.721577, -122.453178);
        d69.addMapPoint(37.721848, -122.455152);
        d69.addMapPoint(37.722527, -122.457212);
        d69.getDistance(currentLocation);
        d69.citySection = "Outside Lands - ";





        //targetDistrict = "Outside Lands - *Ingleside Terraces";
        //targetLocation.setLatitude(37.7207279);
        //targetLocation.setLongitude(-122.4549376);
        //targetZoomLevel = "15z";

			/*
			 * This location was previously confused with the Ingleside Neighborhood and
			 * had the same coordinates
			 *
			 * It is now distinct, the location of the old Ingleside Racetrack,
			 * new HOA and condo development
			 * Zoom coordinates and borders may not be correct,
			 * but centroid should be about right
			 * at 37.724732,-122.468768,17z
			 */
        targetDistrict = "Ingleside Terraces";
        targetLocation.setLatitude(37.724732);
        targetLocation.setLongitude(-122.468768);
        targetZoomLevel = "17z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d70 = new District(targetDistrict,targetDistance,targetZoomLevel,
                targetLocation.getLatitude(),targetLocation.getLongitude());
        targetDistricts[70] = d70;
        d70.addMapPoint(37.721984, -122.462834);
        d70.addMapPoint(37.722120, -122.465495);
        d70.addMapPoint(37.721984, -122.467383);
        d70.addMapPoint(37.721984, -122.470730);
        d70.addMapPoint(37.723681, -122.471675);
        d70.addMapPoint(37.726736, -122.471331);
        d70.addMapPoint(37.729655, -122.471245);
        d70.addMapPoint(37.727279, -122.467726);
        d70.addMapPoint(37.725684, -122.464315);
        d70.addMapPoint(37.724360, -122.462512);
        d70.addMapPoint(37.722866, -122.462770);
        d70.addMapPoint(37.722188, -122.464272);
        d70.addMapPoint(37.722663, -122.465130);
        d70.addMapPoint(37.723104, -122.465902);
        d70.addMapPoint(37.724258, -122.466332);
        d70.addMapPoint(37.724496, -122.467394);
        d70.addMapPoint(37.725327, -122.469797);
        d70.addMapPoint(37.725209, -122.467329);
        d70.addMapPoint(37.724988, -122.466235);
        d70.addMapPoint(37.725243, -122.464733);


        d70.getDistance(currentLocation);
        d70.citySection = "Outside Lands";




        targetDistrict = "Inner Richmond";
        targetLocation.setLatitude(37.7809555);
        targetLocation.setLongitude(-122.462596);
        targetZoomLevel = "14z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d71 = new District(targetDistrict,targetDistance,targetZoomLevel,
                targetLocation.getLatitude(),targetLocation.getLongitude());
        targetDistricts[71] = d71;
        d71.addMapPoint(37.776614, -122.448262);
        d71.addMapPoint(37.777971, -122.454957);
        d71.addMapPoint(37.779599, -122.461309);
        d71.addMapPoint(37.781905, -122.467317);
        d71.addMapPoint(37.784076, -122.471093);
        d71.addMapPoint(37.785975, -122.477273);
        d71.addMapPoint(37.784212, -122.477616);
        d71.addMapPoint(37.781770, -122.478003);
        d71.addMapPoint(37.778920, -122.477659);
        d71.addMapPoint(37.774986, -122.476930);
        d71.addMapPoint(37.774171, -122.476415);
        d71.addMapPoint(37.775935, -122.473325);
        d71.addMapPoint(37.777699, -122.469548);
        d71.addMapPoint(37.780006, -122.466115);
        d71.addMapPoint(37.782991, -122.461137);
        d71.addMapPoint(37.786111, -122.466458);
        d71.addMapPoint(37.786247, -122.460622);
        d71.addMapPoint(37.780956, -122.453755);
        d71.addMapPoint(37.781363, -122.449121);
        d71.addMapPoint(37.779599, -122.448262);
        d71.addMapPoint(37.776478, -122.449807);
        d71.addMapPoint(37.775800, -122.455472);
        d71.addMapPoint(37.775935, -122.461995);
        d71.addMapPoint(37.775935, -122.467832);
        d71.addMapPoint(37.778378, -122.462339);
        d71.addMapPoint(37.786247, -122.472123);
        d71.addMapPoint(37.785704, -122.467488);
        d71.addMapPoint(37.785975, -122.461995);
        d71.addMapPoint(37.779463, -122.454785);
        d71.addMapPoint(37.775935, -122.462510);
        d71.getDistance(currentLocation);
        d71.citySection = "";






        targetDistrict = "**Inner Sunset";//Lets dispense with the asterisks now
        targetDistrict = "Inner Sunset";
        targetLocation.setLatitude(37.763721);
        targetLocation.setLongitude(-122.465405);
        targetZoomLevel = "17z";//probably wrong now
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d72 = new District(targetDistrict,targetDistance,targetZoomLevel,
                targetLocation.getLatitude(),targetLocation.getLongitude());
        targetDistricts[72] = d72;
        d72.addMapPoint(37.765307,-122.477113);
        d72.addMapPoint(37.765918,-122.458145);
        d72.addMapPoint(37.753071,-122.463638);
        d72.addMapPoint(37.747573,-122.470075);
        d72.addMapPoint(37.738264,-122.470290);
        d72.addMapPoint(37.764583, -122.475891);
        d72.addMapPoint(37.763633, -122.473144);
        d72.addMapPoint(37.762140, -122.470054);
        d72.addMapPoint(37.760647, -122.466278);
        d72.addMapPoint(37.760376, -122.464046);
        d72.addMapPoint(37.757119, -122.460441);
        d72.addMapPoint(37.754947, -122.457180);
        d72.addMapPoint(37.758340, -122.455635);
        d72.addMapPoint(37.760919, -122.454777);
        d72.addMapPoint(37.762954, -122.45391);
        d72.addMapPoint(37.765126, -122.456836);
        d72.addMapPoint(37.763769, -122.460956);
        d72.addMapPoint(37.762412, -122.464561);
        d72.addMapPoint(37.759833, -122.466278);
        d72.addMapPoint(37.758883, -122.465763);
        d72.addMapPoint(37.756576, -122.467823);
        d72.addMapPoint(37.755354, -122.468338);
        d72.addMapPoint(37.753047, -122.470226);
        d72.addMapPoint(37.751690, -122.472286);
        d72.addMapPoint(37.750740, -122.473831);
        d72.addMapPoint(37.749247, -122.475033);
        d72.addMapPoint(37.752640, -122.475033);
        d72.addMapPoint(37.755490, -122.474689);
        d72.addMapPoint(37.757526, -122.474861);
        d72.addMapPoint(37.760512, -122.474174);
        d72.addMapPoint(37.764040, -122.476406);
        d72.addMapPoint(37.763633, -122.477093);
        d72.addMapPoint(37.763904, -122.471771);
        d72.addMapPoint(37.764040, -122.472629);
        d72.addMapPoint(37.764583, -122.461986);

        d72.getDistance(currentLocation);
        d72.citySection = "";








        targetDistrict = "**Irish Hill";
        targetLocation.setLatitude(37.742655);
        targetLocation.setLongitude(-122.4904);
        targetZoomLevel = "17z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d73 = new District(targetDistrict,targetDistance,targetZoomLevel,
                targetLocation.getLatitude(),targetLocation.getLongitude());
        targetDistricts[73] = d73;
        d73.getDistance(currentLocation);
        d73.citySection = "";





        targetDistrict = "Islais Creek (New Butchertown)";
        targetLocation.setLatitude(37.748333);
        targetLocation.setLongitude(-122.375556);
        targetZoomLevel = "17z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d74 = new District(targetDistrict,targetDistance,targetZoomLevel,
                targetLocation.getLatitude(),targetLocation.getLongitude());
        targetDistricts[74] = d74;
        d74.addMapPoint(37.750015, -122.387874);
        d74.addMapPoint(37.749854, -122.389838);
        d74.addMapPoint(37.749803, -122.391587);
        d74.addMapPoint(37.747894, -122.387628);
        d74.addMapPoint(37.748497, -122.392059);
        d74.addMapPoint(37.739860, -122.399252);
        d74.addMapPoint(37.738096, -122.396114);
        d74.addMapPoint(37.736229, -122.392852);
        d74.addMapPoint(37.735245, -122.391007);
        d74.addMapPoint(37.737281, -122.390363);
        d74.addMapPoint(37.739963, -122.389376);
        d74.addMapPoint(37.741863, -122.388818);
        d74.addMapPoint(37.745223, -122.387574);
        d74.addMapPoint(37.746750, -122.387660);
        d74.addMapPoint(37.748311, -122.387831);
        d74.addMapPoint(37.746207, -122.393239);
        d74.addMapPoint(37.744374, -122.394955);
        d74.addMapPoint(37.742542, -122.396886);
        d74.addMapPoint(37.741150, -122.397916);
        d74.addMapPoint(37.740879, -122.392466);
        d74.addMapPoint(37.743831, -122.391093);
        d74.addMapPoint(37.746071, -122.390320);
        d74.getDistance(currentLocation);
        d74.citySection = "";




        targetDistrict = "Jackson Square";
        targetLocation.setLatitude(37.796689);
        targetLocation.setLongitude(-122.4035183);
        targetZoomLevel = "17z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d75 = new District(targetDistrict,targetDistance,targetZoomLevel,
                targetLocation.getLatitude(),targetLocation.getLongitude());
        targetDistricts[75] = d75;
        d75.getDistance(currentLocation);
        d75.citySection = "";




        targetDistrict = "Japantown";
        targetLocation.setLatitude(37.785614);
        targetLocation.setLongitude(-122.4306374);
        targetZoomLevel = "17z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d76 = new District(targetDistrict,targetDistance,targetZoomLevel,
                targetLocation.getLatitude(),targetLocation.getLongitude());
        targetDistricts[76] = d76;
        d76.addMapPoint(37.786530, -122.428524);
        d76.addMapPoint(37.786309, -122.430820);
        d76.addMapPoint(37.786055, -122.433073);
        d76.addMapPoint(37.785258, -122.432858);
        d76.addMapPoint(37.784630, -122.432794);
        d76.addMapPoint(37.784936, -122.430283);
        d76.addMapPoint(37.784851, -122.430283);
        d76.addMapPoint(37.785156, -122.428159);
        d76.addMapPoint(37.785818, -122.428309);
        d76.addMapPoint(37.786225, -122.428781);
        d76.addMapPoint(37.786343, -122.429361);
        d76.addMapPoint(37.785987, -122.431549);
        d76.addMapPoint(37.785767, -122.432429);
        d76.addMapPoint(37.785275, -122.432172);
        d76.addMapPoint(37.785478, -122.431335);
        d76.addMapPoint(37.785733, -122.428974);
        d76.addMapPoint(37.785004, -122.428760);
        d76.addMapPoint(37.786123, -122.429554);
        d76.addMapPoint(37.785767, -122.430627);
        d76.addMapPoint(37.785275, -122.429677);
        d76.getDistance(currentLocation);
        d76.citySection = "Western Addition";




        //This is a microhood inside Inner Richmond/Laurel Heights
        targetDistrict = "Jordan Park";
        targetLocation.setLatitude(37.7846509);
        targetLocation.setLongitude(-122.4566383);
        targetZoomLevel = "14z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d77 = new District(targetDistrict,targetDistance,targetZoomLevel,
                targetLocation.getLatitude(),targetLocation.getLongitude());
        targetDistricts[77] = d77;
        d77.addMapPoint(37.781802, -122.454375);
        d77.addMapPoint(37.782531, -122.454632);
        d77.addMapPoint(37.783464, -122.454782);
        d77.addMapPoint(37.784057, -122.454954);
        d77.addMapPoint(37.785058, -122.454868);
        d77.addMapPoint(37.786083, -122.454922);
        d77.addMapPoint(37.785811, -122.455866);
        d77.addMapPoint(37.785557, -122.456961);
        d77.addMapPoint(37.785370, -122.458034);
        d77.addMapPoint(37.785455, -122.459042);
        d77.addMapPoint(37.784760, -122.458956);
        d77.addMapPoint(37.783861, -122.459021);
        d77.addMapPoint(37.783064, -122.458806);
        d77.addMapPoint(37.781606, -122.458634);
        d77.addMapPoint(37.781673, -122.457733);
        d77.addMapPoint(37.781843, -122.456617);
        d77.addMapPoint(37.781894, -122.455566);
        d77.addMapPoint(37.782097, -122.454279);
        d77.addMapPoint(37.782691, -122.455652);
        d77.addMapPoint(37.782776, -122.456725);
        d77.addMapPoint(37.782810, -122.457862);
        d77.addMapPoint(37.783861, -122.457948);
        d77.addMapPoint(37.783929, -122.456687);
        d77.addMapPoint(37.783997, -122.455689);
        d77.addMapPoint(37.785057, -122.455813);
        d77.addMapPoint(37.785006, -122.456907);
        d77.addMapPoint(37.784853, -122.458023);
        d77.addMapPoint(37.784683, -122.456371);
        d77.addMapPoint(37.783649, -122.457315);
        d77.getDistance(currentLocation);
        d77.citySection = "";




        targetDistrict = "La Playa ";
        targetLocation.setLatitude(37.77391);
        targetLocation.setLongitude(-122.509892);
        targetZoomLevel = "17z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d78 = new District(targetDistrict,targetDistance,targetZoomLevel,
                targetLocation.getLatitude(),targetLocation.getLongitude());
        targetDistricts[78] = d78;
        d78.addMapPoint(37.760467, -122.504887);
        d78.addMapPoint(37.760458, -122.505391);
        d78.addMapPoint(37.760433, -122.505906);
        d78.addMapPoint(37.760407, -122.506485);
        d78.addMapPoint(37.760407, -122.506239);
        d78.addMapPoint(37.760390, -122.507000);
        d78.addMapPoint(37.760365, -122.507354);
        d78.addMapPoint(37.760365, -122.507537);
        d78.addMapPoint(37.760339, -122.508052);
        d78.addMapPoint(37.760314, -122.508245);
        d78.addMapPoint(37.760323, -122.508492);
        d78.addMapPoint(37.760306, -122.508535);
        d78.addMapPoint(37.760306, -122.508717);
        d78.addMapPoint(37.760399, -122.509604);
        d78.addMapPoint(37.760244, -122.509191);
        d78.addMapPoint(37.760240, -122.509374);
        d78.addMapPoint(37.760406, -122.509153);
        d78.addMapPoint(37.760450, -122.509163);
        d78.addMapPoint(37.760024, -122.509101);
        d78.addMapPoint(37.760269, -122.508959);
        d78.getDistance(currentLocation);
        d78.citySection = "";





        targetDistrict = "Laguna Honda (Forest Hill Extension)";
        targetLocation.setLatitude(37.747292);
        targetLocation.setLongitude(-122.459044);
        targetZoomLevel = "17z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d79 = new District(targetDistrict,targetDistance,targetZoomLevel,
                targetLocation.getLatitude(),targetLocation.getLongitude());
        targetDistricts[79] = d79;
        d79.addMapPoint(37.742760, -122.455441);
        d79.addMapPoint(37.741640, -122.456600);
        d79.addMapPoint(37.740622, -122.459046);
        d79.addMapPoint(37.741131, -122.459819);
        d79.addMapPoint(37.742319, -122.461106);
        d79.addMapPoint(37.743642, -122.463252);
        d79.addMapPoint(37.744796, -122.462222);
        d79.addMapPoint(37.745441, -122.461278);
        d79.addMapPoint(37.746086, -122.460033);
        d79.addMapPoint(37.746662, -122.459164);
        d79.addMapPoint(37.746951, -122.459148);
        d79.addMapPoint(37.745610, -122.458016);
        d79.addMapPoint(37.743982, -122.456857);
        d79.addMapPoint(37.743201, -122.456128);
        d79.addMapPoint(37.743303, -122.458188);
        d79.addMapPoint(37.743473, -122.459389);
        d79.addMapPoint(37.743948, -122.461020);
        d79.addMapPoint(37.744796, -122.459990);
        d79.addMapPoint(37.745034, -122.460849);
        d79.addMapPoint(37.744287, -122.461750);
        d79.getDistance(currentLocation);
        d79.citySection = "";






        targetDistrict = "Lake Street";
        targetLocation.setLatitude(37.7864845);
        targetLocation.setLongitude(-122.4705415);
        targetZoomLevel = "15z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d80 = new District(targetDistrict,targetDistance,targetZoomLevel,
                targetLocation.getLatitude(),targetLocation.getLongitude());
        targetDistricts[80] = d80;
        d80.addMapPoint(37.786824, -122.481506);
        d80.addMapPoint(37.786586, -122.478760);
        d80.addMapPoint(37.788079, -122.464812);
        d80.addMapPoint(37.788112, -122.463697);
        d80.addMapPoint(37.786790, -122.462752);
        d80.addMapPoint(37.786790, -122.459534);
        d80.addMapPoint(37.786179, -122.461594);
        d80.addMapPoint(37.786756, -122.463654);
        d80.addMapPoint(37.786179, -122.465671);
        d80.addMapPoint(37.785976, -122.467988);
        d80.addMapPoint(37.785772, -122.470048);
        d80.addMapPoint(37.785874, -122.471164);
        d80.addMapPoint(37.785874, -122.472194);
        d80.addMapPoint(37.786247, -122.473438);
        d80.addMapPoint(37.786078, -122.475584);
        d80.addMapPoint(37.786010, -122.477601);
        d80.addMapPoint(37.785874, -122.479790);
        d80.addMapPoint(37.785026, -122.481506);
        d80.addMapPoint(37.784789, -122.478631);
        d80.addMapPoint(37.784789, -122.475455);
        d80.addMapPoint(37.784755, -122.473224);
        d80.addMapPoint(37.784992, -122.469962);
        d80.addMapPoint(37.785196, -122.467859);
        d80.addMapPoint(37.785365, -122.465671);
        d80.addMapPoint(37.785738, -122.463525);
        d80.addMapPoint(37.785942, -122.461594);
        d80.addMapPoint(37.785297, -122.475970);
        d80.addMapPoint(37.784958, -122.477086);
        d80.addMapPoint(37.785365, -122.471421);
        d80.addMapPoint(37.785399, -122.468374);
        d80.getDistance(currentLocation);
        d80.citySection = "";






        targetDistrict = "Lakeside";
        targetLocation.setLatitude(37.725884);
        targetLocation.setLongitude(-122.4734583);
        targetZoomLevel = "14z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d81 = new District(targetDistrict,targetDistance,targetZoomLevel,
                targetLocation.getLatitude(),targetLocation.getLongitude());
        targetDistricts[81] = d81;
        d81.addMapPoint(37.734369, -122.473330);
        d81.addMapPoint(37.733555, -122.473244);
        d81.addMapPoint(37.718212, -122.473072);
        d81.addMapPoint(37.718654, -122.473222);
        d81.addMapPoint(37.719468, -122.473480);
        d81.addMapPoint(37.719842, -122.474381);
        d81.addMapPoint(37.721471, -122.473265);
        d81.addMapPoint(37.721811, -122.474553);
        d81.addMapPoint(37.722625, -122.473523);
        d81.addMapPoint(37.722965, -122.474338);
        d81.addMapPoint(37.724560, -122.473523);
        d81.addMapPoint(37.725511, -122.472922);
        d81.addMapPoint(37.725714, -122.474209);
        d81.addMapPoint(37.726868, -122.473308);
        d81.addMapPoint(37.728158, -122.474081);
        d81.addMapPoint(37.728803, -122.472493);
        d81.addMapPoint(37.730161, -122.472707);
        d81.addMapPoint(37.731077, -122.474081);
        d81.addMapPoint(37.731518, -122.472922);
        d81.addMapPoint(37.732129, -122.474166);
        d81.addMapPoint(37.733012, -122.473609);
        d81.addMapPoint(37.733589, -122.472321);
        d81.addMapPoint(37.733860, -122.474081);
        d81.addMapPoint(37.734234, -122.47236);
        d81.addMapPoint(37.727819, -122.473694);
        d81.addMapPoint(37.725986, -122.473351);
        d81.addMapPoint(37.724832, -122.472664);
        d81.addMapPoint(37.722965, -122.473480);
        d81.addMapPoint(37.720690, -122.474553);
        d81.addMapPoint(37.719876, -122.472836);
        d81.getDistance(currentLocation);
        d81.citySection = "";






        targetDistrict = "Lakeshore";
        targetLocation.setLatitude(37.7193379);
        targetLocation.setLongitude(-122.4896083);
        targetZoomLevel = "13z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d82 = new District(targetDistrict,targetDistance,targetZoomLevel,
                targetLocation.getLatitude(),targetLocation.getLongitude());
        targetDistricts[82] = d82;
        d82.addMapPoint(37.734544, -122.473815);
        d82.addMapPoint(37.734816, -122.482055);
        d82.addMapPoint(37.731829, -122.493728);
        d82.addMapPoint(37.731286, -122.500251);
        d82.addMapPoint(37.732372, -122.507118);
        d82.addMapPoint(37.728028, -122.502311);
        d82.addMapPoint(37.729114, -122.492355);
        d82.addMapPoint(37.730472, -122.488578);
        d82.addMapPoint(37.727485, -122.484458);
        d82.addMapPoint(37.732372, -122.474845);
        d82.addMapPoint(37.728299, -122.474845);
        d82.addMapPoint(37.726398, -122.475189);
        d82.addMapPoint(37.722597, -122.475189);
        d82.addMapPoint(37.719609, -122.475189);
        d82.addMapPoint(37.714178, -122.475532);
        d82.addMapPoint(37.706844, -122.474931);
        d82.addMapPoint(37.707795, -122.478536);
        d82.addMapPoint(37.714721, -122.478364);
        d82.addMapPoint(37.714721, -122.484716);
        d82.addMapPoint(37.716486, -122.479352);
        d82.addMapPoint(37.719542, -122.478064);
        d82.addMapPoint(37.716283, -122.474974);
        d82.addMapPoint(37.723751, -122.480639);
        d82.addMapPoint(37.728775, -122.478493);
        d82.addMapPoint(37.732508, -122.480382);
        d82.addMapPoint(37.732304, -122.487334);
        d82.addMapPoint(37.707659, -122.495187);
        d82.addMapPoint(37.705894, -122.499994);
        d82.addMapPoint(37.713227, -122.498277);
        d82.addMapPoint(37.719202, -122.500337);
        d82.addMapPoint(37.723004, -122.492269);
        d82.addMapPoint(37.724362, -122.502912);
        d82.addMapPoint(37.704943, -122.497934);
        d82.addMapPoint(37.731150, -122.487806);
        d82.addMapPoint(37.730879, -122.495702);
        d82.addMapPoint(37.732372, -122.485059);
        d82.addMapPoint(37.732644, -122.480939);
        d82.addMapPoint(37.723819, -122.478536);
        d82.addMapPoint(37.729250, -122.477335);
        d82.addMapPoint(37.717708, -122.477678);
        d82.getDistance(currentLocation);
        d82.citySection = "";





        targetDistrict = "Laurel Heights";
        targetLocation.setLatitude(37.7842282);
        targetLocation.setLongitude(-122.4529064);
        targetZoomLevel = "16z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d83 = new District(targetDistrict,targetDistance,targetZoomLevel,
                targetLocation.getLatitude(),targetLocation.getLongitude());
        targetDistricts[83] = d83;
        d83.addMapPoint(37.785347, -122.458121);
        d83.addMapPoint(37.784974, -122.456919);
        d83.addMapPoint(37.785721, -122.455846);
        d83.addMapPoint(37.784940, -122.454730);
        d83.addMapPoint(37.785449, -122.453743);
        d83.addMapPoint(37.784873, -122.451984);
        d83.addMapPoint(37.786060, -122.450954);
        d83.addMapPoint(37.785415, -122.450267);
        d83.addMapPoint(37.786331, -122.448894);
        d83.addMapPoint(37.784940, -122.448078);
        d83.addMapPoint(37.783855, -122.448722);
        d83.addMapPoint(37.782872, -122.450267);
        d83.addMapPoint(37.783279, -122.452456);
        d83.addMapPoint(37.782464, -122.453400);
        d83.addMapPoint(37.783177, -122.454559);
        d83.addMapPoint(37.782295, -122.455674);
        d83.addMapPoint(37.783312, -122.45683);
        d83.addMapPoint(37.782227, -122.457820);
        d83.addMapPoint(37.783889, -122.458764);
        d83.addMapPoint(37.783923, -122.457348);
        d83.addMapPoint(37.783991, -122.456275);
        d83.addMapPoint(37.784025, -122.455117);
        d83.addMapPoint(37.784059, -122.454001);
        d83.addMapPoint(37.784093, -122.453100);
        d83.addMapPoint(37.784907, -122.448550);
        d83.addMapPoint(37.784601, -122.449752);
        d83.addMapPoint(37.784432, -122.450653);
        d83.addMapPoint(37.784262, -122.451340);
        d83.addMapPoint(37.785924, -122.451597);
        d83.addMapPoint(37.785653, -122.454816);
        d83.getDistance(currentLocation);
        d83.citySection = "Western Addition";






        targetDistrict = "Little Hollywood";
        targetLocation.setLatitude(37.7119633);
        targetLocation.setLongitude(-122.3983217);
        targetZoomLevel = "15z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d84 = new District(targetDistrict,targetDistance,targetZoomLevel,
                targetLocation.getLatitude(),targetLocation.getLongitude());
        targetDistricts[84] = d84;
        d84.addMapPoint(37.714781, -122.398386);
        d84.addMapPoint(37.713593, -122.399116);
        d84.addMapPoint(37.713321, -122.397699);
        d84.addMapPoint(37.712778, -122.399631);
        d84.addMapPoint(37.712303, -122.397914);
        d84.addMapPoint(37.712235, -122.396970);
        d84.addMapPoint(37.711895, -122.400274);
        d84.addMapPoint(37.711454, -122.398300);
        d84.addMapPoint(37.711013, -122.396584);
        d84.addMapPoint(37.710368, -122.396326);
        d84.addMapPoint(37.710300, -122.397699);
        d84.addMapPoint(37.710877, -122.400360);
        d84.addMapPoint(37.710096, -122.400661);
        d84.addMapPoint(37.709621, -122.398901);
        d84.addMapPoint(37.709281, -122.395597);
        d84.addMapPoint(37.709213, -122.397399);
        d84.addMapPoint(37.709349, -122.397828);
        d84.addMapPoint(37.714340, -122.398558);
        d84.addMapPoint(37.710775, -122.396626);
        d84.addMapPoint(37.709926, -122.398086);
        d84.getDistance(currentLocation);
        d84.citySection = "";







        targetDistrict = "Little Russia";
        targetLocation.setLatitude(37.7802377);
        targetLocation.setLongitude(-122.4815463);
        targetZoomLevel = "16z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d85 = new District(targetDistrict,targetDistance,targetZoomLevel,
                targetLocation.getLatitude(),targetLocation.getLongitude());
        targetDistricts[85] = d85;
        d85.addMapPoint(37.780017, -122.486535);
        d85.addMapPoint(37.780068, -122.485827);
        d85.addMapPoint(37.780136, -122.484690);
        d85.addMapPoint(37.780153, -122.483617);
        d85.addMapPoint(37.780153, -122.482587);
        d85.addMapPoint(37.780204, -122.481514);
        d85.addMapPoint(37.780272, -122.480398);
        d85.addMapPoint(37.780339, -122.479368);
        d85.addMapPoint(37.780331, -122.478333);
        d85.addMapPoint(37.780373, -122.477239);
        d85.addMapPoint(37.780484, -122.476488);
        d85.addMapPoint(37.781806, -122.477373);
        d85.addMapPoint(37.781281, -122.478403);
        d85.addMapPoint(37.781840, -122.479540);
        d85.addMapPoint(37.781315, -122.480549);
        d85.addMapPoint(37.781637, -122.481664);
        d85.addMapPoint(37.781043, -122.482651);
        d85.addMapPoint(37.781637, -122.483810);
        d85.addMapPoint(37.780925, -122.484819);
        d85.addMapPoint(37.781552, -122.485956);
        d85.addMapPoint(37.778635, -122.485741);
        d85.addMapPoint(37.779127, -122.484647);
        d85.addMapPoint(37.778720, -122.483553);
        d85.addMapPoint(37.778788, -122.481407);
        d85.addMapPoint(37.779313, -122.480398);
        d85.addMapPoint(37.779279, -122.479283);
        d85.addMapPoint(37.778906, -122.478210);
        d85.addMapPoint(37.779381, -122.477137);
        d85.addMapPoint(37.779907, -122.478253);
        d85.addMapPoint(37.779788, -122.481471);
        d85.getDistance(currentLocation);
        d85.citySection = "";



        targetDistrict = "Little Saigon";
        targetLocation.setLatitude(37.7842346);
        targetLocation.setLongitude(-122.4177669);
        targetZoomLevel = "17z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d86 = new District(targetDistrict,targetDistance,targetZoomLevel,
                targetLocation.getLatitude(),targetLocation.getLongitude());
        targetDistricts[86] = d86;
        d86.addMapPoint(37.785337, -122.416469);
        d86.addMapPoint(37.785099, -122.417091);
        d86.addMapPoint(37.784930, -122.417885);
        d86.addMapPoint(37.784828, -122.418593);
        d86.addMapPoint(37.784591, -122.419237);
        d86.addMapPoint(37.784150, -122.419323);
        d86.addMapPoint(37.784167, -122.418614);
        d86.addMapPoint(37.784268, -122.417778);
        d86.addMapPoint(37.784370, -122.416855);
        d86.addMapPoint(37.784014, -122.416383);
        d86.addMapPoint(37.784048, -122.417713);
        d86.addMapPoint(37.783624, -122.417627);
        d86.addMapPoint(37.783675, -122.418936);
        d86.addMapPoint(37.783743, -122.418164);
        d86.addMapPoint(37.784116, -122.419022);
        d86.addMapPoint(37.784201, -122.418443);
        d86.addMapPoint(37.784455, -122.416340);
        d86.addMapPoint(37.784947, -122.417885);
        d86.addMapPoint(37.784557, -122.419301);
        d86.addMapPoint(37.784302, -122.417306);
        d86.getDistance(currentLocation);
        d86.citySection = "";



        targetDistrict = "Lone Mountain";
        targetLocation.setLatitude(37.7783446);
        targetLocation.setLongitude(-122.4526678);
        targetZoomLevel = "15z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d87 = new District(targetDistrict,targetDistance,targetZoomLevel,
                targetLocation.getLatitude(),targetLocation.getLongitude());
        targetDistricts[87] = d87;
        d87.addMapPoint(37.781058, -122.448848);
        d87.addMapPoint(37.781194, -122.449878);
        d87.addMapPoint(37.781058, -122.450908);
        d87.addMapPoint(37.780855, -122.452024);
        d87.addMapPoint(37.780583, -122.453054);
        d87.addMapPoint(37.780244, -122.45511);
        d87.addMapPoint(37.779769, -122.456916);
        d87.addMapPoint(37.779634, -122.457861);
        d87.addMapPoint(37.778141, -122.457088);
        d87.addMapPoint(37.776581, -122.456831);
        d87.addMapPoint(37.775495, -122.456573);
        d87.addMapPoint(37.775767, -122.454856);
        d87.addMapPoint(37.775834, -122.453054);
        d87.addMapPoint(37.776106, -122.450908);
        d87.addMapPoint(37.776445, -122.448419);
        d87.addMapPoint(37.776513, -122.447475);
        d87.addMapPoint(37.777734, -122.447733);
        d87.addMapPoint(37.778616, -122.448162);
        d87.addMapPoint(37.779701, -122.448162);
        d87.addMapPoint(37.779769, -122.451509);
        d87.addMapPoint(37.779159, -122.454599);
        d87.addMapPoint(37.778277, -122.456144);
        d87.addMapPoint(37.776309, -122.455972);
        d87.addMapPoint(37.777666, -122.450651);
        d87.addMapPoint(37.777463, -122.448419);
        d87.addMapPoint(37.777666, -122.456144);
        d87.addMapPoint(37.777938, -122.454427);
        d87.addMapPoint(37.777938, -122.452453);
        d87.addMapPoint(37.777938, -122.451509);
        d87.addMapPoint(37.778209, -122.449792);
        d87.addMapPoint(37.778480, -122.448419);
        d87.addMapPoint(37.775427, -122.455371);
        d87.getDistance(currentLocation);
        d87.citySection = "";

        targetDistrict = "Lower Haight";
        targetLocation.setLatitude(37.7717213);
        targetLocation.setLongitude(-122.433711);
        targetZoomLevel = "16z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d88 = new District(targetDistrict,targetDistance,targetZoomLevel,
                targetLocation.getLatitude(),targetLocation.getLongitude());
        targetDistricts[88] = d88;
        d88.addMapPoint(37.774299, -122.427724);
        d88.addMapPoint(37.773994, -122.429183);
        d88.addMapPoint(37.773587, -122.430728);
        d88.addMapPoint(37.773451, -122.432359);
        d88.addMapPoint(37.773078, -122.434033);
        d88.addMapPoint(37.772976, -122.435750);
        d88.addMapPoint(37.772807, -122.437337);
        d88.addMapPoint(37.772603, -122.439054);
        d88.addMapPoint(37.771857, -122.440213);
        d88.addMapPoint(37.772128, -122.438024);
        d88.addMapPoint(37.772366, -122.436265);
        d88.addMapPoint(37.772569, -122.434720);
        d88.addMapPoint(37.772807, -122.433089);
        d88.addMapPoint(37.773044, -122.431243);
        d88.addMapPoint(37.773384, -122.428282);
        d88.addMapPoint(37.772468, -122.427982);
        d88.addMapPoint(37.772247, -122.429645);
        d88.addMapPoint(37.772044, -122.431297);
        d88.addMapPoint(37.771806, -122.432949);
        d88.addMapPoint(37.771586, -122.434537);
        d88.addMapPoint(37.771382, -122.436254);
        d88.addMapPoint(37.771179, -122.437949);
        d88.addMapPoint(37.770636, -122.439151);
        d88.addMapPoint(37.769482, -122.437649);
        d88.addMapPoint(37.769448, -122.436704);
        d88.addMapPoint(37.769856, -122.436297);
        d88.addMapPoint(37.770466, -122.435975);
        d88.addMapPoint(37.770704, -122.434258);
        d88.addMapPoint(37.770873, -122.432692);
        d88.addMapPoint(37.771111, -122.430975);
        d88.addMapPoint(37.771314, -122.429409);
        d88.addMapPoint(37.771569, -122.427521);
        d88.addMapPoint(37.770856, -122.428508);
        d88.addMapPoint(37.770619, -122.430138);
        d88.addMapPoint(37.770415, -122.431769);
        d88.addMapPoint(37.770263, -122.432692);
        d88.addMapPoint(37.770212, -122.433486);
        d88.addMapPoint(37.770161, -122.434301);
        d88.addMapPoint(37.770025, -122.435117);
        d88.addMapPoint(37.769737, -122.436275);
        d88.addMapPoint(37.769448, -122.436694);
        d88.addMapPoint(37.769398, -122.437627);
        d88.getDistance(currentLocation);
        d88.citySection = "Western Addition";



        targetDistrict = "Lower Pacific Heights";
        targetLocation.setLatitude(37.7862924);
        targetLocation.setLongitude(-122.4357759);
        targetZoomLevel = "15z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d89 = new District(targetDistrict,targetDistance,targetZoomLevel,
                targetLocation.getLatitude(),targetLocation.getLongitude());
        targetDistricts[89] = d89;
        d89.addMapPoint(37.785919, -122.427429);
        d89.addMapPoint(37.786157, -122.425669);
        d89.addMapPoint(37.785275, -122.432235);
        d89.addMapPoint(37.784868, -122.433866);
        d89.addMapPoint(37.784834, -122.435497);
        d89.addMapPoint(37.784597, -122.437299);
        d89.addMapPoint(37.784291, -122.437986);
        d89.addMapPoint(37.783986, -122.439660);
        d89.addMapPoint(37.783681, -122.441291);
        d89.addMapPoint(37.783545, -122.442964);
        d89.addMapPoint(37.783240, -122.444595);
        d89.addMapPoint(37.783579, -122.445539);
        d89.addMapPoint(37.784563, -122.445668);
        d89.addMapPoint(37.785512, -122.445797);
        d89.addMapPoint(37.786462, -122.446011);
        d89.addMapPoint(37.787005, -122.445368);
        d89.addMapPoint(37.787276, -122.443694);
        d89.addMapPoint(37.787513, -122.442063);
        d89.addMapPoint(37.787615, -122.440218);
        d89.addMapPoint(37.787886, -122.438716);
        d89.addMapPoint(37.788090, -122.437042);
        d89.addMapPoint(37.788463, -122.435454);
        d89.addMapPoint(37.788565, -122.433823);
        d89.addMapPoint(37.788700, -122.432150);
        d89.addMapPoint(37.788938, -122.430519);
        d89.addMapPoint(37.789175, -122.428888);
        d89.addMapPoint(37.789311, -122.427171);
        d89.addMapPoint(37.788938, -122.426120);
        d89.addMapPoint(37.788056, -122.425873);
        d89.addMapPoint(37.787106, -122.425616);
        d89.addMapPoint(37.786971, -122.426732);
        d89.addMapPoint(37.787174, -122.428491);
        d89.addMapPoint(37.786971, -122.430079);
        d89.addMapPoint(37.786801, -122.431753);
        d89.addMapPoint(37.785919, -122.434843);
        d89.addMapPoint(37.785478, -122.438276);
        d89.addMapPoint(37.785037, -122.441580);
        d89.addMapPoint(37.784834, -122.443554);
        d89.addMapPoint(37.785546, -122.445056);
        d89.addMapPoint(37.786021, -122.441709);
        d89.addMapPoint(37.786394, -122.438447);
        d89.addMapPoint(37.786801, -122.435057);
        d89.addMapPoint(37.787276, -122.431796);
        d89.addMapPoint(37.787717, -122.428577);
        d89.addMapPoint(37.788802, -122.427118);
        d89.addMapPoint(37.788429, -122.430379);
        d89.addMapPoint(37.788056, -122.433684);
        d89.addMapPoint(37.787649, -122.436945);
        d89.addMapPoint(37.787174, -122.440336);
        d89.addMapPoint(37.786801, -122.443554);
        d89.getDistance(currentLocation);
        d89.citySection = "";




        targetDistrict = "Lower Nob Hill";
        targetLocation.setLatitude(37.7890843);
        targetLocation.setLongitude(-122.4136579);
        targetZoomLevel = "16z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d90 = new District(targetDistrict,targetDistance,targetZoomLevel,
                targetLocation.getLatitude(),targetLocation.getLongitude());
        targetDistricts[90] = d90;
        d90.addMapPoint(37.791509, -122.410772);
        d90.addMapPoint(37.791238, -122.412381);
        d90.addMapPoint(37.791102, -122.414055);
        d90.addMapPoint(37.790899, -122.415686);
        d90.addMapPoint(37.790644, -122.417359);
        d90.addMapPoint(37.789915, -122.418475);
        d90.addMapPoint(37.789016, -122.418196);
        d90.addMapPoint(37.788050, -122.417896);
        d90.addMapPoint(37.787134, -122.417767);
        d90.addMapPoint(37.786625, -122.416501);
        d90.addMapPoint(37.786897, -122.414870);
        d90.addMapPoint(37.787117, -122.413239);
        d90.addMapPoint(37.787338, -122.411609);
        d90.addMapPoint(37.787779, -122.409999);
        d90.addMapPoint(37.788253, -122.409034);
        d90.addMapPoint(37.789203, -122.409463);
        d90.addMapPoint(37.790119, -122.409441);
        d90.addMapPoint(37.791051, -122.409613);
        d90.addMapPoint(37.788033, -122.410117);
        d90.addMapPoint(37.787931, -122.411662);
        d90.addMapPoint(37.787694, -122.413379);
        d90.addMapPoint(37.787490, -122.415010);
        d90.addMapPoint(37.787253, -122.416726);
        d90.addMapPoint(37.788203, -122.416898);
        d90.addMapPoint(37.788440, -122.415181);
        d90.addMapPoint(37.788576, -122.413594);
        d90.addMapPoint(37.788847, -122.411920);
        d90.addMapPoint(37.789050, -122.410332);
        d90.addMapPoint(37.790000, -122.410461);
        d90.addMapPoint(37.789830, -122.412134);
        d90.addMapPoint(37.789559, -122.413808);
        d90.addMapPoint(37.789356, -122.415439);
        d90.addMapPoint(37.789186, -122.417156);
        d90.addMapPoint(37.790102, -122.417327);
        d90.addMapPoint(37.790305, -122.415611);
        d90.addMapPoint(37.790543, -122.413980);
        d90.addMapPoint(37.790746, -122.412306);
        d90.addMapPoint(37.791017, -122.410589);
        d90.addMapPoint(37.789254, -122.412091);
        d90.addMapPoint(37.788881, -122.415224);
        d90.getDistance(currentLocation);
        d90.citySection = "";




        //targetDistrict = "Marina District";
        targetDistrict = "Marina District";
        targetLocation.setLatitude(37.8019775);
        targetLocation.setLongitude(-122.4371031);
        targetZoomLevel = "14z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d91 = new District(targetDistrict,targetDistance,targetZoomLevel,
                targetLocation.getLatitude(),targetLocation.getLongitude());
        targetDistricts[91] = d91;
        d91.addMapPoint(0,0);
        d91.getDistance(currentLocation);
        d91.citySection = "North of Downtown";






        targetDistrict = "Merced Heights";
        targetLocation.setLatitude(37.7193656);
        targetLocation.setLongitude(-122.4675014);
        targetZoomLevel = "16z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d92 = new District(targetDistrict,targetDistance,targetZoomLevel,
                targetLocation.getLatitude(),targetLocation.getLongitude());
        targetDistricts[92] = d92;
        d92.addMapPoint(0,0);
        d92.getDistance(currentLocation);
        d92.citySection = "";






        targetDistrict = "Merced Manor";
        targetLocation.setLatitude(37.7328756);
        targetLocation.setLongitude(-122.4784722);
        targetZoomLevel = "16z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d93 = new District(targetDistrict,targetDistance,targetZoomLevel,
                targetLocation.getLatitude(),targetLocation.getLongitude());
        targetDistricts[93] = d93;
        d93.addMapPoint(0,0);
        d93.getDistance(currentLocation);
        d93.citySection = "";






        targetDistrict = "Midtown Terrace";
        targetLocation.setLatitude(37.7511432);
        targetLocation.setLongitude(-122.4532204);
        targetZoomLevel = "15z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d94 = new District(targetDistrict,targetDistance,targetZoomLevel,
                targetLocation.getLatitude(),targetLocation.getLongitude());
        targetDistricts[94] = d94;
        d94.addMapPoint(0,0);
        d94.getDistance(currentLocation);
        d94.citySection = "";






        targetDistrict = "Mid-Market";
        targetLocation.setLatitude(37.7801532);
        targetLocation.setLongitude(-122.4114597);
        targetZoomLevel = "15z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d95 = new District(targetDistrict,targetDistance,targetZoomLevel,
                targetLocation.getLatitude(),targetLocation.getLongitude());
        targetDistricts[95] = d95;
        d95.addMapPoint(0,0);
        d95.getDistance(currentLocation);
        d95.citySection = "Downtown";






        targetDistrict = "**Miraloma Park";
        targetLocation.setLatitude(37.741423);
        targetLocation.setLongitude(-122.446653);
        targetZoomLevel = "17z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d96 = new District(targetDistrict,targetDistance,targetZoomLevel,
                targetLocation.getLatitude(),targetLocation.getLongitude());
        targetDistricts[96] = d96;
        d96.addMapPoint(0,0);
        d96.getDistance(currentLocation);
        d96.citySection = "";





        targetDistrict = "Mission Bay";
        targetLocation.setLatitude(37.7738181);
        targetLocation.setLongitude(-122.39407);
        targetZoomLevel = "14z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d97 = new District(targetDistrict,targetDistance,targetZoomLevel,
                targetLocation.getLatitude(),targetLocation.getLongitude());
        targetDistricts[97] = d97;
        d97.addMapPoint(0,0);
        d97.getDistance(currentLocation);
        d97.citySection = "Downtown";




        targetDistrict = "Mission District";
        targetLocation.setLatitude(37.7599046);
        targetLocation.setLongitude(-122.4168468);
        targetZoomLevel = "14z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d98 = new District(targetDistrict,targetDistance,targetZoomLevel,
                targetLocation.getLatitude(),targetLocation.getLongitude());
        targetDistricts[98] = d98;
        d98.addMapPoint(0,0);
        d98.getDistance(currentLocation);
        d98.citySection = "Southern";




        targetDistrict = "**Mission Dolores";
        targetLocation.setLatitude(37.764387);
        targetLocation.setLongitude(-122.426897);
        targetZoomLevel = "17z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d99 = new District(targetDistrict,targetDistance,targetZoomLevel,
                targetLocation.getLatitude(),targetLocation.getLongitude());
        targetDistricts[99] = d99;
        d99.addMapPoint(0,0);
        d99.getDistance(currentLocation);
        d99.citySection = "";



        targetDistrict = "*Mission Terrace (same as Balboa Park)";
        targetLocation.setLatitude(37.7217182);
        targetLocation.setLongitude(-122.4413936);
        targetZoomLevel = "15z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d100 = new District(targetDistrict,targetDistance,targetZoomLevel,
                targetLocation.getLatitude(),targetLocation.getLongitude());
        targetDistricts[100] = d100;
        d100.addMapPoint(0,0);
        d100.getDistance(currentLocation);
        d100.citySection = "";


        targetDistrict = "Monterey Heights";
        targetLocation.setLatitude(37.733519);
        targetLocation.setLongitude(-122.4625056);
        targetZoomLevel = "16z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d101 = new District(targetDistrict,targetDistance,targetZoomLevel,
                targetLocation.getLatitude(),targetLocation.getLongitude());
        targetDistricts[101] = d101;
        d101.addMapPoint(0,0);
        d101.getDistance(currentLocation);
        d101.citySection = "";

        targetDistrict = "Mount Davidson";
        targetLocation.setLatitude(37.7390371);
        targetLocation.setLongitude(-122.4540034);
        targetZoomLevel = "16z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d102 = new District(targetDistrict,targetDistance,targetZoomLevel,
                targetLocation.getLatitude(),targetLocation.getLongitude());
        targetDistricts[102] = d102;
        d102.addMapPoint(0,0);
        d102.getDistance(currentLocation);
        d102.citySection = "";


        targetDistrict = "Nob Hill";
        targetLocation.setLatitude(37.7929437);
        targetLocation.setLongitude(-122.416112);
        targetZoomLevel = "15z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d103 = new District(targetDistrict,targetDistance,targetZoomLevel,
                targetLocation.getLatitude(),targetLocation.getLongitude());
        targetDistricts[103] = d103;
        d103.addMapPoint(0,0);
        d103.getDistance(currentLocation);
        d103.citySection = "Downtown";



        targetDistrict = "Noe Valley";
        targetLocation.setLatitude(37.7490555);
        targetLocation.setLongitude(-122.4344773);
        targetZoomLevel = "14z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d104 = new District(targetDistrict,targetDistance,targetZoomLevel,
                targetLocation.getLatitude(),targetLocation.getLongitude());
        targetDistricts[104] = d104;
        d104.addMapPoint(37.755367, -122.440357);
        d104.addMapPoint(37.754349, -122.441129);
        d104.addMapPoint(37.753263, -122.441988);
        d104.addMapPoint(37.752449, -122.442224);
        d104.addMapPoint(37.752042, -122.442481);
        d104.addMapPoint(37.751024, -122.442824);
        d104.addMapPoint(37.750175, -122.442696);
        d104.addMapPoint(37.749157, -122.443082);
        d104.addMapPoint(37.748750, -122.443511);
        d104.addMapPoint(37.747800, -122.443511);
        d104.addMapPoint(37.747020, -122.443769);
        d104.addMapPoint(37.747189, -122.442395);
        d104.addMapPoint(37.747087, -122.440421);
        d104.addMapPoint(37.746239, -122.439262);
        d104.addMapPoint(37.745323, -122.439563);
        d104.addMapPoint(37.744203, -122.438533);
        d104.addMapPoint(37.743830, -122.437503);
        d104.addMapPoint(37.742778, -122.435529);
        d104.addMapPoint(37.742506, -122.434456);
        d104.addMapPoint(37.742269, -122.433340);
        d104.addMapPoint(37.742099, -122.431023);
        d104.addMapPoint(37.742303, -122.428791);
        d104.addMapPoint(37.742438, -122.426602);
        d104.addMapPoint(37.743049, -122.424886);
        d104.addMapPoint(37.743830, -122.425057);
        d104.addMapPoint(37.744644, -122.425057);
        d104.addMapPoint(37.745425, -122.425186);
        d104.addMapPoint(37.746239, -122.425315);
        d104.addMapPoint(37.747053, -122.425358);
        d104.addMapPoint(37.747834, -122.425315);
        d104.addMapPoint(37.748648, -122.425487);
        d104.addMapPoint(37.749463, -122.425658);
        d104.addMapPoint(37.750243, -122.425658);
        d104.addMapPoint(37.751058, -122.425701);
        d104.addMapPoint(37.751838, -122.425744);
        d104.addMapPoint(37.753399, -122.425873);
        d104.addMapPoint(37.755061, -122.426087);
        d104.addMapPoint(37.756181, -122.427890);
        d104.addMapPoint(37.755910, -122.430036);
        d104.addMapPoint(37.755842, -122.432353);
        d104.addMapPoint(37.755706, -122.434499);
        d104.addMapPoint(37.755672, -122.435658);
        d104.addMapPoint(37.755469, -122.436730);
        d104.addMapPoint(37.755231, -122.437803);
        d104.addMapPoint(37.754994, -122.438833);
        d104.addMapPoint(37.753399, -122.437782);
        d104.addMapPoint(37.752313, -122.435207);
        d104.addMapPoint(37.749938, -122.433362);
        d104.addMapPoint(37.746748, -122.430272);
        d104.addMapPoint(37.744576, -122.427353);
        d104.addMapPoint(37.744373, -122.431302);
        d104.addMapPoint(37.746409, -122.435507);
        d104.addMapPoint(37.748377, -122.436022);
        d104.addMapPoint(37.750006, -122.436108);
        d104.addMapPoint(37.752381, -122.432160);
        d104.addMapPoint(37.754824, -122.429499);
        d104.addMapPoint(37.753806, -122.434477);
        d104.addMapPoint(37.752788, -122.438855);
        d104.addMapPoint(37.751974, -122.438855);
        d104.addMapPoint(37.750209, -122.439541);
        d104.addMapPoint(37.746477, -122.436537);
        d104.getDistance(currentLocation);
        d104.citySection = "Southern";






        targetDistrict = "North Beach";
        targetLocation.setLatitude(37.8047122);
        targetLocation.setLongitude(-122.4081963);
        targetZoomLevel = "15z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d105 = new District(targetDistrict,targetDistance,targetZoomLevel,
                targetLocation.getLatitude(),targetLocation.getLongitude());
        targetDistricts[105] = d105;
        d105.addMapPoint(new mapPoint(37.808289, -122.419462));
        d105.getDistance(currentLocation);
        d105.citySection = "Downtown";






        //targetDistrict = "North of Panhandle";
        //targetDistrict = "Panhandle/NoPa";
        //The actual panhandle is a park
        targetDistrict = "North of Panhandle (NoPa)";
        targetLocation.setLatitude(37.7763168);
        targetLocation.setLongitude(-122.4423515);
        targetZoomLevel = "16z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d106 = new District(targetDistrict,targetDistance,targetZoomLevel,
                targetLocation.getLatitude(),targetLocation.getLongitude());
        targetDistricts[106] = d106;
        d106.addMapPoint(new mapPoint(37.779022, -122.442899));
        d106.getDistance(currentLocation);
        d106.citySection = "";





        targetDistrict = "Oceanview";
        //sometimes Ocean View
        targetLocation.setLatitude(37.7196153);
        targetLocation.setLongitude(-122.4600507);
        targetZoomLevel = "14z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d107 = new District(targetDistrict,targetDistance,targetZoomLevel,
                targetLocation.getLatitude(),targetLocation.getLongitude());
        targetDistricts[107] = d107;
        d107.addMapPoint(new mapPoint(37.721007, -122.448850));
        d107.getDistance(currentLocation);
        d107.citySection = "Outside Lands";





        targetDistrict = "Outer Mission";
        targetLocation.setLatitude(37.7220888);
        targetLocation.setLongitude(-122.4436351);
        targetZoomLevel = "14z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d108 = new District(targetDistrict,targetDistance,targetZoomLevel,
                targetLocation.getLatitude(),targetLocation.getLongitude());
        targetDistricts[108] = d108;
        d108.addMapPoint(new mapPoint(37.718524, -122.445051));
        d108.getDistance(currentLocation);
        d108.citySection = "Southern";




        targetDistrict = "Outer Richmond";
        targetLocation.setLatitude(37.7794924);
        targetLocation.setLongitude(-122.4948635);
        targetZoomLevel = "14z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d109 = new District(targetDistrict,targetDistance,targetZoomLevel,
                targetLocation.getLatitude(),targetLocation.getLongitude());
        targetDistricts[109] = d109;
        d109.addMapPoint(new mapPoint(37.772278, -122.492870));
        d109.addMapPoint(new mapPoint(37.777230, -122.493385));
        d109.addMapPoint(new mapPoint(37.781504, -122.493686));
        d109.addMapPoint(new mapPoint(37.781165, -122.501668));
        d109.addMapPoint(new mapPoint(37.780961, -122.509522));
        d109.addMapPoint(new mapPoint(37.777502, -122.509221));
        d109.addMapPoint(new mapPoint(37.775297, -122.509779));
        d109.addMapPoint(new mapPoint(37.775161, -122.511195));
        d109.addMapPoint(new mapPoint(37.771565, -122.510723));
        d109.addMapPoint(new mapPoint(37.772040, -122.500037));
        d109.getDistance(currentLocation);
        d109.citySection = "";


        targetDistrict = "Outer Sunset";
        targetLocation.setLatitude(37.7553517);
        targetLocation.setLongitude(-122.4936706);
        targetZoomLevel = "14z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d110 = new District(targetDistrict,targetDistance,targetZoomLevel,
                targetLocation.getLatitude(),targetLocation.getLongitude());
        targetDistricts[110] = d110;
        d110.addMapPoint(new mapPoint(37.764339, -122.496389));
        d110.addMapPoint(new mapPoint(37.764170, -122.500424));
        d110.addMapPoint(new mapPoint(37.764068, -122.503342));
        d110.addMapPoint(new mapPoint(37.763898, -122.506775));
        d110.addMapPoint(new mapPoint(37.763797, -122.510079));
        d110.addMapPoint(new mapPoint(37.749869, -122.508384));
        d110.addMapPoint(new mapPoint(37.738874, -122.506668));
        d110.addMapPoint(new mapPoint(37.735887, -122.506153));
        d110.addMapPoint(new mapPoint(37.734394, -122.494480));
        d110.addMapPoint(new mapPoint(37.748376, -122.495681));
        d110.getDistance(currentLocation);
        d110.citySection = "";


        // I made this one
        targetDistrict = "Central Sunset";
        targetLocation.setLatitude(37.752890);
        targetLocation.setLongitude(-122.484838);
        targetZoomLevel = "14z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d111 = new District(targetDistrict,targetDistance,targetZoomLevel,
                37.752890,-122.484838);
        targetDistricts[111] = d111;
        d111.addMapPoint(new mapPoint(37.765240,-122.477628));
        d111.addMapPoint(new mapPoint(37.764425,-122.495739));
        d111.addMapPoint(new mapPoint(37.747901,-122.494751));
        d111.addMapPoint(new mapPoint(37.748750,-122.476341));
        d111.getDistance(currentLocation);
        d111.citySection = "";

        targetDistrict = "Pacific Heights";
        targetLocation.setLatitude(37.7924282);
        targetLocation.setLongitude(-122.4349945);
        targetZoomLevel = "15z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d112 = new District(targetDistrict,targetDistance,targetZoomLevel,
                targetLocation.getLatitude(),targetLocation.getLongitude());
        targetDistricts[112] = d112;
        d112.getDistance(currentLocation);
        d112.citySection = "North of Downtown";


        targetDistrict = "Parkmerced";
        targetLocation.setLatitude(37.7184207);
        targetLocation.setLongitude(-122.4784131);
        targetZoomLevel = "15z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d113 = new District(targetDistrict,targetDistance,targetZoomLevel,
                targetLocation.getLatitude(),targetLocation.getLongitude());
        targetDistricts[113] = d113;


        d113.getDistance(currentLocation);
        d113.citySection = "";


        targetDistrict = "Parkside";
        targetLocation.setLatitude(37.7328756);
        targetLocation.setLongitude(-122.4784722);
        targetZoomLevel = "16z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d114 = new District(targetDistrict,targetDistance,targetZoomLevel,
                targetLocation.getLatitude(),targetLocation.getLongitude());
        targetDistricts[114] = d114;
        d114.getDistance(currentLocation);
        d114.citySection = "Outside Lands";

        targetDistrict = "**Parnassus";
        targetLocation.setLatitude(37.7582631);
        targetLocation.setLongitude(-122.4571935);
        targetZoomLevel = "14z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d115 = new District(targetDistrict,targetDistance,targetZoomLevel,
                targetLocation.getLatitude(),targetLocation.getLongitude());
        targetDistricts[115] = d115;
        d115.getDistance(currentLocation);
        d115.citySection = "";

        targetDistrict = "**Polk Gulch";
        targetLocation.setLatitude(37.791526);
        targetLocation.setLongitude(-122.4209171);
        targetZoomLevel = "17z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d116 = new District(targetDistrict,targetDistance,targetZoomLevel,
                targetLocation.getLatitude(),targetLocation.getLongitude());
        targetDistricts[116] = d116;
        d116.getDistance(currentLocation);
        d116.citySection = "";


        targetDistrict = "Portola";
        targetLocation.setLatitude(37.7270714);
        targetLocation.setLongitude(-122.4120226);
        targetZoomLevel = "14z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d117 = new District(targetDistrict,targetDistance,targetZoomLevel,
                targetLocation.getLatitude(),targetLocation.getLongitude());
        targetDistricts[117] = d117;
        d117.getDistance(currentLocation);
        d117.citySection = "Southern";



        targetDistrict = "Portola Place";
        targetLocation.setLatitude(37.7280593);
        targetLocation.setLongitude(-122.3978989);
        targetZoomLevel = "16z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d118 = new District(targetDistrict,targetDistance,targetZoomLevel,
                targetLocation.getLatitude(),targetLocation.getLongitude());
        targetDistricts[118] = d118;
        d118.getDistance(currentLocation);
        d118.citySection = "";





        targetDistrict = "Potrero Hill";
        targetLocation.setLatitude(37.758266);
        targetLocation.setLongitude(-122.392634);
        targetZoomLevel = "14z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d119 = new District(targetDistrict,targetDistance,targetZoomLevel,
                targetLocation.getLatitude(),targetLocation.getLongitude());
        targetDistricts[119] = d119;
        d119.getDistance(currentLocation);
        d119.citySection = "Southern";






        targetDistrict = "North of Downtown - Presidio";
        targetLocation.setLatitude(37.7989746);
        targetLocation.setLongitude(-122.4661867);
        targetZoomLevel = "14z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d120 = new District(targetDistrict,targetDistance,targetZoomLevel,
                targetLocation.getLatitude(),targetLocation.getLongitude());
        targetDistricts[120] = d120;
        d120.getDistance(currentLocation);
        d120.citySection = "Outside Lands";






        targetDistrict = "Presidio Heights";
        targetLocation.setLatitude(37.7886932);
        targetLocation.setLongitude(-122.4531634);
        targetZoomLevel = "16z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d121 = new District(targetDistrict,targetDistance,targetZoomLevel,
                targetLocation.getLatitude(),targetLocation.getLongitude());
        targetDistricts[121] = d121;
        d121.getDistance(currentLocation);
        d121.citySection = "";














        targetDistrict = "Rincon Hill";
        targetLocation.setLatitude(37.7868715);
        targetLocation.setLongitude(-122.3917865);
        targetZoomLevel = "16z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d122 = new District(targetDistrict,targetDistance,targetZoomLevel,
                targetLocation.getLatitude(),targetLocation.getLongitude());
        targetDistricts[122] = d122;
        d122.getDistance(currentLocation);
        d122.citySection = "";


        targetDistrict = "Russian Hill";
        targetLocation.setLatitude(37.8008489);
        targetLocation.setLongitude(-122.4159543);
        targetZoomLevel = "15z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d123 = new District(targetDistrict,targetDistance,targetZoomLevel,
                targetLocation.getLatitude(),targetLocation.getLongitude());
        targetDistricts[123] = d123;
        d123.getDistance(currentLocation);
        d123.citySection = "North of Downtown";

        targetDistrict = "Saint Francis Wood";
        //sometimes St. Francis Wood
        targetLocation.setLatitude(37.7366769);
        targetLocation.setLongitude(-122.4660212);
        targetZoomLevel = "15z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d124 = new District(targetDistrict,targetDistance,targetZoomLevel,
                targetLocation.getLatitude(),targetLocation.getLongitude());
        targetDistricts[124] = d124;
        d124.getDistance(currentLocation);
        d124.citySection = "Outside Lands";


        targetDistrict = "Sea Cliff";
        targetLocation.setLatitude(37.7828624);
        targetLocation.setLongitude(-122.4993276);
        targetZoomLevel = "14z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d125 = new District(targetDistrict,targetDistance,targetZoomLevel,
                targetLocation.getLatitude(),targetLocation.getLongitude());
        targetDistricts[125] = d125;
        d125.getDistance(currentLocation);
        d125.citySection = "Outside Lands";






        targetDistrict = "Sherwood Forest";
        targetLocation.setLatitude(37.737581);
        targetLocation.setLongitude(-122.45763);
        targetZoomLevel = "16z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d126 = new District(targetDistrict,targetDistance,targetZoomLevel,
                targetLocation.getLatitude(),targetLocation.getLongitude());
        targetDistricts[126] = d126;
        d126.getDistance(currentLocation);
        d126.citySection = "";





        targetDistrict = "Silver Terrace";
        targetLocation.setLatitude(37.7339535);
        targetLocation.setLongitude(-122.3993016);
        targetZoomLevel = "15z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d127 = new District(targetDistrict,targetDistance,targetZoomLevel,
                targetLocation.getLatitude(),targetLocation.getLongitude());
        targetDistricts[127] = d127;
        d127.getDistance(currentLocation);
        d127.citySection = "";




        targetDistrict = "Somisspo";
        targetLocation.setLatitude(37.7691584);
        targetLocation.setLongitude(-122.4072762);
        targetZoomLevel = "16z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d128 = new District(targetDistrict,targetDistance,targetZoomLevel,
                targetLocation.getLatitude(),targetLocation.getLongitude());
        targetDistricts[128] = d128;
        d128.getDistance(currentLocation);
        d128.citySection = "";




        targetDistrict = "South Beach";
        targetLocation.setLatitude(37.7864283);
        targetLocation.setLongitude(-122.3954732);
        targetZoomLevel = "14z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d129 = new District(targetDistrict,targetDistance,targetZoomLevel,
                targetLocation.getLatitude(),targetLocation.getLongitude());
        targetDistricts[129] = d129;
        d129.getDistance(currentLocation);
        d129.citySection = "";





        targetDistrict = "**South End";
        targetLocation.setLatitude(37.7689385);
        targetLocation.setLongitude(-122.3876133);
        targetZoomLevel = "17z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d130 = new District(targetDistrict,targetDistance,targetZoomLevel,
                targetLocation.getLatitude(),targetLocation.getLongitude());
        targetDistricts[130] = d130;
        d130.getDistance(currentLocation);
        d130.citySection = "";





        targetDistrict = "South of Market(SoMa)";
        targetLocation.setLatitude(37.7808157);
        targetLocation.setLongitude(-122.4024182);
        targetZoomLevel = "13z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d131 = new District(targetDistrict,targetDistance,targetZoomLevel,
                targetLocation.getLatitude(),targetLocation.getLongitude());
        targetDistricts[131] = d131;
        d131.getDistance(currentLocation);
        d131.citySection = "Downtown";




        targetDistrict = "South Park";
        targetLocation.setLatitude(37.7815754);
        targetLocation.setLongitude(-122.3940443);
        targetZoomLevel = "17z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d132 = new District(targetDistrict,targetDistance,targetZoomLevel,
                targetLocation.getLatitude(),targetLocation.getLongitude());
        targetDistricts[132] = d132;
        d132.getDistance(currentLocation);
        d132.citySection = "";



        targetDistrict = "Sunnydale";
        targetLocation.setLatitude(37.7105412);
        targetLocation.setLongitude(-122.4198183);
        targetZoomLevel = "16z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d133 = new District(targetDistrict,targetDistance,targetZoomLevel,
                targetLocation.getLatitude(),targetLocation.getLongitude());
        targetDistricts[133] = d133;
        d133.getDistance(currentLocation);
        d133.citySection = "";



        targetDistrict = "Sunnyside";
        targetLocation.setLatitude(37.7293949);
        targetLocation.setLongitude(-122.4447906);
        targetZoomLevel = "15z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d134 = new District(targetDistrict,targetDistance,targetZoomLevel,
                targetLocation.getLatitude(),targetLocation.getLongitude());
        targetDistricts[134] = d134;
        d134.getDistance(currentLocation);
        d134.citySection = "";




        targetDistrict = "Sunset District";
        targetLocation.setLatitude(37.7500392);
        targetLocation.setLongitude(-122.4840656);
        targetZoomLevel = "13z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d135 = new District(targetDistrict,targetDistance,targetZoomLevel,
                targetLocation.getLatitude(),targetLocation.getLongitude());
        targetDistricts[135] = d135;
        d135.getDistance(currentLocation);
        d135.citySection = "Outside Lands";
    /*
		targetDistrict = "Sunset";
	    targetLocation.setLatitude(37.7500392);
	    targetLocation.setLongitude(-122.4840656);
	    targetZoomLevel = "13z";
	    targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
	    setVars();
	    */








        targetDistrict = "Telegraph Hill";
        targetLocation.setLatitude(37.8016668);
        targetLocation.setLongitude(-122.4061985);
        targetZoomLevel = "15z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d136 = new District(targetDistrict,targetDistance,targetZoomLevel,
                targetLocation.getLatitude(),targetLocation.getLongitude());
        targetDistricts[136] = d136;
        d136.getDistance(currentLocation);
        d136.citySection = "Downtown";






        targetDistrict = "Tenderloin";
        targetLocation.setLatitude(37.7839296);
        targetLocation.setLongitude(-122.4130524);
        targetZoomLevel = "15z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d137 = new District(targetDistrict,targetDistance,targetZoomLevel,
                targetLocation.getLatitude(),targetLocation.getLongitude());
        targetDistricts[137] = d137;
        d137.getDistance(currentLocation);
        d137.citySection = "Downtown";

        targetDistrict = "Treasure Island";
        targetLocation.setLatitude(37.8201487);
        targetLocation.setLongitude(-122.3689871);
        targetZoomLevel = "14z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d138 = new District(targetDistrict,targetDistance,targetZoomLevel,
                targetLocation.getLatitude(),targetLocation.getLongitude());
        targetDistricts[138] = d138;
        d138.getDistance(currentLocation);
        d138.citySection = "North of Downtown";

        targetDistrict = "**Twin Peaks";
        targetLocation.setLatitude(37.752569);
        targetLocation.setLongitude(-122.447546);
        targetZoomLevel = "17z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d139 = new District(targetDistrict,targetDistance,targetZoomLevel,
                targetLocation.getLatitude(),targetLocation.getLongitude());
        targetDistricts[139] = d139;
        d139.getDistance(currentLocation);
        d139.citySection = "";



        targetDistrict = "Union Square";
        targetLocation.setLatitude(37.787994);
        targetLocation.setLongitude(-122.407437);
        targetZoomLevel = "17z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d140 = new District(targetDistrict,targetDistance,targetZoomLevel,
                targetLocation.getLatitude(),targetLocation.getLongitude());
        targetDistricts[140] = d140;
        d140.getDistance(currentLocation);
        d140.citySection = "Downtown";




        targetDistrict = "University Mound";
        targetLocation.setLatitude(37.7259385);
        targetLocation.setLongitude(-122.4183454);
        targetZoomLevel = "16z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d141 = new District(targetDistrict,targetDistance,targetZoomLevel,
                targetLocation.getLatitude(),targetLocation.getLongitude());
        targetDistricts[141] = d141;
        d141.getDistance(currentLocation);
        d141.citySection = "";




        targetDistrict = "Upper Market";
        targetLocation.setLatitude(37.7529277);
        targetLocation.setLongitude(-122.4450824);
        targetZoomLevel = "15z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d142 = new District(targetDistrict,targetDistance,targetZoomLevel,
                targetLocation.getLatitude(),targetLocation.getLongitude());
        targetDistricts[142] = d142;
        d142.getDistance(currentLocation);
        d142.citySection = "";





        targetDistrict = "Visitacion Valley";
        targetLocation.setLatitude(37.7162374);
        targetLocation.setLongitude(-122.414285);
        targetZoomLevel = "14z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d143 = new District(targetDistrict,targetDistance,targetZoomLevel,
                targetLocation.getLatitude(),targetLocation.getLongitude());
        targetDistricts[143] = d143;
        d143.getDistance(currentLocation);
        d143.citySection = "Southern";




        //same as Sutro Heights
        targetDistrict = "Vista del Mar";
        targetLocation.setLatitude(37.7781413);
        targetLocation.setLongitude(-122.5080427);
        targetZoomLevel = "16z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d144 = new District(targetDistrict,targetDistance,targetZoomLevel,
                targetLocation.getLatitude(),targetLocation.getLongitude());
        targetDistricts[144] = d144;
        d144.getDistance(currentLocation);
        d144.citySection = "";




        targetDistrict = "West Portal";
        targetLocation.setLatitude(37.7393017);
        targetLocation.setLongitude(-122.4654826);
        targetZoomLevel = "15z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        //FloatStringString td146 = new FloatStringString();
        //   td146.district = targetDistrict;
        //  td146.distance = targetDistance;
        //  td146.zoomLevel = targetZoomLevel;
        //targetDistrictsArray[145] = td146;
        District d145 = new District(targetDistrict,targetDistance,targetZoomLevel,
                targetLocation.getLatitude(),targetLocation.getLongitude());
        targetDistricts[145] = d145;
        d145.getDistance(currentLocation);
        d145.citySection = "Outside Lands";


        targetDistrict = "Western Addition";
        targetLocation.setLatitude(37.779694);
        targetLocation.setLongitude(-122.4343415);
        targetZoomLevel = "14z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        //FloatStringString td147 = new FloatStringString();
        //    td147.district = targetDistrict;
        //   td147.distance = targetDistance;
        //  td147.zoomLevel = targetZoomLevel;
        //targetDistrictsArray[146] = td147;
        District d146 = new District(targetDistrict,targetDistance,targetZoomLevel,
                targetLocation.getLatitude(),targetLocation.getLongitude());
        targetDistricts[146] = d146;
        d146.getDistance(currentLocation);
        d146.citySection = "Western Addition";


        targetDistrict = "Westwood Highlands";
        targetLocation.setLatitude(37.7332045);
        targetLocation.setLongitude(-122.4561859);
        targetZoomLevel = "16z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        //FloatStringString td148 = new FloatStringString();
        //   td148.district = targetDistrict;
        //  td148.distance = targetDistance;
        //  td148.zoomLevel = targetZoomLevel;
        //targetDistrictsArray[147] = td148;
        District d147 = new District(targetDistrict,targetDistance,targetZoomLevel,
                targetLocation.getLatitude(),targetLocation.getLongitude());
        targetDistricts[147] = d147;
        d147.getDistance(currentLocation);
        d147.citySection = "Outside Lands";



        targetDistrict = "Westwood Park";
        targetLocation.setLatitude(37.7273164);
        targetLocation.setLongitude(-122.4566644);
        targetZoomLevel = "15z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        //FloatStringString td149 = new FloatStringString();
        //   td149.district = targetDistrict;
        //  td149.distance = targetDistance;
        //  td149.zoomLevel = targetZoomLevel;
        //targetDistrictsArray[148] = td149;
        District d148 = new District(targetDistrict,targetDistance,targetZoomLevel,
                targetLocation.getLatitude(),targetLocation.getLongitude());
        targetDistricts[148] = d148;
        d148.getDistance(currentLocation);
        d148.citySection = "Outside Lands";


        targetDistrict = "***Yerba Buena";
        targetLocation.setLatitude(37.785779);
        targetLocation.setLongitude(-122.402156);
        targetZoomLevel = "17z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        //FloatStringString td150 = new FloatStringString();
        //    td150.district = targetDistrict;
        //    td150.distance = targetDistance;
        //    td150.zoomLevel = targetZoomLevel;
        //targetDistrictsArray[149] = td150;
        District d149 = new District(targetDistrict,targetDistance,targetZoomLevel,
                targetLocation.getLatitude(),targetLocation.getLongitude());
        targetDistricts[149] = d149;
        d149.getDistance(currentLocation);
        d149.citySection = "";

        targetDistrict = "**Golden Gate Park";
        targetLocation.setLatitude(37.769421);
        targetLocation.setLongitude(-122.486214);
        targetZoomLevel = "17z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        //FloatStringString td151 = new FloatStringString();
        //  td151.district = targetDistrict;
        // td151.distance = targetDistance;
        //  td151.zoomLevel = targetZoomLevel;
        //targetDistrictsArray[150] = td151;
        District d150 = new District(targetDistrict,targetDistance,targetZoomLevel,
                targetLocation.getLatitude(),targetLocation.getLongitude());
        targetDistricts[150] = d150;
        d150.getDistance(currentLocation);
        d150.citySection = "";





        ////////////////////obsolete



        //repurposed
        targetDistrict = "-obsolete-Cole Valley9";
        targetLocation.setLatitude(7.764099);
        targetLocation.setLongitude(-122.456596);
        targetZoomLevel = "15z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d20 = new District(targetDistrict,targetDistance,targetZoomLevel,
                targetLocation.getLatitude(),targetLocation.getLongitude());
        targetDistricts[20] = d20;
        d20.getDistance(currentLocation);
        d20.citySection = "";

        //repurposed
        targetDistrict = "-obsolete-Glen Park1";
        targetLocation.setLatitude(7.741728);
        targetLocation.setLongitude(-122.424744);
        targetZoomLevel = "14z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d21 = new District(targetDistrict,targetDistance,targetZoomLevel,
                targetLocation.getLatitude(),targetLocation.getLongitude());
        targetDistricts[21] = d21;
        d21.getDistance(currentLocation);
        d21.citySection = "";

        //repurposed
        targetDistrict = "-obsolete-Glen Park2";
        targetLocation.setLatitude(7.741253);
        targetLocation.setLongitude(-122.434357);
        targetZoomLevel = "13z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d22 = new District(targetDistrict,targetDistance,targetZoomLevel,
                targetLocation.getLatitude(),targetLocation.getLongitude());
        targetDistricts[22] = d22;
        d22.getDistance(currentLocation);
        d22.citySection = "";





        //repurposed
        targetDistrict = "-obsolete-Glen Park 3";
        targetLocation.setLatitude(7.735110);
        targetLocation.setLongitude(-122.439678);
        targetZoomLevel = "15z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d23 = new District(targetDistrict,targetDistance,targetZoomLevel,
                targetLocation.getLatitude(),targetLocation.getLongitude());
        targetDistricts[23] = d23;
        d23.getDistance(currentLocation);
        d23.citySection = "";


        //repurposed
        targetDistrict = "-obsolete-Glen Park 4";
        targetLocation.setLatitude(7.733684);
        targetLocation.setLongitude(-122.432512);
        targetZoomLevel = "17z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d24 = new District(targetDistrict,targetDistance,targetZoomLevel,
                targetLocation.getLatitude(),targetLocation.getLongitude());
        targetDistricts[24] = d24;
        d24.getDistance(currentLocation);
        d24.citySection = "";

        //repurposed
        targetDistrict = "-obsolete-Bernal Heights 1";
        targetLocation.setLatitude(7.747241);
        targetLocation.setLongitude(-122.423651);
        targetZoomLevel = "15z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d25 = new District(targetDistrict,targetDistance,targetZoomLevel,
                targetLocation.getLatitude(),targetLocation.getLongitude());
        targetDistricts[25] = d25;
        d25.getDistance(currentLocation);
        d25.citySection = "";










        //repurposed
        targetDistrict = "-obsolete-Bernal Heights 2";
        targetLocation.setLatitude(7.732988);
        targetLocation.setLongitude(-122.431891);
        targetZoomLevel = "16z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d26 = new District(targetDistrict,targetDistance,targetZoomLevel,
                targetLocation.getLatitude(),targetLocation.getLongitude());
        targetDistricts[26] = d26;
        d26.getDistance(currentLocation);
        d26.citySection = "";

        //repurposed
        targetDistrict = "-obsolete-Cole Valey2";
        targetLocation.setLatitude(7.766384);
        targetLocation.setLongitude(-122.452819);
        targetZoomLevel = "15z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d12 = new District(targetDistrict,targetDistance,targetZoomLevel,
                targetLocation.getLatitude(),targetLocation.getLongitude());
        targetDistricts[12] = d12;
        d12.getDistance(currentLocation);
        d12.citySection = "";



        //repurposed
        targetDistrict = "-obsolete-Cole Valey3";
        targetLocation.setLatitude(7.768204);
        targetLocation.setLongitude(-122.453120);
        targetZoomLevel = "14z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d13 = new District(targetDistrict,targetDistance,targetZoomLevel,
                targetLocation.getLatitude(),targetLocation.getLongitude());
        targetDistricts[13] = d13;
        d13.getDistance(currentLocation);
        d13.citySection = "";


        //repurposed
        targetDistrict = "-obsolete-Cole Valey4";
        targetLocation.setLatitude(7.768713);
        targetLocation.setLongitude(-122.448528);
        targetZoomLevel = "15z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d14 = new District(targetDistrict,targetDistance,targetZoomLevel,
                targetLocation.getLatitude(),targetLocation.getLongitude());
        targetDistricts[14] = d14;

        d14.getDistance(currentLocation);
        d14.citySection = "";


        //repurposed
        targetDistrict = "-obsolete-Cole Valley5";
        targetLocation.setLatitude(7.765321);
        targetLocation.setLongitude(-122.447884);
        targetZoomLevel = "15z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d15 = new District(targetDistrict,targetDistance,targetZoomLevel,
                targetLocation.getLatitude(),targetLocation.getLongitude());
        targetDistricts[15] = d15;

        d15.getDistance(currentLocation);
        d15.citySection = "";

        //repurposed
        targetDistrict = "-obsolete-Cole Valley6";
        targetLocation.setLatitude(7.761114);
        targetLocation.setLongitude(-122.447841);
        targetZoomLevel = "16z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d16 = new District(targetDistrict,targetDistance,targetZoomLevel,
                targetLocation.getLatitude(),targetLocation.getLongitude());
        targetDistricts[16] = d16;
        d16.getDistance(currentLocation);
        d16.citySection = "";

        //repurposed
        targetDistrict = "-obsolete-Cole Valley7";
        targetLocation.setLatitude(7.761724);
        targetLocation.setLongitude(-122.451832);
        targetZoomLevel = "14z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d17 = new District(targetDistrict,targetDistance,targetZoomLevel,
                targetLocation.getLatitude(),targetLocation.getLongitude());
        targetDistricts[17] = d17;
        d17.getDistance(currentLocation);
        d17.citySection = "";

        //repurposed
        targetDistrict = "-obsolete-Cole Valley8";
        targetLocation.setLatitude(7.764676);
        targetLocation.setLongitude(-122.452519);
        targetZoomLevel = "14z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d18 = new District(targetDistrict,targetDistance,targetZoomLevel,
                targetLocation.getLatitude(),targetLocation.getLongitude());
        targetDistricts[18] = d18;
        d18.getDistance(currentLocation);
        d18.citySection = "";



        //obsolete
        targetDistrict = "-obsolete-Central Sunset2";
        targetLocation.setLatitude(7.747901);
        targetLocation.setLongitude(-122.494751);
        targetZoomLevel = "17z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d3 = new District(targetDistrict,targetDistance,targetZoomLevel,
                7.747901,-122.494751);
        targetDistricts[3] = d3;
        d3.getDistance(currentLocation);
        d3.citySection = "";

        //obsolete
        targetDistrict = "-obsolete-Central Sunset3";
        targetLocation.setLatitude(7.748750);
        targetLocation.setLongitude(-122.476341);
        targetZoomLevel = "15z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d4 = new District(targetDistrict,targetDistance,targetZoomLevel,
                7.748750,-122.476341);
        targetDistricts[4] = d4;
        d4.getDistance(currentLocation);
        d4.citySection = "";

        //obsolete
        targetDistrict = "-obsolete-Central Sunset4";
        targetLocation.setLatitude(7.765240);
        targetLocation.setLongitude(-122.477628);
        targetZoomLevel = "15z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d5 = new District(targetDistrict,targetDistance,targetZoomLevel,
                7.765240,-122.477628);
        targetDistricts[5] = d5;
        d5.getDistance(currentLocation);
        d5.citySection = "";

        //obsolete
        targetDistrict = "-obsolete-Central Sunset 5";
        targetLocation.setLatitude(7.764425);
        targetLocation.setLongitude(-122.495739);
        targetZoomLevel = "17z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d6 = new District(targetDistrict,targetDistance,targetZoomLevel,
                7.764425,-122.495739);
        targetDistricts[6] = d6;
        d6.getDistance(currentLocation);
        d6.citySection = "";

        //obsolete
        targetDistrict = "-obsolete-Inner Sunset2";
        targetLocation.setLatitude(7.765307);
        targetLocation.setLongitude(-122.477113);
        targetZoomLevel = "15z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d7 = new District(targetDistrict,targetDistance,targetZoomLevel,
                7.765307,-122.477113);
        targetDistricts[7] = d7;
        d7.getDistance(currentLocation);
        d7.citySection = "";


        //obsolete
        targetDistrict = "-obsolete-Inner Sunset 3";
        targetLocation.setLatitude(7.765918);
        targetLocation.setLongitude(-122.458145);
        targetZoomLevel = "17z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d8 = new District(targetDistrict,targetDistance,targetZoomLevel,
                7.765918,-122.458145);
        targetDistricts[8] = d8;
        d8.getDistance(currentLocation);
        d8.citySection = "";


        //obsolete
        targetDistrict = "-obsolete-Inner Sunset4";
        targetLocation.setLatitude(7.753071);
        targetLocation.setLongitude(-122.463638);
        targetZoomLevel = "16z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d9 = new District(targetDistrict,targetDistance,targetZoomLevel,
                7.753071,-122.463638);
        targetDistricts[9] = d9;
        d9.getDistance(currentLocation);
        d9.citySection = "";


        //obsolete
        targetDistrict = "-obsolete-Inner Sunset5";
        targetLocation.setLatitude(7.747573);
        targetLocation.setLongitude(-122.470075);
        targetZoomLevel = "14z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d10 = new District(targetDistrict,targetDistance,targetZoomLevel,
                7.747573,-122.470075);
        targetDistricts[10] = d10;
        d10.getDistance(currentLocation);
        d10.citySection = "";

        //obsolete
        targetDistrict = "-obsolete-Inner Sunset6";
        targetLocation.setLatitude(7.738264);
        targetLocation.setLongitude(-122.470290);
        targetZoomLevel = "14z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d11 = new District(targetDistrict,targetDistance,targetZoomLevel,
                7.738264,-122.470290);
        targetDistricts[11] = d11;
        d11.getDistance(currentLocation);
        d11.citySection = "";

        //obsolete
        targetDistrict = "-obsolete--combined-Downtown - French Quarter";
        targetLocation.setLatitude(7.791307);
        targetLocation.setLongitude(-122.4033648);
        targetZoomLevel = "17z";
        targetDistance =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        setVars();
        District d1 = new District(targetDistrict,targetDistance,targetZoomLevel,
                7.791307,-122.4033648);
        targetDistricts[1] = d1;
        d1.addMapPoint(7.791811, -122.402716);
        d1.addMapPoint(7.791650, -122.404164);
        d1.addMapPoint(7.790819, -122.404014);
        d1.addMapPoint(7.791040, -122.402566);
        d1.getDistance(currentLocation);
        d1.citySection = "";


        ///////////end obsolete





        //////////////////////////
        ///////    //////////////
        /////////////







        //targetDistrictsArray.sort();
        //Arrays.sort(targetDistrictsArray, new FloatStringStringComparator());
        Arrays.sort(targetDistricts, new DistrictComparator());




        //Location.distanceBetween(double startLatitude, double startLongitude, double endLatitude, double endLongitude, float[] results)


        //if (37.747900 < lat < 37.765476) and ( -122.496239 < long < -122.476327) then central sunset

        if (37.734122 < lat && lat < 37.766222 && -122.496239 < lon && lon < -122.457572)  {district = "Inner Sunset";}
        if (37.747900 < lat && lat < 37.765476 && -122.496239 < lon && lon < -122.476327)  {district = "Central Sunset";}
        if (37.734122 < lat && lat < 37.764594 && -122.510230 < lon && lon < -122.493964)  {district = "Outer Sunset";}
        if (37.764119 < lat && lat < 37.774093 && -122.510959 < lon && lon < -122.453195)  {district = "Golden Gate Park";}



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
        for (int i=0;i<125; i++)
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