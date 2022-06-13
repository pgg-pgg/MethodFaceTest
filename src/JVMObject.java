public class JVMObject {

    private int work(int a) {
        //a就是main方法中的10，此时work方法的栈帧的局部变量表的a和main方法栈帧的操作数栈的10就是同一块内存地址
        int z = (a + 5) * 10;
        return z;
    }

    public static void main(String[] args) {
        JVMObject o = new JVMObject();
        System.out.println(o.work(10));//10 这个数字在main方法栈帧的操作数栈中
    }
}

class Person {
    private String tag = "";

    public void setTag(String tag) {
        this.tag = tag;
    }
}
