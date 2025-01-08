import java.util.Scanner;

class Book {
String title;
String author;
int publicationYear;

public Book(String title, String author, int publicationYear) {
this.title = title;
this.author = author;
this.publicationYear = publicationYear;
}
}

class MinHeap {
Book[] heap;
int size;

public MinHeap(int capacity) {
heap = new Book[capacity];
size = 0;
}

// Add book to heap
public void add(Book book) {
if (size == heap.length) {
System.out.println("Heap is full!");
return;
}
heap[size] = book;
bubbleUp(size);
size++;
}

// Remove and return oldest book
public Book removeMin() {
if (size == 0) {
System.out.println("Heap is empty!");
return null;
}
Book min = heap[0];
heap[0] = heap[size - 1];
size--;
bubbleDown(0);
return min;
}

// Display books in heap
public void displayBooks() {
for (int i = 0; i < size; i++) {
System.out.println(heap[i].title + " by " + heap[i].author + " - Published: " + heap[i].publicationYear);
}
}

// Helper methods for heap maintenance
private void bubbleUp(int index) {
int parentIndex = (index - 1) / 2;
if (index > 0 && heap[parentIndex].publicationYear > heap[index].publicationYear) {
swap(parentIndex, index);
bubbleUp(parentIndex);
}
}

private void bubbleDown(int index) {
int leftChildIndex = 2 * index + 1;
int rightChildIndex = 2 * index + 2;
int smallestIndex = index;

if (leftChildIndex < size && heap[leftChildIndex].publicationYear < heap[smallestIndex].publicationYear) {
smallestIndex = leftChildIndex;
}

if (rightChildIndex < size && heap[rightChildIndex].publicationYear < heap[smallestIndex].publicationYear) {
smallestIndex = rightChildIndex;
}

if (smallestIndex != index) {
swap(smallestIndex, index);
bubbleDown(smallestIndex);
}
}

private void swap(int i, int j) {
Book temp = heap[i];
heap[i] = heap[j];
heap[j] = temp;
}
}

public class LibraryBookManagementSystem {
public static void main(String[] args) {
MinHeap bookHeap = new MinHeap(10);
Scanner scanner = new Scanner(System.in);

while (true) {
System.out.println("\n1. Add Book");
System.out.println("2. Borrow Oldest Book");
System.out.println("3. Display Books");
System.out.println("4. Exit");
System.out.print("Choose an option: ");
int option = scanner.nextInt();
scanner.nextLine(); // Consume newline left-over

switch (option) {
case 1:
System.out.print("Enter book title: ");
String title = scanner.nextLine();
System.out.print("Enter author: ");
String author = scanner.nextLine();
System.out.print("Enter publication year: ");
int publicationYear = scanner.nextInt();
scanner.nextLine(); // Consume newline left-over
bookHeap.add(new Book(title, author, publicationYear));
break;
case 2:
Book oldestBook = bookHeap.removeMin();
if (oldestBook != null) {
System.out.println("Borrowing book: " + oldestBook.title + " by " + oldestBook.author + " - Published: " + oldestBook.publicationYear);
}
break;
case 3:
bookHeap.displayBooks();
break;
case 4:
System.out.println("Exiting...");
return;
default:
System.out.println("Invalid option. Please choose again.");
}
}
}
}