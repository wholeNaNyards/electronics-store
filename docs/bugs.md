#Bugs

* Accuracy of backend subtotal to frontend subtotal for Cart Page
    * Especially noticeable when removing products from a User’s cart on Cart Page
* Remove From Cart button click on Cart Page does not shift other products into the current page and breaks pagination when removing the last product on a page
* Broken Image on Products Page load
    * Check console output on page load - No images are broken but a bad request occurs
* Desktop vs Mobile search bar values
    * Perform a search on desktop, switch to mobile view - The mobile search bar will be empty but the previous search value will still be on the filter
