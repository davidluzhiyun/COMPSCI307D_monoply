### Part 1
1. In terms of the Place interface, there are two versions of the interface Place and ControllerPlace, and Place extends
   ControllerPlace. ControllerPlace provides fewer available methods and less access to a place since the user of ControllerPlace
   is the controller.
2. The most abstract interface is the Place interface. The Place interface contains all the information of all places such
   as placeId, prices of rent or purchasing, and actions linked to the place.
3. The API allows for adding new types of places, and no change is needed to make in the base class as long as the new type of place
   is consistent with the contract of base class (liskov substitution principle).
4. The Place interface contains methods such as getHouseCount(), and not every type of place have that attribute (only streets).
   It will throw an exception if no house can be built on that place.

### Part 2
1. The documentation contains useful information about the functionality of the APIs. Besides, the naming of these APIs
   also make them easy to understand.
2. The name of the APIs makes sense and builds abstraction. For example, When one player buys a property, the method `getPurchasePrice()`
   will be called to get the cost of the price, then the method `updateOwner(Player player)` is called to update the owner.
3. The arguments and return value of the APIs along with the name of the API can give the user the basic understanding of 
   what the APIs are for. Some APIs throw exception such as CannotBuildHouseException which provides information to the user
   when such APIs should be used.
4. I think the API design should make it easy for the target user of the APIs spend minimal time and use it correctly. 
   I think the Place API design is good in this sense. Some model components (and controller on the ControllerPlace interface)
   can use the Place API easily.