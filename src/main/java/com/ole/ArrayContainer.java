package com.ole;

public class ArrayContainer implements Container{

    static final double scaleFactor = 1.5;

    int[] container;
    int length;

    ArrayContainer() {
        container = new int[10];
    }

    public int getCapacity() {
        return container.length;
    }

    /**
     * Добавляет элемент в конец
     *
     * @param number Добавляемый элемент
     */
    @Override
    public void add(int number) {
        if(getCapacity()<=length) {
            int newCapacity = (int) (length*scaleFactor);
            int[] temporaryContainer = new int[newCapacity];
            for (int i = 0; i < newCapacity; i++) {
                temporaryContainer[i] = container[i];
            }
            temporaryContainer[length]=number;
            container = temporaryContainer;
        }
        else {
            container[length]=number;
        }
        length++;
    }

    /**
     * Добавляет элемент по заданному индексу
     *
     * @param index  Индекс элемента, за которым будет вставляться новый элемент
     * @param number Добавляемый элемент
     */
    @Override
    public void add(int index, int number) {
        if(getCapacity()<=length) {

        }
        else {
            for (int i = length; i > index ; i++) {
                container[i]=container[i-1];
            }
            container[index]=number;
        }
        length++;
    }

    /**
     * Возвращает Значение элемента по заданному индексу
     *
     * @param index Индекс возвращаемого элемента
     * @return Значение элемента
     */
    @Override
    public int get(int index) throws IndexOutOfBoundsException {
        if(index>=length || index<0)
            throw new IndexOutOfBoundsException("Индекс выходит за пределы контейнера");
        return container[index];
    }

    /**
     * Удаляет элемент по заданному индексу
     *
     * @param index Индекс удаляемого элемента
     * @return Результат удаления
     */
    @Override
    public boolean remove(int index) {
        if(index>=length || index<0)
            return false;
        for (int i = index; i < length-1; i++) {
            container[i]=container[i+1];
        }
        length--;
        return true;
    }
}
