package sample;

import java.util.Random;
import java.util.Scanner;

public class GameField {


    public  String[] colors = {"R","B","Y"};
    final int BOARD = 15;
    int LIVES = 3;

    Tile[][] array_board = new Tile[BOARD][BOARD]; // array board
    Scanner sc = new Scanner(System.in);//for taking user input...
    GameState gameState = GameState.PLAYING;

    private void removeSameColorTiles(int row , int col){
        String color = array_board[row][col].getColor();
        for (int i = 0; i < BOARD; i++){
            for (int j = 0; j < BOARD; j++){
                if(array_board[i][j].getColor().equalsIgnoreCase(color)){
                    array_board[i][j].setValue(0);
                    array_board[i][j].setColor("E");
                }
            }
        }
    }

    private  void removeTiles(int row , int col){

        array_board[row][col].setValue(0);

        //for removing current rows
        for (int j = row; j < BOARD - 1; j++) {
            for (int i = col; i < BOARD - 1; i++) {

                //for removing row s
                if (array_board[col][row].getColor().equalsIgnoreCase(array_board[col][i + 1].getColor())) {
                    array_board[col][i + 1].setColor("E");
                    array_board[col][i + 1].setValue(0);

                }

            }
        }

        //for removing current cols
        for (int j = col; j < BOARD - 1; j++) {
            for (int i = row; i < BOARD - 1; i++) {
                //for removing row s
                if (array_board[row][col].getColor().equalsIgnoreCase(array_board[i + 1][col].getColor())) {
                    array_board[i + 1][row].setColor("E");
                    array_board[i + 1][row].setValue(0);
                }
            }
        }


        array_board[row][col].setColor("E");

    }


    void getUserinput() {

        print("Enter valid row and column :");
        int row = sc.nextInt();
        int col = sc.nextInt();

        if(isValidIndex(row) && isValidIndex(col)) {



             removeTiles(row,col);
             printBoard();

             //if game is over...
             if (isGameOver()) {
                 print("****************************************");
                 print("* YOU WON , there are no tiles left... *");
                 print("****************************************");
                 return;
             }



        }else{
            print("Please enter valid index between 0 - 14 ");
            print("Please enter valid row and column : ");

        }
    }

    void useLive() {
        if(LIVES > 0) {

            print("YOU CAN USE ONLY 3 LIVES: ");
            print("WANNA USE LIVE ? ");
            print("Enter 1 to use Live: ");
            print("Enter 0 to avoid : ");

            int choice = sc.nextInt();
            if(choice == 1){
                LIVES--;
                print("Your remaining lives: "+LIVES);
                print("Enter row and column : ");
                int row = sc.nextInt();
                int col = sc.nextInt();
                removeSameColorTiles(row,col);
                getUserinput();

            }else {
                getUserinput();
            }
        }
    }

    public boolean isValidIndex(int index){
        if(index > 14){
            return  false;
        }
        return true;
    }

    //check if game is over...
    public  boolean isGameOver(){
        int count = 0;
        for (int i =0 ; i < BOARD; i ++){
            for (int j =0 ; j < BOARD; j ++){
                if(array_board[i][j].getValue() == 0){
                    count ++;
                }
            }
        }
        if(count == 225){
            return true;
        }

        return false;
    }

    public  void  generateT(){
        //1 = red : 2 = blue : 3 = yellow
        print("Brick Breaking - GAME");
        print("----------------------------------------");
        print("Instructions:");
        print("It has 3 colors , mentioned below.");
        print("1 = red : R = blue : B = yellow : Y  and E = empty");
        print("GAME RULES");
        print("This game has the board of 15 X 15 rows and columns.");
        print("This game removes the same color tiles on the same row ,column and diagonally.");
        print("Enter th indexes b/t (0-14) to play...");
        print("If you want to remove the same color on the whole board , you can use live.");
        print("If all board gets empty : player wins ! ");
        print("LIVES : 3");
        print("----------------------------------------");

        for (int i =0 ; i < BOARD; i ++){
            for (int j =0 ; j < BOARD; j ++){
                array_board[i][j] = new Tile();

                String c = colors[(new Random()).nextInt(colors.length)];
                //print(c);

                if (c.equalsIgnoreCase("R")) {
                    array_board[i][j].setValue(1);
                    array_board[i][j].setColor("R");
                } else if (c.equalsIgnoreCase("B")) {
                    array_board[i][j].setValue(2);
                    array_board[i][j].setColor("B");
                } else if (c.equalsIgnoreCase("Y")) {
                    array_board[i][j].setValue(3);
                    array_board[i][j].setColor("Y");
                }
            }
        }
    }

    public  void setAllEmpty(){
        for (int i =0 ; i < BOARD; i ++){
            for (int j =0 ; j < BOARD; j ++){
                array_board[i][j].setValue(0);
                array_board[i][j].setColor("");
            }
        }
    }

    public  String toStringM(){
        String dt = "";
        int arrayindex = 0;
        dt += "    0  1  2  3  4  5  6  7  8  9  10 11 12 13 14 ";
        char[] array = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O'};//char array for labelling...
        for (int i =0 ; i < BOARD; i++){
            dt+= "\n";
            dt+= array[arrayindex]+" | ";arrayindex++;
            for (int j =0 ; j < BOARD; j ++){
                dt+=  array_board[i][j].getColor() + "  ";
            }

        }
        dt += "\n";
        return dt;
    }
     void printBoard(){
        System.out.println(toStringM());
    }
    //to display message
    public  void print(String sting){
        System.out.println(sting);
    }


}
