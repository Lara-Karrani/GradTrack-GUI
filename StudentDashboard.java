
package com.mycompany.gradtrack_system;



import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class StudentDashboard extends JFrame {

    public StudentDashboard() {
        setTitle("GRADTRACK - Student Dashboard");
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
        int P = 22;                 // page padding
        int cardW = W - (P * 2);     // content width

        // ===== Top title =====
        JLabel topTitle = new JLabel("STUDENT DASHBOARD", SwingConstants.CENTER);
        topTitle.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        topTitle.setForeground(new Color(108, 114, 122));
        topTitle.setBounds(0, 30, W, 20);
        bg.add(topTitle);

        JLabel welcome = new JLabel("Welcome, Lara");
        welcome.setFont(new Font("Segoe UI", Font.BOLD, 30));
        welcome.setForeground(new Color(28, 31, 36));
        welcome.setBounds(P, 70, cardW, 40);
        bg.add(welcome);

        JLabel project = new JLabel("Project: GradTrack");
        project.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        project.setForeground(new Color(108, 114, 122));
        project.setBounds(P, 112, cardW, 22);
        bg.add(project);

        // ===== Progress card =====
        RoundedCard progressCard = new RoundedCard(22);
        progressCard.setLayout(null);
        progressCard.setBounds(P, 155, cardW, 115);
        bg.add(progressCard);

        JLabel progressLbl = new JLabel("Project Progress");
        progressLbl.setFont(new Font("Segoe UI", Font.BOLD, 16));
        progressLbl.setForeground(new Color(28, 31, 36));
        progressLbl.setBounds(16, 16, 200, 20);
        progressCard.add(progressLbl);

        JLabel progressPct = new JLabel("80%", SwingConstants.RIGHT);
        progressPct.setFont(new Font("Segoe UI", Font.BOLD, 18));
        progressPct.setForeground(new Color(47, 95, 208));
        progressPct.setBounds(progressCard.getWidth() - 80, 14, 60, 24);
        progressCard.add(progressPct);

        ProgressBarPill bar = new ProgressBarPill(80);
        bar.setBounds(16, 56, progressCard.getWidth() - 32, 16);
        progressCard.add(bar);

        // ===== Tiles (2x2) =====
        int tileGap = 14;
        int tileW = (cardW - tileGap) / 2;
        int tileH = 120;

        DashboardTile profile = new DashboardTile("Profile", TileIcon.USER, 0);
        profile.setBounds(P, 290, tileW, tileH);
        bg.add(profile);

        DashboardTile messages = new DashboardTile("Messages", TileIcon.CHAT, 1);
        messages.setBounds(P + tileW + tileGap, 290, tileW, tileH);
        bg.add(messages);

        DashboardTile milestones = new DashboardTile("Milestones", TileIcon.FLAG, 0);
        milestones.setBounds(P, 290 + tileH + tileGap, tileW, tileH);
        bg.add(milestones);

        DashboardTile submissions = new DashboardTile("Submissions", TileIcon.DOC, 0);
        submissions.setBounds(P + tileW + tileGap, 290 + tileH + tileGap, tileW, tileH);
        bg.add(submissions);

        // ===== Recent Milestones =====
        JLabel recent = new JLabel("Recent Milestones");
        recent.setFont(new Font("Segoe UI", Font.BOLD, 18));
        recent.setForeground(new Color(28, 31, 36));
        recent.setBounds(P, 290 + (tileH * 2) + tileGap + 18, cardW, 26);
        bg.add(recent);

        int listY = recent.getY() + 36;

        MilestoneCard m1 = new MilestoneCard(
                StatusIcon.DASHED,
                "Report Draft 1",
                "(Pending)",
                "Due: Oct 15, 2023",
                new Color(108, 114, 122),
                new Color(108, 114, 122)
        );
        m1.setBounds(P, listY, cardW, 90);
        bg.add(m1);

        MilestoneCard m2 = new MilestoneCard(
                StatusIcon.CLOCK,
                "System Architecture Design",
                "(In Progress)",
                "",
                new Color(28, 31, 36),
                new Color(212, 164, 60)
        );
        m2.setBounds(P, listY + 102, cardW, 90);
        bg.add(m2);

        // GUI-only clicks
        profile.setOnClick(() -> JOptionPane.showMessageDialog(this, "Open Profile (GUI only)"));
        messages.setOnClick(() -> JOptionPane.showMessageDialog(this, "Open Messages (GUI only)"));
        milestones.setOnClick(() -> JOptionPane.showMessageDialog(this, "Open Milestones (GUI only)"));
        submissions.setOnClick(() -> JOptionPane.showMessageDialog(this, "Open Submissions (GUI only)"));
    }

    // ================== UI Components ==================

    static class BackgroundPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int w = getWidth(), h = getHeight();

            g2.setColor(new Color(244, 247, 252));
            g2.fillRect(0, 0, w, h);

            g2.setColor(new Color(226, 236, 255));
            g2.fillOval(-120, -70, 420, 300);

            g2.setColor(new Color(238, 244, 255));
            g2.fillOval(-160, h - 340, 520, 520);

            g2.setColor(new Color(233, 241, 255));
            g2.fillOval(w - 320, h - 360, 520, 520);

            g2.dispose();
        }
    }

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

            g2.setColor(new Color(0, 0, 0, 14));
            g2.fillRoundRect(2, 4, w - 4, h - 4, arc, arc);

            g2.setColor(Color.WHITE);
            g2.fillRoundRect(0, 0, w, h - 2, arc, arc);

            g2.setColor(new Color(230, 234, 242));
            g2.drawRoundRect(0, 0, w - 1, h - 3, arc, arc);

            g2.dispose();
            super.paintComponent(g);
        }
    }

    static class ProgressBarPill extends JComponent {
        private final int percent;

        ProgressBarPill(int percent) {
            this.percent = Math.max(0, Math.min(100, percent));
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
            g2.setColor(new Color(47, 95, 208));
            g2.fillRoundRect(0, 0, fillW, h, arc, arc);

            g2.dispose();
        }
    }

    enum TileIcon { USER, CHAT, FLAG, DOC }

    static class DashboardTile extends RoundedCard {
        private Runnable onClick;

        DashboardTile(String text, TileIcon icon, int badgeCount) {
            super(22);
            setLayout(null);
            setCursor(new Cursor(Cursor.HAND_CURSOR));

            IconBubble bubble = new IconBubble(icon, badgeCount);
            bubble.setBounds((160 - 56) / 2, 22, 56, 56);
            add(bubble);

            JLabel lbl = new JLabel(text, SwingConstants.CENTER);
            lbl.setFont(new Font("Segoe UI", Font.PLAIN, 16));
            lbl.setForeground(new Color(28, 31, 36));
            lbl.setBounds(0, 84, 160, 22);
            add(lbl);

            addMouseListener(new java.awt.event.MouseAdapter() {
                @Override public void mouseClicked(java.awt.event.MouseEvent e) {
                    if (onClick != null) onClick.run();
                }
            });
        }

        @Override
        public void setBounds(int x, int y, int w, int h) {
            super.setBounds(x, y, w, h);
            // Reposition internal items based on width
            for (Component c : getComponents()) {
                if (c instanceof IconBubble) c.setBounds((w - 56) / 2, 22, 56, 56);
                if (c instanceof JLabel) c.setBounds(0, 84, w, 22);
            }
        }

        void setOnClick(Runnable r) { this.onClick = r; }
    }

    static class IconBubble extends JComponent {
        private final TileIcon icon;
        private final int badgeCount;

        IconBubble(TileIcon icon, int badgeCount) {
            this.icon = icon;
            this.badgeCount = badgeCount;
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int s = Math.min(getWidth(), getHeight());

            g2.setColor(new Color(238, 242, 250));
            g2.fillOval(0, 0, s, s);

            g2.setStroke(new BasicStroke(2f));
            g2.setColor(new Color(130, 136, 146));

            int cx = s / 2;
            int cy = s / 2;

            switch (icon) {
                case USER -> {
                    g2.drawOval(cx - 8, cy - 10, 16, 16);
                    g2.drawArc(cx - 14, cy + 2, 28, 18, 0, 180);
                }
                case CHAT -> {
                    g2.drawRoundRect(cx - 13, cy - 9, 26, 18, 8, 8);
                    Polygon tail = new Polygon();
                    tail.addPoint(cx - 4, cy + 9);
                    tail.addPoint(cx - 10, cy + 14);
                    tail.addPoint(cx - 2, cy + 12);
                    g2.drawPolygon(tail);
                }
                case FLAG -> {
                    g2.drawLine(cx - 10, cy - 12, cx - 10, cy + 14);
                    g2.drawRoundRect(cx - 10, cy - 12, 22, 14, 6, 6);
                }
                case DOC -> {
                    g2.drawRoundRect(cx - 10, cy - 12, 20, 26, 6, 6);
                    g2.drawLine(cx - 6, cy - 4, cx + 6, cy - 4);
                    g2.drawLine(cx - 6, cy + 2, cx + 6, cy + 2);
                }
            }

            if (badgeCount > 0) {
                g2.setColor(new Color(217, 83, 79));
                g2.fillOval(s - 18, 6, 14, 14);
                g2.setColor(Color.WHITE);
                g2.setFont(new Font("Segoe UI", Font.BOLD, 10));
                String t = String.valueOf(badgeCount);
                FontMetrics fm = g2.getFontMetrics();
                int tx = (s - 18) + (14 - fm.stringWidth(t)) / 2;
                int ty = 6 + ((14 - fm.getHeight()) / 2) + fm.getAscent();
                g2.drawString(t, tx, ty);
            }

            g2.dispose();
        }
    }

    enum StatusIcon { DASHED, CLOCK }

    static class MilestoneCard extends RoundedCard {
        MilestoneCard(StatusIcon icon, String title, String status, String sub, Color titleColor, Color statusColor) {
            super(22);
            setLayout(null);

            StatusBadge left = new StatusBadge(icon);
            left.setBounds(16, 26, 34, 34);
            add(left);

            JLabel t = new JLabel(title);
            t.setFont(new Font("Segoe UI", Font.BOLD, 14));
            t.setForeground(titleColor);
            t.setBounds(62, 20, 240, 22);
            add(t);

            JLabel st = new JLabel(status);
            st.setFont(new Font("Segoe UI", Font.PLAIN, 13));
            st.setForeground(statusColor);
            st.setBounds(260, 22, 140, 20);
            add(st);

            if (sub != null && !sub.isBlank()) {
                JLabel subLbl = new JLabel(sub);
                subLbl.setFont(new Font("Segoe UI", Font.PLAIN, 12));
                subLbl.setForeground(new Color(108, 114, 122));
                subLbl.setBounds(62, 46, 240, 20);
                add(subLbl);
            }
        }
    }

    static class StatusBadge extends JComponent {
        private final StatusIcon icon;

        StatusBadge(StatusIcon icon) {
            this.icon = icon;
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int s = Math.min(getWidth(), getHeight());

            g2.setColor(new Color(245, 247, 252));
            g2.fillOval(0, 0, s, s);

            g2.setStroke(new BasicStroke(2f));

            if (icon == StatusIcon.DASHED) {
                g2.setColor(new Color(160, 166, 176));
                Stroke dashed = new BasicStroke(2f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL,
                        0, new float[]{3f, 3f}, 0);
                g2.setStroke(dashed);
                g2.drawOval(7, 7, 20, 20);
            } else {
                g2.setColor(new Color(212, 164, 60));
                g2.drawOval(7, 7, 20, 20);
                g2.drawLine(17, 17, 17, 12);
                g2.drawLine(17, 17, 22, 18);
            }

            g2.dispose();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new StudentDashboard().setVisible(true));
    }
}