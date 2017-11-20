package main.application;

import main.convois.ConvoiTrainA;
import main.convois.ConvoiTrainB;
import main.stations.StationAB;

public class ControleFerroviaire {
    public static void main(String [] args) {

        int nbTrainA = 4;

        final StationAB stationAB = new StationAB();

        Thread convoiTrainA = new Thread(new ConvoiTrainA(nbTrainA, stationAB));
        convoiTrainA.start();

        //Thread convoiTrainB = new Thread(new ConvoiTrainB(stationAB));

        //convoiTrainB.start();
    }
}
