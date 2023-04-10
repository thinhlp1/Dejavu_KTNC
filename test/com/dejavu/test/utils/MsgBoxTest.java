package com.dejavu.test.utils;

import com.bar.util.MsgBox;
import org.junit.Test;
import static org.junit.Assert.*;

import javax.swing.JFrame;

public class MsgBoxTest {

    @Test
    public void testAlert() {
        JFrame frame = new JFrame();
        String message = "This is an alert message";
        MsgBox.alert(frame, message);
        // The test will pass if the alert dialog is displayed without errors
    }

    @Test
    public void testConfirm() {
        JFrame frame = new JFrame();
        String message = "Do you want to continue?";
        boolean result = MsgBox.confirm(frame, message);
        assertTrue(result);
        // The test will pass if the "Yes" button is clicked in the confirm dialog
    }

    @Test
    public void testPrompt() {
        JFrame frame = new JFrame();
        String message = "Enter your name:";
        String result = MsgBox.prompt(frame, message);
        assertNotNull(result);
        // The test will pass if the prompt dialog is displayed and a non-null string is returned
    }
}
