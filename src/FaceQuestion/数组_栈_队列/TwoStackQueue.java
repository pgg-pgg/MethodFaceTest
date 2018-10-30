package FaceQuestion.数组_栈_队列;

import java.util.Stack;

/**
 * 使用两个栈实现一个队列
 * 创建好两个栈，一个用于压入给定的元素，当需要弹出元素时，
 * 需要压入栈的元素全部压入到
 * 弹出栈中，由弹出栈弹出元素
 */
public class TwoStackQueue {

    private Stack<Integer> stackPush;//专门用于压入元素的栈
    private Stack<Integer> stackPop;//专门用于弹出的栈

    public TwoStackQueue(){
        stackPop=new Stack<>();
        stackPush=new Stack<>();
    }


    public void push(Integer newValue){
        stackPush.push(newValue);
    }

    public Integer Poll(){
        if (stackPush.empty()&&stackPop.empty()){
            throw new RuntimeException("栈为空");
        }else if (stackPop.empty()){
            while (!stackPush.empty()){
                stackPop.push(stackPush.pop());
            }
        }

        return stackPop.pop();
    }

    public int peek() {
        if (stackPop.empty() && stackPush.empty()) {
            throw new RuntimeException("Queue is empty!");
        } else if (stackPop.empty()) {
            while (!stackPush.empty()) {
                stackPop.push(stackPush.pop());
            }
        }
        return stackPop.peek();
    }
}
