package com.safesanfrancisco.sfdb;

/**
 * Created by Brian on 4/24/2016.
 */


        import java.util.ArrayList;
        import java.util.Comparator;

        import android.location.Location;

import com.safesanfrancisco.sfdb.mapPoint;

//public class District (float distance, String district, String zoomLevel){
public class District {

    public Double metersInMile = 1609.34;
    public float distance = 999999999;
    public String district = "<><><><><>";
    public String zoomLevel = "zzzzzzz";
    public String citySection = "zzzzzzz";

    public float targetDistance = 999999999;

    //public mapPoint[] mapPoints;
    ArrayList<mapPoint> mapPoints = new ArrayList<mapPoint>();


    public District() {

    }

    public District(String inDistrict) {
        district = inDistrict;
    }
    public District(String inDistrict, float inDistance, String inZoomLevel) {
        district = inDistrict;
        distance = inDistance;
        zoomLevel = inZoomLevel;
    }
    public District(String inDistrict, float inDistance, String inZoomLevel, mapPoint inMapPoint) {
        district = inDistrict;
        distance = inDistance;
        zoomLevel = inZoomLevel;
        mapPoints.add(inMapPoint);
    }
    public District(String inDistrict, float inDistance, String inZoomLevel, double inLat, double inLon) {
        district = inDistrict;
        distance = inDistance;
        zoomLevel = inZoomLevel;
        mapPoints.add(new mapPoint(inLat, inLon));
    }
    public District(String inDistrict, mapPoint inMapPoint) {
        district = inDistrict;
        mapPoints.add(inMapPoint);
    }

    public void addMapPoint(mapPoint inMapPoint) {
        mapPoints.add(inMapPoint);
    }
    public void addMapPoint(double inLat, double inLon) {
        mapPoints.add(new mapPoint(inLat,inLon));
    }

    public float getDistance() {
        return distance;
    }
    public float getDistance(Location currentLocation) {

        Location targetLocation = new Location("");


        float targetDistanceCandidate = 999999999;
        //    double thisLat;
        //    double thisLon;

	   /*
	    Iterator<this.mapPoints> it = this.mapPoints.iterator();
	    while(it.hasNext())
	    {
	        Object obj = it.next();
	        //Do something with obj
	    }
	    */

        //List<mapPoint> arrayList = new ArrayList<mapPoint>();
        for (mapPoint m : mapPoints) {
            // if(m.equals(value)){
            //do something
            //}
            //thisLat = m.lat;
            //thisLon = m.lon;
            targetLocation.setLatitude(m.lat);
            targetLocation.setLongitude(m.lon);
            targetDistanceCandidate =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
            if(targetDistanceCandidate < targetDistance) {targetDistance = targetDistanceCandidate;}


        }

        //	thisLat = this.mapPoints.get(0).lat;
        //	thisLon = this.mapPoints.get(0).lon;
        //	targetLocation.setLatitude(thisLat);
        //	targetLocation.setLongitude(thisLon);
        //    targetDistanceCandidate =  (float) (targetLocation.distanceTo(currentLocation)/metersInMile);
        //    if(targetDistanceCandidate < targetDistance) {targetDistance = targetDistanceCandidate;}


        distance = targetDistance;
        return targetDistance;
    }



    public void setDistance(float distance) {
        this.distance = distance;
    }




}

class DistrictComparator implements Comparator<District> {
    public int compare(District o1, District o2) {
        float float1 = ((District) o1).getDistance();
        float float2 = ((District) o2).getDistance();
        if(float1==float2){return 0;}
        if(float1<float2){return -1;}
        if(float1>float2){return 1;}
        //if(float2<float1){return float1;}else{return float2;}
        //return str1.compareTo(str2);
        return 0;
    }
}

