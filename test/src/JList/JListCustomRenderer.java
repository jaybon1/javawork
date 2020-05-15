package JList;

import java.awt.BorderLayout;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

public class JListCustomRenderer extends JFrame {
    private int width = 350;
    private int height = 200;
    private JList<Book> listBook;
 
    public JListCustomRenderer() {
        // add main panel
        add(createMainPanel());
        // set display
        setTitle("JLIstCustomRenderer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(width, height);
        setLocationRelativeTo(null);
        setVisible(true);
    }
 
    private JPanel createMainPanel() {
    	JScrollPane a = new JScrollPane();
    	a.setLayout(null);
    	
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(new EmptyBorder(10, 10, 10, 10));
        // create list book and set to scrollpane and add to panel
        panel.add(new JScrollPane(listBook = createListBooks()),
                BorderLayout.CENTER);
        return panel;
    }
 
    private JList<Book> createListBooks() {
        // create List model
        DefaultListModel<Book> model = new DefaultListModel<>();
        // add item to model
        model.addElement(new Book("C/C++ Programming", "A"));
        model.addElement(new Book("Java Programming", "B"));
        model.addElement(new Book("C# Programming", "C"));
        model.addElement(new Book("IOS Programming", "D"));
        model.addElement(new Book("Windows Phone Programming", "E"));
        model.addElement(new Book("Android Programming", "F"));
        // create JList with model
        JList<Book> list = new JList<Book>(model);
        list.setCellRenderer(new BookRenderer());
        return list;
    }
 
    public static void main(String[] args) {
        new JListCustomRenderer();
    }
}