//package com.company;

import java.util.Scanner;
import java.util.TreeMap;
import java.io.*;
import java.util.Map;
import java.util.*;

public class Main {

    public static void locatieDorita(TreeMap<String, Locatie> lista, String numeLocatie){

        System.out.println(lista.get(numeLocatie));
    }

    /**
     *
     * @param lista treeMap-ul de locatii
     * @param loc destinatia dorita
     * @param A prima zi
     * @param B ultima zi
     *
     *          verific ca locatia sa fie disponibila in perioada dorita, sa se afle la destinatia ceruta
     *          si sa fie in top 5 cele mai ieftine
     */
    public static void top5 (TreeMap<String, Locatie> lista, Ierarhie loc, String A, String B){

        double min1=Double.MAX_VALUE, min2=Double.MAX_VALUE, min3 = Double.MAX_VALUE,
                min4=Double.MAX_VALUE, min5 =Double.MAX_VALUE;
        Locatie Ieftin1=null, Ieftin2=null, Ieftin3=null, Ieftin4=null, Ieftin5 = null;
        for(Map.Entry<String,Locatie> entry : lista.entrySet()) {
            Locatie x = entry.getValue();
            // verific ce locatie e disponibila in acea perioada si se afla in orasul dorit
            //si le retin pe 5 dintre ele, cele mai ieftine.
            if (x.perioadaAccesibila(A, B) && x.loc.equals(loc)) {
                if (x.pretMediu < min1) {
                    min5 = min4;
                    min4 = min3;
                    min3 = min2;
                    min2 = min1;
                    min1 = x.pretMediu;
                    Ieftin1 = x;
                } else {
                    if (x.pretMediu < min2) {
                        min5 = min4;
                        min4 = min3;
                        min3 = min2;
                        min2 = x.pretMediu;
                        Ieftin2 = x;
                    } else {
                        if (x.pretMediu < min3) {
                            min5 = min4;
                            min4 = min3;
                            min3 = x.pretMediu;
                            Ieftin3 = x;
                        } else {
                            if (x.pretMediu < min4) {
                                min5 = min4;
                                min4 = x.pretMediu;
                                Ieftin4 = x;
                            } else {
                                if (x.pretMediu < min5) {
                                    min5 = x.pretMediu;
                                    Ieftin5 = x;
                                }
                            }
                        }
                    }

                }
            }
        }
        if (Ieftin1 != null)
            System.out.println(Ieftin1);
        if (Ieftin2 != null)
            System.out.println(Ieftin2);
        if (Ieftin3 != null)
            System.out.println(Ieftin3);
        if (Ieftin4 != null)
            System.out.println(Ieftin4);
        if (Ieftin5 != null)
            System.out.println(Ieftin5);
    }

    /**
     * verific ce locatie este disponibila cel putin 10 zile si o aleg pe cea mai ieftina

     aceasta abordare merge in cazul in care pot alege orice activitate
     daca vreau o anume activitate, se va adauga conditia ca locatia respectiva sa contina activitatea dorita
     metoda pentru acea verificare se gaseste in clasa Locatie
     */
    public static void activitate10zile (TreeMap<String, Locatie> lista /* String activitate dorita*/){
        double min = Double.MAX_VALUE;
        Locatie p = null;
        for(Map.Entry<String,Locatie> entry : lista.entrySet()) {
            Locatie x = entry.getValue();
            if (x.perioadaInZile() >= 10  /*&& x.contineActivitate(activitate_dorita)*/) {
                if (x.pretMediu < min) {
                    min = x.pretMediu;
                    p = x;
                }
            }
        }
        System.out.println(p);

    }

    public static void main(String[] args) {
	// citesc datele dintr-un fisier
        File f = new File("fisier.in");
        try {
            Scanner sDate = new Scanner(f);

            // le adaug intr-un treemap in care cheia este numele locatiei,
            // iar valoarea este informatia despre acea locatie
            // aceasta abordare ma ajuta sa afisez eficient informatiile despre locatia x

            TreeMap<String, Locatie> lista = new TreeMap<>();
            while(sDate.hasNext()) {
                // presupun ca fiecare informatie se afla pe cate o linie a fisierului
                //(numele pe prima linie, tara pe a doua, judetul pe a treia, etc.)
                String nume = sDate.nextLine();
                Ierarhie loc = new Ierarhie(sDate.nextLine(), sDate.nextLine(), sDate.nextLine());
                double pret = Double.parseDouble(sDate.nextLine());
                // presupun ca stiu cate activitati pot practica
                int n = Integer.parseInt(sDate.nextLine());
                Set set = new LinkedHashSet();
                for (int i = 1; i <= n; i++) {
                    set.add(sDate.nextLine());
                }
                String firstDay = sDate.nextLine();
                String lastDay = sDate.nextLine();
                Locatie a = new Locatie (loc, pret, set, firstDay, lastDay);
                lista.put(nume, a);
            }
             System.out.println(lista);

            Scanner sCerinte = new Scanner(System.in);


            // locatia x
            String numeLocatie = sCerinte.nextLine();
            locatieDorita(lista, numeLocatie);


            // top 5

            Ierarhie loc = new Ierarhie(sCerinte.nextLine(), sCerinte.nextLine(), sCerinte.nextLine());
            String A = sCerinte.nextLine();
            String B = sCerinte.nextLine();
            top5(lista, loc, A, B);


            // locatia un e cel mai ieftin 10 zile


            activitate10zile(lista);

        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
    }
}
