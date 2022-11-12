package ooga.view.button;

import java.util.ResourceBundle;
import ooga.Main;

public class FileUploadButton extends CustomizedButton {

  public static final String FILE_UPLOAD_KEY = "FileUpload";

  public FileUploadButton(String language) {
    super(FILE_UPLOAD_KEY, language);
    ResourceBundle id = ResourceBundle.getBundle(Main.ID_PROPERTIES);
    this.setId(id.getString(FILE_UPLOAD_KEY));
  }
}
