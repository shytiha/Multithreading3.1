package org.example;

import java.util.Random;

import static org.example.Utils.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Random random = new Random();
        String[] texts = new String[100000];
        for (int i = 0; i < texts.length; i++) {
            texts[i] = generateText("abc", 3 + random.nextInt(3));
        }

        Thread polindrome = new Thread(() -> {
            for (String text : texts) {
                if (isPalindrome(text) && !isSameChar(text)) {
                    increementCounter(text.length());
                }
            }
        });
        polindrome.start();

        Thread sameChar = new Thread(() -> {
            for (String text : texts) {
                if (isSameChar(text)) {
                    increementCounter(text.length());
                }
            }
        });
        sameChar.start();

        Thread ascendingOrder = new Thread(() -> {
            for (String text : texts) {
                if (!isSameChar(text) && isAscendingOrder(text)) {
                    increementCounter(text.length());
                }
            }
        });
        ascendingOrder.start();


        sameChar.join();
        ascendingOrder.join();
        polindrome.join();

        System.out.println("Красивых слов с длиной 3 " + counter3);
        System.out.println("Красивых слов с длиной 4 " + counter4);
        System.out.println("Красивых слов с длиной 5 " + counter5);
    }
}