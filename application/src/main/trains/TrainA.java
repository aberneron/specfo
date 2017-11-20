package main.trains;

import main.convois.ConvoiTrainA;

public class TrainA extends Train implements Runnable {

    private final String NOM = "TrainA";
    private long id;
    private ConvoiTrainA convoiTrain;

    public TrainA(ConvoiTrainA convoiTrain) {
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
        //convoiTrain.traverserStationABC(this);
    }


}
