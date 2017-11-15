package main.trains;

import java.util.concurrent.TimeUnit;

public abstract class Train {
    private static final int TEMPS_DELAI = 2;
    private static final int TEMPS_PANNE = 1;
    private static final int TEMPS_REPARATION = 9;
    private static final double CHANCE_DELAI = 0.50d;
    private static final double CHANCE_PANNE = 0.30d;

    public abstract String getIdentifiantComplet();
    public abstract void setId(long id);

    protected void delai(){
        double probabilite = Math.random();
        while(probabilite < CHANCE_DELAI)
        {
            try {
                traceDelai(TEMPS_DELAI);
                TimeUnit.SECONDS.sleep(TEMPS_DELAI);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            probabilite = Math.random();
        }
    }

    protected void panne(){
        double probabilite = Math.random();
        if (probabilite < CHANCE_PANNE)
        {
            try {
                tracePanne();
                TimeUnit.SECONDS.sleep(TEMPS_PANNE);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            try {
                traceReparation(TEMPS_REPARATION);
                TimeUnit.SECONDS.sleep(TEMPS_REPARATION);
                traceRepare();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            probabilite = Math.random();
        }
    }

    private void traceDelai(int tempsDelai) {
        System.out.printf("\n%s en delai de %s secondes", getIdentifiantComplet(), tempsDelai);
    }

    private void tracePanne() {
        System.out.printf("\n%s en panne, demande de reparation", getIdentifiantComplet());
    }

    private void traceReparation(int tempsReparation) {
        System.out.printf("\n%s en reparation pour %s secondes", getIdentifiantComplet(), tempsReparation);
    }

    private void traceRepare() {
        System.out.printf("\n%s reparé", getIdentifiantComplet());
    }
}
