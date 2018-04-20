
//Created by John-Tyler Cooper

public class LinkedListADT {
	// Here is me test driver the HW3, Hope you enjoy!!!
	public static void main(String[] args) {

		LList2<String> myLinkedList = new LList2<>();
		myLinkedList.insert("Z");
		myLinkedList.insert("C");
		myLinkedList.insert("A");
		myLinkedList.insert("F");
		myLinkedList.insert("E");
		myLinkedList.moveToPos(1);
		myLinkedList.remove("F");
		myLinkedList.insert("D");
		myLinkedList.insert("B");
		myLinkedList.insert("G");
		System.out.print(myLinkedList.toString());
	}

}
