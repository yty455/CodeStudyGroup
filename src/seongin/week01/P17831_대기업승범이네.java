package seongin.week01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class P17831_대기업승범이네 {
    static int N;
    static List<List<Integer>> list = new ArrayList<>();
    static int[][] dp;
    static int[] power;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int result = 0;

        N = Integer.parseInt(br.readLine());
        for (int i = 0; i <= N; i++) {
            list.add(new ArrayList<>());
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 2; i <= N; i++) {
            list.get(Integer.parseInt(st.nextToken())).add(i);
        }
        dp = new int[N + 1][2]; // 0번엔 자신이 사수가 아닐때, 1번은 자신이 사수일때
        power = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            power[i] = Integer.parseInt(st.nextToken()); // 각자의 power
        }
        dfs(1);
        System.out.println(Math.max(dp[1][1], dp[1][0]));
    }

    static void dfs(int parent) {
        int sum = 0;
        int max = 0;
        for (int child : list.get(parent)) {
            dfs(child);
            int temp = power[parent] * power[child];
            sum += Math.max(dp[child][0], dp[child][1]); // 부사수의 가장 베스트 (사수와 가장 시너지가 좋은 부사수 제외하곤 상태가 어떠하던지 상관 x)
            max = Math.max(max, dp[child][0] + temp - Math.max(dp[child][0], dp[child][1])); // 가장 시너지 좋은 부사수 찾기(부사수가 사수가 아닌 경우 + 시너지 - 부사수의 가장 베스트)
        }
        dp[parent][1] += sum + max; // 내가 사수일때 시너지
        dp[parent][0] += sum;
    }
}
