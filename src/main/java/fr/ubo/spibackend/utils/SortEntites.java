package fr.ubo.spibackend.utils;

import fr.ubo.spibackend.entities.Candidat;

import java.math.BigInteger;

public class SortEntites {

    public static int compareCandidats(Candidat a, Candidat b){

        String x1 = a.getListeSelection();
        String x2 = a.getListeSelection();
        int sComp = x1.compareTo(x2);

        if (sComp != 0) {
            return sComp;
        }

        BigInteger n1 = a.getSelectionNoOrdre()==null? BigInteger.valueOf(0) :a.getSelectionNoOrdre();
        BigInteger n2 = b.getSelectionNoOrdre()==null? BigInteger.valueOf(0) :b.getSelectionNoOrdre();
        sComp = n1.compareTo(n2);
        if (sComp != 0) {
            return sComp;
        }
        String ida = a.getNoCandidat();
        String idb = b.getNoCandidat();

        return ida.compareTo(idb);


    }
}
