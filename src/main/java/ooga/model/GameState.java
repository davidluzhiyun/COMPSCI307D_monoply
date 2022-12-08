package ooga.model;

public enum GameState {
  DICE_RESULT,
  //only getDiceNum() in ModelOutput is updated
  MOVE,
  //only getCurrentPlaceIndex() of the current player is updated
  PAY_RENT,
  COLLECT_SALARY
}
