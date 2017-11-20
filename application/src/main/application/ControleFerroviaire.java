package main.application;

import main.convois.ConvoiTrainA;
import main.convois.ConvoiTrainB;
import main.convois.ConvoiTrainC;
import main.stations.StationAB;
import main.stations.StationABC;
import main.stations.StationBC;

import java.util.Scanner;

public class ControleFerroviaire {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int nbTrainA = 0;
        int nbTrainB = 0;
        int nbTrainC = 0;

        try {
            System.out.print("Entrer le nombre de train A:");
            nbTrainA = Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            System.out.print("Impossible de lire le nombre de train A.");
        }

        try {
            System.out.print("Entrer le nombre de train B:");
            nbTrainB = Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            System.out.print("Impossible de lire le nombre de train B.");
        }

        try {
            System.out.print("Entrer le nombre de train C:");
            nbTrainC = Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            System.out.print("Impossible de lire le nombre de train C.");
        }

        StationAB stationAB = new StationAB();
        StationBC stationBC = new StationBC();
        StationABC stationABC = new StationABC();

        Thread convoiTrainA = new Thread(new ConvoiTrainA(nbTrainA, stationAB, stationABC));
        Thread convoiTrainB = new Thread(new ConvoiTrainB(nbTrainB, stationAB, stationBC, stationABC));
        Thread convoiTrainC = new Thread(new ConvoiTrainC(nbTrainC, stationBC, stationABC));

        convoiTrainA.start();
        convoiTrainB.start();
        convoiTrainC.start();
    }
}
