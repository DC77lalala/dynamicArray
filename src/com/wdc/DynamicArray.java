package com.wdc;

//动态数组


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Consumer;
import java.util.stream.IntStream;

public class DynamicArray implements Iterable<Integer>{

    @Test
    @DisplayName("测试删除")
    public void test(){
        DynamicArray dynamicArray = new DynamicArray();
        dynamicArray.addLast(1);
        dynamicArray.addLast(2);
        dynamicArray.addLast(3);
        dynamicArray.addLast(4);
        dynamicArray.addLast(4);
        dynamicArray.addLast(4);
        dynamicArray.addLast(4);
        dynamicArray.addLast(4);
        dynamicArray.addLast(4);
        dynamicArray.addLast(4);
        dynamicArray.addLast(4);
        dynamicArray.addLast(4);
        dynamicArray.add(2,5);
        int removed = dynamicArray.remove(2);



    }


    private int capacity = 8; //容量
    private int size = 0;  //逻辑大小
    private int[] array = {};





    /**
     * 添加到尾部
     * @param element  待添加的数据
     */
    public void addLast(int element){
//        array[size] = element;
//        size++;
        add(size,element);
    }

    /**
     * 用索引来添加数组
     * @param index 想添加的索引位置
     * @param element 待添加的数据
     */
    public void add(int index,int element){
        checkAndGrow();


        //添加逻辑
        if (index < 0 || index > size)  throw new IllegalArgumentException("index 不合理");

        if (index >= 0 && index < size){
            System.arraycopy(array,index,array,index+1,size - index);

        }
        array[index] = element;
        size++;
    }

    private void checkAndGrow() {
        //容量检查
        if (size == 0){
            array = new int[capacity];
        }else if (size == capacity){
            //进行扩容  （1.5倍）
            capacity += capacity >> 1;
            int[] newArray = new int[capacity];
            System.arraycopy(array,0,newArray,0,size);
            array = newArray;

        }
    }

    /**
     *  获取index 索引的元素
     * @param index 索引
     * @return  对应的数据
     */
    public int get(int index){
        return array[index];
    }


    /**
     * 函数式接口遍历数组，
     * @param consumer  - 遍历要执行的操作，入参：每个元素
     */
    public void foreach(Consumer<Integer> consumer){
        for (int i = 0; i < size; i++) {
            //提供 array[i]
            //返回 void
           consumer.accept(array[i]);
        }
    }


    //使用迭代器遍历
    @Override
    public Iterator iterator() {

        return new Iterator() {
            int i = 0;
            @Override
            public boolean hasNext() {  //是否有下一个元素
                return i < size;
            }

            @Override
            public Integer next() {  //返回当前元素，并指向下一个元素
                return array[i++];  //先返回后自增
            }
        };
    }

    //使用流来遍历
    public IntStream stream(){
        return IntStream.of(Arrays.copyOfRange(array,0,size));
    }


    /**
     *  删除元素
     * @param index  要删除元素的索引
     * @return  删除的元素
     */
    public int remove(int index){
        int removed = array[index];

        if (index < size -1){
            //变化数组范围，让数组前移
            System.arraycopy(array,index + 1,array,index,size - index - 1);

        }
        size--;


        return removed;
    }

}
