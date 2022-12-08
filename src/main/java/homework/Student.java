package homework;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Student {

  private int id;
  private final String name;
  private final Group group;
  private final Map<Subject, List<Double>> marks;


  public Student(String name, Group group)
  {
    if (name == null || name.isEmpty())
    {
      throw new IllegalArgumentException("Incorrect student name, please try again");
    }
    if (group == null)
    {
      throw new IllegalArgumentException("Incorrect student group, please try again");
    }

    this.name = name;
    this.group = group;
    this.marks = new HashMap<>();
  }

  public String getName() {
    return name;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Map<Subject, List<Double>> getMarks() {
    return marks;
  }

  public Group getGroup() {
    return group;
  }

  @Override
  public String toString()
  {
    return String.format("Student id: %s\n", id + 1) +
        String.format("Student name: %s\n", name) +
        String.format("Student group: %s\n", group) +
        String.format("Student marks: %s", marks);
  }
}
