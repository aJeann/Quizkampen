package Client;

import java.net.Socket;

/**
 * Created by Christoffer Grännby
 * Date: 2020-11-15
 * Time: 12:31
 * Project: Quizkampen
 * Copyright: MIT
 */
public class Client implements Runnable {

    private String LOCATION = "127.0.0.1";
    Socket socket;
    private static int PORT = 8901;


    public Client(Socket playerOne, Socket playerTwo) {
//          this.playerOne = new Player(playerOne);
//          this.playerTwo = new Player(playerTwo);

    }

    public Client() {
    }

    public Client(String LOCATION) {
        this.LOCATION = LOCATION;
    }


    @Override
    public void run() {

    }
}

//    public void play() throws Exception {
//        socket = new Socket(LOCATION, PORT);
//        BufferedReader in = new BufferedReader(new InputStreamReader(
//                socket.getInputStream()));
//        PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
//
//        String response;
//        GUI game = new GUI();
//        char mark = 'S';
//        char opponentMark = 'P';
//        try {
//            response = in.readLine();
//            if (response.startsWith("WELCOME")) {
//                mark = response.charAt(8);
//                opponentMark = (mark == 'X' ? 'O' : 'X');
//                frame.setTitle("Tic Tac Toe - Player " + mark);
//            }
//            while (true) {
//                response = in.readLine();
//                if (response.startsWith("VALID_MOVE")) {
//                    messageLabel.setText("Valid move, please wait");
//                    currentSquare.setText(String.valueOf(mark));
//                    currentSquare.repaint();
//                } else if (response.startsWith("OPPONENT_MOVED")) {
//                    int loc = Integer.parseInt(response.substring(15));
//                    board[loc].setText(String.valueOf(opponentMark));
//                    board[loc].repaint();
//                    messageLabel.setText("Opponent moved, your turn");
//                } else if (response.startsWith("VICTORY")) {
//                    messageLabel.setText("You win");
//                    break;
//                } else if (response.startsWith("DEFEAT")) {
//                    messageLabel.setText("You lose");
//                    break;
//                } else if (response.startsWith("TIE")) {
//                    messageLabel.setText("You tied");
//                    break;
//                } else if (response.startsWith("MESSAGE")) {
//                    messageLabel.setText(response.substring(8));
//                }
//            }
//            out.println("QUIT");
//        }
//        finally {
//            socket.close();
//        }
//    }
//
//
//    public static void main(String[] args) throws Exception {
//        while (true) {
//            String serverAddress = (args.length == 0) ? "localhost" : args[1];
//            Client client = new Client(serverAddress);
////            client.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
////            client.frame.setSize(240, 160);
////            client.frame.setVisible(true);
////            client.frame.setResizable(true);
//            client.play();
//            if (!client.wantsToPlayAgain()) {
//                break;
//            }
//}