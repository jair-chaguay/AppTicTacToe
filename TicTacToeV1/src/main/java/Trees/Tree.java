/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Trees;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 * @author User
 */
public class Tree<E> {
    TreeNode<E> root;
    
    public Tree(E contenido) {
        this.root = new TreeNode<>(contenido);
    }
    
    public Tree() {}
    
    public boolean isEmpty() {
        return root == null;
    }
    
    public boolean isLeaf() {
        return root.children.isEmpty();
    }
    
    public E getRoot() {
        return root.contenido;
    }
    
    public void setRoot(E e) {
        root = new TreeNode<>(e);
    }
    
    public List<Tree<E>> getChildren() {
        return root.children;
    }
    
    public void setChild(Tree child) {
        root.children.add(child);
    }
    
    public List<E> recorrer() {
        List<E> result = new LinkedList<>();
        if (isEmpty()) return result;
        
        Deque<Tree<E>> cola = new ArrayDeque<>(); 
        cola.offer(this);
        while(!cola.isEmpty()) {
            Tree<E> tree = cola.poll();
            result.add(tree.root.contenido);
            for (Tree t : tree.getChildren()) {
                cola.offer(t);
            }
        }
        return result;
    }
    
    
    //Probablemente es mejor cambiar estos dos metodos por uno con una interfaz Operation
    public int countLeafs() {
        if (isEmpty()) return 0;
        
        int c = 0;
        Deque<Tree<E>> cola = new ArrayDeque<>();
        cola.offer(this);
        while(!cola.isEmpty()) {
            Tree<E> tree = cola.poll();
            if (!tree.isLeaf()) {
                for (Tree t : tree.getChildren()) {
                    cola.offer(t);
                }
            } else {
                c++;
            }
        }
        return c;
    }
    
    public List<E> getLeafs() {
        List<E> result = new LinkedList<>();
        
        if (isEmpty()) return result;
        
        Deque<Tree<E>> cola = new ArrayDeque<>();
        cola.offer(this);
        while (!cola.isEmpty()) {
            Tree<E> tree = cola.poll();
            if (!tree.isLeaf()) {
                for (Tree t : tree.getChildren()) {
                    cola.offer(t);
                }
            } else {
                result.add(tree.root.contenido);
            }
        }
        return result;
    }
    
//    public ArrayDeque<E> pathTo(E objective) {
//        ArrayDeque<E> result = new ArrayDeque<>();
//        if (isEmpty()) return result;
//        
//        Deque<Tree<E>> cola = new ArrayDeque<>();
//        cola.push(this);
//        
//        while (!cola.isEmpty()) {
//            Tree<E> arbol = cola.pop();
//            result.offerFirst(arbol.root.contenido);
//            if (objective.equals(arbol.root.contenido)) {
//                result.push(this.root.contenido);
//                return result;
//            }
//            if (arbol.isLeaf()) result.poll();
//            for (Tree t : arbol.getChildren()) {
//                cola.push(t);
//            }
//        }        
//        return result;
//    }
    
    public ArrayDeque<E> pathTo(E objetivo) {
        ArrayDeque<E> camino = new ArrayDeque<>();
        if (pathToHelper(this, objetivo, camino)) {
            return camino;
        }
        return new ArrayDeque<>();
    }
    
    private boolean pathToHelper(Tree<E> actual, E objetivo, ArrayDeque<E> camino) {
        if (actual == null) {
            return false;
        }
        camino.push(actual.root.contenido);
        if(objetivo.equals(actual.root.contenido)) return true;
        
        for (Tree<E> t : actual.getChildren()) {
            if (pathToHelper(t, objetivo, camino)) {
                return true;
            }
        }
        camino.pop();
        return false;
    }
    
    private class TreeNode<E> {
        E contenido;
        List<Tree<E>> children;
        
        TreeNode(E contenido) {
            this.contenido = contenido;
            children = new LinkedList<>();
        }       
    } 
}
