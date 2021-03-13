package org.gregory;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

class BloomFilterTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void add() {
        BloomFilter bloomFilter = new BloomFilter(32);
        String strTest = "0123456789";
        for (int i = 0; i < 10; i++) {
            bloomFilter.add(strTest);
            System.out.println(strTest);
/*            System.out.println(bloomFilter.hash1(strTest));
            System.out.println(bloomFilter.hash2(strTest));
            System.out.println(Integer.toBinaryString(bloomFilter.getFilter()));*/
            strTest = strTest.substring(1) + strTest.charAt(0);
        }
    }

    @Test
    void addRandomValue() {
        String strTest = null;
        for (int i = 0; i < 100_000; i++) {
            BloomFilter bloomFilter = new BloomFilter(32);
            for (int j = 0; j < 10; j++) {
                strTest = String.valueOf((int) (Math.random() * 1_000_000));
                bloomFilter.add(strTest);

                System.out.printf("Value = %s  \n", strTest);
                System.out.printf("HashCode №1 = %d \n", bloomFilter.hash1(strTest));
                System.out.printf("HashCode №2 = %d  \n", bloomFilter.hash2(strTest));
                System.out.printf("BloomFilter = %s  \n", Integer.toBinaryString(bloomFilter.getFilter()));

                assertThat(bloomFilter.isValue(strTest))
                        .as("The string %s must be belong this set", strTest)
                        .isTrue();
                System.out.println("-----------------------------------------");
            }
            System.out.println("-----------------------------------------");
            System.out.println("ОБНУЛЕНИЕ");
            System.out.println("-----------------------------------------");
        }
    }

    @Test
    void isValue() {
        for (int i = 0; i < 100_000; i++) {
            BloomFilter bloomFilter = new BloomFilter(32);
            String strTest = "0123456789";
            for (int j = 0; j < 10; j++) {
                bloomFilter.add(strTest);
                System.out.println(strTest);
                System.out.println(bloomFilter.hash1(strTest));
                System.out.println(bloomFilter.hash2(strTest));
                System.out.println(Integer.toBinaryString(bloomFilter.getFilter()));
                assertThat(bloomFilter.isValue(strTest))
                        .as("The string %s must be belong this set", strTest)
                        .isTrue();
                strTest = strTest.substring(1) + strTest.charAt(0);
            }
        }
    }
}