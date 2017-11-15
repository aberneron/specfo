package main.convois;

import main.stations.StationAB;
import main.trains.TrainB;

import java.util.concurrent.TimeUnit;

public class ConvoiTrainB implements Runnable {
    private final StationAB stationAB;

    public ConvoiTrainB(StationAB stationAB) {
        this.stationAB = stationAB;
    }

    @Override
    public void run() {
        while (true) {
            TrainB trainB = new TrainB(this.stationAB);
            Thread thread = new Thread(trainB);
            trainB.setId(thread.getId());
            thread.start();
            try {
                TimeUnit.SECONDS.sleep(4);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
