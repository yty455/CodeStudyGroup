package seongin.week02;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/*
첫 dfs에서는 아무 정점에서 가장 긴 노드를 찾고
찾은 노드에서부터 dfs 돌리면 트리의 지름을 구할 수 있다.
어떠한 노드던지 가장 긴 지름의 목적지 노드를 찾으면 해당 지름의 일부가 트리의 지름에 포함되기 때문이다.
 */

public class P1167_트리의지름 {
    static boolean[] visit;
    static List<List<Node>> list = new ArrayList<>();
    static int max = 0;
    static int maxNode; // 트리의 지름에서 시작점 혹은 끝점이 되는 노드

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int V = Integer.parseInt(br.readLine());

        for (int i = 0; i <= V; i++) {
            list.add(new ArrayList<>()); // 0 dummy
        }
        for (int i = 0; i < V; i++) {
            st = new StringTokenizer(br.readLine());
            int ver = Integer.parseInt(st.nextToken()); // 부모 정점
            int no = Integer.parseInt(st.nextToken());
            while (true) {
                int weight = Integer.parseInt(st.nextToken());
                list.get(ver).add(new Node(no, weight));
                int checker = Integer.parseInt(st.nextToken());
                if (checker == -1) break;
                no = checker;
            }
        }
        visit = new boolean[V + 1];

        visit[1] = true;
        dfs(1, 0);

        Arrays.fill(visit, false);
        visit[maxNode] = true;
        dfs(maxNode, 0);

        System.out.println(max);
    }

    static class Node {
        int no;
        int weight;

        public Node(int no, int weight) {
            this.no = no;
            this.weight = weight;
        }
    }

    static void dfs(int n, int sum) {
        for (Node node : list.get(n)) {
            if (visit[node.no]) continue;
            visit[node.no] = true;
            dfs(node.no, sum + node.weight);
        }
        if (max < sum) {
            max = sum;
            maxNode = n;
        }
    }

}
