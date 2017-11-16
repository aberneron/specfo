package main.trains;

import main.convois.ConvoiTrainB;
import main.stations.StationAB;

public class TrainB extends Train implements Runnable {
    private final String NOM = "TrainB";
    private long id;
    private ConvoiTrainB convoiTrain;

    public TrainB (ConvoiTrainB convoiTrain) {
        this.convoiTrain = convoiTrain;
        this.id = 0;
    }

    @Override
    public String getIdentifiantComplet() {
        return this.NOM + "." + this.id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public void run() {
        delai();
        convoiTrain.panneSegment(1,this);
        convoiTrain.traverserStationAB(this);
        delai();
        convoiTrain.panneSegment(2,this);
        //station BC
        delai();
        convoiTrain.panneSegment(3,this);
        //station ABC
    }
}