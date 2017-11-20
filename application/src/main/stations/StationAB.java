package main.stations;

import main.trains.Train;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class StationAB {
    private final int MAX_TRAIN_TYPE_STATION = 1;
    private final long DUREE_ATTENTE_STATION = 1;
    private final long TEMPS_STATION = 4;
    private final Semaphore SEMAPHORE;

    private static int convoiTrainCourrant = 0;

    private int nombreTrain = 0;
    private int[] prochainTrainEntre;
    private int[] prochainTrainSort;

    public StationAB() {
        this.SEMAPHORE = new Semaphore(this.MAX_TRAIN_TYPE_STATION);

        this.prochainTrainEntre = new int[2];
        this.prochainTrainEntre[0] = 1;
        this.prochainTrainEntre[1] = 1;
        this.prochainTrainSort = new int[2];
        this.prochainTrainSort[0] = 1;
        this.prochainTrainSort[1] = 1;
    }

    public void traverseStation(Train train) {
        try {
            traceRequeteStationTrain(train);
            while (!trainEntreSansDepassement(train)) {
                TimeUnit.SECONDS.sleep(DUREE_ATTENTE_STATION);
            }

            traceStation();
            tourConvoiTrain(train);
            traceStation();

            TimeUnit.SECONDS.sleep(TEMPS_STATION);

            while (!trainSortSansDepassement(train)) {
                TimeUnit.SECONDS.sleep(DUREE_ATTENTE_STATION);
            }
            trainSort(train);

            if (nombreTrain == 0) {
                convoiTrainCourrant = 0;
                this.SEMAPHORE.release();
            }
            traceStation();

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

                System.out.printf("\nWAITING TRAIN HAVE ACQUIRED THE RELEASE SEMAPHORE");

                this.convoiTrainCourrant = train.getConvoiId();
                trainEntre(train);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private boolean trainEntreSansDepassement(Train train) {
        if (this.prochainTrainEntre[train.getConvoiId()] == train.getTrainId())
            return true;
        else
            return false;
    }

    private boolean trainSortSansDepassement(Train train) {
        if (this.prochainTrainSort[train.getConvoiId()] == train.getTrainId())
            return true;
        else
            return false;
    }

    private void trainEntre(Train train) {
        this.prochainTrainEntre[train.getConvoiId()]++;
        this.nombreTrain++;
        traceEntreStationTrain(train);
    }

    private void trainSort(Train train) {
        this.prochainTrainSort[train.getConvoiId()]++;
        this.nombreTrain--;
        traceSortStationTrain(train);
    }

    private void traceAttendreStation(Train train) {
        System.out.printf("\n%s en attente pour entre dans la station", train.getIdentifiantComplet());
    }

    private void traceStation() {
        System.out.printf("\nIl y a %s train à la station AB de type %s", this.nombreTrain, this.convoiTrainCourrant);
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
