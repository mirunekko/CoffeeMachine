import java.util.Scanner;

import static java.lang.Integer.parseInt;

public class CoffeeMachine {
    final Scanner scanner = new Scanner(System.in);
    private int startMoney = 550;
    private int startWater = 400;
    private int startMilk = 540;
    private int startBean = 120;
    private int startCup = 9;

    public void readInput(States state) {
        String input = scanner.next();
        if (state == States.CHOOSING_AN_ACTION) {
            switch (input) {
                case "buy":
                    buyCoffee();
                    chooseAction();
                    break;
                case "fill":
                    fillMachine();
                    chooseAction();
                    break;
                case "take":
                    takeMoney();
                    chooseAction();
                    break;
                case "remaining":
                    showInfo();
                    chooseAction();
                    break;
                case "exit":
                    System.exit(0);
                    break;
                default:
                    break;
            }
        } else if (state == States.CHOOSING_COFFEE) {
            switch (input) {
                case "1":
                    prepareEspresso();
                    break;
                case "2":
                    prepareLatte();
                    break;
                case "3":
                    prepareCappuccino();
                    break;
                case "back":
                    chooseAction();
                default:
                    break;
            }
        } else if (state == States.ADDING_WATER) {
            startWater += parseInt(input);
        } else if (state == States.ADDING_MILK) {
            startMilk += parseInt(input);
        } else if (state == States.ADDING_BEANS) {
            startBean += parseInt(input);
        } else if (state == States.ADDING_CUPS) {
            startCup += parseInt(input);
        }
    }

    public void showInfo() {
        System.out.printf("The coffee machine has:\n" +
                "%d ml of water\n" +
                "%d ml of milk\n" +
                "%d g of coffee beans\n" +
                "%d disposable cups\n" +
                "$%d of money\n", startWater, startMilk, startBean, startCup, startMoney);
    }

    public void chooseAction() {
        System.out.println("Write action (buy, fill, take, remaining, exit):");
        States state = States.CHOOSING_AN_ACTION;
        readInput(state);
    }

    public void buyCoffee() {
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino," +
                " back - to main menu: ");
        States state = States.CHOOSING_COFFEE;
        readInput(state);
    }

    public void prepareEspresso() {
        if (startWater >= 250 && startBean >= 16 && startCup >= 1) {
            System.out.println("I have enough resources, making you a coffee!\n");
            startWater -= 250;
            startBean -= 16;
            startMoney += 4;
            startCup--;
        } else if (startWater < 250) {
            System.out.println("Sorry, not enough water!");
        } else if (startBean < 16) {
            System.out.println("Sorry, not enough beans!");
        } else if (startCup == 0) {
            System.out.println("Sorry, not enough cups!");
        }
    }

    public void prepareLatte() {
        if (startWater >= 350 && startBean >= 20 && startCup >= 1 && startMilk >= 75) {
            System.out.println("I have enough resources, making you a coffee!");
            startWater -= 350;
            startMilk -= 75;
            startBean -= 20;
            startMoney += 7;
            startCup--;
        } else if (startWater < 350) {
            System.out.println("Sorry, not enough water!");
        } else if (startBean < 20) {
            System.out.println("Sorry, not enough beans!");
        } else if (startCup == 0) {
            System.out.println("Sorry, not enough cups!");
        } else if (startMilk < 75) {
            System.out.println("Sorry, not enough milk!");
        }
    }

    public void prepareCappuccino() {
        if (startWater >= 200 && startBean >= 12 && startCup >= 1 && startMilk >= 100) {
            System.out.println("I have enough resources, making you a coffee!");
            startWater -= 200;
            startMilk -= 100;
            startBean -= 12;
            startMoney += 6;
            startCup--;
        } else if (startWater < 200) {
            System.out.println("Sorry, not enough water!");
        } else if (startBean < 12) {
            System.out.println("Sorry, not enough beans!");
        } else if (startCup == 0) {
            System.out.println("Sorry, not enough cups!");
        } else if (startMilk < 100) {
            System.out.println("Sorry, not enough milk!");
        }
    }

    public void takeMoney() {
        System.out.printf("I gave you $%d\n", startMoney);
        startMoney = 0;
    }

    public void fillMachine() {
        System.out.println("Write how many ml of water you want to add");
        States state = States.ADDING_WATER;
        readInput(state);
        System.out.println("Write how many ml of milk you want to add: ");
        state = States.ADDING_MILK;
        readInput(state);
        System.out.println("Write how many grams of coffee beans you want to add:");
        state = States.ADDING_BEANS;
        readInput(state);
        System.out.println("Write how many disposable cups of coffee you want to add: ");
        state = States.ADDING_CUPS;
        readInput(state);
    }
}
