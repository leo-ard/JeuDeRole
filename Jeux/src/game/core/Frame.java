package game.core;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.CardLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Component;
import java.awt.Font;
import javax.swing.SwingConstants;

import game.editeur.Editeur;
import game.armes.Gun;
import game.editeur.ArmeTester;
import game.map.Map;
import game.map.Portal;
import gui.PauseMenu;

import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
//import java.io.FileNotFoundException;

import javax.swing.JCheckBox;
//import java.awt.FlowLayout;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
//import javax.swing.border.BevelBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
//import javax.swing.JList;
import java.awt.List;
import java.awt.Point;
//import java.awt.Rectangle;
//import java.awt.RenderingHints;
//import java.awt.Window;
//import javax.swing.border.CompoundBorder;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import java.awt.Rectangle;
import java.awt.Window;

public class Frame{

	public static JFrame frame;
	public JPanel Menu;
	public static GamePane gp;
	public static Editeur edi;
	public static ArmeTester armeTester;
	public JPanel card;
	public CardLayout cardLayout;
	private JButton bEditor;
	private JButton bOption;
	private JButton bStart;
	private JPanel pEditor;
	private JPanel panel;
	private JLabel lCommand;
	public JTextField tSizeX;
	public JTextField tSizeY;
	public JCheckBox cShowPosition;
	public static boolean mousePressedForPlacingBlock = false;
	public static boolean trueIfRight = false;
	public JTextField tIdR;
	private JButton bSave;
	public JTextField tIdL;
	public JRadioButton rdbtnRectangle;
	public final ButtonGroup buttonGroup = new ButtonGroup();
	public JRadioButton rdbtnDrawing;
	public JRadioButton rdbtnSelection;
	public JCheckBox cColision;
	private JPanel westPanel;
	private JPanel selectedPanel;
	private JPanel unselectedPanel;
	public JLabel lName;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel lNameOfFile;
	public JTextField tNameOfFile;
	public List ListOfmaps;
	private JButton bAdd;
	private JButton bLoad;
	public JTextField tName;
	public JLabel lFileName;
	private JButton bOpen;
	public JTextField tFileName;
	public JTextField txPortalPos;
	private JLabel lblX;
	private JLabel label_6;
	public JTextField tYPortalPos;
	public JButton btnLoad;
	public JCheckBox cJustOneWay;
	private JButton bDelete;
	public JLabel lY;
	public JLabel lX;
	public JLabel lId;
	private JLabel lblSelectABlock;
	private JButton bDeleteC;
	private JPanel pOptions;
	private JButton bBackO;
	private JButton bBackC;
	public JPanel pGame;
	private JLabel lblPause;
	private JPanel EastPanel;
	private JButton btnGunTest;
	public List armeList;
	private JLabel lblNombreDeBalles;
	private JLabel lblNewLabel;
	private JLabel label_3;
	private JLabel lblExplosif;
	private JLabel lblAutomatique;
	public JTextField tGunName;
	public JTextField tGunType;
	public JSpinner sDomage;
	public JSpinner sPrecision;
	public JSpinner sSpeed;
	public JSpinner sVitesseDeTir;
	public JCheckBox cGunExplosif;
	public JCheckBox cGunAuto;
	public JButton bLoadGun;
	public JButton btnSaveGun;
	private JButton bCreateGun;
	public static  PauseMenu pPause;
	

	/**
	 * Create the application.
	 */
	public Frame() {
		initializeMenu();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initializeMenu() {
		cardLayout = new CardLayout(); 
		card = new JPanel();
		edi = new Editeur();
		armeTester = new ArmeTester();
		armeTester.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent paramMouseEvent) {
				if(armeTester.hasFocus() == false){
					armeTester.requestFocus();
				}
				
			}
		});
		//FlowLayout flowLayout = (FlowLayout) edi.getLayout();
		edi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent paramMouseEvent) {
				if(edi.hasFocus() == false){
					edi.requestFocus();
				}
				
			}
			
			public void mousePressed(MouseEvent p){
				if(p.isControlDown() == false){
					mousePressedForPlacingBlock = true;
					if(p.getButton() == MouseEvent.BUTTON1){
						trueIfRight = true;
					}
					else if(p.getButton() == MouseEvent.BUTTON3){
						trueIfRight = false;
					}
				}
				else{
					if(p.getButton() == MouseEvent.BUTTON1){
						tIdR.setText(GamePane.map.getSelectedBlock().getId()+"");
					}
					if(p.getButton() == MouseEvent.BUTTON3){
						tIdL.setText(GamePane.map.getSelectedBlock().getId()+"");
					}
				}
			}
			
			public void mouseReleased(MouseEvent p){
				mousePressedForPlacingBlock = false;
			}
		});
		//edi.setBounds(0, 0, 1264, 761);
		pEditor = new JPanel();
		frame = new JFrame();
		frame.setMinimumSize(new Dimension(1280, 800));
		frame.setSize(new Dimension(1280, 800));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//frame.setContentPane(new GamePane());
		

		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
		
		frame.setResizable(true);
		
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		card.setLayout(cardLayout);
		
		/*pGame.setLayout(new BorderLayout(0,0));
		pGame.add(gp);*/
		pEditor.setLayout(new BorderLayout(0,0));
		pEditor.add(edi);
		JPanel pMenu = new JPanel();
		card.add("Menu", pMenu);
		card.add("Editor", pEditor);
		edi.thread.interrupt();
		
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(200, 10));
		panel.setMinimumSize(new Dimension(100, 100));
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		pEditor.add(panel, BorderLayout.EAST);
		
		lCommand = new JLabel("Commands");
		lCommand.setFont(new Font("Tahoma", Font.BOLD, 30));
		lCommand.setMinimumSize(new Dimension(100, 14));
		lCommand.setMaximumSize(new Dimension(100, 14));
		
		tSizeX = new JTextField();
		tSizeX.setColumns(10);
		
		JLabel lblSize = new JLabel("Size :");
		lblSize.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		tSizeY = new JTextField();
		tSizeY.setColumns(10);
		
		cShowPosition = new JCheckBox("show position");
		
		tIdR = new JTextField();
		tIdR.setColumns(10);
		tIdR.setText(1+"");
		
		JLabel label = new JLabel("Id :");
		label.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		bSave = new JButton("Save");
		
		bOpen = new JButton("Open");
		
		tIdL = new JTextField();
		tIdL.setColumns(10);
		tIdL.setText(2+"");
		
		JLabel lblTool = new JLabel("Tool : ");
		lblTool.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		rdbtnDrawing = new JRadioButton("Drawing");
		buttonGroup.add(rdbtnDrawing);
		
		rdbtnSelection = new JRadioButton("Selection");
		buttonGroup.add(rdbtnSelection);
		
		rdbtnRectangle = new JRadioButton("Rectangle");
		buttonGroup.add(rdbtnRectangle);
		rdbtnDrawing.setSelected(true);
		
		cColision = new JCheckBox("Colision");
		
		lFileName = new JLabel("File Name :");
		lFileName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		JLabel label_5 = new JLabel("Name :");
		label_5.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		tName = new JTextField();
		tName.setColumns(10);
		
		tFileName = new JTextField();
		tFileName.setColumns(10);

		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(lCommand, GroupLayout.DEFAULT_SIZE, 180, Short.MAX_VALUE)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblTool)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(rdbtnSelection)
								.addComponent(rdbtnDrawing)
								.addComponent(rdbtnRectangle)))
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.TRAILING)
								.addComponent(label, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblSize))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(tSizeX, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
								.addComponent(tIdR, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE))
							.addGap(4)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(tIdL, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)
								.addComponent(tSizeY, GroupLayout.PREFERRED_SIZE, 35, GroupLayout.PREFERRED_SIZE)))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lFileName)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(tFileName, GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE))
						.addComponent(bSave, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
						.addComponent(bOpen, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(label_5)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(tName, GroupLayout.DEFAULT_SIZE, 119, Short.MAX_VALUE))
						.addComponent(cColision, GroupLayout.PREFERRED_SIZE, 91, GroupLayout.PREFERRED_SIZE)
						.addComponent(cShowPosition))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lCommand, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(17)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_5, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
						.addComponent(tName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lFileName, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
						.addComponent(tFileName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(12)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(tSizeY, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(tSizeX, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSize))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(label, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
							.addComponent(tIdR, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(tIdL, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblTool)
						.addComponent(rdbtnDrawing))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(rdbtnSelection)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(rdbtnRectangle)
					.addPreferredGap(ComponentPlacement.RELATED, 359, Short.MAX_VALUE)
					.addComponent(cColision)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(cShowPosition)
					.addGap(18)
					.addComponent(bOpen)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(bSave)
					.addContainerGap())
		);
		panel.setLayout(gl_panel);
		
		westPanel = new JPanel();
		westPanel.setBorder(new LineBorder(Color.BLACK));
		westPanel.setMinimumSize(new Dimension(100, 100));
		pEditor.add(westPanel, BorderLayout.WEST);
		westPanel.setLayout(new CardLayout(0, 0));
		
		
		selectedPanel = new JPanel();
		selectedPanel.setBorder(null);
		selectedPanel.setPreferredSize(new Dimension(200, 10));
		selectedPanel.setMinimumSize(new Dimension(100, 10));
		westPanel.add(selectedPanel, "selectedPanel");
		
		JLabel lSelected = new JLabel("Selected");
		lSelected.setFont(new Font("Tahoma", Font.BOLD, 30));
		
		lId = new JLabel("Id: ");
		lId.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		lName = new JLabel("Name :");
		lName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		label_1 = new JLabel("Portals Options");
		label_1.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		label_2 = new JLabel("Informations");
		label_2.setFont(new Font("Tahoma", Font.BOLD, 15));
		
		lNameOfFile = new JLabel("Name of file :");
		lNameOfFile.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		tNameOfFile = new JTextField();
		tNameOfFile.setColumns(10);
		
		JLabel label_4 = new JLabel("Portal position :");
		label_4.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		txPortalPos = new JTextField();
		txPortalPos.setColumns(10);
		
		lblX = new JLabel("X :");
		
		label_6 = new JLabel("Y :");
		
		tYPortalPos = new JTextField();
		tYPortalPos.setColumns(10);
		
		btnLoad = new JButton("Load");
		
		cJustOneWay = new JCheckBox("Just One Way");
		
		bDelete = new JButton("Delete");
		
		lX = new JLabel("X :");
		lX.setFont(new Font("Tahoma", Font.PLAIN, 15));
		
		lY = new JLabel("Y :");
		lY.setFont(new Font("Tahoma", Font.PLAIN, 15));
		GroupLayout gl_selectedPanel = new GroupLayout(selectedPanel);
		gl_selectedPanel.setHorizontalGroup(
			gl_selectedPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_selectedPanel.createSequentialGroup()
					.addGap(34)
					.addGroup(gl_selectedPanel.createParallelGroup(Alignment.TRAILING)
						.addGroup(gl_selectedPanel.createSequentialGroup()
							.addGroup(gl_selectedPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_selectedPanel.createSequentialGroup()
									.addGap(12)
									.addGroup(gl_selectedPanel.createParallelGroup(Alignment.LEADING, false)
										.addComponent(lName, GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
										.addComponent(lX, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(lY, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
										.addComponent(lId, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
								.addComponent(lSelected)
								.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE))
							.addGap(30))
						.addGroup(gl_selectedPanel.createSequentialGroup()
							.addGroup(gl_selectedPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 136, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_selectedPanel.createSequentialGroup()
									.addGap(12)
									.addGroup(gl_selectedPanel.createParallelGroup(Alignment.LEADING)
										.addComponent(lNameOfFile, GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
										.addComponent(label_4, GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
										.addGroup(gl_selectedPanel.createSequentialGroup()
											.addGap(12)
											.addGroup(gl_selectedPanel.createParallelGroup(Alignment.LEADING)
												.addGroup(gl_selectedPanel.createSequentialGroup()
													.addComponent(label_6, GroupLayout.PREFERRED_SIZE, 14, GroupLayout.PREFERRED_SIZE)
													.addPreferredGap(ComponentPlacement.RELATED)
													.addComponent(tYPortalPos, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))
												.addGroup(gl_selectedPanel.createSequentialGroup()
													.addComponent(lblX)
													.addPreferredGap(ComponentPlacement.RELATED)
													.addComponent(txPortalPos, GroupLayout.PREFERRED_SIZE, 40, GroupLayout.PREFERRED_SIZE))))
										.addComponent(cJustOneWay)
										.addGroup(gl_selectedPanel.createParallelGroup(Alignment.TRAILING, false)
											.addComponent(btnLoad, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
											.addComponent(bDelete, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE))
										.addComponent(tNameOfFile, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
							.addContainerGap())))
		);
		gl_selectedPanel.setVerticalGroup(
			gl_selectedPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_selectedPanel.createSequentialGroup()
					.addContainerGap()
					.addComponent(lSelected)
					.addGap(18)
					.addComponent(label_2, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lName, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lId)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lX, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lY, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
					.addGap(16)
					.addComponent(label_1, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lNameOfFile, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(tNameOfFile, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(label_4, GroupLayout.PREFERRED_SIZE, 19, GroupLayout.PREFERRED_SIZE)
					.addGap(3)
					.addGroup(gl_selectedPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblX)
						.addComponent(txPortalPos, GroupLayout.PREFERRED_SIZE, 20, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(gl_selectedPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(label_6)
						.addComponent(tYPortalPos, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(cJustOneWay)
					.addGap(7)
					.addComponent(btnLoad)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(bDelete)
					.addContainerGap(322, Short.MAX_VALUE))
		);
		selectedPanel.setLayout(gl_selectedPanel);
		
		unselectedPanel = new JPanel();
		westPanel.add(unselectedPanel, "unselectedPanel");
		unselectedPanel.setLayout(null);
		
		lblSelectABlock = new JLabel("Select a block");
		lblSelectABlock.setBounds(59, 341, 80, 56);
		unselectedPanel.add(lblSelectABlock);
		//edi.setLayout(null);
		
		cardLayout.show(card, "Menu");
		frame.getContentPane().add(card);
		
		bStart = new JButton("Start");
		bStart.setFont(new Font("Tahoma", Font.PLAIN, 50));
		
		((CardLayout) westPanel.getLayout()).show(westPanel, "unselectedPanel");
		
		
		JLabel lNameOfGame = new JLabel("THE BEST GAME OF 'EM ALL");
		lNameOfGame.setHorizontalAlignment(SwingConstants.CENTER);
		lNameOfGame.setAlignmentX(Component.CENTER_ALIGNMENT);
		lNameOfGame.setFont(new Font("Tahoma", Font.PLAIN, 50));
		
		bEditor = new JButton("Editor");
		
		bEditor.setFont(new Font("Tahoma", Font.PLAIN, 40));
		
		bOption = new JButton("Options\r\n");
		bOption.setFont(new Font("Tahoma", Font.PLAIN, 40));

		GroupLayout gl_pMenu = new GroupLayout(pMenu);
		gl_pMenu.setHorizontalGroup(
			gl_pMenu.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pMenu.createSequentialGroup()
					.addGap(315)
					.addGroup(gl_pMenu.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_pMenu.createSequentialGroup()
							.addComponent(bEditor, GroupLayout.DEFAULT_SIZE, 307, Short.MAX_VALUE)
							.addGap(18)
							.addComponent(bOption, GroupLayout.DEFAULT_SIZE, 309, Short.MAX_VALUE))
						.addComponent(lNameOfGame, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(bStart, GroupLayout.DEFAULT_SIZE, 634, Short.MAX_VALUE))
					.addGap(315))
		);
		gl_pMenu.setVerticalGroup(
			gl_pMenu.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pMenu.createSequentialGroup()
					.addGap(61)
					.addComponent(lNameOfGame, GroupLayout.DEFAULT_SIZE, 82, Short.MAX_VALUE)
					.addGap(18)
					.addComponent(bStart, GroupLayout.DEFAULT_SIZE, 283, Short.MAX_VALUE)
					.addGap(18)
					.addGroup(gl_pMenu.createParallelGroup(Alignment.BASELINE)
						.addComponent(bEditor, GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
						.addComponent(bOption, GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE))
					.addGap(159))
		);
		pMenu.setLayout(gl_pMenu);
		
		JPanel pChoseMap = new JPanel();
		card.add(pChoseMap, "choseMap");
		pChoseMap.setLayout(null);
		
		JLabel lChoseTheMap = new JLabel("CHOSE THE MAP");
		lChoseTheMap.setBounds(431, 147, 375, 61);
		lChoseTheMap.setHorizontalAlignment(SwingConstants.CENTER);
		lChoseTheMap.setFont(new Font("Tahoma", Font.PLAIN, 50));
		lChoseTheMap.setAlignmentX(0.5f);
		pChoseMap.add(lChoseTheMap);
		
		bLoad = new JButton("Load");
		bLoad.setBounds(708, 500, 98, 26);
		pChoseMap.add(bLoad);
		
		ListOfmaps = new List();
		ListOfmaps.setMultipleMode(false);
		ListOfmaps.setBounds(431, 236, 375, 244);
		pChoseMap.add(ListOfmaps);
		
		bAdd = new JButton("add");
		bAdd.setBounds(598, 500, 98, 26);
		pChoseMap.add(bAdd);
		
		bDeleteC = new JButton("Delete");
		bDeleteC.setBounds(431, 500, 98, 26);
		pChoseMap.add(bDeleteC);
		
		bBackC = new JButton("<---");
		bBackC.setFont(new Font("DejaVu Sans", Font.PLAIN, 20));
		bBackC.setBounds(12, 12, 72, 34);
		pChoseMap.add(bBackC);
		
		btnGunTest = new JButton("Gun Test");
		btnGunTest.setBounds(708, 538, 98, 26);
		pChoseMap.add(btnGunTest);
		
		pOptions = new JPanel();
		card.add(pOptions, "Options");
		
		bBackO = new JButton("<---");
		bBackO.setFont(new Font("DejaVu Sans", Font.PLAIN, 20));
		GroupLayout gl_pOptions = new GroupLayout(pOptions);
		gl_pOptions.setHorizontalGroup(
			gl_pOptions.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pOptions.createSequentialGroup()
					.addContainerGap()
					.addComponent(bBackO)
					.addContainerGap(1180, Short.MAX_VALUE))
		);
		gl_pOptions.setVerticalGroup(
			gl_pOptions.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pOptions.createSequentialGroup()
					.addContainerGap()
					.addComponent(bBackO)
					.addContainerGap(715, Short.MAX_VALUE))
		);
		pOptions.setLayout(gl_pOptions);
		
		pGame = new JPanel();
		pGame.setBounds(new Rectangle(0, 0, 10000, 10000));
		card.add(pGame, "Game");
		pGame.setLayout(new BorderLayout(0, 0));
		pPause = new PauseMenu();
		pPause.setBounds(new Rectangle(0, 0,GamePane.WIDTH, GamePane.HEIGHT));
		
		
		lblPause = new JLabel("Pause");
		lblPause.setFont(new Font("Corbel", Font.BOLD, 30));
		lblPause.setForeground(Color.LIGHT_GRAY);
		GroupLayout gl_pPause = new GroupLayout(pPause);
		gl_pPause.setHorizontalGroup(
			gl_pPause.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_pPause.createSequentialGroup()
					.addGap(578)
					.addComponent(lblPause, GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)
					.addGap(578))
		);
		gl_pPause.setVerticalGroup(
			gl_pPause.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_pPause.createSequentialGroup()
					.addGap(170)
					.addComponent(lblPause, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
					.addGap(593))
		);
		pPause.setLayout(gl_pPause);
		pPause.setVisible(false);
		pGame.add(pPause, BorderLayout.CENTER);
		gp = new GamePane();
		pGame.add(gp);
		gp.thread.interrupt();
		
		//gp.addKeyListener(gp.l);
		//edi.addKeyListener(edi.l);
		gp.setLayout(null);
		
		JPanel pArme = new JPanel();
		card.add(pArme, "Gun");
		pArme.setLayout(new BorderLayout(0, 0));
		
		pArme.add(armeTester, BorderLayout.CENTER);
		armeTester.thread.interrupt();
		
		EastPanel = new JPanel();
		EastPanel.setPreferredSize(new Dimension(200, 10));
		pArme.add(EastPanel, BorderLayout.EAST);
		EastPanel.setLayout(null);
		
		armeList = new List();
		armeList.setBounds(10, 10, 180, 113);
		EastPanel.add(armeList);
		
		JLabel lGunName = new JLabel("Name");
		lGunName.setBounds(10, 172, 55, 16);
		EastPanel.add(lGunName);
		
		JLabel lType = new JLabel("Type");
		lType.setBounds(10, 200, 55, 16);
		EastPanel.add(lType);
		
		JLabel lGunDomage = new JLabel("Domage");
		lGunDomage.setBounds(10, 228, 55, 16);
		EastPanel.add(lGunDomage);
		
		JLabel lGunPrecision = new JLabel("Precision");
		lGunPrecision.setBounds(10, 256, 55, 16);
		EastPanel.add(lGunPrecision);
		
		JLabel lGunSpeed = new JLabel("Speed");
		lGunSpeed.setBounds(10, 284, 55, 16);
		EastPanel.add(lGunSpeed);
		
		lblNombreDeBalles = new JLabel("balles par millieme");
		lblNombreDeBalles.setBounds(10, 312, 119, 16);
		EastPanel.add(lblNombreDeBalles);
		
		lblNewLabel = new JLabel("Durabilite");
		lblNewLabel.setBounds(10, 340, 55, 16);
		EastPanel.add(lblNewLabel);
		
		label_3 = new JLabel("Knockback");
		label_3.setBounds(10, 368, 66, 16);
		EastPanel.add(label_3);
		
		lblExplosif = new JLabel("Explosif");
		lblExplosif.setBounds(10, 396, 55, 16);
		EastPanel.add(lblExplosif);
		
		lblAutomatique = new JLabel("Automatique");
		lblAutomatique.setBounds(10, 424, 83, 16);
		EastPanel.add(lblAutomatique);
		
		tGunName = new JTextField();
		tGunName.setBounds(74, 170, 114, 20);
		EastPanel.add(tGunName);
		tGunName.setColumns(10);
		
		tGunType = new JTextField();
		tGunType.setColumns(10);
		tGunType.setBounds(74, 198, 116, 20);
		EastPanel.add(tGunType);
		
		sDomage = new JSpinner();
		sDomage.setModel(new SpinnerNumberModel(0, 0, 1000, 1));
		sDomage.setBounds(147, 226, 41, 20);
		EastPanel.add(sDomage);
		
		sPrecision = new JSpinner();
		sPrecision.setModel(new SpinnerNumberModel(0, 0, 100, 1));
		sPrecision.setBounds(147, 254, 41, 20);
		EastPanel.add(sPrecision);
		
		sSpeed = new JSpinner();
		sSpeed.setModel(new SpinnerNumberModel(1, 1, 100, 1));
		sSpeed.setBounds(147, 282, 41, 20);
		EastPanel.add(sSpeed);
		
		sVitesseDeTir = new JSpinner();
		sVitesseDeTir.setModel(new SpinnerNumberModel(1000, 1, 100000, 25));
		sVitesseDeTir.setBounds(133, 310, 55, 20);
		EastPanel.add(sVitesseDeTir);
		
		cGunExplosif = new JCheckBox("");
		cGunExplosif.setBounds(169, 396, 21, 24);
		EastPanel.add(cGunExplosif);
		
		cGunAuto = new JCheckBox("");
		cGunAuto.setBounds(169, 424, 21, 24);
		EastPanel.add(cGunAuto);
		
		bLoadGun = new JButton("Load Gun");
		bLoadGun.setBounds(90, 132, 98, 26);
		EastPanel.add(bLoadGun);
		
		btnSaveGun = new JButton("Save");
		
		btnSaveGun.setBounds(105, 456, 83, 26);
		EastPanel.add(btnSaveGun);
		
		bCreateGun = new JButton("Create");

		bCreateGun.setBounds(10, 456, 83, 26);
		EastPanel.add(bCreateGun);
		
		Listeners();
	}
	
	public void Listeners(){
		bStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cardLayout.show(card, "Game");
				gp.thread.start();
				GamePane.WB = true;
			}
		});
		bEditor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(card, "choseMap");
				Map.loadMapsToList();
			}
		});
		tSizeX.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					GamePane.map.setSizeX(Integer.parseInt(tSizeX.getText()));
				}
				catch(NumberFormatException e1){
					JOptionPane.showMessageDialog(Frame.frame, "PAS DE LETTRES");
				}
				edi.requestFocus();
			}
		});
		
		tSizeY.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					GamePane.map.setSizeY(Integer.parseInt(tSizeY.getText()));
				}
				catch(NumberFormatException e1){
					JOptionPane.showMessageDialog(Frame.frame, "PAS DE LETTRES");
				}
				edi.requestFocus();
			}
		});
		
		bSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GamePane.map.save();
				edi.requestFocus();
			}
		});
		tIdR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				edi.requestFocus();
			}
		});
		tIdL.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				edi.requestFocus();
			}
		});
		bDeleteC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(ListOfmaps.getSelectedIndex() >= 0){
					if(JOptionPane.YES_OPTION == JOptionPane.showConfirmDialog(Frame.frame, "Are you sure that u want to delete "+ListOfmaps.getSelectedItem() +"?", "Just a simple question", 0)){
						Map.removeFromIndex(ListOfmaps.getSelectedItem());
						ListOfmaps.remove(ListOfmaps.getSelectedIndex());
					}
				}
			}
		});
		bLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cardLayout.show(card, "Editor");
				edi.strMapToLoad = ListOfmaps.getSelectedItem();
				edi.restart();
			}
		});
		bAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String str = Map.getAvalableInIndex("Map");
				edi.mapToLoad = new Map(str,str,20,20);
				
				Map.addIndexFile(str);
				cardLayout.show(card, "Editor");
				edi.restart();
			}
		});
		bOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GamePane.map.save();
				edi.thread.stop();
				cardLayout.show(card,"choseMap");
			}
		});
		tName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				GamePane.map.setName(tName.getText());
			}
		});
		tFileName.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(Map.isAvalableInIndex(tFileName.getText())){
					Map.changeThisByThatInIndex(GamePane.map.getFileName(), tFileName.getText());
					GamePane.map.setFileName(tFileName.getText());
					GamePane.map.save();
				}else{
					JOptionPane.showMessageDialog(Frame.frame, "\""+tFileName.getText()+"\""+" is not avalaible");
				}
			}
		});
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!tNameOfFile.getText().equals("")&&!txPortalPos.getText().equals("")&&!tYPortalPos.getText().equals("")){	
					if(Map.isRedSelection){
						int x;
						int y;
						try{
							x = Integer.parseInt(txPortalPos.getText());
							y = Integer.parseInt(tYPortalPos.getText());
						}catch(NumberFormatException e){
							JOptionPane.showMessageDialog(Frame.frame,"I DONT WANT LETTERS IN POSITIONS !");
							return;
						}
						
						Map m = new Map(tNameOfFile.getText());
						if(!m.isLoaded()){
							JOptionPane.showMessageDialog(Frame.frame,"This file doesnt exist !");
							return;
						}
						GamePane.map.addPortal(Map.redSelectedBlock, new Point(x,y), tNameOfFile.getText());
						
						if(!cJustOneWay.isSelected()){
							m.addPortal(new Point(x,y), Map.redSelectedBlock, GamePane.map.getFileName());
							m.save();
						}
						
					}
					else
						JOptionPane.showMessageDialog(Frame.frame,"Be in SELECTION mode to use this");
				}
				else
					JOptionPane.showMessageDialog(Frame.frame,"Fill all the blank spaces !");
			}
		});
		
		bDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(Map.isRedSelection){
					if(!cJustOneWay.isSelected()){
						try{
						Portal p = GamePane.map.getPortal((int)Map.redSelectedBlock.getX(), (int)Map.redSelectedBlock.getY());
						Map m = new Map(p.getName());
						m.removePortal((int)p.getPointB().getX(), (int)p.getPointB().getY());
						m.save();
						}catch(NullPointerException e){};
					}
					GamePane.map.removePortal((int)Map.redSelectedBlock.getX(), (int)Map.redSelectedBlock.getY());
					
				}
			}
		});
		
		bBackO.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cardLayout.show(card, "Menu");
			}
		});
		
		bBackC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cardLayout.show(card, "Menu");
			}
		});
		bOption.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cardLayout.show(card, "Options");
			}
		});
		btnGunTest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cardLayout.show(card, "Gun");
				armeTester.restart();
			}
		});
		bLoadGun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Gun.loadGunIntoPanel();
			}
		});
		btnSaveGun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				for(int i = 0; i < Gun.GUNS.size();i++){
					if(Gun.GUNS.get(i).getNom().equals(Gun.GUNS.get(Gun.loaded).getNom())){
						JOptionPane.showConfirmDialog(null, "KELI WAS HERE");
						return;
					}
				}
				Gun.GUNS.get(Gun.loaded).setNom(tGunName.getText());
				Gun.GUNS.get(Gun.loaded).setType(tGunType.getText());
				Gun.GUNS.get(Gun.loaded).setDomage((int)sDomage.getValue());
				Gun.GUNS.get(Gun.loaded).setPrecision((int)sPrecision.getValue());
				Gun.GUNS.get(Gun.loaded).setNbDeBalle((int)sVitesseDeTir.getValue());
				Gun.GUNS.get(Gun.loaded).setSpeed((int)sSpeed.getValue());
				Gun.GUNS.get(Gun.loaded).setExplosif(cGunExplosif.isSelected());
				Gun.GUNS.get(Gun.loaded).setAuto(cGunAuto.isSelected());
				Gun.saveGuns();
				Gun.loadGuns();
			}
		});
		
		bCreateGun.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Gun g = new Gun();
				g.setNom(tGunName.getText());
				g.setType(tGunType.getText());
				g.setDomage((int)sDomage.getValue());
				g.setPrecision((int)sPrecision.getValue());
				g.setNbDeBalle((int)sVitesseDeTir.getValue());
				g.setSpeed((int)sSpeed.getValue());
				g.setExplosif(cGunExplosif.isSelected());
				g.setAuto(cGunAuto.isSelected());
				Gun.GUNS.add(g);
				Gun.saveGuns();
				Gun.loadGuns();
			}
		});
		
	}
	
	public void changeWestPanel(String str){
		((CardLayout) westPanel.getLayout()).show(westPanel, str);
	}
}
