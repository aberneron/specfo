package main.convois;

import main.Panne;
import main.stations.StationABC;
import main.stations.StationBC;
import main.trains.TrainC;

import java.util.concurrent.TimeUnit;

public class ConvoiTrainC implements Runnable {
    private static final int TEMPS_NOUVEAU_TRAINC = 2;
    private static final int ID = 2;

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

    /**
     * Création successive des NB_TRAIN_C trains C.
     * Chaque train C est géré par un thread avec une priorité maximum pour
     * répondre Ã  la spécification du LTS.
     * On attend ensuite TEMPS_NOUVEAU_TRAINC secondes avant de créer le prochain train.
     */
    @Override
    public void run() {
        int i = 1;
        while (i <= this.NB_TRAIN_C) {
            TrainC trainC = new TrainC(this);
            Thread thread = new Thread(trainC);
            trainC.setId(i);
            thread.setPriority(Thread.MAX_PRIORITY);
            thread.start();
            try {
                TimeUnit.SECONDS.sleep(this.TEMPS_NOUVEAU_TRAINC);
                i++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
