package jdev.tracker;


import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "placemark")
public class PlaceMark {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "PLACEMARK_ID")
    public int placemark_id;
    @Column(name="ROUTE_ID")
    public int route_id;
    @Column(name="LONGITUDE")
    public double longitude;
    @Column(name="LATITUDE")
    public double latitude;
    @Column(name="TIME")
    public long time;
    @Column(name="ROW_TIME")
    public Timestamp row_time;

    public int getPlacemark_id() {
        return placemark_id;
    }

    public void setPlacemark_id(int placemark_id) {
        this.placemark_id = placemark_id;
    }

    public int getRoute_id() {
        return route_id;
    }

    public void setRoute_id(int route_id) {
        this.route_id = route_id;
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

    public Timestamp getRow_time() {
        return row_time;
    }

    public void setRow_time(Timestamp row_time) {
        this.row_time = row_time;
    }
}
