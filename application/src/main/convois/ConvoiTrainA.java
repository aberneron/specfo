package main.convois;

import main.Panne;
import main.stations.StationAB;
import main.trains.TrainA;

import java.util.concurrent.TimeUnit;

public class ConvoiTrainA implements Runnable {
    private static final int TEMPS_NOUVEAU_TRAINA = 2;
    private static final int convoiId = 1;

    private final int nbTrainA;
    private final StationAB stationAB;
    //private final StationABC stationABC;

    private Panne panneAB;
    private Panne panneABC;

    public ConvoiTrainA(int nbTrainA, StationAB stationAB) {
        this.nbTrainA = nbTrainA;
        this.stationAB = stationAB;

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

    public void traverserStationAB(TrainA trainA) {
        this.stationAB.traverseStation(trainA);
    }

    public int getConvoiId() {
        return this.convoiId;
    }

    @Override
    public void run() {
        int i = 0;
        while (i < this.nbTrainA) {
            TrainA trainA = new TrainA(this);
            Thread thread = new Thread(trainA);
            trainA.setId(i + 1);
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
