import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login {
	public static void login() {
		JFrame f_login = new JFrame("MovieBase Login");//creating instance of JFrame. The login screen
		JLabel l1,l2; 
		l1=new JLabel("Username");  //Create label Username on the screen
		l1.setBounds(30,15, 100,30); //x axis, y axis, width, height
		l2=new JLabel("Password");  //Create label Password on the screen
		l2.setBounds(30,50, 100,30); //x axis, y axis, width, height
		
		JTextField In_user = new JTextField(); //Create input text field for username
		In_user.setBounds(110, 15, 200, 30);
		JPasswordField In_password = new JPasswordField(); //Create input text field for password
		In_password.setBounds(110, 50, 200, 30);
		
		JButton login_Button =new JButton("Login");//creating instance of JButton for Login Button
		login_Button.setBounds(130,90,80,25);//Dimensions for button
 
		// Easiest way to press submit by pressing enter. Each frame can set its default event key like this.
		f_login.getRootPane().setDefaultButton(login_Button); 
		
		login_Button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String username = In_user.getText(); //Store username entered by the user in the variable "username"
		        String password = In_password.getText(); //Store password entered by the user in the variable "password"

		        if(username.equals("")) {
		        	JOptionPane.showMessageDialog(null, "Please enter username");  //Display dialog message
		        }
		        else if(password.equals("")) {
		        	JOptionPane.showMessageDialog(null, "Please enter password");  //Display dialog message
		        }
		        else { // if both pass and username are entered
		        	System.out.println("Login connect");
		        	Connection connection = ConnectDB.connect();  //Connect to the database

			        try {
			        	Statement stmt = connection.createStatement(
			        	    ResultSet.TYPE_SCROLL_SENSITIVE,
			        	    ResultSet.CONCUR_READ_ONLY); //Make the cursor in result move back and forth
			        	stmt.executeUpdate("USE MOVIES"); //Use the database with the name "MOVIES"
			              String st = ("SELECT * FROM USERS WHERE USERNAME='"+username+"' AND PASSWORD='"+password+"'"); 
			              //Retreive username and passwords from users
			              ResultSet result = stmt.executeQuery(st); //Execute query
			              if(result.next()==false) { //Move pointer below
			                  System.out.print("No user");  
			                  JOptionPane.showMessageDialog(null,"Wrong Username/Password!"); //Display Message
			              }
			              else {
			            	  f_login.dispose(); //JFrame window to be destroyed and cleaned up by the operating system
			            	  result.beforeFirst();  //Move the pointer above
			                while(result.next()) { //Loop until finding the SQL result
			                  String admin = result.getString("ADMIN"); //user is admin
			                  //System.out.println(admin);
			                  String UID = result.getString("USERNAME"); //Get user ID of the user
			                  if(admin.equals("1")) { //If boolean value 1
			                	  admin_menu.Show_AdminMenu(); //redirect to admin menu
			                  }
			                  else{
			                	  user_menu.Show_UserMenu(UID); //redirect to user menu for that user ID
			                  }
			              }
			              }			          
			        }
			        catch (Exception ex) {
		                 ex.printStackTrace();
		            }
		        }
		        
			}
		  });

		f_login.add(In_password); //add password
		f_login.add(login_Button);//adding button in JFrame  
		f_login.add(In_user);  //add user
		f_login.add(l1);  // add label1 i.e. for username
		f_login.add(l2); // add label2 i.e. for password
	     
		f_login.setSize(400,180);//400 width and 500 height  
		f_login.setLayout(null);//using no layout managers  
		f_login.setVisible(true);//making the frame visible 
		f_login.setLocationRelativeTo(null);			
		}
	}
