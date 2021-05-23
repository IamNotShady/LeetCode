package com.github.mine;

import java.security.PublicKey;
import java.util.*;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import com.github.structure.ListNode;
import com.sun.org.apache.bcel.internal.generic.NEW;

import jdk.internal.org.objectweb.asm.tree.LineNumberNode;

public class offer {

    public static void main(String[] args) {
//        System.out.println(964632435 * 10);
//        System.out.println(reverse(1534236469));
//        ListNode root = new ListNode(1);
//        ListNode aa = root;
//        root.next = new ListNode(2);
//        root = root.next;
//        root.next = new ListNode(3);
//        root = root.next;
//        root.next = new ListNode(4);
//        root = root.next;
//        root.next = new ListNode(5);
//        root = root.next;
//        reverseList(aa);
//        System.out.println(hammingWeight(00000000000000000000000000001011));
//        findContinuousSequence(3);
//        TreeNode root = new TreeNode(37);
//        TreeNode tmp = root;
//        root.left = new TreeNode(-34);
//        root.right = new TreeNode(-48);
//        root.left.right = new TreeNode(-100);
//        root = root.right;
//        root.left = new TreeNode(-101);
//        root.right = new TreeNode(48);
//        root = root.right;
//        root.left = new TreeNode(-54);
//        root = root.left;
//        root.left = new TreeNode(-71);
//        root.right = new TreeNode(-22);
//        root.right.right = new TreeNode(8);
//        lowestCommonAncestor(tmp,new TreeNode(48),new TreeNode(-71));
//        TreeNode root =  createTreeNode(new Integer[]{1,2,2,null,3,null,3});
//        System.out.println(isSymmetric(root));
          insertionSort(new int[]{4,5,6,1,3,2},5);
    }

    public String reverseLeftWords(String s, int n) {
        return s.substring(n, s.length()) + s.substring(0, n);
    }

    public int[] twoSum(int[] nums, int target) {
        int[] res = new int[2];
        for (int i = 0; i < nums.length; i++) {
            for (int n = 0; n < nums.length; n++) {
                if (i == n) continue;
                if (nums[i] + nums[n] == target) return new int[]{i, n};
            }
        }
        return res;
    }

    public int[] twoSum2(int[] nums, int target) {
        Map<Integer, Integer> tempMap = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            Integer value = tempMap.get(nums[i]);
            if (null != value) {
                return new int[]{value, i};
            }
            tempMap.put(target - nums[i], i);
        }
        return new int[2];
    }

    public static int reverse(int x) {
        if (x == 0) return 0;
        long tmp = 0;
        while (x != 0) {
            int b = x % 10;
            tmp = (tmp * 10) + b;
            x = x / 10;
        }
        if (tmp > Integer.MAX_VALUE || tmp < Integer.MIN_VALUE) return 0;
        return (int) tmp;
    }


    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }


    public static int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }

    public TreeNode mirrorTree(TreeNode root) {
        if (root == null) return root;
        TreeNode left = root.left;
        TreeNode right = root.right;
        root.left = right;
        root.right = left;
        mirrorTree(root.left);
        mirrorTree(root.right);
        return root;
    }

    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode root = head;
        int i = 0;
        while (root != null) {
            ++i;
            root = root.next;
        }
        root = head;
        for (int a = 1; a < i; a++) {
            if (a == (i - k) + 1) {
                break;
            }
            root = root.next;
        }
        return root;
    }


    public int[] printNumbers(int n) {
        if (n == 0) return null;
        int size = 1;
        for (int x = 0; x < n; x++) {
            size = size * 10;
        }
        int[] res = new int[size - 1];
        for (int i = 1; i < size; i++) {
            res[i - 1] = i;
        }
        return res;
    }

    public String replaceSpace(String s) {
        return s.replace(" ", "%20");
    }

    public int[] reversePrint(ListNode head) {
        if (head == null) return new int[0];
        int size = 1;
        ListNode newHead = head;
        while (head.next != null) {
            size++;
            head = head.next;
        }
        int[] res = new int[size];
        for (int i = 0; i < size; i++) {
            res[i] = newHead.val;
            newHead = newHead.next;
        }
        int[] ress = new int[size];
        for (int a = 0; a < size; a++) {
            ress[a] = res[size - a - 1];
        }
        return ress;
    }

    public int kthLargest(TreeNode root, int k) {
        if (root == null) return 0;
        List<Integer> res = new ArrayList<>();
        nodeNum(root, res);
        return res.get(k - 1);
    }

    public void nodeNum(TreeNode node, List<Integer> res) {
        if (node == null) return;
        nodeNum(node.right, res);
        res.add(node.val);
        nodeNum(node.left, res);
    }

    public static ListNode reverseList(ListNode head) {
        if (null == head || null == head.next) return head;
        ListNode pre = null;
        ListNode curr = head;
        while (null != curr) {
            ListNode next = curr.next;
            curr.next = pre;
            pre = curr;
            curr = next;
        }
        return pre;
    }

    public static int hammingWeight(int n) {
        int res = 0;
        while (n != 0) {
            res += n & 1;
            n >>>= 1;
        }
        return res;
    }

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        ListNode node = new ListNode(0);
        ListNode curr = node;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                curr.next = l1;
                l1 = l1.next;
            } else {
                curr.next = l2;
                l2 = l2.next;
            }
            curr = curr.next;
        }
        curr.next = l1 == null ? l1 : l1;
        return node;
    }

    class CQueue {

        Stack<Integer> stack1;
        Stack<Integer> stack2;

        public CQueue() {
            this.stack1 = new Stack<>();
            this.stack2 = new Stack<>();
        }

        public void appendTail(int value) {
            stack1.push(value);
        }

        public int deleteHead() {
            if (stack1.isEmpty()) return -1;
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
            int res = stack2.pop();
            while (!stack2.isEmpty()) {
                stack1.push(stack2.pop());
            }
            return res;
        }
    }

    public static int[][] findContinuousSequence(int target) {
        List<int[]> vec = new ArrayList<int[]>();
        int sum = 0, limit = (target - 1) / 2; // (target - 1) / 2 等效于 target / 2 下取整
        for (int i = 1; i <= limit; ++i) {
            for (int j = i; ; ++j) {
                sum += j;
                if (sum > target) {
                    sum = 0;
                    break;
                } else if (sum == target) {
                    int[] res = new int[j - i + 1];
                    for (int k = i; k <= j; ++k) {
                        res[k - i] = k;
                    }
                    vec.add(res);
                    sum = 0;
                    break;
                }
            }
        }
        return vec.toArray(new int[vec.size()][]);
    }

    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (includeTwoNode(root.left,p) && includeTwoNode(root.left,q)){
            root = root.left;
            return lowestCommonAncestor(root,p,q);
        }
        if (includeTwoNode(root.right,p) && includeTwoNode(root.right,q)){
            root = root.right;
            return lowestCommonAncestor(root,p,q);
        }
        return root;
    }

    public static boolean includeTwoNode(TreeNode root, TreeNode node) {
        if (null == root) {
            return false;
        }
        if (root.val == node.val) {
            return true;
        }
        return (includeTwoNode(root.left,node)||includeTwoNode(root.right,node));
    }

    class Solution {
        public Map<Integer,TreeNode> allNode = new HashMap<>();
        public Set<Integer> visited = new HashSet<>();

        public void dfs(TreeNode node) {
            if (node.left!=null) {
                allNode.put(node.left.val,node);
                dfs(node.left);
            }
            if (node.right != null) {
                allNode.put(node.right.val,node);
                dfs(node.right);
            }
        }

        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            dfs(root);
            while (p != null) {
                visited.add(p.val);
                p = allNode.get(p.val);
            }
            while (q != null) {
                if (visited.contains(q.val)) {
                    return q;
                }
                q = allNode.get(q.val);
            }
            return null;
        }
    }

    public int majorityElement(int[] nums) {
        Map<Integer,Integer> numMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int n = nums[i];
            if (numMap.containsKey(n)) {
                numMap.put(n,numMap.get(n)+1);
            } else{
                numMap.put(n,1);
            }
            if (numMap.get(n)>nums.length/2){
                return n;
            }
        }
        return 0;
    }

    public static TreeNode createTreeNode(Integer[] array){
        TreeNode root = createTreeNode(array, 1);
        return root;
    }

    private static TreeNode createTreeNode(Integer[] array, int index) {
        if(index > array.length){
            return null;
        }
        Integer value = array[index - 1];
        if(value == null){
            return null;
        }
        TreeNode node = new TreeNode(value);
        node.left = createTreeNode(array, index * 2);
        node.right = createTreeNode(array, index * 2 + 1);
        return node;
    }

    public static List<List<Integer>> levelOrder(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        List<List<Integer>> result = new ArrayList<>();
        if (root != null) queue.add(root);
        while (!queue.isEmpty()) {
            List<Integer> tmp =new ArrayList<>();
            for(int i = queue.size(); i > 0; i--) {
                TreeNode node  = queue.poll();
                tmp.add(node.val);
                if (node.left != null) queue.add(node.left);
                if (node.right!= null) queue.add(node.right);
            }
            result.add(tmp);
        }
        return result;
    }

    public int findRepeatNumber(int[] nums) {
        Set<Integer> tmp = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (tmp.contains(nums[i])) {
                return nums[i];
            }
            tmp.add(nums[i]);
        }
        return -1;
    }

    public int[] twoSum1(int[] nums, int target) {
        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(nums[i])) {
                return new int[]{nums[i],map.get(nums[i])};
            }
            int aa = target - nums[i];
            map.put(aa,nums[i]);
        }
        return new int[0];
    }

    public int[] twoSum3(int[] nums, int target) {
        int i = 0,j=nums.length-1;
        while (i<j) {
            int res = nums[i] + nums[j];
            if (res<target) ++i;
            if (res>target) --j;
            if (res == target) {
                return new int[]{nums[i],nums[j]};
            }
        }
        return new int[0];
    }

    public int[] exchange(int[] nums) {
        int i =0,j = nums.length-1;
        while (i<j) {
            if (nums[i]%2 == 0 && nums[j] %2 ==1) {
                int tmp = nums[i];
                nums[i] = nums[j];
                nums[j] = tmp;
                ++i;
                --j;
                continue;
            }
            if (nums[i]%2 == 0){
                --j;
                continue;
            }
            if (nums[j] %2 ==1) {
                ++i;
                continue;
            }
            ++i;
        }
        return nums;
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode node1 = headA;
        ListNode node2 = headB;
        while (node1 != node2) {
            node1 = node1 == null ? headB : node1.next;
            node2 = node2 == null ? headA : node2.next;
        }
        return node1;
    }

    public char firstUniqChar(String s) {
        Map<Character,Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        for (char c : s.toCharArray()) {
            if (map.get(c) == 1){
                return c;
            }
        }
        return ' ';
    }

    public ListNode deleteNode(ListNode head, int val) {
        if (null!=head && head.val == val) return head.next;
        ListNode tmp = head;
        while (null != head) {
            ListNode next = head.next;
            if (null != next && next.val == val) {
                head.next = next.next;
                break;
            }
            head = next;
        }
        return tmp;
    }

    public static boolean isBalanced(TreeNode root) {
        if (root == null) return true;
        return Math.abs(maxDepth(root.left) - maxDepth(root.right)) <= 1 &&isBalanced(root.left) && isBalanced(root.right);
    }

    public static boolean isSymmetric(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        if (null!=root) queue.add(root);
        while (!queue.isEmpty()) {
            List<Integer> list = new ArrayList<>();
            for (int i = queue.size();i>0;--i){
                TreeNode node = queue.poll();
                if (node==null) {
                    list.add(-1);
                } else {
                    list.add(node.val);
                    queue.add(node.left);
                    queue.add(node.right);
                }
            }
            int i=0,j=list.size()-1;
            while (i<j) {
                if (list.get(i) != list.get(j)) {
                    return false;
                }
                ++i;
                --j;
            }
        }
        return true;
    }


    // 插入排序，a表示数组，n表示数组大小
    public static void insertionSort(int[] a, int n) {
        if (n <= 1) return;
        for (int i = 1; i < n; ++i) {
            int value = a[i];
            int j = i - 1;
            // 查找插入的位置
            for (; j >= 0; --j) {
                if (a[j] > value) {
                    a[j+1] = a[j];  // 数据移动
                } else {
                    break;
                }
            }
            a[j+1] = value; // 插入数据
        }
    }

    // 归并排序
    public static void mergeSort(int[] arr, int low, int high){
        if (low<high){
            int mid = (low+high)/2;
            mergeSort(arr,low,mid);
            mergeSort(arr,mid+1,high);
            merge(arr,low,mid,high);
        }
    }

    public static void merge(int[] arr,int low,int mid,int high) {
        int[] tmp = new int[high-low+1];
        int i = low;
        int j = mid+1;
        int k = 0;
        for (;i<=mid&&j<=high;++k) {
            if (arr[i]<=arr[j]){
                tmp[k] = arr[i++];
            } else {
                tmp[k] = arr[j++];
            }
        }
        while (i<=mid){
            tmp[k++] = arr[i++];
        }
        while (j<=high) {
            tmp[k++] = arr[j++];
        }
        for (int n =0;n<tmp.length;n++) {
            arr[low+n] = tmp[n];
        }
    }
    //快速排序
    public static void quickSort(int[] arr,int low,int high) {
        Arrays.sort(arr);
    }


}
