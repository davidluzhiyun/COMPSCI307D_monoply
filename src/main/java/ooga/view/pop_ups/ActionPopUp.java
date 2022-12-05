package ooga.view.pop_ups;

import java.util.ResourceBundle;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import ooga.Main;
import ooga.Reflection;
import ooga.view.GameView;
import ooga.view.InteractiveObject;
import ooga.view.StartView;
import ooga.view.View;

public abstract class ActionPopUp extends View implements PopUp {

  public static final String PLAYER_TEXT_KEY = "PlayerText";
  public static final String WIDTH = "Width";
  public static final String HEIGHT = "Height";

  public void popUpStyle(Scene scene, String file) {
    String fileName = String.format("%s.css", file);
    scene.getStylesheets()
        .add(View.class.getResource(View.STYLESHEETS + fileName).toExternalForm());
  }

  public abstract void close();

  public abstract void createScene();

}
