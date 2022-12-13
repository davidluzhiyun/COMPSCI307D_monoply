package ooga.view.components;

import javafx.scene.control.Label;
import javafx.scene.control.OverrunStyle;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class ResizableTextLabel {

  private final double defaultFontSize = 13;
  private final Font defaultFont = Font.font(defaultFontSize);
  private double MAX_TEXT_WIDTH = 50;
  private final Label label;
  private String text;

  public ResizableTextLabel(String text) {
    label = new Label();
    this.text = text;
  }

  public ResizableTextLabel(String text, double width) {
    label = new Label();
    label.setTextOverrun(OverrunStyle.CLIP);
    this.text = text;
    this.MAX_TEXT_WIDTH = width;
  }

  public Label build() {
    label.setText(text);
    final TextField tf = new TextField(text);
    label.setFont(defaultFont);
    label.textProperty().addListener((observable, oldValue, newValue) -> {
      //create temp Text object with the same text as the label
      //and measure its width using default label font size
      Text tmpText = new Text(newValue);
      tmpText.setFont(defaultFont);

      double textWidth = tmpText.getLayoutBounds().getWidth();

      //check if text width is smaller than maximum width allowed
      if (textWidth <= MAX_TEXT_WIDTH) {
        label.setFont(defaultFont);
      } else {
        //and if it isn't, calculate new font size,
        // so that label text width matches MAX_TEXT_WIDTH
        double newFontSize = defaultFontSize * MAX_TEXT_WIDTH / textWidth - 1;
        label.setFont(Font.font(defaultFont.getFamily(), newFontSize));
      }
    });
    label.textProperty().bind(tf.textProperty());

    return label;
  }
}
