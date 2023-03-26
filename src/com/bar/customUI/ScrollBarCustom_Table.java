package com.bar.customUI;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JScrollBar;

public class ScrollBarCustom_Table extends JScrollBar {

    public ScrollBarCustom_Table() {
        setUI(new ModernScrollBarUI_Table());
        setPreferredSize(new Dimension(8, 8));
        setForeground(new Color(48, 144, 216));
        setBackground(new Color(255, 255, 255));
    }
}
