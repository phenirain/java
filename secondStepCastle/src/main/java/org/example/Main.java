package org.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        Castle castle = new Castle();
        castle.Welcome();
        throw new RuntimeException("This is an uncaught exception");
    }
}