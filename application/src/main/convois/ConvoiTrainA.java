package main.convois;

import main.stations.StationAB;
import main.trains.TrainA;

import java.util.concurrent.TimeUnit;

public class ConvoiTrainA implements Runnable {
    private final StationAB stationAB;
    private final int MAX_TRAIN_A = 2;
    private final int TEMPS_NOUVEAU_TRAINA = 10;

    public ConvoiTrainA (StationAB stationAB) {
        this.stationAB = stationAB;
    }

    @Override
    public void run() {
        int i = 0;
        while (i < this.MAX_TRAIN_A) {
            TrainA trainA = new TrainA(this.stationAB);
            Thread thread = new Thread(trainA);
            trainA.setId(thread.getId());
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
