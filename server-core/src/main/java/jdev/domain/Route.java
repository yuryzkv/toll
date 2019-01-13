package jdev.domain;

import javax.persistence.*;

@Entity
@Table(name="route")
public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ROUTE_ID")
    public int routeId;
    @Column(name="NAME")
    public String name;
    @Column(name="ROUTE_DEVICE")
    public String routeDevice;

    public int getRouteId() {
        return routeId;
    }

    public void setRouteId(int routeId) {
        this.routeId = routeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRouteDevice() {
        return routeDevice;
    }

    public void setRouteDevice(String routeDevice) {
        this.routeDevice = routeDevice;
    }
}
