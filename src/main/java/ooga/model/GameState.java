package ooga.model;

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
  GAME_SET_UP,

  NEXT_PLAYER,

  BUY_PROPERTY,

  MORTGAGE,

  GET_PLACE_ACTIONS,

  BUILD_HOUSE,

  BUILD_HOTEL,

  COMMUNITY_CARD,
}
