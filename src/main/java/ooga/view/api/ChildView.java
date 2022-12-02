package ooga.view.api;

import javafx.scene.Node;

/**
 * This functional interface is implemented by frontend elements that can be added as a child of
 * Parent Nodes.
 *
 * @param <T> extends JavaFX Node
 * @author Hosung Kim
 */
@FunctionalInterface
public interface ChildView<T extends Node> {

  /**
   * This method returns the JavaFX Node that can be added or deleted from javaFX Parent Nodes.
   *
   * @return A JavaFX Node element which is potentially added to a Parent Node.
   */
  T getView();
}
