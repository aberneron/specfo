package main.trains;

import main.stations.StationAB;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

public class TrainA extends Train implements Runnable {

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
        delai();
        panne();
        delai();
        stationAB.traverseStation(this);
        
        //etc..
    }    
    


}
