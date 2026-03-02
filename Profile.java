/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.gradtrack_system;


import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class Profile extends JFrame {

    public Profile() {
        setTitle("GRADTRACK - Profile");
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
        int P = 24;
        int cardW = getWidth() - (P * 2);

        // ===== Name =====
        JLabel name = new JLabel("Lara Karrani", SwingConstants.CENTER);
        name.setFont(new Font("Segoe UI", Font.BOLD, 28));
        name.setForeground(new Color(28, 31, 36));
        name.setBounds(0, 120, W, 36);
        bg.add(name);

        // ===== Role pill =====
        PillLabel role = new PillLabel("Undergraduate Student");
        role.setBounds((W - 210) / 2, 165, 210, 36);
        bg.add(role);

        // ===== Cards =====
        InfoCard email = new InfoCard(
                CardIcon.MAIL,
                "Email Address",
                "Lara001@stu.kau.edu.sa"
        );
        email.setBounds(P, 240, cardW, 92);
        bg.add(email);

        InfoCard dept = new InfoCard(
                CardIcon.BUILDING,
                "Department",
                "Information Technology"
        );
        dept.setBounds(P, 350, cardW, 92);
        bg.add(dept);

        InfoCard course = new InfoCard(
                CardIcon.BOOK,
                "Course",
                "CPIT 305 Phase 2"
        );
        course.setBounds(P, 460, cardW, 92);
        bg.add(course);
    }

    // ================== UI Parts ==================

    static class BackgroundPanel extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int w = getWidth(), h = getHeight();

            // base
            g2.setColor(new Color(244, 247, 252));
            g2.fillRect(0, 0, w, h);

            // soft wave-ish blobs (subtle)
            g2.setColor(new Color(226, 236, 255));
            g2.fillOval(-140, -80, 520, 340);

            g2.setColor(new Color(238, 244, 255));
            g2.fillOval(w - 380, 80, 520, 360);

            g2.setColor(new Color(233, 241, 255));
            g2.fillOval(-220, h - 420, 620, 560);

            g2.setColor(new Color(238, 244, 255));
            g2.fillOval(w - 420, h - 520, 680, 620);

            g2.dispose();
        }
    }

    static class PillLabel extends JComponent {
        private final String text;

        PillLabel(String text) {
            this.text = text;
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int w = getWidth();
            int h = getHeight();
            int arc = h;

            g2.setColor(new Color(232, 240, 255));
            g2.fillRoundRect(0, 0, w, h, arc, arc);

            g2.setColor(new Color(47, 95, 208));
            g2.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            FontMetrics fm = g2.getFontMetrics();
            int tx = (w - fm.stringWidth(text)) / 2;
            int ty = (h - fm.getHeight()) / 2 + fm.getAscent();
            g2.drawString(text, tx, ty);

            g2.dispose();
        }
    }

    enum CardIcon { MAIL, BUILDING, BOOK }

    static class InfoCard extends JPanel {
        private final CardIcon icon;
        private final String title;
        private final String value;

        InfoCard(CardIcon icon, String title, String value) {
            this.icon = icon;
            this.title = title;
            this.value = value;
            setOpaque(false);
            setLayout(null);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int w = getWidth();
            int h = getHeight();
            int arc = 24;

            // shadow
            g2.setColor(new Color(0, 0, 0, 14));
            g2.fillRoundRect(2, 4, w - 4, h - 4, arc, arc);

            // card
            g2.setColor(Color.WHITE);
            g2.fillRoundRect(0, 0, w, h - 2, arc, arc);

            // border
            g2.setColor(new Color(230, 234, 242));
            g2.drawRoundRect(0, 0, w - 1, h - 3, arc, arc);

            // left icon circle
            g2.setColor(new Color(245, 247, 252));
            g2.fillOval(18, 22, 48, 48);

            // icon drawing
            drawIcon(g2, 18, 22, 48, 48);

            // texts
            g2.setColor(new Color(144, 150, 160));
            g2.setFont(new Font("Segoe UI", Font.PLAIN, 13));
            g2.drawString(title, 86, 42);

            g2.setColor(new Color(28, 31, 36));
            g2.setFont(new Font("Segoe UI", Font.BOLD, 16));
            g2.drawString(value, 86, 68);

            g2.dispose();
            super.paintComponent(g);
        }

        private void drawIcon(Graphics2D g2, int x, int y, int w, int h) {
            g2.setStroke(new BasicStroke(2f));
            g2.setColor(new Color(130, 136, 146));

            int cx = x + w / 2;
            int cy = y + h / 2;

            switch (icon) {
                case MAIL -> {
                    int bx = cx - 12, by = cy - 8;
                    g2.drawRoundRect(bx, by, 24, 16, 6, 6);
                    g2.drawLine(bx, by, cx, by + 8);
                    g2.drawLine(cx, by + 8, bx + 24, by);
                }
                case BUILDING -> {
                    g2.drawRoundRect(cx - 12, cy - 10, 24, 18, 6, 6);
                    g2.drawLine(cx - 14, cy - 10, cx + 14, cy - 10);
                    g2.drawLine(cx - 10, cy + 8, cx + 10, cy + 8);
                    // columns
                    g2.drawLine(cx - 8, cy - 6, cx - 8, cy + 8);
                    g2.drawLine(cx, cy - 6, cx, cy + 8);
                    g2.drawLine(cx + 8, cy - 6, cx + 8, cy + 8);
                }
                case BOOK -> {
                    g2.drawRoundRect(cx - 12, cy - 10, 24, 20, 6, 6);
                    g2.drawLine(cx, cy - 10, cx, cy + 10);
                    g2.drawLine(cx - 6, cy - 6, cx - 2, cy - 6);
                    g2.drawLine(cx + 2, cy - 6, cx + 6, cy - 6);
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Profile().setVisible(true));
    }
}