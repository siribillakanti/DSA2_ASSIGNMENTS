class Node {

    int data;
    Node left, right;

    Node(int value) {
        data = value;
        left = null;
        right = null;
    }
}

public class assignment_CO1 {

    Node root;

    // Insert node into BST
    Node insert(Node root, int value) {

        if (root == null) {
            return new Node(value);
        }

        if (value < root.data) {
            root.left = insert(root.left, value);
        }

        else if (value > root.data) {
            root.right = insert(root.right, value);
        }

        return root;
    }

    // Search operation
    boolean search(Node root, int key) {

        if (root == null) {
            return false;
        }

        if (root.data == key) {
            return true;
        }

        if (key < root.data) {
            return search(root.left, key);
        }

        return search(root.right, key);
    }

    // Inorder Traversal
    void inorder(Node root) {

        if (root != null) {

            inorder(root.left);

            System.out.print(root.data + " ");

            inorder(root.right);
        }
    }

    public static void main(String[] args) {

        assignment_CO1 tree = new assignment_CO1();

        int values[] = { 50, 30, 70, 20, 40, 60, 80 };

        // Insert values into BST
        for (int value : values) {
            tree.root = tree.insert(tree.root, value);
        }

        // Display inorder traversal
        System.out.println("Inorder Traversal:");
        tree.inorder(tree.root);

        // Search element
        int key = 60;

        if (tree.search(tree.root, key)) {
            System.out.println("\nProfile ID " + key + " found in BST.");
        }

        else {
            System.out.println("\nProfile ID not found.");
        }
    }
}