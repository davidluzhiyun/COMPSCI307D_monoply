package ooga.view.pop_ups;

import java.util.ResourceBundle;
import javafx.scene.Scene;
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
  private String myLanguage;

  public ActionPopUp (String language) {
    this.myLanguage = language;
  }
  public void popUpStyle(Scene scene, String file) {
    String fileName = String.format("%s.css", file);
    scene.getStylesheets()
        .add(View.class.getResource(View.STYLESHEETS + fileName).toExternalForm());
  }

  public abstract void close();

  public abstract void createScene();

  @Override
  public InteractiveObject makeInteractiveObject(String name) {
    Reflection reflection = new Reflection();
    ResourceBundle resources = ResourceBundle.getBundle(View.BUTTON_PROPERTIES);
    String className = resources.getString(name);
    InteractiveObject object = (InteractiveObject) reflection.makeObject(className,
        new Class[]{String.class},
        new Object[]{myLanguage});
    String method = resources.getString(
        String.format(StartView.STRING_FORMATTER, name, StartView.METHOD));
    if (name.contains(StartView.DROP_DOWN)) {
      object.setAction(reflection.makeMethod(method, ActionPopUp.class, new Class[]{Number.class}),
          this);
    } else {
      object.setAction(reflection.makeMethod(method, ActionPopUp.class, null), this);
    }
    return object;
  }

  /**
   * Set within property files to handle changes to GamePieceDropDown
   */
  public void previewPiece(Number newValue) {
    System.out.println("does this get called??");
    ResourceBundle choiceResources = ResourceBundle.getBundle(
        Main.DEFAULT_RESOURCE_PACKAGE + StartView.DROP_DOWN);
    String gamePiece = choiceResources.getString(
        String.format(StartView.STRING_INT_FORMATTER, GameView.GAME_PIECE, newValue));
    System.out.println(gamePiece);
  }

}
