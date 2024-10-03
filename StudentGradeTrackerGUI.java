import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class StudentGradeTrackerGUI {
  private JFrame frame;
  private JTextField numStudentsField;
  private JTextField[] gradeFields;
  private JLabel averageLabel;
  private JLabel highestLabel;
  private JLabel lowestLabel;

  public static void main(String[] args) {
    SwingUtilities.invokeLater(new Runnable() {
      public void run() {
        new StudentGradeTrackerGUI().createAndShowGUI();
      }
    });
  }

  private void createAndShowGUI() {
    frame = new JFrame("Student Grade Tracker");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JPanel panel = new JPanel();
    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));


    JPanel numStudentsPanel = new JPanel();
    numStudentsPanel.add(new JLabel("Enter the number of students:"));
    numStudentsField = new JTextField(5);
    numStudentsPanel.add(numStudentsField);
    JButton enterButton = new JButton("Enter");
    enterButton.addActionListener(new EnterButtonListener());
    numStudentsPanel.add(enterButton);
    panel.add(numStudentsPanel);

   
    gradeFields = new JTextField[0];

   
    JPanel resultsPanel = new JPanel();
    resultsPanel.add(new JLabel("Average grade:"));
    averageLabel = new JLabel("");
    resultsPanel.add(averageLabel);
    resultsPanel.add(new JLabel("Highest grade:"));
    highestLabel = new JLabel("");
    resultsPanel.add(highestLabel);
    resultsPanel.add(new JLabel("Lowest grade:"));
    lowestLabel = new JLabel("");
    resultsPanel.add(lowestLabel);
    panel.add(resultsPanel);

    frame.getContentPane().add(panel);
    frame.pack();
    frame.setVisible(true);
  }

  private class EnterButtonListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      int numStudents = Integer.parseInt(numStudentsField.getText());
      gradeFields = new JTextField[numStudents];

      JPanel gradePanel = new JPanel();
      gradePanel.setLayout(new BoxLayout(gradePanel, BoxLayout.Y_AXIS));
      for (int i = 0; i < numStudents; i++) {
        JPanel gradeFieldPanel = new JPanel();
        gradeFieldPanel.add(new JLabel("Enter grade for student " + (i + 1) + ":"));
        JTextField gradeField = new JTextField(5);
        gradeFields[i] = gradeField;
        gradeFieldPanel.add(gradeField);
        gradePanel.add(gradeFieldPanel);
      }
      JButton calculateButton = new JButton("Calculate");
      calculateButton.addActionListener(new CalculateButtonListener());
      gradePanel.add(calculateButton);
      frame.getContentPane().add(gradePanel);
      frame.pack();
    }
  }

  private class CalculateButtonListener implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      ArrayList<Integer> grades = new ArrayList<>();
      for (JTextField gradeField : gradeFields) {
        int grade = Integer.parseInt(gradeField.getText());
        grades.add(grade);
      }

      int sum = 0;
      int highest = grades.get(0);
      int lowest = grades.get(0);

      for (int grade : grades) {
        sum += grade;
        if (grade > highest) {
          highest = grade;
        }
        if (grade < lowest) {
          lowest = grade;
        }
      }

      double average = (double) sum / grades.size();

      averageLabel.setText(String.valueOf(average));
      highestLabel.setText(String.valueOf(highest));
      lowestLabel.setText(String.valueOf(lowest));
    }
  }
}