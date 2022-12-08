package homework;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main
{
  private static Register register;

  public static void main(String[] args)
  {
    Scanner scanner = new Scanner(System.in);
    prepareData();
    int menu = 1;

    while (menu != 0)
    {
      printMenu();
      menu = scanner.nextInt();

      try
      {
        switch (menu)
        {
          case 1:
            createStudent();
            break;
          case 2:
            getStudentInfo();
            break;
          case 3:
            addMarksForStudent();
            break;
          case 4:
            calculateStudentAverageMark();
            break;
          case 5:
            calculateGroupAverageMark();
            break;
          case 6:
            calculateStudentAverageMarkBySubject();
            break;
          case 0:
            System.out.println("Closing application...");
            break;
          default:
            System.out.println("Please, select correct point (0-6)");
            break;
        }
      } catch (Exception e)
      {
        System.out.println("Error! " + e.getMessage());
      }
    }
  }

  private static void prepareData()
  {
    System.out.println("Starting application...");

    register = new Register();

    Student firstStudent = new Student("Alan", Group.IO_7_2);
    register.addStudent(firstStudent);
    register.addMarksForStudent(1, Subject.ENGLISH, List.of(4.0));
    register.addMarksForStudent(1, Subject.SALESFORCE_PROGRAMMING, List.of(2.0, 4.0));
    register.addMarksForStudent(1, Subject.JAVA_PROGRAMMING, List.of(2.0));

    Student secondStudent = new Student("Kamil", Group.IO_7_2);
    register.addStudent(secondStudent);
    register.addMarksForStudent(2, Subject.ENGLISH, List.of(5.0));
    register.addMarksForStudent(2, Subject.SALESFORCE_PROGRAMMING, List.of(1.0, 4.0));
    register.addMarksForStudent(2, Subject.JAVA_PROGRAMMING, List.of());

    System.out.println("\nApplication is running");
  }

  private static void printMenu()
  {
    String menu = "\n[1] - Create Student\n" +
        "[2] - Get Student Info\n" +
        "[3] - Add Marks For Student\n" +
        "[4] - Calculate Student Average Mark\n" +
        "[5] - Calculate Group Average Mark\n" +
        "[6] - Calculate Student Average Mark By Subject\n" +
        "[0] - Exit";
    System.out.println(menu);
  }

  private static void createStudent()
  {
    Scanner scanner = new Scanner(System.in);

    System.out.println("Enter student name:");
    String name = scanner.nextLine();
    System.out.printf("Select group for student: %s\n", Arrays.toString(Group.values()));
    String groupAsString = scanner.nextLine();
    Group group = Group.valueOf(groupAsString);

    Student student = new Student(name, group);
    register.addStudent(student);
  }

  private static void getStudentInfo()
  {
    Scanner scanner = new Scanner(System.in);

    System.out.println("Enter student id: ");
    int id = scanner.nextInt();

    register.getStudentInfo(id);
  }

  private static void addMarksForStudent()
  {
    Scanner scanner = new Scanner(System.in);

    System.out.println("Enter student id: ");
    int id = scanner.nextInt();
    System.out.printf("Select subject: %s\n", Arrays.toString(Subject.values()));

    scanner = new Scanner(System.in);
    String subjectAsString = scanner.nextLine();
    Subject subject = Subject.valueOf(subjectAsString);

    System.out.println("Enter marks from {2, 3, 3.5, 4, 4.5, 5} separated by a space (ex. 2 4 3.5): ");
    String[] marksAsString = scanner.nextLine().split(" ");
    List<Double> marks = new ArrayList<>();

    for (String s : marksAsString)
    {
      marks.add(Double.valueOf(s));
    }

    register.addMarksForStudent(id, subject, marks);
  }

  private static void calculateStudentAverageMark()
  {
    Scanner scanner = new Scanner(System.in);

    System.out.println("Enter student id: ");
    int id = scanner.nextInt();

    register.getStudentAverageMark(id);
  }

  private static void calculateGroupAverageMark()
  {
    Scanner scanner = new Scanner(System.in);

    System.out.println("Select group: " + Arrays.toString(Group.values()));
    String groupAsString = scanner.nextLine();
    Group group = Group.valueOf(groupAsString);

    register.getGroupAverageMark(group);
  }

  private static void calculateStudentAverageMarkBySubject()
  {
    Scanner scanner = new Scanner(System.in);

    System.out.println("Enter student id: ");
    int id = scanner.nextInt();

    scanner = new Scanner(System.in);
    System.out.println("Select subject: " + Arrays.toString(Subject.values()));
    String subjectAsString = scanner.nextLine();
    Subject subject = Subject.valueOf(subjectAsString);

    register.getStudentAverageMarkBySubject(id, subject);
  }
}