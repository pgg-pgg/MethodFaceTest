package FaceQuestion.二叉树;


/**
 * 前缀树
 *
 */
public class TrieTree {

    public static class TrieNode{
        public int path;//每个字符串节点路过的次数
        public int end;//每个字符串的节点出现的次数
        public TrieNode[] map;//字符的路径

        public TrieNode(){
            path=0;
            end=0;
            map=new TrieNode[26];
        }
    }

    /**
     * 前缀树类
     */
    public static class Trie{

        private TrieNode root;
        public Trie(){
            root=new TrieNode();
        }

        /**
         * 插入一个字符串
         * @param word
         */
        public void insert(String word){
            if (word==null){
                return;
            }

            char[] chs=word.toCharArray();//将字符串转换为字符数组

            TrieNode node=root;
            int index=0;//每个字符的ascll码代表的序号
            for (int i=0;i<chs.length;i++){
                index=chs[i]-'a';//将每个字符转换为0,1,2,3...序号值
                if(node.map[index]==null){//如果这条边为空,那么新建一个节点
                    node.map[index]=new TrieNode();
                }
                node=node.map[index];//节点到下一个
                node.path++;//经过的路径次数+1
            }
            node.end++;//整个字符串出现的次数+1；
        }

        /**
         * 删除一个字符串
         * 删除时，需要判断节点路劲经过次数是否已经为0，为0才可以删除
         * @param word
         */
        public void delete(String word){
            if(search(word)){
                //如果该字符串存在
                char[] chs=word.toCharArray();
                TrieNode node=root;
                int index=0;
                for (int i=0;i<chs.length;i++){
                    index=chs[i]-'a';
                    if (node.map[index].path--==1){
                        //如果一个节点的path值为0，那么就可以删除此节点
                        node.map[index]=null;
                        return;
                    }
                    node=node.map[index];//节点不断向下
                }
                node.end--;
            }
        }

        /**
         * 在字典树中寻找是否存在摸个字符串
         * @param word
         * @return
         */
        public boolean search(String word){
            if (word==null){
                return false;
            }

            char[] chs=word.toCharArray();
            TrieNode node=root;
            int index=0;
            for (int i=0;i<chs.length;i++){
                index=chs[i]-'a';
                if (node.map[index]==null){
                    return false;
                }
                node=node.map[index];
            }
            return node.end!=0;
        }

        /**
         * 寻找一个字符串的前缀部分出现的次数
         * @param pre
         * @return
         */
        public int prefixNumber(String pre){
            if (pre==null){
                return 0;
            }
            char[] chs=pre.toCharArray();
            TrieNode node=root;
            int index=0;
            for (int i=0;i<chs.length;i++){
                index=chs[i]-'a';
                if (node.map[index]==null){
                    return 0;
                }
                node=node.map[index];
            }
            return node.path;
        }
    }
}
