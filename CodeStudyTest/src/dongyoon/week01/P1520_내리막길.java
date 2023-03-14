package dongyoon.week01;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P1520_내리막길 {

	static int N, M;
	static int[][] map;

	static int[][] dp;

	static int[] dx = { -1, 1, 0, 0 }; // 상 하 좌 우
	static int[] dy = { 0, 0, -1, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		M = Integer.parseInt(st.nextToken());
		N = Integer.parseInt(st.nextToken());

		map = new int[M][N];
		dp = new int[M][N];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			Arrays.fill(dp[i], -1);
			for (int j = 0; j < N; j++)
				map[i][j] = Integer.parseInt(st.nextToken());
		}

		dp[0][0] = 1;
		System.out.println(up(M - 1, N - 1));

	}

	static int up(int x, int y) {
		int sum = 0;
		int nx, ny;
		if (dp[x][y] != -1)
			return dp[x][y];
		for (int d = 0; d < 4; d++) {
			nx = x + dx[d];
			ny = y + dy[d];

			if (nx < 0 || ny < 0 || nx >= M || ny >= N || map[nx][ny] <= map[x][y])
				continue;

			if (dp[nx][ny] == -1)
				sum += up(nx, ny);
			else {
				sum += dp[nx][ny];
			}
		}
		dp[x][y] = sum;

		return sum;

	}

}
