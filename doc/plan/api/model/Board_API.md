#### Board API Overview

## Overview
* Board holds data used inside model classes
* ViewBoard holds data used for view to update

## Details
* Board holds a list of Places in the order of the board.
* ViewBoard holds a list of ViewPlaces in the order of the board.
* The main difference is that ViewBoard contains the information of what action can be done to this place (typically property)

## Considerations
* Apply polymorphism to ViewPlace.