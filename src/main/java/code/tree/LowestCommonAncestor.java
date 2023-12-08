package code.tree;

public class LowestCommonAncestor {
    public static BST.Node findLCA(BST.Node root, int num1, int num2){
        if (root == null)
            return null;
        if (num1 < root.data && num2 < root.data) // both the keys present in left subtree
            return findLCA(root.left, num1, num2);
        else if (num1 > root.data && num2 > root.data) // both the keys present in right subtree
            return findLCA(root.right, num1, num2);
        else
            return root; // one key is present in left subtree and another ein right subtree
    }
}
