package main.convois;

import main.Panne;
import main.stations.StationABC;
import main.stations.StationBC;
import main.trains.TrainC;

import java.util.concurrent.TimeUnit;

public class ConvoiTrainC implements Runnable {
    private static final int TEMPS_NOUVEAU_TRAINA = 2;
    private static final int ID = 3;

    private final int NB_TRAIN_C;
    private final StationBC STATION_BC;
    private final StationABC STATION_ABC;

    private Panne panneBC;
    private Panne panneABC;

    public ConvoiTrainC(int nbTrainC, StationBC stationBC, StationABC stationABC) {
        this.NB_TRAIN_C = nbTrainC;
        this.STATION_BC = stationBC;
        this.STATION_ABC = stationABC;

        this.panneBC = new Panne("BC");
        this.panneABC = new Panne("ABC");
    }

    public void panneSegmentBC(TrainC trainC) {
        panneSegment(this.panneBC, trainC);
    }

    public void panneSegmentABC(TrainC trainC) {
        panneSegment(this.panneABC, trainC);
    }

    private void panneSegment(Panne panneSegment, TrainC trainC) {
        panneSegment.panne(trainC);
    }

    public void traverseStationBC(TrainC trainC) {
        this.STATION_BC.traverseStation(trainC);
    }

    public void traverseStationABC(TrainC trainC) {
        this.STATION_ABC.traverseStation(trainC);
    }

    public int getConvoiId() {
        return this.ID;
    }

    @Override
    public void run() {
        int i = 0;
        while (i < this.NB_TRAIN_C) {
            TrainC trainC = new TrainC(this);
            Thread thread = new Thread(trainC);
            trainC.setId(i + 1);
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
