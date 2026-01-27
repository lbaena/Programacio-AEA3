package lbaena.Classes;

public enum AccionsPersona {
    MENU("Menú Principal"),
    AFEGIR("Afegir Persona"),
    MODIFICAR("Modificar Persona"),
    ELIMINAR("Eliminar Persona"),
    VEURE("Veure Persones");

    private String text;
    AccionsPersona(String text) {
        this.text = text;
    }

    public static AccionsPersona fromString(String text) {
        for (AccionsPersona accio : AccionsPersona.values()) {
            if (accio.text.equalsIgnoreCase(text)) {
                return accio;
            }
        }
        return null;
    }

}
