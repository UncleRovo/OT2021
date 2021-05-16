package data;

import java.util.Random;

public class Battle {
    Character player;
    Character opponent;
    int stake;
    public String option;

    public Battle(Character player, Character opponent, int stake) {
        this.player = player;
        this.opponent = opponent;
        this.stake = stake;
    }
    
    public String fight() {
        if (winner() == player) {
            int winamount = opponent.takeMoney(stake);
            player.addMoney(winamount);
            return winmessage(winamount);
        } else if (tryToConvince() == false) {
            opponent.addMoney(player.takeMoney(stake));
            return this.losemessage();
        } else {
            return this.newchancemessage();
        }
    }
    
    public int probability() {
        int base = 45;
        int luckDiff = player.luck - opponent.luck;
        base += luckDiff * 4;
        base += player.luck;
        return base;
    }
    
    public Character winner() {
        if (doesPlayerWin()) {
            return player;
        }
        return opponent;
    }
    
    public boolean doesPlayerWin() {
        int probability = this.probability();
        Random rng = new Random();
        int random = rng.nextInt(100) + 1;
        return random <= probability;
    }
    
    public boolean tryToConvince() {
        int chances = this.charismaChance();
        Random rng = new Random();
        int random = rng.nextInt(100) + 1;
        
        return random <= chances; 
    }
    
    public int charismaChance() {
        int charDiff = player.charisma - opponent.charisma;
        if (charDiff < -3) {
            return 0;
        }
        
        charDiff += 3;
        
        int chances = 1 + (charDiff * 2);
        
        return chances;
    }

    private String winmessage(int win) {
        return "You chose " + option.toLowerCase() + " and your opponent chose " + this.getwinOption(option) +",\n you won! You get " + win + " moneys for winning.";
    }
    
    private String losemessage() {
        return "You chose " + option.toLowerCase() + " and your opponent chose " + this.getloseOption(option) +",\n you lost! You part your ways with " + stake + " of your moneys.";
    }
    
    private String newchancemessage() {
        return "You chose " + option.toLowerCase() + " and your opponent chose " + this.getloseOption(option) +", \nyou lost! Though, Rival thinks you're a great guy \nso you get to try again!";
    }

    public String getwinOption(String option) {
        option = option.toLowerCase();
        
        switch (option) {
            case "rock":
                return "scissors";
            case "paper":
                return "rock";
            default:
                return "paper";
        }
    }
    
    public String getloseOption(String option) {
        option = option.toLowerCase();
        
        switch (option) {
            case "rock":
                return "paper";
            case "paper":
                return "scissors";
            default:
                return "rock";
        }
    }
}
