package seongin.week01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class P1949_우수마을 {
    static ArrayList<ArrayList<Integer>> list = new ArrayList<>();
    static int[] people;
    static boolean[] visit;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        people = new int[N + 1];
        visit = new boolean[N + 1];
        dp = new int[N + 1][2];

        for (int i = 1; i <= N; i++) {
            people[i] = Integer.parseInt(st.nextToken());
        }

        // 인접리스트 만들기
        for (int i = 0; i <= N; i++) {
            list.add(new ArrayList<>());
        }
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            list.get(n1).add(n2);
            list.get(n2).add(n1);
        }

        dfs(1); // 아무거나 루트노드로 잡아도 됨

        System.out.println(Math.max(dp[1][0], dp[1][1]));
    }

    static void dfs(int node) {
        visit[node] = true;
        for (int next : list.get(node)) {
            if (visit[next]) continue;
            dfs(next);
            dp[node][1] += dp[next][0];
            dp[node][0] += Math.max(dp[next][0], dp[next][1]);
        }
        dp[node][1] += people[node];
    }
}
