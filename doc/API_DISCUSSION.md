# API Discussion
### Names


## Collections Discussion

* What is the purpose of each interface implemented by LinkedList?
  * `LinkedList` implements `Serializable`, `Cloneable`, `Iterable<E>`, `Collection<E>`, `Deque<E>`, `List<E>`, `Queue<E>`
  * `Serializable` is a marker interface deciding if instances can be Serialized or Deserialized.
  * `Cloneable` is a marker interface deciding if instances can be cloned instead of using a new operator.
  *  A class implementing `Iterable<E>` allows the instance of this class to be iterated.
  *  A class implementing `Collection<E>` can have a group of objects, known as its elements.
  *  A class implementing `Deque<E>` supports the addition or removal of elements from either end of the data structure.
  *  A class implementing `List<E>` is an ordered collection.
  *  A class implementing `Queue<E>` is an ordered collection used to hold the elements in some order, typically FIFO.
* What is the purpose of each superclass of HashMap?
  * `HashMap<K,V>` extends `AbstractMap<K,V>`, which provides a skeletal implementation of the Map interface, to minimize
  the effort required to implement this interface (JavaDoc).
  * `AbstractMap<K,V>` extends `Object`, which is the root of the class hierarchy that every class extends.

* How many different implementations are there for a Set?
  * `AbstractSet`, `ConcurrentHashMap.KeySetView,` `ConcurrentSkipListSet`, `CopyOnWriteArraySet`, `EnumSet`, `HashSet`, 
  `JobStateReasons`, `LinkedHashSet`, `TreeSet`, 9 classes in total.
  * The number justifies it being an interface because these 9 classes all share the same functionalities provided by 
  the interface, but the ways these functionalities are implemented are volatile.
* What methods are common to all collections?
  * `add(E e)`, `clear()`, `contains(Object o)`, `isEmpty()`, `iterator()`, `remove(Object o)`, `size()`, ... These are all
  very common methods that we may use on a `List<E>`.

* What methods are common to all Queues?
  * `add(e)`, `offer(e)`, `remove()`, `poll()`, `element()`, `peek()`

* What is the purpose of the collection utility classes?
  * The `Collections` utility class holds static methods that operate on or return collections. It is highly probable that these methods
  are going to be reused, so a non-instantiable util class holding these static methods are created. However, it might not
  be good design to add that functionality directly to the collection types themselves because the methods in `Collections` is not 
  specific to one particular type of Collection. Instead, these algorithms that can be used on any object implementing the `Collection<E>`
  interface, although some methods take a set or map as parameter.

* What is wrong with the design of the Stack class?
  * `Stack<E>` class probably should not extend `Vector` because `Satck` should not have methods like `add(E e)` or `remove()`.
  As mentioned in the JavaDoc in `Stack<E>`, a more complete and consistent set of LIFO stack operations is provided by the `Deque<E>` interface.

* What makes this a good API?
  * A good API should be easy to learn and hard to misuse - it should be sufficient to take a brief look at the documentation and know how to use the API.
  * A good API should also be open for extension - you can build more specific features based on APIs.

* In what can this API be improved?
  * A `Stack<E>` is not `Vector<E>`, so it should not extend `Vector<E>`, so composition could be preferable. Alternatively, create an interface for stack
  that implements `Collection` and make `add(E e)` add to the last.


## Cell Society

#### Existing Simulation API

* Classes and Methods
  * Class `Model`, external API 
    ```JAVA 
    public void computeStates()
    ```
    Used by the controller to compute and return the newest states of the cells.
  * Class `Cell`, internal API
    ```JAVA 
    public void setFutureState(List<Cell> neighbors)
    ```
    Used by the model to set its future states based on its neighbors.
  * Class `FireCell`, not an API
    ```JAVA 
    private void setBURNING()
    ```
    Used inside its own class.


#### Selecting and running a simulation

* English description
  * The frontend get the grid from controller using external API and requires the model to update it periodically. Then the 
  frontend using its internal API to display the grid in some user-friendly way.
  * Classes and Methods
    The controller reads from configuration file and create a model based on that simulation type
    ```JAVA 
    public GridWrapper getViewGrid()
    ```
    ```JAVA 
    public GridWrapper updateGrid()
    ```
    The view gets the grid and requires the controller to update the grid.
    ```JAVA 
    public GridWrapper updateGrid()
    ```
    The controller asks the model to update and return the states.