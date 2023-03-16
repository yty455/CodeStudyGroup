package dongyoon.week02;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class P1167_트리의지름 {

	static int V, max, max_node;
	static boolean[] visit;
	static List<List<int[]>> list = new ArrayList<List<int[]>>();

	static void dfs(int n, int sum) {
		visit[n] = true;
		if (sum > max) {
			max_node = n;
			max = sum;
		}

		for (int[] node : list.get(n)) {
			if (visit[node[0]])
				continue;
			dfs(node[0], sum + node[1]);
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

		V = Integer.parseInt(br.readLine());
		for (int i = 0; i <= V; i++) {
			list.add(new ArrayList<int[]>());
		}

		for (int i = 1; i <= V; i++) {
			st = new StringTokenizer(br.readLine());
			int idx = Integer.parseInt(st.nextToken());
			while (true) {
				int to = Integer.parseInt(st.nextToken());
				if (to == -1)
					break;
				int d = Integer.parseInt(st.nextToken());
				list.get(idx).add(new int[] { to, d });
			}
		}

		// 100,000 x 100,000 배열 못만든다!!
		visit = new boolean[V + 1];
		dfs(1, 0);

		// 위에서 선택된 max의 노드가 트리의지름의 끝점(일부분) -> 다시 탐색
		Arrays.fill(visit, false);
		dfs(max_node, 0);
		System.out.println(max);
	}

}
