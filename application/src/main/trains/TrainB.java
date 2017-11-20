package main.trains;

import main.convois.ConvoiTrainB;

public class TrainB extends Train implements Runnable {
    private final String NOM = "TrainB";
    private long id;
    private ConvoiTrainB convoiTrain;

    public TrainB(ConvoiTrainB convoiTrain) {
        this.convoiTrain = convoiTrain;
        this.id = 0;
    }

    @Override
    public String getIdentifiantComplet() {
        return this.NOM + "." + this.id;
    }

    @Override
    public int getConvoiId() {
        return this.convoiTrain.getConvoiId();
    }

    @Override
    public long getTrainId() {
        return this.id;
    }

    @Override
    public void setId(long id) {
        this.id = id;
    }

    @Override
    public void run() {
        delai();
        convoiTrain.panneSegmentAB(this);
        convoiTrain.traverserStationAB(this);
        //delai();
        //convoiTrain.panneSegment(2,this);
        //station BC
        //delai();
        //convoiTrain.panneSegment(3,this);
        //station ABC
    }
}