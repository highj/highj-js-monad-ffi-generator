package org.highj.js_monad_ffi_generator;

import org.highj.data.List;
import org.highj.data.stateful.Effect0;
import org.highj.data.stateful.Effect1;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.net.URI;
import java.util.Arrays;

public class FFIGeneratorPanel extends JPanel {
    private JFileChooser jFileChooser = new JFileChooser();
    private Effect1<List<URI>> addUrisEffect;
    private Effect0 removeSelectedEffect;

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
        webIDLSelected();
        jFileChooser.setMultiSelectionEnabled(true);
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
            radWebIDL.addActionListener(e -> webIDLSelected());
            radJQueryXML.addActionListener(e -> jqueryXMLSelected());
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
            DefaultListModel<URI> sourcesModel = new DefaultListModel<>();
            JList<URI> lstSources = new JList<>(sourcesModel);
            {
                GridBagConstraints c = new GridBagConstraints();
                c.gridx = 0;
                c.gridy = 3;
                c.weightx = 1.0;
                c.weighty = 1.0;
                c.fill = GridBagConstraints.BOTH;
                add(new JScrollPane(lstSources), c);
            }
            addUrisEffect = (List<URI> uris) -> {
                for (URI uri : uris) {
                    sourcesModel.addElement(uri);
                }
            };
            removeSelectedEffect = () -> {
                int[] indices = lstSources.getSelectedIndices();
                Arrays.sort(indices);
                for (int i = indices.length-1; i >= 0; --i) {
                    sourcesModel.remove(indices[i]);
                }
            };
        }
        {
            JPanel jPanel = new JPanel(new FlowLayout());
            JButton btnAddFiles = new JButton("Add Files...");
            JButton btnAddURL = new JButton("Add URL...");
            JButton btnRemoveSelected = new JButton("Remove Selected");
            btnAddFiles.addActionListener(e -> addFilesClicked());
            btnRemoveSelected.addActionListener(e -> removeSelectedClicked());
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

    private static final FileNameExtensionFilter idlFileFilter = new FileNameExtensionFilter("IDL Files (*.idl)", "idl");
    private static final FileNameExtensionFilter xmlFileFilter = new FileNameExtensionFilter("XML Files (*.xml)", "xml");

    private void webIDLSelected() {
        jFileChooser.resetChoosableFileFilters();
        jFileChooser.setFileFilter(idlFileFilter);
    }

    private void jqueryXMLSelected() {
        jFileChooser.resetChoosableFileFilters();
        jFileChooser.setFileFilter(xmlFileFilter);
    }

    private void addFilesClicked() {
        if (jFileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File[] files = jFileChooser.getSelectedFiles();
            addUrisEffect.run(List.of(files).map(File::toURI));
        }
    }

    private void removeSelectedClicked() {
        removeSelectedEffect.run();
    }
}
