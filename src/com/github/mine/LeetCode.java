package com.github.mine;

import java.security.PublicKey;
import java.util.*;

import com.github.structure.ListNode;
import com.github.structure.TreeNode;

public class LeetCode {

    // 链表环的检测
    public ListNode detectCycle(ListNode head) {
        if(null == head || head.next == null) {
            return null;
        }
        ListNode fast = head,slow = head;
        while (fast != null) {
            slow = slow.next;
            if (fast.next != null) {
                fast = fast.next.next;
            } else {
                return null;
            }
            if (slow == fast) {
                ListNode pot = head;
                while (pot != slow) {
                    pot = pot.next;
                    slow = slow.next;
                }
                return pot;
            }
        }
        return null;
    }

    // 动态扩容的数组
    class DynamicArray{

        int[] data;

        int capacity;

        int n;

        public DynamicArray(int n) {
            capacity = n;
            n = 0;
            data = new int[n];
        }

        public void add(int v) {
            if (n > capacity * 0.7) {
                int[] newData = new int[capacity * 2];
                System.arraycopy(data, 0, newData, 0, n);
                data = newData;
            }
            data[n++] = v;
        }

        public void set(int index,int v) {
            data[index] = v;
        }


        public int get(int index){
            return data[index];
        }
    }

    // 实现两个有序数组合并为一个有序数组
    public int[] merge2Array(int[] arr1,int[] arr2){
        if (null == arr1 || arr1.length == 0) {
            return arr2;
        }
        if (null == arr2 || arr2.length == 0) {
            return arr1;
        }
        int[] res = new int[arr1.length+arr2.length];
        int index1 = 0,index2 = 0,num = 0;
        while (index1<arr1.length && index2<arr2.length) {
            if (arr1[index1]<arr2[index2]){
                res[num++] = arr1[index1++];
            } else {
                res[num++] = arr2[index2++];
            }
        }
        while (index1<arr1.length) {
            res[num++] = arr1[index1++];
        }
        while (index2<arr2.length) {
            res[num++] = arr2[index2++];
        }
        return res;
    }

    // 求链表的中间结点
    public ListNode findMidNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (null != fast && fast.next != null) {
            fast = head.next.next;
            slow = head.next;
        }
        return slow;
    }

    // 三数之和为0
    public List<List<Integer>> threeSum(int[] nums) {
        int n = nums.length;
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        for (int first = 0;first<n;first++) {
            if (first>0 && nums[first] == nums[first-1]) {
                continue;
            }
            int target = 0-nums[first];
            int third = n-1;
            for (int second = first+1;second<n;second++) {
                if (second>first+1 && nums[second] == nums[second-1]){
                    continue;
                }
                while (third>second && nums[second] + nums[third] > target) {
                    --third;
                }
                if (second == third) {
                    break;
                }
                if (nums[second] + nums[third] == target) {
                    List<Integer> tmp = new ArrayList<>();
                    tmp.add(nums[first]);
                    tmp.add(nums[second]);
                    tmp.add(nums[third]);
                    res.add(tmp);
                }
            }
        }
        return res;
    }

    //多数元素 https://leetcode-cn.com/problems/majority-element/
    public int majorityElement(int[] nums) {
        int count = 0;
        int tmp = 0;
        for (int i = 0; i < nums.length; i++) {
            if (count == 0) {
                tmp = nums[i];
            }
            count += (nums[i] == tmp) ? 1 : -1;
        }
        return tmp;
    }

    //缺失的第一个正数
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; ++i) {
            while (nums[i] > 0 && nums[i] <= n && nums[nums[i] - 1] != nums[i]) {
                int temp = nums[nums[i] - 1];
                nums[nums[i] - 1] = nums[i];
                nums[i] = temp;
            }
        }
        for (int i = 0; i < n; ++i) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }
        return n + 1;
    }

    //合并K个升序链表 https://leetcode-cn.com/problems/merge-k-sorted-lists/
    public ListNode mergeKLists(ListNode[] lists) {
        if(null == lists || lists.length == 0) {
            return null;
        }
        ListNode node = lists[0];
        for (int i = 1;i<lists.length;i++) {
            ListNode tmp = OfferEasy.mergeTwoLists(node,lists[i]);
            if (null != tmp) {
                node = tmp;
            }
        }
        return node;
    }

    // https://leetcode-cn.com/problems/valid-parentheses/
    public static boolean isValid(String s) {
        Stack<Character> tmp = new Stack<>();
        boolean valid = true;
        for (char c : s.toCharArray()) {
            if ('(' == c||'{' == c || '[' == c) {
                tmp.add(c);
            }
            if (')' == c||'}' == c || ']' == c) {
                if (tmp.isEmpty()) {
                    valid = false;
                    break;
                }
            }
            if (')' == c) {
                Character cc = tmp.pop();
                if (null == cc || cc != '(') {
                    valid = false;
                    break;
                }
            }
            if ('}' == c) {
                Character cc = tmp.pop();
                if (null == cc || cc != '{') {
                    valid = false;
                    break;
                }

            }
            if (']' == c) {
                Character cc = tmp.pop();
                if (null == cc || cc != '[') {
                    valid = false;
                    break;
                }
            }
        }
        return valid && tmp.isEmpty();
    }

    public static void main(String[] args) {
        System.out.println(isValid("()"));
    }

    // https://leetcode-cn.com/problems/sliding-window-maximum/
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        PriorityQueue<int[]> pq = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] != o2[0]?o2[0]-o1[0]:o2[1]-o1[1];
            }
        });
        for (int i =0;i<k;++i) {
            pq.offer(new int[]{nums[i],i});
        }
        int[] res = new int[n-k+1];
        res[0] = pq.peek()[0];
        for (int i=k;i<n;++i) {
            pq.offer(new int[]{nums[i],i});
            while (pq.peek()[1] <= i-k) {
                pq.poll();
            }
            res[i-k+1] = pq.peek()[0];
        }
        return res;
    }

    // https://leetcode-cn.com/problems/climbing-stairs/
    public int climbStairs(int n) {
        int[] memo = new int[n+1];
        return climbStairsMemo(n,memo);
    }

    public int climbStairsMemo(int n,int[] memo) {
        if (memo[n] >0) {
            return memo[n];
        }
        if (n ==1) {
            memo[n]=1;
        } else if (n == 2) {
            memo[n] = 2;
        } else {
            memo[n] =climbStairsMemo(n-1,memo)+climbStairsMemo(n-2,memo);
        }
        return memo[n];
    }

    // 求x的平方根  二分法
    public static int sqort(int x) {
        int l = 0;
        int r = x;
        int res = -1;
        while (l<=r){
            int mid = l + (r-l)/2;
            if ((long)mid * mid <= x) {
                l = mid+1;
                res = mid;
            } else {
                r = mid-1;
            }
        }
        return res;
    }

    // https://leetcode-cn.com/problems/lru-cache/
    // 使用hash表和双向链表实现LRU
    class LRUCache {

        class DLinkedNode{
            int key;
            int value;
            DLinkedNode pre;
            DLinkedNode next;

            public DLinkedNode() {
            }

            public DLinkedNode(int key, int value) {
                this.key = key;
                this.value = value;
            }
        }

        int size;
        int capacity;
        private DLinkedNode head;
        private DLinkedNode tail;
        private HashMap<Integer,DLinkedNode> map;

        public LRUCache(int capacity) {
            this.capacity = capacity;
            this.size = 0;
            this.head = new DLinkedNode();
            this.tail = new DLinkedNode();
            head.next = tail;
            tail.pre = head;
            map = new HashMap<>();
        }

        public int get(int key) {
            DLinkedNode node = map.get(key);
            if (null != node) {
                move2Head(node);
                return node.value;
            }
            return -1;
        }

        public void put(int key, int value) {
            DLinkedNode node = map.get(key);
            if (null == node) {
                node = new DLinkedNode(key,value);
                map.put(key,node);
                add2Head(node);
                ++size;
                if (size>capacity) {
                    DLinkedNode tail = removeTail();
                    map.remove(tail.key);
                    --size;
                }
            } else {
                node.value = value;
                move2Head(node);
            }
        }

        public void removeNode(DLinkedNode node) {
            node.pre.next = node.next;
            node.next.pre = node.pre;
        }

        public void add2Head(DLinkedNode node) {
            node.next = head.next;
            node.pre = head;
            node.next.pre = node;
            head.next = node;
        }

        public void move2Head(DLinkedNode node) {
            removeNode(node);
            add2Head(node);
        }

        public DLinkedNode removeTail() {
            DLinkedNode node = tail.pre;
            removeNode(node);
            return node;
        }
    }

    // 反转二叉树 https://leetcode-cn.com/problems/invert-binary-tree/
    public TreeNode invertTree(TreeNode root) {
        if (null != root) {
            TreeNode tmp = root.left;
            root.left = root.right;
            root.right = tmp;
            invertTree(root.left);
            invertTree(root.right);
        }
        return root;
    }

    //验证二叉搜索树 https://leetcode-cn.com/problems/validate-binary-search-tree/
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root,Long.MIN_VALUE,Long.MAX_VALUE);
    }

    public boolean isValidBST(TreeNode root, long lower,long upper) {
        if (root == null) {
            return true;
        }
        if (root.val <=lower || root.val>=upper) {
            return false;
        }
        return isValidBST(root.left,lower,root.val) && isValidBST(root.right,root.val,upper);
    }

    // https://leetcode-cn.com/problems/path-sum/
    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (null == root) return false;
        if (root.left == null && root.right == null) {
            return targetSum == root.val;
        }
        return hasPathSum(root.left,targetSum- root.val) || hasPathSum(root.right,targetSum-root.val);
    }

    // https://leetcode-cn.com/problems/successor-lcci/
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        List<TreeNode> tmp = new ArrayList<>();
        midorder(root,tmp);
        for (int i = 0; i < tmp.size(); i++) {
            if (tmp.get(i).val == p.val) {
                if (i<tmp.size()-1){
                    return tmp.get(i+1);
                }
            }
        }
        return null;
    }

    public void midorder(TreeNode root,List<TreeNode> tmp) {
        if (null == root) {
            return;
        }
        midorder(root.left,tmp);
        tmp.add(root);
        midorder(root.right,tmp);
    }

    // https://leetcode-cn.com/problems/permutations/
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        LinkedList<Integer> tmp = new LinkedList<>();
        dfsPermute(nums,new LinkedList<>(),res);
        return res;
    }

    public void dfsPermute(int[] nums,LinkedList<Integer> tmp,List<List<Integer>> res) {
        if (tmp.size() == nums.length) {
            res.add(new ArrayList<>(tmp));
            return;
        }
        for (int i = 0;i<nums.length;++i) {
            if (tmp.contains(nums[i])){
                continue;
            }
            tmp.add(nums[i]);
            dfsPermute(nums,tmp,res);
            tmp.removeLast();
        }
    }

    // https://leetcode-cn.com/problems/minimum-depth-of-binary-tree/
    public int minDepth(TreeNode root) {

    }


}
