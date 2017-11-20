package main.convois;

import main.Panne;
import main.stations.StationAB;
import main.stations.StationABC;
import main.trains.TrainA;

import java.util.concurrent.TimeUnit;

public class ConvoiTrainA implements Runnable {
    private static final int TEMPS_NOUVEAU_TRAINA = 2;
    private static final int ID = 0;

    private final int NB_TRAIN_A;
    private final StationAB STATION_AB;
    private final StationABC STATION_ABC;

    private Panne panneAB;
    private Panne panneABC;

    public ConvoiTrainA(int nbTrainA, StationAB stationAB, StationABC stationABC) {
        this.NB_TRAIN_A = nbTrainA;
        this.STATION_AB = stationAB;
        this.STATION_ABC = stationABC;

        this.panneAB = new Panne("AB");
        this.panneABC = new Panne("ABC");
    }

    public void panneSegmentAB(TrainA trainA) {
        panneSegment(this.panneAB, trainA);
    }

    public void panneSegmentABC(TrainA trainA) {
        panneSegment(this.panneABC, trainA);
    }

    private void panneSegment(Panne panneSegment, TrainA trainA) {
        panneSegment.panne(trainA);
    }

    public void traverseStationAB(TrainA trainA) {
        this.STATION_AB.traverseStation(trainA);
    }

    public void traverseStationABC(TrainA trainA) {
        this.STATION_ABC.traverseStation(trainA);
    }

    public int getConvoiId() {
        return this.ID;
    }

    @Override
    public void run() {
        int i = 1;
        while (i <= this.NB_TRAIN_A) {
            TrainA trainA = new TrainA(this);
            Thread thread = new Thread(trainA);
            trainA.setId(i);
            thread.setPriority(Thread.MIN_PRIORITY);
            thread.start();
            try {
                TimeUnit.SECONDS.sleep(this.TEMPS_NOUVEAU_TRAINA);
                i++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
