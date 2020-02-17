import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Riccardo Canonico
 */
public class Countdown extends Thread{
    boolean b=false;
    boolean b2;
    public boolean connected;
    public int timer;
    
    public Countdown(int timer) {
    	this.timer=timer;
    }
    
    @Override
    public void run(){
        while(timer > 0 && !connected){
            try {
            	sleep(1000);           	
                b2 = this.isInterrupted();                
                System.out.println("CountDown: "+timer/1000+ " secondi");
                timer-=1000;
            }
            catch (InterruptedException ex) {
    			//ex.printStackTrace();
            }
        }
    }
}
