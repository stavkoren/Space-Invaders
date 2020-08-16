package scores;

import biuoop.DialogManager;
import biuoop.GUI;

/**
 *DialogUser - in charge of get user name.
 */
public  class DialogUser {
    /***
     * getUserDetails - diolo uer about its name.
     * @param score - users score
     * @param gui .
     * @return ScoreInfo.
     */
    public static ScoreInfo getUserDetails(int score, GUI gui) {
        DialogManager dialog = gui.getDialogManager();
        String name = dialog.showQuestionDialog("Name", "What is your name?", "");
        return  new ScoreInfo(name, score);
    }
}
