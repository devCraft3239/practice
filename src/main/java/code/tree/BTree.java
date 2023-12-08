package code.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 *                  1  level 1
 *                 / \
 *               /    \
 *              2      3
 *             / \
 *            /   \
 *           4    5
 * */

public class BTree {
    BTree.Node root;

    static class Node {
        int data;
        BTree.Node left, right;
    }

    BTree(){
        this.root = null;
    }

    BTree(BTree.Node root){
        this.root = root;
    }

    int height(BTree.Node root){
        if(root == null)
            return 0;
        return Math.max(height(root.left), height(root.right))+1;
    }

    void printLevelOrder(BTree.Node root){
        int h =  height(root); // O(logn)
        for (int i = 0; i < h; i++) {
            printCurrentLevel(root,i);
        }
    }

    // BFS traversal
    void printCurrentLevel(BTree.Node root, int level){
        if (root == null)
            return;
        if (level ==  0)
            System.out.println(root.data);
        if(level > 0){
            printCurrentLevel(root.left, level-1);
            printCurrentLevel(root.right, level-1);
        }
    }

    void printLevelOrderQueue(BTree.Node root){
        Queue<BTree.Node> queue =  new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()){
            BTree.Node node =  queue.poll();
            System.out.println(node.data);
            if(node.left != null)
                queue.add(node.left);
            if (node.right != null)
                queue.add(node.right);
        }
    }


    // DFS traversal
    void preOrder(BTree.Node root){
        if (root == null)
            return;
        System.out.println(root.data);
        preOrder(root.left);
        preOrder(root.right);
    }

    void inOrder(BTree.Node root){
        if (root == null)
            return;
        preOrder(root.left);
        System.out.println(root.data);
        preOrder(root.right);
    }

    void postOrder(BTree.Node root){
        if (root == null)
            return;
        preOrder(root.left);
        preOrder(root.right);
        System.out.println(root.data);
    }

}