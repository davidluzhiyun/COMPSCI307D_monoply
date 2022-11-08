package view;

public interface StartView {

  /**
   * Functionality will likely be implemented through a ChoiceBox or something similar, but
   * essentially will provide users with the ability to choose a CSS style file to apply for the
   * rest of their game experience.
   */
  public void setStyle();

  /**
   * Again, functionality will likely be implemented through a ChoiceBox. Users will be able to
   * select a language, this will ensure that all other text is displayed in the appropriate
   * langauge.
   */
  public void setLanguage();

  /**
   * Will likely implement a FileChooser that lets users upload their own configuration file to
   * start a game.
   */
  public void uploadFile();

  /**
   * Most likely called when players click a button, this will "record" the user's style & language
   * choice, their config file, and will then create the main game view screen. SHOULD DISPLAY AN
   * ERROR IF USERS TRY TO SUBMIT WITHOUT UPLOADING A FILE OR SELECTING A LANGUAGE FIRST.
   */
  public void submit();
}