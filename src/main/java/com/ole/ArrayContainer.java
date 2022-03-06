package com.ole;

public class ArrayContainer implements Container{


    private static final double scaleFactor = 1.5;

    private int[] container;
    //реальное количество элементов в контейнере
    private int length;

    public ArrayContainer() {
        container = new int[10];
    }

    /**
     * Добавляет элемент в конец
     *
     * @param number Добавляемый элемент
     */
    @Override
    public void add(int number) {
        add(length-1, number);
    }

    /**
     * Добавляет элемент после заданного индекса
     *
     * @param index  Индекс элемента, за которым будет вставляться новый элемент
     * @param number Добавляемый элемент
     */
    @Override
    public void add(int index, int number) throws IndexOutOfBoundsException {
        if(index>=length || index<0) {
            throw new IndexOutOfBoundsException("Индекс выходит за пределы контейнера");
        }
        else {
            if(getCapacity()<=length) {
                expand();
            }
            for (int i = length; i > index+1; i++) {
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
        if (length - 1 - index >= 0) System.arraycopy(container, index + 1, container, index, length - 1 - index);
        length--;
        if(getCapacity()>2*length*scaleFactor)
            shrink();
        return true;
    }

    /**
     * Возвращает количество элементов в контейнере
     *
     * @return количество элементов в контейнере
     */
    @Override
    public int getSize() {
        return length;
    }

    /**
     *  Возвращает реальный размер контейнера
     * @return реальный размер контейнера
     */
    public int getCapacity() {
        return container.length;
    }

    private void expand() {
        int newCapacity = (int) (length*scaleFactor);
        int[] temporaryContainer = new int[newCapacity];
        if (length >= 0) System.arraycopy(container, 0, temporaryContainer, 0, length);
        container = temporaryContainer;
    }

    private void shrink() {
        int newCapacity = (int)(length * scaleFactor);
        int[] tempContainer = new int[newCapacity];
        if (length >= 0) System.arraycopy(container, 0, tempContainer, 0, length);
        container=tempContainer;
    }
}