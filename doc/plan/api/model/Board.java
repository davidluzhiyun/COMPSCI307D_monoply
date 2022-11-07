package model;

import com.google.gson.Gson;

public interface Board {
  /**
   * A sample return value:
   * {
   *   "SIZE": [11, 11],
   *   "STREET": {
   *     "MEDITERRANEAN AVENUE": [1, 3],
   *     "ORIENTAL AVENUE": [6, 8, 9],
   *     ST. CHARLES PLACE: [11, 13, 14]
   *   },
   *   "UTILITIES": {
   *     "ELECTRIC COMPANY": 12,
   *     "CHEMICAL PLANT": 28
   *   },
   *   "RAILROADS": {
   *     "NORTH RAILROAD": 15,
   *     "SOUTH RAILROAD": 30
   *   },
   *   "CHANCES": [7, 18, 29],
   *   "COMMUNIST CHEST": [14, 29, 36],
   *   "TAXES": [4, 38],
   *   "GO": 0,
   *   "JAIL": 10
   * }
   */
  Gson getBoardInfo();
}
