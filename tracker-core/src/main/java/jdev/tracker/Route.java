package jdev.tracker;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Route {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="ROUTE_ID")
    public int route_id;
    @Column(name="NAME")
    public String name;

    public int getRoute_id() {
        return route_id;
    }

    public void setRoute_id(int route_id) {
        this.route_id = route_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
