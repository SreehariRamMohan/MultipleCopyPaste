package Design;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.FlavorEvent;
import java.awt.datatransfer.FlavorListener;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

public class ActionManager extends JFrame implements KeyListener, FocusListener, ActionListener, MouseListener, Runnable {
	private Clipboard clipboard;
	private Storage storage;
    private JTextArea textArea; 
    private JTextArea textArea2; 
    private JLabel textLabel;
    private JScrollPane scrollPane;
    private JTextField textInput;
    private JTextField textField;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JTextField textField1;
    private JLabel lable1;
    private JTextArea textArea1;
    private Thread thread;
    
    private JButton button;
    
    //private JPanel panel;
    JLabel label;
    private boolean deletePressed = false;
    private boolean dPressed = false;
    private boolean cPressed = false;
    private boolean xPressed = false;
    private boolean controlPressed = false;
    private String content = "Here men from the planet Earth\n"
    	      + "first set foot upon the Moon,\n" + "July 1969, AD.\n"
    	      + "We came in peace for all mankind.";
	// gets reference of storage so it can call commands. 
	public ActionManager(Storage storage, String s ) {
		super(s);
		addFocusListener(this);
		addKeyListener(this);
		this.storage = storage;	
		JPanel p = new JPanel();
		setSize(1000, 1000);
        label = new JLabel("Multiple Copy Paste!");
        label.setForeground(Color.red);
        label.setFont(label.getFont().deriveFont(20f));
        p.add(label);
        p.setBackground(Color.blue);
        add(p); 
        setVisible(true);  
        
       
        add(p);
        textField = new JTextField(8);
        textField.setEditable(true);
        textField.setForeground(Color.BLACK);
       
        textField.addMouseListener(this);
        textField.addKeyListener(this);
        this.add(textField);
        textField.setFont(textField.getFont().deriveFont(50f));
        p.add(textField);
        
        
        textArea = new JTextArea(); 
        textArea.setEditable(false);
		textArea.setBounds(100, 150, 200, 100);
		this.add(textArea);
		p.add(textArea);
		textArea.setText("Penguins are birds with black and white feathers and a funny waddle.  But unlike most birds, penguins are not able to fly -- in the air that is.  Penguins spend as much as 75% of their time underwater, searching for food in the ocean.  When they are in the water, they dive and flap their wings.  It looks just like they are flying!");
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.addKeyListener(this);
		textArea.addMouseListener(this);
		
		 textArea1 = new JTextArea(); 
		 textArea1.setEditable(true);
		textArea1.setBounds(100, 300, 200, 100);
		this.add(textArea1);
		p.add(textArea1);
		textArea1.setText("Your writing goes here!");
		textArea1.setWrapStyleWord(true);
		textArea1.addKeyListener(this);
		textArea1.addMouseListener(this);
		textArea1.setLineWrap(true);
		textArea1.setWrapStyleWord(true);
		
		
		
		
        setVisible(true);
		repaint();   
		/*Thread t1 = new Thread();
		t1.start(); */
		thread = new Thread(this);
		thread.start();
		System.out.println("Created thread");
		
		
	}
	

   
    

 
    @Override
    public void keyPressed(KeyEvent e) {
    	if(e.getKeyCode() == 8) {
    		deletePressed = true;
    	}
        if (e.getKeyCode() == 17) {
            //System.out.println("Control was pressed.");
            controlPressed = true;
        }
        if(e.getKeyCode() == KeyEvent.VK_D) {
        	dPressed = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_C) {
        	cPressed = true;
            //System.out.println("C was pressed.");
        }
        if(e.getKeyCode() == KeyEvent.VK_X) {
        	xPressed = true;
        }
        if(controlPressed && cPressed) {
        	//System.out.println("Both have been pressed");
        		if(e.getKeyCode() == 16) {
        			System.out.println(storage.cycleThrough());
        			textField.setText(storage.cycleThrough());
        		}
        } else if(controlPressed && xPressed) {
        	storage.remove(storage.getCurrentIndex());
        	System.out.println("Removed");
        	textField.setText("Removed");
        } else if(controlPressed && dPressed) {
        	textArea1.setText(storage.spitOutArray());
        	System.out.println("Printed out summary");
        } else if(deletePressed && controlPressed) {
        	storage.deleteAll();
        	System.out.println("deleted entire database");
        	textField.setText("Data delete!");
        	
        }

    }
    
    @Override
	public void keyReleased(KeyEvent e) {
    	  if (e.getKeyCode() == 17) {
              //System.out.println("Control was released.");
              controlPressed = false;
          }
          if (e.getKeyCode() == KeyEvent.VK_C) {
              //System.out.println("C was released.");
              cPressed = false;
              
            Toolkit toolkit = Toolkit.getDefaultToolkit();
      		clipboard = toolkit.getSystemClipboard();
      		String result = "";
			try {
				result = (String) clipboard.getData(DataFlavor.stringFlavor);
			} catch (UnsupportedFlavorException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
      		System.out.println("String from Clipboard:" + result);
          }
          if(e.getKeyCode() == KeyEvent.VK_X) {
          	xPressed = false;
          }
          if(e.getKeyCode() == KeyEvent.VK_D) {
          	dPressed = false;
          }
          if(e.getKeyCode() == 8) {
      		deletePressed = false;
      	}
	}
  

   

   

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void focusGained(FocusEvent e) {
		System.out.println("focus ");
		
	}

	@Override
	public void focusLost(FocusEvent e) {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
        //String selectedtext = e.getSelected();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		System.out.println("In mouse released method");
		if(textField != null) {
			if (textField.getSelectedText() != null) { // See if they selected something
				System.out.println("Getting selected text");
				String s = textField.getSelectedText();
				
					System.out.println("HERE");
			        storage.add(s);
			        System.out.println("added " + s + " to clipboard");
		    	} 
		    }
			if(textArea != null){
		    	if (textArea.getSelectedText() != null) { // See if they selected something
					String a = textArea.getSelectedText();
			        storage.add(a);
			        System.out.println("added " + a + " to clipboard");
		    	}
		    } 
		
	}
	

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}






	@Override
	public void run()  {
		System.out.println("In run block");
		String prevResult = "";
		boolean firstTime = true;
		while(true) {
			try {
				thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Toolkit toolkit = Toolkit.getDefaultToolkit();
      		clipboard = toolkit.getSystemClipboard();
      		String result = "";
			try {
				result = (String) clipboard.getData(DataFlavor.stringFlavor);
			} catch (UnsupportedFlavorException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			if(!result.equals(prevResult) && !firstTime) {
				System.out.println(result);
				prevResult = result;
				storage.add(result);
			}
		
			firstTime = false;
		} 
	}
	
	/*public static void main(String[] args) {
		Toolkit.getDefaultToolkit().getSystemClipboard().addFlavorListener(new FlavorListener() { 
            @Override 
            public void flavorsChanged(FlavorEvent e) { 
                System.out.println("changed!!! " + e.getSource() + " " + e.toString()); 
            } 
        }); 
        try {
			Thread.sleep(100);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
    }*/



}





	



