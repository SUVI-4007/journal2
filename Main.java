import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

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

    void displayInfo() {
        int age = Period.between(dob, LocalDate.now()).getYears();
        System.out.println("Name: " + name + ", Age: " + age);
    }
}

class StudentCourses {
    String[] courses = {"Math", "Science", "English"};
    int[] marks = {85, 90, 78};

    void displayCourses() {
        System.out.println("Courses and Marks:");
        for (int i = 0; i < courses.length; i++) {
            System.out.println(courses[i] + ": " + marks[i]);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Student s = new Student("Alice", "2003-08-15");
        s.displayInfo();

        StudentCourses sc = new StudentCourses();
        sc.displayCourses();
    }
}
