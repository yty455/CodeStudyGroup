package seongin.week01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P1520_내리막길 {
    static int M, N, result;
    static int[][] map;
    static boolean[][] visit;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        result = 0;
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new int[M][N];
        visit = new boolean[M][N];

        visit[0][0] = true;
        dfs(0, 0);
        visit[0][0] = false;
//        for (int i = 0; i < M; i++) {
//            System.out.println(Arrays.toString(visit[i]));
//        }
        System.out.println(result);
    }

    static void dfs(int x, int y) {
        if (x == M - 1 && y == N - 1) {
            result++;
            return;
        }
        for (int i = 0; i < M; i++) {
            System.out.println(Arrays.toString(visit[i]));
        }

        for (int d = 0; d < 4; d++) {
            int nx = x + dx[d];
            int ny = y + dy[d];



            if (nx < 0 || ny < 0 || nx >= M || ny >= N || visit[nx][ny] || map[x][y] <= map[nx][ny]) continue;
            visit[nx][ny] = true;
            dfs(nx, ny);
            visit[nx][ny] = false;
        }

    }
}
