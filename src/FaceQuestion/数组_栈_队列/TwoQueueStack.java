package FaceQuestion.数组_栈_队列;


import java.util.LinkedList;
import java.util.Queue;

/**
 * 使用队列完成栈结构
 * 使用两个队列不断的倒进倒出来实现
 * 创建两个队列1,2
 * 入队时正常入其中一个队，弹出时，将队尾元素保留,前面元素压入到另一个队列中
 */
public class TwoQueueStack {

    private Queue<Integer> queue;
    private Queue<Integer> help;

    public TwoQueueStack(){
        queue=new LinkedList<>();
        help=new LinkedList<>();
    }

    public void push(int pushInt){
        queue.add(pushInt);
    }

    public int peek(){
        if (queue.isEmpty()){
            throw new RuntimeException("栈为空");
        }
        while (queue.size()!=1){
            help.add(queue.poll());
        }

        int res=queue.poll();
        help.add(res);
        swap();
        return res ;
    }

    public int poll(){
        if (queue.isEmpty()){
            throw new RuntimeException("栈为空");
        }

        while (queue.size()>1){
            help.add(queue.poll());
        }
        int res=queue.poll();
        swap();
        return res;
    }

    //交换一下help和queue的引用
    private void swap(){
        Queue<Integer> tmp=queue;
        queue=help;
        help=tmp;
    }
}
