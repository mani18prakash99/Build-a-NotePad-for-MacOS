package notepad;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Notepad extends JFrame implements ActionListener{
    
    JTextArea txt;
    String newTxt;
    
    
    Notepad(){
        setTitle("Notepad");
        //setIcon
        setBounds(60,60,600,500);
        //setExtendedState(JFrame.MAXIMIZED_BOTH);
        ImageIcon icon = new ImageIcon("notepad.png");
        Image i = icon.getImage();
        setIconImage(i);
        
        JMenuBar menubar = new JMenuBar();
        menubar.setBackground(Color.blue);
        
        JMenu file = new JMenu("File");
        
        
        
        JMenuItem newdoc = new JMenuItem("New");
        newdoc.addActionListener(this);
        newdoc.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
        
        JMenuItem open = new JMenuItem("Open");
        open.addActionListener(this);
        open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
        
        JMenuItem save = new JMenuItem("Save");
        save.addActionListener(this);
        save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
        
        JMenuItem print = new JMenuItem("Print");
        print.addActionListener(this);
        print.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
        
        JMenuItem exit = new JMenuItem("Exit");
        exit.addActionListener(this);
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
        
        
        file.add(newdoc);
        file.add(open);
        file.add(save);
        file.add(print);
        file.add(exit);
        
        menubar.add(file);
        
        JMenu edit = new JMenu("Edit");
        
        
        JMenuItem copy = new JMenuItem("Copy");
        copy.addActionListener(this);
        copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
        
        JMenuItem cut = new JMenuItem("Cut");
        cut.addActionListener(this);
        cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
        
        JMenuItem paste = new JMenuItem("Paste");
        paste.addActionListener(this);
        paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
        
        JMenuItem selectall = new JMenuItem("Select All");
        selectall.addActionListener(this);
        selectall.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
        
        edit.add(copy);
        edit.add(cut);
        edit.add(paste);
        edit.add(selectall);
        
        menubar.add(edit);
        
        JMenu helpmenu = new JMenu("Help");
        
        
        JMenuItem help = new JMenuItem("About");
        help.addActionListener(this);
        copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, Toolkit.getDefaultToolkit().getMenuShortcutKeyMaskEx()));
        
        helpmenu.add(help);
        
        menubar.add(helpmenu);
        
        txt = new JTextArea();
        //txt.setFont("SAN_SERIF",Font.PLAIN,18);
        //txt.setWrapStyleWord(true);
        add(txt);
        
        JScrollPane pane = new JScrollPane(txt);
        pane.setBorder(BorderFactory.createEmptyBorder());
        add(pane);
        
        
        setJMenuBar(menubar);      
        
        setVisible(true);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("New")){
            txt.setText("");
        }
        else if(e.getActionCommand().equals("Open")){
            JFileChooser chooser = new JFileChooser();
            chooser.setAcceptAllFileFilterUsed(false);
            FileNameExtensionFilter restrict = new FileNameExtensionFilter("Only .txt files","txt");
            
            chooser.addChoosableFileFilter(restrict);
            
            int action = chooser.showOpenDialog(this);
            if(action != JFileChooser.APPROVE_OPTION){   
                return;
            }
            
            
            File file = chooser.getSelectedFile();
            
            try{
                BufferedReader reader = new BufferedReader(new FileReader(file));
                txt.read(reader,null);
            }
            catch(Exception ea){
                ea.printStackTrace();
            }  
            
        }
        else if(e.getActionCommand().equals("Save")){
            JFileChooser saveas = new JFileChooser();
            saveas.setApproveButtonText("Save");
            
            int action = saveas.showOpenDialog(this);
            if(action != JFileChooser.APPROVE_OPTION){   
                return;
            }
            
            File filename = new File(saveas.getSelectedFile() + ".txt");
            BufferedWriter outFile = null;
            
            try{
                 outFile = new BufferedWriter(new FileWriter(filename));
                txt.write(outFile);
                
            }
            catch(Exception ee){
                ee.printStackTrace();
            }
            
        }
        else if(e.getActionCommand().equals("Print")){
            
            try{
                txt.print();
            }catch(Exception e1){
                e1.printStackTrace();
            }
        }
        else if(e.getActionCommand().equals("Exit")){
            System.exit(0);
        }
        else if(e.getActionCommand().equals("Copy")){
            newTxt = txt.getSelectedText();
        }
        else if(e.getActionCommand().equals("Paste")){
            txt.replaceRange("", txt.getSelectionStart(),txt.getSelectionEnd() );
        }
        else if(e.getActionCommand().equals("Cut")){
            newTxt = txt.getSelectedText();
        }
        else if(e.getActionCommand().equals("SelectAll")){
            txt.selectAll();
        }
        else if(e.getActionCommand().equals("About")){
            new About().setVisible(true);
        }
    }

    public static void main(String[] args) {
        // TODO code application logic here
        Notepad notepad = new Notepad();
        
    }

    
    
}