package samsungcrm;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerFeedbackGUI {
    private JFrame frame;
    private JTable table;
    private DefaultTableModel tableModel;

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                CustomerFeedbackGUI window = new CustomerFeedbackGUI();
                window.frame.setVisible(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public CustomerFeedbackGUI() {
        initialize();
        loadData();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setBounds(100, 100, 1000, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(new BorderLayout());

        // background color
        frame.getContentPane().setBackground(Color.BLACK);

       
        ImageIcon logoIcon = resizeImageIcon("C:\\Users\\Bogs\\Downloads\\samsung-logo-samsung-icon-free-free-vector.jpg", 300, 200); 
        JLabel lblLogo = new JLabel(logoIcon);
        frame.getContentPane().add(lblLogo, BorderLayout.NORTH); 

        // Table setup
        String[] columnNames = {"Customer ID", "Customer Name", "Feedback ID", "Feedback Date", "Feedback"};
        tableModel = new DefaultTableModel(columnNames, 0);
        table = new JTable(tableModel) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make table cells non-editable
            }
        };
        table.setFont(new Font("SansSerif", Font.PLAIN, 14));
        table.setRowHeight(50);
        table.getColumnModel().getColumn(4).setCellRenderer(new TextAreaRenderer());
        table.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 16));
        table.getTableHeader().setBackground(Color.BLUE);
        table.getTableHeader().setForeground(Color.WHITE);
        table.setBackground(Color.BLACK);
        table.setForeground(Color.WHITE);
        JScrollPane scrollPane = new JScrollPane(table);

        // Add padding around the table
        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBackground(Color.BLACK);
        tablePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        tablePanel.add(scrollPane, BorderLayout.CENTER);

        frame.getContentPane().add(tablePanel, BorderLayout.CENTER);
    }

    private ImageIcon resizeImageIcon(String imagePath, int width, int height) {
        ImageIcon icon = new ImageIcon(imagePath);
        Image image = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(image);
    }

    private void loadData() {
        Connection connection = workbenchconnection.getConnection();
        if (connection != null) {
            try {
                String query = "SELECT customer.customer_id, customer.customer_name, feedback.feedback_id, feedback.feedback_date, feedback.feedback " +
                               "FROM customer " +
                               "JOIN feedback ON customer.customer_id = feedback.customer_id";
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {
                    int customerId = resultSet.getInt("customer_id");
                    String customerName = resultSet.getString("customer_name");
                    int feedbackId = resultSet.getInt("feedback_id");
                    String feedbackDate = resultSet.getString("feedback_date");
                    String feedback = resultSet.getString("feedback");

                    tableModel.addRow(new Object[]{customerId, customerName, feedbackId, feedbackDate, feedback});
                }

                resultSet.close();
                preparedStatement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(frame, "SQL Error: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(frame, "Error connecting to database.");
        }
    }

    // cell renderer for the feedback column to handle multi-line text
    private static class TextAreaRenderer extends JTextArea implements TableCellRenderer {
        public TextAreaRenderer() {
            setLineWrap(true);
            setWrapStyleWord(true);
        }

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setText(value != null ? value.toString() : "");
            setBackground(table.getBackground());
            setForeground(table.getForeground());
            setFont(table.getFont());
            setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            setSize(table.getColumnModel().getColumn(column).getWidth(), getPreferredSize().height);
            if (table.getRowHeight(row) != getPreferredSize().height) {
                table.setRowHeight(row, getPreferredSize().height);
            }
            return this;
        }
    }

    public void setVisible(boolean visible) {
        frame.setVisible(visible);
    }
}
