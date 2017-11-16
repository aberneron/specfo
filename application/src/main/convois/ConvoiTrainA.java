package main.convois;

import main.Panne;
import main.stations.StationAB;
import main.trains.TrainA;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class ConvoiTrainA implements Runnable {
    private static final int MAX_TRAIN_A = 2;
    private static final int TEMPS_NOUVEAU_TRAINA = 5;

    private ArrayList<Panne> Pannes;
    private final StationAB stationAB;
    //private final StationABC stationABC;

    public ConvoiTrainA(StationAB stationAB) {
        this.stationAB = stationAB;
        this.Pannes = new ArrayList<Panne>();
        this.Pannes.add(new Panne(1));
        this.Pannes.add(new Panne(2));
    }

    public void panneSegment(int segment, TrainA trainA) {
        this.Pannes.get(segment - 1).panne(trainA);
    }

    public void traverserStationAB(TrainA trainA) {
        this.stationAB.traverseStation(trainA);
    }

    @Override
    public void run() {
        int i = 0;
        while (i < this.MAX_TRAIN_A) {
            TrainA trainA = new TrainA(this);
            Thread thread = new Thread(trainA);
            trainA.setId(i+1);
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
