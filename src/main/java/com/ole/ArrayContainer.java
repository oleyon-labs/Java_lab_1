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
     * Создает контейнер с элементами из заданного массива
     * @param array массив первоначальных значений
     */
    public ArrayContainer(int[] array) {
        int newCapacity = (int) (array.length*scaleFactor);
        container = new int[newCapacity];
        length= array.length;
        System.arraycopy(array, 0, container, 0, length);
    }

    /**
     * Добавляет элемент в конец
     *
     * @param number Добавляемый элемент
     */
    @Override
    public void add(int number) {
        //add(length-1, number);
        add(length, number);
    }

    /**
     * Добавляет элемент на место заданного индекса
     *
     * @param index  Индекс, в который будет вставляться новый элемент. Не может превышать length
     * @param number Добавляемый элемент
     */
    @Override
    public void add(int index, int number) throws IndexOutOfBoundsException {
        if(index>length || index<0) {
            throw new IndexOutOfBoundsException("Индекс выходит за пределы контейнера");
        }
        else {
            if(getCapacity()<=length) {
                expand();
            }
            for (int i = length; i > index; i--) {
                container[i]=container[i-1];
            }
            container[index]=number;
        }
        length++;
        /*if(index>=length || index<0) {
            throw new IndexOutOfBoundsException("Индекс выходит за пределы контейнера");
        }
        else {
            if(getCapacity()<=length) {
                expand();
            }
            for (int i = length; i > index+1; i--) {
                container[i]=container[i-1];
            }
            container[index+1]=number;
        }
        length++;*/
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

    /**
     * Копирует все элементы контейнера в массив
     * @return массив элементов из контейнера
     */
    public int[] ToArray() {
        if (length >= 0) {
            int[] copy = new int[length];
            System.arraycopy(container, 0, copy, 0, length);
            return copy;
        }
        return null;
    }

    private void expand() {
        int newCapacity = (int) (length*scaleFactor);
        int[] temporaryContainer = new int[newCapacity];
        if (length >= 0) System.arraycopy(container, 0, temporaryContainer, 0, length);
        container = temporaryContainer;
    }

    private void shrink() {
        int newCapacity = Math.max((int)(length * scaleFactor), 10);
        int[] tempContainer = new int[newCapacity];
        if (length >= 0) System.arraycopy(container, 0, tempContainer, 0, length);
        container=tempContainer;
    }
}