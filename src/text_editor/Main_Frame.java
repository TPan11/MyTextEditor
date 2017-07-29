package text_editor;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;
import javax.swing.ScrollPaneConstants;
import javax.swing.filechooser.*;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;


public class Main_Frame extends JFrame{

	private File currentEditingFile = null;
	private int fontsize = 14;
	private ImageIcon img;
	private JButton btnNew;
	private JFrame frmTextEditor;
	private JFileChooser fileOpener;
	private JFileChooser saveDialog;
	private JTextArea display;
	private JToolBar toolBar;
	private JButton btnOpen;
	private JButton btnSave;
	private JButton btnIncreaseFont;
	private JButton btnDecreaseFont;
	private JScrollPane scrollPane;
	private JButton btnCopy;
	private JButton btnPaste;
	private JButton btnCut;
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem menuItem;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main_Frame window = new Main_Frame();
					window.frmTextEditor.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	/**
	 * Create the application.
	 */
	public Main_Frame() {
		initialize();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
		fileOpener.setFileFilter(filter);
		
		frmTextEditor.setLocationRelativeTo(null);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmTextEditor = new JFrame();
		frmTextEditor.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frmTextEditor.addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e){
            	int confirm = JOptionPane.showConfirmDialog(frmTextEditor, "Are you sure you want to exit the program?", "Exit", JOptionPane.YES_NO_OPTION);
        		if(confirm == JOptionPane.YES_OPTION){
        			System.exit(0);
        		}
        		else{}
            }
        });
		frmTextEditor.setTitle("Text Editor");
		frmTextEditor.setBounds(100, 100, 713, 470);
		frmTextEditor.setResizable(true);
		frmTextEditor.getContentPane().setLayout(null);
		
		fileOpener = new JFileChooser();
		fileOpener.setDialogType(JFileChooser.OPEN_DIALOG);
		
		saveDialog = new JFileChooser();
		saveDialog.setDialogType(JFileChooser.SAVE_DIALOG);
		saveDialog.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		
		menuBar = new JMenuBar();
		frmTextEditor.setJMenuBar(menuBar);
		
		menu = new JMenu("File");
		menu.setMnemonic(KeyEvent.VK_F);
		menuBar.add(menu);
		
		menuItem = new JMenuItem("New", KeyEvent.VK_N);
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewActionPerformed(e);
			}
		});
		menu.add(menuItem);
		
		menuItem = new JMenuItem("Open", KeyEvent.VK_O);
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnOpenActionPerformed(e);
			}
		});
		menu.add(menuItem);
		
		menuItem = new JMenuItem("Save", KeyEvent.VK_S);
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnSaveActionPerformed(e);
			}
		});
		menu.add(menuItem);
		
		menu.addSeparator();
		
		menuItem = new JMenuItem("Exit", KeyEvent.VK_E);
		menuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnExitActionPerformed(e);
			}
		});
		menu.add(menuItem);
		
		toolBar = new JToolBar();
		toolBar.setBounds(0, 0, 695, 62);
		frmTextEditor.getContentPane().add(toolBar);
		
		img = new ImageIcon(this.getClass().getResource("/New-file.png"));
		btnNew = new JButton("New");
		btnNew.setHorizontalTextPosition(SwingConstants.CENTER);
		btnNew.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnNewActionPerformed(e);
			}
		});
		btnNew.setToolTipText("New blank file");
		btnNew.setIcon(img);
		toolBar.add(btnNew);
		
		img = new ImageIcon(this.getClass().getResource("/Open-file.png"));
		btnOpen = new JButton("Open");
		btnOpen.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnOpen.setHorizontalTextPosition(SwingConstants.CENTER);
		btnOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btnOpenActionPerformed(evt);
			}
		});
		btnOpen.setIcon(img);
		btnOpen.setToolTipText("Open a new file");
		toolBar.add(btnOpen);
		
		
		img = new ImageIcon(this.getClass().getResource("/Save-file.png"));
		btnSave = new JButton("Save");
		btnSave.setHorizontalTextPosition(SwingConstants.CENTER);
		btnSave.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				btnSaveActionPerformed(evt);
			}
		});
		btnSave.setToolTipText("Save the file");
		btnSave.setIcon(img);
		toolBar.add(btnSave);
		
		img = new ImageIcon(this.getClass().getResource("/Increase-Font.png"));
		btnIncreaseFont = new JButton("Increase Font");
		btnIncreaseFont.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnIncreaseFontActionPerformed(e);
			}
		});
		btnIncreaseFont.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnIncreaseFont.setHorizontalTextPosition(SwingConstants.CENTER);
		btnIncreaseFont.setToolTipText("Increase font size");
		btnIncreaseFont.setIcon(img);
		toolBar.add(btnIncreaseFont);
		
		img = new ImageIcon(this.getClass().getResource("/Decrease-Font.png"));
		btnDecreaseFont = new JButton("Decrease Font");
		btnDecreaseFont.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				btnDecreaseFontActionPerformed(e);
			}
		});
		btnDecreaseFont.setHorizontalTextPosition(SwingConstants.CENTER);
		btnDecreaseFont.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnDecreaseFont.setToolTipText("Decrease Font Size");
		btnDecreaseFont.setIcon(img);
		toolBar.add(btnDecreaseFont);
		
		btnCopy = new JButton("Copy");
		btnCopy.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnCopy.setHorizontalTextPosition(SwingConstants.CENTER);
		img = new ImageIcon(this.getClass().getResource("/Copy-icon.png"));
		btnCopy.setIcon(img);
		btnCopy.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				display.copy();
			}
		});
		toolBar.add(btnCopy);
		
		btnPaste = new JButton("Paste");
		btnPaste.setHorizontalTextPosition(SwingConstants.CENTER);
		btnPaste.setVerticalTextPosition(SwingConstants.BOTTOM);
		img = new ImageIcon(this.getClass().getResource("/Paste-icon.png"));
		btnPaste.setIcon(img);
		btnPaste.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				display.paste();
			}
		});
		toolBar.add(btnPaste);
		
		btnCut = new JButton("Cut");
		btnCut.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnCut.setHorizontalTextPosition(SwingConstants.CENTER);
		img = new ImageIcon(this.getClass().getResource("/Cut-icon.png"));
		btnCut.setIcon(img);
		btnCut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				display.cut();
			}
		});
		toolBar.add(btnCut);
		
		display = new JTextArea();
		display.setBounds(0, 65, 695, 358);
		display.setFont(new Font("Times New Roman", Font.PLAIN, fontsize));
		//frmTextEditor.getContentPane().add(display);
		display.setColumns(20);
		display.setRows(5);
		display.setLineWrap(true);
		
		
		scrollPane = new JScrollPane(display);
		scrollPane.setBounds(0, 70, 695, 353);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		frmTextEditor.getContentPane().add(scrollPane);
		
	}
	
	private void btnOpenActionPerformed(ActionEvent evt){
		
		int status = fileOpener.showOpenDialog(frmTextEditor);
		if(status == JFileChooser.APPROVE_OPTION){
			currentEditingFile = fileOpener.getSelectedFile();
			System.out.println("File chosen. File name "+ fileOpener.getSelectedFile().getName());
			
			try {
				Scanner sc = new Scanner(new FileInputStream(currentEditingFile));
				String buffer = "";
				while(sc.hasNext()){
					buffer+= sc.nextLine() + "\n";
				}
				display.setText(buffer);
				sc.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				Logger.getLogger(Main_Frame.class.getName()).log(Level.SEVERE, null, e);
			}
		}
		else{
			System.out.println("No File Chosen");
		}
	}
	
	private void btnSaveActionPerformed(ActionEvent evt){
		if(currentEditingFile == null){
			int status = saveDialog.showOpenDialog(frmTextEditor);
			if(status == JFileChooser.APPROVE_OPTION){
				String filename = JOptionPane.showInputDialog("File Name", "untitled.txt");
				if(!filename.contains(".txt")){
					filename += ".txt";
				}
				File f = new File(saveDialog.getSelectedFile()+ "\\" + filename);
				if(f.exists()){
					JOptionPane.showMessageDialog(frmTextEditor, "File Already Exists", "Error", JOptionPane.ERROR_MESSAGE);
					return;
				}
				else{
					try {
						f.createNewFile();
						PrintWriter printwriter = new PrintWriter(f);
						printwriter.write(display.getText());
						printwriter.close();
						JOptionPane.showMessageDialog(frmTextEditor, "File Saved", "Saved", JOptionPane.INFORMATION_MESSAGE);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						Logger.getLogger(Main_Frame.class.getName()).log(Level.SEVERE, null, e);
					}
				}
			}
			else{
				JOptionPane.showMessageDialog(frmTextEditor, "Can't save", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		else{
			try {
				PrintWriter printwriter = new PrintWriter(currentEditingFile);
				printwriter.write(display.getText());
				printwriter.close();
				JOptionPane.showMessageDialog(frmTextEditor, "Current File Saved", "Saved", JOptionPane.INFORMATION_MESSAGE);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				Logger.getLogger(Main_Frame.class.getName()).log(Level.SEVERE, null, e);
			}
		}
	}
	private void btnNewActionPerformed(ActionEvent evt){
		int option = JOptionPane.showConfirmDialog(frmTextEditor, "Do you want to save open file?");
		if(option == JOptionPane.OK_OPTION){
			if(currentEditingFile == null){
				int status = saveDialog.showOpenDialog(frmTextEditor);
				if(status == JFileChooser.APPROVE_OPTION){
					String filename = JOptionPane.showInputDialog("File Name", "untitled.txt");
					if(!filename.contains(".txt")){
						filename += ".txt";
					}
					File f = new File(saveDialog.getSelectedFile()+"\\"+filename);
					if(f.exists()){
						JOptionPane.showMessageDialog(frmTextEditor, "File Exists", "Error", JOptionPane.ERROR_MESSAGE);
						return;
					}
					else{
						try {
							f.createNewFile();
							PrintWriter printwriter = new PrintWriter(f);
							printwriter.write(display.getText());
							printwriter.close();
							JOptionPane.showMessageDialog(frmTextEditor, "File saved", "Saved", JOptionPane.INFORMATION_MESSAGE);
						} catch (IOException e) {
							// TODO Auto-generated catch block
							Logger.getLogger(Main_Frame.class.getName()).log(Level.SEVERE, null, e);
						}
					}
				}
			}
			else{
				try {
					PrintWriter printwriter = new PrintWriter(currentEditingFile);
					printwriter.write(display.getText());
					printwriter.close();
					JOptionPane.showMessageDialog(frmTextEditor, "File saved", "Saved", JOptionPane.INFORMATION_MESSAGE);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					Logger.getLogger(Main_Frame.class.getName()).log(Level.SEVERE, null, e);
				}
			}
			display.setText("");
			currentEditingFile=null;
		}
		else if(option == JOptionPane.NO_OPTION){
			display.setText("");
			currentEditingFile=null;
		}
	}
	
	private void btnIncreaseFontActionPerformed(ActionEvent evt){
		Font f = display.getFont();
		String Name = f.getName();
		int size = f.getSize();
		int style = f.getStyle();
		if(size == 64)
			return;
		display.setFont(new Font(Name, style, ++size));
	}
	
	private void btnDecreaseFontActionPerformed(ActionEvent evt){
		Font f = display.getFont();
		String Name = f.getName();
		int size = f.getSize();
		int style = f.getStyle();
		if(size == 3)
			return;
		display.setFont(new Font(Name, style, --size));
	}
	
	private void btnExitActionPerformed(ActionEvent evt){
		int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to exit the program?", "Exit", JOptionPane.YES_NO_OPTION);
		if(confirm == JOptionPane.YES_OPTION){
			System.exit(0);
		}
	}
}
