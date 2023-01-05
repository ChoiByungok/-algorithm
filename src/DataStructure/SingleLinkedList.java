package DataStructure;

/**
 * 링크드 리스트 직접 구현해보기
 * 리스트의 마지막에 데이터 추가 하는것만 해봄
 * 리스트를 출력하는것 해봄
 * 리스트 사이에 데이터 추가하는것 해봄
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
    public void printAll() {
        if(head == null){
            System.out.println("데이터 없음");
        }else {
            Node<T> node = this.head;
            while (node != null){
                System.out.print(node.data + " ");
                node = node.next;
            }
        }
    }
    public Node<T> search(T data) {
        if(head == null){
            return null;
        }else {
            Node<T> node = this.head;
            while (node != null){
                if(node.data == data){
                    return node;
                }else {
                    node = node.next;
                }
            }
            return null;
        }
    }

    /**
     *
     * @param data 어떤 데이터 뒤에 넣을 것인가 없을 시 맨 뒤에 삽입됨
     * @param isData 집어넣을 데이터
     */
    public void addNodeInside(T data, T isData) {
        Node<T> search = this.search(isData);
        if(search == null){
            this.addNode(data);
        }else {
            Node<T> nextNode = search.next;
            search.next = new Node<>(data);
            search.next.next = nextNode;
        }
    }

    public static void main(String[] args) {
        SingleLinkedList<Integer> MyLinkedList = new SingleLinkedList<>();
        MyLinkedList.addNode(1);
        MyLinkedList.addNode(2);
        MyLinkedList.addNode(3);
        MyLinkedList.addNode(4);

        MyLinkedList.addNodeInside(5,1);
        MyLinkedList.addNodeInside(10,5);


        MyLinkedList.printAll();
    }
}