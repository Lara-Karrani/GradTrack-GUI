
package com.mycompany.gradtrack_system;



import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicComboBoxUI;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class LoginFrame extends JFrame {

    public LoginFrame() {
        setTitle("GRADTRACK - Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(420, 860);
        setLocationRelativeTo(null);
        setResizable(false);

        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception ignored) {}

        BackgroundPanel root = new BackgroundPanel();
        root.setLayout(new GridBagLayout());
        setContentPane(root);

        JPanel content = new JPanel();
        content.setOpaque(false);
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));
        content.setBorder(new EmptyBorder(40, 40, 40, 40));
        content.setPreferredSize(new Dimension(380, 760));

        // Icon
        CapIconCircle icon = new CapIconCircle(86);
        icon.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Title
        JLabel title = new JLabel("GRADTRACK");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(new Font("Segoe UI", Font.BOLD, 30));
        title.setForeground(new Color(28, 31, 36));

        content.add(Box.createVerticalStrut(40));
        content.add(icon);
        content.add(Box.createVerticalStrut(24));
        content.add(title);
        content.add(Box.createVerticalStrut(34));

        // Email
        JLabel emailLbl = fieldLabel("Email");
        RoundedTextField emailField = new RoundedTextField();
        emailField.setText("user@kau.edu.sa");

        content.add(emailLbl);
        content.add(Box.createVerticalStrut(8));
        content.add(emailField);
        content.add(Box.createVerticalStrut(18));

        // Password
        JLabel passLbl = fieldLabel("Password");
        RoundedPasswordField passField = new RoundedPasswordField();
        passField.setText("************"); // GUI only

        content.add(passLbl);
        content.add(Box.createVerticalStrut(8));
        content.add(passField);
        content.add(Box.createVerticalStrut(18));

        // Role
        JLabel roleLbl = fieldLabel("Role");
        RoundedComboBox<String> roleBox = new RoundedComboBox<>(new String[]{"Student", "Supervisor"});
        roleBox.setSelectedIndex(0);

        content.add(roleLbl);
        content.add(Box.createVerticalStrut(8));
        content.add(roleBox);
        content.add(Box.createVerticalStrut(28));

        // ✅ Login button (FIXED)
        RoundedButton loginBtn = new RoundedButton("Login", 26);
        loginBtn.setBackground(new Color(47, 95, 208));
        loginBtn.setForeground(Color.WHITE);
        loginBtn.setFont(new Font("Segoe UI", Font.BOLD, 18));
        loginBtn.setPreferredSize(new Dimension(320, 56));
        loginBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 56));

        // ✅ Exit button (FIXED)
        RoundedButton exitBtn = new RoundedButton("Exit", 26);
        exitBtn.setBackground(Color.WHITE);
        exitBtn.setForeground(new Color(90, 96, 104));
        exitBtn.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        exitBtn.setPreferredSize(new Dimension(320, 56));
        exitBtn.setMaximumSize(new Dimension(Integer.MAX_VALUE, 56));

        content.add(loginBtn);
        content.add(Box.createVerticalStrut(14));
        content.add(exitBtn);

        // Actions (GUI only)
        loginBtn.addActionListener(e ->
                JOptionPane.showMessageDialog(this, "Login clicked (GUI only)."));
        exitBtn.addActionListener(e -> System.exit(0));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTH;
        root.add(content, gbc);
    }

    private JLabel fieldLabel(String text) {
        JLabel lbl = new JLabel(text);
        lbl.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lbl.setForeground(new Color(108, 114, 122));
        lbl.setAlignmentX(Component.LEFT_ALIGNMENT);
        return lbl;
    }

    // ========= Custom UI =========

    static class BackgroundPanel extends JPanel {
        BackgroundPanel() {
            setOpaque(true);
            setBackground(new Color(244, 247, 252));
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int w = getWidth();
            int h = getHeight();

            g2.setColor(new Color(244, 247, 252));
            g2.fillRect(0, 0, w, h);

            g2.setColor(new Color(226, 236, 255));
            g2.fillOval(-80, -40, 320, 240);

            g2.setColor(new Color(238, 244, 255));
            g2.fillOval(-120, h - 320, 420, 420);

            g2.setColor(new Color(233, 241, 255));
            g2.fillOval(w - 280, h - 320, 420, 420);

            g2.dispose();
        }
    }

    static class CapIconCircle extends JComponent {
        private final int size;

        CapIconCircle(int size) {
            this.size = size;
            setPreferredSize(new Dimension(size, size));
            setMinimumSize(new Dimension(size, size));
            setMaximumSize(new Dimension(size, size));
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            g2.setColor(new Color(225, 234, 252));
            g2.fillOval(0, 0, size, size);

            int cx = size / 2;
            int cy = size / 2;

            Polygon capTop = new Polygon();
            capTop.addPoint(cx, cy - 18);
            capTop.addPoint(cx + 22, cy - 6);
            capTop.addPoint(cx, cy + 6);
            capTop.addPoint(cx - 22, cy - 6);

            g2.setColor(new Color(27, 64, 152));
            g2.fillPolygon(capTop);

            g2.setColor(new Color(34, 74, 170));
            g2.fillRoundRect(cx - 16, cy + 4, 32, 10, 8, 8);

            g2.setStroke(new BasicStroke(2f));
            g2.setColor(new Color(212, 164, 60));
            g2.drawLine(cx + 12, cy - 6, cx + 20, cy + 10);
            g2.fillOval(cx + 18, cy + 10, 6, 6);

            g2.dispose();
        }
    }

    static class RoundedTextField extends JTextField {
        private final int arc = 22;

        RoundedTextField() {
            setOpaque(false);
            setBorder(new EmptyBorder(14, 16, 14, 16));
            setFont(new Font("Segoe UI", Font.PLAIN, 16));
            setForeground(new Color(40, 44, 50));
            setCaretColor(new Color(40, 44, 50));
            setMaximumSize(new Dimension(Integer.MAX_VALUE, 54));
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            g2.setColor(new Color(246, 248, 252));
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), arc, arc);

            g2.setColor(new Color(220, 226, 236));
            g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, arc, arc);

            g2.dispose();
            super.paintComponent(g);
        }
    }

    static class RoundedPasswordField extends JPasswordField {
        private final int arc = 22;

        RoundedPasswordField() {
            setEchoChar('•');
            setOpaque(false);
            setBorder(new EmptyBorder(14, 16, 14, 16));
            setFont(new Font("Segoe UI", Font.PLAIN, 16));
            setForeground(new Color(40, 44, 50));
            setCaretColor(new Color(40, 44, 50));
            setMaximumSize(new Dimension(Integer.MAX_VALUE, 54));
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            g2.setColor(new Color(246, 248, 252));
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), arc, arc);

            g2.setColor(new Color(220, 226, 236));
            g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, arc, arc);

            g2.dispose();
            super.paintComponent(g);
        }
    }

    static class RoundedComboBox<T> extends JComboBox<T> {
        private final int arc = 22;

        RoundedComboBox(T[] items) {
            super(items);
            setOpaque(false);
            setFont(new Font("Segoe UI", Font.PLAIN, 16));
            setForeground(new Color(40, 44, 50));
            setBorder(new EmptyBorder(10, 12, 10, 12));
            setMaximumSize(new Dimension(Integer.MAX_VALUE, 54));

            setUI(new BasicComboBoxUI() {
                @Override
                protected JButton createArrowButton() {
                    JButton b = new JButton("˅");
                    b.setBorderPainted(false);
                    b.setContentAreaFilled(false);
                    b.setFocusPainted(false);
                    b.setForeground(new Color(120, 126, 134));
                    b.setFont(new Font("Segoe UI", Font.BOLD, 16));
                    return b;
                }
            });
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            g2.setColor(new Color(246, 248, 252));
            g2.fillRoundRect(0, 0, getWidth(), getHeight(), arc, arc);

            g2.setColor(new Color(220, 226, 236));
            g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, arc, arc);

            g2.dispose();
            super.paintComponent(g);
        }
    }

    // ✅ FIXED RoundedButton (no double text, no layout problems)
    static class RoundedButton extends JButton {
        private final int arc;

        RoundedButton(String text, int arc) {
            super(text);
            this.arc = arc;
            setOpaque(false);
            setBorderPainted(false);
            setContentAreaFilled(false);
            setFocusPainted(false);
            setCursor(new Cursor(Cursor.HAND_CURSOR));
            setHorizontalAlignment(SwingConstants.CENTER);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int w = getWidth();
            int h = getHeight();

            // subtle shadow
            g2.setColor(new Color(0, 0, 0, 18));
            g2.fillRoundRect(2, 4, w - 4, h - 4, arc, arc);

            // fill
            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, w, h - 2, arc, arc);

            // border for white button
            if (Color.WHITE.equals(getBackground())) {
                g2.setColor(new Color(220, 226, 236));
                g2.drawRoundRect(0, 0, w - 1, h - 3, arc, arc);
            }

            g2.dispose();
            super.paintComponent(g); // draw text once (fixed)
        }

        @Override
        public boolean contains(int x, int y) {
            Shape s = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), arc, arc);
            return s.contains(x, y);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginFrame().setVisible(true));
    }
}