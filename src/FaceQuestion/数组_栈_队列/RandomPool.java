package FaceQuestion.数组_栈_队列;

import java.util.HashMap;

/**
 * 设计RandomPool结构
 * 设计一种结构,在该结构中有如下三个功能:
 * insert(key):将某个key加入到该结构,做到不重复加入。
 * delete(key):将原本在结构中的某个key移除。
 * getRandom():等概率随机返回结构中的任何一个key。
 *
 *
 * 思路：
 * 使用两个hashmap，HashMap<T,Integer> keyIndexMap和HashMap<Integer,T> indexKeyMap
 */
public class RandomPool<K> {


    private HashMap<K, Integer> keyIndexMap;
    private HashMap<Integer, K> indexKeyMap;
    private int size;

    public RandomPool() {
        this.keyIndexMap = new HashMap<>();
        this.indexKeyMap = new HashMap<>();
        //标记hashmap中存储的所有记录个数，同时保证HashMap中Integer为连续的正整数，我们就可以通过这个size来等概率返回元素
        this.size = 0;
    }

    public void insert(K key){
        if (!this.keyIndexMap.containsKey(key)){//保证不重复添加
            this.keyIndexMap.put(key,this.size);
            this.indexKeyMap.put(this.size++,key);
        }
    }

    /**
     * 删除时，将最后一条记录和要删除的记录交换，然后删除最后一条记录，可以保证下标连续不间断，从而保证等概率返回
     * @param key
     */
    public void delete(K key){
        if (this.keyIndexMap.containsKey(key)){
            int deleteIndex=this.keyIndexMap.get(key);//获取要删除元素的下标
            int lastIndex=--this.size;
            K lastKey=this.indexKeyMap.get(lastIndex);//获取最后一个元素
            this.keyIndexMap.put(lastKey,deleteIndex);//把最后一个元素的下标赋值到最后一个元素的下标
            this.indexKeyMap.put(deleteIndex,lastKey);//把最后一个元素赋值到要删除元素
            this.keyIndexMap.remove(key);//删除key
            this.indexKeyMap.remove(lastIndex);//删除最后一条记录

        }
    }

    /**
     *
     * 等概率返回一个key
     * @return
     */
    public K getRandom() {
        if (this.size == 0) {
            return null;
        }
        int randomIndex = (int) (Math.random() * this.size);
        return this.indexKeyMap.get(randomIndex);
    }

}
