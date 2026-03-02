
package com.mycompany.gradtrack_system;


import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class Milestones extends JFrame {

    public Milestones() {
        setTitle("GRADTRACK - Milestones");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(420, 860);
        setLocationRelativeTo(null);
        setResizable(false);

        BackgroundPanel bg = new BackgroundPanel();
        bg.setLayout(null);
        setContentPane(bg);

        int W = 420;
        int P = 22;
        int cardW = W - (P * 2);

        // ===== Title =====
        JLabel title = new JLabel("MILESTONES", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 18));
        title.setForeground(new Color(28, 31, 36));
        title.setBounds(0, 40, W, 30);
        bg.add(title);

        int y = 110;

        // 1️⃣ Pending
        MilestoneCard m1 = new MilestoneCard(
                "Report Draft 1",
                "Due: Oct 15, 2023",
                Status.PENDING
        );
        m1.setBounds(P, y, cardW, 110);
        bg.add(m1);

        y += 130;

        // 2️⃣ In Progress (Highlighted)
        MilestoneCard m2 = new MilestoneCard(
                "System Architecture Design",
                "Due: Oct 05, 2023",
                Status.IN_PROGRESS
        );
        m2.setBounds(P, y, cardW, 120);
        bg.add(m2);

        y += 140;

        // 3️⃣ Done
        MilestoneCard m3 = new MilestoneCard(
                "Abstract & Introduction",
                "Completed: Sep 28, 2023",
                Status.DONE
        );
        m3.setBounds(P, y, cardW, 110);
        bg.add(m3);

        y += 130;

        // 4️⃣ Done
        MilestoneCard m4 = new MilestoneCard(
                "Project Proposal",
                "Completed: Sep 15, 2023",
                Status.DONE
        );
        m4.setBounds(P, y, cardW, 110);
        bg.add(m4);
    }

    // ================= UI =================

    enum Status { PENDING, IN_PROGRESS, DONE }

    static class BackgroundPanel extends JPanel {
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int w = getWidth();
            int h = getHeight();

            g2.setColor(new Color(244, 247, 252));
            g2.fillRect(0, 0, w, h);

            g2.setColor(new Color(226, 236, 255));
            g2.fillOval(-120, -60, 520, 300);

            g2.setColor(new Color(238, 244, 255));
            g2.fillOval(w - 400, h - 500, 700, 600);

            g2.dispose();
        }
    }

    static class MilestoneCard extends JPanel {

        private final String title;
        private final String subtitle;
        private final Status status;

        MilestoneCard(String title, String subtitle, Status status) {
            this.title = title;
            this.subtitle = subtitle;
            this.status = status;
            setOpaque(false);
            setLayout(null);
        }

        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int w = getWidth();
            int h = getHeight();
            int arc = 24;

            Color cardColor = Color.WHITE;

            if (status == Status.IN_PROGRESS)
                cardColor = new Color(235, 242, 255);

            // shadow
            g2.setColor(new Color(0, 0, 0, 14));
            g2.fillRoundRect(2, 4, w - 4, h - 4, arc, arc);

            // card
            g2.setColor(cardColor);
            g2.fillRoundRect(0, 0, w, h - 2, arc, arc);

            // border
            g2.setColor(new Color(230, 234, 242));
            g2.drawRoundRect(0, 0, w - 1, h - 3, arc, arc);

            // LEFT BLUE STRIP for IN_PROGRESS
            if (status == Status.IN_PROGRESS) {
                g2.setColor(new Color(47, 95, 208));
                g2.fillRoundRect(0, 0, 8, h - 2, arc, arc);
            }

            // icon circle
            Color iconBg = new Color(240, 243, 250);
            Color iconColor = new Color(150, 156, 168);

            if (status == Status.DONE) {
                iconBg = new Color(223, 245, 234);
                iconColor = new Color(68, 168, 120);
            }

            if (status == Status.IN_PROGRESS) {
                iconBg = new Color(225, 234, 252);
                iconColor = new Color(47, 95, 208);
            }

            g2.setColor(iconBg);
            g2.fillOval(20, 30, 48, 48);

            g2.setStroke(new BasicStroke(2f));
            g2.setColor(iconColor);

            int cx = 44;
            int cy = 54;

            if (status == Status.PENDING) {
                g2.drawOval(cx - 10, cy - 10, 20, 20);
            } else if (status == Status.IN_PROGRESS) {
                g2.drawOval(cx - 10, cy - 10, 20, 20);
                g2.drawLine(cx, cy - 10, cx, cy);
                g2.drawLine(cx, cy, cx + 6, cy + 4);
            } else {
                g2.drawOval(cx - 10, cy - 10, 20, 20);
                g2.drawLine(cx - 5, cy, cx - 1, cy + 4);
                g2.drawLine(cx - 1, cy + 4, cx + 6, cy - 4);
            }

            // Title
            g2.setFont(new Font("Segoe UI", Font.BOLD, 15));
            g2.setColor(new Color(28, 31, 36));
            g2.drawString(title, 86, 45);

            // Subtitle
            g2.setFont(new Font("Segoe UI", Font.PLAIN, 13));
            g2.setColor(new Color(144, 150, 160));
            g2.drawString(subtitle, 86, 65);

            // Status badge
            drawBadge(g2, w, h);

            g2.dispose();
        }

        private void drawBadge(Graphics2D g2, int w, int h) {
            String text = "";
            Color bgColor = new Color(230, 234, 242);
            Color txtColor = new Color(120, 126, 138);

            if (status == Status.PENDING) {
                text = "Pending";
            } else if (status == Status.IN_PROGRESS) {
                text = "In Progress";
                bgColor = new Color(220, 232, 255);
                txtColor = new Color(47, 95, 208);
            } else {
                text = "Done";
                bgColor = new Color(223, 245, 234);
                txtColor = new Color(68, 168, 120);
            }

            Font font = new Font("Segoe UI", Font.PLAIN, 12);
            g2.setFont(font);
            FontMetrics fm = g2.getFontMetrics();
            int tw = fm.stringWidth(text) + 20;

            int x = 86;
            int y = h - 36;

            g2.setColor(bgColor);
            g2.fillRoundRect(x, y, tw, 26, 18, 18);

            g2.setColor(txtColor);
            g2.drawString(text, x + 10, y + 17);

            // action button (only for pending & in progress)
            if (status == Status.PENDING || status == Status.IN_PROGRESS) {

                String btnText = status == Status.PENDING ? "Mark Done" : "Finish";
                int bw = fm.stringWidth(btnText) + 40;

                int bx = w - bw - 20;
                int by = h - 44;

                g2.setColor(status == Status.IN_PROGRESS
                        ? new Color(47, 95, 208)
                        : Color.WHITE);

                g2.fillRoundRect(bx, by, bw, 32, 18, 18);

                g2.setColor(status == Status.IN_PROGRESS
                        ? Color.WHITE
                        : new Color(28, 31, 36));

                g2.drawString(btnText, bx + 24, by + 20);

                if (status == Status.PENDING) {
                    g2.setColor(new Color(180, 186, 196));
                    g2.drawRoundRect(bx, by, bw, 32, 18, 18);
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Milestones().setVisible(true));
    }
}