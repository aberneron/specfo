package main.stations;

import main.trains.Train;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class StationAB {
    private final int MAX_TRAINS_STATION = 1;
    private final long DUREE_ATTENTE_STATION = 2;
    private final Semaphore SEMAPHORE;

    public StationAB() {
        this.SEMAPHORE = new Semaphore(this.MAX_TRAINS_STATION);
    }

    public void traverseStation(Train train){
        try {
            traceRequeteStationTrain(train);
            this.SEMAPHORE.acquire();
            traceEntreStationTrain(train);
            TimeUnit.SECONDS.sleep(DUREE_ATTENTE_STATION);
        } catch (InterruptedException e){

        } finally {
            traceSortStationTrain(train);
            this.SEMAPHORE.release();
        }
    }

    private void traceRequeteStationTrain(Train train) {
        System.out.printf("\n%s requête à la station AB", train.getIdentifiantComplet());
    }

    private void traceEntreStationTrain(Train train) {
        System.out.printf("\n%s entre dans la station AB", train.getIdentifiantComplet());
    }

    private void traceSortStationTrain(Train train) {
        System.out.printf("\n%s sort de la station AB", train.getIdentifiantComplet());
    }
}
