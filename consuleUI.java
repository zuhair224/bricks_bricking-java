package sample;

import java.lang.management.LockInfo;
import java.util.Random;
import java.util.Scanner;

public class ConsoleUI {

    public static void main(String[] args) {

        //instance
        GameField gameField = new GameField();
        gameField.generateT(); // generating tiles...
        gameField.printBoard(); // displaying on console...

        while (!gameField.isGameOver()){
            try {
                gameField.getUserinput();
                gameField.useLive();
            }catch (Exception e){
                gameField.print("Message : "+e.getMessage());
                break;
            }
        }
    }

}
