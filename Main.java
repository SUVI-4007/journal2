import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;

class Student {
    String name;
    LocalDate dob;

    Student(String name, String dobStr) {
        this.name = name;
        DateTimeFormatter formatter = dobStr.contains("-") && dobStr.length() == 10 && dobStr.charAt(2) == '-'
                ? DateTimeFormatter.ofPattern("dd-MM-yyyy")
                : DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.dob = LocalDate.parse(dobStr, formatter);
    }

    int getAge() {
        return Period.between(dob, LocalDate.now()).getYears();
    }

    void displayInfo() {
        System.out.println("Name: " + name + ", Age: " + getAge());
    }
}

class Course {
    String courseName;
    int marks;

    Course(String courseName, int marks) {
        this.courseName = courseName;
        this.marks = marks;
    }
}

class StudentCourses {
    Map<String, List<Course>> studentCourses = new HashMap<>();

    void addCourses(String studentName, List<Course> courses) {
        studentCourses.put(studentName, courses);
    }

    void displayCourses(String studentName) {
        List<Course> courses = studentCourses.get(studentName);
        if (courses == null || courses.isEmpty()) {
            System.out.println("No courses found for " + studentName);
            return;
        }

        System.out.println("Courses and Marks for " + studentName + ":");
        int total = 0;
        for (Course c : courses) {
            System.out.println(c.courseName + ": " + c.marks);
            total += c.marks;
        }
        double avg = (double) total / courses.size();
        System.out.println("Average Marks: " + avg);
    }

    void displaySortedCourses(String studentName) {
        List<Course> courses = studentCourses.get(studentName);
        if (courses == null || courses.isEmpty()) {
            System.out.println("No courses found for " + studentName);
            return;
        }

        courses.sort((c1, c2) -> Integer.compare(c2.marks, c1.marks));

        System.out.println("Courses for " + studentName + " (Sorted by Marks):");
        for (Course c : courses) {
            System.out.println(c.courseName + ": " + c.marks);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Student s1 = new Student("Alice", "2003-08-15");
        Student s2 = new Student("Bob", "15-09-2002");
        Student s3 = new Student("Charlie", "2004-06-20");

        s1.displayInfo();
        s2.displayInfo();
        s3.displayInfo();

        StudentCourses sc = new StudentCourses();

        sc.addCourses("Alice", Arrays.asList(
                new Course("Math", 85),
                new Course("Science", 90),
                new Course("English", 78)
        ));

        sc.addCourses("Bob", Arrays.asList(
                new Course("Math", 72),
                new Course("History", 88),
                new Course("Biology", 91)
        ));

        sc.addCourses("Charlie", Arrays.asList(
                new Course("Physics", 80),
                new Course("Chemistry", 84),
                new Course("Math", 92)
        ));

        System.out.println();
        sc.displayCourses("Alice");
        System.out.println();
        sc.displayCourses("Bob");
        System.out.println();
        sc.displayCourses("Charlie");

        System.out.println("\nSorted Course Marks:");
        sc.displaySortedCourses("Charlie");
    }
}
