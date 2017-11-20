package main.application;

import main.convois.ConvoiTrainA;
import main.convois.ConvoiTrainB;
import main.convois.ConvoiTrainC;
import main.stations.StationAB;
import main.stations.StationABC;
import main.stations.StationBC;

public class ControleFerroviaire {
    public static void main(String [] args) {

        int nbTrainA = 3;
        int nbTrainB = 3;
        int nbTrainC = 3;

        StationAB stationAB = new StationAB();
        StationBC stationBC = new StationBC();
        StationABC stationABC = new StationABC();

        Thread convoiTrainA = new Thread(new ConvoiTrainA(nbTrainA, stationAB, stationABC));
        Thread convoiTrainB = new Thread(new ConvoiTrainB(nbTrainB, stationAB, stationBC, stationABC));
        Thread convoiTrainC = new Thread(new ConvoiTrainC(nbTrainC, stationBC, stationABC));

        convoiTrainA.start();
        //convoiTrainB.start();
        //convoiTrainC.start();
    }
}
