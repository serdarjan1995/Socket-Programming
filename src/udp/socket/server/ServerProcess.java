package udp.socket.server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Scanner;



public class ServerProcess implements Runnable{
	private DatagramSocket aSocket;
	private int mat1[][];
	private int mat2[][];
	private int m1row = 0;
	private int m1col = 0;
	private int m2row = 0;
	private int m2col = 0;
	private int serverPort;
	private InetAddress serverIP;
	private boolean isSocketBound;
	private String errorMessage;
	
	public ServerProcess(InetAddress ip, int port) {
		serverPort = port;
		serverIP = ip;
		isSocketBound = false;
		errorMessage = "";
	}
	
	public void run() {
    	aSocket = null;
        try{
            aSocket = new DatagramSocket(serverPort,serverIP);
            isSocketBound = true;
            DatagramPacket request;
            DatagramPacket reply;
            byte[] buffer = new byte[1000];
            String requestString;
            Scanner scanner = null;
            int functionCode = 0;
            int check = 0;
            int resultMatrix[][];
            
            while(true){
               request = new DatagramPacket(buffer, buffer.length);
               aSocket.receive(request);
               requestString = new String(request.getData()).toString().substring(0,request.getLength());
               if(requestString.equals("Are you alive?")) {
            	   reply = new DatagramPacket("Server is up!".getBytes(),
                    "Server is up!".length(), request.getAddress(), request.getPort());
            	   aSocket.send(reply);
               }
               else {
            	   check = 0;
            	   errorMessage = "Error";
            	   scanner = new Scanner(requestString);
            	   //System.out.println(s);
            	   if (scanner.hasNext()) {
            		   functionCode = scanner.nextInt();
            		   m1row = scanner.nextInt();
            		   m1col = scanner.nextInt();
            		   m2row = scanner.nextInt();
            		   m2col = scanner.nextInt();
            	   }
            	   
            	   if( functionCode == 1 || functionCode == 2 ) {
            		   if( (m1row == m2row) && (m1col == m2col) ) {
            			   check = 1;
            		   }else {
            			   errorMessage = "Error! Dimensions should be equal";
            		   }
            	   }
            	   else if( functionCode == 3 )  {
            		   if( m1col == m2row ) {
            			   check = 1;
            		   }else {
            			   errorMessage = "Error! Column size of First Matrix should be equal to"
            			   		+ "Row size of the Second Matrix, " + m1col + " != " + m2row;
            		   }
            	   }
            	   
            	   if( check == 1 ) {  /// matrix row column control pass
            		   mat1 = new int[m1row][m1col];
                	   mat2 = new int[m2row][m2col];
                       
                       int temp=0;
    	               for(int i=0; i<m1row; i++) {
    	       				for(int j=0; j<m1col && scanner.hasNext(); j++) {
    	       					temp = scanner.nextInt();
    	       					mat1[i][j] = temp;
    	       					//System.out.print(temp + " ");
    	       				}
    	       				//System.out.println();
    	       			}
    	               
    	               for(int i=0; i<m2row; i++) {
    	       				for(int j=0; j<m2col && scanner.hasNext(); j++) {
    	       					temp = scanner.nextInt();
    	       					mat2[i][j] = temp;
    	       					//System.out.print(temp + " ");
    	       				}
    	       				//System.out.println();
    	       			}
                       
                       scanner.close(); //processing raw data finished
                       resultMatrix = doCalculations(functionCode);
                       if(resultMatrix != null) {
                    	   String replyString = "";
                    	   if(functionCode == 3) { // if process was multiplications
                    		   replyString = m1row + " " + m2col + " ";
                    		   for(int i=0; i<m1row; i++) {
                    			   for( int j=0; j<m2col; j++) {
                    				   replyString += resultMatrix[i][j] + " ";
                    			   }
                    		   }
                    	   }else { // otherwise. ( addition | subtraction )
                    		   replyString = m1row + " " + m1col + " ";
                    		   for(int i=0; i<m1row; i++) {
                    			   for( int j=0; j<m1col; j++) {
                    				   replyString += resultMatrix[i][j] + " ";
                    			   }
                    		   }
                    	   }
                    	   reply = new DatagramPacket(replyString.getBytes(),
                    			   replyString.length(), request.getAddress(), request.getPort());
                           aSocket.send(reply);
                       }
                       else { /// if there was an error with operation. 
                    	   reply = new DatagramPacket("Error!".getBytes(),
                    			   "Error!".length(), request.getAddress(), request.getPort());
                           aSocket.send(reply);
                       }
            	   }
            	   else { // matrix row column control did not pass !!!ERROR
            		   reply = new DatagramPacket(errorMessage.getBytes(),
            				   errorMessage.length(), request.getAddress(), request.getPort());
                       aSocket.send(reply);
            	   }
            	   
            	   
               }
            }
        }catch (SocketException e){
        	errorMessage = e.getMessage();
        	System.out.println("SeverSocket: " + errorMessage);
        }
         catch (IOException e){
        	errorMessage = e.getMessage();
        	System.out.println("IO: " + errorMessage);
        }
	    finally{
	    	if(aSocket != null) aSocket.close();
	    }
    }
	
	
	
	
	public synchronized void unbind(){
		if(aSocket != null) aSocket.close();
	}

	
	
	public int[][] doCalculations(int functionCode) {
		int resultMat[][] = null;
		
		switch (functionCode) {
			case 1: //Addition
				resultMat = new int[m1row][m1col];
				for( int i=0; i<m1row; i++) {
					for( int j=0; j<m1col; j++) {
						resultMat[i][j] = mat1[i][j] + mat2[i][j];
					}
				}
				break;
				
			case 2: //Subtraction
				resultMat = new int[m1row][m1col];
				for( int i=0; i<m1row; i++) {
					for( int j=0; j<m1col; j++) {
						resultMat[i][j] = mat1[i][j] - mat2[i][j];
					}
				}
				break;

			case 3: //Multiplication
				resultMat = new int[m1row][m2col];
				for( int i=0; i<m1row; i++) {
					for( int j=0; j<m2col; j++) {
						for( int p=0; p<m1col; p++) {
							resultMat[i][j] += mat1[i][p] * mat2[p][j];
						}
					}
						
					}
				break;
	
			default: break;
			}
		return resultMat;
	}
	
	public synchronized boolean  isScocketBound() {
		return isSocketBound;
	}
	
	public synchronized String getErrorMessage() {
		return errorMessage;
	}

}
