package JList;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
 
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
 
public class BookRenderer extends JPanel implements ListCellRenderer<Book> {
 
    private JLabel lbName = new JLabel();
    private JLabel lbAuthor = new JLabel();
    private JButton deleteButton = new JButton("삭제하기");
 
    public BookRenderer() {
        setLayout(new BorderLayout(5, 5));
 
        JPanel panelText = new JPanel(new GridLayout(0, 2));
        panelText.add(lbName);
        panelText.add(deleteButton);
        panelText.add(lbAuthor);
        add(panelText, BorderLayout.CENTER);
    }
 
    @Override
    public Component getListCellRendererComponent(JList<? extends Book> list,
            Book book, int index, boolean isSelected, boolean cellHasFocus) {
 
        lbName.setText(book.getName());
        lbAuthor.setText(book.getAuthor());
        lbAuthor.setForeground(Color.blue);
 
        return this;
    }
}