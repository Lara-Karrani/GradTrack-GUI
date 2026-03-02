
package com.mycompany.gradtrack_system;


import javax.swing.*;
import java.awt.*;

public class Submissions extends JFrame {

    public Submissions() {
        setTitle("GRADTRACK - Submissions");
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

        // ===== Title =====
        JLabel title = new JLabel("SUBMISSIONS", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 18));
        title.setForeground(new Color(28, 31, 36));
        title.setBounds(0, 40, W, 30);
        bg.add(title);

        // ===== Top status card =====
        StatusHeaderCard header = new StatusHeaderCard("Need Changes", "v3.0");
        header.setBounds(P, 95, cardW, 90);
        bg.add(header);

        // ===== Section title =====
        JLabel sec = new JLabel("Version History");
        sec.setFont(new Font("Segoe UI", Font.BOLD, 14));
        sec.setForeground(new Color(28, 31, 36));
        sec.setBounds(P, 205, cardW, 20);
        bg.add(sec);

        // ===== Cards =====
        SubmissionCard v3 = new SubmissionCard(
                true,
                "Version 3 (Latest)",
                "Report_Draft_v3.pdf",
                "Need Changes",
                "Supervisor Note: The architecture\ndiagram needs more detail on the\ndatabase layer."
        );
        v3.setBounds(P, 235, cardW, 210);
        bg.add(v3);

        SubmissionCard v2 = new SubmissionCard(
                false,
                "Version 2",
                "Report_Draft_v2.pdf",
                "Archived",
                null
        );
        v2.setBounds(P, 465, cardW, 110);
        bg.add(v2);

        SubmissionCard v1 = new SubmissionCard(
                false,
                "Version 1",
                "Report_Draft_v1.pdf",
                "Archived",
                null
        );
        v1.setBounds(P, 590, cardW, 110);
        bg.add(v1);

        // ===== Bottom button =====
        RoundedButton upload = new RoundedButton("Upload New Version", 28, true);
        upload.setFont(new Font("Segoe UI", Font.BOLD, 16));
        upload.setBounds(P, 730, cardW, 60);
        bg.add(upload);

        upload.addActionListener(e -> JOptionPane.showMessageDialog(this, "Upload (GUI only)"));
    }

    // =================== UI ===================

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
            g2.fillOval(-120, -60, 520, 300);

            g2.setColor(new Color(238, 244, 255));
            g2.fillOval(w - 420, h - 520, 720, 640);

            g2.dispose();
        }
    }

    static class StatusHeaderCard extends JPanel {
        private final String status;
        private final String version;

        StatusHeaderCard(String status, String version) {
            this.status = status;
            this.version = version;
            setOpaque(false);
            setLayout(null);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int w = getWidth(), h = getHeight();
            int arc = 24;

            // shadow
            g2.setColor(new Color(0, 0, 0, 14));
            g2.fillRoundRect(2, 4, w - 4, h - 4, arc, arc);

            // card
            g2.setColor(Color.WHITE);
            g2.fillRoundRect(0, 0, w, h - 2, arc, arc);

            g2.setColor(new Color(230, 234, 242));
            g2.drawRoundRect(0, 0, w - 1, h - 3, arc, arc);

            // divider
            g2.setColor(new Color(235, 238, 245));
            g2.drawLine(w / 2, 16, w / 2, h - 18);

            // left
            g2.setFont(new Font("Segoe UI", Font.PLAIN, 13));
            g2.setColor(new Color(144, 150, 160));
            g2.drawString("Current Status", 18, 32);

            // status icon
            g2.setColor(new Color(212, 164, 60));
            g2.setStroke(new BasicStroke(2f));
            g2.drawOval(18, 46, 18, 18);
            g2.drawString("!", 25, 60);

            g2.setFont(new Font("Segoe UI", Font.BOLD, 16));
            g2.setColor(new Color(212, 164, 60));
            g2.drawString(status, 44, 62);

            // right
            g2.setFont(new Font("Segoe UI", Font.PLAIN, 13));
            g2.setColor(new Color(144, 150, 160));
            g2.drawString("Latest Version", w / 2 + 18, 32);

            g2.setFont(new Font("Segoe UI", Font.BOLD, 18));
            g2.setColor(new Color(28, 31, 36));
            g2.drawString(version, w / 2 + 18, 62);

            g2.dispose();
            super.paintComponent(g);
        }
    }

    static class SubmissionCard extends JPanel {
        private final boolean highlight;
        private final String title;
        private final String file;
        private final String badge;
        private final String note;

        SubmissionCard(boolean highlight, String title, String file, String badge, String note) {
            this.highlight = highlight;
            this.title = title;
            this.file = file;
            this.badge = badge;
            this.note = note;
            setOpaque(false);
            setLayout(null);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int w = getWidth(), h = getHeight();
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

            // highlight left strip
            if (highlight) {
                g2.setColor(new Color(212, 164, 60));
                g2.fillRoundRect(0, 0, 6, h - 2, arc, arc);
            }

            // doc icon circle
            g2.setColor(new Color(240, 243, 250));
            g2.fillOval(18, 18, 42, 42);

            g2.setStroke(new BasicStroke(2f));
            g2.setColor(new Color(47, 95, 208));
            int cx = 39, cy = 39;
            g2.drawRoundRect(cx - 8, cy - 12, 16, 22, 6, 6);
            g2.drawLine(cx - 5, cy - 3, cx + 5, cy - 3);

            // title/file
            g2.setFont(new Font("Segoe UI", Font.BOLD, 15));
            g2.setColor(new Color(28, 31, 36));
            g2.drawString(title, 70, 34);

            g2.setFont(new Font("Segoe UI", Font.PLAIN, 13));
            g2.setColor(new Color(144, 150, 160));
            g2.drawString(file, 70, 56);

            // badge
            drawBadge(g2, w);

            // note box (only highlight)
            if (note != null) {
                int nx = 70;
                int ny = 78;
                int nw = w - 90;
                int nh = 96;

                g2.setColor(new Color(244, 246, 250));
                g2.fillRoundRect(nx, ny, nw, nh, 18, 18);

                g2.setColor(new Color(225, 230, 240));
                g2.drawRoundRect(nx, ny, nw, nh, 18, 18);

                g2.setFont(new Font("Segoe UI", Font.ITALIC, 13));
                g2.setColor(new Color(120, 126, 138));

                drawMultiline(g2, "\"" + note + "\"", nx + 14, ny + 26, nw - 28);
            }

            g2.dispose();
            super.paintComponent(g);
        }

        private void drawBadge(Graphics2D g2, int w) {
            boolean needChanges = "Need Changes".equalsIgnoreCase(badge);

            Color bg = needChanges ? new Color(253, 242, 220) : new Color(236, 239, 246);
            Color tx = needChanges ? new Color(212, 164, 60) : new Color(150, 156, 168);

            g2.setFont(new Font("Segoe UI", Font.PLAIN, 12));
            FontMetrics fm = g2.getFontMetrics();
            int bw = fm.stringWidth(badge) + 22;

            int bx = w - bw - 18;
            int by = 20;

            g2.setColor(bg);
            g2.fillRoundRect(bx, by, bw, 26, 18, 18);

            g2.setColor(tx);
            g2.drawString(badge, bx + 11, by + 17);
        }

        private void drawMultiline(Graphics2D g2, String s, int x, int y, int maxW) {
            FontMetrics fm = g2.getFontMetrics();
            String[] lines = s.split("\n");
            int lineH = fm.getHeight();
            int yy = y;

            for (String line : lines) {
                if (fm.stringWidth(line) <= maxW) {
                    g2.drawString(line, x, yy);
                    yy += lineH;
                } else {
                    String[] words = line.split(" ");
                    StringBuilder cur = new StringBuilder();
                    for (String w : words) {
                        String test = cur.length() == 0 ? w : cur + " " + w;
                        if (fm.stringWidth(test) > maxW) {
                            g2.drawString(cur.toString(), x, yy);
                            yy += lineH;
                            cur = new StringBuilder(w);
                        } else cur = new StringBuilder(test);
                    }
                    if (cur.length() > 0) {
                        g2.drawString(cur.toString(), x, yy);
                        yy += lineH;
                    }
                }
            }
        }
    }

    static class RoundedButton extends JButton {
        private final int arc;
        private final boolean primary;

        RoundedButton(String text, int arc, boolean primary) {
            super(text);
            this.arc = arc;
            this.primary = primary;
            setFocusPainted(false);
            setBorderPainted(false);
            setContentAreaFilled(false);
            setOpaque(false);
            setCursor(new Cursor(Cursor.HAND_CURSOR));
            setForeground(primary ? Color.WHITE : new Color(28, 31, 36));
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int w = getWidth(), h = getHeight();

            // shadow
            g2.setColor(new Color(0, 0, 0, 16));
            g2.fillRoundRect(2, 4, w - 4, h - 4, arc, arc);

            // fill
            g2.setColor(primary ? new Color(47, 95, 208) : Color.WHITE);
            g2.fillRoundRect(0, 0, w, h - 2, arc, arc);

            if (!primary) {
                g2.setColor(new Color(230, 234, 242));
                g2.drawRoundRect(0, 0, w - 1, h - 3, arc, arc);
            }

            // upload icon (left)
            if (primary) {
                g2.setStroke(new BasicStroke(2f));
                g2.setColor(Color.WHITE);
                int ix = 26, iy = h / 2 - 10;
                g2.drawRoundRect(ix, iy, 16, 20, 6, 6);
                g2.drawLine(ix + 8, iy + 12, ix + 8, iy + 6);
                g2.drawLine(ix + 8, iy + 6, ix + 4, iy + 10);
                g2.drawLine(ix + 8, iy + 6, ix + 12, iy + 10);
            }

            g2.dispose();
            super.paintComponent(g);
        }

        @Override
        public boolean contains(int x, int y) {
            Shape s = new java.awt.geom.RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), arc, arc);
            return s.contains(x, y);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Submissions().setVisible(true));
    }
}