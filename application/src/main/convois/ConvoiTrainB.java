package main.convois;

import main.Panne;
import main.stations.StationAB;
import main.trains.TrainB;

import java.util.concurrent.TimeUnit;

public class ConvoiTrainB implements Runnable {
    private static final int TEMPS_NOUVEAU_TRAINB = 2;
    private static final int convoiId = 2;

    private final int nbTrainB;
    private final StationAB stationAB;
    //private final StationBC stationBC;
    //private final StationABC stationABC;

    private Panne panneAB;
    private Panne panneBC;
    private Panne panneABC;

    public ConvoiTrainB(int nbTrainB, StationAB stationAB) {
        this.nbTrainB = nbTrainB;
        this.stationAB = stationAB;

        this.panneAB = new Panne("AB");
        this.panneBC = new Panne("BC");
        this.panneABC = new Panne("ABC");
    }

    public void panneSegmentAB(TrainB trainB) {
        panneSegment(this.panneAB, trainB);
    }

    public void panneSegmentBC(TrainB trainB) {
        panneSegment(this.panneBC, trainB);
    }

    public void panneSegmentABC(TrainB trainB) {
        panneSegment(this.panneABC, trainB);
    }

    private void panneSegment(Panne panneSegment, TrainB trainB) {
        panneSegment.panne(trainB);
    }

    public void traverserStationAB(TrainB trainB) {
        this.stationAB.traverseStation(trainB);
    }

    public int getConvoiId() {
        return this.convoiId;
    }

    @Override
    public void run() {
        int i = 0;
        while (i < this.nbTrainB) {
            TrainB trainB = new TrainB(this);
            Thread thread = new Thread(trainB);
            trainB.setId(i + 1);
            thread.start();
            try {
                TimeUnit.SECONDS.sleep(this.TEMPS_NOUVEAU_TRAINB);
                i++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
