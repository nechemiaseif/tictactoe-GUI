// Nechemia Seif

package ticTacToeGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static javax.swing.JOptionPane.YES_NO_OPTION;

public class TicTacToeFrame extends JFrame {

    private TicTacToeModel gameModel;
    private JLabel statusBar;

    public TicTacToeFrame() {

        gameModel = new TicTacToeModel();
        statusBar = new JLabel(" ");

        setSize(500, 520);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        add(new TicTacToePanel(), BorderLayout.CENTER);
        add(statusBar, BorderLayout.SOUTH);

        setVisible(true);
    }

    class TicTacToePanel extends JPanel {

        private JButton[][] gridButtons = new JButton[3][3];

        public TicTacToePanel() {

            setLayout(new GridLayout(3, 3));
            ButtonEventHandler buttonEventHandler = new ButtonEventHandler();
            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    gridButtons[row][col] = new JButton();
                    gridButtons[row][col].setFont(new Font("Arial",
                            Font.BOLD, 120));

                    add(gridButtons[row][col]);

                    gridButtons[row][col].addActionListener(buttonEventHandler);
                }
            }
        }

        private void restartGame() {

            gameModel.initialize();

            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    gridButtons[row][col].setText("");
                }
            }

            statusBar.setText("");
        }

        private class ButtonEventHandler implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {

                int row = 0, col = 0;
                gridLocationFinder:
                for (row = 0; row < 3; row++) {
                    for (col = 0; col < 3; col++) {
                        if (e.getSource() == gridButtons[row][col]) {
                            break gridLocationFinder;
                        }
                    }
                }

                if (gameModel.setCell(row, col, gameModel.currentPlayer())) {

                    JButton buttonPressed = (JButton) e.getSource();
                    buttonPressed.setText(gameModel.currentPlayer().toString());

                    buttonPressed.setForeground(gameModel.currentPlayer()
                            .toString().equals("X") ? Color.RED : Color.BLUE);

                    if (gameModel.isGameOver()) {

                        String gameOverMessage = gameModel.hasWinner()
                                ? "Congratulations, "
                                + gameModel.currentPlayer().toString()
                                + ", you win!"
                                : "It's a draw!";

                        statusBar.setText(gameOverMessage);

                        int restart
                                = JOptionPane.showConfirmDialog
                                        (TicTacToePanel.this, gameOverMessage
                                        + " Would you like to play again?",
                                        "", YES_NO_OPTION);

                        switch (restart) {
                            case 0:
                                restartGame();
                                break;
                            case 1:
                                System.exit(0);
                        }

                    } else {
                        gameModel.togglePlayer();
                        statusBar.setText("Player "
                                + gameModel.currentPlayer().toString()
                                + "'s turn");
                    }
                }
            }
        }
    }
}
