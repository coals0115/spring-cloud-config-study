package org.example.springcloudconfig;

import java.util.Scanner;

public class HarvestWaterloo {

    static int R, C;
    static char[][] field;
    static boolean[][] visited;
    static int totalValue = 0;

    record Point(int row, int col) {}

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        R = Integer.parseInt(sc.nextLine());
        C = Integer.parseInt(sc.nextLine());
        field = new char[R][C];
        visited = new boolean[R][C];

        for (int i = 0; i < R; i++) {
            field[i] = sc.nextLine().toCharArray();
        }

        int startRow = Integer.parseInt(sc.nextLine());
        int startCol = Integer.parseInt(sc.nextLine());

        harvest(new Point(startRow, startCol));

        System.out.println(totalValue);
    }

    static void harvest(Point p) {
        int row = p.row();
        int col = p.col();

        if (row < 0 || row >= R || col < 0 || col >= C) return;
        if (visited[row][col] || field[row][col] == '*') return;

        visited[row][col] = true;

        totalValue += switch (field[row][col]) {
            case 'S' -> 1;
            case 'M' -> 5;
            case 'L' -> 10;
            default -> 0; // 혹시 다른 문자가 있을 경우
        };

        // 4방향 재귀 호출
        harvest(new Point(row - 1, col)); // 위
        harvest(new Point(row + 1, col)); // 아래
        harvest(new Point(row, col - 1)); // 왼쪽
        harvest(new Point(row, col + 1)); // 오른쪽
    }
}