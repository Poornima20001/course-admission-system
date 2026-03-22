package realworld.java;

import java.util.*;

public class Institute {

    private Scanner sc = new Scanner(System.in);

    // Seats for courses
    private int javaSeat = 1;
    private int pythonSeat = 6;
    private int dotnetSeat = 4;

    // Enrolled students
    private ArrayList<Student> javaStudents = new ArrayList<>();
    private ArrayList<Student> pythonStudents = new ArrayList<>();
    private ArrayList<Student> dotnetStudents = new ArrayList<>();

    // Waiting lists
    private Queue<Student> javaWaiting = new LinkedList<>();
    private Queue<Student> pythonWaiting = new LinkedList<>();
    private Queue<Student> dotnetWaiting = new LinkedList<>();

    // Generate random student ID
    private int generateId() {
        return (int) (Math.random() * 9000 + 1000);
    }

    // Admission method
    public void admission() {
        sc.nextLine(); // consume newline
        System.out.print("Enter your name: ");
        String name = sc.nextLine();

        System.out.print("Enter the course (java/python/dotnet): ");
        String course = sc.nextLine().toLowerCase();

        int id = generateId();
        Student s = new Student(id, name);

        switch (course) {
            case "java":
                if (javaSeat > 0) {
                    javaStudents.add(s);
                    javaSeat--;
                    System.out.println(name + " admitted to Java, ID: " + id);
                } else {
                    javaWaiting.add(s);
                    System.out.println(name + " added to Java waiting list");
                }
                break;

            case "python":
                if (pythonSeat > 0) {
                    pythonStudents.add(s);
                    pythonSeat--;
                    System.out.println(name + " admitted to Python, ID: " + id);
                } else {
                    pythonWaiting.add(s);
                    System.out.println(name + " added to Python waiting list");
                }
                break;

            case "dotnet":
                if (dotnetSeat > 0) {
                    dotnetStudents.add(s);
                    dotnetSeat--;
                    System.out.println(name + " admitted to DotNet, ID: " + id);
                } else {
                    dotnetWaiting.add(s);
                    System.out.println(name + " added to DotNet waiting list");
                }
                break;

            default:
                System.out.println("Invalid course selection!");
        }
    }

    // Discontinue student
    public void discontinue() {
        sc.nextLine(); // consume newline
        System.out.print("Enter course (java/python/dotnet): ");
        String course = sc.nextLine().toLowerCase();

        switch (course) {
            case "java":
                removeStudent(javaStudents, javaWaiting, "Java");
                break;
            case "python":
                removeStudent(pythonStudents, pythonWaiting, "Python");
                break;
            case "dotnet":
                removeStudent(dotnetStudents, dotnetWaiting, "DotNet");
                break;
            default:
                System.out.println("Invalid course!");
        }
    }

    private void removeStudent(ArrayList<Student> students, Queue<Student> waiting, String courseName) {
        System.out.print("Enter Student ID to discontinue: ");
        int id = sc.nextInt();

        Iterator<Student> it = students.iterator();
        boolean found = false;

        while (it.hasNext()) {
            Student s = it.next();
            if (s.getId() == id) {
                System.out.println(s.getName() + " discontinued from " + courseName);
                it.remove();
                found = true;

                if (!waiting.isEmpty()) {
                    Student ws = waiting.poll();
                    students.add(ws);
                    System.out.println(ws.getName() + " moved from waiting list to " + courseName);
                }
                break;
            }
        }

        if (!found) {
            System.out.println("Student not found in " + courseName);
        }
    }
}
