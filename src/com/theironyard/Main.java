package com.theironyard;

import spark.Spark;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Spark.staticFileLocation("/public");

        Spark.init();

        Spark.get("/hello", ((request, response) -> {
            return "Hello World";
        }));
    }
}
