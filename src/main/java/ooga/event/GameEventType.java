package ooga.event;

public enum GameEventType {
  VIEW_TO_CONTROLLER_GAME_START,

  VIEW_TO_CONTROLLER_ROLL_DICE,

  VIEW_TO_CONTROLLER_GET_PLACE_ACTIONS,

  CONTROLLER_TO_MODEL_GAME_START,

  CONTROLLER_TO_VIEW_PLAYER_START,
  CONTROLLER_TO_VIEW_BOARD_SET_UP,

  CONTROLLER_TO_VIEW_INIT_DATA,
  CONTROLLER_TO_VIEW_UPDATE_DATA,

  CONTROLLER_TO_VIEW_GET_PLACE_ACTIONS,

  CONTROLLER_TO_MODEL_ROLL_DICE,
  MODEL_TO_CONTROLLER_DICE_ROLLED,
  CONTROLLER_TO_VIEW_ROLL_DICE,
  VIEW_LAUNCH_GAME_SCREEN,
  VIEW_LAUNCH_GAME_EDITOR_SCREEN,

  MODEL_TO_CONTROLLER_BOARD_SET_UP,

  MODEL_TO_CONTROLLER_UPDATE_DATA,

  MODEL_TO_CONTROLLER_GET_PLACE_ACTIONS,

  MODEL_TO_CONTROLLER_PLAYER_START,
}
