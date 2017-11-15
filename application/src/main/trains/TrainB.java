package main.trains;

import main.stations.StationAB;

public class TrainB implements Train, Runnable {
    private final String NOM = "TrainB";
    private long id;
    private StationAB stationAB;

    public TrainB (StationAB stationAB) {
        this.stationAB = stationAB;
        this.id = 0;
    }

    @Override
    public String getIdentifiantComplet() {
        return this.NOM + "." + this.id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public void run() {
        stationAB.traverseStation(this);
    }
}