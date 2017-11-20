package main.trains;

import main.convois.ConvoiTrainC;

public class TrainC extends Train implements Runnable {
    private final String NOM = "TrainC";
    private long id;
    private ConvoiTrainC convoiTrain;

    public TrainC(ConvoiTrainC convoiTrain) {
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
        convoiTrain.panneSegmentBC(this);
        convoiTrain.traverseStationBC(this);

        delai();
        convoiTrain.panneSegmentABC(this);
        convoiTrain.traverseStationABC(this);
    }
}
