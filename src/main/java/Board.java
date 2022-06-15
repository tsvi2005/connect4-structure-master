import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;

public class Board extends JFrame {
    private final JButton[][] squares = new JButton[7][];
    private int player = 1;
    private int xOneY = 1;
    private int xTwoY = 1;
    private int xThreeY = 1;
    private int xFourY = 1;
    private int xFiveY = 1;
    private int xSixY = 1;
    private int xSevenY = 1;


    public Board() {
        for(int i = 0; i < Constant.BOARD_WIDTH; ++i) {
            this.squares[i] = new JButton[Constant.BOARD_WIDTH];

            for(int j = 0; j < Constant.BOARD_WIDTH; ++j) {
                JButton square = new JButton();
                if (i == 0) {
                    square.setText(String.valueOf(j + 1));
                    square.setBackground(Color.WHITE);
                    square.setFont(new Font(Constant.FONT_NAME, 1, Constant.FONT_SIZE));
                    square.addActionListener((e) -> {
                        int column = Integer.valueOf(square.getText());
                        int rawY = 1;
                        switch (column){
                            case 1:
                             rawY = xOneY ;
                             xOneY++;
                             break;
                            case 2:
                                rawY = xTwoY ;
                                xTwoY++;
                                break;
                            case 3:
                                rawY = xThreeY ;
                                xThreeY++;
                                break;
                            case 4:
                                rawY = xFourY ;
                                xFourY++;
                                break;
                            case 5:
                                rawY = xFiveY ;
                                xFiveY++;
                                break;
                            case 6:
                                rawY = xSixY ;
                                xSixY++;
                                break;
                            case 7:
                                rawY = xSevenY ;
                                xSevenY++;
                                break;
                        }
                        if (rawY<7){
                            placeSquare(column,rawY, numPlayer(player));

                            if (winner()){
                                System.out.println("Player "+numPlayer(player)+ " win");
                            }
                            player++;
                        }
                    });
                } else {
                    square.setEnabled(false);
                }

                this.squares[i][j] = square;
                this.add(square);
            }
        }

        this.setLocationRelativeTo((Component)null);
        GridLayout gridLayout = new GridLayout(Constant.BOARD_WIDTH, Constant.BOARD_HEIGHT);
        this.setLayout(gridLayout);
        this.setSize(Constant.BOARD_HEIGHT * Constant.SQUARE_SIZE, Constant.BOARD_HEIGHT * Constant.SQUARE_SIZE);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public void placeSquare(int x, int y, int player) {
        this.squares[Constant.BOARD_WIDTH - y][x - 1].setBackground(player == 1 ? Color.RED : Color.YELLOW);
    }

    public int getPlayerInSquare(int x, int y) {
        byte player = 0;

        try {
            Color backgroundColor = this.squares[Constant.BOARD_WIDTH - y][x - 1].getBackground();
            if (backgroundColor.equals(Color.RED)) {
                player = 1;
            } else if (backgroundColor.equals(Color.YELLOW)) {
                player = 2;
            }
        } catch (Exception var5) {
            var5.printStackTrace();
        }

        return player;
    }
    public int numPlayer(int playerNum){
        int playerNumber = playerNum % 2 +1;
        return playerNumber;
    }
    public boolean winner(){
        boolean win = false;
        for (int i = 1; i <5 ; i++) {
            for (int j = 1; j < 5; j++) {
               if (getPlayerInSquare(i,j)==1 || getPlayerInSquare(i,j)==2) {
                   if (getPlayerInSquare(i, j) == getPlayerInSquare(i + 1, j + 1) && getPlayerInSquare(i, j) == getPlayerInSquare(i + 2, j + 2) && getPlayerInSquare(i, j) == getPlayerInSquare(i + 3, j + 3)) {
                       win = true;
                       break;
                   }
                   if (getPlayerInSquare(i, j) == getPlayerInSquare(i + 1, j) && getPlayerInSquare(i, j) == getPlayerInSquare(i + 2, j) && getPlayerInSquare(i, j) == getPlayerInSquare(i + 3, j)) {
                       win = true;
                       break;
                   }
                   if (getPlayerInSquare(i, j) == getPlayerInSquare(i + 1, j) && getPlayerInSquare(i, j) == getPlayerInSquare(i, j + 2) && getPlayerInSquare(i, j) == getPlayerInSquare(i, j + 3)) {
                       win = true;
                       break;
                   }
               }
            }
        }
        return win;
    }

}
