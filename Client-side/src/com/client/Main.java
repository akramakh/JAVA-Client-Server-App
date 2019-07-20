
package com.client;
import java.io.*;
import java.net.*; 
import javax.swing.*;
import java.awt.*; 
import java.awt.event.*;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author Akram A Ak
 */
public class Main extends JFrame{
private static Socket s;
private static DataInputStream dis;
private static DataOutputStream dos;
private static JTextArea msg_area ;
private static JTextField msg_text;
private static JButton send_btn;
private static JButton close_btn;
private static JButton exit_btn;
private static JScrollPane sp;
 
 public Main(){
  super("Client-side");
 setSize(520,550);
 setLayout(null); 
 setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE); 
 setVisible(true); 
 //setResizable(false);
  msg_area = new JTextArea();
  msg_text=new JTextField(); 
 send_btn=new JButton("Send");
  close_btn=new JButton("Close Chat");
  exit_btn=new JButton("EXIT");
   sp=new JScrollPane();
 msg_area.setBounds(5,5,490,400); 
 msg_text.setBounds(5,415,490,40);
 send_btn.setBounds(5,465,100,30);
 close_btn.setBounds(115,465,110,30);
 exit_btn.setBounds(390,465,100,30); 
 send_btn.setBackground(Color.blue); 
 send_btn.setForeground(Color.white); 
 exit_btn.setBackground(Color.red);
 exit_btn.setForeground(Color.white);
 sp.setViewportView(msg_area); 
 msg_area.setEditable(false); 
 add(msg_area); add(msg_text);
 add(send_btn); add(close_btn);
 add(exit_btn);
send_btn.addActionListener(new ActionListener(){ 
            public void actionPerformed(ActionEvent e){ 
    String msgout ="";
 try{ 
    msgout=msg_text.getText().trim();
    dos.writeUTF("Client |   "+msgout);
    msg_area.setText(msg_area.getText().trim()+"\n"+"      Me |   "+msgout);
    msg_text.setText("");
}catch(Exception ex){
      JOptionPane.showMessageDialog(sp, "Thier is no Connection Opened !!!");
 }}}
);
close_btn.addActionListener(new ActionListener(){ 
            public void actionPerformed(ActionEvent e)  {
            try {
                s.close();
            } catch (IOException ex) {
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
            }});
exit_btn.addActionListener(new ActionListener(){ 
            public void actionPerformed(ActionEvent e){ 
System.exit(0);
            }});
}
 
    public static void main(String[] args) {
        // TODO code application logic here
         try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        Main c=new Main();
        String msgin=""; 
        try{
             s=new Socket("127.0.0.1",2222);
            dis =new DataInputStream(s.getInputStream());
            dos =new DataOutputStream(s.getOutputStream()); 
            while(true){ 
                msgin=dis.readUTF(); 
        msg_area.setText(msg_area.getText().trim()+"\n"+msgin); 
            }
        }catch(Exception e){
            e.printStackTrace(); 
            }
    }
    
    
}
