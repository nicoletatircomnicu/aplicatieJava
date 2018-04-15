//package com.company;

import java.util.*;
import java.text.*;
import java.util.concurrent.*;

public class Locatie {
    // String nume;
    Ierarhie loc;
    double pretMediu;
    Set<String> activitati;
    String t1, t2;

    public Locatie (Ierarhie i, double pret, Set l, String t1, String t2) {
        // this.nume = nume;
        this.loc = i;
        this.pretMediu = pret;
        this.activitati = l;
        this.t1 = t1;
        this.t2 = t2;
    }

    @Override
    public String toString() {
        return "Locatie {" +
                "loc= " + loc +
                ", pretMediu= " + pretMediu +
                ", activitati= " + activitati +
                ", firstDay= '" + t1 + '\'' +
                ", lastDay= '" + t2 + '\'' +
                '}' + '\n';
    }

    /**
     * verific daca pot practica activitatea x
     * @param x activitatea dorita
     * @return true/false
     */
    public boolean contineActivitate(String x){
        Iterator<String> i = this.activitati.iterator();
        while(i.hasNext()){
            if (i.next().compareTo(x) == 0) return true;
        }
        return false;
    }

    /**
     *
     * vreau sa stiu daca locatia este disponibila 10 zile
     * @return numarul de zile in care este disponibila locatia
     */
    public long perioadaInZile(){
        SimpleDateFormat myFormat = new SimpleDateFormat("yyyy/MM/dd");
        try {
            Date date1 = myFormat.parse(this.t1);
            Date date2 = myFormat.parse(this.t2);
            long diff = date2.getTime() - date1.getTime();
            return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
        } catch (ParseException e) {
            return -1;
        }
    }

    /**
     * verific daca locatia este disponibila intr-o anume perioada
     * @param A prima zi
     * @param B ultima zi
     * @return true/false
     */
    public boolean perioadaAccesibila (String A, String B){
        if ((A.compareTo(t1) >= 0) && (B.compareTo(t2) <= 0))
            return true;
        return false;
    }
}
