import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Calculator extends JFrame implements ActionListener {

	JPanel panel;
	JTextField text;
	JButton[] buttons;
	String[] labels = { "MC", "MR", "MS", "M+", "M-", "Backspace", "CE", "C", "+-", "sqrt", "7", "8", "9", "/", "%",
			"4", "5", "6", "*", "1/x", "1", "2", "3", "-", "", "0", "", ".", "+", "=" };

	double result = 0;
	String operator = "=";
	private boolean startOfNumber = true;

	public Calculator() {
		setTitle("����");
		text = new JTextField(40);
		panel = new JPanel();
		text.setText(" ");
		panel.setLayout(new GridLayout(0, 5, 3, 3));
		buttons = new JButton[30];
		int index = 0;
		for (int rows = 0; rows < 6; rows++) {
			for (int cols = 0; cols < 5; cols++) {
				buttons[index] = new JButton(labels[index]);
				if (cols >= 3 || rows < 2)
					buttons[index].setForeground(Color.red);
				else
					buttons[index].setForeground(Color.blue);
				panel.add(buttons[index]);
				buttons[index].addActionListener(this);
				index++;
			}
		}
		add(text, BorderLayout.NORTH);
		add(panel, BorderLayout.CENTER);
		setVisible(true);
		pack();	
		
	}

	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		if (command.charAt(0) == 'C') {
			startOfNumber = true;
			result = 0;
			operator = "=";
			text.setText("0.0");
		}
		
	    else if(command.charAt(0) >= '0' && command.charAt(0) <= '9' || command.equals(".")){
            if(startOfNumber == true)
               text.setText(command);
            else
               text.setText(text.getText() + command);
            startOfNumber = false;
         }
	    else{
            if(startOfNumber){
               if(command.equals("-")){
                  text.setText(command);
                  startOfNumber = false;
               }
               else
                  operator = command;
               }
            else{
               double x = Double.parseDouble(text.getText());
               calculate(x);
               operator = command;
               startOfNumber = true;
               }   
	    }
	}
	private void calculate(double n){
        if(operator.equals("+"))
           result += n;
        else if(operator.equals("-"))
           result -= n;
        else if(operator.equals("*"))
           result *= n;
        else if(operator.equals("/"))
           result /= n;
        else if(operator.equals("="))
           result = n;
        
        text.setText(" " + result);
        }


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Calculator c = new Calculator();
	}

}
