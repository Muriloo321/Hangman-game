import models.services.GameStructure;

public class Main {
    public static void main(String[] args) {
        //Por questões de conveniência, preferi nomear classes, métodos e variáveis na língua inglesa.

        GameStructure game = new GameStructure();
        game.initializeGame();

            while(game.getLives() >= 0) {
                game.round();
            }

    }
}