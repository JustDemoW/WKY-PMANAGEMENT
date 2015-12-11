package com.sys.pm.util;
import java.security.*;  
import javax.crypto.*;  
import sun.misc.*;
/**       
    *   ʹ��DES���������,�ɶ�byte[],String���ͽ��м��������  
    *   ���Ŀ�ʹ��String,byte[]�洢.   
    *   ����:  
    *   void getKey(String   strKey)��strKey����������һ��Key     
    *   String getEncString(String strMing)��strMing���м���,����String����  
    *   String getDesString(String strMi)��strMin���н���,����String����  
    *   byte[] getEncCode(byte[] byteS)byte[]�͵ļ���  
    *   byte[] getDesCode(byte[] byteD)byte[]�͵Ľ���  
    */      
public class EncryptUtil{
  private Key key;
  private byte[] byteMi = null;
  private byte[] byteMing = null;
  private String strMi= "";
  private String strM= ""; 
  //  ���ݲ�������KEY   
  public void setKey(String strKey){ 
   try{  
        KeyGenerator _generator = KeyGenerator.getInstance("DES");  
        _generator.init(new SecureRandom(strKey.getBytes()));  
        this.key = _generator.generateKey();  
        _generator=null;
        }
    catch(Exception e){
     e.printStackTrace();
     }
   
    }  
  //  ����String��������,String�������  
  public void setEncString(String strMing){
   BASE64Encoder base64en = new BASE64Encoder();  
    try {
     this.byteMing = strMing.getBytes("UTF8");  
      this.byteMi = this.getEncCode(this.byteMing);  
      this.strMi = base64en.encode(this.byteMi);
     }  
    catch(Exception e)
    {
     e.printStackTrace();
     }
   finally
     {
 
      this.byteMing = null;  
      this.byteMi = null;
      }
  }  
  //������byte[]��������,byte[]�������    
  private byte[] getEncCode(byte[] byteS){
   byte[] byteFina = null;  
    Cipher cipher;  
    try
     {
      cipher = Cipher.getInstance("DES");  
      cipher.init(Cipher.ENCRYPT_MODE,key);  
      byteFina = cipher.doFinal(byteS);
      }  
    catch(Exception e)
     {
      e.printStackTrace();
      }  
    finally
    {
     cipher = null;
     }
       
   return byteFina;
  } 
// ����:��String��������,String�������   
  public void setDesString(String strMi){  
   BASE64Decoder base64De = new BASE64Decoder();   
    try
    {
     this.byteMi = base64De.decodeBuffer(strMi);  
      this.byteMing = this.getDesCode(byteMi);  
      this.strM = new String(byteMing,"UTF8");  
      }  
    catch(Exception e)
     {
      e.printStackTrace();
      }  
    finally
     {
      base64De = null;  
      byteMing = null;  
      byteMi = null;
      }  
  
  }
  // ������byte[]��������,��byte[]�������    
 private byte[] getDesCode(byte[] byteD){
   Cipher cipher;  
    byte[] byteFina=null;  
    try{
     cipher = Cipher.getInstance("DES");  
      cipher.init(Cipher.DECRYPT_MODE,key);  
      byteFina = cipher.doFinal(byteD);
      }
   catch(Exception e)
     {
      e.printStackTrace();
      }
   finally
     {
      cipher=null;
      }  
    return byteFina;
  } 
  //���ؼ��ܺ������strMi  
  public String getStrMi()
  {
   return strMi;
  }
  //���ؽ��ܺ������
  public String getStrM()
  {
   return strM;
  }
 }  