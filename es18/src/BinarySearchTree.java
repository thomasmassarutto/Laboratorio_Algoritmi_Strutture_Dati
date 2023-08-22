import java.util.*;
import java.util.Scanner;

public class BinarySearchTree {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String input = scanner.nextLine();

        scanner.close();

        String[] tokens = input.split(" ");
        int[] index = {0}; // Usiamo un array per mantenere traccia dell'indice durante il parsing

        TreeNode root = buildTree(tokens, index);

        BSTChecker checker = new BSTChecker();

        if (checker.isBST(root)) {
            System.out.println("1");
        } else {
            System.out.println("0");
        }
    }

    private static TreeNode buildTree(String[] tokens, int[] index) {
        if (index[0] >= tokens.length || tokens[index[0]].equals("NULL")) {
            index[0]++;
            return null;
        }

        TreeNode node = new TreeNode(Integer.parseInt(tokens[index[0]]));
        index[0]++;

        node.left = buildTree(tokens, index);
        node.right = buildTree(tokens, index);

        return node;
    }
}
