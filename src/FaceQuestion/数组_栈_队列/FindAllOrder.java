package FaceQuestion.数组_栈_队列;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FindAllOrder {
    public ArrayList<String> Permutation(String str) {
        List<String> res=new ArrayList<>();
        if (str!=null&&str.length()>0){
            PremutationHelper(str.toCharArray(),0,res);
            Collections.sort(res);
        }
        return (ArrayList<String>) res;
    }

    private void PremutationHelper(char[] cs, int i, List<String> res) {
        if (i==cs.length-1){
            String val=String.valueOf(cs);
            if (!res.contains(val)){
                res.add(val);
            }
        }else {
            for (int j=i;j<cs.length;j++){
                swap(cs,i,j);
                PremutationHelper(cs,i+1,res);
                swap(cs,i,j);
            }
        }
    }

    private void swap(char[] cs, int i, int j) {
        char temp=cs[i];
        cs[i]=cs[j];
        cs[j]=temp;
    }

}
