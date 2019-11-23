package psoft.backend.projeto.comparators;

import psoft.backend.projeto.entidades.Campanha;

import java.util.Comparator;

public class ComparadorDiferencaParaMeta implements Comparator<Campanha> {

    @Override
    public int compare(Campanha campanha, Campanha t1) {
        if (!campanha.atingiuMeta() && !t1.atingiuMeta() &&
                campanha.diferencaParaMeta() < t1.diferencaParaMeta()) {
            return -1;
        } else if (!campanha.atingiuMeta() && !t1.atingiuMeta() &&
                campanha.diferencaParaMeta() > t1.diferencaParaMeta()) {
            return 1;
        } else {
            return 0;
        }
    }
}
