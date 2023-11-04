package baseball.controller;

import baseball.service.GameService;
import baseball.view.GameView;

public class GameController {
    GameView gameView = new GameView();
    GameService gameService = new GameService();
    public void startGame() {
        boolean endGameFlag = false;
        gameView.gameStart();
        while (!endGameFlag) {
            gameService.setNumber();
            playGame();
            endGameFlag = stopGame();
        }
    }
    public void playGame() {
        boolean correctFlag = false;
        while (!correctFlag) {
            gameView.readNumber();
            String result = gameService.play();
            gameView.printResult(result);
            if (result.contains("3스트라이크")) {
                correctFlag = true;
                gameView.gameOver();
            }
        }

    }
    public boolean stopGame() {
        gameView.gameRestart();
        return gameService.stop();
    }

}
