package app.util;

import javax.swing.JLabel;
import java.text.SimpleDateFormat;


public class DigitalClock {
	
	Thread clockThread = null;
	int hours = 0;
	int minutes = 0;
	int seconds = 0;
	static String timeString = "";

	public DigitalClock(final JLabel lblClock) {
		new Thread(new Runnable(){
			@Override
			public void run(){ 
				try{
					updateTime(lblClock);
				}
				catch (Exception ie){ 

				}
			}
		}).start();
	}


	@SuppressWarnings("static-access")
	public void updateTime(JLabel lblClock){
		try{
			while(true){
				lblClock.setText(new SimpleDateFormat("hh:mm:ss a").format(new java.util.Date()));
				Thread.currentThread().sleep(1000);
			}
		}
		catch (Exception e){
			System.out.println("Exception in Thread Sleep : "+e);
		}
		{

		}
		try{
			while(true){
				lblClock.setText(new SimpleDateFormat("hh:mm:ss a").format(new java.util.Date()));
				Thread.currentThread().sleep(1000);
			}
		}
		catch (Exception e){
			System.out.println("Exception in Thread Sleep : "+e);
		}
	}

}