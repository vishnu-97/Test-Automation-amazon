package com.genesis;
import org.sikuli.script.FindFailed;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;
//import org.sikuli.script.Screen;
import org.testng.annotations.Test;
public class SikuliTest {
	
	@Test
	void screenSikuli() {
		System.out.println("HELLO");
		Screen screen=new Screen();
		Pattern search=new Pattern(System.getProperty("user.dir")+"/Res/Screenshot (79).png");
		try {
			screen.type(search, "book");
		
			screen.click(search);
			Thread.sleep(10000);
		} catch (FindFailed e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("sikuli");
		
	}
	
		
		
		
	
}
                              