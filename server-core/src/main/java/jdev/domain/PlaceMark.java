package jdev.domain;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="placemark")
public class PlaceMark {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="PLACEMARK_ID")
    public int placemark_id;

    @Column(name="ROUTE_ID")
    public int routeId;
    @Column(name="LONGITUDE")
    public double longitude;
    @Column(name="LATITUDE")
    public double latitude;
    @Column(name="TIME")
    public long time;
    @Column(name="ROW_TIME")
    public Timestamp rowTime;

    public int getPlacemark_id() {
        return placemark_id;
    }

    public void setPlacemark_id(int placemark_id) {
        this.placemark_id = placemark_id;
    }

    public int getRouteId() {
        return routeId;
    }

    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public Timestamp getRowTime() {
        return rowTime;
    }

    public void setRowTime(Timestamp rowTime) {
        this.rowTime = rowTime;
    }
}
