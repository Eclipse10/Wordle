import javax.swing.*; 
import java.awt.*; 

public class PopUpWindow extends JFrame
{
	private JPanel panel; 
	private JLabel label; 
	private JLabel label2; 
	public static int fontSize = 26; 
	
	public PopUpWindow()
	{
		super(); 
		this.setSize(400, 200); 
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setLayout(new GridLayout(1, 1));
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
	
		setUpPanel(); 
		setUpLabel(); 
		panel.setSize(getWidth(), getHeight());
		panel.add(label); 
		panel.add(label2); 
		this.add(panel); 
		
		this.setVisible(true);
	}
	
	private void setUpPanel()
	{
		panel = new JPanel(); 
		panel.setLayout(new GridLayout(2, 1));
//		panel.setAlignmentX(CENTER_ALIGNMENT);
//		panel.setAlignmentY(CENTER_ALIGNMENT);
		panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
	}
	
	private void setUpLabel()
	{
		label = new JLabel(); 		
		label.setFont(new Font("Tahoma", Font.PLAIN, fontSize));
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setVerticalAlignment(JLabel.CENTER);
		
		label2 = new JLabel(); 		
		label2.setFont(new Font("Tahoma", Font.PLAIN, fontSize));
		label2.setHorizontalAlignment(JLabel.CENTER);
		label2.setVerticalAlignment(JLabel.CENTER);
	}
	
	public void showWinMessage()
	{
		this.setTitle("Congratulations!"); 
		panel.setBackground(new Color(180, 255, 190)); 
		label.setFont(new Font("Tahoma", Font.BOLD, fontSize));
		label.setForeground(new Color(0, 100, 10));
		label.setText("You Won!"); 
		
		label2.setFont(new Font("Tahoma", Font.PLAIN, fontSize));
		label2.setForeground(new Color(0, 100, 10));
		label2.setText("Impressive!"); 
	}
	
	public void showLoseMessage(String answer)
	{
		this.setTitle("You Lost!"); 
		panel.setBackground(new Color(255, 204, 203)); 
		
		label.setFont(new Font("Tahoma", Font.BOLD, fontSize));
		label.setForeground(new Color(200, 30, 30));
		label.setText("Game Over!");
		
		label2.setFont(new Font("Tahoma", Font.PLAIN, fontSize));
		label2.setForeground(new Color(200, 30, 30));
		label2.setText("The word was " + answer);
	}
	
	public void showNotValidWordMessage()
	{
		this.setTitle("Invalid Entry!"); 
		panel.setBackground(new Color(180, 180, 250)); 
		
		label.setFont(new Font("Tahoma", Font.BOLD,  fontSize));
		label.setForeground(new Color(30, 30, 255));
		label.setText("Invalid Entry"); 
		
		label2.setFont(new Font("Tahoma", Font.PLAIN, fontSize));
		label2.setForeground(new Color(30, 30, 255));
		label2.setText("Enter a valid word"); 
	}
	
	public static void setFontSize(int size)
	{
		fontSize = size; 
	}
	
}
