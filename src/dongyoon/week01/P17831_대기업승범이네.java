package dongyoon.week01;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class P17831_대기업승범이네 {

	static int N;
	static int[] score;
	static int[][] synergy;
	static List<List<Integer>> adjList = new ArrayList<List<Integer>>();

	static void dfs(int idx) {
//		System.out.println(idx);
		if (adjList.get(idx).size() == 0)
			return;
		int max = Integer.MIN_VALUE;
		for (int n : adjList.get(idx)) {
			dfs(n);
			// 선택안할때 자식중 큰값 가져옴
			synergy[idx][0] += Math.max(synergy[n][0], synergy[n][1]);
			// 선택할때 자식들중 젤 큰값이랑 비교
			synergy[idx][1] += Math.max(synergy[n][0], synergy[n][1]);
			max = Math.max(max, score[idx] * score[n] - Math.max(synergy[n][0], synergy[n][1]) + synergy[n][0]);
//			System.out.println("idx = " + idx + "  , max= " + max + " , dd=" + Math.max(synergy[n][0], synergy[n][1]));
		}
		synergy[idx][1] += max;
//		System.out.println("idx = " + idx + ",  시너지 0 : " + synergy[idx][0] + ", 1 : " + synergy[idx][1]);

	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		score = new int[N + 1];
		synergy = new int[N + 1][2]; // 0포함X 1포함O
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i <= N; i++)
			adjList.add(new ArrayList<Integer>());
		for (int i = 2; i <= N; i++) {
			int n = Integer.parseInt(st.nextToken());
			adjList.get(n).add(i);
		}
		st = new StringTokenizer(br.readLine());
		for (int i = 1; i <= N; i++) {
			score[i] = Integer.parseInt(st.nextToken());
		}

		dfs(1);

		System.out.println(Math.max(synergy[1][0], synergy[1][1]));
	}

}
