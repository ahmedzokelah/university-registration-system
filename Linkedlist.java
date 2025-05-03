package linkedlist;

import java.util.Scanner;

public class Linkedlist {
    private static University university = new University();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("University Management System");
        System.out.println("----------------------------");

        boolean running = true;
        while (running) {
            printMenu();
            System.out.print("Enter your choice: ");
            int choice = getIntInput();

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    removeStudent();
                    break;
                case 3:
                    addCourse();
                    break;
                case 4:
                    removeCourse();
                    break;
                case 5:
                    enrollStudent();
                    break;
                case 6:
                    unenrollStudent();
                    break;
                case 7:
                    displayAllStudents();
                    break;
                case 8:
                    displayAllCourses();
                    break;
                case 9:
                    displayStudentCourses();
                    break;
                case 10:
                    displayCourseStudents();
                    break;
                case 11:
                    undoAction();
                    break;
                case 12:
                    redoAction();
                    break;
                case 13:
                    checkCourseAvailability();
                    break;
                case 14:
                    checkStudentStatus();
                    break;
                    case 15:
                    sortStudentCourses();
                    break;
                case 16:
                    sortCourseStudents();
                    break;
                case 0:
                    running = false;
                    System.out.println("Exiting system...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\nMain Menu:");
        System.out.println("1. Add Student");
        System.out.println("2. Remove Student");
        System.out.println("3. Add Course");
        System.out.println("4. Remove Course");
        System.out.println("5. Enroll Student in Course");
        System.out.println("6. Unenroll Student from Course");
        System.out.println("7. Display All Students");
        System.out.println("8. Display All Courses");
        System.out.println("9. Display Student's Courses");
        System.out.println("10. Display Course's Students");
        System.out.println("11. Undo Last Action");
        System.out.println("12. Redo Last Action");
        System.out.println("13. Check Course Availability");
        System.out.println("14. Check Student Status");
        System.out.println("15. Sort Student's Courses");
        System.out.println("16. Sort Course's Students");
        System.out.println("0. Exit");
    }

    private static int getIntInput() {
        while (!scanner.hasNextInt()) {
            System.out.println("Please enter a valid number.");
            scanner.next(); // discard invalid input
        }
        return scanner.nextInt();
    }
      private static void sortStudentCourses() {
        System.out.print("Enter student ID to sort courses: ");
        int studentId = getIntInput();
        university.sort_courses_in_student(studentId);
        System.out.println("Courses sorted for student " + studentId);
        
        // Display the sorted courses
        System.out.println("Sorted courses:");
        university.display_student_courses(studentId);
    }
      private static void sortCourseStudents() {
        System.out.print("Enter course ID to sort students: ");
        int courseId = getIntInput();
        university.sort_students_in_course(courseId);
        System.out.println("Students sorted for course " + courseId);
        
        // Display the sorted students
        System.out.println("Sorted students:");
        university.display_course_students(courseId);
    }

    private static void addStudent() {
        System.out.print("Enter student ID: ");
        int id = getIntInput();
        university.addstudent(id);
        System.out.println("Student " + id + " added successfully.");
    }

    private static void removeStudent() {
        System.out.print("Enter student ID to remove: ");
        int id = getIntInput();
        university.removestudent(id);
        System.out.println("Student " + id + " removed successfully.");
    }

    private static void addCourse() {
        System.out.print("Enter course ID: ");
        int id = getIntInput();
        university.addcourse(id);
        System.out.println("Course " + id + " added successfully.");
    }

    private static void removeCourse() {
        System.out.print("Enter course ID to remove: ");
        int id = getIntInput();
        university.removecourse(id);
        System.out.println("Course " + id + " removed successfully.");
    }

    private static void enrollStudent() {
        System.out.print("Enter student ID: ");
        int studentId = getIntInput();
        System.out.print("Enter course ID: ");
        int courseId = getIntInput();
        university.enrollment(studentId, courseId);
        System.out.println("Student " + studentId + " enrolled in course " + courseId);
    }

    private static void unenrollStudent() {
        System.out.print("Enter student ID: ");
        int studentId = getIntInput();
        System.out.print("Enter course ID: ");
        int courseId = getIntInput();
        university.removeEnrollment(studentId, courseId);
        System.out.println("Student " + studentId + " unenrolled from course " + courseId);
    }

    private static void displayAllStudents() {
        System.out.println("\nList of all students:");
        university.displayallstudents();
    }

    private static void displayAllCourses() {
        System.out.println("\nList of all courses:");
        university.displayallcourses();
    }

    private static void displayStudentCourses() {
        System.out.print("Enter student ID: ");
        int id = getIntInput();
        System.out.println("\nCourses for student " + id + ":");
        university.display_student_courses(id);
    }

    private static void displayCourseStudents() {
        System.out.print("Enter course ID: ");
        int id = getIntInput();
        System.out.println("\nStudents enrolled in course " + id + ":");
        university.display_course_students(id);
    }

    private static void undoAction() {
        university.undo();
        System.out.println("Undo performed successfully.");
    }

    private static void redoAction() {
        university.redo();
        System.out.println("Redo performed successfully.");
    }

    private static void checkCourseAvailability() {
        System.out.print("Enter course ID: ");
        int id = getIntInput();
        university.is_full_course(id);
    }

    private static void checkStudentStatus() {
        System.out.print("Enter student ID: ");
        int id = getIntInput();
        boolean status = university.is_normal_student(id);
        System.out.println("Student " + id + " is " + (status ? "normal" : "not normal"));
    }
}