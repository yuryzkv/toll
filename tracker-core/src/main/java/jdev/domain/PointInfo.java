package jdev.domain;

import javax.persistence.*;

@Entity
@Table(name="POINT_INFO")
public class PointInfo {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="POINT_INFO_ID")
    public int pointInfoId;
    @Column(name="PLACEMARK_ID")
    public int placemarkId;
    @Column(name="ROUTE_ID")
    public int routeId;
    @Column(name="ROW_TIME")
    public int rowTime;

    public int getPointInfoId() {
        return pointInfoId;
    }

    public void setPointInfoId(int pointInfoId) {
        this.pointInfoId = pointInfoId;
    }

    public int getPlacemarkId() {
        return placemarkId;
    }

    public void setPlacemarkId(int placemarkId) {
        this.placemarkId = placemarkId;
    }

    public int getRouteId() {
        return routeId;
    }

    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }

    public int getRowTime() {
        return rowTime;
    }

    public void setRowTime(int rowTime) {
        this.rowTime = rowTime;
    }
}
