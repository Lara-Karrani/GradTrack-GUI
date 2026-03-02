package com.mycompany.gradtrack_system;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class SupervisorReviewSubmissions extends JFrame {

    public SupervisorReviewSubmissions() {
        setTitle("GRADTRACK - Review Submissions");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(420, 860);
        setLocationRelativeTo(null);
        setResizable(false);

        try { UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel"); }
        catch (Exception ignored) {}

        int W = 420;
        int P = 22;
        int contentW = W - (P * 2);

        BgPanel bg = new BgPanel();
        bg.setLayout(null);
        setContentPane(bg);

        // ===== Top: back + title =====
        JLabel back = new JLabel("←");
        back.setFont(new Font("Segoe UI", Font.BOLD, 20));
        back.setForeground(new Color(28, 31, 36));
        back.setBounds(P, 34, 30, 30);
        back.setCursor(new Cursor(Cursor.HAND_CURSOR));
        back.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override public void mouseClicked(java.awt.event.MouseEvent e) {
                JOptionPane.showMessageDialog(SupervisorReviewSubmissions.this, "Back (GUI only)");
            }
        });
        bg.add(back);

        JLabel title = new JLabel("REVIEW SUBMISSIONS", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 16));
        title.setForeground(new Color(28, 31, 36));
        title.setBounds(0, 40, W, 24);
        bg.add(title);

        // ===== Main Card (top) =====
        RoundedCard mainCard = new RoundedCard(26, new Color(250, 251, 255));
        mainCard.setLayout(null);
        mainCard.setBounds(P, 92, contentW, 470);
        bg.add(mainCard);

        // Student name + subtitle
        JLabel studentName = new JLabel("Lara Ahmed");
        studentName.setFont(new Font("Segoe UI", Font.BOLD, 16));
        studentName.setForeground(new Color(28, 31, 36));
        studentName.setBounds(18, 18, 220, 22);
        mainCard.add(studentName);

        JLabel sys = new JLabel("GradTrack System");
        sys.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        sys.setForeground(new Color(140, 146, 156));
        sys.setBounds(18, 40, 220, 18);
        mainCard.add(sys);

        // Version pill (v3 Pending)
        Pill versionPill = new Pill("v3 Pending", new Color(230, 239, 255), new Color(47, 95, 208));
        versionPill.setBounds(contentW - 18 - 110, 18, 110, 28);
        mainCard.add(versionPill);

        // File row card
        RoundedCard fileRow = new RoundedCard(20, Color.WHITE);
        fileRow.setLayout(null);
        fileRow.setBounds(18, 74, contentW - 36, 78);
        mainCard.add(fileRow);

        DocIcon doc = new DocIcon(new Color(47, 95, 208), new Color(230, 239, 255));
        doc.setBounds(14, 18, 42, 42);
        fileRow.add(doc);

        JLabel fileName = new JLabel("Report_Draft_v3.pdf");
        fileName.setFont(new Font("Segoe UI", Font.BOLD, 13));
        fileName.setForeground(new Color(28, 31, 36));
        fileName.setBounds(66, 18, 220, 18);
        fileRow.add(fileName);

        JLabel uploaded = new JLabel("Uploaded today at 10:30 AM");
        uploaded.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        uploaded.setForeground(new Color(140, 146, 156));
        uploaded.setBounds(66, 38, 220, 16);
        fileRow.add(uploaded);

        DownloadButton download = new DownloadButton();
        download.setBounds(fileRow.getWidth() - 52, 18, 38, 38);
        fileRow.add(download);

        // Section: Supervisor Decision
        JLabel decisionTitle = sectionLabel("SUPERVISOR DECISION");
        decisionTitle.setBounds(18, 168, 250, 18);
        mainCard.add(decisionTitle);

        ChoicePill approve = new ChoicePill("Approve", ChoicePill.Type.APPROVE);
        ChoicePill needChanges = new ChoicePill("Need Changes", ChoicePill.Type.CHANGES);

        approve.setBounds(18, 194, (contentW - 36 - 12) / 2, 44);
        needChanges.setBounds(18 + (contentW - 36 - 12) / 2 + 12, 194, (contentW - 36 - 12) / 2, 44);

        // default selection: Approve (feel free)
        approve.setSelected(true);
        needChanges.setSelected(false);

        approve.setOnClick(() -> {
            approve.setSelected(true);
            needChanges.setSelected(false);
            mainCard.repaint();
        });
        needChanges.setOnClick(() -> {
            approve.setSelected(false);
            needChanges.setSelected(true);
            mainCard.repaint();
        });

        mainCard.add(approve);
        mainCard.add(needChanges);

        // Section: Feedback Comment
        JLabel fbTitle = sectionLabel("FEEDBACK COMMENT");
        fbTitle.setBounds(18, 252, 250, 18);
        mainCard.add(fbTitle);

        RoundedTextArea feedback = new RoundedTextArea("Type your feedback here...");
        feedback.setBounds(18, 276, contentW - 36, 120);
        mainCard.add(feedback);

        // Submit Review button
        PrimaryButton submit = new PrimaryButton("Submit Review");
        submit.setBounds(18, 412, contentW - 36, 56);
        submit.addActionListener(e -> JOptionPane.showMessageDialog(this, "Submit Review (GUI only)"));
        mainCard.add(submit);

        // ===== Version History Card (bottom) =====
        RoundedCard history = new RoundedCard(26, new Color(250, 251, 255));
        history.setLayout(null);
        history.setBounds(P, 580, contentW, 240);
        bg.add(history);

        JLabel vhTitle = sectionLabel("VERSION HISTORY");
        vhTitle.setBounds(18, 16, 200, 18);
        history.add(vhTitle);

        HistoryItem v2 = new HistoryItem(
                "Version 2", "(Need Changes)",
                "Report_Draft_v2.pdf",
                "\"Architecture diagram needs more detail.\""
        );
        v2.setBounds(18, 44, contentW - 36, 86);
        history.add(v2);

        JSeparator sep = new JSeparator();
        sep.setForeground(new Color(235, 238, 245));
        sep.setBounds(18, 132, contentW - 36, 1);
        history.add(sep);

        HistoryItem v1 = new HistoryItem(
                "Version 1", "(Need Changes)",
                "Report_Draft_v1.pdf",
                "\"Format is incorrect. Please follow the template.\""
        );
        v1.setBounds(18, 144, contentW - 36, 86);
        history.add(v1);
    }

    // ================= helpers =================

    private static JLabel sectionLabel(String text) {
        JLabel lbl = new JLabel(text);
        lbl.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        lbl.setForeground(new Color(140, 146, 156));
        return lbl;
    }

    // ================= UI components =================

    static class BgPanel extends JPanel {
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
            g2.fillOval(-160, -90, 560, 360);

            g2.setColor(new Color(238, 244, 255));
            g2.fillOval(w - 420, 80, 560, 380);

            g2.setColor(new Color(233, 241, 255));
            g2.fillOval(-220, h - 520, 700, 620);

            g2.setColor(new Color(238, 244, 255));
            g2.fillOval(w - 520, h - 560, 740, 640);

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

            // shadow
            g2.setColor(new Color(0, 0, 0, 14));
            g2.fillRoundRect(2, 4, w - 4, h - 4, arc, arc);

            // fill
            g2.setColor(fill);
            g2.fillRoundRect(0, 0, w, h - 2, arc, arc);

            // border
            g2.setColor(new Color(230, 234, 242));
            g2.drawRoundRect(0, 0, w - 1, h - 3, arc, arc);

            g2.dispose();
            super.paintComponent(g);
        }
    }

    static class Pill extends JComponent {
        private final String text;
        private final Color bg;
        private final Color fg;

        Pill(String text, Color bg, Color fg) {
            this.text = text;
            this.bg = bg;
            this.fg = fg;
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            int w = getWidth(), h = getHeight();

            g2.setColor(bg);
            g2.fillRoundRect(0, 0, w, h, 22, 22);

            g2.setColor(fg);
            g2.setFont(new Font("Segoe UI", Font.BOLD, 12));
            FontMetrics fm = g2.getFontMetrics();
            g2.drawString(text, (w - fm.stringWidth(text)) / 2, (h + fm.getAscent() - fm.getDescent()) / 2);

            g2.dispose();
        }
    }

    static class DocIcon extends JComponent {
        private final Color iconColor;
        private final Color bg;

        DocIcon(Color iconColor, Color bg) {
            this.iconColor = iconColor;
            this.bg = bg;
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int w = getWidth(), h = getHeight();

            g2.setColor(bg);
            g2.fillRoundRect(0, 0, w, h, 14, 14);

            g2.setColor(iconColor);
            g2.setStroke(new BasicStroke(2f));

            int cx = w / 2;
            int cy = h / 2;

            // doc
            g2.drawRoundRect(cx - 9, cy - 12, 18, 24, 6, 6);
            g2.drawLine(cx - 5, cy - 4, cx + 5, cy - 4);
            g2.drawLine(cx - 5, cy + 1, cx + 5, cy + 1);

            g2.dispose();
        }
    }

    static class DownloadButton extends JButton {
        DownloadButton() {
            super("");
            setOpaque(false);
            setBorderPainted(false);
            setContentAreaFilled(false);
            setFocusPainted(false);
            setCursor(new Cursor(Cursor.HAND_CURSOR));
            addActionListener(e -> JOptionPane.showMessageDialog(this, "Download (GUI only)"));
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int s = Math.min(getWidth(), getHeight());

            g2.setColor(new Color(245, 247, 252));
            g2.fillOval(0, 0, s, s);

            g2.setColor(new Color(230, 234, 242));
            g2.setStroke(new BasicStroke(1.5f));
            g2.drawOval(0, 0, s - 1, s - 1);

            g2.setColor(new Color(120, 126, 138));
            g2.setStroke(new BasicStroke(2f));

            int cx = s / 2;
            int cy = s / 2;

            // arrow down
            g2.drawLine(cx, cy - 8, cx, cy + 6);
            g2.drawLine(cx - 5, cy + 1, cx, cy + 6);
            g2.drawLine(cx + 5, cy + 1, cx, cy + 6);

            // base
            g2.drawLine(cx - 8, cy + 10, cx + 8, cy + 10);

            g2.dispose();
            super.paintComponent(g);
        }
    }

    static class ChoicePill extends JComponent {
        enum Type { APPROVE, CHANGES }

        private boolean selected;
        private Runnable onClick;
        private final String text;
        private final Type type;

        ChoicePill(String text, Type type) {
            this.text = text;
            this.type = type;
            setOpaque(false);
            setCursor(new Cursor(Cursor.HAND_CURSOR));
            addMouseListener(new java.awt.event.MouseAdapter() {
                @Override public void mouseClicked(java.awt.event.MouseEvent e) {
                    if (onClick != null) onClick.run();
                }
            });
        }

        void setOnClick(Runnable r) { this.onClick = r; }
        void setSelected(boolean b) { this.selected = b; repaint(); }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int w = getWidth(), h = getHeight();
            int arc = 22;

            Color stroke = (type == Type.APPROVE) ? new Color(47, 95, 208) : new Color(212, 164, 60);
            Color fill = selected ? new Color(230, 239, 255) : Color.WHITE;

            if (type == Type.CHANGES && selected) fill = new Color(253, 242, 220);

            g2.setColor(new Color(0, 0, 0, 10));
            g2.fillRoundRect(2, 3, w - 4, h - 4, arc, arc);

            g2.setColor(fill);
            g2.fillRoundRect(0, 0, w, h - 2, arc, arc);

            g2.setColor(stroke);
            g2.setStroke(new BasicStroke(2f));
            g2.drawRoundRect(0, 0, w - 1, h - 3, arc, arc);

            // icon + text
            g2.setFont(new Font("Segoe UI", Font.BOLD, 13));
            g2.setColor(stroke);

            int iconX = 18;
            int cy = h / 2;

            g2.setStroke(new BasicStroke(2f));
            if (type == Type.APPROVE) {
                // check circle
                g2.drawOval(iconX, cy - 8, 16, 16);
                g2.drawLine(iconX + 4, cy, iconX + 7, cy + 3);
                g2.drawLine(iconX + 7, cy + 3, iconX + 12, cy - 3);
            } else {
                // exclamation circle
                g2.drawOval(iconX, cy - 8, 16, 16);
                g2.drawLine(iconX + 8, cy - 4, iconX + 8, cy + 3);
                g2.fillOval(iconX + 7, cy + 5, 3, 3);
            }

            FontMetrics fm = g2.getFontMetrics();
            int textX = iconX + 24;
            g2.drawString(text, textX, (h + fm.getAscent() - fm.getDescent()) / 2);

            g2.dispose();
        }
    }

    static class RoundedTextArea extends JComponent {
        private final JTextArea area;
        private final String placeholder;

        RoundedTextArea(String placeholder) {
            this.placeholder = placeholder;
            setOpaque(false);
            setLayout(null);

            area = new JTextArea();
            area.setFont(new Font("Segoe UI", Font.PLAIN, 13));
            area.setForeground(new Color(28, 31, 36));
            area.setLineWrap(true);
            area.setWrapStyleWord(true);
            area.setOpaque(false);
            area.setBorder(new EmptyBorder(14, 16, 14, 16));
            area.setText(placeholder);

            area.addFocusListener(new java.awt.event.FocusAdapter() {
                @Override public void focusGained(java.awt.event.FocusEvent e) {
                    if (placeholder.equals(area.getText())) area.setText("");
                }
                @Override public void focusLost(java.awt.event.FocusEvent e) {
                    if (area.getText().trim().isEmpty()) area.setText(placeholder);
                }
            });

            add(area);
        }

        @Override
        public void setBounds(int x, int y, int w, int h) {
            super.setBounds(x, y, w, h);
            area.setBounds(0, 0, w, h);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int w = getWidth(), h = getHeight();
            int arc = 18;

            g2.setColor(new Color(245, 247, 252));
            g2.fillRoundRect(0, 0, w, h, arc, arc);

            g2.setColor(new Color(210, 216, 228));
            g2.setStroke(new BasicStroke(2f));
            g2.drawRoundRect(0, 0, w - 1, h - 1, arc, arc);

            g2.dispose();
            super.paintComponent(g);
        }
    }

    static class PrimaryButton extends JButton {
        PrimaryButton(String text) {
            super(text);
            setOpaque(false);
            setBorderPainted(false);
            setContentAreaFilled(false);
            setFocusPainted(false);
            setCursor(new Cursor(Cursor.HAND_CURSOR));
            setForeground(Color.WHITE);
            setFont(new Font("Segoe UI", Font.BOLD, 15));
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int w = getWidth(), h = getHeight();
            int arc = 26;

            // shadow
            g2.setColor(new Color(0, 0, 0, 16));
            g2.fillRoundRect(2, 4, w - 4, h - 4, arc, arc);

            // fill
            g2.setColor(new Color(47, 95, 208));
            g2.fillRoundRect(0, 0, w, h - 2, arc, arc);

            // plane icon (simple)
            g2.setColor(Color.WHITE);
            g2.setStroke(new BasicStroke(2f));
            int cx = 48;
            int cy = h / 2;

            Polygon p = new Polygon();
            p.addPoint(cx - 10, cy);
            p.addPoint(cx + 10, cy - 10);
            p.addPoint(cx + 4, cy + 12);
            g2.drawPolygon(p);
            g2.drawLine(cx - 10, cy, cx + 4, cy + 2);

            g2.dispose();
            super.paintComponent(g);
        }

        @Override
        public boolean contains(int x, int y) {
            Shape s = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 26, 26);
            return s.contains(x, y);
        }
    }

    static class HistoryItem extends JComponent {
        private final String vTitle;
        private final String status;
        private final String file;
        private final String note;

        HistoryItem(String vTitle, String status, String file, String note) {
            this.vTitle = vTitle;
            this.status = status;
            this.file = file;
            this.note = note;
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int w = getWidth();

            // small warning icon
            g2.setColor(new Color(253, 242, 220));
            g2.fillOval(0, 8, 26, 26);

            g2.setColor(new Color(212, 164, 60));
            g2.setStroke(new BasicStroke(2f));
            g2.drawOval(0, 8, 26, 26);
            g2.drawLine(13, 15, 13, 25);
            g2.fillOval(12, 28, 3, 3);

            int x = 38;

            g2.setColor(new Color(28, 31, 36));
            g2.setFont(new Font("Segoe UI", Font.BOLD, 13));
            g2.drawString(vTitle, x, 24);

            g2.setColor(new Color(120, 126, 138));
            g2.setFont(new Font("Segoe UI", Font.PLAIN, 12));
            g2.drawString(status, x + 80, 24);

            g2.setColor(new Color(140, 146, 156));
            g2.setFont(new Font("Segoe UI", Font.PLAIN, 11));
            g2.drawString(file, x, 42);

            // note bubble
            int bx = x;
            int by = 52;
            int bw = w - x;
            int bh = 30;

            g2.setColor(new Color(245, 247, 252));
            g2.fillRoundRect(bx, by, bw, bh, 16, 16);

            g2.setColor(new Color(160, 166, 176));
            g2.setFont(new Font("Segoe UI", Font.ITALIC, 11));
            g2.drawString(note, bx + 12, by + 20);

            g2.dispose();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new SupervisorReviewSubmissions().setVisible(true));
    }
}