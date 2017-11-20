package main.stations;

import main.trains.Train;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class StationAB {
    private static int convoiTrainCourrant = 0;
    private final int MAX_TRAIN_TYPE_STATION = 1;
    private final long DUREE_ATTENTE_STATION = 1;
    private final long TEMPS_STATION = 4;
    private final Semaphore SEMAPHORE;
    private int nombreTrain = 0;
    private Map<Integer, Integer> prochainTrainEntree = new HashMap<Integer, Integer>();
    private Map<Integer, Integer> prochainTrainSortie = new HashMap<Integer, Integer>();

    public StationAB() {
        this.SEMAPHORE = new Semaphore(this.MAX_TRAIN_TYPE_STATION);

        this.prochainTrainEntree.put(0, 1);
        this.prochainTrainEntree.put(1, 1);
        this.prochainTrainSortie.put(0, 1);
        this.prochainTrainSortie.put(1, 1);
    }

    public void traverseStation(Train train) {
        try {
            traceRequeteStationTrain(train);
            while (!trainEntreSansDepassement(train)) {
                TimeUnit.SECONDS.sleep(DUREE_ATTENTE_STATION);
            }
            tourConvoiTrain(train);

            TimeUnit.SECONDS.sleep(TEMPS_STATION);

            while (!trainSortSansDepassement(train)) {
                TimeUnit.SECONDS.sleep(DUREE_ATTENTE_STATION);
            }
            trainSort(train);

            if (nombreTrain == 0) {
                convoiTrainCourrant = 0;
                this.SEMAPHORE.release();
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void tourConvoiTrain(Train train) {
        try {
            if (this.convoiTrainCourrant == 0) {
                this.convoiTrainCourrant = train.getConvoiId();
                this.SEMAPHORE.acquire();
                trainEntre(train);

            } else if (this.convoiTrainCourrant == train.getConvoiId()) {

                trainEntre(train);
            } else { //wrong train need to wait.
                traceAttendreStation(train);
                TimeUnit.SECONDS.sleep(DUREE_ATTENTE_STATION);
                this.SEMAPHORE.acquire();

                this.convoiTrainCourrant = train.getConvoiId();
                trainEntre(train);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private boolean trainEntreSansDepassement(Train train) {
        if (this.prochainTrainEntree.get(train.getConvoiId()) == train.getTrainId())
            return true;
        else
            return false;
    }

    private boolean trainSortSansDepassement(Train train) {
        if (this.prochainTrainSortie.get(train.getConvoiId()) == train.getTrainId())
            return true;
        else
            return false;
    }

    private void trainEntre(Train train) {
        this.prochainTrainEntree.put(train.getConvoiId(), this.prochainTrainEntree.get(train.getConvoiId()) + 1);
        this.nombreTrain++;
        traceEntreStationTrain(train);
    }

    private void trainSort(Train train) {
        this.prochainTrainSortie.put(train.getConvoiId(), this.prochainTrainSortie.get(train.getConvoiId()) + 1);
        this.nombreTrain--;
        traceSortStationTrain(train);
    }

    private void traceAttendreStation(Train train) {
        System.out.printf("\n%s en attente pour entre dans la station AB", train.getIdentifiantComplet());
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
