package main.trains;

import java.util.concurrent.TimeUnit;

public abstract class Train {
    private static final int TEMPS_DELAI = 2;
    private static final double CHANCE_DELAI = 0.50d;

    public abstract String getIdentifiantComplet();

    public abstract void setId(long id);

    protected void delai() {
        double probabilite = Math.random();
        while (probabilite < CHANCE_DELAI) {
            try {
                traceDelai(TEMPS_DELAI);
                TimeUnit.SECONDS.sleep(TEMPS_DELAI);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            probabilite = Math.random();
        }
    }

    private void traceDelai(int tempsDelai) {
        System.out.printf("\n%s en delai de %s secondes", getIdentifiantComplet(), tempsDelai);
    }
}
