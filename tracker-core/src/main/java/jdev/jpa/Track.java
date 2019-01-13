package jdev.jpa;

import java.util.ArrayList;

public class Track {
    public String trackName;
    public String devId;
    public ArrayList<LandMark> trackPoints;

    public ArrayList<LandMark> getTrackPoints() {
        return trackPoints;
    }

    public void setTrackPoints(ArrayList<LandMark> trackPoints) {
        this.trackPoints = trackPoints;
    }

    public String getTrackName() {
        return trackName;
    }

    public void setTrackName(String trackName) {
        this.trackName = trackName;
    }

    public String getDevId() {
        return devId;
    }

    public void setDevId(String devId) {
        this.devId = devId;
    }
}
