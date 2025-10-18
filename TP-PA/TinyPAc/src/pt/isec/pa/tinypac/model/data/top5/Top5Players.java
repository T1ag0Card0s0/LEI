package pt.isec.pa.tinypac.model.data.top5;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Top5Players (top5 melhores jogadores)
 * <p>
 *     Aqui é guardada uma lista de pontuações com 5 elementos com o nome e a respetiva pontuação de cada jogador
 * </p>
 * @author Tiago Cardoso 2021138999
 * @version guiVersion
 */
public class Top5Players implements Serializable {
    private final List<ScoreEntry> entries;

    /**
     * Construtor do Top5Players
     */
    public Top5Players() {
        entries = new ArrayList<>();
    }

    /**
     * Adiciona ou atualiza o nome do jogador e a sua pontuação
     * @param name nome do jogador
     * @param score pontuação
     */
    public void addPlayer(String name, int score) {
        for (ScoreEntry entry : entries) {
            if (entry.getName().equals(name)) {
                if (score > entry.getScore()) {
                    entry.setScore(score);
                    sortEntries();
                }
                return;
            }
        }

        ScoreEntry newEntry = new ScoreEntry(name, score);
        entries.add(newEntry);
        sortEntries();

        if (entries.size() > 5) {
            entries.remove(entries.size() - 1);
        }
    }

    /**
     * Retorna a lista de nome e pontuação dos 5 mehores jogadores
     * @return lista de string top5.
     */
    public List<String> getTop5() {
        List<String> result = new ArrayList<>();
        for (ScoreEntry entry : entries) {
            result.add(entry.toString());
        }
        return result;
    }

    /**
     * Organiza os elementos por ordem decrescente na pontuação.
     */
    private void sortEntries() {
        entries.sort(Comparator.comparingInt(ScoreEntry::getScore).reversed());
    }
}
