package main.application;

import main.convois.ConvoiTrainA;
import main.convois.ConvoiTrainB;
import main.stations.StationAB;

public class ControleFerroviaire {
    public static void main(String [] args) {
        final StationAB stationAB = new StationAB();

        Thread convoiTrainA = new Thread(new ConvoiTrainA(stationAB));
        Thread convoiTrainB = new Thread(new ConvoiTrainB(stationAB));
        convoiTrainA.start();
        convoiTrainB.start();
    }
}
