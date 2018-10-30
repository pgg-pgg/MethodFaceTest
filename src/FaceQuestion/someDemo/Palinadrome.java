package FaceQuestion.someDemo;


/**
 * 判断一个数是否为回文数
 */
public class Palinadrome {

    /**
     * 判断是否是回文数
     * @param n
     * @return
     */
    public boolean isPalinadrome(int n){
        if (n<0){
            return false;
        }
        int help=1;
        while (n/help>10){
            help*=10;
        }

        while (n!=0){
            if (n/help!=n%10){
                return false;
            }

            n=(n%help)/10;
            help/=100;
        }

        return true;
    }
}
