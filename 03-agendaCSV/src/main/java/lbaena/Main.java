package lbaena;

import lbaena.Classes.AccionsPersona;
import lbaena.Classes.Persona;
import vicent.Bellver.MissatgesIAlertes.MissatgesJavaSwing;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    private static MissatgesJavaSwing missatgesJavaSwing = new MissatgesJavaSwing();


    public static void main(String[] args) {
        boolean sortir = false;
        AccionsPersona act;
        act = AccionsPersona.MENU;

        do {
            switch (act){
                case MENU:
                    String[] opcions = {"Afegir Persona", "Modificar Persona", "Eliminar Persona", "Veure Persones", "Sortir"};
                    AccionsPersona seleccio = AccionsPersona.fromString(missatgesJavaSwing.menu(opcions,"Gestió de Persones", "Selecciona una opció:"));
                    act = seleccio != null ? seleccio : AccionsPersona.MENU;
                    break;
                case AFEGIR:
                    Persona crea_persona = creaPersona();
                    crea_persona.afegeixPersona();
                    missatgesJavaSwing.missatgeInfo("Persona afegida:\n" + crea_persona.toString());
                    act = AccionsPersona.MENU;
                    break;
                case MODIFICAR:


                    act = AccionsPersona.MENU;
                    break;
                case ELIMINAR:
                    Persona elimina_persona = modelaEsborraPersona();
                    missatgesJavaSwing.missatgeInfo("Estàs segur que vols eliminar la persona:\n" + elimina_persona.toString());
                    elimina_persona.eliminaPersona();
                    act = AccionsPersona.MENU;
                    break;
                case VEURE:

                    act = AccionsPersona.MENU;
                    break;
                default:
                    sortir = true;
                    break;


            }
        } while (!sortir);

    }

    private static Persona creaPersona(){
        String[] opcionsAfegir = {"Nom de la persona", "Tornar al Menú"};
        Persona persona = new Persona();
        String nom = missatgesJavaSwing.introdueixDades("Introdueix el nom de la persona:");
        persona.setNom(nom);
        String cognom = missatgesJavaSwing.introdueixDades("Introdueix el cognom de la persona:");
        persona.setCognom(cognom);
        int edat = Integer.parseInt(missatgesJavaSwing.introdueixDades("Introdueix l'edat de la persona:"));
        persona.setEdat(edat);
        double sou = Double.parseDouble(missatgesJavaSwing.introdueixDades("Introdueix el sou de la persona:"));
        persona.setSou(sou);
        return persona;
    }

    private static Persona modelaEsborraPersona(){
        Persona persona = new Persona();
        String nom = missatgesJavaSwing.introdueixDades("Introdueix el nom de la persona:");
        persona.setNom(nom);
        String cognom = missatgesJavaSwing.introdueixDades("Introdueix el cognom de la persona:");
        persona.setCognom(cognom);
        return persona;
    }

}