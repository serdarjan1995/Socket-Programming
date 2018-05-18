package udp.socket.client;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CLabel;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.widgets.*;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;
import org.eclipse.wb.swt.SWTResourceManager;

public class MainClient {
	private static Text first_0_0;
	private static Text first_0_1;
	private static Text first_0_3;
	private static Text first_0_2;
	private static Text first_0_4;
	private static Text first_0_5;
	private static Text first_1_0;
	private static Text first_1_1;
	private static Text first_1_2;
	private static Text first_1_3;
	private static Text first_1_4;
	private static Text first_1_5;
	private static Text first_2_0;
	private static Text first_2_1;
	private static Text first_2_2;
	private static Text first_2_3;
	private static Text first_2_4;
	private static Text first_2_5;
	private static Text first_3_0;
	private static Text first_3_1;
	private static Text first_3_2;
	private static Text first_3_3;
	private static Text first_3_4;
	private static Text first_3_5;
	private static Text first_4_0;
	private static Text first_4_1;
	private static Text first_4_2;
	private static Text first_4_3;
	private static Text first_4_4;
	private static Text first_4_5;
	private static Text first_5_0;
	private static Text first_5_1;
	private static Text first_5_2;
	private static Text first_5_3;
	private static Text first_5_4;
	private static Text first_5_5;
	private static Text second_5_0;
	private static Text second_0_0;
	private static Text second_1_0;
	private static Text second_2_0;
	private static Text second_3_2;
	private static Text second_4_1;
	private static Text second_3_1;
	private static Text second_3_0;
	private static Text second_4_0;
	private static Text second_5_1;
	private static Text second_5_2;
	private static Text second_5_3;
	private static Text second_5_4;
	private static Text second_5_5;
	private static Text second_4_5;
	private static Text second_4_4;
	private static Text second_4_2;
	private static Text second_4_3;
	private static Text second_3_3;
	private static Text second_3_4;
	private static Text second_3_5;
	private static Text second_2_5;
	private static Text second_2_4;
	private static Text second_2_3;
	private static Text second_2_2;
	private static Text second_2_1;
	private static Text second_1_1;
	private static Text second_0_1;
	private static Text second_0_2;
	private static Text second_1_2;
	private static Text second_1_3;
	private static Text second_0_3;
	private static Text second_0_4;
	private static Text second_1_4;
	private static Text second_1_5;
	private static Text second_0_5;
	
	private static CLabel resultMatrixLbl;
	private static StyledText serverStatusText;
	private static Shell shell;
	private static Integer M1_ROW;
	private static Integer M1_COL;
	private static Integer M2_ROW;
	private static Integer M2_COL;
	private static ArrayList <Text> mat1Cells;
	private static ArrayList <Text> mat2Cells;
	private static Button btnSendData;
	private static int functionCode;
	private static CLabel lblServerIp;
	private static CLabel lblServerPort;
	private static Text serverIPtext;
	private static Text serverPORTtext;

	/**
	 * Launch the application.
	 * @param args
	 */
	
	public static void main(String[] args) {
		Display display = Display.getDefault();
		shell = new Shell();
		shell.setSize(500, 595);
		shell.setText("Client application");
		shell.setLayout(new FormLayout());
		
		mat1Cells = new ArrayList <Text>();
		mat2Cells = new ArrayList <Text>();
		functionCode = 1;
		
		Composite composite = new Composite(shell, SWT.BORDER);
		FormData fd_composite = new FormData();
		fd_composite.bottom = new FormAttachment(0, 85);
		fd_composite.top = new FormAttachment(0, 10);
		fd_composite.left = new FormAttachment(0, 10);
		fd_composite.right = new FormAttachment(100, -23);
		composite.setLayoutData(fd_composite);
		
		CLabel label = new CLabel(composite, SWT.NONE);
		label.setText("Server status:");
		label.setBounds(10, 37, 75, 21);
		
		serverStatusText = new StyledText(composite, SWT.BORDER);
		serverStatusText.setBackground(SWTResourceManager.getColor(SWT.COLOR_INFO_BACKGROUND));
		serverStatusText.setEnabled(false);
		serverStatusText.setDoubleClickEnabled(false);
		serverStatusText.setText("Unknown");
		serverStatusText.setEditable(false);
		serverStatusText.setBounds(91, 37, 164, 19);
		
		Button btnCheckServer = new Button(composite, SWT.NONE);
		btnCheckServer.setText("Check server");
		btnCheckServer.setBounds(284, 35, 101, 23);
		btnCheckServer.addListener(SWT.Selection, new Listener(){
		      public void handleEvent(Event e) {
		          if(e.type == SWT.Selection) {
		        	checkServer();
		          }
		        }
		      });
		
		Group group = new Group(shell, SWT.NONE);
		
		lblServerIp = new CLabel(composite, SWT.NONE);
		lblServerIp.setBounds(10, 10, 61, 21);
		lblServerIp.setText("Server IP:");
		
		lblServerPort = new CLabel(composite, SWT.NONE);
		lblServerPort.setBounds(212, 10, 66, 21);
		lblServerPort.setText("Server port:");
		
		serverIPtext = new Text(composite, SWT.BORDER);
		serverIPtext.setBounds(73, 10, 115, 21);
		FormData fd_group = new FormData();
		fd_group.top = new FormAttachment(composite, 6);
		fd_group.right = new FormAttachment(100, -23);
		fd_group.left = new FormAttachment(0, 10);
		
		serverPORTtext = new Text(composite, SWT.BORDER);
		serverPORTtext.setBounds(284, 10, 76, 21);
		group.setLayoutData(fd_group);
		group.setText("Select function");
		
		Button radioBtnAddition = new Button(group, SWT.RADIO);
		radioBtnAddition.setText("Addition");
		radioBtnAddition.setSelection(true);
		radioBtnAddition.setBounds(10, 21, 72, 16);
		radioBtnAddition.addListener(SWT.Selection, new Listener(){
		      public void handleEvent(Event e) {
		          if(e.type == SWT.Selection) {
		        	functionCode = 1;
		          }
		        }
		      });
		
		Button radioBtnSubtraction = new Button(group, SWT.RADIO);
		radioBtnSubtraction.setText("Subtraction");
		radioBtnSubtraction.setBounds(102, 21, 80, 16);
		radioBtnSubtraction.addListener(SWT.Selection, new Listener(){
		      public void handleEvent(Event e) {
		          if(e.type == SWT.Selection) {
		        	functionCode = 2;
		          }
		        }
		      });
		
		Button radioBtnMultiplication = new Button(group, SWT.RADIO);
		radioBtnMultiplication.setText("Multiplication");
		radioBtnMultiplication.setBounds(196, 21, 90, 16);
		radioBtnMultiplication.addListener(SWT.Selection, new Listener(){
		      public void handleEvent(Event e) {
		          if(e.type == SWT.Selection) {
		        	functionCode = 3;
		          }
		        }
		      });
		
		Composite composite_1 = new Composite(shell, SWT.BORDER);
		fd_group.bottom = new FormAttachment(composite_1, -6);
		composite_1.setLayout(null);
		FormData fd_composite_1 = new FormData();
		fd_composite_1.right = new FormAttachment(100, -23);
		fd_composite_1.left = new FormAttachment(0, 10);
		fd_composite_1.top = new FormAttachment(0, 144);
		composite_1.setLayoutData(fd_composite_1);
		
		Composite firstMatrixComposite = new Composite(composite_1, SWT.BORDER);
		firstMatrixComposite.setBounds(0, 60, 223, 185);
		firstMatrixComposite.setLayout(null);
		{//Matrix gui
		first_0_0 = new Text(firstMatrixComposite, SWT.BORDER);
		first_0_0.setBounds(10, 10, 25, 21);
		mat1Cells.add(first_0_0);
		
		first_0_1 = new Text(firstMatrixComposite, SWT.BORDER);
		first_0_1.setBounds(41, 10, 25, 21);
		mat1Cells.add(first_0_1);

		first_0_2 = new Text(firstMatrixComposite, SWT.BORDER);
		first_0_2.setBounds(72, 10, 25, 21);
		mat1Cells.add(first_0_2);
		
		first_0_3 = new Text(firstMatrixComposite, SWT.BORDER);
		first_0_3.setBounds(103, 10, 25, 21);
		mat1Cells.add(first_0_3);
		
		first_0_4 = new Text(firstMatrixComposite, SWT.BORDER);
		first_0_4.setBounds(134, 10, 25, 21);
		mat1Cells.add(first_0_4);
		
		first_0_5 = new Text(firstMatrixComposite, SWT.BORDER);
		first_0_5.setBounds(165, 10, 25, 21);
		mat1Cells.add(first_0_5);
		
		first_1_0 = new Text(firstMatrixComposite, SWT.BORDER);
		first_1_0.setBounds(10, 37, 25, 21);
		mat1Cells.add(first_1_0);
		
		first_1_1 = new Text(firstMatrixComposite, SWT.BORDER);
		first_1_1.setBounds(41, 37, 25, 21);
		mat1Cells.add(first_1_1);
		
		first_1_2 = new Text(firstMatrixComposite, SWT.BORDER);
		first_1_2.setBounds(72, 37, 25, 21);
		mat1Cells.add(first_1_2);
		
		first_1_3 = new Text(firstMatrixComposite, SWT.BORDER);
		first_1_3.setBounds(103, 37, 25, 21);
		mat1Cells.add(first_1_3);
		
		first_1_4 = new Text(firstMatrixComposite, SWT.BORDER);
		first_1_4.setBounds(134, 37, 25, 21);
		mat1Cells.add(first_1_4);
		
		first_1_5 = new Text(firstMatrixComposite, SWT.BORDER);
		first_1_5.setBounds(165, 37, 25, 21);
		mat1Cells.add(first_1_5);
		
		first_2_0 = new Text(firstMatrixComposite, SWT.BORDER);
		first_2_0.setBounds(10, 64, 25, 21);
		mat1Cells.add(first_2_0);
		
		first_2_1 = new Text(firstMatrixComposite, SWT.BORDER);
		first_2_1.setBounds(41, 64, 25, 21);
		mat1Cells.add(first_2_1);
		
		first_2_2 = new Text(firstMatrixComposite, SWT.BORDER);
		first_2_2.setBounds(72, 64, 25, 21);
		mat1Cells.add(first_2_2);
		
		first_2_3 = new Text(firstMatrixComposite, SWT.BORDER);
		first_2_3.setBounds(103, 64, 25, 21);
		mat1Cells.add(first_2_3);
		
		first_2_4 = new Text(firstMatrixComposite, SWT.BORDER);
		first_2_4.setBounds(134, 64, 25, 21);
		mat1Cells.add(first_2_4);
		
		first_2_5 = new Text(firstMatrixComposite, SWT.BORDER);
		first_2_5.setBounds(165, 64, 25, 21);
		mat1Cells.add(first_2_5);
		
		first_3_0 = new Text(firstMatrixComposite, SWT.BORDER);
		first_3_0.setBounds(10, 91, 25, 21);
		mat1Cells.add(first_3_0);
		
		first_3_1 = new Text(firstMatrixComposite, SWT.BORDER);
		first_3_1.setBounds(41, 91, 25, 21);
		mat1Cells.add(first_3_1);
		
		first_3_2 = new Text(firstMatrixComposite, SWT.BORDER);
		first_3_2.setBounds(72, 91, 25, 21);
		mat1Cells.add(first_3_2);
		
		first_3_3 = new Text(firstMatrixComposite, SWT.BORDER);
		first_3_3.setBounds(103, 91, 25, 21);
		mat1Cells.add(first_3_3);
		
		first_3_4 = new Text(firstMatrixComposite, SWT.BORDER);
		first_3_4.setBounds(134, 91, 25, 21);
		mat1Cells.add(first_3_4);
		
		first_3_5 = new Text(firstMatrixComposite, SWT.BORDER);
		first_3_5.setBounds(165, 91, 25, 21);
		mat1Cells.add(first_3_5);
		
		first_4_0 = new Text(firstMatrixComposite, SWT.BORDER);
		first_4_0.setBounds(10, 118, 25, 21);
		mat1Cells.add(first_4_0);
		
		first_4_1 = new Text(firstMatrixComposite, SWT.BORDER);
		first_4_1.setBounds(41, 118, 25, 21);
		mat1Cells.add(first_4_1);
		
		first_4_2 = new Text(firstMatrixComposite, SWT.BORDER);
		first_4_2.setBounds(72, 118, 25, 21);
		mat1Cells.add(first_4_2);
		
		first_4_3 = new Text(firstMatrixComposite, SWT.BORDER);
		first_4_3.setBounds(103, 118, 25, 21);
		mat1Cells.add(first_4_3);
		
		first_4_4 = new Text(firstMatrixComposite, SWT.BORDER);
		first_4_4.setBounds(134, 118, 25, 21);
		mat1Cells.add(first_4_4);
		
		first_4_5 = new Text(firstMatrixComposite, SWT.BORDER);
		first_4_5.setBounds(165, 118, 25, 21);
		mat1Cells.add(first_4_5);
		
		first_5_0 = new Text(firstMatrixComposite, SWT.BORDER);
		first_5_0.setBounds(10, 145, 25, 21);
		mat1Cells.add(first_5_0);
		
		first_5_1 = new Text(firstMatrixComposite, SWT.BORDER);
		first_5_1.setBounds(41, 145, 25, 21);
		mat1Cells.add(first_5_1);
		
		first_5_2 = new Text(firstMatrixComposite, SWT.BORDER);
		first_5_2.setBounds(72, 145, 25, 21);
		mat1Cells.add(first_5_2);
		
		first_5_3 = new Text(firstMatrixComposite, SWT.BORDER);
		first_5_3.setBounds(103, 145, 25, 21);
		mat1Cells.add(first_5_3);
		
		first_5_4 = new Text(firstMatrixComposite, SWT.BORDER);
		first_5_4.setBounds(134, 145, 25, 21);
		mat1Cells.add(first_5_4);
		
		first_5_5 = new Text(firstMatrixComposite, SWT.BORDER);
		first_5_5.setBounds(165, 145, 25, 21);
		mat1Cells.add(first_5_5);
		
		
		Composite secondMatrixComposite = new Composite(composite_1, SWT.BORDER);
		secondMatrixComposite.setBounds(223, 60, 224, 185);
		
		
		
		second_0_0 = new Text(secondMatrixComposite, SWT.BORDER);
		second_0_0.setBounds(10, 10, 25, 21);
		mat2Cells.add(second_0_0);
		
		second_0_1 = new Text(secondMatrixComposite, SWT.BORDER);
		second_0_1.setBounds(41, 10, 25, 21);
		mat2Cells.add(second_0_1);
		
		second_0_2 = new Text(secondMatrixComposite, SWT.BORDER);
		second_0_2.setBounds(72, 10, 25, 21);
		mat2Cells.add(second_0_2);

		second_0_3 = new Text(secondMatrixComposite, SWT.BORDER);
		second_0_3.setBounds(103, 10, 25, 21);
		mat2Cells.add(second_0_3);
		
		second_0_4 = new Text(secondMatrixComposite, SWT.BORDER);
		second_0_4.setBounds(134, 10, 25, 21);
		mat2Cells.add(second_0_4);
		
		second_0_5 = new Text(secondMatrixComposite, SWT.BORDER);
		second_0_5.setBounds(165, 10, 25, 21);
		mat2Cells.add(second_0_5);
		
		second_1_0 = new Text(secondMatrixComposite, SWT.BORDER);
		second_1_0.setBounds(10, 37, 25, 21);
		mat2Cells.add(second_1_0);

		second_1_1 = new Text(secondMatrixComposite, SWT.BORDER);
		second_1_1.setBounds(41, 37, 25, 21);
		mat2Cells.add(second_1_1);
		
		second_1_2 = new Text(secondMatrixComposite, SWT.BORDER);
		second_1_2.setBounds(72, 37, 25, 21);
		mat2Cells.add(second_1_2);
		
		second_1_3 = new Text(secondMatrixComposite, SWT.BORDER);
		second_1_3.setBounds(103, 37, 25, 21);
		mat2Cells.add(second_1_3);
		
		second_1_4 = new Text(secondMatrixComposite, SWT.BORDER);
		second_1_4.setBounds(134, 37, 25, 21);
		mat2Cells.add(second_1_4);
		
		second_1_5 = new Text(secondMatrixComposite, SWT.BORDER);
		second_1_5.setBounds(165, 37, 25, 21);
		mat2Cells.add(second_1_5);
		
		second_2_0 = new Text(secondMatrixComposite, SWT.BORDER);
		second_2_0.setBounds(10, 64, 25, 21);
		mat2Cells.add(second_2_0);
		
		second_2_1 = new Text(secondMatrixComposite, SWT.BORDER);
		second_2_1.setBounds(41, 64, 25, 21);
		mat2Cells.add(second_2_1);
		
		second_2_2 = new Text(secondMatrixComposite, SWT.BORDER);
		second_2_2.setBounds(72, 64, 25, 21);
		mat2Cells.add(second_2_2);
		
		second_2_3 = new Text(secondMatrixComposite, SWT.BORDER);
		second_2_3.setBounds(103, 64, 25, 21);
		mat2Cells.add(second_2_3);
		
		second_2_4 = new Text(secondMatrixComposite, SWT.BORDER);
		second_2_4.setBounds(134, 64, 25, 21);
		mat2Cells.add(second_2_4);
		
		second_2_5 = new Text(secondMatrixComposite, SWT.BORDER);
		second_2_5.setBounds(165, 64, 25, 21);
		mat2Cells.add(second_2_5);

		second_3_0 = new Text(secondMatrixComposite, SWT.BORDER);
		second_3_0.setBounds(10, 91, 25, 21);
		mat2Cells.add(second_3_0);
		
		second_3_1 = new Text(secondMatrixComposite, SWT.BORDER);
		second_3_1.setBounds(41, 91, 25, 21);
		mat2Cells.add(second_3_1);
		
		second_3_2 = new Text(secondMatrixComposite, SWT.BORDER);
		second_3_2.setBounds(72, 91, 25, 21);
		mat2Cells.add(second_3_2);
		
		second_3_3 = new Text(secondMatrixComposite, SWT.BORDER);
		second_3_3.setBounds(103, 91, 25, 21);
		mat2Cells.add(second_3_3);
		
		second_3_4 = new Text(secondMatrixComposite, SWT.BORDER);
		second_3_4.setBounds(134, 91, 25, 21);
		mat2Cells.add(second_3_4);
		
		second_3_5 = new Text(secondMatrixComposite, SWT.BORDER);
		second_3_5.setBounds(165, 91, 25, 21);
		mat2Cells.add(second_3_5);
		
		second_4_0 = new Text(secondMatrixComposite, SWT.BORDER);
		second_4_0.setBounds(10, 118, 25, 21);
		mat2Cells.add(second_4_0);

		second_4_1 = new Text(secondMatrixComposite, SWT.BORDER);
		second_4_1.setBounds(41, 118, 25, 21);
		mat2Cells.add(second_4_1);
		
		second_4_2 = new Text(secondMatrixComposite, SWT.BORDER);
		second_4_2.setBounds(72, 118, 25, 21);
		mat2Cells.add(second_4_2);
		
		second_4_3 = new Text(secondMatrixComposite, SWT.BORDER);
		second_4_3.setBounds(103, 118, 25, 21);
		mat2Cells.add(second_4_3);
		
		second_4_4 = new Text(secondMatrixComposite, SWT.BORDER);
		second_4_4.setBounds(134, 118, 25, 21);
		mat2Cells.add(second_4_4);
		
		second_4_5 = new Text(secondMatrixComposite, SWT.BORDER);
		second_4_5.setBounds(165, 118, 25, 21);
		mat2Cells.add(second_4_5);
		
		second_5_0 = new Text(secondMatrixComposite, SWT.BORDER);
		second_5_0.setBounds(10, 145, 25, 21);
		mat2Cells.add(second_5_0);
		
		second_5_1 = new Text(secondMatrixComposite, SWT.BORDER);
		second_5_1.setBounds(41, 145, 25, 21);
		mat2Cells.add(second_5_1);
		
		second_5_2 = new Text(secondMatrixComposite, SWT.BORDER);
		second_5_2.setBounds(72, 145, 25, 21);
		mat2Cells.add(second_5_2);
		
		second_5_3 = new Text(secondMatrixComposite, SWT.BORDER);
		second_5_3.setBounds(103, 145, 25, 21);
		mat2Cells.add(second_5_3);
		
		second_5_4 = new Text(secondMatrixComposite, SWT.BORDER);
		second_5_4.setBounds(134, 145, 25, 21);
		mat2Cells.add(second_5_4);
		
		second_5_5 = new Text(secondMatrixComposite, SWT.BORDER);
		second_5_5.setBounds(165, 145, 25, 21);
		mat2Cells.add(second_5_5);
		
		}///matrix Gui data end 
		
		CLabel lblFirstMatrix = new CLabel(composite_1, SWT.NONE);
		lblFirstMatrix.setBounds(10, 10, 78, 21);
		lblFirstMatrix.setText("First Matrix:");
		
		CLabel label_2 = new CLabel(composite_1, SWT.NONE);
		label_2.setBounds(10, 33, 42, 21);
		label_2.setText("Rows:");
		
		CLabel lblColumns = new CLabel(composite_1, SWT.NONE);
		lblColumns.setBounds(103, 33, 61, 21);
		lblColumns.setText("Columns:");
		
		CCombo cmbFirstMtxRow = new CCombo(composite_1, SWT.BORDER | SWT.READ_ONLY);
		cmbFirstMtxRow.setBounds(58, 33, 39, 21);
		cmbFirstMtxRow.setTouchEnabled(true);
		cmbFirstMtxRow.setEditable(false);
		cmbFirstMtxRow.setVisibleItemCount(8);
		cmbFirstMtxRow.setListVisible(true);
		cmbFirstMtxRow.setItems(new String[] {"1", "2", "3", "4", "5", "6"});
		cmbFirstMtxRow.select(0);
		cmbFirstMtxRow.addListener(SWT.Selection, new Listener(){
		      public void handleEvent(Event e) {
		    	  if(e.type == SWT.Selection) {
		        	  M1_ROW = cmbFirstMtxRow.getSelectionIndex()+1;
		          }
		        }
		      });
		
		CCombo cmbFirstMtxCol = new CCombo(composite_1, SWT.BORDER);
		cmbFirstMtxCol.setBounds(170, 33, 39, 21);
		cmbFirstMtxCol.setEditable(false);
		cmbFirstMtxCol.setListVisible(true);
		cmbFirstMtxCol.setVisibleItemCount(8);
		cmbFirstMtxCol.setItems(new String[] {"1", "2", "3", "4", "5", "6"});
		cmbFirstMtxCol.select(0);
		cmbFirstMtxCol.addListener(SWT.Selection, new Listener(){
		      public void handleEvent(Event e) {
		    	  if(e.type == SWT.Selection) {
		        	  M1_COL = cmbFirstMtxCol.getSelectionIndex()+1;
		          }
		        }
		      });
		
		CCombo cmbSecondMtxCol = new CCombo(composite_1, SWT.BORDER);
		cmbSecondMtxCol.setBounds(398, 33, 39, 21);
		cmbSecondMtxCol.setListVisible(true);
		cmbSecondMtxCol.setEditable(false);
		cmbSecondMtxCol.setVisibleItemCount(8);
		cmbSecondMtxCol.setItems(new String[] {"1", "2", "3", "4", "5", "6"});
		cmbSecondMtxCol.select(0);
		cmbSecondMtxCol.addListener(SWT.Selection, new Listener(){
		      public void handleEvent(Event e) {
		    	  if(e.type == SWT.Selection) {
		        	  M2_COL = cmbSecondMtxCol.getSelectionIndex()+1;
		          }
		        }
		      });
		
		CLabel lblColumns_1 = new CLabel(composite_1, SWT.NONE);
		lblColumns_1.setBounds(330, 33, 62, 21);
		lblColumns_1.setText("Columns:");
		
		CCombo cmbSecondMtxRow = new CCombo(composite_1, SWT.BORDER);
		cmbSecondMtxRow.setBounds(285, 33, 39, 21);
		cmbSecondMtxRow.setEditable(false);
		cmbSecondMtxRow.setVisibleItemCount(8);
		cmbSecondMtxRow.setListVisible(true);
		cmbSecondMtxRow.setItems(new String[] {"1", "2", "3", "4", "5", "6"});
		cmbSecondMtxRow.select(0);
		cmbSecondMtxRow.addListener(SWT.Selection, new Listener(){
		      public void handleEvent(Event e) {
		    	  if(e.type == SWT.Selection) {
		        	  M2_ROW = cmbSecondMtxRow.getSelectionIndex()+1;
		          }
		        }
		      });
		
		CLabel label_6 = new CLabel(composite_1, SWT.NONE);
		label_6.setBounds(237, 33, 42, 21);
		label_6.setText("Rows:");
		
		CLabel lblSecondMatrix = new CLabel(composite_1, SWT.NONE);
		lblSecondMatrix.setBounds(237, 8, 88, 21);
		lblSecondMatrix.setText("Second Matrix:");
		
		Label label_1 = new Label(composite_1, SWT.SEPARATOR | SWT.VERTICAL);
		label_1.setBounds(223, 0, 2, 64);
		
		btnSendData = new Button(shell, SWT.NONE);
		fd_composite_1.bottom = new FormAttachment(btnSendData, -6);
		FormData fd_btnSendData = new FormData();
		fd_btnSendData.top = new FormAttachment(0, 399);
		fd_btnSendData.left = new FormAttachment(0, 206);
		btnSendData.setLayoutData(fd_btnSendData);
		btnSendData.setText("Send Data");
		btnSendData.addListener(SWT.Selection, new Listener(){
		      public void handleEvent(Event e) {
		          if(e.type == SWT.Selection) {
		        	  sendDataToServer();
		          }
		        }
		      });
		
		
		resultMatrixLbl = new CLabel(shell, SWT.NONE);
		resultMatrixLbl.setBackground(SWTResourceManager.getColor(SWT.COLOR_LIST_BACKGROUND));
		FormData fd_resultMatrixLbl = new FormData();
		fd_resultMatrixLbl.bottom = new FormAttachment(btnSendData, 122, SWT.BOTTOM);
		fd_resultMatrixLbl.top = new FormAttachment(btnSendData, 1);
		fd_resultMatrixLbl.right = new FormAttachment(100, -144);
		fd_resultMatrixLbl.left = new FormAttachment(0, 149);
		resultMatrixLbl.setLayoutData(fd_resultMatrixLbl);
		resultMatrixLbl.setText("");
		
		
		
		M1_ROW = cmbFirstMtxRow.getSelectionIndex()+1;
		M1_COL = cmbFirstMtxCol.getSelectionIndex()+1;
		M2_ROW = cmbSecondMtxRow.getSelectionIndex()+1;
		M2_COL = cmbSecondMtxCol.getSelectionIndex()+1;
		
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		System.out.println("This message should appear after program closing!");
	}
	///check server availability
	private static void checkServer() {
		DatagramSocket aSocket = null;
		InetAddress aHost = null;
		int serverPort=0;
		serverStatusText.setText("Server is not reachable!");
		serverStatusText.setBackground(SWTResourceManager.getColor(SWT.COLOR_MAGENTA));
		MessageBox dialog;
        try {
              if(serverIPtext.getText().isEmpty()) {
            	  aHost = InetAddress.getLocalHost();
              }
              else {
            	  try {
            		  aHost = InetAddress.getByName(serverIPtext.getText());
            	  }catch(Exception e){
            		  	dialog = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
                  		dialog.setText("Invalid IP");
                  		dialog.setMessage("Please check IP");
                  		dialog.open();
                  		return;
            	  }
              }
              
              if(serverPORTtext.getText().isEmpty()) {
            	  serverPort = 9996;
              }
              else {
            	  try {
            		  serverPort = Integer.parseInt(serverPORTtext.getText());
            	  }catch(Exception e){
            		  	dialog = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
                  		dialog.setText("Invalid port");
                  		dialog.setMessage("Please check port number");
                  		dialog.open();
                  		return;
            	  }
              }
              aSocket = new DatagramSocket();
              
              String s = "Are you alive?";
              aSocket.connect(aHost,serverPort);
              DatagramPacket request = new DatagramPacket(s.getBytes(),  s.length(),
            		  										aHost, serverPort);
              aSocket.send(request);
              byte[] buffer = new byte[20];
              DatagramPacket reply = new DatagramPacket(buffer, buffer.length);
              aSocket.receive(reply);
              s = new String(reply.getData()).toString().substring(0,reply.getLength());
              if(s.equals("Server is up!")) {
            	  serverStatusText.setText(s);
            	  serverStatusText.setBackground(SWTResourceManager.getColor(SWT.COLOR_GREEN));
              }
              //System.out.println("Reply: " + s);
        }
        catch (SocketException e){
        	System.out.println("ClientSocket: " + e.getMessage());
        	dialog = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
        	dialog.setText("ClientSocket");
        	dialog.setMessage(e.getMessage());
        	dialog.open();
        }
        catch (IOException e){
        	System.out.println("IO: " + e.getMessage());
        	dialog = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
        	dialog.setText("IO");
        	dialog.setMessage(e.getMessage());
        	dialog.open();
        }
        finally
      {
           if(aSocket != null) aSocket.close();
      }
	}
	
	///Send message to server
		private static void sendDataToServer() {
				checkServer();
				DatagramSocket aSocket = null;
				MessageBox dialog;
				String requestString;
				String replyMessage;
				DatagramPacket request;
				DatagramPacket reply;
				InetAddress aHost = null;
				int serverPort=0;
	            byte[] buffer = new byte[1000];
		        try {
		              if(serverIPtext.getText().isEmpty()) {
		            	  aHost = InetAddress.getLocalHost();
		              }
		              else {
		            	  try {
		            		  aHost = InetAddress.getByName(serverIPtext.getText());
		            	  }catch(Exception e){
		            		  	dialog = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
		                  		dialog.setText("Invalid IP");
		                  		dialog.setMessage("Please check IP");
		                  		dialog.open();
		                  		return;
		            	  }
		              }
		              
		              if(serverPORTtext.getText().isEmpty()) {
		            	  serverPort = 9996;
		              }
		              else {
		            	  try {
		            		  serverPort = Integer.parseInt(serverPORTtext.getText());
		            	  }catch(Exception e){
		            		  	dialog = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
		                  		dialog.setText("Invalid port");
		                  		dialog.setMessage("Please check port number");
		                  		dialog.open();
		                  		return;
		            	  }
		              }
		              aSocket = new DatagramSocket();
		              aSocket.connect(aHost,serverPort);
		              
		              requestString = functionCode + " " + M1_ROW + " " + M1_COL
		            		  + " " + M2_ROW + " " + M2_COL + " ";
		              String validatedMatrixInputString = validateInputsAndMakeMessage();
		              
		              if(validatedMatrixInputString == null) {
		            	  return;
		              }
		              requestString += validatedMatrixInputString;
		              
		              System.out.println("requestString:" + requestString);
		              
		              request = new DatagramPacket(requestString.getBytes(),
		            		  requestString.length(), aHost, serverPort);
		              aSocket.send(request);
		              
		              reply = new DatagramPacket(buffer, buffer.length);
		              aSocket.receive(reply);
		              replyMessage = new String(reply.getData()).toString().substring(0,reply.getLength());
		              
		              if(replyMessage.contains("Error")) {
		            	    dialog = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
		            	  	dialog.setText("ERROR");
				        	dialog.setMessage(replyMessage);
				        	dialog.open();
		              }
		              else {
		            	  Scanner scanner = new Scanner(replyMessage);
		            	  String resultMatrixString = "";
		            	  if(scanner.hasNext()) {
		            		  int mat3row = scanner.nextInt();
		            		  int mat3col = scanner.nextInt();
		            		  for( int i=0; i<mat3row; i++) {
		            			  for( int j=0; j<mat3col; j++) {
		            				  resultMatrixString +=  String.format( "%8s", scanner.nextInt()) ;
		            			  }
		            			  resultMatrixString +=  "\n";
		            		  }
		            	  }
		            	  scanner.close();
		            	  /*dialog = new MessageBox(shell, SWT.OK);
		            	  dialog.setText("Result Matrix:");
				          dialog.setMessage(resultMatrixString);
				          dialog.open();*/
		            	  resultMatrixLbl.setText(resultMatrixString);
		              }
		              
		        }
		        catch (SocketException e){
		        	System.out.println("ClientSocket: " + e.getMessage());
		        	dialog = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
		        	dialog.setText("ClientSocket");
		        	dialog.setMessage(e.getMessage());
		        	dialog.open();
		        }
		        catch (IOException e){
		        	System.out.println("IO: " + e.getMessage());
		        	dialog = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
		        	dialog.setText("IO");
		        	dialog.setMessage(e.getMessage());
		        	dialog.open();
		        }
		        finally
		      {
		           if(aSocket != null) aSocket.close();
		      }
			
		}
	
	//validate matrix input
	public static String validateInputsAndMakeMessage() {
		int k=0;
		int mat=0;
		String dataString = "";
		resultMatrixLbl.setText("");
		try {
			mat++;
			for(int i=0; i<M1_ROW; i++) {
				for(int j=0; j<M1_COL; j++) {
					int temp = Integer.parseInt(mat1Cells.get(k++).getText());
					dataString += temp + " ";
					//System.out.print(temp + " ");
				}
				k = k + 6 - M1_COL;
				//System.out.println();
			}
			k=0;
			mat++;
			for(int i=0; i<M2_ROW; i++) {
				for(int j=0; j<M2_COL; j++) {
					int temp = Integer.parseInt(mat2Cells.get(k++).getText());
					dataString += temp + " ";
					//System.out.print(temp + " ");
				}
				k = k + 6 - M2_COL;
				//System.out.println();
			}
		}
		catch(Exception e){
			MessageBox dialog = new MessageBox(shell, SWT.ICON_ERROR | SWT.OK);
        	dialog.setText("Validation Error");
        	dialog.setMessage("Validation Error in Matrix{" + mat + "} "+ k + " cell");
        	dialog.open();
        	return null;
		}
		//System.out.println(dataString);
		return dataString;
	}
}
