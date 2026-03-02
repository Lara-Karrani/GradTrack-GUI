package com.mycompany.gradtrack_system;
// SupervisorManageTasks

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicComboBoxUI;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.RoundRectangle2D;

public class SupervisorManageTasks extends JFrame {

    public SupervisorManageTasks() {
        setTitle("GRADTRACK - Manage Tasks");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(420, 860);
        setLocationRelativeTo(null);
        setResizable(false);

        try { UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel"); }
        catch (Exception ignored) {}

        BackgroundPanel bg = new BackgroundPanel();
        bg.setLayout(null);
        setContentPane(bg);

        final int W = 420;
        final int P = 22;
        final int contentW = W - (P * 2);

        // ===== Top Bar =====
        JLabel title = new JLabel("MANAGE TASKS", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 18));
        title.setForeground(new Color(28, 31, 36));
        title.setBounds(0, 40, W, 24);
        bg.add(title);

        BackButton back = new BackButton();
        back.setBounds(P - 4, 32, 44, 44);
        bg.add(back);

        // ===== Card: Create New Task =====
        RoundedCard createCard = new RoundedCard(24);
        createCard.setLayout(null);
        createCard.setBounds(P, 100, contentW, 150);
        bg.add(createCard);

        JLabel createLbl = smallSectionTitle("CREATE NEW TASK");
        createLbl.setBounds(18, 16, 250, 18);
        createCard.add(createLbl);

        RoundedTextField taskTitle = new RoundedTextField("Task Title");
        taskTitle.setBounds(18, 44, contentW - 36, 46);
        createCard.add(taskTitle);

        // Row: student dropdown + assign button
        RoundedCombo studentCombo = new RoundedCombo(new String[]{"Lara Ahmed", "Ahmad", "Sarah"});
        studentCombo.setBounds(18, 96, 190, 46);
        createCard.add(studentCombo);

        PrimaryButton assignBtn = new PrimaryButton("+  Assign Task");
        assignBtn.setBounds(18 + 190 + 12, 96, (contentW - 36) - 190 - 12, 46);
        createCard.add(assignBtn);

        assignBtn.addActionListener(e -> JOptionPane.showMessageDialog(this, "Assign Task (GUI only)"));

        // ===== Section Title =====
        JLabel assignedTitle = smallSectionTitle("ASSIGNED TASKS");
        assignedTitle.setBounds(P + 2, 270, 250, 18);
        bg.add(assignedTitle);

        // ===== Task Cards =====
        TaskCard t1 = new TaskCard(
                "Report Draft 1",
                "Lara Ahmed",
                TaskStatus.PENDING,
                "Due: Oct 15"
        );
        t1.setBounds(P, 296, contentW, 120);
        bg.add(t1);

        TaskCard t2 = new TaskCard(
                "Project Proposal",
                "Lara Ahmed",
                TaskStatus.DONE,
                "Due: Sep 15"
        );
        t2.setBounds(P, 432, contentW, 120);
        bg.add(t2);

        JLabel assignedTitle2 = smallSectionTitle("ASSIGNED TASKS");
        assignedTitle2.setBounds(P + 2, 576, 250, 18);
        bg.add(assignedTitle2);

        TaskCard t3 = new TaskCard(
                "Report Draft 1",
                "Lara Ahmed",
                TaskStatus.DONE,
                "Due: Sep 15"
        );
        t3.setBounds(P, 602, contentW, 120);
        bg.add(t3);
    }

    private JLabel smallSectionTitle(String text) {
        JLabel lbl = new JLabel(text);
        lbl.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lbl.setForeground(new Color(144, 150, 160));
        return lbl;
    }

    // ================== Background ==================

    static class BackgroundPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int w = getWidth(), h = getHeight();

            g2.setColor(new Color(244, 247, 252));
            g2.fillRect(0, 0, w, h);

            // soft blobs
            g2.setColor(new Color(226, 236, 255));
            g2.fillOval(-140, -80, 520, 340);

            g2.setColor(new Color(238, 244, 255));
            g2.fillOval(w - 380, 90, 520, 360);

            g2.setColor(new Color(233, 241, 255));
            g2.fillOval(-220, h - 420, 620, 560);

            g2.setColor(new Color(238, 244, 255));
            g2.fillOval(w - 420, h - 520, 680, 620);

            g2.dispose();
        }
    }

    // ================== Components ==================

    static class RoundedCard extends JPanel {
        private final int arc;

        RoundedCard(int arc) {
            this.arc = arc;
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int w = getWidth(), h = getHeight();

            // shadow
            g2.setColor(new Color(0, 0, 0, 14));
            g2.fillRoundRect(2, 4, w - 4, h - 4, arc, arc);

            // fill
            g2.setColor(new Color(250, 251, 255));
            g2.fillRoundRect(0, 0, w, h - 2, arc, arc);

            // border
            g2.setColor(new Color(230, 234, 242));
            g2.drawRoundRect(0, 0, w - 1, h - 3, arc, arc);

            g2.dispose();
            super.paintComponent(g);
        }
    }

    // ✅ RoundedCombo (ONE COPY ONLY) - بدون المستطيل الرمادي
    static class RoundedCombo extends JComponent {

        private final JComboBox<String> combo;

        RoundedCombo(String[] items) {
            setLayout(null);
            setOpaque(false);

            combo = new JComboBox<>(items);
            combo.setFont(new Font("Segoe UI", Font.PLAIN, 13));
            combo.setForeground(new Color(28, 31, 36));
            combo.setBorder(null);
            combo.setOpaque(false);
            combo.setFocusable(false);

            // شفافية كاملة (Nimbus fix)
            combo.setBackground(new Color(0, 0, 0, 0));

            combo.setUI(new BasicComboBoxUI() {
                @Override
                protected JButton createArrowButton() {
                    JButton b = new JButton();
                    b.setBorder(null);
                    b.setContentAreaFilled(false);
                    b.setFocusPainted(false);
                    b.setOpaque(false);
                    return b;
                }

                @Override
                public void paintCurrentValue(Graphics g, Rectangle bounds, boolean hasFocus) {
                    Graphics2D g2 = (Graphics2D) g.create();
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                    g2.setFont(new Font("Segoe UI", Font.PLAIN, 13));
                    g2.setColor(new Color(28, 31, 36));

                    Object selected = combo.getSelectedItem();
                    String text = selected == null ? "" : selected.toString();

                    // نص داخلي padding
                    g2.drawString(text, 16, bounds.height / 2 + 5);

                    g2.dispose();
                }

                @Override
                public void paintCurrentValueBackground(Graphics g, Rectangle bounds, boolean hasFocus) {
                    // no background
                }
            });

            // renderer للقائمة المنسدلة فقط
            combo.setRenderer((list, value, index, isSelected, cellHasFocus) -> {
                JLabel lbl = new JLabel(value == null ? "" : value.toString());
                lbl.setFont(new Font("Segoe UI", Font.PLAIN, 13));
                lbl.setBorder(new EmptyBorder(8, 12, 8, 12));
                lbl.setOpaque(true);

                lbl.setBackground(isSelected ? new Color(230, 239, 255) : Color.WHITE);
                lbl.setForeground(new Color(28, 31, 36));
                return lbl;
            });

            add(combo);
        }

        @Override
        public void setBounds(int x, int y, int w, int h) {
            super.setBounds(x, y, w, h);
            combo.setBounds(0, 0, w, h);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int w = getWidth();
            int h = getHeight();
            int arc = 18;

            g2.setColor(Color.WHITE);
            g2.fillRoundRect(0, 0, w, h, arc, arc);

            g2.setColor(new Color(220, 226, 236));
            g2.drawRoundRect(0, 0, w - 1, h - 1, arc, arc);

            // سهم يدوي
            g2.setColor(new Color(120, 126, 136));
            g2.setFont(new Font("Segoe UI", Font.BOLD, 14));
            g2.drawString("˅", w - 20, h / 2 + 5);

            g2.dispose();
            super.paintComponent(g);
        }

        public JComboBox<String> getCombo() { return combo; }
    }

    static class RoundedTextField extends JComponent {
        private final JTextField field;
        private final String placeholder;

        RoundedTextField(String placeholder) {
            this.placeholder = placeholder;
            setOpaque(false);
            setLayout(null);

            field = new JTextField();
            field.setBorder(new EmptyBorder(0, 16, 0, 16));
            field.setFont(new Font("Segoe UI", Font.PLAIN, 13));
            field.setForeground(new Color(28, 31, 36));
            field.setOpaque(false);
            field.setText(placeholder);

            field.addFocusListener(new java.awt.event.FocusAdapter() {
                @Override public void focusGained(java.awt.event.FocusEvent e) {
                    if (field.getText().equals(placeholder)) field.setText("");
                }
                @Override public void focusLost(java.awt.event.FocusEvent e) {
                    if (field.getText().trim().isEmpty()) field.setText(placeholder);
                }
            });

            add(field);
        }

        @Override
        public void setBounds(int x, int y, int w, int h) {
            super.setBounds(x, y, w, h);
            field.setBounds(0, 0, w, h);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int w = getWidth(), h = getHeight();
            int arc = 18;

            g2.setColor(Color.WHITE);
            g2.fillRoundRect(0, 0, w, h, arc, arc);

            g2.setColor(new Color(220, 226, 236));
            g2.drawRoundRect(0, 0, w - 1, h - 1, arc, arc);

            g2.dispose();
            super.paintComponent(g);
        }
    }

    static class PrimaryButton extends JButton {
        private final int arc = 18;

        PrimaryButton(String text) {
            super(text);
            setFont(new Font("Segoe UI", Font.BOLD, 14));
            setForeground(Color.WHITE);
            setBackground(new Color(47, 95, 208));
            setBorderPainted(false);
            setContentAreaFilled(false);
            setFocusPainted(false);
            setOpaque(false);
            setCursor(new Cursor(Cursor.HAND_CURSOR));
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int w = getWidth(), h = getHeight();

            g2.setColor(new Color(0, 0, 0, 14));
            g2.fillRoundRect(2, 4, w - 4, h - 4, arc, arc);

            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, w, h - 2, arc, arc);

            g2.dispose();
            super.paintComponent(g);
        }

        @Override
        public boolean contains(int x, int y) {
            Shape s = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), arc, arc);
            return s.contains(x, y);
        }
    }

    static class BackButton extends JButton {
        BackButton() {
            super("←");
            setFont(new Font("Segoe UI", Font.BOLD, 18));
            setForeground(new Color(28, 31, 36));
            setBorderPainted(false);
            setContentAreaFilled(false);
            setFocusPainted(false);
            setOpaque(false);
            setCursor(new Cursor(Cursor.HAND_CURSOR));
            addActionListener(e -> JOptionPane.showMessageDialog(this, "Back (GUI only)"));
        }
    }

    enum TaskStatus { PENDING, DONE }

    static class TaskCard extends RoundedCard {
        TaskCard(String title, String student, TaskStatus status, String due) {
            super(24);
            setLayout(null);

            JLabel t = new JLabel(title);
            t.setFont(new Font("Segoe UI", Font.BOLD, 16));
            t.setForeground(new Color(28, 31, 36));
            t.setBounds(18, 18, 260, 22);
            add(t);

            CircleIconButton edit = new CircleIconButton(CircleIconButton.IconType.EDIT);
            edit.setBounds(310, 16, 36, 36);
            add(edit);

            CircleIconButton trash = new CircleIconButton(CircleIconButton.IconType.TRASH);
            trash.setBounds(352, 16, 36, 36);
            add(trash);

            JLabel studentRow = new JLabel("  " + student);
            studentRow.setFont(new Font("Segoe UI", Font.PLAIN, 12));
            studentRow.setForeground(new Color(144, 150, 160));
            studentRow.setBounds(18, 44, 220, 18);
            add(studentRow);

            StatusPill pill = new StatusPill(status);
            pill.setBounds(18, 68, 120, 36);
            add(pill);

            DuePill duePill = new DuePill(due);
            duePill.setBounds(270, 68, 118, 36);
            add(duePill);

            edit.addActionListener(e -> JOptionPane.showMessageDialog(this, "Edit (GUI only)"));
            trash.addActionListener(e -> JOptionPane.showMessageDialog(this, "Delete (GUI only)"));
        }
    }

    static class CircleIconButton extends JButton {
        enum IconType { EDIT, TRASH }
        private final IconType type;

        CircleIconButton(IconType type) {
            super("");
            this.type = type;
            setOpaque(false);
            setBorderPainted(false);
            setContentAreaFilled(false);
            setFocusPainted(false);
            setCursor(new Cursor(Cursor.HAND_CURSOR));
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int s = Math.min(getWidth(), getHeight());

            g2.setColor(new Color(0, 0, 0, 10));
            g2.fillOval(2, 3, s - 4, s - 4);

            g2.setColor(new Color(245, 247, 252));
            g2.fillOval(0, 0, s, s);

            g2.setColor(new Color(220, 226, 236));
            g2.drawOval(0, 0, s - 1, s - 1);

            g2.setStroke(new BasicStroke(2f));
            g2.setColor(new Color(140, 146, 156));

            int cx = s / 2, cy = s / 2;

            if (type == IconType.EDIT) {
                g2.drawLine(cx - 6, cy + 6, cx + 7, cy - 7);
                g2.drawLine(cx + 3, cy - 10, cx + 10, cy - 3);
                g2.drawLine(cx - 10, cy + 10, cx - 3, cy + 10);
            } else {
                g2.drawRect(cx - 6, cy - 3, 12, 12);
                g2.drawLine(cx - 8, cy - 3, cx + 8, cy - 3);
                g2.drawLine(cx - 4, cy - 7, cx + 4, cy - 7);
            }

            g2.dispose();
            super.paintComponent(g);
        }

        @Override
        public boolean contains(int x, int y) {
            int s = Math.min(getWidth(), getHeight());
            Shape circle = new Ellipse2D.Float(0, 0, s, s);
            return circle.contains(x, y);
        }
    }

    static class DuePill extends JComponent {
        private final String text;

        DuePill(String text) {
            this.text = text;
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int w = getWidth(), h = getHeight();
            int arc = 16;

            g2.setColor(new Color(245, 247, 252));
            g2.fillRoundRect(0, 0, w, h, arc, arc);

            g2.setColor(new Color(220, 226, 236));
            g2.drawRoundRect(0, 0, w - 1, h - 1, arc, arc);

            g2.setFont(new Font("Segoe UI", Font.PLAIN, 12));
            g2.setColor(new Color(144, 150, 160));

            FontMetrics fm = g2.getFontMetrics();
            int tx = (w - fm.stringWidth(text)) / 2;
            int ty = (h + fm.getAscent()) / 2 - 2;
            g2.drawString(text, tx, ty);

            g2.dispose();
        }
    }

    static class StatusPill extends JComponent {
        private TaskStatus status;

        StatusPill(TaskStatus status) {
            this.status = status;
            setOpaque(false);
            setCursor(new Cursor(Cursor.HAND_CURSOR));

            addMouseListener(new java.awt.event.MouseAdapter() {
                @Override public void mouseClicked(java.awt.event.MouseEvent e) {
                    StatusPill.this.status = (StatusPill.this.status == TaskStatus.PENDING) ? TaskStatus.DONE : TaskStatus.PENDING;
                    repaint();
                }
            });
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int w = getWidth(), h = getHeight();
            int arc = 16;

            Color bg, border, textC;

            if (status == TaskStatus.PENDING) {
                bg = new Color(253, 242, 220);
                border = new Color(212, 164, 60);
                textC = new Color(120, 80, 10);
            } else {
                bg = new Color(227, 247, 238);
                border = new Color(68, 168, 120);
                textC = new Color(20, 110, 70);
            }

            g2.setColor(bg);
            g2.fillRoundRect(0, 0, w, h, arc, arc);

            g2.setColor(border);
            g2.drawRoundRect(0, 0, w - 1, h - 1, arc, arc);

            String label = (status == TaskStatus.PENDING) ? "Pending" : "Done";
            g2.setFont(new Font("Segoe UI", Font.BOLD, 12));
            g2.setColor(textC);

            FontMetrics fm = g2.getFontMetrics();
            int tx = 18;
            int ty = (h + fm.getAscent()) / 2 - 2;
            g2.drawString(label, tx, ty);

            g2.setFont(new Font("Segoe UI", Font.BOLD, 14));
            g2.setColor(new Color(120, 126, 136));
            g2.drawString("˅", w - 22, ty);

            g2.dispose();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SupervisorManageTasks().setVisible(true));
    }
}