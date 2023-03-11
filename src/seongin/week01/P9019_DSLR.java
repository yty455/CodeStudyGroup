package seongin.week01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

/*
사소한 부분 때문에 시간초과나 메모리 초과가 나는 게 아니다.
초과가 나면 반복되는 연산이 있는지 제대로 확인하자.
 */

public class P9019_DSLR {
    static int init, target;
    static boolean[] visit;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            st = new StringTokenizer(br.readLine());
            init = Integer.parseInt(st.nextToken());
            target = Integer.parseInt(st.nextToken());
            visit = new boolean[10000];

            sb.append(bfs(init)).append('\n');
        }
        System.out.println(sb);
    }

    static String bfs(int num) {
        Queue<UpdateNum> queue = new ArrayDeque<>();
        queue.offer(new UpdateNum(num, ""));

        while (!queue.isEmpty()) {
            UpdateNum v = queue.poll();
            if (v.num == target) {
                return v.command;
            }

            if (!visit[v.D()]) {
                queue.add(new UpdateNum(v.D(), v.command + "D"));
                visit[v.D()] = true;
            }
            if (!visit[v.S()]) {
                queue.add(new UpdateNum(v.S(), v.command + "S"));
                visit[v.S()] = true;
            }
            if (!visit[v.L()]) {
                queue.add(new UpdateNum(v.L(), v.command + "L"));
                visit[v.L()] = true;
            }
            if (!visit[v.R()]) {
                queue.add(new UpdateNum(v.R(), v.command + "R"));
                visit[v.R()] = true;
            }
        }
        return "";
    }

    static class UpdateNum {
        int num;
        String command;

        UpdateNum(int num, String command) {
            this.num = num;
            this.command = command;
        }

        int D() {
            return 2 * num % 10000;
        }

        int S() {
            return num == 0 ? 9999 : num - 1;
        }

        int L() {
            return num % 1000 * 10 + num / 1000;
        }

        int R() {
            return num % 10 * 1000 + num / 10;
        }
    }
}