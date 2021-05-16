package data;

import useruis.DialogueHandler;

public class Dialogue {

    public DialogueHandler d;
    public String[][] lines = new String[12][12];

    public Dialogue() {
        //0 = Rival
        lines[0][0] = "Hello. Have you ever wondered why\nwe're able to play RPS with no hands?";
        lines[0][1] = "Well, wanna try?";
        lines[0][2] = "_CHOICE_";
        lines[0][3] = "Well, wanna try?\nC = Yes   V = No";
        lines[0][4] = "_BATTLE_";
        lines[0][5] = "You chose 'no'";
        lines[0][6] = "_END_";
        lines[0][7] = "You beat me fair and square, there's\nno doubt about that.";
        lines[0][8] = "_END_";
        lines[0][9] = "Go away! I won't play with losers\nwho have no money!";
        lines[0][10] = "_END_";
    }
}
