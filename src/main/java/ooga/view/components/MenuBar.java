package ooga.view.components;

import java.util.ResourceBundle;
import javafx.scene.Node;
import javafx.scene.layout.HBox;
import ooga.Main;
import ooga.Reflection;
import ooga.view.InteractiveObject;
import ooga.view.StartView;
import ooga.view.View;
import ooga.view.api.ChildView;
import ooga.view.button.CustomizedButton;
import ooga.view.scene.MonopolyGameEditorScene;
import ooga.view.scene.SceneManager;

public class MenuBar extends View implements ChildView {

  private String myLanguage;
  private HBox buttonBar;
  public MenuBar(String language) {
    ResourceBundle resources = ResourceBundle.getBundle(Main.DEFAULT_RESOURCE_PACKAGE + SceneManager.USER_INTERFACE);
    String[] buttons = resources.getString("MenuBarButtons").split(", ");
    this.myLanguage = language;
    makeButtons(buttons);
  }
  private void makeButtons(String[] buttons) {
    this.buttonBar = new HBox();
    for (String button : buttons) {
      buttonBar.getChildren().add((CustomizedButton) makeInteractiveObject(button, myLanguage, this));
    }
  }
  public void saveFile() {}
  public void changeDimensions() {
  }
  public void fileHandler() {}

  @Override
  public Node getView() {
    return buttonBar;
  }
}
