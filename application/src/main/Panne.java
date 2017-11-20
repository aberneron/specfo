package main;

import main.trains.Train;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Panne {
    private static final int MAX_TRAINS = 1;

    private static final int TEMPS_PANNE = 1;
    private static final double CHANCE_PANNE = 0.20d;

    private static final int TEMPS_REPARATION = 7;
    private static final int TEMPS_APPEL_REPARATION = 1;
    private static final double CHANCE_REPARATION = 0.50d;

    private final Semaphore SEMAPHORE;

    private String nomSegment;

    public Panne(String nomSegment) {
        this.SEMAPHORE = new Semaphore(this.MAX_TRAINS);
        this.nomSegment = nomSegment;
    }

    public void panne(Train train) {
        double probabilite = Math.random();
        if (probabilite < CHANCE_PANNE) {
            try {
                this.SEMAPHORE.acquire();
                traceTrainEnPanne(train);
                TimeUnit.SECONDS.sleep(TEMPS_PANNE);
                reparation(train);

            } catch (InterruptedException e) {
            } finally {
                this.SEMAPHORE.release();
                traceTrainRepare(train);
            }
        }
    }

    private void reparation(Train train) throws InterruptedException {
        double probabilite = Math.random();

        while (probabilite > CHANCE_REPARATION) {
            probabilite = Math.random();
            TimeUnit.SECONDS.sleep(TEMPS_APPEL_REPARATION);
        }

        traceTrainEnReparation(train);
        TimeUnit.SECONDS.sleep(TEMPS_REPARATION);
    }

    private void traceTrainEnPanne(Train train) {
        System.out.printf("\n%s tombe en panne sur segment %s", train.getIdentifiantComplet(), this.nomSegment);
    }

    private void traceTrainEnReparation(Train train) {
        System.out.printf("\n%s en réparation pour %s secondes", train.getIdentifiantComplet(), TEMPS_REPARATION);
    }

    private void traceTrainRepare(Train train) {
        System.out.printf("\n%s est réparé", train.getIdentifiantComplet());
    }
}
