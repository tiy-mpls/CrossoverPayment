# CrossoverPayment

## Prerequisites

Make sure you have the Java SDK installed. If you are using homebrew then you can install it with `brew update && brew cask install java`.

## Running the server

Can be run with the command `java -jar CrossoverPayment.jar`

Default port is 4567. You can open http://localhost:4567 in the browser to view the site.

HTML, CSS, and JS go in `resources/public`

##Product Info
* id - int
* name - String
* description - String
* price - BigDecimal
* imageName - String

##Tax Info
* rate - BigDecimal
* total - BigDecimal
* zip - String
* subtotal - BigDecimal

## Backend Routes
`/products` - GET - returns all Product Info in JSON

`/get-cart` - GET - returns cart info as `id` and `quantity` in JSON

`/get-product` - GET - returns individual Product Info, needs `id` passed to it 

`/tax` - GET - returns Tax Info in JSON, needs `zip` and `subtotal` passed to it


`/add-product` - POST - adds a product to the cart, expects to recieve product ID as `id`

`/remove-product` - POST - removes a product from the cart, expects to receive product ID as `id`

`/change-quantity` - POST - changes quantity of the product in the card, expects product ID as `id` and new quantity as `quantity`
