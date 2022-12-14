package ooga.view.pop_ups;

import com.google.gson.internal.LinkedTreeMap;
import java.util.Map;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import ooga.event.GameEvent;

/**
 * Should appear when users click on a property on the board.
 */
public class PropertyInfoPopUp extends InformationPopUp {

  private Map<String, LinkedTreeMap> map;

  public PropertyInfoPopUp(GameEvent event) {
    this.map = (Map<String, LinkedTreeMap>) event.getGameEventCommand().getCommand()
        .getCommandArgs();
  }

  @Override
  public void showMessage(String language) {
    VBox text = new VBox();
    for (String key : map.keySet()) {
      Label txt = new Label(String.format("%s: %s", key.toUpperCase(), map.get(key)));
      text.getChildren().add(txt);
    }
    Alert alert = new Alert(AlertType.INFORMATION);
    alert.setGraphic(text);
    alert.showAndWait();
  }
}
