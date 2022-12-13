## OOGA API Changes
### Team: Ragtag, Team 3
### Names: Elaine Guo, Hosung Kim, David Lu, Leila Nelson, Luyao Wang


#### API #1
ModelOutput

* Method changed: getGameState()

    * Why was the change made?
      * This change was included so that the model is still able to simplify its event output and still have the granularity of creating events on specific actions that are being done.

    * Major or Minor (how much they affected your team mate's code)
      * Major

    * Better or Worse (and why)
      * Better
        * As previously stated, this allows the model to keep the ModelOutput interface as the output of any event it creates, but the gamestate allows the controller to see what is actually changed.


* Method changed: getQueryIndex()

    * Why was the change made?
      * This change was included so that the controller will be able to know what place a user is asking for when a place action is being taken.

    * Major or Minor (how much they affected your team mate's code)
      * Major

    * Better or Worse (and why)
      * Better
        * This change allows the controller to implement multiple place actions easier and makes it so that the controller has to store less information permanently.


#### API #2
ControllerPlace

* Method changed: getPlayers()

    * Why was the change made?
      * This method is no longer needed since it was never used.

    * Major or Minor (how much they affected your team mate's code)
      * Minor

    * Better or Worse (and why)
      * Better
        * Deprecating this method reduces unnecessray code and this method is also detrimental to the design if we actually update this every time a player moves.


* Method changed: getColorSetId()

    * Why was the change made?
      * This method was changed to throw an exception, so that what calls this method knows whether or not the place belongs to a colorset.

    * Major or Minor (how much they affected your team mate's code)
      * Minor

    * Better or Worse (and why)
      * Better (slightly)
        * As previously stated, this allows for what is using this place to check whether or not it actually belongs/has a colorsetId.


#### API #3
Controller

* Method changed: onGameEvent(GameEvent event)

    * Why was the change made?
      * This change was made to allow specific events to be handled in different manners.

    * Major or Minor (how much they affected your team mate's code)
      * Minor

    * Better or Worse (and why)
      * I would say it is both better and worse. It is better in a sense that it allows the controller to implement for functionality; however, it is worse in the sense that it does not allow for generalization since the change added some "hard coded" event listening.


* Method changed: getControllerPlaces()

    * Why was the change made?
      * This change was included so that other classes, specifically runnables, are able to access the places stored in the controller.

    * Major or Minor (how much they affected your team mate's code)
      * Minor

    * Better or Worse (and why)
      * From a design perspective, I would say that this makes the design worse since it is a public getter method. However, it does allow the functionality of important features.


#### API #4

* Method changed:

    * Why was the change made?

    * Major or Minor (how much they affected your team mate's code)

    * Better or Worse (and why)


* Method changed:

    * Why was the change made?

    * Major or Minor (how much they affected your team mate's code)

    * Better or Worse (and why)
