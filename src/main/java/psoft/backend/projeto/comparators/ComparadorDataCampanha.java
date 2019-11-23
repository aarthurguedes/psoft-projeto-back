package psoft.backend.projeto.comparators;

import psoft.backend.projeto.entidades.Campanha;

import java.util.Comparator;

public class ComparadorDataCampanha implements Comparator<Campanha> {

    @Override
    public int compare(Campanha campanha, Campanha t1) {
        if (campanha.getDeadline().after(t1.getDeadline())) {
            return 1;
        } else if (campanha.getDeadline().before(t1.getDeadline())) {
            return -1;
        } else {
            return 0;
        }
    }
}
