package pt.isec.pa.tinypac.model.data.top5;

import java.io.Serializable;

/**
 * ScoreEntry (Elemento da tabela de pontuação)
 * <p>
 *     Aqui é guardado o nome e a pontuação de um jogador
 * </p>
 */
public class ScoreEntry implements Serializable {
    private final String name;
    private int score;

    /**
     * Construtor do elemento da tabela
     * @param name nome do utilizador
     * @param score pontuação obtida
     */
    public ScoreEntry(String name, int score) {
        this.name = name;
        this.score = score;
    }

    /**
     * Atribui um valor à pontuação
     * @param score pontuação obtida
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Retorna o nome do utilizador
     * @return String name
     */
    public String getName() {
        return name;
    }

    /**
     * Retrona a pontuação obtida.
     * @return score
     */
    public int getScore() {
        return score;
    }

    /**
     * Retorna uma string no formato nome : pontuação
     * @return String name + ":"+score
     */
    @Override
    public String toString() {
        return name + " : " + score;
    }
}