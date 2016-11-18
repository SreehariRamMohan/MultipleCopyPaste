package Design;
import java.awt.Toolkit;
import java.awt.datatransfer.FlavorEvent;
import java.awt.datatransfer.FlavorListener;
public class CheckClipBoard {

	public static void main(String[] args) {
		Toolkit.getDefaultToolkit().getSystemClipboard().addFlavorListener(new FlavorListener() { 
            @Override 
            public void flavorsChanged(FlavorEvent e) { 
                System.out.println("changed!!! " + e.getSource() + " " + e.toString()); 
            } 
        }); 
        try {
			Thread.sleep(100000L);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
    }
}


