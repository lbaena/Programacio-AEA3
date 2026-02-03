import lbaena.Fitxers;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

class FitxersTest {

    @TempDir
    Path tempDir;

    @Test
    void testExisteix() throws IOException {
        Path fitxer = tempDir.resolve("test.txt");
        Files.createFile(fitxer);
        Fitxers f = new Fitxers(fitxer.toString());
        assertTrue(f.existeix());
    }

    @Test
    void testRetornaError() throws IOException {
        Path fitxer = tempDir.resolve("test.txt");
        Files.createFile(fitxer);
        Fitxers f = new Fitxers(fitxer.toString());
        assertThrowsExactly(IOException.class,()->{
            f.propietariFitxer();
        });
    }


    @Test
    void testCreaDirectori() throws IOException {
        Path nouDir = tempDir.resolve("meuDirectori");
        Fitxers f = new Fitxers(nouDir.toString());

        f.creaDirectori();
        assertTrue(Files.exists(nouDir));
        assertTrue(Files.isDirectory(nouDir));
    }
    @Test
    void testEliminarFitxerDirectori() throws IOException {
        Path fitxer = tempDir.resolve("eliminar.txt");
        Files.createFile(fitxer);

        Fitxers f = new Fitxers(fitxer.toString());
        f.eliminarFitxerDirectori();
        assertFalse(Files.exists(fitxer));
    }

    @Test
    void testCopiarFitxerDirectori() throws IOException {
        Path origen = tempDir.resolve("origen.txt");
        Path desti = tempDir.resolve("desti.txt");
        Files.writeString(origen, "Hola món");

        Fitxers f = new Fitxers("");
        f.copiarFitxerDirectori(origen.toString(), desti.toString());

        assertTrue(Files.exists(desti));
        assertEquals("Hola món", Files.readString(desti));
    }

    @Test
    void testEscriuFitxerText() throws IOException {
        Path fitxer = tempDir.resolve("escriure.txt");
        Fitxers f = new Fitxers(fitxer.toString());
        String contingut = "Text de prova";
        f.escriuFitxerText(contingut, false);
        assertEquals(contingut, Files.readString(fitxer));
    }

    @Test
    void testRetornaContingutFitxerLlista() throws IOException, InterruptedException {
        Path fitxer = tempDir.resolve("llista.txt");
        Files.writeString(fitxer, "Linia 1\nLinia 2");
        Fitxers f = new Fitxers(fitxer.toString());
        List<String> linies = f.retornaContingutFitxerLlista();
        assertEquals(2, linies.size());
        assertEquals("Linia 1", linies.get(0));
        assertEquals("Linia 2", linies.get(1));
    }


    @Test
    void testMetadadesFitxer() throws IOException {
        Path fitxer = tempDir.resolve("meta.txt");
        Files.createFile(fitxer);
        Fitxers f = new Fitxers(fitxer.toString());
        String metadades = f.metadadesFitxer();
        assertNotNull(metadades);
        String[] parts = metadades.split(";");
        assertEquals(4, parts.length);
    }
}