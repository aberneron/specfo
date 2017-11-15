package main.trains;

import main.stations.StationAB;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

public class TrainA implements Train, Runnable {
    private static final int TEMPS_DELAI = 2;
    private static final int TEMPS_PANNE = 1;
    private static final int TEMPS_REPARATION = 9;
    private static final double CHANCE_DELAI = 0.50d;
    private static final double CHANCE_PANNE = 0.30d;
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
    
    private void delai(){
        double probabilite = Math.random();
        while(probabilite < CHANCE_DELAI)
        {
            try {
                traceDelai();
                TimeUnit.SECONDS.sleep(TEMPS_DELAI);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            probabilite = Math.random();
        }
    }

    private void panne(){
        double probabilite = Math.random();
        while(probabilite < CHANCE_PANNE)
        {
            try {
                tracePanne();
                TimeUnit.SECONDS.sleep(TEMPS_PANNE);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                traceReparation();
                TimeUnit.SECONDS.sleep(TEMPS_REPARATION);
                traceRepare();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            probabilite = Math.random();
        }
    }

    private void traceDelai() {
        System.out.printf("\n%s en delai de %s secondes", getIdentifiantComplet(), TEMPS_DELAI);
    }

    private void tracePanne() {
        System.out.printf("\n%s en panne, demande de reparation", getIdentifiantComplet());
    }

    private void traceReparation() {
        System.out.printf("\n%s en reparation pour %s secondes", getIdentifiantComplet(), TEMPS_REPARATION);
    }

    private void traceRepare() {
        System.out.printf("\n%s reparé", getIdentifiantComplet());
    }

}
