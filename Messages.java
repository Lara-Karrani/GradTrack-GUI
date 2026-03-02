
package com.mycompany.gradtrack_system;



import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class Messages extends JFrame {

    public Messages() {
        setTitle("GRADTRACK - Messages");
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
        int contentW = W - (P * 2);

        // ===== Top Title =====
        JLabel title = new JLabel("MESSAGES", SwingConstants.CENTER);
        title.setFont(new Font("Segoe UI", Font.BOLD, 18));
        title.setForeground(new Color(28, 31, 36));
        title.setBounds(0, 40, W, 24);
        bg.add(title);

        // ===== Tabs row (avatars) =====
        AvatarTab tab1 = new AvatarTab("Dr. Samaher", true, true);
        tab1.setBounds(P, 90, 150, 98);          // ✅ أعلى شوي + أكبر شوي
        bg.add(tab1);

        AvatarTab tab2 = new AvatarTab("Group Chat", false, false);
        tab2.setBounds(P + 160, 90, 150, 98);    // ✅ توازن
        bg.add(tab2);

        // ===== Chat Card =====
        RoundedCard chatCard = new RoundedCard(24);
        chatCard.setLayout(null);
        chatCard.setBounds(P, 200, contentW, 470);  // ✅ أعلى شوي + أطول
        bg.add(chatCard);

        // message 1 (left)
        Bubble left1 = new Bubble(
                "Hello Lara, please make sure to\nsubmit Draft 1 by Thursday.",
                "10:42 AM",
                BubbleSide.LEFT
        );
        left1.setBounds(18, 24, contentW - 36, 118);
        chatCard.add(left1);

        // message 2 (right)
        Bubble right1 = new Bubble(
                "Sure Dr. Samaher, I am finalizing the\narchitecture diagram now.",
                "11:15 AM",
                BubbleSide.RIGHT
        );
        right1.setBounds(18, 152, contentW - 36, 128);
        chatCard.add(right1);

        // message 3 (left)
        Bubble left2 = new Bubble(
                "Great, looking forward to it.",
                "11:30 AM",
                BubbleSide.LEFT
        );
        left2.setBounds(18, 294, contentW - 36, 100);
        chatCard.add(left2);

        // ===== Input bar =====
        InputBar input = new InputBar();
        input.setBounds(P, 690, contentW, 66); // ✅ رفعناه فوق
        bg.add(input);

        // GUI-only (toggle tabs)
        tab1.setOnClick(() -> { tab1.setSelected(true); tab2.setSelected(false); bg.repaint(); });
        tab2.setOnClick(() -> { tab1.setSelected(false); tab2.setSelected(true); bg.repaint(); });
    }

    // ================== UI Parts ==================

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

            g2.setColor(new Color(250, 251, 255));
            g2.fillRoundRect(0, 0, w, h - 2, arc, arc);

            g2.setColor(new Color(230, 234, 242));
            g2.drawRoundRect(0, 0, w - 1, h - 3, arc, arc);

            g2.dispose();
            super.paintComponent(g);
        }
    }

    static class AvatarTab extends JComponent {
        private final String label;
        private boolean selected;
        private final boolean online;
        private Runnable onClick;

        AvatarTab(String label, boolean selected, boolean online) {
            this.label = label;
            this.selected = selected;
            this.online = online;
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

            int w = getWidth();

            // ✅ Center avatar horizontally
            int s = 58;
            int cx = (w - s) / 2;
            int cy = 10;

            // selected ring
            if (selected) {
                g2.setColor(new Color(47, 95, 208));
                g2.setStroke(new BasicStroke(3f));
                g2.drawOval(cx - 3, cy - 3, s + 6, s + 6);
            }

            // avatar bg
            g2.setColor(new Color(240, 243, 250));
            g2.fillOval(cx, cy, s, s);

            // person icon
            g2.setStroke(new BasicStroke(2f));
            g2.setColor(new Color(150, 156, 168));
            int icx = cx + s / 2;
            int icy = cy + s / 2;
            g2.drawOval(icx - 8, icy - 10, 16, 16);
            g2.drawArc(icx - 14, icy + 2, 28, 18, 0, 180);

            // online dot
            if (online) {
                g2.setColor(new Color(68, 168, 120));
                g2.fillOval(cx + s - 10, cy + s - 6, 10, 10);
                g2.setColor(Color.WHITE);
                g2.setStroke(new BasicStroke(2f));
                g2.drawOval(cx + s - 10, cy + s - 6, 10, 10);
            }

            // ✅ label UNDER the circle (not overlapping)
            g2.setFont(new Font("Segoe UI", Font.PLAIN, 13));
            g2.setColor(selected ? new Color(28, 31, 36) : new Color(150, 156, 168));
            FontMetrics fm = g2.getFontMetrics();
            int tx = (w - fm.stringWidth(label)) / 2;
            g2.drawString(label, tx, 88);

            g2.dispose();
        }
    }

    enum BubbleSide { LEFT, RIGHT }

    static class Bubble extends JComponent {
        private final String text;
        private final String time;
        private final BubbleSide side;

        Bubble(String text, String time, BubbleSide side) {
            this.text = text;
            this.time = time;
            this.side = side;
            setOpaque(false);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int w = getWidth();
            int h = getHeight();

            int bubbleW = (int) (w * 0.78);
            int bubbleH = h - 30;

            int x = (side == BubbleSide.LEFT) ? 0 : (w - bubbleW);
            int y = 0;

            int arc = 18;

            Color bubbleColor = (side == BubbleSide.LEFT)
                    ? new Color(236, 239, 246)
                    : new Color(47, 95, 208);

            Color textColor = (side == BubbleSide.LEFT)
                    ? new Color(28, 31, 36)
                    : Color.WHITE;

            // shadow
            g2.setColor(new Color(0, 0, 0, 10));
            g2.fillRoundRect(x + 2, y + 3, bubbleW - 4, bubbleH - 2, arc, arc);

            // bubble
            g2.setColor(bubbleColor);
            g2.fillRoundRect(x, y, bubbleW, bubbleH, arc, arc);

            // text
            g2.setColor(textColor);
            g2.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            drawMultiline(g2, text, x + 16, y + 26, bubbleW - 32);

            // time
            g2.setColor(new Color(160, 166, 176));
            g2.setFont(new Font("Segoe UI", Font.PLAIN, 11));
            int ty = h - 10;

            if (side == BubbleSide.LEFT) {
                g2.drawString(time, x + 6, ty);
            } else {
                FontMetrics fm = g2.getFontMetrics();
                g2.drawString(time, x + bubbleW - fm.stringWidth(time) - 6, ty);
            }

            g2.dispose();
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
                        } else {
                            cur = new StringBuilder(test);
                        }
                    }
                    if (cur.length() > 0) {
                        g2.drawString(cur.toString(), x, yy);
                        yy += lineH;
                    }
                }
            }
        }
    }

    static class InputBar extends JComponent {
        private final JTextField field;
        private final SendButton send;

        InputBar() {
            setOpaque(false);
            setLayout(null);

            field = new JTextField();
            field.setBorder(new EmptyBorder(0, 16, 0, 16));
            field.setFont(new Font("Segoe UI", Font.PLAIN, 14));
            field.setForeground(new Color(28, 31, 36));
            field.setOpaque(false);
            field.setText("Type a message...");

            send = new SendButton();

            add(field);
            add(send);

            field.addFocusListener(new java.awt.event.FocusAdapter() {
                @Override public void focusGained(java.awt.event.FocusEvent e) {
                    if ("Type a message...".equals(field.getText())) field.setText("");
                }
                @Override public void focusLost(java.awt.event.FocusEvent e) {
                    if (field.getText().trim().isEmpty()) field.setText("Type a message...");
                }
            });

            send.addActionListener(e -> JOptionPane.showMessageDialog(this, "Send (GUI only)"));
        }

        @Override
        public void setBounds(int x, int y, int w, int h) {
            super.setBounds(x, y, w, h);
            field.setBounds(12, 6, w - 12 - 54, h - 12);
            send.setBounds(w - 52, 8, 44, 44);
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            int w = getWidth(), h = getHeight();
            int arc = 24;

            g2.setColor(new Color(0, 0, 0, 12));
            g2.fillRoundRect(2, 3, w - 4, h - 4, arc, arc);

            g2.setColor(Color.WHITE);
            g2.fillRoundRect(0, 0, w, h - 2, arc, arc);

            g2.setColor(new Color(230, 234, 242));
            g2.drawRoundRect(0, 0, w - 1, h - 3, arc, arc);

            g2.dispose();
            super.paintComponent(g);
        }
    }

    static class SendButton extends JButton {
        SendButton() {
            super("");
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

            g2.setColor(new Color(0, 0, 0, 18));
            g2.fillOval(2, 3, s - 4, s - 4);

            g2.setColor(new Color(47, 95, 208));
            g2.fillOval(0, 0, s, s);

            g2.setColor(Color.WHITE);
            g2.setStroke(new BasicStroke(2f));
            int cx = s / 2;
            int cy = s / 2;

            Polygon p = new Polygon();
            p.addPoint(cx - 8, cy - 2);
            p.addPoint(cx + 10, cy - 10);
            p.addPoint(cx + 4, cy + 12);
            g2.drawPolygon(p);
            g2.drawLine(cx - 8, cy - 2, cx + 4, cy + 2);

            g2.dispose();
            super.paintComponent(g);
        }

        @Override
        public boolean contains(int x, int y) {
            int s = Math.min(getWidth(), getHeight());
            Shape circle = new java.awt.geom.Ellipse2D.Float(0, 0, s, s);
            return circle.contains(x, y);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Messages().setVisible(true));
    }
}