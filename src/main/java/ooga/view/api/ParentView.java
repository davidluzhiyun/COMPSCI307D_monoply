package ooga.view.api;

import javafx.scene.Node;

/**
 * This interface allows View Components to have "children" elements.
 *
 * @param <T> extends JavaFX Node
 * @author Hosung Kim
 */
@FunctionalInterface
public interface ParentView<T> {

  /**
   * This method adds a child Node to the ParentView in a hierarchical manner.
   *
   * @param child Child Node to be added to be ParentView
   */
  void addChild(T child);
}
