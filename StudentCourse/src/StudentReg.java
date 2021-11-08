import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import net.proteanit.sql.DbUtils;

import javax.swing.border.EtchedBorder;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.JButton;
import java.sql.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
public class StudentReg {

	private JFrame frame;
	private JTextField txtfname;
	private JTextField txtlname;
	private JTextField txtemail;
	private JTextField txtmobnumber;
	private JTextField txtcourse;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StudentReg window = new StudentReg();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public StudentReg() {
		initialize();
		Connect();
		table_load();
	}

	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	private JTable table;
	private JTextField txtSid;
	
	public void Connect()
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/Student_reg","root","");
		}
		catch (ClassNotFoundException ex)
		{
			
		}
		catch (SQLException ex)
		{
			
		}
	}
	
	public void table_load()
    {
     try
     {
    pst = con.prepareStatement("select * from studentinfo");
    rs = pst.executeQuery();
    table.setModel(DbUtils.resultSetToTableModel(rs));
}
     catch (SQLException e)
     {
     e.printStackTrace();
  }
    }
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 910, 557);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Student Course Registration");
		lblNewLabel.setFont(new Font("Arial Black", Font.BOLD | Font.ITALIC, 25));
		lblNewLabel.setBounds(261, 11, 414, 30);
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Registration", TitledBorder.LEADING, TitledBorder.ABOVE_TOP, null, Color.RED));
		panel.setBounds(10, 64, 453, 443);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("First Name");
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_1.setBounds(10, 75, 113, 24);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Last Name");
		lblNewLabel_1_1.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_1_1.setBounds(10, 137, 113, 24);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("E-Mail ID");
		lblNewLabel_1_2.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_1_2.setBounds(10, 197, 113, 24);
		panel.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_3 = new JLabel("Mobile NO");
		lblNewLabel_1_3.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_1_3.setBounds(10, 254, 113, 24);
		panel.add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_4 = new JLabel("Course");
		lblNewLabel_1_4.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_1_4.setBounds(10, 310, 113, 24);
		panel.add(lblNewLabel_1_4);
		
		txtfname = new JTextField();
		txtfname.setBounds(133, 75, 215, 25);
		panel.add(txtfname);
		txtfname.setColumns(10);
		
		txtlname = new JTextField();
		txtlname.setColumns(10);
		txtlname.setBounds(133, 137, 215, 25);
		panel.add(txtlname);
		
		txtemail = new JTextField();
		txtemail.setColumns(10);
		txtemail.setBounds(133, 200, 215, 25);
		panel.add(txtemail);
		
		txtmobnumber = new JTextField();
		txtmobnumber.setColumns(10);
		txtmobnumber.setBounds(133, 254, 215, 25);
		panel.add(txtmobnumber);
		
		txtcourse = new JTextField();
		txtcourse.setColumns(10);
		txtcourse.setBounds(133, 315, 215, 25);
		panel.add(txtcourse);
		
		JButton btnNewButton = new JButton("Register");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String fname,lname,email,mobnumber,course;
				
				fname = txtfname.getText();
				lname = txtlname.getText();
				email = txtemail.getText();
				mobnumber = txtmobnumber.getText();
				course = txtcourse.getText();
				int len = mobnumber.length();
               
                if (len != 10) {
                    JOptionPane.showMessageDialog(btnNewButton, "Enter a valid mobile number");
                }

				try {
					pst = con.prepareStatement("insert into studentinfo(fname,lname,email,mobnumber,course)values(?,?,?,?,?)");
					pst.setString(1, fname);
					pst.setString(2, lname);
					pst.setString(3, email);
					pst.setString(4, mobnumber);
					pst.setString(5, course);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Registration Successful!");
					table_load();
					          
					txtfname.setText("");
					txtlname.setText("");
					txtemail.setText("");
					txtmobnumber.setText("");
					txtcourse.setText("");
					txtfname.requestFocus();
					   }
					 
					catch (SQLException e1)
					        {
					e1.printStackTrace();
					}
				 
			}
		});
		btnNewButton.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 15));
		btnNewButton.setBounds(29, 364, 113, 37);
		panel.add(btnNewButton);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 15));
		btnExit.setBounds(173, 364, 113, 37);
		panel.add(btnExit);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				txtfname.setText("");
				txtlname.setText("");
				txtemail.setText("");
				txtmobnumber.setText("");
				txtcourse.setText("");
				txtfname.requestFocus();
				
			}
		});
		btnClear.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 15));
		btnClear.setBounds(315, 364, 113, 37);
		panel.add(btnClear);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
                String fname,lname,email,mobnumber,course,Sid;
				
				fname = txtfname.getText();
				lname = txtlname.getText();
				email = txtemail.getText();
				mobnumber = txtmobnumber.getText();
				course = txtcourse.getText();
				Sid = txtSid.getText();
				
				try {
					pst = con.prepareStatement("update studentinfo set fname = ?,lname = ?,email = ?,mobnumber = ?,course = ? where studentID =?");
					pst.setString(1, fname);
					pst.setString(2, lname);
					pst.setString(3, email);
					pst.setString(4, mobnumber);
					pst.setString(5, course);
					pst.setString(6, Sid);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Updated Successful!");
					table_load();
					          
					txtfname.setText("");
					txtlname.setText("");
					txtemail.setText("");
					txtmobnumber.setText("");
					txtcourse.setText("");
					txtfname.requestFocus();
					   }
					 
					catch (SQLException e1)
					        {
					e1.printStackTrace();
					}
				
			}
		});
		btnUpdate.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 15));
		btnUpdate.setBounds(527, 426, 113, 37);
		frame.getContentPane().add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
                String Sid;
				Sid = txtSid.getText();
				
				try {
					pst = con.prepareStatement("delete from studentinfo where studentID =?");
					
					pst.setString(1, Sid);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Deleted  Successful!");
					table_load();
					          
					txtfname.setText("");
					txtlname.setText("");
					txtemail.setText("");
					txtmobnumber.setText("");
					txtcourse.setText("");
					txtfname.requestFocus();
					   }
					 
					catch (SQLException e1)
					        {
					e1.printStackTrace();
					}
				
			}
		});
		btnDelete.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 15));
		btnDelete.setBounds(704, 426, 113, 37);
		frame.getContentPane().add(btnDelete);
		
		table = new JTable();
		table.setBounds(484, 77, 387, 196);
		frame.getContentPane().add(table);
		
		JButton btnDelete_1 = new JButton("Delete");
		btnDelete_1.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 15));
		btnDelete_1.setBounds(1194, 402, 113, 37);
		frame.getContentPane().add(btnDelete_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Search", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLUE));
		panel_1.setBounds(484, 300, 387, 102);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JButton btnDelete_2 = new JButton("Delete");
		btnDelete_2.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 15));
		btnDelete_2.setBounds(733, 107, 113, 37);
		panel_1.add(btnDelete_2);
		
		JLabel lblNewLabel_1_4_1 = new JLabel("Student_ID");
		lblNewLabel_1_4_1.setFont(new Font("Arial", Font.BOLD, 20));
		lblNewLabel_1_4_1.setBounds(43, 17, 113, 24);
		panel_1.add(lblNewLabel_1_4_1);
		
		txtSid = new JTextField();
		txtSid.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				try {
			          
		            String studentID = txtSid.getText();
		 
		                pst = con.prepareStatement("select fname,lname,email,mobnumber,course from studentinfo where studentID = ?");
		                pst.setString(1, studentID);
		                ResultSet rs = pst.executeQuery();
		 
		            if(rs.next()==true)
		            {
		              
		                String fname = rs.getString(1);
		                String lname = rs.getString(2);
		                String email = rs.getString(3);
		                String mobnumber = rs.getString(4);
		                String course = rs.getString(5);
		                
		                txtfname.setText(fname);
		                txtlname.setText(lname);
		                txtemail.setText(email);
		                txtmobnumber.setText(mobnumber);
		                txtcourse.setText(course);
		                
		                
		            }  
		            else
		            {
		            	txtfname.setText("");
						txtlname.setText("");
						txtemail.setText("");
						txtmobnumber.setText("");
						txtcourse.setText("");
		            }
		            
		 
		 
		        }
		catch (SQLException ex) {
		          
		        }	
				
			}
		});
		txtSid.setColumns(10);
		txtSid.setBounds(166, 16, 215, 25);
		panel_1.add(txtSid);
	}
}
