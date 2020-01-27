
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
    @Override
    public void run(){
        for(int i=10000; i > 0; i-=1000){
            try {
                if(!connected){
                    sleep(1000);
                    b2 = this.isInterrupted();
                    System.out.println("CountDown: "+i/1000+ " secondi");
                }
                else{
                    break; 
                }
            } 
            catch (InterruptedException ex) {
                System.out.println("ERRORE");
            }
        }
    }
}
