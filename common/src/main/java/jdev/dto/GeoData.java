package jdev.dto;

public class GeoData {
    public double distance;
    public double azimut2;
    public double azimut1;
    public double speed;

    public double getAzimut1() {
        return azimut1;
    }

    public void setAzimut1(double azimut1) {
        this.azimut1 = azimut1;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getAzimut2() {
        return azimut2;
    }

    public void setAzimut2(double azimut2) {
        this.azimut2 = azimut2;
    }
}
