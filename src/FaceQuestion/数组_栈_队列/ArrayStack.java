package FaceQuestion.数组_栈_队列;

/**
 * 使用固定长度的数组实现一个栈结构
 */
public class ArrayStack {

    Integer[] arr;
    Integer index;
    public ArrayStack(int arraySize){
        if (arraySize<0){
            throw new IllegalArgumentException("数组初始化错误");
        }

        arr=new Integer[arraySize];
        index=0;
    }

    /**
     * 返回栈顶元素但不弹出
     * @return
     */
    public Integer peek(){
        if (index==0){
            return null;
        }
        return arr[index-1];
    }

    /**
     * 弹出栈顶元素
     * @return
     */
    public Integer pop(){
        if (index==0){
            throw new ArrayIndexOutOfBoundsException("栈越界！！！");
        }
        return arr[--index];
    }

    /**
     * 压栈
     * @param obj
     */
    public void push(int obj){
        if (index==arr.length){
            throw new ArrayIndexOutOfBoundsException("栈越界！！！");
        }

        arr[index++]=obj;
    }
}
