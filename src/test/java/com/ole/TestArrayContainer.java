package com.ole;

import com.ole.ArrayContainer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Random;

public class TestArrayContainer {
    @Test
    @DisplayName("Тест всевозможных вариантов добавления в контейнер")
    void testAdd(){
        ArrayContainer container = new ArrayContainer();
        container.add(5);
        container.add(6);
        container.add(7);
        container.add(0,10);
        container.add(2,12);
        container.add(5,11);

        int[] ideal = new int[]{10,5,12,6,7,11};
        int[] result = container.ToArray();

        Assertions.assertArrayEquals(ideal, result);

    }

    @Test
    @DisplayName("Тест удаления эдементов из контейнера")
    void testRemove() {
        int[] startingArray= new int[] {1,2,3,4,5,6,7,8};
        ArrayContainer container = new ArrayContainer(startingArray);
        container.remove(3);
        container.remove(0);
        container.remove(5);

        int[] ideal = new int[] {2,3,5,6,7};
        int[] result = container.ToArray();

        Assertions.assertArrayEquals(ideal, result);
    }

    @Test
    @DisplayName("Тест на большом количестве данных")
    void testExpandShrink() {
        ArrayContainer container = new ArrayContainer();
        container.add(10);
        container.add(20);
        Random random = new Random();
        int n=1000;
        for (int i = 0; i < n-2; i++) {
            container.add(1, random.nextInt());
        }

        Assertions.assertEquals(10, container.get(0));
        Assertions.assertEquals(20, container.get(container.getSize()-1));

        //удаляем центральные кроме 1
        for (int i = 0; i < n-3; i++) {
            container.remove(1);
        }

        Assertions.assertEquals(10, container.get(0));
        Assertions.assertEquals(20, container.get(2));

        //удаляем по краям
        int middle = container.get(1);
        container.remove(0);
        container.remove(container.getSize()-1);

        Assertions.assertEquals(middle, container.get(0));

    }
}
