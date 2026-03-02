package com.mycompany.gradtrack_system;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class SupervisorDashboard extends JFrame {

    public SupervisorDashboard() {
        setTitle("GRADTRACK - Supervisor Dashboard");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(420, 860);
        setLocationRelativeTo(null);
        setResizable(false);

        try { UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel"); }
        catch (Exception ignored) {}

        BackgroundPanel bg = new BackgroundPanel();
        bg.setLayout(null);
        setContentPane(bg);

        int W = 420;
        int P = 22;
        int cardW = W - (P * 2);

        // ===== Header (Gradient + waves) =====
        int headerH = 230; //  أعلى مثل الآيفون + يمنع القص
        HeaderPanel header = new HeaderPanel();
        header.setLayout(null);
        header.setBounds(0, 0, W, headerH);
        bg.add(header);

        JLabel hd1 = new JLabel("SUPERVISOR DASHBOARD");
        hd1.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        hd1.setForeground(new Color(235, 240, 255));
        hd1.setBounds(P, 66, cardW, 18);
        header.add(hd1);

        JLabel hd2 = new JLabel("Welcome back, Dr. Samaher");
        hd2.setFont(new Font("Segoe UI", Font.BOLD, 26));
        hd2.setForeground(Color.WHITE);
        hd2.setBounds(P, 96, cardW, 36);
        header.add(hd2);

        // ===== Card: Student Monitoring (overlap into header) =====
        int monitoringY = headerH - 45;  // نزلناه لتحت عشان ما ينقص
        int monitoringH = 215;

        RoundedCard monitoring = new RoundedCard(26, Color.WHITE);
        monitoring.setLayout(null);
        monitoring.setBounds(P, monitoringY, cardW, monitoringH);
        bg.add(monitoring);

        JLabel monTitle = smallSectionTitle("STUDENT MONITORING");
        monTitle.setBounds(18, 16, 250, 18);
        monitoring.add(monTitle);

        StudentRow s1 = new StudentRow("Lara Ahmed", 80, new Color(47, 95, 208), "Last update: 2 hours ago");
        s1.setBounds(18, 46, cardW - 36, 72);
        monitoring.add(s1);

        JSeparator sep = new JSeparator();
        sep.setForeground(new Color(235, 238, 245));
        sep.setBounds(18, 122, cardW - 36, 1);
        monitoring.add(sep);

        StudentRow s2 = new StudentRow("Ahmad", 45, new Color(212, 164, 60), "Last update: 3 days ago");
        s2.setBounds(18, 136, cardW - 36, 72);
        monitoring.add(s2);

        // ===== Card: Management Actions =====
        int actionsY = monitoringY + monitoringH + 22;
        int actionsH = 360;

        RoundedCard actions = new RoundedCard(26, Color.WHITE);
        actions.setLayout(null);
        actions.setBounds(P, actionsY, cardW, actionsH);
        bg.add(actions);

        JLabel actTitle = smallSectionTitle("MANAGEMENT ACTIONS");
        actTitle.setBounds(18, 16, 250, 18);
        actions.add(actTitle);

        // inner container like iPhone design
        InnerContainer inner = new InnerContainer(22);
        inner.setBounds(18, 46, cardW - 36, 250);
        inner.setLayout(null);
        actions.add(inner);

        ActionRow r1 = new ActionRow(ActionIcon.CHAT, "Messages", "View all conversations",
                new Color(47, 95, 208), new Color(230, 239, 255), true);
        r1.setBounds(10, 10, inner.getWidth() - 20, 72);
        inner.add(r1);

        ActionRow r2 = new ActionRow(ActionIcon.DOC, "Review Submissions", "Approve or request changes",
                new Color(212, 164, 60), new Color(253, 242, 220), false);
        r2.setBounds(10, 92, inner.getWidth() - 20, 72);
        inner.add(r2);

        ActionRow r3 = new ActionRow(ActionIcon.FLAG, "Manage Tasks", "Create and assign milestones",
                new Color(68, 168, 120), new Color(227, 247, 238), false);
        r3.setBounds(10, 174, inner.getWidth() - 20, 72);
        inner.add(r3);

        // Bottom buttons
        int btnY = 312;
        int btnW = (cardW - 36 - 12) / 2;

        RoundedButton profileBtn = new RoundedButton("Profile", 22);
        profileBtn.setBackground(Color.WHITE);
        profileBtn.setForeground(new Color(70, 74, 82));
        profileBtn.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        profileBtn.setBounds(18, btnY, btnW, 44);
        actions.add(profileBtn);

        RoundedButton logoutBtn = new RoundedButton("Logout", 22);
        logoutBtn.setBackground(Color.WHITE);
        logoutBtn.setForeground(new Color(217, 83, 79));
        logoutBtn.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        logoutBtn.setBounds(18 + btnW + 12, btnY, btnW, 44);
        actions.add(logoutBtn);

        // GUI-only actions
        r1.setOnClick(() -> JOptionPane.showMessageDialog(this, "Open Messages (GUI only)"));
        r2.setOnClick(() -> JOptionPane.showMessageDialog(this, "Open Review Submissions (GUI only)"));
        r3.setOnClick(() -> JOptionPane.showMessageDialog(this, "Open Manage Tasks (GUI only)"));
        profileBtn.addActionListener(e -> JOptionPane.showMessageDialog(this, "Open Profile (GUI only)"));
        logoutBtn.addActionListener(e -> JOptionPane.showMessageDialog(this, "Logout (GUI only)"));
    }

    private JLabel smallSectionTitle(String text) {
        JLabel lbl = new JLabel(text);
        lbl.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lbl.setForeground(new Color(144, 150, 160));
        return lbl;
    }

    // ================= Panels / Components =================

    static class BackgroundPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int w = getWidth(), h = getHeight();

            g2.setColor(new Color(244, 247, 252));
            g2.fillRect(0, 0, w, h);

            // subtle blobs like iPhone background
            g2.setColor(new Color(226, 236, 255));
            g2.fillOval(-220, 420, 620, 520);

            g2.setColor(new Color(238, 244, 255));
            g2.fillOval(w - 420, 520, 740, 620);

            g2.setColor(new Color(233, 241, 255));
            g2.fillOval(w - 260, 260, 520, 420);

            g2.dispose();
        }
    }

    static class HeaderPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int w = getWidth(), h = getHeight();

            // gradient
            GradientPaint gp = new GradientPaint(0, 0, new Color(47, 95, 208), w, h, new Color(60, 108, 224));
            g2.setPaint(gp);
            g2.fillRect(0, 0, w, h);

            // light wave blobs inside header
            g2.setColor(new Color(255, 255, 255, 22));
            g2.fillOval(-120, -70, 440, 300);

            g2.setColor(new Color(255, 255, 255, 16));
            g2.fillOval(w - 360, 40, 560, 340);

            g2.setColor(new Color(255, 255, 255, 14));
            g2.fillOval(-170, h - 210, 590, 270);

            // curved bottom cut
            g2.setColor(new Color(244, 247, 252));
            g2.fillRoundRect(-60, h - 86, w + 120, 170, 110, 110);

            g2.dispose();
        }
    }

    static class RoundedCard extends JPanel {
        private final int arc;
        private final Color fill;

        RoundedCard(int arc, Color fill) {
            this.arc = arc;
            this.fill = fill;
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int w = getWidth(), h = getHeight();

            g2.setColor(new Color(0, 0, 0, 14));
            g2.fillRoundRect(2, 4, w - 4, h - 4, arc, arc);

            g2.setColor(fill);
            g2.fillRoundRect(0, 0, w, h - 2, arc, arc);

            g2.setColor(new Color(230, 234, 242));
            g2.drawRoundRect(0, 0, w - 1, h - 3, arc, arc);

            g2.dispose();
            super.paintComponent(g);
        }
    }

    static class InnerContainer extends JPanel {
        private final int arc;
        InnerContainer(int arc) {
            this.arc = arc;
            setOpaque(false);
        }
        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int w = getWidth(), h = getHeight();

            g2.setColor(new Color(247, 249, 255));
            g2.fillRoundRect(0, 0, w, h, arc, arc);

            g2.setColor(new Color(235, 238, 245));
            g2.drawRoundRect(0, 0, w - 1, h - 1, arc, arc);

            g2.dispose();
            super.paintComponent(g);
        }
    }

    static class StudentRow extends JPanel {
        StudentRow(String name, int percent, Color color, String update) {
            setOpaque(false);
            setLayout(null);

            JLabel n = new JLabel(name);
            n.setFont(new Font("Segoe UI", Font.BOLD, 14));
            n.setForeground(new Color(28, 31, 36));
            n.setBounds(0, 0, 220, 20);
            add(n);

            JLabel p = new JLabel(percent + "%", SwingConstants.RIGHT);
            p.setFont(new Font("Segoe UI", Font.BOLD, 14));
            p.setForeground(color);
            p.setBounds(0, 0, 0, 0);
            add(p);

            ProgressBarPill bar = new ProgressBarPill(percent, color);
            bar.setBounds(0, 28, 320, 10);
            add(bar);

            JLabel u = new JLabel(update);
            u.setFont(new Font("Segoe UI", Font.PLAIN, 12));
            u.setForeground(new Color(144, 150, 160));
            u.setBounds(0, 44, 260, 18);
            add(u);
        }

        @Override
        public void setBounds(int x, int y, int w, int h) {
            super.setBounds(x, y, w, h);
            for (Component c : getComponents()) {
                if (c instanceof JLabel lbl && lbl.getText().endsWith("%")) lbl.setBounds(w - 60, 0, 60, 20);
                if (c instanceof ProgressBarPill pb) pb.setBounds(0, 28, w, 10);
            }
        }
    }

    static class ProgressBarPill extends JComponent {
        private final int percent;
        private final Color fill;

        ProgressBarPill(int percent, Color fill) {
            this.percent = Math.max(0, Math.min(100, percent));
            this.fill = fill;
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int w = getWidth(), h = getHeight();
            int arc = h;

            g2.setColor(new Color(232, 236, 244));
            g2.fillRoundRect(0, 0, w, h, arc, arc);

            int fillW = (int) (w * (percent / 100.0));
            g2.setColor(fill);
            g2.fillRoundRect(0, 0, fillW, h, arc, arc);

            g2.dispose();
        }
    }

    enum ActionIcon { CHAT, DOC, FLAG }

    static class ActionRow extends JPanel {
        private Runnable onClick;

        private final ActionIcon icon;
        private final String title;
        private final String subtitle;
        private final Color iconColor;
        private final Color iconBg;
        private final boolean showDot;

        ActionRow(ActionIcon icon, String title, String subtitle, Color iconColor, Color iconBg, boolean showDot) {
            this.icon = icon;
            this.title = title;
            this.subtitle = subtitle;
            this.iconColor = iconColor;
            this.iconBg = iconBg;
            this.showDot = showDot;

            setOpaque(false);
            setLayout(null);
            setCursor(new Cursor(Cursor.HAND_CURSOR));

            addMouseListener(new java.awt.event.MouseAdapter() {
                @Override public void mouseClicked(java.awt.event.MouseEvent e) {
                    if (onClick != null) onClick.run();
                }
            });
        }

        void setOnClick(Runnable r) { this.onClick = r; }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int w = getWidth(), h = getHeight();
            int arc = 18;

            g2.setColor(Color.WHITE);
            g2.fillRoundRect(0, 0, w, h, arc, arc);

            g2.setColor(new Color(235, 238, 245));
            g2.drawRoundRect(0, 0, w - 1, h - 1, arc, arc);

            g2.setColor(iconBg);
            g2.fillRoundRect(14, 14, 44, 44, 14, 14);

            if (showDot) {
                g2.setColor(new Color(235, 87, 87));
                g2.fillOval(46, 14, 10, 10);
            }

            g2.setColor(iconColor);
            g2.setStroke(new BasicStroke(2f));
            int cx = 14 + 22;
            int cy = 14 + 22;

            switch (icon) {
                case CHAT -> {
                    g2.drawRoundRect(cx - 10, cy - 8, 20, 14, 6, 6);
                    Polygon tail = new Polygon();
                    tail.addPoint(cx - 2, cy + 6);
                    tail.addPoint(cx - 8, cy + 12);
                    tail.addPoint(cx, cy + 9);
                    g2.drawPolygon(tail);
                }
                case DOC -> {
                    g2.drawRoundRect(cx - 9, cy - 11, 18, 22, 6, 6);
                    g2.drawLine(cx - 5, cy - 3, cx + 5, cy - 3);
                    g2.drawLine(cx - 5, cy + 2, cx + 5, cy + 2);
                }
                case FLAG -> {
                    g2.drawLine(cx - 7, cy - 11, cx - 7, cy + 12);
                    g2.drawRoundRect(cx - 7, cy - 11, 16, 12, 6, 6);
                }
            }

            g2.setFont(new Font("Segoe UI", Font.BOLD, 15));
            g2.setColor(new Color(28, 31, 36));
            g2.drawString(title, 72, 34);

            g2.setFont(new Font("Segoe UI", Font.PLAIN, 12));
            g2.setColor(new Color(144, 150, 160));
            g2.drawString(subtitle, 72, 54);

            g2.setFont(new Font("Segoe UI", Font.BOLD, 18));
            g2.setColor(new Color(160, 166, 176));
            g2.drawString("›", w - 22, 44);

            g2.dispose();
            super.paintComponent(g);
        }
    }

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

            int w = getWidth(), h = getHeight();

            g2.setColor(new Color(0, 0, 0, 12));
            g2.fillRoundRect(2, 3, w - 4, h - 4, arc, arc);

            g2.setColor(getBackground());
            g2.fillRoundRect(0, 0, w, h - 2, arc, arc);

            g2.setColor(new Color(230, 234, 242));
            g2.drawRoundRect(0, 0, w - 1, h - 3, arc, arc);

            g2.dispose();
            super.paintComponent(g);
        }

        @Override
        public boolean contains(int x, int y) {
            Shape s = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), arc, arc);
            return s.contains(x, y);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SupervisorDashboard().setVisible(true));
    }
}