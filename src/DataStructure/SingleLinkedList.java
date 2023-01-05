package DataStructure;

/**
 * 링크드 리스트 직접 구현해보기
 * 리스트의 마지막에 데이터 추가 하는것만 해봄
 */
public class SingleLinkedList<T> {
    public Node<T> head = null;
    public static class Node<T> {
        T data;
        Node<T> next = null;

        public Node(T data) {
            this.data = data;
        }
    }
    public void addNode(T data) {
        if(head == null){
            head = new Node<T>(data);

        }else{
            Node<T> node = this.head;
            while(node.next != null){
                node = node.next;
            }
            node.next = new Node<T>(data);
        }
    }

    public static void main(String[] args) {
        SingleLinkedList<Integer> MyLinkedList = new SingleLinkedList<>();
        MyLinkedList.addNode(1);
        MyLinkedList.addNode(2);

        System.out.println(MyLinkedList.head.data);
        System.out.println(MyLinkedList.head.next.data);
    }
}