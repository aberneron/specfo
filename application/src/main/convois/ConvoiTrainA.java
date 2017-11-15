package main.convois;

import main.stations.StationAB;
import main.trains.TrainA;

import java.util.concurrent.TimeUnit;

public class ConvoiTrainA implements Runnable {
    private final StationAB stationAB;

    public ConvoiTrainA (StationAB stationAB) {
        this.stationAB = stationAB;
    }

    @Override
    public void run() {
        while (true) {
            TrainA trainA = new TrainA(this.stationAB);
            Thread thread = new Thread(trainA);
            trainA.setId(thread.getId());
            thread.start();
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
