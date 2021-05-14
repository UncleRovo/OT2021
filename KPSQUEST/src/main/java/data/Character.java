package data;

public class Character {
    int luck;
    int charisma;
    int money;

    public Character(int luck, int charisma, int money) {
        this.luck = luck;
        this.charisma = charisma;
        this.money = money;
    }
    
    public int takeMoney(int i) {
        if (money - i < 0) {
            int amount = money;
            money = 0;
            return amount;
        } else {
            money -= i;
            return i;
        }
    }
    
    public void addMoney(int i) {
        money += i;
        if (money > 5000) {
            money = 5000;
        }
    }
}
