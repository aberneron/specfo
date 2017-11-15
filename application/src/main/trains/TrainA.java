package main.trains;

import main.stations.StationAB;

public class TrainA implements Train, Runnable {
    private final String NOM = "TrainA";
    private long id;
    private StationAB stationAB;

    public TrainA (StationAB stationAB) {
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
