package scores;


import java.io.File;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.BufferedOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.EOFException;

import java.util.ArrayList;
import java.util.List;

/**
 * HighScoresTable - manages a table of size high-scores.
 */
public class HighScoresTable {
    private ArrayList<ScoreInfo> scores;
    private int size;
    /**
     * HighScoresTable- Create an empty high-scores table with the specified size.
     * @param size the table holds up to size top scores.
     */
    public HighScoresTable(int size) {
        this.size = size;
        //initialize table
        scores = new ArrayList<ScoreInfo>();
    }

    /**
     *  Add a high-score.
     * @param score .
     */
    public void add(ScoreInfo score) {
        //get its rank
        int rank = getRank(score.getScore());
        if (rank <= size) {
            scores.add(rank - 1, score);
        }
        //delete unnessery games
        if (scores.size() > this.size) {
            scores.remove(scores.size() - 1);
        }
    }

    /**
     * isNewHighScore .
     * @param score to add.
     * @return true if the score should getting
     * into thr table of false otherwise.
     */
    public boolean isNewHighScore(int score) {
        //get its rank
        int rank = getRank(score);
        if (rank <= size) {
            //new high score
              return true;
        }
        return false;
    }

    /**
     * size -accessor .
     * @return table size.
     */
    public int size() {
        return size;
    }

    /**
     * getHighScores  accessor.
     * @return high scores table.
     */
    public List<ScoreInfo> getHighScores() {
        return scores;
    }

    /**
     * getRank- return the rank of the current score: where will it
     //be on the list if added .
     * @param score new score to add.
     * @return score's rank.
     */
    public int getRank(int score) {
        //check if its the highest score
        if (scores.size() == 0 || scores.get(0).getScore() < score) {
            //a nre record
            return 1;
        }
        //search for its rank
        int rank = 0;
        while (rank < scores.size() && rank < size) {
            //the score is smaller than current score
            if (scores.get(rank).getScore() < score) {
                return  rank + 1;
            }
            rank++;
        }
        //i rank is bigger than list size but there is steel enough space return it
        if (rank + 1 > scores.size() && rank < size) {
            return rank + 1;
        }
        rank = scores.size() + 1;
        // score is too low
        return rank;
    }
    /**
     * clear- Clears the table.
     */
    public void clear() {
        scores.clear();
    }

    /**
     * Load table data from file. Current table data is cleared.
     * @param filename .
     */
    public void load(File filename)  {
        //Get table from static method
        HighScoresTable table = HighScoresTable.loadFromFile(filename);
        this.scores =  table.scores;
    }

    /**
     * Save table data to the specified file.
     * @param filename file name to write to .
     * @throws IOException .
     */
    public void save(File filename) throws IOException {
        createNewFileIfNotExist(filename);
        FileOutputStream fos = new FileOutputStream(filename);
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        ObjectOutputStream oos = new ObjectOutputStream(bos);
        for (ScoreInfo score: scores) {
            oos.writeObject(score);
        }
        oos.close();
        fos.close();
        bos.close();
    }

    /**
     *  Read a table from file and return it.
    If the file does not exist, or there is a problem with
     reading it, an empty table is returned.
     * @param filename .
     * @return new table
     */
    public static HighScoresTable loadFromFile(File filename) {
        FileInputStream file;
        ObjectInputStream input;
        ArrayList<ScoreInfo> highScores = new ArrayList<>();
        try {
             file = new FileInputStream(filename);
             input = new ObjectInputStream(file);
            Object obj = new Object();
                //read file into array list
            try {
            while (input != null) {
                 obj = input.readObject();
                 highScores.add((ScoreInfo) obj);
            }
            //end of file
            } catch (EOFException e) {
                input.close();
                file.close();
            } catch (IOException e) {
                input.close();
                file.close();
            }
        } catch (Exception e) {
            //en exception accured return empty list .
            return new HighScoresTable(0);
        }
        //create new table
        HighScoresTable table = new HighScoresTable(highScores.size());
        //add the scores
        for (ScoreInfo score: highScores) {
            table.add(score);
        }
        //returbn the table
        return table;
    }

    /**
     * createNewFileIfNotExist - create new file id not exist.
     * @param fileName  file.
     */
    public static void createNewFileIfNotExist(File fileName)  {
        //crete new file if not exist
        if (!fileName.exists()) {
            try {
                fileName.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
