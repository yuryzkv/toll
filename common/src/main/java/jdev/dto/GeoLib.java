package jdev.dto;

import net.sf.geographiclib.Geodesic;
import net.sf.geographiclib.GeodesicData;


public class GeoLib {

    public static GeoData getGeoData(PointDTO a, PointDTO b) {
        GeodesicData geodesicData = Geodesic.WGS84.Inverse(a.getLat(), a.getLon(), b.getLat(), b.getLon());
        GeoData geoData = new GeoData();
        // the distance calculate in meters
        geoData.setDistance(geodesicData.s12);
        // head of first point
        geoData.setAzimut2(geodesicData.azi2);
        // head of second point
        geoData.setAzimut1(geodesicData.azi1);
        long difTime = b.getTime() -  a.getTime();
        if(difTime != 0) {
            System.out.println("The distance is "+geoData.distance+"m");
            System.out.println("The time is "+(difTime/1000)+"s");
            // object's speed
            geoData.setSpeed(geoData.distance / (difTime/1000));
        }
        return geoData;
    }

}
