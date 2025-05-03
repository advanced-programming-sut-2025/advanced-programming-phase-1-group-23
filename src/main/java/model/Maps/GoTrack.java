package model.Maps;

import javax.swing.text.TabableView;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class GoTrack {
    public static ArrayList<Tile> cells;
    private static final int[][] DIRECTIONS = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};

    public static void pathBFS(Tile src, Tile dest, ArrayList<Tile> arr) {
        cells = arr;
        boolean[][] visited = new boolean[9][10];
        PriorityQueue<Tile> queue = new PriorityQueue<>(new CellComparator());
        queue.add(src);
        visited[src.getCoordinate().getX()][src.getCoordinate().getY()] = true;
        while (!queue.isEmpty()) {
            Tile curr = queue.poll();
            for (int[] dir : DIRECTIONS) {
                int newX = curr.getCoordinate().getX() + dir[0];
                int newY = curr.getCoordinate().getY() + dir[1];
                if (newX >= 0 && newX < 9 && newY >= 0 && newY < 10 && !visited[newX][newY]) {
                    Tile neighbour = findCell(newX, newY);
                    if (!neighbour.getObjectOnCell().canWalk) {
                        continue;
                    }
                    neighbour.prev = curr;
                    neighbour.distance = neighbour.prev.distance + 1;
                    if (curr.prev == null) {
                        neighbour.turns = curr.turns;
                    } else if (neighbour.diffXPrev() == curr.diffXPrev() && neighbour.diffYPrev() == curr.diffYPrev()) {
                        neighbour.turns = neighbour.prev.turns;
                    } else {
                        neighbour.turns = neighbour.prev.turns + 1;
                    }
                    for (int[] dir2 : DIRECTIONS) {
                        int i = newX + dir2[0];
                        int j = newY + dir2[1];
                        Tile d = findCell(i, j);
                        if (d != null && i == dest.getCoordinate().getX() && j == dest.getCoordinate().getY()) {
                            d.prev = neighbour;
                            if (!(neighbour.diffXPrev() == d.diffXPrev() && neighbour.diffYPrev() == d.diffYPrev())) {
                                neighbour.turns += 1;
                            }
                        }
                    }
                    neighbour.energy = (neighbour.distance + 10 * neighbour.turns);
                    visited[neighbour.getCoordinate().getX()][neighbour.getCoordinate().getY()] = true;
                    if ((newX == 0 && newY == 3) || (newX == 1 && newY == 3)) {
                        int x = 5;
                    }
                    queue.add(neighbour);
                }
            }
        }
    }

    public static class CellComparator implements Comparator<Tile> {
        @Override
        public int compare(Tile c1, Tile c2) {
            return Double.compare(c1.energy, c2.energy);
        }
    }

    public static Tile findCell(int x, int y) {
        for (Tile cell : cells) {
            if (cell.getCoordinate().getX() == x && cell.getCoordinate().getY() == y) {
                return cell;
            }
        }
        return null;
    }
}
