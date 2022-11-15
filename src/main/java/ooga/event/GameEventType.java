package ooga.event;

public enum GameEventType {
  VIEW_TO_CONTROLLER_GAME_START,
  CONTROLLER_TO_MODEL_GAME_START,
  CONTROLLER_TO_VIEW_PLAYER_START,
  CONTROLLER_TO_VIEW_BOARD_SET_UP,
  VIEW_TO_CONTROLLER_ROLL_DICE,
  CONTROLLER_TO_VIEW_INIT_DATA,
  CONTROLLER_TO_VIEW_UPDATE_DATA,
  CONTROLLER_TO_MODEL_ROLL_DICE,
  MODEL_TO_CONTROLLER_DICE_ROLLED,
  CONTROLLER_TO_VIEW_ROLL_DICE,
}
