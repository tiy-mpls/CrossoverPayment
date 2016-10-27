# CrossoverPayment

HTML, CSS, and JS go in `resources/public`

Can be run with the command `java -jar CrossoverPayment.jar` 
Default port is 4567

##Product Info
id - int
name - String
description - String
price - BigDecimal
imageName - String

##Routes
`/products` - GET - returns all product info in JSON
`/get-cart` - GET - returns cart info as `id` and `quantity` in JSON
`/get-product` - GET - returns individual product info, needs `id` passed to it 

`/add-product` - POST - adds a product to the cart, expects to recieve product ID as `id`
`/remove-product` - POST - removes a product from the cart, expects to receive product ID as `id`
`/change-quantity` - POST - changes quantity of the product in the card, expects product ID as `id` and new quantity as `quantity`
