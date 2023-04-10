package com.dejavu.test.utils;

import com.bar.util.XImage;
import org.junit.Test;
import java.io.File;
import javax.swing.ImageIcon;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 *
 * @author PC
 */
public class XImageTest {

    @Test
    public void testSave() {
        File src = new File("test.png");
        XImage.save(src);
        File dst = new File("logos/test.png");
        assertTrue(dst.exists());
        dst.delete();
    }

    @Test
    public void testRead() {
        File src = new File("test.png");
        XImage.save(src);
        ImageIcon icon = XImage.read("test.png");
        assertNotNull(icon);
        assertEquals(100, icon.getIconWidth());
        assertEquals(50, icon.getIconHeight());
        File dst = new File("logos/test.png");
        dst.delete();
    }

}
