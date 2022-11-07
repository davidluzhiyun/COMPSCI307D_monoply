package model;

import com.google.gson.Gson;

public interface Properties {
  /**
   * Sample return value for street
   * {
   *   "NAME": "Campus Drive",
   *   "PURCHASE_PRICE": 100,
   *   "MORTGAGE_PRICE": 50,
   *   "RENT": 6,
   *   "RENT_WITH_COLOR_SET": 12,
   *   "RENT_WITH_HOUSES": = [30, 90, 270, 400, 550],
   *   "HOUSES_COST": 100
   * }
   * Sample return value for railroad
   * {
   *   "NAME": "North Railroad",
   *   "PURCHASE_PRICE": 200,
   *   "MORTGAGE_PRICE": 100,
   *   "RENT": [25, 50, 100, 200]
   * }
   * Sample return value for utility
   * {
   *   "NAME": "Fire Plant",
   *   "PURCHASE_PRICE": 150
   *   "MORTGAGE_VALUE": 75,
   *   "DESCRIPTION": "If one Utility is owned, rent is 4 times amount shown on dice. If both Utilities are owned, rent is 10 times amount shown on dice."
   * }
   * @return
   */
  Gson getInformation();
}
