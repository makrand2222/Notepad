 
package notepad;
import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.io.File;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;

public class Notepad implements ActionListener
{
    JFrame jf,font_frame;
    JComboBox font_family,font_size,font_style;
    JButton okay;
    File file;
    JTextArea textarea;
    JMenuItem neww,save,saveas,open,font,cut,paste,copy,font_colour,background_colour;
    Notepad()
   
    {
        try
        {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        }
        catch(Exception ce)
        {
            ce.printStackTrace();
        }

        jf=new JFrame("*Untitled*- Notepad");
        jf.setSize(700, 600);
        jf.setDefaultCloseOperation(jf.EXIT_ON_CLOSE);
        jf.setLocationRelativeTo(null);
        
        JMenuBar jmenubar=new JMenuBar();
        
        JMenu file=new JMenu("File");
        neww=new JMenuItem("New");
        neww.addActionListener(this);
        file.add(neww);
        
        open= new JMenuItem("Open");
        open.addActionListener(this);
        file.add(open);
        
        save=new JMenuItem("Save");
        save.addActionListener(this);
        file.add(save);
        
        saveas=new JMenuItem("Save as...");
        saveas.addActionListener(this);
        file.add(saveas); 
        
        jmenubar.add(file);
        
        JMenu edit=new JMenu("Edit");
        
        cut=new JMenuItem("Cut");
        cut.addActionListener(this);
        edit.add(cut);
        
        copy=new JMenuItem("Copy");
        copy.addActionListener(this);
        edit.add(copy);
        
        paste=new JMenuItem("Paste");
        paste.addActionListener(this);
        edit.add(paste);
        jmenubar.add(edit);
        
        JMenu format=new JMenu("Format");
        font=new JMenuItem("Font");
        font.addActionListener(this);
        format.add(font);
        jmenubar.add(format);
        
        font_colour=new JMenuItem("Font Colour");
        font_colour.addActionListener(this);
        format.add(font_colour);
        
        background_colour=new JMenuItem("Background Colour");
        background_colour.addActionListener(this);
        format.add(background_colour);
        
        jf.setJMenuBar(jmenubar);
        
        textarea=new JTextArea();
        
        JScrollPane scrollpane=new JScrollPane(textarea);
        scrollpane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        
        jf.add(scrollpane);
        
        jf.setVisible(true);
    }
        
    public static void main(String[] args)
    {
        new Notepad();   
    }

    @Override
    public void actionPerformed(ActionEvent e) 
    {
        if(e.getSource()==neww)
        {
            newFile();
        }
        if(e.getSource()==open)
        {
             openFile();       
        }
            
        if(e.getSource()==saveas)
        {
            saveasfile();
        }
        if(e.getSource()==save)
        {
         saveFile();
        }
        
        if(e.getSource()==cut)
        {
            textarea.cut();
        }
        if(e.getSource()==copy)
        {
            textarea.copy();
        }
        if(e.getSource()==paste)
        {
            textarea.paste();
        }
        if(e.getSource()==font)
        {
            openFontFrame();
        }
        if(e.getSource()==okay)
        {
         okayButtonCode();
        }
        if(e.getSource()==font_colour)
        {
            Color c=JColorChooser.showDialog(jf, "Choose font colour", Color.white);
            textarea.setForeground(c);
        }
        if(e.getSource()==background_colour)
        {
            Color c=JColorChooser.showDialog(jf, "Choose background colour", Color.gray);
            textarea.setBackground(c);
        }
    }
        void okayButtonCode()
        {
            String fontfamily = (String)font_family.getSelectedItem();
            String fontsize   = (String)font_size.getSelectedItem();
            String fontstyle  = (String)font_style.getSelectedItem();
            System.out.println(fontfamily+" "+fontsize+" "+fontstyle); 
            int style=0;
            if(fontstyle.equals("Plain"))
            {
                style=0;
            }
            if(fontstyle.equals("Bold"))
            {
                style=1;
            }
            if(fontstyle.equals("Italic"))
            {
                style=2;
            }
            Font fontt=new Font(fontfamily, style, Integer.parseInt(fontsize));
            textarea.setFont(fontt);
            
            font_frame.setVisible(false);
     
        }
        void openFontFrame()
        {
            font_frame=new JFrame("Choose Font");
            font_frame.setSize(500, 500);
            font_frame.setLocationRelativeTo(jf);
            font_frame.setLayout(null);
            
            String fonts[]=GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
            font_family=new JComboBox(fonts);
            font_family.setBounds(50, 100, 100, 30);
            font_frame.add(font_family);
            
            String[] sizes={"10","12","14","16","18","20","22","24","26","28","30"};
            font_size=new JComboBox(sizes);
            font_size.setBounds(200, 100, 100, 30);
            font_frame.add(font_size);
            
            String[] Styles={"Bold","Italic","Plain"};
            font_style=new JComboBox(Styles);
            font_style.setBounds(350, 100, 100, 30);
            font_frame.add(font_style);
            
            okay=new JButton("Okay");
            okay.setBounds(200, 300, 100, 50);
            okay.addActionListener(this);
            font_frame.add(okay);
            
            font_frame.setVisible(true);
        }
        void saveFile()
         {
             String title=jf.getTitle();
            if(title.equals("*Untitled*- Notepad"))
            {
                saveasfile();
            }
            else
            {
                String text=textarea.getText();
                try(FileOutputStream fos=new FileOutputStream(file))
                 {
                   byte[] b=text.getBytes();
                   fos.write(b);
                 }
                catch(IOException ee)
                 {
                    ee.printStackTrace();
                 }   
            }
         }
    
    void openFile()
    {
        JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(jf);
            if(result==0)
            {
                textarea.setText("");
                File file= fileChooser.getSelectedFile();
                 jf.setTitle(file.getName());
            try
               {
                 FileInputStream fis=new FileInputStream(file);
                 int i;
                 
                 while((i=fis.read()) !=-1)
                    {
                        textarea.append(String.valueOf((char)i));
                    }
                }
            catch(Exception ee)
            {
              ee.printStackTrace();
            }
            }
    }
        
    void saveasfile()
    {
        JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showSaveDialog(jf);
            if(result==0)
            {
                String text=textarea.getText();
                file=fileChooser.getSelectedFile();
                jf.setTitle(file.getName());
         try(FileOutputStream fos=new FileOutputStream(file))
            {
            byte[] b=text.getBytes();
            fos.write(b);
            }
        catch(IOException ee)
                {
                    ee.printStackTrace();
                }
            
            }
    }
    void newFile()
    {
        String text=textarea.getText();
          
            if(!text.equals(""))
            {
               int i=JOptionPane.showConfirmDialog(jf, "Do you want to save this file?");
            
               if(i==0)
               {
                saveasfile();
                textarea.setText("");
                jf.setTitle("*Untitled*- Notepad");
               }
               else if(i==1)
               {
                   textarea.setText("");
                
               }
            }
    }
}
