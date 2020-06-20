/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cmdesignodev;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.text.DefaultStyledDocument;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;
import javax.swing.undo.UndoManager;

/**
 *
 * @author lenovo
 */
public class CmdDesignOdev extends JFrame implements ActionListener{
 StyledDocument styledDocument;
    public static CmdDesignOdev deneme;
    JTextPane textPane  = new JTextPane();
    JScrollPane jsp = new JScrollPane(textPane,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    JMenuBar menuBar;
    JMenu menu;
    JMenuItem dosyaAc;
    JMenuItem yeniDosya;
    JMenuItem dosyaKaydet;
    JMenuItem dosyaPaylas;
    JButton undo;
    JButton redo;
    int dot;
    int mark;
    UndoManager undoManager;
    public CmdDesignOdev(){
        textPane.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent me) {
                textPane.setEditable(true);
            }

            @Override
            public void mousePressed(MouseEvent me) {
                textPane.setEditable(false);
            }

            @Override
            public void mouseReleased(MouseEvent me) {
                
            }

            @Override
            public void mouseEntered(MouseEvent me) {

            }

            @Override
            public void mouseExited(MouseEvent me) {

            }
        });
        textPane.addCaretListener(new SelectedText());   
        styledDocument = new DefaultStyledDocument();
        addStylesToDocument(styledDocument);
        textPane.setStyledDocument(styledDocument);
        textPane.setVisible(true);
        textPane.setText("Bir Dosya Seçmek İçin Dosya Aç Butonuna Tıklayınız.");
        textPane.setFont(new Font("Times New Roman" , Font.PLAIN , 15));
        add(jsp);
        
        setSize(500, 500);
        setTitle("Dosya Okuma Programı");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //Menüyü kurma.
        menuBar = new JMenuBar();
        menu = new JMenu("Dosya");    
        dosyaAc = new JMenuItem("Dosya Aç");
        dosyaAc.addActionListener(this);
        yeniDosya = new JMenuItem("Yeni Dosya");
        yeniDosya.addActionListener(this);
        dosyaKaydet = new JMenuItem("Dosya Kaydet");
        dosyaKaydet.addActionListener(this);
        dosyaPaylas = new JMenuItem("Paylaş");
        dosyaPaylas.addActionListener(this);
        menu.add(dosyaAc);
        menu.add(yeniDosya);
        menu.add(dosyaKaydet);
        menu.add(dosyaPaylas);
        menuBar.add(menu);
        add(menuBar,BorderLayout.NORTH);
        
        undo = new JButton("Geri Al");
        undo.addActionListener(this);
        add(undo,BorderLayout.SOUTH);
        
        undoManager = new UndoManager();
        textPane.getDocument().addUndoableEditListener(new UndoableEditListener() {
            @Override
            public void undoableEditHappened(UndoableEditEvent uee) {
                undoManager.addEdit(uee.getEdit());
            }
        });
        
        setVisible(true);
    }
    
    public static void main(String[] args) {
        deneme = new CmdDesignOdev();              
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
         if(ae.getSource().equals(dosyaAc))
        {
            //dosya seçme işlemini yapıyor.
            DosyaIslem dosyaIslem = new DosyaIslem(deneme);
            DosyaAcKomutu dosyaAcKomut = new DosyaAcKomutu(dosyaIslem);
            KumandaButon kumanda = new KumandaButon(dosyaAcKomut);
            kumanda.tusaBas();
            }
         else if(ae.getSource().equals(yeniDosya)){
            DosyaIslem dosyaIslem = new DosyaIslem(deneme);
            YeniDosyaKomutu yeniDosyaKomut = new YeniDosyaKomutu(dosyaIslem);
            KumandaButon kumanda = new KumandaButon(yeniDosyaKomut);
            kumanda.tusaBas();
         }
         else if(ae.getSource().equals(dosyaKaydet)){
            DosyaIslem dosyaIslem = new DosyaIslem(deneme);
            DosyaKaydetKomutu dosyaKaydetKomutu = new DosyaKaydetKomutu(dosyaIslem);
            KumandaButon kumanda = new KumandaButon(dosyaKaydetKomutu);
            kumanda.tusaBas();
         }
         else if(ae.getSource().equals(undo)){
             DosyaIslem dosyaIslem = new DosyaIslem(deneme);
             dosyaIslem.geriAl();
         }
        }


    private class SelectedText implements CaretListener {

        @Override
        public void caretUpdate(CaretEvent event) {
             dot = event.getDot();
             mark = event.getMark();
            if (dot != mark) {
                if (dot < mark) {
                    int temp = dot;
                    dot = mark;
                    mark = temp;
                }
                textPane.addKeyListener(new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent ke) {
                        
                    }

                    @Override
                    public void keyPressed(KeyEvent ke) {
                        
                    }

                    @Override
                    public void keyReleased(KeyEvent ke) {
                            if(ke.getKeyCode()==KeyEvent.VK_K){
                            
                            DosyaIslem dosyaIslem = new DosyaIslem(deneme);
                            KoyuYapKomutu koyuYapKomut = new KoyuYapKomutu(dosyaIslem);
                            KumandaButon kumanda = new KumandaButon(koyuYapKomut);
                            kumanda.tusaBas();
                            
                            textPane.setEditable(true);
                        }
                            else if(ke.getKeyCode() == KeyEvent.VK_E){
                                
                            DosyaIslem dosyaIslem = new DosyaIslem(deneme);
                            ItalikYapKomutu italikYapKomut = new ItalikYapKomutu(dosyaIslem);
                            KumandaButon kumanda = new KumandaButon(italikYapKomut);
                            kumanda.tusaBas();
                            
                            textPane.setEditable(true);
                        }       
                    }
                });     
            }
        }  
    }
        private void addStylesToDocument(StyledDocument styledDocument) {
        Style def = StyleContext.getDefaultStyleContext().getStyle(
                StyleContext.DEFAULT_STYLE);
        Style s = styledDocument.addStyle("bold", def);
        Style s2 = styledDocument.addStyle("italic", def);
        StyleConstants.setBold(s, true);
        StyleConstants.setItalic(s2, true);
    }

        
}
