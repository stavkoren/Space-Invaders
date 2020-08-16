package scores;

import java.io.Serializable;

/**
 * ScoreInfo- holds name and score information.
 */
public class ScoreInfo implements Serializable {
    private String name;
    private int score;

    /**
     * ScoreInfo - constructor .
     *
     * @param name  of player.
     * @param score of player .
     */
    public ScoreInfo(String name, int score) {
        this.name = name;
        this.score = score;
    }

    /**
     * getName - accessor .
     *
     * @return name .
     */
    public String getName() {
        return name;
    }

    /**
     * getScore - accessor .
     * @return score.
     */
    public int getScore() {
        return score;
    }
    @Override
    public String toString() {
        return "Name:" + name + " Score: " + score;
    }
}
