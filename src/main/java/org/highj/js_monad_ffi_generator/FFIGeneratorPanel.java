package org.highj.js_monad_ffi_generator;

import javax.swing.*;
import java.awt.*;
import java.net.URI;

public class FFIGeneratorPanel extends JPanel {

    public FFIGeneratorPanel() {
        init();
    }

    private void init() {
        // Generate FFI from:
        // (*) Web IDL   ( ) jQuery XML Documentation
        // Source:
        // |```````````````````````````````````````````|
        // |                                           |
        // |                                           |
        // |___________________________________________|
        //  ___________   _________   _________________
        // | Add Files | | Add URL | | Remove Selected |
        //  ```````````   `````````   `````````````````
        // ---------------------------------------------
        //  Target Folder:
        //   __________________________________________
        //  |                                          |
        //   ``````````````````````````````````````````
        //   ___________
        //  | Browse... |
        //   ```````````
        // ---------------------------------------------
        //  ______________
        // | Generate FFI |
        //  ``````````````
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        {
            JLabel jLabel = new JLabel("Generate FFI from:");
            {
                GridBagConstraints c = new GridBagConstraints();
                c.gridx = 0;
                c.gridy = 0;
                c.weightx = 1.0;
                c.gridwidth = 2;
                c.anchor = GridBagConstraints.WEST;
                add(jLabel, c);
            }
        }
        {
            JPanel jPanel = new JPanel(new FlowLayout());
            JRadioButton radWebIDL = new JRadioButton("Web IDL", true);
            JRadioButton radJQueryXML = new JRadioButton("jQuery XML Documentation");
            ButtonGroup buttonGroup = new ButtonGroup();
            buttonGroup.add(radWebIDL);
            buttonGroup.add(radJQueryXML);
            jPanel.add(radWebIDL);
            jPanel.add(radJQueryXML);
            {
                GridBagConstraints c = new GridBagConstraints();
                c.gridx = 0;
                c.gridy = 1;
                c.anchor = GridBagConstraints.WEST;
                add(jPanel, c);
            }
        }
        {
            JLabel jLabel = new JLabel("Sources:");
            {
                GridBagConstraints c = new GridBagConstraints();
                c.gridx = 0;
                c.gridy = 2;
                c.anchor = GridBagConstraints.WEST;
                add(jLabel, c);
            }
        }
        {
            JList<URI> lstSources = new JList<>();
            {
                GridBagConstraints c = new GridBagConstraints();
                c.gridx = 0;
                c.gridy = 3;
                c.weightx = 1.0;
                c.weighty = 1.0;
                c.fill = GridBagConstraints.BOTH;
                add(new JScrollPane(lstSources), c);
            }
        }
        {
            JPanel jPanel = new JPanel(new FlowLayout());
            JButton btnAddFiles = new JButton("Add Files...");
            JButton btnAddURL = new JButton("Add URL...");
            JButton btnRemoveSelected = new JButton("Remove Selected");
            jPanel.add(btnAddFiles);
            jPanel.add(btnAddURL);
            jPanel.add(btnRemoveSelected);
            {
                GridBagConstraints c = new GridBagConstraints();
                c.gridx = 0;
                c.gridy = 4;
                c.anchor = GridBagConstraints.WEST;
                add(jPanel, c);
            }
        }
        {
            GridBagConstraints c = new GridBagConstraints();
            c.gridx = 0;
            c.gridy = 5;
            c.fill = GridBagConstraints.HORIZONTAL;
            add(new JSeparator(JSeparator.HORIZONTAL), c);
        }
        {
            JLabel jLabel = new JLabel("Target Folder:");
            {
                GridBagConstraints c = new GridBagConstraints();
                c.gridx = 0;
                c.gridy = 6;
                c.anchor = GridBagConstraints.WEST;
                add(jLabel, c);
            }
        }
        {
            JTextField txtTargetFolder = new JTextField();
            txtTargetFolder.setEditable(false);
            {
                GridBagConstraints c = new GridBagConstraints();
                c.gridx = 0;
                c.gridy = 7;
                c.fill = GridBagConstraints.HORIZONTAL;
                add(txtTargetFolder, c);
            }
        }
        {
            JButton btnTargetFolder = new JButton("Browse...");
            {
                JPanel jPanel = new JPanel(new FlowLayout());
                jPanel.add(btnTargetFolder);
                GridBagConstraints c = new GridBagConstraints();
                c.gridx = 0;
                c.gridy = 8;
                c.anchor = GridBagConstraints.WEST;
                add(jPanel, c);
            }
        }
        {
            GridBagConstraints c = new GridBagConstraints();
            c.gridx = 0;
            c.gridy = 9;
            c.fill = GridBagConstraints.HORIZONTAL;
            add(new JSeparator(JSeparator.HORIZONTAL), c);
        }
        {
            JButton btnGenerateFFI = new JButton("Generate FFI");
            {
                JPanel jPanel = new JPanel(new FlowLayout());
                jPanel.add(btnGenerateFFI);
                GridBagConstraints c = new GridBagConstraints();
                c.gridx = 0;
                c.gridy = 10;
                c.anchor = GridBagConstraints.WEST;
                add(jPanel, c);
            }
        }
    }
}
