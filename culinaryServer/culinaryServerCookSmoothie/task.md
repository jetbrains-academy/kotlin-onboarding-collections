The last recipe is a smoothie! Let's make a fruit smoothie with berries and oranges.
In this task we will make the `Smoothie` button live.

In this task it does not matter which of the collection we will use since we will use all the possible fruits. 
As an example, you will use a list to implement the functions, but you can try a sequence afterward.

In this task, you need to implement three functions in the `Smoothie.kt` file:

- `getFruitsForSmoothie`, which returns a list of fruits for the smoothie:

    - gets baskets of all possible fruits (see `FruitType` and `FridgeImpl.getBasketOf`)
    - put each basket in the kitchen (see `KitchenImpl.put`)
    - take from each basket `capacity` number of fruits (see `KitchenImpl.put`) (see `KitchenImpl.takeFromBasket`)
    - and finally sort fruits by the amount of sugar (`sugarContent`)

- `cookSmoothie` - which accepts a list of fruits for the smoothie and cook them:

    - add each of them to the blender (see `BlenderImpl.add`)
    - and blend them in the end (see `BlenderImpl.blend`)


<div class="hint" title="Click me to view the expected state of the application after completing this task">

![Current state](../../utils/src/main/resources/images/master/chef/states/smoothie.gif)

</div>