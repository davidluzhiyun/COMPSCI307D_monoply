package ooga.model;

/**
 * The gamestates is included in the ModelOutput, which is sent from the model to controller for data update.
 * The controller select the data needed based on different gamestates.
 */

public enum GameState {
  DICE_RESULT,
  //only getDiceNum() in ModelOutput is updated
  MOVE,
  //stationary actions and getCurrentPlaceIndex() of the current player is updated
  PAY_RENT,
  //The amounts of money of the current player and the owner of the property are updated
  PAY_TAX,
  //The amount of money of the current player is updated
  COLLECT_SALARY,
  //The amount of money of the current player is updated
  TO_JAIL,
  // The position of the player and whether the player is in jail updated
  OUT_OF_JAIL,
  // The money the player has, the position of the player and whether the player is in jail updated
  GAME_SET_UP,
  NEXT_PLAYER,
  BUY_PROPERTY,
  MORTGAGE,
  GET_PLACE_ACTIONS,
  LOAD_BOARD,
  BUILD_HOUSE,
  COMMUNITY_CARD,
  LOAD_GAME_STATE,
  BANKRUPT,
  GAME_END
}
