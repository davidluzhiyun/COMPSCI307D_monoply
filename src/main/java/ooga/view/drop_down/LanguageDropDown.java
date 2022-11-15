package ooga.view.drop_down;

import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.geometry.Side;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import ooga.Main;
import ooga.view.StartView;
import ooga.view.View;

public class LanguageDropDown extends CustomizedDropDown{

  public static final String LANGUAGE_MENU_KEY = "LanguageDropDown";
  public static final String LANGUAGES_KEY = "AvailableLanguages";
  private final ResourceBundle myLanguageResources;

  public LanguageDropDown(String language) {
    super(LANGUAGE_MENU_KEY, language);
    ResourceBundle id = ResourceBundle.getBundle(Main.ID_PROPERTIES);
    myLanguageResources = ResourceBundle.getBundle(View.CHOICE_BOX_PROPERTIES);
    String image = myLanguageResources.getString(language + "Flag");
    this.setId(id.getString(LANGUAGE_MENU_KEY));
    createChoices();
    addImageBackground(image);
  }

  @Override
  public void createChoices() {
    String[] availableLanguages = myLanguageResources.getString(LANGUAGES_KEY).split(StartView.COMMA_REGEX);
    choiceBox = new ChoiceBox<>(FXCollections.observableArrayList(availableLanguages));
    choiceBox.setId("LanguageChoiceBox");
    this.getChildren().add(choiceBox);
  }

  private void addImageBackground(String imageUrl) {
    choiceBox.setBackground(new Background(
        new BackgroundImage(
            new Image(imageUrl),
            BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
            new BackgroundPosition(Side.RIGHT, 0, true, Side.BOTTOM, 0, true),
            new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, false, false)
        )));}
  }
