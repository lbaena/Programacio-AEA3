package lbaena.Classes;

import lbaena.Fitxers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Persona {
    private String nom;
    private String cognom;
    private int edat;
    private double sou;

    static String nomFitxer = "./persones.csv";

    static Fitxers fitxers = new Fitxers(nomFitxer);

    public Persona(String nom, String cognom, int edat, double sou) {
        this.nom = nom;
        this.cognom = cognom;
        this.edat = edat;
        this.sou = sou;
    }
    public Persona() {}

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCognom() {
        return cognom;
    }

    public void setCognom(String cognom) {
        this.cognom = cognom;
    }

    public int getEdat() {
        return edat;
    }

    public void setEdat(int edat) {
        this.edat = edat;
    }

    public double getSou() {
        return sou;
    }

    public void setSou(double sou) {
        this.sou = sou;
    }

    @Override
    public String toString() {
        return nom + ";" + cognom + ";" + edat + ";" + sou;
    }

    public void afegeixPersona() {
        try {
           List<Persona> persones = getPersonesFromFile();
           persones.add(this);
           String fitxerContingut = "";
           for (Persona persona : persones) {
               fitxerContingut = fitxerContingut + persona.toString();
           }
            fitxers.escriuFitxerText(fitxerContingut + System.lineSeparator(), true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void eliminaPersona() {
        try {
            List<Persona> persones = getPersonesFromFile();
            persones.removeIf(p -> p.getNom().equals(this.nom) && p.getCognom().equals(this.cognom));


            fitxers.escriuFitxerText("", false); // truncar
            for (Persona p : persones) {
                fitxers.escriuFitxerText(p.toString() + System.lineSeparator(), true);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public List<Persona> getPersonesFromFile() {
        List<String> contingut = null;
        List<Persona> persones = new ArrayList<>();

        try {
            contingut = fitxers.retornaContingutFitxerLlista();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        for (String linia : contingut) {
            String[] dades = linia.split(";");
            if (dades.length == 4) {
                String nom = dades[0];
                String cognom = dades[1];
                int edat = Integer.parseInt(dades[2]);
                double sou = Double.parseDouble(dades[3]);
                Persona persona = new Persona(nom, cognom, edat, sou);
                persones.add(persona);
            }
        }

        return persones;
    }


}
