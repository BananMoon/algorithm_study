package yun;

import java.util.Arrays;
import java.util.Comparator;

public class 가장큰수 {
	public String solution(int[] numbers) {
		String answer = "";
		//1. 주어진 정수 조합
        //2. 정렬로 가장 큰수를 리스트 맨 앞으로 가져오기
		
		String[] str = new String[numbers.length];
		
		for(int i = 0; i < numbers.length; i++) {
			str[i] = String.valueOf(numbers[i]);
		}
		
		//내림차순 정렬
		Arrays.sort(str, new Comparator<String>() {
			@Override
			public int compare(String a, String b) {
				return (b+a).compareTo(a+b);
			}
		});
		
		//0값이 중복일경우
		if(str[0].equals("0")) {
			return "0";
		}
		
		//0이 아니면 문자열을 더한다.
		for(String s : str) {
			answer += s;
		}
		return answer;
	}
	
	public static void main(String[] args) {
//		int[] numbers = {3, 30, 34, 5, 9};
		int[] numbers = {4, 24, 3, 1, 9};
		가장큰수 k = new 가장큰수();
		System.out.println("가장큰수=>"+k.solution(numbers));
	}

}
