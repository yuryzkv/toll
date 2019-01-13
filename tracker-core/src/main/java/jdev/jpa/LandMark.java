package jdev.jpa;

import javax.xml.bind.annotation.XmlElement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LandMark {
    public double lat;
    public double lon;
    public double ele;
    public long curren_time;

    @XmlElement(name = "coordinates")
    public void setCoordinates(String coordinates){
        String[] arrayCoordinate = coordinates.split(",");
        setLon(Double.parseDouble(arrayCoordinate[0]));
        setLat(Double.parseDouble(arrayCoordinate[1]));
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }


    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public double getEle() {
        return ele;
    }

    public void setEle(double ele) {
        this.ele = ele;
    }

    public long getCurren_time() {
        return curren_time;
    }


    public void setCurren_time(long curren_time) {
        this.curren_time = curren_time;
    }

    public void setTime(String time) throws ParseException {
        //   2018/09/08 04:21:35+00
        SimpleDateFormat dateFormat  = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss+00");
        Date date = dateFormat.parse(time);
        this.curren_time = date.getTime();
        System.out.println("===> Point's DATE is "+dateFormat.format(date));
    }

    @Override
    public String toString(){
        String s = "lat="+lat+",lon="+lon+",current_time="+curren_time;
        return s;
    }
}
