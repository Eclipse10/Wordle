import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*; 

import javax.swing.*;

public class Wordsendiz extends JFrame 
{
	private WPanel[][] panels; 
	
	private int currentRow; 
	private boolean rowEntered; 
	private boolean isGameOver; 
	private boolean isWinner; 
	private boolean isInvalid; 
	
	private String word; 
	private ArrayList<String> activeEntries; 
	private ArrayList<String> savedEntries; 
	private ArrayList<String> words;
	private PopUpWindow message; 
	
	public Wordsendiz() 
	{
		super("Wordsendiz");
		this.setSize(600, 600);
		this.setResizable(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new GridLayout(6, 6));
		this.setBackground(Color.WHITE);
		
		activeEntries = new ArrayList<String>(); 
		savedEntries = new ArrayList<String>(); 
		words = new ArrayList<String>(10000);
		setUpPanels(); 
		setUpNewGame(); 
		setUpKeyListener(); 
		setUpPopUp(); 
		setVisible(true); 
	}
	
	private void setUpKeyListener()
	{
		addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e)
			{
				if (isGameOver)
				{
					endGame(); 
				}
				
				int keyPressed = e.getKeyCode(); 
		
				if (keyPressed >= 'A' && keyPressed <= 'Z')
				{
					if(activeEntries.size() < 5)
					{
						activeEntries.add("" + (char)keyPressed);
						update(); 
					}
				}
				if (keyPressed == 10)
				{
					if (activeEntries.size() == 5 && !isGameOver)
					{
						if(words.contains(getEntryAsString()))
						{
							rowEntered = true; 
							update(); 
						}
						else if(!isInvalid && !message.isVisible())
						{
							showInvalidMessage(); 
							isInvalid = true; 
						}
						else
						{
							message.setVisible(false);
							isInvalid = false; 
						}
					}
				}
				if (keyPressed == 8)
				{
					if(activeEntries.size() > 0 && !isGameOver)
					{
						activeEntries.remove(activeEntries.size() - 1);
						update(); 
					}
				}
				//System.out.println(keyPressed + " is " + (char)keyPressed);
			}
		});
	}
	
	private void setUpNewGame()
	{
		activeEntries.clear();
		savedEntries.clear(); 
		
		word = getRandomWord(); 
		currentRow = 0; 
		rowEntered = false; 
		isGameOver = false; 
		isWinner = false; 
		isInvalid = false; 
		
		for(WPanel[] row: panels)
		{
			for(WPanel p: row)
				p.reset(); 
		}
		
		repaint(); 
	}
	
	private void setUpPanels()
	{
		panels = new WPanel[6][5];
		
		for(int r = 0; r < panels.length; r++)
		{
			for(int c = 0; c < panels[r].length; c++)
			{
				WPanel panel = new WPanel(getWidth() / 5, getHeight() / 6); 
				panels[r][c] = panel;  
				
				this.add(panel); 
			}
		}
	}
	
	private void update()
	{
		if (rowEntered)
		{	
			showResult(); 
			
			for(String s: activeEntries)
				savedEntries.add(s); 
			activeEntries.clear(); 
			rowEntered = false; 
		}	
		else if (isGameOver)
			endGame(); 
		else
			showCurrentRow(); 
	}
	
	private boolean[] getMatches()
	{
		boolean[] matches = new boolean[5];
		
		for(int i = 0; i < 5 && i < activeEntries.size(); i++)
		{
			if(activeEntries.get(i).equals(word.substring(i, i + 1)))
			{
				matches[i] = true; 
			}
		}
		
		return matches; 
	}
	
	private boolean[] getPartialMatches()
	{
		boolean[] matches = getMatches(); 
		boolean[] partial = new boolean[5]; 
		
		ArrayList<String> tracker = new ArrayList<String>();
		for(int i = 0; i < word.length(); i++)
		{
			tracker.add(word.substring(i, i + 1)); 
		}
		
		for(int i = 0; i < tracker.size(); i++)
		{
			if(tracker.get(i).equals(activeEntries.get(i)))
				tracker.set(i, "#"); 
		}
		
		for(int i = 0; i < 5 && i < activeEntries.size(); i++)
		{
			String entryLetter = activeEntries.get(i);
			
			if(word.indexOf(entryLetter) != i && word.contains(entryLetter) && tracker.contains(entryLetter)
					&& !matches[i])
			{
				partial[i] = true; 
				tracker.set(tracker.indexOf(entryLetter), "#"); 
				//System.out.println("Tracker: " + tracker); 
			}
		}
		
		return partial; 
	}
	
	public void showResult()
	{
		boolean[] matches = getMatches(); 
		boolean[] partial = getPartialMatches(); 
		WPanel[] rowPanel = panels[currentRow]; 
		int matchCount = 0; 
		
		for (int i = 0; i < 5; i++)
		{
			WPanel current = rowPanel[i]; 
			
			if (matches[i])
			{
				current.setBackgroundColor(new Color(1, 154, 1));
				current.setTextColor(Color.WHITE);
				matchCount++; 
			}
			else if (partial[i])
			{
				current.setBackgroundColor(new Color(255, 196, 37));
				current.setTextColor(Color.WHITE);
			}
			else
			{
				current.setBackgroundColor(Color.GRAY);
				current.setTextColor(Color.WHITE);
			}
		}
		
		currentRow++; 
		
		if(matchCount == 5)
			isWinner = true; 
		if(matchCount == 5 || currentRow > 5)
		{
			isGameOver = true; 
			endGame(); 
		}
	}
	
	public void showCurrentRow()
	{
		WPanel[] rowPanel = panels[currentRow];
		
		for(int i = 0; i < rowPanel.length; i++)
		{
			if (i < activeEntries.size())
				rowPanel[i].setLetter(activeEntries.get(i)); 
			else
				rowPanel[i].setLetter(""); 
		}
	}
	
	public String getRandomWord() 
	{
		String str = ""; 
		
		try 
		{
			File file = new File("fiveLetterWords.txt");
			Scanner reader = new Scanner(file); 
			
			while(reader.hasNext())
				words.add(reader.nextLine().toUpperCase()); 
		
			int rand = (int)(Math.random() * words.size()); 
		
			str = words.get(rand); 
			
			reader.close();
		}
		catch (FileNotFoundException obj)
		{
			
		} 
			
		if(str.equals(""))
			return "ERROR"; 
		else
			return str; 
		
	}
	
	private String getEntryAsString()
	{
		String str = "";
		
		for(String s: activeEntries)
			str += s.charAt(0); 
		
		return str; 
	}
	
	public void showInvalidMessage()
	{
		message.showNotValidWordMessage();
		message.setVisible(true);
	}
	
	public void setUpPopUp()
	{
		message = new PopUpWindow(); 
		message.setVisible(false);
		
		message.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e)
			{
				int keyPressed = e.getKeyCode(); 
		
				if (keyPressed == 10 && !isGameOver)
				{
					if(isInvalid)
					{
						message.setVisible(false);
						isInvalid = false; 
					}
					else
					{
						showInvalidMessage(); 
						isInvalid = true; 
					}
				}
				else if (keyPressed == 10 && isGameOver && message.isVisible())
				{
					showPlayAgainWindow(); 
				}
				//System.out.println(keyPressed + " is " + (char)keyPressed);
			}
		});
		
		message.addWindowListener(new WindowListener() {

			public void windowOpened(WindowEvent e) {}

			public void windowClosing(WindowEvent e)
			{
				if(isGameOver && message.isVisible())
				{
					showPlayAgainWindow(); 
				}
			}

			public void windowClosed(WindowEvent e) {}
			public void windowIconified(WindowEvent e) {}
			public void windowDeiconified(WindowEvent e) {}
			public void windowActivated(WindowEvent e) {}
			public void windowDeactivated(WindowEvent e) {}
		});
	}
	
	public void endGame()
	{
		if(isWinner)
		{
			message.showWinMessage();
			message.setVisible(true);
		}
		else
		{
			message.showLoseMessage(word);
			message.setVisible(true);
		}
	}
	
	public void showPlayAgainWindow()
	{
		message.setVisible(false); 
		
		PlayAgainWindow window = new PlayAgainWindow(); 
		
		
//		window.addKeyListener(new KeyAdapter() {
//			public void keyPressed(KeyEvent e)
//			{
//				int keyPressed = e.getKeyCode(); 
//		
//				if (keyPressed == 10)
//				{
//					window.setVisible(false); 
//					setUpNewGame(); 
//					window.dispose(); 
//				}
//				if (keyPressed == 39)
//					window.getNoButton().requestFocusInWindow(); 
//				if (keyPressed == 37)
//					window.getYesButton().requestFocusInWindow(); 
//				 
//				System.out.println(keyPressed + " is " + (char)keyPressed);
//			}
//		});
//		
		JButton yes = window.getYesButton();
		yes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				window.dispose();
				setUpNewGame(); 
			}
		}); 
		
		JButton no = window.getNoButton();
		no.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				window.dispose();
				endProgram();  
			}
		}); 
		
	}
	
	
	public void endProgram()
	{
		System.exit(0); 
	}
	
	public static void main(String[] args)
	{
		Wordsendiz w = new Wordsendiz(); 
		

		

	}
	
	
}
