package psoft.backend.projeto.comparators;

import psoft.backend.projeto.entidades.Campanha;

import java.util.Comparator;

public class ComparadorLikeCampanha implements Comparator<Campanha> {

    @Override
    public int compare(Campanha campanha, Campanha t1) {
        if (campanha.getCurtidas().size() < t1.getCurtidas().size()) {
            return 1;
        } else if (campanha.getCurtidas().size() > t1.getCurtidas().size()) {
            return -1;
        } else {
            return 0;
        }
    }
}
