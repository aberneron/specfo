package main.convois;

import main.Panne;
import main.stations.StationAB;
import main.stations.StationABC;
import main.stations.StationBC;
import main.trains.TrainB;

import java.util.concurrent.TimeUnit;

public class ConvoiTrainB implements Runnable {
    private static final int TEMPS_NOUVEAU_TRAINB = 2;
    private static final int ID = 1;

    private final int NB_TRAIN_B;
    private final StationAB STATION_AB;
    private final StationBC STATION_BC;
    private final StationABC STATION_ABC;

    private Panne panneAB;
    private Panne panneBC;
    private Panne panneABC;

    public ConvoiTrainB(int nbTrainB, StationAB stationAB, StationBC stationBC, StationABC stationABC) {
        this.NB_TRAIN_B = nbTrainB;
        this.STATION_AB = stationAB;
        this.STATION_BC = stationBC;
        this.STATION_ABC = stationABC;

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

    public void traverseStationAB(TrainB trainB) {
        this.STATION_AB.traverseStation(trainB);
    }

    public void traverseStationBC(TrainB trainB) {
        this.STATION_BC.traverseStation(trainB);
    }

    public void traverseStationABC(TrainB trainB) {
        this.STATION_ABC.traverseStation(trainB);
    }

    public int getConvoiId() {
        return this.ID;
    }

    @Override
    public void run() {
        int i = 1;
        while (i <= this.NB_TRAIN_B) {
            TrainB trainB = new TrainB(this);
            Thread thread = new Thread(trainB);
            trainB.setId(i);
            thread.setPriority(Thread.MAX_PRIORITY);
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
