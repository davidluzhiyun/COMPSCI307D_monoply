public class Controller {

    public Controller(GameEventHandler gameEventHandler) {
        this.gameEventHandler = gameEventHandler;
    }

    // TODO: Find a better way to check the game event type other than switch case statements
    public void onGameEvent(GameEvent event) {
        switch (event.getGameEventType()) {
            case "GAME_START":
                Command cmd = event.getGameEventCommand().getCommand();
                gameStart(cmd);
                break;

            case "ROLL_DICE":
                Command cmd = event.getGameEventCommand().getCommand();
                rollDice(cmd);
                break;

            case "UPDATE_VIEW":
                Command cmd = event.getGameEventCommand().getCommand();
                updateView(cmd);
                break;

            case "UPDATE_MODEL":
                Command cmd = event.getGameEventCommand().getCommand();
                updateModel(cmd);
                break;
        }
    }
}

