package com.believe.lld.yubi;

import java.util.HashMap;

public class SubArray {

	/**
	 * given integer array, need to find the count of subarray, whose sum is equal
	 * to given number k
	 * 
	 * 
	 */

	public static void main(String[] args) {

//		int[] arr = {9,4, 20, 3, 10, 5}; //2^n, 
		int[] arr = { 10, 2, -2, -20, 10 }; //(-20,10)
		int k = -10; // output 3 , 20,10,3 ,, 9,4,20
//		int left=0;

		HashMap<Integer, Integer> hmap = new HashMap<Integer, Integer>();// 0=1,
		hmap.put(0, 1);
		int count = 0;
		int globalSum = 0;

		for (int i = 0; i < arr.length; i++) {

			globalSum += arr[i];  //10 , 12 , 10, -10, 0
			if (hmap.containsKey(globalSum - k))  //10-(-10)=20, 12-(-10)=22, 20, -20, 10
				count += hmap.get(globalSum - k);//0, 0, 0, 0,2

			hmap.put(globalSum, hmap.getOrDefault(globalSum, 0) + 1);//0=1,10=2, 12=1, -10=1, 

		}
		System.out.println("hmap:"+hmap);
		System.out.println("count: " + count);
	}
	
	/**
	 * given integer array 
	 * findout two numbers , whose product is the maximum
	 * 
	 * arr[] = {1,3,5,7,8, -10, -20}; (7,8)  negative numbers
	 * O(n*n) brute-force
	 * O(nlogn + n) 
	 * O(n+n)=O(n)  //1st,2nd max and 1st, 2nd min , Math.max()
	 *  {-10, 1, 3, 5, 7, 8}
	 *  
	 *  
	 * */

}
