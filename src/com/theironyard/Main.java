package com.theironyard;

import jodd.json.JsonParser;
import jodd.json.JsonSerializer;
import spark.Spark;

import java.io.*;
import java.math.BigDecimal;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

    protected static  ArrayList<Product> products = new ArrayList<>();
    protected static HashMap<Integer, Integer> cart = new HashMap<>();

    private static final String NEWLINE = "\r\n";

    private static final String AVALARA_API_KEY = "Dw2thiPOXnnFliIbDP%2BlpJl%2F%2BReqHDLqdI9Ho9f0%2Fu580RTtGZKMVH8fTgiUmuKyTdPcqWsVXBUVYsxqyUO7xw%3D%3D";

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
            Product product = getProduct(id);
            JsonSerializer serializer = new JsonSerializer();
            return serializer.serialize(product);
        }));

        Spark.get("/tax", ((request, response) -> {
            String zipCode = request.queryParams("zip");
            BigDecimal subTotal = new BigDecimal(request.queryParams("subtotal"));

            TotalTaxRate totalTaxRate = getTaxInfo(zipCode);

            FinalCost finalCost = new FinalCost(subTotal, zipCode, totalTaxRate.getTotalRate());

            JsonSerializer serializer = new JsonSerializer();
            return serializer.serialize(finalCost);
        }));

        //POST routes
        Spark.post("/add-product", ((request, response) -> {
            int id = Integer.parseInt(request.queryParams("id"));
            addProductToCart(id);
            return "";
        }));

        Spark.post("/remove-product", ((request, response) -> {
            int id = Integer.parseInt(request.queryParams("id"));
            removeProductFromCart(id);
            return "";
        }));

        Spark.post("/change-quantity", ((request, response) -> {
            int id = Integer.parseInt(request.queryParams("id"));
            int quantity = Integer.parseInt(request.queryParams("quantity"));
            changeProductQuantityInCart(id, quantity);
            return "";
        }));
    }

    protected static void changeProductQuantityInCart(int id, int quantity) {
        cart.replace(id, quantity);
    }

    protected static void removeProductFromCart(int id) {
        cart.remove(id);
    }

    protected static void addProductToCart(int id) {
        if(cart.containsKey(id)) {
            cart.replace(id, cart.get(id) + 1);
        } else {
            cart.put(id, 1);
        }
    }

    protected static Product getProduct(int id) {
        Product product = null;
        for(Product p : products) {
            if(p.getId() == id) {
                product = p;
            }
        }
        return product;
    }

    protected static TotalTaxRate getTaxInfo(String zipCode) throws IOException {
        URL userInfoUrl = new URL(String.format("https://taxrates.api.avalara.com:443/postal?country=usa&postal=%s&apikey=%s", zipCode, AVALARA_API_KEY));
        URLConnection uc = userInfoUrl.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
        StringBuilder sb = new StringBuilder();
        while(in.ready()) {
            sb.append(in.readLine());
        }

        JsonParser parser = new JsonParser();
        TotalTaxRate totalTaxRate = parser.parse(sb.toString(), TotalTaxRate.class);

        return totalTaxRate;
    }

    protected static void loadProducts() {
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
