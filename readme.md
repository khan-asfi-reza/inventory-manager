# Inventory Management and Billing


- Product: In the problem, the company has three types of products, which are Music albums,
  Movies, and Games. So we can see at the top of the hierarchy, all of them are “Product”.
  Thus, we have a class named Product. The Product class has some data fields. Please
  take a look at the uml diagram. learn more.

- Stockable Product: We have to stock each product that means for each we can have many
  instances of a product. So, what we are doing here is, creating a class named
  StockableProduct which is keeping count of the number of instances we have stocked of a
  product by using a data field named numberofItemsStocked. This is a specialized class of
  the Product class, which means this class has all the abilities of the Product class and on
  top of that this class can do some other operations which the Product class cannot. Learn
  more.
- Game: As we have a type of product named Game, we have created a class called “Game”
  which is a specialized class of the StockableProduct class.
  Page || 2
- Music: As we have a type of product named Music, we have created a class called “Music”
  which is a specialized class of the StockableProduct class.
- Movie: As we have a type of product named Movie, we have created a class called “Movie”
  which is a specialized class of the StockableProduct class.
- Inventory: Because this is an inventory management and billing system, we must have a
  class named Inventory. This class will basically hold the StockableProducts. In this case,
  we are taking a category based management system. Here we are going to think of each
  stockable product as a type / category of product. So we are going to store the stockable
  products in the inventory. And when someone wants a product from the inventory, we are
  going to give them an instance of the product. Learn more.
  Example: Suppose, a shop has 20 Doritos. So in this case, Doritos is a product, and it has
  20 instances. We can consider Doritos as a StockableProduct, and the number of items
  stocked is 20.
- Invoice: We also have to manage billing, so for billing we have to make a list of the products
  the buyer wants to buy. For this purpose, we are using a class named Invoice. When building
  the Invoice class, we are taking an item based approach, rather than taking a categorized
  approach as the Inventory. So, here we will take instance of a category, as a product. Learn
  more.
