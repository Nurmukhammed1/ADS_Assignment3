import java.util.Iterator;
import java.util.Queue;

public class BST<K extends Comparable<K>, V> {

    private Node root;

    private class Node {
        private K key;
        private V value;
        private Node left;
        private Node right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public void put(K key, V value) {
        put(root, key, value);
    }

    private void put(Node current, K key, V value) {
        if (current == null) {
            root = new Node(key, value);
            return;
        }

        Node prev = null;
        while (current != null) {
            if (key.compareTo(current.key) < 0) {
                prev = current;
                current = current.left;
            } else if (key.compareTo(current.key) > 0) {
                prev = current;
                current = current.right;
            } else {
                current.value = value;
                return;
            }
        }

        current = new Node(key, value);

        if (key.compareTo(prev.key) < 0) {
            prev.left = current;
        } else if (key.compareTo(prev.key) > 0) {
            prev.right = current;
        }
    }

    public V get(K key) {
        return get(root, key);
    }

    private V get(Node current, K key) {
        while (current != null) {
            if (key.compareTo(current.key) < 0) {
                current = current.left;
            } else if (key.compareTo(current.key) > 0) {
                current = current.right;
            } else {
                return current.value;
            }
        }
        return null;
    }

    public void delete(K key) {
        delete(root, key);
    }

    private void delete(Node current, K key) {
        Node parent =null;
        while(current!=null) {
            if (key.compareTo(current.key) < 0) {
                parent=current;
                current =current.left;
            } else if (key.compareTo(current.key) > 0) {
                parent = current;
                current = current.right;
            }
            else
            {
                if(current.left==null && current.right==null)
                {
                    if(parent.left==current)
                        parent.left=null;
                    else if(parent.right==current)
                        parent.right=null;

                    current = null;

                    break;
                }
                else
                {
                    if(current.left==null)
                    {
                        if(parent.right==current)
                            parent.right=current.right;
                        else if(parent.left==current)
                            parent.left=current.right;
                        current=null;
                        break;
                    }
                    else if(current.right==null)
                    {
                        if(parent.left==current)
                            parent.left=current.left;
                        else if(parent.right==current)
                            parent.right=current.left;
                        current=null;
                        break;
                    }
                    else
                    {
                        Node temp=current;
                        Node px=null;
                        temp=temp.right;
                        while(temp.left!=null)
                        { px=temp;
                            temp=temp.left;
                        }
                        current.key=temp.key;
                        if(px.left==temp)
                            px.left=null;
                        else if(px.left==temp)
                            px.right=null;
                        temp=null;
                        break;
                    }
                }
            }
        }
    }

}
