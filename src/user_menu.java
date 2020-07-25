import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import net.proteanit.sql.DbUtils;

public class user_menu {
	public static void Show_UserMenu(String UID) throws IOException {
		JFrame f_user=new JFrame("User Functions"); //Give dialog box name as User functions
	    //f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Exit user menu on closing the dialog box
	    
		BufferedImage image = ImageIO.read(new File("src/image/logo2.png"));;
        JLabel Logo = new JLabel(new ImageIcon(image));
		Logo.setBounds(300,10,280,120);
		
		JButton viewM_button=new JButton("View Movies");//creating instance of JButton  
	    viewM_button.setBounds(20,20,120,25);//x axis, y axis, width, height 
	    viewM_button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				JFrame f_Available = new JFrame("Movies Available"); 
	            //f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				Connection connection = ConnectDB.connect(); //connect to database
	            String sql="select * from MOVIE"; //select all movies
	            try {
	            	Statement stmt = connection.createStatement();
	                stmt.executeUpdate("USE MOVIES"); //use database
	                stmt=connection.createStatement();
	                ResultSet result = stmt.executeQuery(sql);
	                JTable movie_list= new JTable(); //view data in table format
	                movie_list.setModel(DbUtils.resultSetToTableModel(result)); 
	                //mention scroll bar
	                JScrollPane scrollPane = new JScrollPane(movie_list); 
	 
	                f_Available.add(scrollPane); //add scrollpane
	                f_Available.setSize(800, 400); //set size for frame
	                f_Available.setVisible(true);
	                f_Available.setLocationRelativeTo(null);	            	
	            }
	            catch (SQLException e1) {
	                 JOptionPane.showMessageDialog(null, e1);
	            }    
			} 
	    	
	    });
	    
	    JButton add_movie=new JButton("Add Movie"); //creating instance of JButton for adding movies
	    add_movie.setBounds(20,60,120,25); 
	     
	    add_movie.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//set frame to enter movie details
                JFrame Movie_Detail = new JFrame("Enter Movie Details");
                //g.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                // set labels
                JLabel l1,l2,l3;  
                l1=new JLabel("Movie Name");  //lebel 1 for movie name
                l1.setBounds(30,15, 100,30); 
                 
                l2=new JLabel("Date watched");  //label 2 for Date watched
                l2.setBounds(30,53, 100,30); 
                 
                l3=new JLabel("Movie/Series");  //label 3 for Movie/Series
                l3.setBounds(30,90, 100,30); 
                 
                //set text field for movie name
                JTextField movie_name = new JTextField();
                movie_name.setBounds(110, 15, 200, 30);
                 
                //set text field for genre 
                JTextField Date_watch =new JTextField();
                Date_watch.setBounds(110, 53, 200, 30);
                //set text field for type
                JTextField movie_type=new JTextField();
                movie_type.setBounds(110, 90, 200, 30);
                
                JButton Submit_button=new JButton("Submit");//creating instance of JButton to submit details  
                Submit_button.setBounds(130,130,80,25);//x axis, y axis, width, height 
                Submit_button.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// assign the name, date, type
	                    String name = movie_name.getText();
	                    String date = Date_watch.getText();
	                    String type  = movie_type.getText();
	                    
	                    Connection connection = ConnectDB.connect(); //connect to database
	                    
	                    try {
	                         Statement stmt = connection.createStatement();
	                         stmt.executeUpdate("USE MOVIES");
	                         stmt.executeUpdate("INSERT INTO MOVIE(NAME,DATE_WATCHED,TYPE) VALUES ('"+name+"','"+date+"',"+type+")");
	                         JOptionPane.showMessageDialog(null,"Movie added!");
	                         Movie_Detail.dispose();
	                        }
	                        catch (SQLException e1) {
	                             JOptionPane.showMessageDialog(null, e1);
	                        }
					}
                	
                });
                Movie_Detail.add(l3);
                Movie_Detail.add(Submit_button);
                Movie_Detail.add(l1);
                Movie_Detail.add(l2);
                Movie_Detail.add(movie_name);
                Movie_Detail.add(Date_watch);
                Movie_Detail.add(movie_type);
                Movie_Detail.setSize(350,200);//400 width and 500 height  
                Movie_Detail.setLayout(null);//using no layout managers  
                Movie_Detail.setVisible(true);//making the frame visible 
                Movie_Detail.setLocationRelativeTo(null);
                     
			}
	    	
	    });
	    
	    JButton delete_movie=new JButton("Delete Movie"); //creating instance of JButton for adding movies
	    delete_movie.setBounds(20,100,120,25); 
	     
	    delete_movie.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//set frame to enter movie details
                JFrame Movie_Detail = new JFrame("Enter Movie Details");
                //g.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                // set labels
                JLabel l1,l2,l3;  
                l1=new JLabel("Name");  //lebel 1 for movie name
                l1.setBounds(15,15, 100,30); 
                 
                l2=new JLabel("Date watched");  //label 2 for Date watched
                l2.setBounds(15,53, 100,30); 
                 
                l3=new JLabel("Movie/Series");  //label 3 for Movie/Series
                l3.setBounds(15,90, 100,30); 
                 
                //set text field for movie name
                JTextField movie_name = new JTextField();
                movie_name.setBounds(130, 15, 200, 30);
                 
                //set text field for date watched
        		UtilDateModel model = new UtilDateModel();
        		model.setSelected(true);
        		
        		//Use datapicker library allownig to pick date from calender
        		JDatePanelImpl datePicker = new JDatePanelImpl(model, new Properties());
        		JDatePickerImpl Date_watch = new JDatePickerImpl(datePicker, new DateLabelFormatter());
                
//                JFormattedTextField Date_watch =new JFormattedTextField(DateFormat.getDateInstance(DateFormat.MEDIUM));
//                Date_watch.setValue(new Date());
                Date_watch.setBounds(130, 53, 200, 30);
                //set text field for type
                String typeStrings[] = { "Series", "Movie" };
                //Create the combo box, Indices start at 0, so 1 specifies the movies.
                JComboBox movie_type = new JComboBox(typeStrings);
                movie_type.setBounds(130, 91, 200, 30);  

                JButton Submit_button=new JButton("Delete");//creating instance of JButton to submit details  
                Submit_button.setBounds(130,130,80,25);//x axis, y axis, width, height 
                Submit_button.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						// assign the name
	                    String name = movie_name.getText();
	                    //Assign and adjust date fromat
	                    java.util.Date dateUtil = (Date) Date_watch.getModel().getValue();
	                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	                    String date = formatter.format(dateUtil);
	                    // assign type
	                    String type  = (String) movie_type.getSelectedItem();
	                    
	                    Connection connection = ConnectDB.connect(); //connect to database
	                    
	                    try {
	                         Statement stmt = connection.createStatement();
	                         stmt.executeUpdate("USE MOVIES");
	                         stmt.executeUpdate("DELETE FROM MOVIE WHERE NAME = '"+name+"' AND DATE_WATCHED = '"+date+"' ");
	                         JOptionPane.showMessageDialog(null,"Movie deleted!");
	                         Movie_Detail.dispose();
	                        }
	                        catch (SQLException e1) {
	                             JOptionPane.showMessageDialog(null, e1);
	                        }
					}
                	
                });
                Movie_Detail.add(l3);
                Movie_Detail.add(Submit_button);
                Movie_Detail.add(l1);
                Movie_Detail.add(l2);
                Movie_Detail.add(movie_name);
                Movie_Detail.add(Date_watch);
                Movie_Detail.add(movie_type);
                Movie_Detail.setSize(350,200);//400 width and 500 height  
                Movie_Detail.setLayout(null);//using no layout managers  
                Movie_Detail.setVisible(true);//making the frame visible 
                Movie_Detail.setLocationRelativeTo(null);
                     
			}
	    	
	    });


	    f_user.add(viewM_button); // view movies
	    f_user.add(add_movie); // add movies
	    f_user.add(delete_movie); // delete movies
	    f_user.add(Logo); // add logo
	    f_user.setSize(600,200);//400 width and 500 height  
	    f_user.setLayout(null);//using no layout managers  
	    f_user.setVisible(true);//making the frame visible 
	    f_user.setLocationRelativeTo(null);
	    
	}
}
