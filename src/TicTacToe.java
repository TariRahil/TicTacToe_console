import java.sql.ClientInfoStatus;
import java.util.*;

public class TicTacToe {
    private char[][] gameBoard;
    private boolean isPlayer;
    private char symbol = ' ';
    private static ArrayList<Integer> playerPosition = new ArrayList<Integer>();
    private static ArrayList<Integer> cupPosition = new ArrayList<Integer>();



    public TicTacToe() {
        this.gameBoard = new char[][]{{' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '},
                {'-', '+', '-', '+', '-'},
                {' ', '|', ' ', '|', ' '}};
        this.isPlayer = true;
    }

    public char[][] game(){
        printGameBoard();
        return gameBoard;
    }
    public void printGameBoard(){
        for (char[] row : gameBoard) {
            for (char c : row)
                System.out.print(c);
            System.out.println();
        }
    }

    public void start(){
        int pos;
        while (true) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter a placement (1-9): ");
            pos = scanner.nextInt();
            symbol = 'X';
            while (playerPosition.contains(pos) ||
                    cupPosition.contains(pos)){
                System.out.println("Enter a correct position: ");
                pos = scanner.nextInt();
            }
            playerPosition.add(pos);
            String winner = checkWinner();
            if (winner.length() > 0){
                System.out.println(winner);
                break;
            }
            placeInBoard(pos);
            isPlayer = false;


            Random random = new Random();
            pos = random.nextInt(9) + 1;
            symbol = 'O';
            while (playerPosition.contains(pos) ||
                    cupPosition.contains(pos)){
                System.out.println("Enter a correct position: ");
                pos = random.nextInt(9) + 1;
            }
            cupPosition.add(pos);
            placeInBoard(pos);
            isPlayer = true;

            winner = checkWinner();
            if (winner.length() > 0){
                System.out.println(winner);
                break;
            }
            System.out.println(winner);
        }
    }

    private String checkWinner() {
        List topRow = Arrays.asList(1,2,3);
        List midRow = Arrays.asList(4,5,6);
        List botRow = Arrays.asList(7,8,9);
        List leftcol = Arrays.asList(1,4,7);
        List midcol = Arrays.asList(2,5,8);
        List rightcol = Arrays.asList(3,6,9);
        List cross1 = Arrays.asList(3,5,7);
        List cross2 = Arrays.asList(1,5,9);

        List<List> winner = new ArrayList<List>();
        winner.add(topRow);
        winner.add(midRow);
        winner.add(botRow);
        winner.add(leftcol);
        winner.add(midcol);
        winner.add(rightcol);
        winner.add(cross1);
        winner.add(cross2);

        for (List l: winner){
            if (playerPosition.containsAll(l)){
                return "you win!";
            } else if (cupPosition.containsAll(l)) {
                return "cpu wins!";
            }else if (playerPosition.size() + cupPosition.size() == 9){
                return "CAT!";
            }
        }
        return "";
    }


    public void placeInBoard(int pos){
        switch (pos){
            case 1:
                gameBoard[0][0] = symbol;
                break;
            case 2:
                gameBoard[0][2] = symbol;
                break;
            case 3:
                gameBoard[0][4] = symbol;
                break;
            case 4:
                gameBoard[2][0] = symbol;
                break;
            case 5:
                gameBoard[2][2] = symbol;
                break;
            case 6:
                gameBoard[2][4] = symbol;
                break;
            case 7:
                gameBoard[4][0] = symbol;
                break;
            case 8:
                gameBoard[4][2] = symbol;
                break;
            case 9:
                gameBoard[4][4] = symbol;
                break;
            default:
                break;
        }
        printGameBoard();
    }
}
