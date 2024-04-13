import javax.swing.*; 
import java.awt.*;

public class PlayAgainWindow extends JFrame
{
	private JPanel panel; 
	private JPanel panel2; 
	private JLabel label; 
	private JButton yesButton; 
	private JButton noButton; 
	public static int fontSize = 20; 
	
	public PlayAgainWindow()
	{
		super("Play Again?"); 
		this.setSize(400, 200); 
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.setLayout(new GridLayout(2, 1));
		setBackground(Color.WHITE);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		setUpButtons(); 
		setUpLabel(); 
		setUpPanels(); 
		
		this.add(panel); 
		this.add(panel2); 
		
		this.setVisible(true);
	}
	
	private void setUpPanels()
	{
		panel = new JPanel(); 
		panel.setSize(getWidth(), getHeight());
		panel.setLayout(new GridLayout(1, 1));
		panel.add(label); 
		panel.setBackground(Color.WHITE);
		
		panel2 = new JPanel(); 
		panel2.setSize(getWidth(), getHeight());
		panel2.setLayout(new GridLayout(2, 5, 10, 10));
		panel2.setBackground(Color.WHITE);
		
		JLabel blank = new JLabel(); 
		blank.setBackground(Color.WHITE);
		
		panel2.add(new JLabel()); 
		panel2.add(yesButton); 
		panel2.add(new JLabel()); 
		panel2.add(noButton); 
		panel2.add(new JLabel()); 
		
		for(int i = 0; i < 5; i++)
			panel2.add(new JLabel()); 
		//panel2.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
	}
	
	private void setUpLabel()
	{
		label = new JLabel(); 
		label.setFont(new Font("Tahoma", Font.BOLD, fontSize));
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setVerticalAlignment(JLabel.CENTER);
		label.setText("Play again?");
	}
	
	private void setUpButtons()
	{
		yesButton = new JButton("Yes"); 
		yesButton.setFont(new Font("Tahoma", Font.BOLD, 4 * fontSize / 5));
		yesButton.setHorizontalAlignment(JButton.CENTER);
		yesButton.setVerticalAlignment(JButton.CENTER);
		yesButton.setBackground(new Color(50, 120, 50));
		yesButton.setForeground(Color.WHITE);
		
		noButton = new JButton("No");
		noButton.setFont(new Font("Tahoma", Font.BOLD, 4 * fontSize / 5));
		noButton.setHorizontalAlignment(JButton.CENTER);
		noButton.setVerticalAlignment(JButton.CENTER);
		noButton.setBackground(new Color(220, 50, 50));
		noButton.setForeground(Color.WHITE);
	}
	
	public JButton getYesButton()
	{
		return yesButton; 
	}
	
	public JButton getNoButton()
	{
		return noButton; 
	}
	
	public static void main(String[] args)
	{
		PlayAgainWindow w = new PlayAgainWindow();
		w.setVisible(true);
	}
}
