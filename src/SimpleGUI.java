import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class SimpleGUI extends JFrame implements ActionListener, ItemListener, ChangeListener {

    private JCheckBox checkBox1;
    private JCheckBox checkBox2;
    private JLabel welcomeLabel;
    private JTextArea textArea;
    private JTextField textField;

    public SimpleGUI() {
        super("Frame title");
        init();
    }

    private void init() {
        // setting up the frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(550, 400);
        setLocation(300, 50);

        // create the MenuBar and menu components
        JMenuBar menuBar = new JMenuBar();
        JMenu menu1 = new JMenu("File");
        JMenuItem menuItem1 = new JMenuItem("Open");
        JMenuItem menuItem2 = new JMenuItem("Save as");
        menu1.add(menuItem1);
        menu1.add(menuItem2);
        JMenu menu2 = new JMenu("Help");
        JMenuItem menuItem3 = new JMenuItem("FAQ");
        JMenuItem menuItem4 = new JMenuItem("About");
        menu2.add(menuItem3);
        menu2.add(menuItem4);

        // add "File" and "Help" menus to the MenuBar
        menuBar.add(menu1);
        menuBar.add(menu2);



        // create the big text area located in the middle
        textArea = new JTextArea();

        // create welcome label
        welcomeLabel = new JLabel("Welcome to eternal pain!");
        welcomeLabel.setFont(new Font("Times New Roman", Font.BOLD, 25));

        // create slider and adjust settings
        JSlider slider = new JSlider(0, 40, 20);
        slider.setMinorTickSpacing(1);
        slider.setMajorTickSpacing(5);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);

        // create a panel for organizing the label and slider
        JPanel sliderPanel = new JPanel();

        // add label and slider, in left-to-right order
        sliderPanel.add(welcomeLabel);
        sliderPanel.add(slider);

        // create the components at the bottom
        JLabel label = new JLabel("Enter Text");
        textField = new JTextField(10); // accepts up to 10 characters
        JButton sendButton = new JButton("Send");
        JButton resetButton = new JButton("Reset");
        JButton openButton = new JButton("Open");

        // create checkboxes
        checkBox1 = new JCheckBox("Yes");
        checkBox1.setBounds(100, 100, 50, 50);
        checkBox2 = new JCheckBox("No", true);
        checkBox2.setBounds(100, 150, 50, 50);

        // create a panel for organizing the components at the bottom
        JPanel panel = new JPanel(); // a "panel" is not visible

        // add bottom components to the panel, in left-to-right order
        panel.add(label);
        panel.add(textField);
        panel.add(sendButton);
        panel.add(resetButton);
        panel.add(openButton);
        panel.add(checkBox1);
        panel.add(checkBox2);

        // creating a third panel to place slider and bottom panels vertically
        // (allows two rows of UI elements to be displayed)
        JPanel combinedPanels = new JPanel();
        combinedPanels.setLayout(new GridLayout(2, 1));
        combinedPanels.add(sliderPanel, BorderLayout.NORTH);
        combinedPanels.add(panel, BorderLayout.SOUTH);

        // add the menu bar to the TOP of the frame, the big white text area
        // to the MIDDLE of the frame, and the "combinedPanels" (which has
        // the label, slider, text box, buttons, and checkboxes) at the BOTTOM
        add(menuBar, BorderLayout.NORTH);
        add(textArea, BorderLayout.CENTER);
        add(combinedPanels, BorderLayout.SOUTH);

        // --- SETTING UP EVENT HANDLING ----
        //setting up buttons to use ActionListener interface and actionPerformed method
        sendButton.addActionListener(this);
        resetButton.addActionListener(this);
        openButton.addActionListener(this);
        menuItem1.addActionListener(this);
        menuItem2.addActionListener(this);
        menuItem3.addActionListener(this);
        menuItem4.addActionListener(this);


        //setting up checkboxes to use ItemListener interface and itemStateChanged method
        checkBox1.addItemListener(this);
        checkBox2.addItemListener(this);

        slider.addChangeListener(this);
        // display the frame!
        setVisible(true);
    }

    // ActionListener interface method, called when a button is clicked
    public void actionPerformed(ActionEvent ae) {
        // cast ae to a JButton object since we want to call the getText method on it;
        // casting is needed since getSource() returns Object type, NOT a JButton
        Object source = ae.getSource();
        if (source instanceof JButton) {
            JButton button = (JButton) source;
            String text = button.getText();

            if (text.equals("Send")) {
                welcomeLabel.setText("Send pressed!");
                String textBoxText = textField.getText();
                String updatedText = textArea.getText() + textBoxText;
                textArea.setText(updatedText);
            } else if (text.equals("Reset")) {
                welcomeLabel.setText("Reset pressed!");
                textArea.setText("");
                textField.setText("");
                checkBox1.setSelected(false);
                checkBox2.setSelected(false);
            } else if (text.equals("Open")) {
                JFrame newFrame = new JFrame ("Hello there!");
                newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                newFrame.setSize(200, 300);
                newFrame.setLocation(300, 100);
                JLabel hello = new JLabel("Hey There!");
                JPanel newPanel = new JPanel();
                newPanel.add(hello);
                newFrame.add(newPanel, BorderLayout.NORTH);
                newFrame.setVisible(true);
            }
        } else if (source instanceof JMenuItem) {
            JMenuItem item = (JMenuItem) source;
            String menuText = item.getText();
            textField.setText(menuText);
        }
    }

    // ItemListener interface method, called when EITHER check box is toggled!
    public void itemStateChanged(ItemEvent e) {

        // cast e to a JCheckBox object since we want to call the getText method on it;
        // casting is needed since getSource() returns Object type, NOT a JCheckBox
        Object source = e.getSource();
        JCheckBox cb = (JCheckBox) source;
        String cbText = cb.getText();

        int checkBoxOnOrOff = e.getStateChange(); // 1 for selected, 2 for deselected
        if (checkBoxOnOrOff == 1) {
            welcomeLabel.setText(cbText + " box SELECTED!");
            if (checkBox1.isSelected()) {
                String updatedText = textArea.getText() + " Why did you select yes, you should've picked no";
                textArea.setText(updatedText);
            } else if (checkBox2.isSelected()) {
                String updatedText = textArea.getText() + " Why did you select no, you should've picked yes";
                textArea.setText(updatedText);
            }
        } else if (checkBoxOnOrOff == 2) {
            welcomeLabel.setText(cbText + " box DESELECTED!");
        }

        // we don't "print" with GUI based apps, but printing
        // can still be helpful for testing and debugging!
        System.out.println("Current state: yes `= " + checkBox1.isSelected() + ", no = " + checkBox2.isSelected());
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        Object source = e.getSource();
        JSlider slider = (JSlider) source;
        int value = slider.getValue();
        textArea.setText("" + value);
    }
}