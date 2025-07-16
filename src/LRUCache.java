import java.util.*;

 class LRUNode {
    int key;
    int val;
    LRUNode prev;
    LRUNode next;

    LRUNode(int key, int val) {
        this.key = key;
        this.val = val;
        next = null;
        prev = null;

    }

}
public class LRUCache {

    Map<Integer, LRUNode> cache;
    int capacity;;
    LRUNode head;
    LRUNode tail;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.cache = new HashMap<>();
        this.head = new LRUNode(0, 0);
        this.tail = new LRUNode(0, 0);
        this.head.next = this.tail;
        this.tail.prev = this.head;
    }

    public int get(int key) {
        if (cache.containsKey(key)) {
            LRUNode currNode = cache.get(key);
            remove(currNode);
            insert(currNode);

            return currNode.val;
        }
        return -1;

    }

    public void put(int key, int value) {
        if (cache.containsKey(key)) {
            remove(cache.get(key));
        }
        LRUNode currNode = new LRUNode(key, value);

        cache.put(key, currNode);
        capacity++;
        if (cache.size() > capacity) {
            remove(this.head.next);
        }
    }
    private void remove(LRUNode node) {
        LRUNode prev = node.prev;
        LRUNode nxt = node.next;
        prev.next = nxt;
        nxt.prev = prev;
    }

    private void insert(LRUNode node) {
        LRUNode prev = this.tail.prev;
        prev.next = node;
        node.prev = prev;
        node.next = this.tail;
        this.tail.prev = node;
    }
}
