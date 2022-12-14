package ooga.view.components;

import java.util.ResourceBundle;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import ooga.model.player.ControllerPlayer;
import ooga.view.View;
import ooga.view.api.ChildView;

public class PlayerHUD extends VBox implements ChildView {

  public static final String HUD_ID = "HudId";
  public static final String HUD_MONEY = "HudMoney";
  public static final String HUD_JAIL = "HudJailStatus";
  public static final String HUD_PROPERTIES = "HudProperties";


  public PlayerHUD(String language, ControllerPlayer player) {
    ResourceBundle resources = ResourceBundle.getBundle(View.POP_UP_PROPERTIES);
    Label playerText = new Label(String.format(resources.getString(HUD_ID), player.getPlayerId()+1));
    Label money = new Label(String.format(resources.getString(HUD_MONEY), player.getTotalMoney()));
    Label jail = new Label(
        String.format(resources.getString(HUD_JAIL), player.remainingJailTurns()));
    Label properties = new Label(String.format(resources.getString(HUD_PROPERTIES), player.getPropertyIndices()));
    this.getChildren().addAll(playerText, money, properties, jail);
  }

  @Override
  public Node getView() {
    return this;
  }
}
