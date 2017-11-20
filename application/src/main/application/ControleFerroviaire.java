package main.application;

import main.convois.ConvoiTrainA;
import main.convois.ConvoiTrainB;
import main.stations.StationAB;
import main.stations.StationABC;

public class ControleFerroviaire {
    public static void main(String [] args) {

        int nbTrainA = 3;

        StationAB stationAB = new StationAB();
        StationABC stationABC = new StationABC();

        Thread convoiTrainA = new Thread(new ConvoiTrainA(nbTrainA, stationAB, stationABC));
        convoiTrainA.start();

        //Thread convoiTrainB = new Thread(new ConvoiTrainB(stationAB));

        //convoiTrainB.start();
    }
}
