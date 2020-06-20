/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cmdesignodev;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.BadLocationException;

/**
 *
 * @author lenovo
 */
public class DosyaIslem {
    CmdDesignOdev deneme;
    int dot ;
    int mark;
    DosyaIslem(CmdDesignOdev deneme) {
        this.deneme = deneme;
        this.dot = deneme.dot;
        this.mark = deneme.mark;
    }

    void dosyaAc() {
          FileInputStream fis = null;
        try {
            String path = "";
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "txt", "txt");
            chooser.setFileFilter(filter);
            int returnVal = chooser.showOpenDialog(null);
            if(returnVal == JFileChooser.APPROVE_OPTION) {
                File selectedFile = chooser.getSelectedFile();
                System.out.println("Bu dosyayı açmak istedin: " +
                        selectedFile.getAbsolutePath());
                path = selectedFile.getAbsolutePath();
            }
            deneme.textPane.setText("");
            File file = new File(path);
            fis = new FileInputStream(file);
            InputStreamReader isr = new InputStreamReader(fis);
            try (BufferedReader br = new BufferedReader(isr)) {
                String line;
                while((line = br.readLine()) != null){
                    //process the line
                    deneme.textPane.setText(deneme.textPane.getText()+line);
                    deneme.textPane.setText(deneme.textPane.getText()+"\n");
                }
            } catch (IOException ex) {
                Logger.getLogger(DosyaIslem.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(DosyaIslem.class.getName()).log(Level.SEVERE, null, ex);
        }
finally {
            try {
                fis.close();
            } catch (IOException ex) {
                Logger.getLogger(DosyaIslem.class.getName()).log(Level.SEVERE, null, ex);
            }
        }    }
    

    void yeniDosya() {
        deneme.textPane.setText("");
    }

    void dosyaKaydet() {
                try {
            JFileChooser chooser = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "txt", "txt");
            chooser.setFileFilter(filter);
            int returnVal = chooser.showSaveDialog(null);
            if(returnVal == JFileChooser.APPROVE_OPTION) {
                FileWriter fw = new FileWriter(chooser.getSelectedFile()+".txt");
                fw.write(deneme.textPane.getText().toString());
                fw.close();
            }
    }   catch (IOException ex) {
            Logger.getLogger(DosyaIslem.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }

    void koyuYap() {
        boldSelectedText(mark, dot);
    }

    void italikYap() {
        italicSelectedText(mark, dot);
    }
    
    private void boldSelectedText(int mark, int dot) {
            try {
                int length = dot - mark;
                String s = deneme.styledDocument.getText(mark, length);
                deneme.styledDocument.remove(mark, length);
                deneme.styledDocument.insertString(mark, s,
                        deneme.styledDocument.getStyle("bold"));
            } catch (BadLocationException e) {
                e.printStackTrace();
            }
        }
            
     private void italicSelectedText(int mark, int dot){
                            try {
                int length = dot - mark;
                String s = deneme.styledDocument.getText(mark, length);
                deneme.styledDocument.remove(mark, length);
                deneme.styledDocument.insertString(mark, s,
                        deneme.styledDocument.getStyle("italic"));
            } catch (BadLocationException e) {
                e.printStackTrace();
            }
            }
            
    void geriAl(){
                if(deneme.undoManager.canUndo()){
                deneme.undoManager.undo();
             }
    }
}
