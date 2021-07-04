package com.github.mine;

import java.util.Random;

public class Sort {

    // 堆排序
    public static void heapSort(int[] nums) {
        int len = nums.length -1;
        buildMaxHeap(nums,len);
        for (int i = len;i>=1;--i) {
            swap(nums,i,0);
            len -=1;
            maxHeap(nums,0,len);
        }
    }

    public static void buildMaxHeap(int[] nums,int len) {
        for (int i = len / 2; i >= 0; --i) {
            maxHeap(nums,i,len);
        }
    }

    public static void maxHeap(int[] nums,int i,int len) {
        while (true) {
            int large = i;
            int lson = i*2+1;
            int rson = i*2+2;
            if (lson<=len && nums[lson] > nums[large]) {
                large = lson;
            }
            if (rson <=len && nums[rson] > nums[large]) {
                large = rson;
            }
            if (large == i) {
                break;
            }
            swap(nums,i,large);
            i = large;
        }
    }

    public static void swap(int[] arr,int i,int j) {
        int n = arr[i];
        arr[i] = arr[j];
        arr[j] = n;
    }

    //快速排序
    public static void quickSort(int[] arr,int low,int high) {
        if (low<high) {
            int i = new Random().nextInt(high - low + 1) + low; // 随机选一个作为我们的主元
            swap(arr, high, i);
            int tmp = arr[high];
            int l = low;
            for (int j = low;j<=high-1;++j) {
                if (arr[j] <=tmp) {
                    swap(arr,l,j);
                    ++l;
                }
            }
            swap(arr,l,high);
            quickSort(arr,low,l-1);
            quickSort(arr,l+1,high);
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

    // 冒泡排序
    public static void bubbleSort(int[] nums) {
        int tmp;
        for (int i = 0; i < nums.length - 1; ++i) {
            for (int j = 0; j < nums.length - i - 1; ++j) {
                if (nums[j] > nums[j + 1]) {
                    tmp = nums[j + 1];
                    nums[j + 1] = nums[j];
                    nums[j] = tmp;
                }
            }
        }
    }
}
