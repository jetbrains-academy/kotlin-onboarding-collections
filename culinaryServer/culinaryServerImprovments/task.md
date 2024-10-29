Congratulations! You did a great job and created a working application.
We have put together a few ideas on how you could further improve this project on your own.
These improvements will not be assessed as part of the course.
Some of them may require changes to both the client side (what is displayed in the browser)
and the server side (the application logic).
As we don't cover the client-server architecture in this course,
you could either explore it on your own or implement ideas that don't require investigating third-party code.

<div class="hint" title="Click me to learn how to change the ode of the application">

To change the mode of the application and cok different recipes, you need to go to the `GameResource.kt` file 
and change the body of the `getCurrentTask` function, possible values:

- `Task.SOUP.toString()` - the tomato soup recipe
- `Task.SALAD.toString()` - the salad recipe
- `Task.SMOOTHIE.toString()` - the smoothie recipe

</div>

**Server improvements:**

- You could add more types of fruits and vegetables for the recipes.
- You could add new recipes to cook something else.
- You could add more complex logic to the recipes like food generation 
or using more ingredients for cooking, mix the possible actions, and these changes will be applied automatically on the clien side.


**Client improvements:**

- Adding new types of fruits and vegetables for the recipes requires improvements to both the server and the client — you need
  to upload new pictures to the client side and handle them.
- Adding new recipes requires improvements to both the server and the client — you need
to add more buttons and add handlers to process them.

<p align="center">
    <img src="../../utils/src/main/resources/images/old/school/finish.svg" alt="Possible improvments" width="200"/>
</p>