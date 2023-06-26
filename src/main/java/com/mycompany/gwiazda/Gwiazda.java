/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.gwiazda;

/**
 *
 * @author natan
 */
import java.util.*;

public class Gwiazda {
    //region deklaracja zmiennych
    private String nazwa;
    private String nazwaKatalogowa;
    private int deklinacja;
    private int rektascensja;
    private double OWG;//Obserwowana wielkosc gwiazdowa
    private double AWG; //absolutna wielkosc gwiazdowa
    private double LataSwietlne;
    private String polkulaPN_PD;
    private double temperatura;
    private double masa;
    private String Gwiazdozbior;
    private static final String[] greckie = {
            "alfa", "beta", "gamma", "delta", "epsilon", "dzeta", "eta", "teta",
            "jota", "kappa", "lambda", "mi", "ni", "ksi", "omikron", "pi", "ro",
            "sigma", "tau", "ypsilon", "fi", "chi", "psi", "omega"
    };

    private static final String[] Gwiazdozbiory = {
            "fajny","fajniejszy","najlepszy","dupiaty"
    };

    //pomoc
    private static int ileGwiazd;

    //endregion

    //region konstruktos plus sety
    public Gwiazda(String nazwa,int deklinacja,int rektascencja,double OWG,double LataSwietlne,double temperatura,double masa,String Gwiazdozbior) {
        //nazwa
        if (nazwa.matches("[A-Z]{3}[0-9]{4}")) {
            this.nazwa = nazwa;
        } else {
            throw new IllegalArgumentException("Nazwa nie spełnia wymagań: 3 duże litery i 4 cyfry");
        }

        //deklinacja
        if (deklinacja > 0 && deklinacja <= 90) {
            this.deklinacja = deklinacja;
        } else if (deklinacja >= -90 && deklinacja < 0) {
            this.deklinacja = deklinacja;
        } else {
            throw new IllegalArgumentException("deklinacja pomiedzy 0 a 90 lub -90 a 0");
        }

        //rektascencja
        if(rektascencja>=0&&rektascencja<=24)
        {
            this.rektascensja=rektascencja;
        }
        else{
            throw new IllegalArgumentException("rektascencja pomiedzy 0 a 24");
        }

        //Obserwowana Wielkosc Gwiazdowa
        if(OWG>=-26.7&&OWG<=15)
        {
            this.OWG=OWG;
        }
        else{
            throw new IllegalArgumentException("OWG pomiedzy -26,7 a 15");
        }
        //absolutna Wielkosc Gwiazdowa


        //lata swietlne
        this.LataSwietlne=LataSwietlne;

        //temperatura
        if(temperatura>2000){
            this.temperatura=temperatura;
        }
        else{
            throw new IllegalArgumentException("Temperatura musi byc wieksza");
        }
        //masa (od masy slonca)
        if(masa>0.1&&masa<=50){
            this.masa=masa;
        }
        else{
            throw new IllegalArgumentException("masa ma byc pomiedzy 0,1 a 50 masy slonca");
        }

        ileGwiazd++; //liczy ile jest gwiazd
    } //konstruktor


    public String polkulaset(){
        if(deklinacja<0){
            polkulaPN_PD="pd";
        return "pd";}
        else
        {
        polkulaPN_PD="pn";
        return "pn";
        }
    }                                                           //ustawienie polkuli based on deklinacja
    public double OWGset(){
        if(OWG>=-26.7&&OWG<=15)
        {
            this.OWG=OWG;
            return OWG;
        }
        else{
            throw new IllegalArgumentException("OWG pomiedzy -26,7 a 15");
        }
    }//OWG

    public double AWGset(){
        if(OWG>=-26.7&&OWG<=15) {
            this.AWG = OWG - 5 * Math.log10(LataSwietlne * 0.3066) + 5;
            return AWG;
        }
        else{
            throw new IllegalArgumentException("cos poszlo nie tak");
        }
    }//AWG
//endregion

    //region nadanie nazwy katalogowej
    public static void sortuj(ArrayList<Gwiazda> arr) {
        Collections.sort(arr, (o1, o2) -> Double.compare(o2.OWGset(), o1.OWGset()));
nadajNazwe(arr);

    }                               //sortuje wszystkie gwiazdy malejaco based on OWG
    public static void nadajNazwe(ArrayList<Gwiazda> arr)
    {



            for (int i = 0; i < arr.size(); i++) {
                arr.get(i).nazwaKatalogowa =arr.get(i).Gwiazdozbior+arr.get(i).greckie[i];
            }

    }

       //endregion
    public static void wypisz(ArrayList<Gwiazda> x) {
        for (int i = 0; i < x.size(); i++) {
            System.out.println("Gwiazda z gwiazdozbioru " + (i + 1) + ":");
            System.out.println("  nazwa: " + x.get(i).nazwa);
            System.out.println("  polkula: " + x.get(i).polkulaset());
            System.out.println("  nazwa katalogu:: " + x.get(i).nazwaKatalogowa);
            System.out.println("  deklinacja:: " + x.get(i).deklinacja);
            System.out.println("  rektascencja: " + x.get(i).rektascensja);
            System.out.println("  OWG: " + x.get(i).OWG);
            System.out.println("  aWG: " + x.get(i).AWGset());
            System.out.println("  Lata swietlne: " + x.get(i).LataSwietlne);
            System.out.println("  temperatura: " + x.get(i).temperatura);
            System.out.println("  masa: " + x.get(i).masa);

        }

    }                                     //wypisanie gwiazdy
    public static void wypiszSimple(ArrayList<Gwiazda> x) {
        for (int i = 0; i < x.size(); i++) {
            System.out.println("Gwiazda z gwiazdozbioru " + (i + 1) + ":");
            System.out.println("  nazwa: " + x.get(i).nazwa);
            System.out.println("  nazwa katalogu:: " + x.get(i).nazwaKatalogowa);

        }

    }                               //wypisanie nazwy i katalogu
    public static void usunGwiazde(ArrayList<Gwiazda> arr, String nazwa) {
        for (int i = 0; i < arr.size(); i++) {
            if (arr.get(i).nazwa == nazwa) {
                arr.remove(i);
                wypiszSimple(arr);
                break;
            }
        }
        sortuj(arr);
        nadajNazwe(arr);
    }                //usuniecie gwiazdy

    //region funkcje wyszukujace

    public static void GwiazdyJakDaleko(ArrayList<Gwiazda> arr,int x){
        System.out.println("Gwiazdy ktore sa "+x+" lat swietlnych od nas(w parsekach): ");
        //1pc=3,2616    0,3066=1rok
        double ilepc=x*0.3066;
        System.out.println("jest to "+ilepc+" parsekow ");
        for (int i = 0; i < arr.size(); i++) {
            if(ilepc==x){
                System.out.println("  nazwa: " + arr.get(i).nazwa+" katalog: "+arr.get(i).nazwaKatalogowa);
            }
        }
    }                    //wyszukaj gwiazdy znajdujące się w odległości x parseków od Ziemii
    public static void TempWPrzedziale(ArrayList<Gwiazda> arr,int x,int y) {
        System.out.println("Gwiazdy ktore maja temperature pomiedzy"+x+" a "+y);
        for (int i = 0; i < arr.size(); i++) {
            if(arr.get(i).temperatura>=x&&arr.get(i).temperatura<=y)
            {
                System.out.println("  nazwa: " + arr.get(i).nazwa+" katalog: "+arr.get(i).nazwaKatalogowa);
            }
        }


    }              //wyszukaj gwiazdy o temperaturze w zadanym przedziale;

    public static void WielkoscGwiazdowa(ArrayList<Gwiazda> arr,double x,double y){
        System.out.println("Gwiazdy o wielkosci gwiazdowej wprzedziale od "+x+" do "+y);
        for (int i = 0; i < arr.size(); i++) {
            if(arr.get(i).masa>=x&&arr.get(i).masa<=y)
            {
                System.out.println("  nazwa: " + arr.get(i).nazwa+" katalog: "+arr.get(i).nazwaKatalogowa);
            }
        }
    }       //wyszukaj gwiazdy o wielkości gwiazdowej w zadanym przedziale;
    public static void ZPolkuli(ArrayList<Gwiazda> arr,String jaka) {
        if (jaka == "pn") {
            for (int i = 0; i < arr.size(); i++) {
                if(arr.get(i).polkulaset()=="pn"){

                System.out.println("  nazwa: " + arr.get(i).nazwa+" katalog: "+arr.get(i).nazwaKatalogowa+ "deklinacja:"+ arr.get(i).deklinacja+ "polkula:"+ arr.get(i).polkulaset());
            }}

        }

        else if(jaka == "pd") {
            for (int i = 0; i < arr.size(); i++) {
            if(arr.get(i).polkulaset()=="pd"){

                System.out.println("  nazwa: " + arr.get(i).nazwa+" katalog: "+arr.get(i).nazwaKatalogowa+ "deklinacja:"+ arr.get(i).deklinacja+ "polkula:"+ arr.get(i).polkulaset());
            }}}

        else{
                throw new IllegalArgumentException("podaj dobrego stringa");
            }
    }                     //wyszukaj gwiazdy z półkuli północnej / południowej

     public static void PotencjalnaSupernova(ArrayList<Gwiazda> arr){
         for (int i = 0; i < arr.size(); i++) {
             if(arr.get(i).masa>=1.44)
             {
                 System.out.println("  nazwa: " + arr.get(i).nazwa+" katalog: "+arr.get(i).nazwaKatalogowa+" masa potencjalnej: "+arr.get(i).masa);
             }
         }
     }                     //wyszukaj potencjalne supernowe. 1.44 masy Słońca.

    //endregion

    //region main
    public static void main(String[] args)
    {
        String[] Gwiazdozbiory={
                "fajny","fajniejszy","najlepszy","dupiaty"
        };
        ArrayList<Gwiazda> x = new ArrayList<>();
        x.add(new Gwiazda("AAA1234",10,19,10,30,40000,20,Gwiazdozbiory[1]));
        x.add(new Gwiazda("AAA4321",-10,20,13,30,40120,21,Gwiazdozbiory[2]));
        x.add(new Gwiazda("AAA4444",20,24,14,21,40200, 15,Gwiazdozbiory[1]));
        x.add(new Gwiazda("POT1111",-40,22,11,21,40200, 1,Gwiazdozbiory[1]));


        sortuj(x);
        nadajNazwe(x);
        wypisz(x);
        //usunGwiazde(x,"AAA4444");              //ma w sobie wypisz


                                          //funkcje wyszukujace
        //GwiazdyJakDaleko(x,10)
        //TempWPrzedziale(x,40120,40200);
        //WielkoscGwiazdowa(x,15,20);
        //ZPolkuli(x,"pn");         //jaka="pn" lub "pd"
        //PotencjalnaSupernova(x);

    }
    //endregion main
}