package org.highj.js_monad_ffi_generator;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        SwingUtilities.invokeLater(() -> {
            FFIGeneratorPanel panel = new FFIGeneratorPanel();
            panel.setGenerateFFIEffect(() -> {
                for (File targetFolder : panel.getTargetFolder()) {
                    try {
                        FFIGenerator.generateFFI(panel.getSourceUris(), targetFolder).run();
                    } catch (IOException e) {
                        JOptionPane.showMessageDialog(panel, "An IO problem occured: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                        e.printStackTrace();
                    }
                }
            });
            JFrame jFrame = new JFrame("JS Monad FFI Generator");
            jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            jFrame.setLayout(new BorderLayout());
            jFrame.add(new FFIGeneratorPanel(), BorderLayout.CENTER);
            jFrame.pack();
            jFrame.setLocationRelativeTo(null);
            jFrame.setVisible(true);
        });
    }
}
