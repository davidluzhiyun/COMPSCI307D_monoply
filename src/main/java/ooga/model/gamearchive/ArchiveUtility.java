package ooga.model.gamearchive;

import ooga.model.player.AddOneDiceRoll;
import ooga.model.player.AddOneDiceRollJail;
import ooga.model.player.CanBuildOn;
import ooga.model.player.Player;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ResourceBundle;

import static ooga.model.GameModel.DEFAULT_RESOURCE_PACKAGE;
import static ooga.model.player.CanBuildOn.PLAYER_PACKAGE_NAME;

/**
 * Static utility class for the ease of factory pattern to create a player.
 * AddOneDiceRoll is the interface which decides the behavior when a player gains a new dice roll chance (go to jail after 3 consequent doubles or not)
 * CanBuildOn is the interface which decides whether a player can build house can that street (has to own all the streets of same color or not)
 */

public class ArchiveUtility {
  private static final String houseCheckerToken = "HouseBuildChecker";
  private static final String addOneDiceRollToken = "AddOneDiceRoll";
  private static final Logger LOG = LogManager.getLogger(InitialConfigLoader.class);
  private static ResourceBundle myResources = ResourceBundle.getBundle(DEFAULT_RESOURCE_PACKAGE + "Model");
  public static  AddOneDiceRoll createAddOneDiceRollJail(boolean goJailOrNot, Player player) {
    AddOneDiceRoll addOneDiceRollJail;
    Class<?> addOneDiceRollJailClass;
    try {
      addOneDiceRollJailClass = Class.forName(PLAYER_PACKAGE_NAME + myResources.getString(addOneDiceRollToken + goJailOrNot));
    } catch (ClassNotFoundException e) {
      LOG.warn(e);
      throw new IllegalStateException("classNotFound", e);
    }
    Constructor<?>[] makeNewPlace = addOneDiceRollJailClass.getConstructors();
    try {
      addOneDiceRollJail = (AddOneDiceRoll) makeNewPlace[0].newInstance(player);
    } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
      LOG.warn(e);
      throw new RuntimeException(e);
    }
    return addOneDiceRollJail;
  }

  public static CanBuildOn createHouseBuildChecker(boolean checkColorOrNot) {
    CanBuildOn checker;
    Class<?> checkerClass;
    try {
      checkerClass = Class.forName(PLAYER_PACKAGE_NAME + myResources.getString(houseCheckerToken + checkColorOrNot));
    } catch (ClassNotFoundException e) {
      LOG.warn(e);
      throw new IllegalStateException("classNotFound", e);
    }
    Constructor<?>[] makeNewPlace = checkerClass.getConstructors();
    try {
      checker = (CanBuildOn) makeNewPlace[0].newInstance();
    } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
      LOG.warn(e);
      throw new RuntimeException(e);
    }
    return checker;
  }
}
