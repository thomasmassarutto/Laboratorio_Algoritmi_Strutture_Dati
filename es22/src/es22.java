
import java.util.Scanner;

class AVLNode {
    int key;
    int height;
    String val;
    AVLNode left;
    AVLNode right;

    AVLNode(int k, String v) {
        key = k;
        height = 1;
        val = v;
        left = null;
        right = null;
    }
}

public class es22 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AVLNode root = null;

        while (true) {
            String input = scanner.nextLine();
            String[] tokens = input.split(" ");
            String command = tokens[0];

            switch (command) {
                case "insert":
                    int key = Integer.parseInt(tokens[1]);
                    String value = tokens[2];
                    root = insertAVL(root, key, value);
                    break;
                case "remove":
                    int removeKey = Integer.parseInt(tokens[1]);
                    root = delete(root, removeKey);
                    break;
                case "find":
                    int findKey = Integer.parseInt(tokens[1]);
                    search(root, findKey);
                    break;
                case "clear":
                    root = null;
                    break;
                case "show":
                    printPreOrder(root);
                    System.out.println();
                    break;
                default:
                    scanner.close();
                    return;
            }
        }
    }

    static AVLNode insertAVL(AVLNode node, int key, String value) {
        if (node == null) {
            return new AVLNode(key, value);
        } else {
            if (node.key > key) {
                node.left = insertAVL(node.left, key, value);
            } else {
                node.right = insertAVL(node.right, key, value);
            }
            node = updateHeight(node);
            node = balance(node);
            return node;
        }
    }

    static AVLNode rotateLeft(AVLNode x) {
        AVLNode t = x.right;
        x.right = t.left;
        x = updateHeight(x);
        t.left = x;
        t = updateHeight(t);
        return t;
    }

    static AVLNode rotateRight(AVLNode x) {
        AVLNode t = x.left;
        x.left = t.right;
        x = updateHeight(x);
        t.right = x;
        t = updateHeight(t);
        return t;
    }

    static AVLNode rotateLeftRight(AVLNode x) {
        x.left = rotateLeft(x.left);
        return rotateRight(x);
    }

    static AVLNode rotateRightLeft(AVLNode x) {
        x.right = rotateRight(x.right);
        return rotateLeft(x);
    }

    static void deleteTree(AVLNode tree) {
        if (tree != null) {
            deleteTree(tree.left);
            deleteTree(tree.right);
        }
    }

    static void printPreOrder(AVLNode node) {
        if (node != null) {
            System.out.print(node.key + ":" + node.val + ":" + node.height + " ");
            printPreOrder(node.left);
            printPreOrder(node.right);
        } else {
            System.out.print("NULL ");
        }
    }

    static void search(AVLNode node, int key) {
        if (node != null) {
            if (node.key == key) {
                System.out.println(node.val);
            } else if (node.key > key) {
                search(node.left, key);
            } else {
                search(node.right, key);
            }
        }
    }

    static AVLNode delete(AVLNode node, int key) {
        if (node == null) {
            return null;
        }
        if (node.key == key) {
            if (node.left == null && node.right == null) {
                return null;
            } else if (node.right == null) {
                return node.left;
            } else if (node.left == null) {
                return node.right;
            } else {
                AVLNode[] p = new AVLNode[1];
                node.right = extractSuccessor(node.right, p);
                p[0].left = node.left;
                p[0].right = node.right;
                p[0] = updateHeight(p[0]);
                p[0] = balance(p[0]);
                return p[0];
            }
        } else {
            if (node.key > key) {
                node.left = delete(node.left, key);
                node = updateHeight(node);
                node = balance(node);
            } else {
                node.right = delete(node.right, key);
                node = updateHeight(node);
                node = balance(node);
            }
            return node;
        }
    }
    
    static AVLNode extractSuccessor(AVLNode node, AVLNode[] successor) {
        if (node.left != null) {
            if (node.left.left != null) {
                node.left = extractSuccessor(node.left, successor);
                node = updateHeight(node);
                node = balance(node);
                return node;
            } else {
                successor[0] = node.left;
                node.left = node.left.right;
                node = updateHeight(node);
                node = balance(node);
                return node;
            }
        } else {
            successor[0] = node;
            return node.right;
        }
    }

    static AVLNode updateHeight(AVLNode node) {
        int hl = (node.left != null) ? node.left.height : 0;
        int hr = (node.right != null) ? node.right.height : 0;
        node.height = 1 + Math.max(hl, hr);
        return node;
    }

    static int getBalanceFactor(AVLNode node) {
        int hl = (node.left != null) ? node.left.height : 0;
        int hr = (node.right != null) ? node.right.height : 0;
        return hr - hl;
    }

    static AVLNode balance(AVLNode node) {
        int balanceFactor = getBalanceFactor(node);
        
        if (balanceFactor > 1) { // Right heavy
            if (getBalanceFactor(node.right) < 0) {
                return rotateRightLeft(node);
            } else {
                return rotateLeft(node);
            }
        } else if (balanceFactor < -1) { // Left heavy
            if (getBalanceFactor(node.left) > 0) {
                return rotateLeftRight(node);
            } else {
                return rotateRight(node);
            }
        }
        return node;
    }
}
