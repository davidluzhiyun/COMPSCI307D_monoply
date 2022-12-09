package ooga.event;

public enum GameEventType {
  VIEW_TO_CONTROLLER_GAME_START,
  VIEW_TO_CONTROLLER_ROLL_DICE,
  VIEW_TO_CONTROLLER_GET_PLACE_ACTIONS,

  VIEW_TO_CONTROLLER_GET_PLACE_INFO,

  VIEW_TO_CONTROLLER_LOAD_BOARD,

  CONTROLLER_TO_VIEW_LOAD_BOARD,

  CONTROLLER_TO_VIEW_GET_PLACE_INFO,

  CONTROLLER_TO_MODEL_GAME_START,
  CONTROLLER_TO_VIEW_PLAYER_START,
  CONTROLLER_TO_VIEW_BOARD_SET_UP,
  CONTROLLER_TO_VIEW_GET_PLACE_ACTIONS,
  CONTROLLER_TO_MODEL_ROLL_DICE,
  CONTROLLER_TO_VIEW_BUY_PROPERTY,

  CONTROLLER_TO_VIEW_MORTGAGE,

  MODEL_TO_CONTROLLER_MORTGAGE,

  MODEL_TO_CONTROLLER_DICE_ROLLED,
  CONTROLLER_TO_VIEW_ROLL_DICE,
  MODEL_TO_CONTROLLER_BOARD_SET_UP,
  MODEL_TO_CONTROLLER_UPDATE_DATA,
  MODEL_TO_CONTROLLER_GET_PLACE_ACTIONS,
  MODEL_TO_CONTROLLER_PLAYER_START,
  MODEL_TO_CONTROLLER_BUY_PROPERTY,
  VIEW_LAUNCH_GAME_SCREEN,
  VIEW_LAUNCH_GAME_SELECTION_SCREEN,
  VIEW_LAUNCH_GAME_EDITOR_SCREEN,
  VIEW_LAUNCH_BOARD_EDITOR,
}
