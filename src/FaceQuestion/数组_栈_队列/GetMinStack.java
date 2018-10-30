package FaceQuestion.数组_栈_队列;

import java.util.Stack;

/**
 * 实现一个特殊的栈,在实现栈的基本功能的基础上,再实现返回栈中最小元素的操作。
 * 思路：使用两个栈来实现，一个正常存储元素，一个只存储小元素（每次入栈都比较一下，比栈顶元素小就压入）
 */
public class GetMinStack {

    private Stack<Integer> stackData;
    private Stack<Integer> stackMin;

    private GetMinStack(){
        this.stackData=new Stack<>();
        this.stackMin=new Stack<>();
    }


    private void push(int newNum){
        if (this.stackMin.isEmpty()){
            this.stackMin.push(newNum);
        }else if (newNum<=this.getMin()){
            this.stackMin.push(newNum);
        }
        this.stackData.push(newNum);
    }

    public int pop() {
        if (this.stackData.isEmpty()) {
            throw new RuntimeException("Your stack is empty.");
        }
        int value = this.stackData.pop();
        if (value == this.getMin()) {
            this.stackMin.pop();
        }
        return value;
    }
    private int getMin() {
        if (this.stackMin.isEmpty()){
            throw new RuntimeException("栈溢出");
        }
        return this.stackMin.peek();
    }
}
