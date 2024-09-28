Let's practice! Let's start with the familiar approach with collections cook the tomato soup from the previous lesson with a _list_ of tomatoes.
In this task we will make the `Cook Tomato Soup` button live.

In this task, you need to implement two functions in the `TomatoSoup.kt` file:

- `getTomatoesForSoup` - which get `NUMBER_OF_TOMATOES` tomatoes from the _fridge_ (a `VegetableType` with type `VegetableType.Tomato`), see `FridgeImpl` class to find the nessesary API
- `prepareTomatoes` - that accept a _list_ of tomatoes, and do the following with each item frm the list:

  - put it into the kitchen (see `KitchenImpl.put`)
  - cut each element (see `KitchenImpl.cut`)
  - take each cut tomato (see `KitchenImpl.take`)
  - put each tomato into the pot (see `PotImpl.put`)

<div class="hint" title="Click me to view the expected state of the application after completing this task">

![Current state](TODO)

</div>
