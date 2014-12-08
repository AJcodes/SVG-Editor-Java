package view;

import controller.ClosableTabbedPane;
import controller.MainCanvas;
import controller.SVGReader;
import controller.SVGWriter;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JFileChooser;
import java.awt.*;

 
import javax.swing.BoxLayout;
import javax.swing.ComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JViewport;
import javax.swing.KeyStroke;

import java.io.File;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;



import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

import javax.swing.border.EmptyBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.JTabbedPane;
import javax.swing.border.EtchedBorder;

import model.Group;
import model.Rect;
import model.Shape;
import javax.swing.JSeparator;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.factories.FormFactory;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.JSlider;

public class MainWindow {

	private JFrame frame;
	private JColorChooser colorchooser;
	private DocDialog properties;
	private JFileChooser chooser;
	private JDialog colordialog;
	private JLabel lblDimensions;
	private ClosableTabbedPane tabbedPane;
	private Color newColor;
	private static int index;
	private static int sel;
	private static int index2;
	private String[] values = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
	MainCanvas canvas;
	private ArrayList<MainCanvas> canvast;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		index = 0;
		index2 = 1;
		canvast = new ArrayList<MainCanvas>();
		frame = new JFrame();
		colorchooser = new JColorChooser();
		properties = new DocDialog();
		tabbedPane = new ClosableTabbedPane();
		tabbedPane.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		colordialog = new JDialog();
		frame.setBounds(100, 100, 800, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.WEST);
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(0,0,200,100);
		panel.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		
						GridBagLayout gbl_panel = new GridBagLayout();
						gbl_panel.columnWidths = new int[]{81, 0};
						gbl_panel.rowHeights = new int[]{23, 33, 23, 23, 23, 23, 23, 0, 0, 0, 0};
						gbl_panel.columnWeights = new double[]{1.0, Double.MIN_VALUE};
						gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
						panel.setLayout(gbl_panel);
						
								
								final JButton btnNewButton_2 = new JButton("Line", new ImageIcon("Icons/line-icon.gif"));
								
								
										
										final JButton btnRotate = new JButton("Rotate", new ImageIcon("Icons/rotate.png"));
//										btnNewButton_1.addActionListener(new ActionListener() {
//											public void actionPerformed(ActionEvent arg0) {
//												
//											}
//										});
										

										final JButton btnNewButton_0 = new JButton("Select", new ImageIcon("Icons/cursor.png"));
										GridBagConstraints gbc_btnNewButton_0 = new GridBagConstraints();
										gbc_btnNewButton_0.insets = new Insets(0, 0, 5, 0);
										gbc_btnNewButton_0.fill = GridBagConstraints.BOTH;
										gbc_btnNewButton_0.gridx = 0;
										gbc_btnNewButton_0.gridy = 1;
										panel.add(btnNewButton_0, gbc_btnNewButton_0);
										GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
										gbc_btnNewButton_1.fill = GridBagConstraints.BOTH;
										gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 0);
										gbc_btnNewButton_1.gridx = 0;
										gbc_btnNewButton_1.gridy = 2;
										panel.add(btnRotate, gbc_btnNewButton_1);
								GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
								gbc_btnNewButton_2.fill = GridBagConstraints.BOTH;
								gbc_btnNewButton_2.insets = new Insets(0, 0, 5, 0);
								gbc_btnNewButton_2.gridx = 0;
								gbc_btnNewButton_2.gridy = 3;
								panel.add(btnNewButton_2, gbc_btnNewButton_2);
						
								
								final JButton btnRectangle = new JButton("Rectangle", new ImageIcon("Icons/rectangle_icon.png"));
								GridBagConstraints gbc_btnRectangle = new GridBagConstraints();
								gbc_btnRectangle.fill = GridBagConstraints.BOTH;
								gbc_btnRectangle.insets = new Insets(0, 0, 5, 0);
								gbc_btnRectangle.gridx = 0;
								gbc_btnRectangle.gridy = 4;
								panel.add(btnRectangle, gbc_btnRectangle);
						final JButton btnCircle = new JButton("Oval", new ImageIcon("Icons/circle_icon.jpg"));
						GridBagConstraints gbc_btnCircle = new GridBagConstraints();
						gbc_btnCircle.fill = GridBagConstraints.BOTH;
						gbc_btnCircle.insets = new Insets(0, 0, 5, 0);
						gbc_btnCircle.gridx = 0;
						gbc_btnCircle.gridy = 5;
						panel.add(btnCircle, gbc_btnCircle);
		
		
		frame.getContentPane().add(tabbedPane, BorderLayout.CENTER);
		
		tabbedPane.setBackground(Color.GRAY);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1.setBackground(Color.LIGHT_GRAY);
		frame.getContentPane().add(panel_1, BorderLayout.EAST);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{92, 0};
		gbl_panel_1.rowHeights = new int[]{36, 0, 0, 23, 35, 9, 9, 0, 109, 18, 20, 0};
		gbl_panel_1.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JButton btnDelete = new JButton("Delete", new ImageIcon("Icons/delete-icon.png"));
		GridBagConstraints gbc_btnDelete = new GridBagConstraints();
		gbc_btnDelete.fill = GridBagConstraints.BOTH;
		gbc_btnDelete.insets = new Insets(0, 0, 5, 0);
		gbc_btnDelete.gridx = 0;
		gbc_btnDelete.gridy = 1;
		panel_1.add(btnDelete, gbc_btnDelete);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JScrollPane test = (JScrollPane) tabbedPane.getComponentAt(sel);
		        JViewport test2 = test.getViewport();
		        JPanel test3 = (JPanel) test2.getView();
		        MainCanvas test4 = (MainCanvas) test3.getComponent(0);
		        for (int i = test4.shapes.size() - 1; i >= 0; i--){
		        	Shape s = (Shape)test4.shapes.get(i);
		        	if (s.isSelected == true){
		        		test4.shapes.remove(s);
		        	}
		        }
		        if (test4.selGroup != null){
		        	test4.Groups.remove(test4.selGroup);
		        }
		        test4.repaint();
		    }
				
			});
		btnDelete.setMnemonic(KeyEvent.VK_DELETE);
		
		
		final JButton button = new JButton("", new ImageIcon("Icons/colorpick.jpg"));
		GridBagConstraints gbc_button = new GridBagConstraints();
		gbc_button.fill = GridBagConstraints.BOTH;
		gbc_button.insets = new Insets(0, 0, 5, 0);
		gbc_button.gridx = 0;
		gbc_button.gridy = 2;
		panel_1.add(button, gbc_button);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				colordialog.getContentPane().add(colorchooser);
				colordialog.setBounds(0, 0, 500, 300);
				colordialog.setVisible(true);
				
				
			}
		});
		
		JButton btnApplyToFill = new JButton("", new ImageIcon("Icons/fill.png"));
		GridBagConstraints gbc_btnApplyToFill = new GridBagConstraints();
		gbc_btnApplyToFill.fill = GridBagConstraints.BOTH;
		gbc_btnApplyToFill.insets = new Insets(0, 0, 5, 0);
		gbc_btnApplyToFill.gridx = 0;
		gbc_btnApplyToFill.gridy = 3;
		panel_1.add(btnApplyToFill, gbc_btnApplyToFill);
		btnApplyToFill.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JScrollPane test = (JScrollPane) tabbedPane.getComponentAt(sel);
		        JViewport test2 = test.getViewport();
		        JPanel test3 = (JPanel) test2.getView();
		        MainCanvas test4 = (MainCanvas) test3.getComponent(0);
//						        test4.clickedShape.setColor(newColor);
		        for (int i = test4.shapes.size() - 1; i >= 0; i--){
		        	Shape s = (Shape)test4.shapes.get(i);
		        	if (s.isSelected == true){
		        		s.setColor(newColor);
		        	}
		        }
		        if (!test4.selGroup.isEmpty()){
		        	test4.selGroup.setColor(newColor);
		        }
		        test4.repaint();
				
				
			}
		});
		
		JButton btnApplyToStroke = new JButton("", new ImageIcon("Icons/paintbrush.jpg"));
		GridBagConstraints gbc_btnApplyToStroke = new GridBagConstraints();
		gbc_btnApplyToStroke.fill = GridBagConstraints.BOTH;
		gbc_btnApplyToStroke.insets = new Insets(0, 0, 5, 0);
		gbc_btnApplyToStroke.gridx = 0;
		gbc_btnApplyToStroke.gridy = 4;
		panel_1.add(btnApplyToStroke, gbc_btnApplyToStroke);
		btnApplyToStroke.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JScrollPane test = (JScrollPane) tabbedPane.getComponentAt(sel);
		        JViewport test2 = test.getViewport();
		        JPanel test3 = (JPanel) test2.getView();
		        MainCanvas test4 = (MainCanvas) test3.getComponent(0);
//						        test4.clickedShape.setStroke(newColor);
		        for (int i = test4.shapes.size() - 1; i >= 0; i--){
		        	Shape s = (Shape)test4.shapes.get(i);
		        	if (s.isSelected == true){
		        		s.setStroke(newColor);
		        	}
		        }
		        if (!test4.selGroup.isEmpty()){
		        	test4.selGroup.setStroke(newColor);
		        }
		        test4.repaint();
				
				
			}
		});
		
		JLabel lblStrokeWidth = new JLabel("Stroke Width: ");
		lblStrokeWidth.setHorizontalAlignment(SwingConstants.CENTER);
		lblStrokeWidth.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		GridBagConstraints gbc_lblStrokeWidth = new GridBagConstraints();
		gbc_lblStrokeWidth.insets = new Insets(0, 0, 5, 0);
		gbc_lblStrokeWidth.gridx = 0;
		gbc_lblStrokeWidth.gridy = 6;
		panel_1.add(lblStrokeWidth, gbc_lblStrokeWidth);
		
		JComboBox comboBox = new JComboBox(values);
		comboBox.setEditable(true);
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox.fill = GridBagConstraints.BOTH;
		gbc_comboBox.gridx = 0;
		gbc_comboBox.gridy = 7;
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JScrollPane test = (JScrollPane) tabbedPane.getComponentAt(sel);
		        JViewport test2 = test.getViewport();
		        JPanel test3 = (JPanel) test2.getView();
		        MainCanvas test4 = (MainCanvas) test3.getComponent(0);
				JComboBox cb = (JComboBox)e.getSource();
		        String newSelection = (String)cb.getSelectedItem();
		        if (isInteger(newSelection)){
			        for (int i = test4.shapes.size() - 1; i >= 0; i--){
			        	Shape s = (Shape)test4.shapes.get(i);
			        	if (s.isSelected == true){
			        		s.setStrokeWidth(Float.parseFloat(newSelection));
			        	}
			        }
			        if (!test4.selGroup.isEmpty()){
			        	test4.selGroup.setStroke(newColor);
			        }
			        test4.repaint();
		        }
				
			}
		});
		panel_1.add(comboBox, gbc_comboBox);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new SoftBevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_2.setBackground(Color.LIGHT_GRAY);
		frame.getContentPane().add(panel_2, BorderLayout.SOUTH);
		panel_2.setLayout(new BorderLayout(0, 0));
		
		lblDimensions = new JLabel("Dimensions: ");
		lblDimensions.setHorizontalAlignment(SwingConstants.CENTER);
		lblDimensions.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		panel_2.add(lblDimensions, BorderLayout.WEST);
		
		final JSlider slider = new JSlider(JSlider.HORIZONTAL, 0, 200, 100);
		slider.setMajorTickSpacing(100);
		slider.setMinorTickSpacing(1);
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		panel_2.add(slider);
		
		colorchooser.getSelectionModel().addChangeListener(new ChangeListener(){
			public void stateChanged(ChangeEvent e) {
		        newColor = colorchooser.getColor();
		        button.setBackground(newColor);
		        
		    }
		});
		
		final JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		final JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmNew = new JMenuItem("New");
		mnFile.add(mntmNew);
		
		
		
		JMenuItem mntmOpen = new JMenuItem("Open");
		mntmOpen.setHorizontalAlignment(SwingConstants.LEFT);
		mnFile.add(mntmOpen);
		
		
		JMenuItem mntmSave = new JMenuItem("Save");
		mntmSave.setHorizontalAlignment(SwingConstants.LEFT);
		mnFile.add(mntmSave);
		mntmSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JScrollPane test = (JScrollPane) tabbedPane.getComponentAt(sel);
		        JViewport test2 = test.getViewport();
		        JPanel test3 = (JPanel) test2.getView();
		        MainCanvas test4 = (MainCanvas) test3.getComponent(0);
		        if (!test4.path.equals("")){
	        	SVGWriter testwrite = new SVGWriter(test4.path, test4.width, test4.height, test4.shapes, test4.Groups);
		        }
			}
		});
		KeyStroke ctrlS = KeyStroke.getKeyStroke(KeyEvent.VK_S,InputEvent.CTRL_MASK);
		mntmSave.setAccelerator(ctrlS);
		
		JMenuItem mntmSaveAs = new JMenuItem("Save As");
		mnFile.add(mntmSaveAs);
		mntmSaveAs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JScrollPane test = (JScrollPane) tabbedPane.getComponentAt(sel);
		        JViewport test2 = test.getViewport();
		        JPanel test3 = (JPanel) test2.getView();
		        MainCanvas test4 = (MainCanvas) test3.getComponent(0);
		        chooser = new JFileChooser();
				chooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
			        public boolean accept(File f) {
			          return f.getName().toLowerCase().endsWith(".svg")
			              || f.isDirectory();
			        }

			        public String getDescription() {
			          return "SVG Images";
			        }
			      });
				int returnVal = chooser.showSaveDialog(frame);

		        if (returnVal == JFileChooser.APPROVE_OPTION) {
		        	File f = chooser.getSelectedFile();
		        	String path = f.getAbsolutePath();
		        	SVGWriter testwrite = new SVGWriter(path, test4.width, test4.height, test4.shapes, test4.Groups);
		        }
		        
				
				
			}
		});
		KeyStroke ctrlsS = KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_DOWN_MASK | InputEvent.SHIFT_DOWN_MASK, false);
		mntmSaveAs.setAccelerator(ctrlsS);
		
		JSeparator separator = new JSeparator();
		mnFile.add(separator);
		
		JMenuItem mntmDocumentProperties = new JMenuItem("Document Properties");
		mnFile.add(mntmDocumentProperties);
		mntmDocumentProperties.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				properties.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				properties.setVisible(true);
				
				
			}
		});
		KeyStroke ctrlD = KeyStroke.getKeyStroke(KeyEvent.VK_D,InputEvent.CTRL_MASK);
		mntmDocumentProperties.setAccelerator(ctrlD);
		
		JSeparator separator_1 = new JSeparator();
		mnFile.add(separator_1);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mnFile.add(mntmExit);
		
		final JMenu mnHelp = new JMenu("Edit");
		menuBar.add(mnHelp);
		
		final JMenuItem mntmBringToFront = new JMenuItem("Bring to Front");
		mntmBringToFront.setHorizontalAlignment(SwingConstants.LEFT);
		mnHelp.add(mntmBringToFront);
		KeyStroke ctrlB = KeyStroke.getKeyStroke(KeyEvent.VK_B,InputEvent.CTRL_MASK);
		mntmBringToFront.setAccelerator(ctrlB);
		
		final JMenuItem mntmSelectAll = new JMenuItem("Select All");
		mntmSelectAll.setHorizontalAlignment(SwingConstants.LEFT);
		mnHelp.add(mntmSelectAll);
		mntmSelectAll.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JScrollPane test = (JScrollPane) tabbedPane.getComponentAt(sel);
		        JViewport test2 = test.getViewport();
		        JPanel test3 = (JPanel) test2.getView();
		        MainCanvas test4 = (MainCanvas) test3.getComponent(0);
		        test4.isSelect = true;
		        test4.shapetype = "";
		        test4.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		        for (int i = test4.shapes.size() - 1; i >= 0; i--){
		    		Shape s = (Shape)test4.shapes.get(i);
		    		s.isSelected = true;
		    		test4.clickedGroup.add(s);
		    		test4.repaint();
		        }
		        for (int i = test4.Groups.size() - 1; i >= 0; i--){
		        	Group g = test4.Groups.get(i);
		        	for (Shape t:g.children){
		        		t.isSelected = true;
		        		test4.clickedGroup.add(t);
		        		test4.repaint();
		        	}
		        }
				
			}
		});
		KeyStroke ctrlA = KeyStroke.getKeyStroke(KeyEvent.VK_A,InputEvent.CTRL_MASK);
		mntmSelectAll.setAccelerator(ctrlA);
		
		final JMenuItem mntmGroup = new JMenuItem("Group");
		mntmGroup.setHorizontalAlignment(SwingConstants.LEFT);
		mnHelp.add(mntmGroup);
		mntmGroup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JScrollPane test = (JScrollPane) tabbedPane.getComponentAt(sel);
		        JViewport test2 = test.getViewport();
		        JPanel test3 = (JPanel) test2.getView();
		        MainCanvas test4 = (MainCanvas) test3.getComponent(0);
		        test4.isSelect = true;
		        test4.shapetype = "";
		        test4.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		        Group testg = new Group();
		        
				for (int i = 0; i < test4.clickedGroup.size(); i++){
					Shape s = test4.clickedGroup.get(i);
					s.isSelected = true;
					testg.add(s);
			    	test4.shapes.remove(s);
			    	//test4.repaint();
			    }
				test4.Groups.add(testg);
				test4.repaint();
				
				
			}
		});
		KeyStroke ctrlG = KeyStroke.getKeyStroke(KeyEvent.VK_G,InputEvent.CTRL_MASK);
		mntmGroup.setAccelerator(ctrlG);
		
		final JMenuItem mntmUngroup = new JMenuItem("Ungroup");
		mntmUngroup.setHorizontalAlignment(SwingConstants.LEFT);
		mnHelp.add(mntmUngroup);
		mntmUngroup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JScrollPane test = (JScrollPane) tabbedPane.getComponentAt(sel);
		        JViewport test2 = test.getViewport();
		        JPanel test3 = (JPanel) test2.getView();
		        MainCanvas test4 = (MainCanvas) test3.getComponent(0);
		        test4.isSelect = true;
		        test4.shapetype = "";
		        test4.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				for (int i = 0; i < test4.selGroup.size(); i++){
					Shape s = test4.selGroup.get(i);
					test4.shapes.add(s);
					//test4.clickedGroup.remove(s);
			    	//test4.repaint();
			    }
				test4.Groups.remove(test4.selGroup);
				//test4.clickedGroup = new Group();
				test4.repaint();
				
				
			}
		});
		KeyStroke ctrlU = KeyStroke.getKeyStroke(KeyEvent.VK_U,InputEvent.CTRL_MASK);
		mntmUngroup.setAccelerator(ctrlU);
		
		JMenu mnAbout = new JMenu("Help");
		menuBar.add(mnAbout);
		
		mntmNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				canvas = new MainCanvas("",500, 400);
				canvast.add(canvas);
				JPanel test = new JPanel();
				test.setLayout(new BoxLayout(test, BoxLayout.X_AXIS));
				test.add(canvast.get(index));
				
				JScrollPane scrollPane2 = new JScrollPane();
				scrollPane2.setViewportView(test);
				scrollPane2.setBackground(Color.DARK_GRAY);
				mntmBringToFront.addActionListener(canvast.get(index));
				btnNewButton_0.addActionListener(canvast.get(index));
				btnCircle.addActionListener(canvast.get(index));
				btnRectangle.addActionListener(canvast.get(index));
				btnNewButton_2.addActionListener(canvast.get(index));
				btnRotate.addActionListener(canvast.get(index));
			    tabbedPane.insertTab("Untitled Document 00" + index2, null, scrollPane2, "", index);
				tabbedPane.setSelectedIndex(index);
				
		    	index++;
		    	index2++;
			}
		});
		KeyStroke ctrlN = KeyStroke.getKeyStroke(KeyEvent.VK_N,InputEvent.CTRL_MASK);
		mntmNew.setAccelerator(ctrlN);
		
		mntmOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				chooser = new JFileChooser();
				chooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
			        public boolean accept(File f) {
			          return f.getName().toLowerCase().endsWith(".svg")
			              || f.isDirectory();
			        }

			        public String getDescription() {
			          return "SVG Images";
			        }
			      });
				int returnVal = chooser.showOpenDialog(frame);

		        if (returnVal == JFileChooser.APPROVE_OPTION) {
		        	File f = chooser.getSelectedFile();
		        	String path = f.getAbsolutePath();
		        	
		        	SVGReader open = new SVGReader(path);
		        	
		        	canvas = new MainCanvas(path, open.returnWidth(), open.returnHeight());
					canvast.add(canvas);
					canvast.get(index).Groups = open.returnGroups();
					canvast.get(index).shapes = open.returnShapes();
					canvast.get(index).repaint();
					JPanel test = new JPanel();
					test.setLayout(new BoxLayout(test, BoxLayout.X_AXIS));
					test.add(canvast.get(index));
					JScrollPane scrollPane2 = new JScrollPane();
					scrollPane2.setViewportView(test);
					scrollPane2.getMaximumSize();
					scrollPane2.setBackground(Color.DARK_GRAY);
					mntmBringToFront.addActionListener(canvast.get(index));
					btnNewButton_0.addActionListener(canvast.get(index));
					btnCircle.addActionListener(canvast.get(index));
					btnRectangle.addActionListener(canvast.get(index));
					btnNewButton_2.addActionListener(canvast.get(index));
					btnRotate.addActionListener(canvast.get(index));
					tabbedPane.insertTab(f.getName(), null, scrollPane2, "", index);
					tabbedPane.setSelectedIndex(index);
			    	index++;
			    	index2++;
		        	
		            
		        } else {
		            
		        }
			}
		});
		KeyStroke ctrlO = KeyStroke.getKeyStroke(KeyEvent.VK_O,InputEvent.CTRL_MASK);
		mntmOpen.setAccelerator(ctrlO);
		
		tabbedPane.addChangeListener(new ChangeListener() {

		    public void stateChanged(ChangeEvent evt) {
		        JTabbedPane pane = (JTabbedPane)evt.getSource();
		        sel = pane.getSelectedIndex();
		        if (sel > -1){
		        JScrollPane test = (JScrollPane) tabbedPane.getComponentAt(sel);
		        JViewport test2 = test.getViewport();
		        JPanel test3 = (JPanel) test2.getView();
		        MainCanvas test4 = (MainCanvas) test3.getComponent(0);
		        lblDimensions.setText(test4.owidth + " x " + test4.oheight);
		        slider.setValue((int) (test4.scale*100));
		        properties.textField.setText(Double.toString(test4.owidth));
		        properties.textField_1.setText(Double.toString(test4.oheight));
		        }
		    }
		});
		slider.addChangeListener(new ChangeListener() {

		    public void stateChanged(ChangeEvent evt) {
		        JScrollPane test = (JScrollPane) tabbedPane.getComponentAt(sel);
		        JViewport test2 = test.getViewport();
		        JPanel test3 = (JPanel) test2.getView();
		        MainCanvas test4 = (MainCanvas) test3.getComponent(0);
		        JSlider source = (JSlider)evt.getSource();
		        if (source.getValueIsAdjusting()) {
		            int fps = (int)source.getValue();
		            test4.scale = Math.max(0.00001, fps/100.0); 
		            test4.repaint();
		            test4.width = test4.testrect.getWidth() * test4.scale;
		    		test4.height = test4.testrect.getHeight()* test4.scale;
		    		test4.setBounds(0,0,(int)test4.width, (int)test4.height);
		    		test4.setPreferredSize(new Dimension((int)test4.width,(int)test4.height));
		    		test4.revalidate();
		        }
		    }
		});
	
	}
	
	
	
	
	
	
	class DocDialog extends JDialog {

		private final JPanel contentPanel = new JPanel();
		private JTextField textField;
		private JTextField textField_1;

		/**
		 * Create the dialog.
		 */
		public DocDialog() {
			setTitle("Document Properties");
			setBounds(100, 100, 295, 162);
			setResizable(false);
			getContentPane().setLayout(new BorderLayout());
			contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
			getContentPane().add(contentPanel, BorderLayout.CENTER);
			contentPanel.setLayout(null);
			
			textField = new JTextField();
			textField.setBounds(103, 22, 104, 20);
			contentPanel.add(textField);
			textField.setColumns(10);
			
			textField_1 = new JTextField();
			textField_1.setBounds(103, 58, 104, 20);
			contentPanel.add(textField_1);
			textField_1.setColumns(10);
			
			JLabel lblWidth = new JLabel("Width:");
			lblWidth.setHorizontalAlignment(SwingConstants.CENTER);
			lblWidth.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblWidth.setBounds(22, 24, 46, 14);
			contentPanel.add(lblWidth);
			
			JLabel lblHeight = new JLabel("Height:");
			lblHeight.setHorizontalAlignment(SwingConstants.CENTER);
			lblHeight.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblHeight.setBounds(22, 60, 46, 14);
			contentPanel.add(lblHeight);
			
			{
				JPanel buttonPane = new JPanel();
				buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
				getContentPane().add(buttonPane, BorderLayout.SOUTH);
				{
					JButton okButton = new JButton("OK");
					okButton.setActionCommand("OK");
					okButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							if (!textField.getText().isEmpty() && !textField_1.getText().isEmpty()){
								JScrollPane test = (JScrollPane) tabbedPane.getComponentAt(sel);
						        JViewport test2 = test.getViewport();
						        JPanel test3 = (JPanel) test2.getView();
						        MainCanvas test4 = (MainCanvas) test3.getComponent(0);
						        if (isInteger(textField.getText()) && isInteger(textField_1.getText())){
							        test4.width = Double.parseDouble(textField.getText());
							        test4.height = Double.parseDouble(textField_1.getText());
							        test4.owidth = test4.width;
							        test4.oheight = test4.height;
							        test4.testrect = new Rect(0, 0, (int)test4.width, (int)test4.height);
							        test4.testrect.setColor(Color.WHITE);
							        test4.testrect.setStroke(Color.WHITE);
							        test4.repaint();
							        test4.setBounds(0,0,(int)test4.width, (int)test4.height);
						    		test4.setPreferredSize(new Dimension((int)test4.width,(int)test4.height));
						    		test4.revalidate();
							        test4.revalidate();
							        lblDimensions.setText(test4.owidth + " x " + test4.oheight);
							        setVisible(false);
						        }
							}
						}
					});
					buttonPane.add(okButton);
					getRootPane().setDefaultButton(okButton);
				}
				{
					JButton cancelButton = new JButton("Cancel");
					cancelButton.setActionCommand("Cancel");
					cancelButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							setVisible(false);
						}
					});
					buttonPane.add(cancelButton);
				}
			}
		}
	}


	public static boolean isInteger( String input )  
    {  
       try  
       {  
          Integer.parseInt( input );  
          return true;  
       }  
       catch( Exception e )  
       {  
          return false;  
       }  
    }  

	
	
	
		
}



