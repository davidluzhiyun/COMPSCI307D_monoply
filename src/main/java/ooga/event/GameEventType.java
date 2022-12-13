package ooga.event;

public enum GameEventType {
  VIEW_TO_CONTROLLER_GAME_START,

  VIEW_TO_MODEL_GET_PLACE_ACTIONS,
  VIEW_TO_MODEL_ROLL_DICE,

  VIEW_TO_CONTROLLER_GET_PLACE_INFO,

  VIEW_TO_CONTROLLER_LOAD_BOARD,
  VIEW_TO_MODEL_PURCHASE_PROPERTY,

  CONTROLLER_TO_CONTROLLER_ROW_COL,

  CONTROLLER_TO_MODEL_CHECK_PLACE_ACTION,

  CONTROLLER_TO_VIEW_LOAD_BOARD,

  CONTROLLER_TO_VIEW_LOAD_GAME_STATE,

  CONTROLLER_TO_VIEW_GET_PLACE_INFO,

  CONTROLLER_TO_MODEL_GAME_START,
  CONTROLLER_TO_VIEW_PLAYER_START,
  CONTROLLER_TO_VIEW_START_GAME,
  CONTROLLER_TO_VIEW_GET_PLACE_ACTIONS,
  CONTROLLER_TO_VIEW_BUY_PROPERTY,

  CONTROLLER_TO_VIEW_MORTGAGE,

  CONTROLLER_TO_VIEW_ROLL_DICE,

  CONTROLLER_TO_VIEW_MOVE,

  CONTROLLER_TO_VIEW_PAY_RENT,

  CONTROLLER_TO_VIEW_PAY_TAX,

  CONTROLLER_TO_VIEW_COLLECT_SALARY,

  CONTROLLER_TO_VIEW_BUILD_HOUSE,
  CONTROLLER_TO_VIEW_COMMUNITY_CARD,

  CONTROLLER_TO_VIEW_TO_JAIL,

  CONTROLLER_TO_VIEW_OUT_OF_JAIL,

  CONTROLLER_TO_VIEW_BANKRUPTCY,

  MODEL_TO_CONTROLLER_UPDATE_DATA,
  VIEW_LAUNCH_GAME_SCREEN,
  VIEW_LAUNCH_GAME_SELECTION_SCREEN,
  VIEW_LAUNCH_GAME_EDITOR_SCREEN,
  VIEW_LAUNCH_BOARD_EDITOR,
  VIEW_POST_ACTION_DRAW_BOARD,
  MODEL_TO_MODEL_DICE_RESULT,
  MODEL_TO_MODEL_COLLECT_SALARY,
  MODEL_TO_MODEL_PAY_TAX,
  MODEL_TO_MODEL_MOVE,
  MODEL_TO_MODEL_PAY_RENT,
  VIEW_TO_MODEL_END_TURN
}
