package com.theironyard;

import jodd.json.JsonSerializer;
import spark.Spark;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    private static ArrayList<Product> products = new ArrayList<>();
    private static HashMap<Integer, Integer> cart = new HashMap<>();

    private static final String NEWLINE = "\r\n";

    public static void main(String[] args) {

        loadProducts();

        Spark.staticFileLocation("/public");

        Spark.init();

        Spark.get("/hello", ((request, response) -> {
            return "Hello World";
        }));


        //GET routes
        Spark.get("/products", ((request, response) -> {
            JsonSerializer serializer = new JsonSerializer();
            return serializer.deep(true).serialize(products);
        }));

        Spark.get("/get-cart", ((request, response) -> {
            JsonSerializer serializer = new JsonSerializer();
            return serializer.deep(true).serialize(cart);
        }));

        Spark.get("/get-product", ((request, response) -> {
            int id = Integer.parseInt(request.queryParams("id"));
            Product product = null;
            for(Product p : products) {
                if(p.getId() == id) {
                    product = p;
                }
            }
            JsonSerializer serializer = new JsonSerializer();
            return serializer.serialize(product);
        }));

        //POST routes
        Spark.post("/add-product", ((request, response) -> {

            int id = Integer.parseInt(request.queryParams("id"));
            if(cart.containsKey(id)) {
                cart.replace(id, cart.get(id) + 1);
            } else {
                cart.put(id, 1);
            }

            return "";
        }));

        Spark.post("/remove-product", ((request, response) -> {
            int id = Integer.parseInt(request.queryParams("id"));
            cart.remove(id);
            return "";
        }));

        Spark.post("/change-quantity", ((request, response) -> {
            int id = Integer.parseInt(request.queryParams("id"));
            int quantity = Integer.parseInt(request.queryParams("quantity"));
            cart.replace(id, quantity);
            return "";
        }));
    }

    private static void loadProducts() {
        File file = new File("products.csv");
        try {
            Scanner scanner = new Scanner(file);
            while(scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String lineParts[] = line.split(";");
                Product product = new Product();
                product.setId(Integer.parseInt(lineParts[0]));
                product.setName(lineParts[1]);
                product.setDescription(lineParts[2]);
                product.setPrice(new BigDecimal(lineParts[3]));
                product.setImageName(lineParts[4]);

                products.add(product);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error loading product file");
            e.printStackTrace();
        }
    }
}
