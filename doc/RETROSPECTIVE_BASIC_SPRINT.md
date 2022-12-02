# OOGA Retrospective Discussion

### Names

Elaine Guo, Hosung Kim, David Lu, Leila Nelson, Luyao Wang

## Project's current progress

### View

* Monopoly Board is created. It is designed to take account future game editing functionality.

### Model

* Simplified game model is implemented; some communication between controller is not perfect.

## Current level of communication

* Using GroupMe to update status and notify other team members if any interface updates are
  required.

## Satisfaction with team roles

* Good. Everyone has clear roles and can fulfill the roles on time.

## Teamwork that worked well

* The view divided the work into main board/various components.

* The model use separate interfaces to control access so that the model is secure.

## Teamwork that could be improved

* The View needs to communicate more with the Controller and Model team to connect the Frontend with
  the Backend.
  Working separately on each component makes agile development possible, but the components need to
  be connected.
* In some cases, the view can talk to the model instead of through controller for simplification (events will still be used).

## Teamwork to improve next Sprint

* View is divided into two sections. Main GamePlayBoard and Various Components.
  Measuring the merge commit history for the view would be an improvement for the next sprint.
  This can be measured by counting the commit history that merged separate view components.
* The model should make sure the view and controller do not need hold extra information.