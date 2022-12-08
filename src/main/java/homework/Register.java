package homework;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class Register {

  private final List<Student> students;


  public Register() {
    students = new ArrayList<>();
  }


  public int addStudent(Student student)
  {
    if (students.contains(student))
    {
      System.out.println("Error! Student already exist in register\n");
      return -1;
    }

    students.add(student);
    student.setId(students.indexOf(student));
    System.out.printf("\nStudent %s was created with id %s\n", student.getName(), student.getId() + 1);

    return 1;
  }


  public int addMarksForStudent(int id, Subject subject, List<Double> marks)
  {
    List<Double> validMarks = new ArrayList<>();
    validMarks.add(2.0);
    validMarks.add(3.0);
    validMarks.add(3.5);
    validMarks.add(4.0);
    validMarks.add(4.5);
    validMarks.add(5.0);

    if (verifyStudentId(id))
    {
      System.out.printf("\nError! There is no student with id %s\n", id);
      return -1;
    }
    if (subject == null)
    {
      System.out.println("Error! Invalid subject\n");
      return -1;
    }
    if (marks == null)
    {
      System.out.println("Error! Invalid marks list\n");
      return -1;
    }
    for (Double mark : marks)
    {
      if(!validMarks.contains(mark) || mark <= 0.0)
      {
        System.out.printf("\nError! Invalid mark value: %s\n", mark);
        return -1;
      }
    }

    System.out.printf("\n%s marks are %s for student with id %s...\n", subject, marks, id);
    students.get(id - 1).getMarks().put(subject, marks);

    return 1;
  }


  public String getStudentInfo(int id)
  {
    if (verifyStudentId(id))
    {
      System.out.printf("\nError! There is no student with id %s\n", id);
      return String.format("Error! There is no student with id %s", id);
    }

    System.out.printf("\nGetting info about student with id %s...\n", id);

    String info = students.get(id - 1).toString();
    System.out.println(info);

    return info;
  }


  public double getStudentAverageMark(int id)
  {
    if (verifyStudentId(id))
    {
      System.out.printf("\nError! There is no student with id %s\n", id);
      return -1;
    }

    double sum = 0;
    int marksCount = 0;
    Collection<List<Double>> collection = students.get(id - 1).getMarks().values()
        .stream()
        .filter(c -> !c.isEmpty())
        .collect(Collectors.toList());

    for (List<Double> marks : collection)
    {
      for (Double mark : marks)
      {
        sum += mark;
        marksCount++;
      }
    }

    if (marksCount == 0)
    {
      marksCount = 1;
    }
    double result = sum / marksCount;

    System.out.printf("\nAverage for student with id %s: %.2f\n", id, result);

    return Double.parseDouble(String.format("%.2f", result).replace(',', '.'));
  }


  public double getGroupAverageMark(Group group)
  {
    if (group == null)
    {
      System.out.println("Error! Invalid group\n");
      return -1;
    }

    double sum = 0;
    List<Student> studentsInGroup = students.stream()
        .filter(s -> s.getGroup().equals(group))
        .collect(Collectors.toList());

    for (Student student : studentsInGroup)
    {
      sum += getStudentAverageMark(student.getId() + 1);
    }

    int size = studentsInGroup.size();
    if (size == 0)
    {
      size = 1;
    }

    double result = sum / size;
    System.out.printf("\nAverage for students from group %s: %.2f\n", group, result);

    return Double.parseDouble(String.format("%.2f", result).replace(',', '.'));
  }


  public double getStudentAverageMarkBySubject(int id, Subject subject)
  {
    if (verifyStudentId(id))
    {
      System.out.printf("\nError! There is no student with id %s\n", id);
      return -1;
    }
    if (subject == null)
    {
      System.out.println("Error! Invalid subject\n");
      return -1;
    }
    if (!students.get(id - 1).getMarks().containsKey(subject))
    {
      System.out.println("Error! Student has not marks for this subject\n");
      return -1;
    }

    double sum = 0;
    List<Double> marks = students.get(id - 1).getMarks().get(subject);

    for (Double mark : marks)
    {
      sum += mark;
    }

    int size = marks.size();
    if (size == 0)
    {
      size = 1;
    }

    double result = sum / size;
    System.out.printf("\nSubject average in %s for student with id %s: %.2f\n", subject, id, result);

    return Double.parseDouble(String.format("%.2f", result).replace(',', '.'));
  }

  private boolean verifyStudentId(int id)
  {
    return id <= 0 || id > students.size();
  }
}
