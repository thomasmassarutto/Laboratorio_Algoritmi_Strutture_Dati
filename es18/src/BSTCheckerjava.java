import java.util.*;
import java.util.Scanner;

class BSTChecker {
    // Utilizziamo un array di lunghezza 1 per mantenere il valore precedente
    private TreeNode[] prev = { null };

    public boolean isBST(TreeNode root) {
        return isBST(root, prev);
    }

    private boolean isBST(TreeNode node, TreeNode[] prev) {
        if (node == null) {
            return true;
        }

        // Verifica il sottoalbero sinistro
        if (!isBST(node.left, prev)) {
            return false;
        }

        // Controlla se il valore del nodo corrente è maggiore del precedente
        if (prev[0] != null && node.val <= prev[0].val) {
            return false;
        }

        prev[0] = node;

        // Verifica il sottoalbero destro
        return isBST(node.right, prev);
    }
}
