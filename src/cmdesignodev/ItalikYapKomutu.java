/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cmdesignodev;

/**
 *
 * @author lenovo
 */
public class ItalikYapKomutu implements Komut{
    DosyaIslem dosyaIslem;
   
    public ItalikYapKomutu(DosyaIslem dosyaIslem){
        this.dosyaIslem = dosyaIslem;
    }
    
    @Override
    public void calistir() {
        dosyaIslem.italikYap();
    }

}
