package code.tree;

import com.sun.org.apache.xerces.internal.dom.DeepNodeListImpl;

import javax.swing.*;

public class BST {
    Node root;
    static class Node{
        int data;
        Node left, right;

        Node(int data){
            this.data =  data;
            this.left = null;
            this.right = null;
        }
    }

    public void insert(int data){
        root =  insertRecur(root, data);
    }

    public Node insertRecur(Node root, int data){
        if (root == null)
            return new Node(data);
        if (data < root.data)
            root.left = insertRecur(root.left, data);
        else
            root.right = insertRecur(root.right, data);
        return root;
    }

    public Node searchKeyRecur(Node root, int key) {
        if (root == null)
            return null;
        if (root.data == key)
            return root;
        if (key < root.data)
            return searchKeyRecur(root.left, key);
        else
            return searchKeyRecur(root.right, key);
    }

    public Node deleteRecur(Node root, int key){
        if (root == null)
            return null;
        if (key < root.data)
            root.left = deleteRecur(root.left, key);
        else if(key > root.data)
            root.right = deleteRecur(root.right, key);
        else{
            if(root.left == null && root.right == null) // both childs are null
                return null;
            else if(root.right  == null)  // one child is null
                return root.left;
            else if(root.left  == null) // one child is null
                return root.right;
            else{ // both the childs are non-null
                Node temp =  minNode(root.right);
                root.data = temp.data;
                root.right = deleteRecur(root.right, temp.data);
            }
        }
        return root;
    }

    public Node minNode(Node root){
        while (root.left  != null)
            root =  root.left;
        return root;
    }


    public static boolean checkBST(Node root){
        if (root == null)
            return true;
        if (root.data < root.left.data || root.data > root.right.data)
            return false;
        return checkBST(root.left) && checkBST(root.right);
    }
}
