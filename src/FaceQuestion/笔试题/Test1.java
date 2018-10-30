package FaceQuestion.笔试题;

import java.util.*;


public class Test1 {

    public static void main(String[] args) {
        Scanner scanner=new Scanner(System.in);
        String s=scanner.nextLine();
        String[] split = s.split("\"");
        String s1=scanner.nextLine();
        String[] split2=s.split("\"");
        Set<String> wordDic=new HashSet<>();
        for (int i=1;i<split2.length;i=i+2){
            wordDic.add(split2[i]);
        }
        System.out.println(wordDic);
        List<String> strings = wordBreak(split[1], wordDic);
        StringBuilder builder=new StringBuilder();
        builder.append("[");
        for (int i=0;i<strings.size();i++){
            if (i==strings.size()){
                builder.append(strings.get(i));
            }else {
                builder.append(strings.get(i)+",");
            }
        }
        builder.append("]");
        System.out.println(builder.toString());
        System.out.println(strings.size());
    }

    static List<String> res = new ArrayList<>();
    public static void helper(String ans, int index, List<String>[] listArray){
        if(index == 0) {
            res.add(ans.trim());
            return;
        }
        if(listArray[index] == null){
            return;
        }
        for(String word : listArray[index]){
            helper(" " + word + ans, index - word.length(), listArray);
        }
        return;
    }
    public static List<String> wordBreak(String s, Set<String> wordDict) {
        ArrayList<String>[] listArray = new ArrayList[s.length() + 1];
        listArray[0] = new ArrayList<>();
        for(int i = 0; i < s.length(); i++){
            if(listArray[i] != null){
                for(int j = i + 1; j <= s.length(); j++){
                    String sub = s.substring(i, j);
                    if(wordDict.contains(sub)){
                        if(listArray[j] == null){
                            listArray[j] = new ArrayList<String>();
                        }
                        listArray[j].add(sub);
                    }
                }
            }
        }
        helper("", s.length(), listArray);
        return res;
    }

}
