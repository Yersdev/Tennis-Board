package com.yers.tennisboard;

import com.yers.tennisboard.entity.Game;

public class UtilHelper {
    public static Game getGame1() {
        Game game = new Game();
        game.setFullName1("User 1");
        game.setFullName2("User 2");
        game.setScore1(1);
        game.setScore2(2);
        game.setSet1(1);
        game.setSet2(2);
        return game;
    }
}
