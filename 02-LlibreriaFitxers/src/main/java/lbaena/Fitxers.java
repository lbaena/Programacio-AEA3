package lbaena;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.*;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.io.File;
import java.util.List;


public class Fitxers {

    // vars
    public String ruta;

    // constructors
    public Fitxers(String ruta) {
        this.ruta = ruta;
    }

    // getters i setters
    public String getRuta() {
        return ruta;
    }

    // amb IO

    /**
     * Comprova si un fitxer o directori existeix utilitzant la classe File.
     *
     * @return true si el fitxer o directori existeix, false en cas contrari.
     */
    public boolean existeixIO() {
        boolean exists = false;
        try {
            File file = new File(ruta);
            exists = file.exists();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return exists;
    }
    /**
     * Crea un directori utilitzant la classe File.
     */

    public void creaDirectoriIO() {
        try {
            File dir = new File(ruta);
            if (!dir.exists()) {
                dir.mkdirs();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Elimina un fitxer o directori utilitzant la classe File.
     */
    public void eliminaFitxerDirectoriIO() {
        try {
            File file = new File(ruta);
            if (file.exists()) {
                file.delete();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Copia un fitxer o directori d'una ubicació a una altra utilitzant la classe File.
     *
     * @param fitxerOrigen La ruta del fitxer o directori d'origen.
     * @param fitxerDesti  La ruta del fitxer o directori de destí.
     */
    public void moureFitxerDirectoriIO(String fitxerOrigen, String fitxerDesti) {
        try {
            File origen = new File(fitxerOrigen);
            File desti = new File(fitxerDesti);
            if (origen.exists()) {
                origen.renameTo(desti);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Retorna les propietats d'un fitxer o directori utilitzant la classe File.
     *
     * @param fitxer La ruta del fitxer o directori.
     * @return Una cadena amb les propietats separades per CSV.
     */

    public String propietatsFitxerIO(String fitxer) {
        String propietats = "";
        try {
            File file = new File(fitxer);
            if (file.exists()) {
                propietats += file.canRead() ? "true;" : "false;";
                propietats += file.canWrite() ? "true;" : "false;";
                propietats += file.canExecute() ? "true;" : "false;";
                propietats += file.isDirectory() ? "true;" : "false;";
                propietats += file.isFile() ? "true;" : "false;";
                propietats += file.isHidden() ? "true" : "false";

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return propietats;
    }



    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public boolean existeix(){
        Path path = Paths.get(ruta);
        return Files.exists(path);
    }

    public void creaDirectori() throws IOException {
        Path path = Paths.get(ruta);
        Files.createDirectories(path);
    }

    public String propietariFitxer() throws IOException {
        Path path = Paths.get(ruta);
        return Files.getOwner(path).getName();
    }

    public void eliminarFitxerDirectori() throws IOException {
        Path path = Paths.get(ruta);
        Files.deleteIfExists(path);
    }

    public void copiarFitxerDirectori(String origen, String desti) throws IOException {
        Path pathOrigen = Paths.get(origen);
        Path pathDesti = Paths.get(desti);
        Files.copy(pathOrigen, pathDesti);
    }

    public void moureFitxerDirectori(String origen, String desti) throws IOException {
        Path pathOrigen = Paths.get(origen);
        Path pathDesti = Paths.get(desti);
        Files.move(pathOrigen, pathDesti);
    }

    public String metadadesFitxer() throws IOException {
        Path path = Paths.get(ruta);
        String csv;
        String headers = "tamany;data_creacio;data_modificacio;permisos;";

        BasicFileAttributes attrs = Files.readAttributes(path, BasicFileAttributes.class);

        csv = "\n" +  Files.size(path) + ";" +
              attrs.creationTime() + ";" +
              attrs.lastModifiedTime() + ";" +
              Files.getPosixFilePermissions(path).toString();

        return headers+csv;
    }

    public void escriuFitxerText(String text, boolean afegir) throws IOException {
        Path path = Paths.get(ruta);
        Files.writeString(path, text);
    }

    public List<String> retornaContingutFitxerLlista(String charset) throws IOException, InterruptedException {
        Path path = Paths.get(ruta);
        return Files.readAllLines(path, java.nio.charset.Charset.forName(charset));
    }

    public List<String> retornaContingutFitxerLlista() throws IOException, InterruptedException {
        Path path = Paths.get(ruta);
        return Files.readAllLines(path);
    }

    public void mostraContingutFitxer() throws IOException, InterruptedException {
        Path path = Paths.get(ruta);
        System.out.println(Files.readString(path));
    }
}