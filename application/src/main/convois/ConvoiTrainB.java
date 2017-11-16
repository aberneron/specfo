package main.convois;

import main.Panne;
import main.stations.StationAB;
import main.trains.TrainA;
import main.trains.TrainB;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class ConvoiTrainB implements Runnable {
    private static final int MAX_TRAIN_B = 2;
    private static final int TEMPS_NOUVEAU_TRAINB = 5;

    private ArrayList<Panne> Pannes;
    private final StationAB stationAB;
    //private final StationABC stationABC;

    public ConvoiTrainB (StationAB stationAB) {
        this.stationAB = stationAB;
        this.Pannes = new ArrayList<Panne>();
        this.Pannes.add(new Panne(1));
        this.Pannes.add(new Panne(2));
        this.Pannes.add(new Panne(3));
    }

    public void panneSegment(int segment, TrainB trainB) {
        this.Pannes.get(segment-1).panne(trainB);
    }

    public void traverserStationAB(TrainB trainB) {
        this.stationAB.traverseStation(trainB);
    }

    @Override
    public void run() {
        int i = 0;
        while (i < this.MAX_TRAIN_B) {
            TrainB trainB = new TrainB(this);
            Thread thread = new Thread(trainB);
            trainB.setId(i+1);
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
