package FaceQuestion.数组_栈_队列;

import java.util.ArrayList;

public class Solution {

    /**
     *                     1
     *               2           3
     *            4     5      6    7
     *
     */

    public class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }

    }




    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root, int target){
        ArrayList<ArrayList<Integer>> listAll=new ArrayList<>();
        ArrayList<Integer> list=new ArrayList<>();
        if (root==null){
            return listAll;
        }
        list.add(root.val);
        target-=root.val;
        if (target==0&&root.left==null&&root.right==null){
            listAll.add(new ArrayList<>(list));
        }
        FindPath(root.left,target);
        FindPath(root.right,target);
        list.remove(list.size()-1);
        return listAll;
    }
}
