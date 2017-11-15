package main.convois;

import main.stations.StationAB;
import main.trains.TrainB;

import java.util.concurrent.TimeUnit;

public class ConvoiTrainB implements Runnable {
    private final StationAB stationAB;
    private final int MAX_TRAIN_B = 2;
    private final int TEMPS_NOUVEAU_TRAINB = 8;

    public ConvoiTrainB(StationAB stationAB) {
        this.stationAB = stationAB;
    }

    @Override
    public void run() {
        int i = 0;
        while (i < this.MAX_TRAIN_B) {
            TrainB trainB = new TrainB(this.stationAB);
            Thread thread = new Thread(trainB);
            trainB.setId(thread.getId());
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
