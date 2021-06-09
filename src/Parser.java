import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Parser {
    public static Catalogo parse(String nomeFicheiro) throws LinhaIncorretaException {
        List<String> linhas = lerFicheiro(nomeFicheiro);
        if(linhas.size() == 0) return null;
        Catalogo catalogo = new Catalogo();
        String ultima = null; Jogador j = null;
        String[] linhaPartida;
        for (String linha : linhas) {
            linhaPartida = linha.split(":", 2);
            switch (linhaPartida[0]) {
                case "Equipa" -> {
                    Equipa e = Equipa.parse(linhaPartida[1]);
                    catalogo.addEquipa(e);
                    ultima = e.getName();
                }
                case "Guarda-Redes" -> {
                    j = GuardaRedes.parse(linhaPartida[1]);
                    catalogo.addJogador(j,ultima);
                }
                case "Defesa" -> {
                    j = Defesa.parse(linhaPartida[1]);
                    catalogo.addJogador(j,ultima);
                }
                case "Medio" -> {
                    j = Medio.parse(linhaPartida[1]);
                    catalogo.addJogador(j,ultima);
                }
                case "Lateral" -> {
                    j = Lateral.parse(linhaPartida[1]);
                    catalogo.addJogador(j,ultima);
                }
                case "Avancado" -> {
                    j = Avancado.parse(linhaPartida[1]);
                    catalogo.addJogador(j,ultima);
                }
                case "Jogo" -> {
                    InfoJogo ij = InfoJogo.parse(linhaPartida[1]);
                    catalogo.addInfoJogo(ij);
                }
                default -> throw new LinhaIncorretaException();
            }
        }
        return catalogo;
    }

    public static List<String> lerFicheiro(String nomeFich) {
        List<String> lines;
        try { lines = Files.readAllLines(Paths.get(nomeFich), StandardCharsets.UTF_8); }
        catch(IOException exc) { lines = new ArrayList<>(); }
        return lines;
    }
}
