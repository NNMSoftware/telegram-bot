package ru.nnmsoftware.encounter.api;

import ru.nnmsoftware.encounter.beans.LevelInfo;

/**
 * Created by stille on 02.09.16.
 */
public class EncounterApi {

    public String login(String login, String password) {
        // TODO: 02.09.16 implementation
        return null;
    }

    public void logout(String token) {
        // TODO: 02.09.16 implementation
    }

    public boolean enterTheGame(String token, long gameId) {
        // TODO: 02.09.16 implementation
        return false;
    }

    public boolean sendCode(String token, String code) {
        // TODO: 02.09.16 implementation
        return false;
    }

    public boolean sendBonusCode(String token, String code) {
        // TODO: 02.09.16 implementation
        return false;
    }

    public LevelInfo getCurrentLevelInfo(String token) {
        // TODO: 02.09.16 implementation
        return new LevelInfo();
    }

}