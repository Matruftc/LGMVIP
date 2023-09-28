import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

 class TicTacToe extends JFrame implements ActionListener {
    private JButton[][] boardButtons;
    private char currentPlayer;
    private boolean gameOver;

    public TicTacToe() {
        setTitle("Tic-Tac-Toe");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 300);
        setLayout(new GridLayout(3, 3));

        currentPlayer = 'X';
        boardButtons = new JButton[3][3];
        gameOver = false;

        // Initialize the buttons and add action listeners
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                boardButtons[i][j] = new JButton("");
                boardButtons[i][j].setFont(new Font("Arial", Font.PLAIN, 40));
                boardButtons[i][j].setFocusPainted(false);
                boardButtons[i][j].addActionListener(this);
                add(boardButtons[i][j]);
            }
        }

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (gameOver) {
            return;
        }

        JButton buttonClicked = (JButton) e.getSource();

        if (!buttonClicked.getText().equals("")) {
            return;
        }

        buttonClicked.setText(String.valueOf(currentPlayer));

        if (checkWin() || checkDraw()) {
            gameOver = true;
            displayResult();
        } else {
            currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
        }
    }

    private boolean checkWin() {
        // Check rows, columns, and diagonals for a win
        for (int i = 0; i < 3; i++) {
            if (boardButtons[i][0].getText().equals(String.valueOf(currentPlayer))
                    && boardButtons[i][1].getText().equals(String.valueOf(currentPlayer))
                    && boardButtons[i][2].getText().equals(String.valueOf(currentPlayer))) {
                return true; // Row win
            }
            if (boardButtons[0][i].getText().equals(String.valueOf(currentPlayer))
                    && boardButtons[1][i].getText().equals(String.valueOf(currentPlayer))
                    && boardButtons[2][i].getText().equals(String.valueOf(currentPlayer))) {
                return true; // Column win
            }
        }
        if (boardButtons[0][0].getText().equals(String.valueOf(currentPlayer))
                && boardButtons[1][1].getText().equals(String.valueOf(currentPlayer))
                && boardButtons[2][2].getText().equals(String.valueOf(currentPlayer))) {
            return true; // Diagonal win (top-left to bottom-right)
        }
        if (boardButtons[0][2].getText().equals(String.valueOf(currentPlayer))
                && boardButtons[1][1].getText().equals(String.valueOf(currentPlayer))
                && boardButtons[2][0].getText().equals(String.valueOf(currentPlayer))) {
            return true; // Diagonal win (top-right to bottom-left)
        }

        return false;
    }

    private boolean checkDraw() {
        // Check for a draw (no empty cells left)
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (boardButtons[i][j].getText().equals("")) {
                    return false; // There's an empty cell, the game is not a draw yet
                }
            }
        }
        return true; // All cells are filled, it's a draw
    }

    private void displayResult() {
        String result;
        if (checkWin()) {
            result = "Player " + currentPlayer + " wins!";
        } else {
            result = "It's a draw!";
        }
        JOptionPane.showMessageDialog(this, result, "Game Over", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new TicTacToe();
        });
    }
}
 
