package ooga;

import java.lang.reflect.InvocationTargetException;

/**
 * @author Leila
 * <p>
 * Used to create objects via reflection. NOTE: I borrowed some of my code that I wrote from the
 * cellsociety project.
 */
public class Reflection {

  public Object makeObject(String className, Class<?>[] constructorClasses, Object[] parameters) {
    Object o = null;
    try {
      Class<?> clazz = Class.forName(className);
      o = clazz.getDeclaredConstructor(constructorClasses)
          .newInstance(parameters);
    } catch (NoSuchMethodException | IllegalAccessException | InstantiationException |
             InvocationTargetException | ClassNotFoundException e) {
      throw new RuntimeException(e);
    }
    return o;
  }
}
