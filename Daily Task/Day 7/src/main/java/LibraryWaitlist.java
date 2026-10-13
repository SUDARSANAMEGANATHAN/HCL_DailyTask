
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Comparator;

public class LibraryWaitlist {

    static class Request {
        String name;
        String role;

        Request(String name, String role) {
            this.name = name;
            this.role = role;
        }

        public String toString() {
            return name + " (" + role + ")";
        }
    }

    static class Book {
        String title;
        boolean overdue;

        Book(String title, boolean overdue) {
            this.title = title;
            this.overdue = overdue;
        }

        public String toString() {
            return title;
        }
    }

    public static void main(String[] args) {

        // 1. ArrayList catalogue
        ArrayList<String> catalogue = new ArrayList<>();

        catalogue.add("Java");
        catalogue.add("Python");
        catalogue.add("C++");
        catalogue.add("Java");

        System.out.println("Catalogue: " + catalogue);

        // 2. LinkedHashSet removes duplicates
        LinkedHashSet<String> uniqueBooks =
                new LinkedHashSet<>(catalogue);

        System.out.println("Unique books: " + uniqueBooks);

        // 3. ArrayDeque normal waitlist
        ArrayDeque<String> waitlist = new ArrayDeque<>();

        waitlist.offer("Arun");
        waitlist.offer("Bala");
        waitlist.offer("Charan");

        System.out.println("\nNormal waitlist: " + waitlist);
        System.out.println("Serving: " + waitlist.poll());
        System.out.println("Remaining: " + waitlist);

        // 4. PriorityQueue: faculty first
        PriorityQueue<Request> priorityWaitlist =
                new PriorityQueue<>(
                        Comparator.comparingInt(
                                (Request r) ->
                                        r.role.equals("Faculty") ? 1 : 2
                        )
                );

        priorityWaitlist.offer(new Request("Arun", "Student"));
        priorityWaitlist.offer(new Request("Meena", "Faculty"));
        priorityWaitlist.offer(new Request("Bala", "Student"));
        priorityWaitlist.offer(new Request("Ravi", "Faculty"));

        System.out.println("\nPriority waitlist:");

        while (!priorityWaitlist.isEmpty()) {
            System.out.println(priorityWaitlist.poll());
        }

        // 5. Iterator.remove for overdue books
        ArrayList<Book> books = new ArrayList<>();

        books.add(new Book("Java Basics", false));
        books.add(new Book("Old Python Book", true));
        books.add(new Book("Data Structures", false));
        books.add(new Book("Old C Book", true));

        Iterator<Book> iterator = books.iterator();

        while (iterator.hasNext()) {
            Book book = iterator.next();

            if (book.overdue) {
                iterator.remove();
            }
        }

        System.out.println("\nBooks after overdue removal: " + books);

        // 6. ArrayList vs LinkedList performance
        int n = 20000;

        ArrayList<Integer> arrayList = new ArrayList<>();
        LinkedList<Integer> linkedList = new LinkedList<>();

        long start = System.nanoTime();

        for (int i = 0; i < n; i++) {
            arrayList.add(0, i);
        }

        long arrayInsertTime = System.nanoTime() - start;

        start = System.nanoTime();

        for (int i = 0; i < n; i++) {
            linkedList.add(0, i);
        }

        long linkedInsertTime = System.nanoTime() - start;

        start = System.nanoTime();

        for (int i = 0; i < n; i++) {
            arrayList.get(i);
        }

        long arrayGetTime = System.nanoTime() - start;

        start = System.nanoTime();

        for (int i = 0; i < n; i++) {
            linkedList.get(i);
        }

        long linkedGetTime = System.nanoTime() - start;

        System.out.println("\nPerformance comparison (nanoseconds):");
        System.out.println("ArrayList front insertion: " + arrayInsertTime);
        System.out.println("LinkedList front insertion: " + linkedInsertTime);
        System.out.println("ArrayList get(i): " + arrayGetTime);
        System.out.println("LinkedList get(i): " + linkedGetTime);
    }
}
