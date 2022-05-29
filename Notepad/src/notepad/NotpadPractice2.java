/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package notepad;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;

/**
 *
 * @author Makrand Mandhare
 */
public class NotpadPractice2 implements ActionListener
{
 NotpadPractice2()
 {
     try
     {
     UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
     }
     catch(Exception e)
     {
         System.out.println(e);
     }
     JFrame jf=new JFrame("*Untitled*-Notpad");
     jf.setSize(600,500);
     jf.setDefaultCloseOperation(jf.EXIT_ON_CLOSE);
     jf.setLocationRelativeTo(null);
     
     JMenuBar jb=new JMenuBar();
     
     JMenu file=new JMenu("File");
     JMenuItem neww=new JMenuItem("New");
     file.add(neww);
     
     JMenuItem open=new JMenuItem("Open");
     file.add(open);
     
     JMenuItem save=new JMenuItem("Save");
     file.add(save);
     
     JMenuItem saveas=new JMenuItem("Save As");
     file.add(saveas);
    
     jb.add(file);
     
     JMenu edit=new JMenu("Edit");
     JMenuItem cut=new JMenuItem("Cut");
     edit.add(cut);
     
     JMenuItem paste=new JMenuItem("Paste");
     edit.add(paste);
     
     JMenuItem copy=new JMenuItem("Copy");
     edit.add(copy);
     
     jb.add(edit);
     
     JMenu format=new JMenu("Format");
     JMenuItem font=new JMenuItem("Font");
     format.add(font);
     
     JMenuItem fontcolour=new JMenuItem("Font Colour");
     format.add(fontcolour);
     
     JMenuItem background_colour=new JMenuItem("Background Colour");
     format.add(background_colour);
     
     jb.add(format);
     
     jf.setJMenuBar(jb);
     
     JTextArea textarea=new JTextArea();
     
     JScrollPane scrollpane=new JScrollPane(textarea);
     scrollpane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
     scrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
     
     jf.add(scrollpane);
     
     jf.setVisible(true);
 }
    public static void main(String[] args) 
    {
      new NotpadPractice2();
    }

    @Override
    public void actionPerformed(ActionEvent arg0) 
    {
   
    }
}
