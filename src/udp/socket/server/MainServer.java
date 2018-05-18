package udp.socket.server;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Button;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.widgets.Text;


public class MainServer{
	private static Text textIP;
	private static Text textPORT;
	private static ServerProcess sp;
	private static Thread threadServerCheck;

	public static void main(String[] args) {
		Display display = Display.getDefault();
		Shell shell = new Shell();
		shell.setSize(286, 180);
		shell.setText("Server Application");
		
		CLabel lblIp = new CLabel(shell, SWT.NONE);
		lblIp.setBounds(36, 21, 29, 21);
		lblIp.setText("IP:");
		
		CLabel lblPort = new CLabel(shell, SWT.NONE);
		lblPort.setBounds(24, 48, 36, 21);
		lblPort.setText("port:");
		
		textIP = new Text(shell, SWT.BORDER);
		textIP.setBounds(70, 21, 111, 21);
		
		textPORT = new Text(shell, SWT.BORDER);
		textPORT.setBounds(70, 48, 111, 21);
		
		Button btnStartServer = new Button(shell, SWT.NONE);
		btnStartServer.setBounds(90, 90, 75, 25);
		btnStartServer.setText("Open Socket");
		btnStartServer.addListener(SWT.Selection, new Listener(){
		      public void handleEvent(Event e) {
		         if(e.type == SWT.Selection && btnStartServer.getText().equals("Open Socket")) {
		        	 InetAddress ip = null;
		        	 int port = 0;
		        	 MessageBox dialog;
		        	 boolean isAllriht = true;

		        	 if(textIP.getText().isEmpty()) {
		        		 try {
							ip = InetAddress.getLocalHost();
						} catch (UnknownHostException e1) {
							isAllriht = false;
							e1.printStackTrace();
						}
		        	 }
		        	 else {
		        		 try {
							ip = InetAddress.getByName(textIP.getText());
						} catch (UnknownHostException e1) {
							 isAllriht = false;
							 dialog = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
					         dialog.setText("Message");
					         dialog.setMessage("Please check ip number");
					         dialog.open();
						}
		        	 }
		        	 
		        	 if(textPORT.getText().isEmpty()) {
		        		 port = 9996;
		        	 }
		        	 else {
		        		 try {
								port = Integer.parseInt(textPORT.getText());
							} catch (Exception e1) {
								 isAllriht = false;
								 dialog = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
						         dialog.setText("Message");
						         dialog.setMessage("Please check port number");
						         dialog.open();
							}
		        	 }
		        	 
		        	if( isAllriht ) {
		        		sp = new ServerProcess(ip,port);
				     	threadServerCheck = new Thread(sp);
				        threadServerCheck.start();
				        try {
							Thread.sleep(500); ///idle wait to obtain updated info from child thread
						} catch (InterruptedException e1) {
							e1.printStackTrace();
						}
			        	if(sp.isScocketBound()) {
				        	 dialog = new MessageBox(shell, SWT.ICON_WORKING | SWT.OK);
					         dialog.setText("Message");
					         dialog.setMessage("Service Started");
					         dialog.open();
					         btnStartServer.setText("Running");
			        	 }
			        	 else {
			        		 dialog = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
					         dialog.setText("Message");
					         dialog.setMessage(sp.getErrorMessage());
					         dialog.open();
			        	 }
		        	}
					
		        	 
		         }
		         }
		      });
		
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		if(threadServerCheck.isAlive()) {
			sp.unbind();
 			threadServerCheck.interrupt();
		}
	}
	

}
