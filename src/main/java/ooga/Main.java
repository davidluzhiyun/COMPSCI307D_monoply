package ooga;

import ooga.controller.SampleController;
import ooga.event.GameEvent;
import ooga.event.GameEventHandler;
import ooga.view.SampleView;

public class Main {
    /**
     * Start of the program.
     */
    public static void main (String[] args) {
        GameEventHandler gameEventHandler = new GameEventHandler();
        SampleController sampleController = new SampleController(gameEventHandler);
        gameEventHandler.addEventListener(sampleController);
        SampleView sampleView = new SampleView(gameEventHandler);
        gameEventHandler.addEventListener(sampleView);
        sampleView.start();
    }
}
