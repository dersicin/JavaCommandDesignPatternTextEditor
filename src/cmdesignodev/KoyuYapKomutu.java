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
public class KoyuYapKomutu implements Komut{
    DosyaIslem dosyaIslem;
   
    public KoyuYapKomutu(DosyaIslem dosyaIslem){
        this.dosyaIslem = dosyaIslem;
    }
    
    @Override
    public void calistir() {
        dosyaIslem.koyuYap();
    }

}
