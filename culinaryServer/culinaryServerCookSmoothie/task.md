The last recipe is a smoothie! Let's make a delicious fruit smoothie with berries and oranges.
In this task, we'll make the `Smoothie` button live.

Currently, it doesn't matter which collection we use since we'll be using all available fruits. 
As an example, you will use a list to implement the functions, but you can try a sequence afterward.

In this task, you need to implement three functions in the `Smoothie.kt` file:

- `getFruitsForSmoothie` – returns a list of fruits for the smoothie:

    - Retrieve baskets of all available fruits (see `FruitType` and `FridgeImpl.getBasketOf`).
    - Place each basket in the kitchen (see `KitchenImpl.put`).
    - Take the specified `capacity` of fruits from each basket (see `KitchenImpl.takeFromBasket`).
    - Finally, sort the fruits by their sugar content (See `sugarContent` in the enum class `FruitType`).

- `cookSmoothie` – accepts a list of fruits for the smoothie and cooks them:

    - Add each fruit to the blender (see `BlenderImpl.add`).
    - Blend the ingredients together (see `BlenderImpl.blend`).


<div class="hint" title="Click me to view the expected state of the application after completing this task">

![Current state](../../utils/src/main/resources/images/master/chef/states/smoothie.gif)

</div>

If you have any difficulties, **hints will help you solve this task**.

----

### Hints

<div class="hint" title="Click me to learn how to use API functions">

The game has the `GameEnviroment.kt` file which contains _instances_ of all possible objects, e.g.
it has `kitchen` variable for `KitchenImpl` and to use `KitchenImpl` API you need to call it with `kitchen`:
```kotlin
kitchen.put(...)
```
</div>

