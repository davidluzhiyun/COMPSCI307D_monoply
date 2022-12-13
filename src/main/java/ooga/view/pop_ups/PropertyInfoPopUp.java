package ooga.view.pop_ups;

import com.google.gson.internal.LinkedTreeMap;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import ooga.Main;
import ooga.event.GameEvent;

/**
 * Should appear when users click on a property on the board.
 */
public class PropertyInfoPopUp extends InformationPopUp {
  private Map<String, LinkedTreeMap> map;
  public PropertyInfoPopUp(GameEvent event) {
    this.map = (Map<String, LinkedTreeMap>) event.getGameEventCommand().getCommand().getCommandArgs();
    for (String key : map.keySet()) {
      System.out.println(key);
      System.out.println(map.get(key));
    }
  }

  @Override
  public void showMessage(String language) {
    VBox text = new VBox();
    for (String key : map.keySet()) {
      Label txt = new Label(String.format("%s: %s", key.toUpperCase(), map.get(key)));
      text.getChildren().add(txt);
    }
//    ResourceBundle resources = ResourceBundle.getBundle(Main.DEFAULT_LANGUAGE_PACKAGE + language);
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setGraphic(text);
//    alert.setTitle(resources.getString(RENT_MESSAGE_TITLE));
//    alert.setHeaderText(resources.getString(RENT_MESSAGE_HEADER));
    alert.showAndWait();
  }
}
