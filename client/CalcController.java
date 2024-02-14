package L11.client;

import javax.swing.*;
import java.io.IOException;

public class CalcController {
	private CalcClient client;
	private CalcUI ui = new CalcUI(this);

	private void showCalcUI() {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new JFrame("Client");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.add(ui);
				frame.pack();
				frame.setVisible(true);
			}
		});
	}

	public CalcController(CalcClient client) {
		this.client = client;
		client.setCalcController(this);
		showCalcUI();
	}
	public void newCalculation(String nbr1, String nbr2, String operation) {
		try {
			client.newCalculation(nbr1+","+nbr2+","+operation);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void newResponse(final String response) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				ui.setResult(response);
			}
		});
	}
	public static void main(String[] args) {
		//ip hemifrån
		String ip = "127.0.0.1";
		//ip på plats MAU
		//String ip = "195.178.227.53";

		try {
			CalcClient clientA = new CalcClientA(ip, 721);
			new CalcController(clientA);

//			CalcClient clientB = new CalcClientB(ip,722);
//			new CalcController(clientB);

//            CalcClient clientC = new CalcClientC(ip, 723);
//            new CalcController(clientC);

//            CalcClient clientD = new CalcClientD(ip, 724);
//            new CalcController(clientD);
		}catch(Exception e) {
			e.printStackTrace();
		}

	}
}

