package data;

import java.util.Random;

public class Battle {
    Character player;
    Character opponent;
    int stake;

    public Battle(Character player, Character opponent, int stake) {
        this.player = player;
        this.opponent = opponent;
        this.stake = stake;
    }
    
    public void fight() {
        if (winner() == player) {
            player.addMoney(opponent.takeMoney(stake));
        } else if (tryToConvince() == false) {
            opponent.addMoney(player.takeMoney(stake));
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
}
