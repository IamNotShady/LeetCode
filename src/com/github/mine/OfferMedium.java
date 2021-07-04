package com.github.mine;

import java.util.*;

import com.github.structure.TreeNode;

public class OfferMedium {

    // 8皇后问题
    public static class Cal8Queues{

        public static void main(String[] args) {
            cal8queues(0);
        }

        public static int result[] = new int[8];

        public static void cal8queues(int row) {
            if (row == 8) {
                // 输出result;
                return;
            }
            for (int col = 0;col<8;++col) {
                if (isOkRow(row,col)) {
                    result[row] = col;
                    cal8queues(row+1);
                }
            }
        }

        public static boolean isOkRow(int row,int col) {
            int leftup = col-1,rightup = col+1;
            for (int i = row-1;i>=0;--i) {
                if (result[i] == col) return false;
                if (leftup>=0) {
                    if (result[i] == leftup) return false;
                }
                if (rightup<8) {
                    if (result[i] == rightup) return false;
                }
                --leftup;++rightup;
            }
            return true;
        }
    }


    public int numWays(int n) {
        if(n == 1) return 1;
        n += numWays(n - 1);
        return n;
    }

    class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }

    public Node copyRandomList(Node head) {
        if (null == head ) return null;
        Node cur = head;
        Map<Node,Node> tmp =new HashMap<>();
        while (null != cur) {
            tmp.put(cur,new Node(cur.val));
            cur = cur.next;
        }
        cur = head;
        while (null != cur) {
            tmp.get(cur).next = tmp.get(cur.next);
            tmp.get(cur).random = tmp.get(cur.random);
            cur = cur.next;
         }
        return tmp.get(head);
    }

    private Map<Integer,Integer> indexMap = new HashMap<>();

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int n = inorder.length;
        for (int i = 0;i<n;i++) {
            indexMap.put(inorder[i],i);
        }
        return buildMyTree(preorder,inorder,0,n-1,0,n-1);
    }

    public TreeNode buildMyTree(int[] preorder, int[] inorder, int preLeft, int preRight, int inLeft, int inRight) {
        if (preLeft>preRight) {
            return null;
        }
        int rootV = preorder[preLeft];
        int rootIndex = indexMap.get(rootV);
        TreeNode root = new TreeNode(rootV);
        int leftSize = rootIndex  - inLeft;
        root.left = buildMyTree(preorder,inorder,preLeft+1,preLeft+leftSize,inLeft,rootIndex-1);
        root.right = buildMyTree(preorder,inorder,preLeft+leftSize+1,preRight,rootIndex+1,inRight);
        return root;
    }

    public int maxValue(int[][] grid) {
        int rowNUm = grid.length;
        if (rowNUm == 0) return 0;
        int colNum = grid[0].length;
        int row = 0;
        int col = 0;
        int max = 0;
        while (row<= rowNUm && col<= colNum) {
            max+=grid[row][col];
            if (row ==rowNUm && col< colNum) {
                ++col;
                continue;
            }
            if (row<rowNUm && col == colNum) {
                ++row;
                continue;
            }
            if (grid[row+1][col] > grid[row][col+1]) {
                ++row;
            }else {
                ++col;
            }
        }
        return max;
    }

    public TreeNode treeToDoublyList(TreeNode root) {
        List<TreeNode> tmp = new ArrayList<>();
        addNodeToList(tmp,root);
        TreeNode watch = new TreeNode(0);
        TreeNode pre = watch;
        for (int i = 0;i<tmp.size();++i) {
            TreeNode node = tmp.get(i);
            pre.right = node;
            node.left = pre;
            pre = node;
        }
        pre.right = watch.right;
        watch.right.left = pre;
        return watch.right;
    }

    public void addNodeToList(List<TreeNode> tmp, TreeNode tree) {
        if (null == tree) return;
        addNodeToList(tmp,tree.left);
        tmp.add(tree);
        addNodeToList(tmp,tree.right);
    }

    private List<List<Integer>> res = new ArrayList<>();
    private List<Integer> path = new ArrayList<>();

    public List<List<Integer>> pathSum(TreeNode root, int target) {
        recur(root,target);
        return res;
    }

    public void recur(TreeNode root, int target) {
        if (null == root) return;
        path.add(root.val);
        target -= root.val;
        if (target == 0 && null == root.left && null == root.right) {
            res.add(new LinkedList<>(path));
        }
        recur(root.left,target);
        recur(root.right,target);
        path.remove(path.size()-1);
    }
    //剑指 Offer 38. 字符串的排列
    public String[] permutation(String s) {
        Set<String> res = new HashSet<>();
        dfsString(s.toCharArray(),"",new boolean[s.length()],res);
        return res.toArray(new String[res.size()]);
    }

    public void dfsString(char[] str,String tmp,boolean[] visited,Set<String> res) {
        if (str.length == tmp.length()){
            res.add(tmp);
            return;
        }
        for (int i = 0;i<str.length;++i) {
            if (visited[i]) {
                continue;
            }
            visited[i] = true;
            dfsString(str,tmp+str[i],visited,res);
            visited[i] = false;
        }
    }


    public String minNumber(int[] nums) {
        String[] strs = new String[nums.length];
        for(int i = 0; i < nums.length; i++)
            strs[i] = String.valueOf(nums[i]);
        Arrays.sort(strs, (x, y) -> (x + y).compareTo(y + x));
        StringBuilder res = new StringBuilder();
        for(String s : strs)
            res.append(s);
        return res.toString();

    }

    public boolean verifyPostorder(int[] postorder,int left,int right) {
        if (left>=right) return true;
        int p = left;
        while (postorder[p]<postorder[right]) {
            ++p;
        }
        int m = p;
        while (postorder[p] >postorder[right]) {
            ++p;
        }
        return p == right && verifyPostorder(postorder,left,m-1) && verifyPostorder(postorder,m,right-1);
    }

    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        if (null == matrix || matrix.length ==0 || matrix[0].length ==0) {
            return false;
        }
        int rowNum = matrix.length-1;
        int colNum = matrix[0].length -1;
        int row = 0;
        int col = rowNum;
        while (row <= rowNum && col >= 0) {
            int num = matrix[row][col];
            if (num == target) {
                return true;
            } else if (num>target) {
                col--;
            } else  {
                row++;
            }
        }
        return false;
    }

    // 剑指 Offer 29. 顺时针打印矩阵



}
