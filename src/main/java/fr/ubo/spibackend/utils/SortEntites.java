package fr.ubo.spibackend.utils;

import fr.ubo.spibackend.entities.Candidat;

import java.math.BigInteger;

public class SortEntites {

    public static int compareCandidats(Candidat a, Candidat b){

        Integer x1 = selectionListComparator(a.getListeSelection()==null? "" :a.getListeSelection());
        Integer x2 = selectionListComparator(b.getListeSelection()==null? "" :b.getListeSelection());
        int sComp = x1.compareTo(x2);

        if (sComp != 0) {
            System.out.println("scomp liste "+x1+x2+"=="+sComp);
            return sComp;
        }

        Integer n1 = a.getSelectionNoOrdre()==null? Integer.valueOf(0) :a.getSelectionNoOrdre();
        Integer n2 = b.getSelectionNoOrdre()==null? Integer.valueOf(0) :b.getSelectionNoOrdre();
        sComp = n1.compareTo(n2);
        if (sComp != 0) {
            System.out.println("scomp SelectionOrdre "+n1+n2+"=="+sComp);
            return sComp;
        }
        String ida = a.getNoCandidat();
        String idb = b.getNoCandidat();

        return ida.compareTo(idb);


    }

    private static int selectionListComparator(String ls){
        switch (ls){
            case "LP":
                return 1;
            case "LA":
                return 2;
            case "NR":
                return 3;
            default:
                return 1000;
        }
    }
}
