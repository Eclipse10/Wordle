import javax.swing.*; 
import java.awt.*; 

public class WPanel extends JPanel
{
	private Color background; 
	private Color textColor; 
	private JLabel label; 
	private String letter; 
	private int w;
	private int h; 
	
	public WPanel(int w, int h)
	{
		super(); 
		background = Color.WHITE; 
		textColor = Color.BLACK; 
		label = new JLabel(); 
		letter = ""; 
		this.w = w; 
		this.h = h; 
		
		setUp(); 
		
		this.add(label); 
		this.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 2));
	}
	
	private void setUp()
	{
		setSize(w, h); 
		setLayout(new GridLayout(1, 1));
		setBackground(background);
		setAlignmentX(CENTER_ALIGNMENT);
		setAlignmentY(CENTER_ALIGNMENT);
		
		label.setLayout(new BorderLayout());
		label.setBorder(BorderFactory.createLineBorder(Color.WHITE, 3));
		label.setFont(new Font("Tahoma", Font.BOLD, Math.min(getWidth(), getHeight()) / 2));
//		label.setAlignmentX(CENTER_ALIGNMENT);
//		label.setAlignmentY(CENTER_ALIGNMENT);
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setVerticalAlignment(JLabel.CENTER);
	}
	
	public void reset()
	{
		background = Color.WHITE; 
		textColor = Color.BLACK; 
		letter = ""; 
		update(); 
	}
	
	public void update()
	{
		setBackground(background); 
		
		label.setForeground(textColor); 
		label.setFont(new Font("Tahoma", Font.BOLD, Math.min(getWidth(), getHeight()) / 2));
		label.setText(letter); 
		label.repaint(); 
		repaint(); 
	}
	
	public String getLetter()
	{
		return letter; 
	}
	
	public void setBackgroundColor(Color c)
	{
		background = c; 
		update(); 
	}
	
	public void setTextColor(Color c)
	{
		textColor = c; 
		update(); 
	}
	
	public void setLetter(String s)
	{
		boolean isSpaceOrEmpty = s.equals("") || s.equals(" "); 
		boolean isOneLetter = s.length() == 1 && Character.isLetter(s.charAt(0));
		
		if(isSpaceOrEmpty || isOneLetter)
		{
			letter = s; 
			label.setText(s);
			label.setForeground(textColor);
			update(); 
		}
		else
			throw new IllegalArgumentException(); 
	}
	

	
	
	
}
