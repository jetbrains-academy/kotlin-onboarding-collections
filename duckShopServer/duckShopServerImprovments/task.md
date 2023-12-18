Congratulations! You did a great job and created a working application.
We have put together a few ideas on how you can further improve this project by yourself.
These improvements will not be tested within the course.
Some improvements require changes to both the client (what is displayed in the browser)
and the server (the application logic).
We don't cover the client-server architecture in this course,
so you can either explore that on your own or implement ideas that don't require investigating third-party code.

**Server improvements:**

- Currently, the ducks are hardcoded in the `Duck` enum class.
  As an improvement, you can add generation for different kinds of ducks.
- You can add more complex logic for adding, removing, or shuffling ducks.
- Currently, we lose the application state if we turn off the server.
  You can implement the ability to save the current state of the duck shop in files,
  and when the server is starting, you can extract this data.


**Client improvements:**

- To be able to show generated ducks in a correct way, you can improve the client logic and generate
a duck picture by duck's properties, e.g. color, or set of accessories.
- You can make alive the duck shop via adding animations for all actions.

<p align="center">
    <img src="../../utils/src/main/resources/images/duck/shop/finish.svg" alt="Possible improvments" width="400"/>
</p>