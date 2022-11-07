### Design goals
* Use abstraction to create a generic game state so that it can be easily modified
and extended upon
* Reduce redundant code to make the overall design good

#### Primary Architecture
* The game will be split into three main components: model, view and controller
* All of the 3 components will be closed to each other
  * Main communication will be done through event handling, not public methods
* The abstractions used will be derived from each game component, focusing on creating abstractions based on functionality and similarity
  * Some will be based upon the genre's/game variation's main similarities (e.g. the use of a board)
  * Thus, extending upon the game will be simple because adding components will be implementing the correlating abstractions
* APIs will be focused on interacting with classes in each component, not between the components


