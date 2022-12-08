package homework;


import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UnitTests {
  private Register register;

  @BeforeEach
  public void prepareTestData() {
    System.out.println("PREPARING TEST DATA...");

    register = new Register();

    Student firstStudent = new Student("Alan", Group.IO_7_2);
    register.addStudent(firstStudent);
    register.addMarksForStudent(1, Subject.ENGLISH, List.of(4.0));
    register.addMarksForStudent(1, Subject.SALESFORCE_PROGRAMMING, List.of(2.0, 4.0));
    register.addMarksForStudent(1, Subject.JAVA_PROGRAMMING, List.of(2.0));

    Student secondStudent = new Student("Kamil", Group.IO_7_2);
    register.addStudent(secondStudent);
    register.addMarksForStudent(2, Subject.ENGLISH, List.of(5.0));
    register.addMarksForStudent(2, Subject.SALESFORCE_PROGRAMMING, List.of(2.0, 4.0));
    register.addMarksForStudent(2, Subject.JAVA_PROGRAMMING, List.of());

    Student thirdStudent = new Student("Wojciech", Group.IO_7_1);
    register.addStudent(thirdStudent);

    System.out.println("\nTEST DATA IS READY!");
  }

  //Niepoprawne stworzenie studenta
  @Test
  public void verifyCreatingStudentNegativePath() {
    Assertions.assertThrows(IllegalArgumentException.class,
        () -> new Student(null, Group.IO_7_3));
    Assertions.assertThrows(IllegalArgumentException.class,
        () -> new Student("", Group.IO_7_3));
    Assertions.assertThrows(IllegalArgumentException.class,
        () -> new Student("Alan", null));
  }

  @Test
  public void verifyAddingStudent() {
    int result = register.addStudent(new Student("Alan", Group.IO_7_3));
    Assertions.assertEquals(result, 1);
  }

  //Dodanie istniejącego studenta
  @Test
  public void verifyAddingStudentNegativePath() {
    Student student = new Student("Zbigniew", Group.IO_7_3);
    int result = register.addStudent(student);
    Assertions.assertEquals(result, 1);

    result = register.addStudent(student);
    Assertions.assertEquals(result, -1);
  }

  @Test
  public void verifyAddingMarksForStudent() {
    Assertions.assertEquals(register.addMarksForStudent(3, Subject.SALESFORCE_PROGRAMMING, List.of(2.0, 4.0)), 1);
  }

  //Dodanie niepoprawnych ocen
  @Test
  public void verifyAddingMarksForStudentNegativePath() {
    Assertions.assertEquals(register.addMarksForStudent(7, Subject.DOT_NET_APPLICATION, List.of(2.0, 4.0)), -1);
    Assertions.assertEquals(register.addMarksForStudent(3, null, List.of(2.0, 4.0)), -1);
    Assertions.assertEquals(register.addMarksForStudent(3, Subject.ENGLISH, null), -1);
    Assertions.assertEquals(register.addMarksForStudent(3, Subject.ENGLISH, List.of(0.0, 4.0)), -1);
    Assertions.assertEquals(register.addMarksForStudent(3, Subject.ENGLISH, List.of(1.0, 6.0)), -1);
    Assertions.assertEquals(register.addMarksForStudent(3, Subject.ENGLISH, List.of(-5.0, -3.0)), -1);
  }

  @Test
  public void verifyStudentInfo() {
    List<String> studentInfo = List.of(register.getStudentInfo(1).split("\n"));

    Assertions.assertEquals(studentInfo.get(0), "Student id: 1");
    Assertions.assertEquals(studentInfo.get(1), "Student name: Alan");
    Assertions.assertEquals(studentInfo.get(2), "Student group: IO_7_2");
    Assertions.assertEquals(studentInfo.get(3), "Student marks: {SALESFORCE_PROGRAMMING=[2.0, 4.0], JAVA_PROGRAMMING=[2.0], ENGLISH=[4.0]}");
  }

  //Sprawdzanie istnienia studenta o niepoprawnym id
  @Test
  public void verifyStudentInfoNegativePath() {
    int id = 7;
    String studentInfo = register.getStudentInfo(id);
    String expectedResult = String.format("Error! There is no student with id %s", id);

    Assertions.assertEquals(expectedResult, studentInfo);
  }

  @Test
  public void verifyStudentAverageMark() {
    Assertions.assertEquals(register.getStudentAverageMark(1), 3.00);
    Assertions.assertEquals(register.getStudentAverageMark(2), 3.67);
    Assertions.assertEquals(register.getStudentAverageMark(3), 0.00);
  }

  //Sprawdzenia istnienia studenta o niepoprawnym id
  @Test
  public void verifyStudentAverageMarkNegativePath() {
    Assertions.assertEquals(register.getStudentAverageMark(7), -1.00);
    Assertions.assertEquals(register.getStudentAverageMark(0), -1.00);
    Assertions.assertEquals(register.getStudentAverageMark(-5), -1.00);
  }

  @Test
  public void verifyGroupAverageMark() {
    Assertions.assertEquals(register.getGroupAverageMark(Group.IO_7_1), 0.00);
    Assertions.assertEquals(register.getGroupAverageMark(Group.IO_7_2), 3.34);
    Assertions.assertEquals(register.getGroupAverageMark(Group.IO_7_3), 0.00);
  }

  @Test
  public void verifyGroupAverageMarkNegativePath() {
    Assertions.assertEquals(register.getGroupAverageMark(null), -1.00);
  }

  @Test
  public void verifyStudentAverageMarkBySubject() {
    Assertions.assertEquals(register.getStudentAverageMarkBySubject(1, Subject.SALESFORCE_PROGRAMMING), 3.00);
    Assertions.assertEquals(register.getStudentAverageMarkBySubject(1, Subject.ENGLISH), 4.00);

    Assertions.assertEquals(register.getStudentAverageMarkBySubject(2, Subject.JAVA_PROGRAMMING), 0.00);
  }

  @Test
  public void verifyStudentAverageMarkBySubjectNegativePath() {
    //Dla studenta który niema przedmiotu SALESFORCE_PROGRAMMING
    Assertions.assertEquals(register.getStudentAverageMarkBySubject(3, Subject.SALESFORCE_PROGRAMMING), -1.00);
    //Nie istnieje studenta o id 8
    Assertions.assertEquals(register.getStudentAverageMarkBySubject(8, Subject.SALESFORCE_PROGRAMMING), -1.00);
    //Przedmiot - null
    Assertions.assertEquals(register.getStudentAverageMarkBySubject(1, null), -1.00);
  }
}