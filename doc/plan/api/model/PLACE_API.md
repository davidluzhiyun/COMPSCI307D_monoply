#### Place API Overview

## Overview
* Place holds data used inside model classes

## Details
* Place has 3 direct subinterfaces, Property, UncertaintyPlace, and Jail.
* Property has 3 direct subinterfaces, Railroad, Street, and Utility.
* UncertaintyPlace has 3 direct subinterfaces, Chance and CommunistChest.
* Some fields in Property such as purchasePrice and Rent are read from .json file according to the id of the place.

## Considerations
* How the actions of Chance is implemented