import java.util.Scanner;
public class es22 {
        static class Node {
        int key;
        int height;
        String val;
        Node left;
        Node right;

        public Node(int key, String val) {
            this.key = key;
            this.height = 1;
            this.val = val;
            this.left = null;
            this.right = null;
        }
    }
   
    public static Node minValueNode(Node node) {
        Node current = node;
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    public static Node delete(Node root, int key) {
        if (root == null) {
            return root;
        }
    
        if (key < root.key) {
            root.left = delete(root.left, key);
        } else if (key > root.key) {
            root.right = delete(root.right, key);
        } else {
            if (root.left == null || root.right == null) {
                Node temp = (root.left != null) ? root.left : root.right;
    
                if (temp == null) {
                    temp = root;
                    root = null;
                } else {
                    root = temp;
                }
            } else {
                Node temp = minValueNode(root.right);
                root.key = temp.key;
                root.val = temp.val; // Update val here
                root.right = delete(root.right, temp.key);
            }
        }
    
        if (root == null) {
            return root;
        }
    
        root.height = 1 + Math.max(height(root.left), height(root.right));
        int balance = getBalance(root);
    
        if (balance > 1 && getBalance(root.left) >= 0) {
            return rotateRight(root);
        }
        if (balance > 1 && getBalance(root.left) < 0) {
            root.left = rotateLeft(root.left);
            return rotateRight(root);
        }
        if (balance < -1 && getBalance(root.right) <= 0) {
            return rotateLeft(root);
        }
        if (balance < -1 && getBalance(root.right) > 0) {
            root.right = rotateRight(root.right);
            return rotateLeft(root);
        }
    
        return root;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Node root = null;

        while (true) {
            String line = scanner.nextLine();
            String[] parts = line.split(" ");
            String command = parts[0];

            if (command.equals("insert")) {
                int key = Integer.parseInt(parts[1]);
                String value = parts[2];
                root = insert(root, key, value);
            } 
            else if(command.equals ("remove")){
                int keyToRemove = Integer.parseInt(parts[1]);
                root = delete(root, keyToRemove);
                
            }
            
            else if (command.equals("show")) {
                printTree(root);
            } else if (command.equals("exit")) {
                break;
            }
            else if (command.equals("find")) {
                int keyToFind = Integer.parseInt(parts[1]);
                search(root, keyToFind);
                break;
            }
        }

        scanner.close();
    }

    public static Node insert(Node node, int key, String value) {
        if (node == null) {
            return new Node(key, value);
        }

        if (key < node.key) {
            node.left = insert(node.left, key, value);
        } else if (key > node.key) {
            node.right = insert(node.right, key, value);
        } else {
            return node; 
        }

        node.height = 1 + Math.max(height(node.left), height(node.right));

        int balance = getBalance(node);

        if (balance > 1) {
            if (key < node.left.key) {
                return rotateRight(node);
            } else {
                node.left = rotateLeft(node.left);
                return rotateRight(node);
            }
        }
        if (balance < -1) {
            if (key > node.right.key) {
                return rotateLeft(node);
            } else {
                node.right = rotateRight(node.right);
                return rotateLeft(node);
            }
        }

        return node;
    }

    static void search(Node n, int key) {
        if (n != null) {
            if (n.key == key) {
                System.out.println(n.val);
            } else if (n.key > key) {
                search(n.left, key);
            } else {
                search(n.right, key);
            }
        }
    }

    public static int height(Node node) {
        if (node == null) {
            return 0;
        }
        return node.height;
    }

    public static int getBalance(Node node) {
        if (node == null) {
            return 0;
        }
        return height(node.left) - height(node.right);
    }

    public static Node rotateRight(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = 1 + Math.max(height(y.left), height(y.right));
        x.height = 1 + Math.max(height(x.left), height(x.right));

        return x;
    }

    public static Node rotateLeft(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = 1 + Math.max(height(x.left), height(x.right));
        y.height = 1 + Math.max(height(y.left), height(y.right));

        return y;
    }

    public static void printTree(Node node) {
        if (node != null) {
            System.out.println(node.key + ":" + node.val + ":" + node.height);
            printTree(node.left);
            printTree(node.right);
        } else {
            System.out.println("NULL");
        }
    }
}
