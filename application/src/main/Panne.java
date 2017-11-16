package main;

import main.trains.Train;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Panne {
    private static final int MAX_TRAINS = 1;
    private static final int TEMPS_REPARATION = 7;
    private static final int TEMPS_PANNE = 1;
    private static final double CHANCE_PANNE = 0.20d;
    private static final double CHANCE_REPARATION = 0.30d;
    private final Semaphore SEMAPHORE;
    private long id;

    public Panne(int id) {
        this.SEMAPHORE = new Semaphore(this.MAX_TRAINS);
        this.id = id;
    }

    public void panne(Train train) {
        double probabilite = Math.random();
        if (probabilite < CHANCE_PANNE) {
            try {
                this.SEMAPHORE.acquire();
                traceTrainEnPanne(train);
                TimeUnit.SECONDS.sleep(TEMPS_PANNE);

                probabilite = Math.random();
                while (probabilite > CHANCE_REPARATION) {
                    probabilite = Math.random();
                }

                traceTrainEnReparation(train);
                TimeUnit.SECONDS.sleep(TEMPS_REPARATION);

            } catch (InterruptedException e) {
            } finally {
                this.SEMAPHORE.release();
                traceTrainRepare(train);
            }
        }
    }

    private void traceTrainEnPanne(Train train) {
        System.out.printf("\n%s tombe en panne sur segment %s", train.getIdentifiantComplet(), id);
    }

    private void traceTrainEnReparation(Train train) {
        System.out.printf("\n%s en réparation pour %s secondes", train.getIdentifiantComplet(), TEMPS_REPARATION);
    }

    private void traceTrainRepare(Train train) {
        System.out.printf("\n%s est réparé", train.getIdentifiantComplet());
    }
}
