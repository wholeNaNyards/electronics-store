#Future Work
<hr>

* Error handling for `loadCart()` in CartService.js
* Error handling for `addToCart()` in CartService.js
* Error handling for `removeFromCart()` in CartService.js
* Anchor footer to bottom of site when scrollbar isn’t present
* Improve look and feel of empty Cart page
* Improve look and feel of empty Products page
* Improve look and feel of Under Construction page
* Improve look and feel of Home page
* Remove `updateFilters()` from ProductFilterController.js
    * Can be refactored to remove need for it, let other methods handle the updating
* Create custom message converter to bring back item count and subtotal instead of hard coding into first item - This will also remove the need for multiple queries
* Write Javadocs
* Implement product rating system
* Integrate Spring Security
* Implement user login
    * Allow users to log in and stay logged in. This will allow each user to have their own cart
    * Will also remove the hard coded requests to the database, such as the get cart request: `/users/1/items` in ProductService.js
* Implement administration section to create new products
    * Allow images to be uploaded, or select from existing ones
