/* MATTHEOS KALKOUNIS AM: p3110065  
   KONSTANTINA LIAKOU AM: p3150092
   PANAGIOTIS MILIOS AM: p3150109*/





import java.awt.*;       
import java.awt.event.*; 
import javax.swing.*;   
import javax.swing.event.*;
import java.io.*;
import java.lang.*;
import java.util.*; 

public class mainApp extends JFrame implements ListSelectionListener,MouseListener,ActionListener
{
    public static String fullname,phone,date,ddate,totalcost;
	private DefaultListModel listModel1,listModel2,listModel3,listModel4;
	private static JList list1 = new JList();
	private static JList list2 = new JList();
	private static JList list3 = new JList();
	private static JList list4 = new JList();
	private JButton but1,but2,but3;
	private static ArrayList<Order> orders = new ArrayList<Order>();
	private static ArrayList<Sales> sales = new ArrayList<Sales>();
	private static ArrayList<Integer> availability = new ArrayList<Integer>();
	private static ArrayList<Products1> products = new ArrayList<Products1>();
	private static JPanel jp1,jp2,paneButton,paneButton1;
	private JTextArea resultArea = new JTextArea("Product details: \nPlease select a Product to view its specs\n",10,20);
	private JTextPane resultArea1;
	private static JScrollPane listScroller2,listScroller3,listScroller4;
	private static int[] position = new int[6];
	
	public static int productsSaleCode=0;                
	public static int productsOrderCode=0;
	private int a1,a2,a3,a4,a5,a6,pointer,k,number,number1,number2;
	private String line,line2;
	private Products1 Myproduct;
	private BufferedReader reader,reader2;
	private File f;
	
	private JRadioButtonMenuItem[] OpenFile;
	private JLabel displayJLabel;
	private ButtonGroup OpenFileButtonGroup;
	
    public mainApp() 
	{
		 
		 
		 
		 
		 
		 
        setTitle("Menu");
		
		JMenu fileMenu = new JMenu("File");
		fileMenu.setMnemonic('F');
		/////
		JFileChooser fileChooser = new JFileChooser();
		
		////
		//pinakas pou emfanizei onomata
		String[] OpenFileNames = {"Load Sales File", "Load Orders File", "Load Available Products File"};
		JMenu OpenFileMenu = new JMenu("Options");
		OpenFileMenu.setMnemonic('p');
		
		//dhmhourgei stoixeia koumpiwn epioghs gia ta onomata
		OpenFile = new JRadioButtonMenuItem[OpenFileNames.length];
		OpenFileButtonGroup = new ButtonGroup(); //diaxeirizetai ta onomata
		
		//dhmiourgei stoixeia koumpiwn epiloghs menu OpenFile
		for (int count = 0; count < OpenFileNames.length; count++){
			OpenFile[count] = new JRadioButtonMenuItem(OpenFileNames[count]);
			OpenFileMenu.add(OpenFile[count]);
			OpenFileButtonGroup.add(OpenFile[count]);
		}
		OpenFile[0].addActionListener(this);
		OpenFile[1].addActionListener(this);
		OpenFile[2].addActionListener(this); 
		
		fileMenu.add(OpenFileMenu);
		
		JMenuItem exitItem = new JMenuItem("Exit");
		exitItem.setMnemonic('x');
		fileMenu.add(exitItem);
		exitItem.addActionListener(
			new ActionListener () {
				public void actionPerformed(ActionEvent e){
					System.exit(0);
				}
			}
		);
		
		
		JMenuBar bar = new JMenuBar();
		setJMenuBar(bar);
		bar.add(fileMenu); 
		
		
		
		
		
        JTabbedPane jtp = new JTabbedPane();
		setSize(600,600);
        getContentPane().add(jtp);
        jp1 = new JPanel(); // Upo-upodoxeas Noumero 1 (Available Devices)
		jp1.setLayout(new BorderLayout());
		JPanel jp3 = new JPanel(); // Upo-upodoxeas Noumero 3
		
		listModel1 = new DefaultListModel();
		listModel1.addElement("TV");
		listModel1.addElement("BRDVD");
		listModel1.addElement("Camera");
		listModel1.addElement("Gaming");
		listModel1.addElement("Washingmachine");
		listModel1.addElement("Fridges");
		list1 = new JList(listModel1);
		list1.addListSelectionListener(this);
		JScrollPane listScroller1 = new JScrollPane(list1); 
		listScroller1.setPreferredSize(new Dimension(100, 100));  
		jp1.add(listScroller1, BorderLayout.WEST);
		
		listModel2 = new DefaultListModel();
		list2 = new JList(listModel2);
		list2.addMouseListener(this);
		listScroller2 = new JScrollPane(list2); 
		listScroller2.setPreferredSize(new Dimension(100,100));  
		jp1.add(listScroller2, BorderLayout.CENTER); 
		
	
		
		resultArea.setFont(new Font("Serif", Font.ITALIC, 18)); // orizoume to result area  (ekei pou emfanizontai ta stoixeia twn Products)
		resultArea.setForeground(Color.BLUE);
		resultArea.setEditable(false); 
		jp1.add(resultArea, BorderLayout.EAST);
		jtp.addTab("Available Devices", jp1);
        
		
        jp2 = new JPanel(); // Upo-upodoxeas Noumero 2 (Orders)
		jp2.setLayout(new GridLayout(0,2));
		listModel3 = new DefaultListModel();
		for (Order i : orders)
		{
			listModel3.addElement(i.getOcode());
		}
		list3 = new JList(listModel3);
		JScrollPane listScroller3 = new JScrollPane(list3); 
		listScroller3.setPreferredSize(new Dimension(100, 600));  
		jp2.add(listScroller3, new FlowLayout());
		
		but1 = new JButton("Click to change the status of an Order");
		but2 = new JButton("Search for an Order");
		but1.setPreferredSize(new Dimension(300, 100));
		but2.setPreferredSize(new Dimension(300, 100));
		but1.addActionListener(this);
		but2.addActionListener(this);
		paneButton = new JPanel();
		paneButton.setLayout(new GridLayout(4,0));
		paneButton.add(but1);
		paneButton.add(but2);
		jp2.add(paneButton,new GridLayout());
		jtp.addTab("Orders", jp2);
		
		
		jp3 = new JPanel(); // Upo-upodoxeas Noumero 3
		jp3.setLayout(new GridLayout(0,2));
		listModel4 = new DefaultListModel();
		for (Sales i1 : sales)
		{
			listModel4.addElement(i1.getScode());
		}
		list4 = new JList(listModel4);
		JScrollPane listScroller4 = new JScrollPane(list4); 
		listScroller4.setPreferredSize(new Dimension(100, 600));  
		jp3.add(listScroller4, new FlowLayout());
		but3 = new JButton("Search for a sale");
		but3.setPreferredSize(new Dimension(300,100));
		but3.addActionListener(this);
		paneButton1 = new JPanel();
		paneButton1.setLayout(new GridLayout(4,0));
		paneButton1.add(but3);
		jp3.add(paneButton1,new GridLayout());
        jtp.addTab("Sales", jp3);
         
	}
	
	public void actionPerformed(ActionEvent e) 
	{
		int i,i1;
		int j=0;
		int j1=0;
		i = list3.getSelectedIndex();
		i1 = list4.getSelectedIndex();
		String date1,name1,name2;
		if ( e.getSource() == but1 && i>=0 )
		{
			date1 = (String)JOptionPane.showInputDialog(this, "Please type the date of the Sale",Calendar.getInstance().getTime());
			orders.get(i).setFlag(true);
		}
		else if ( e.getSource() == but2 )
		{
			name1 = (String)JOptionPane.showInputDialog(this, "Please give me the name of the Client");
			for (Order k1 : orders)
			{
				if ( name1.equalsIgnoreCase(k1.getFullname()) ) 
				{
					JOptionPane.showMessageDialog(null,orders.get(j));
				}
				j=j+1;
			}
		}
		else if ( e.getSource() == but3 )
		{
			name2 = (String)JOptionPane.showInputDialog(this, "Please give me the name of the Client");
			for (Sales k2 : sales)
			{
				if (name2.equalsIgnoreCase(k2.getFullName()))
				{
					JOptionPane.showMessageDialog(null,sales.get(j1));
				}
				j1 = j1 + 1;
			}
		}
		else if (e.getSource() == OpenFile[0] )
		{
			JFileChooser chooser = new JFileChooser();
			chooser.setMultiSelectionEnabled(true);
			int option = chooser.showOpenDialog(mainApp.this);
			if (option == JFileChooser.APPROVE_OPTION) 
			{
				File[] sf = chooser.getSelectedFiles();
				String filelist = "nothing";
				if (sf.length > 0) filelist = sf[0].getName();
				for (int ip = 1; ip < sf.length; ip++) 
				{
					filelist += ", " + sf[ip].getName();
				}
				File f = null;
				BufferedReader reader = null;
				BufferedReader reader2 = null;
				Sales Mysale = new Sales();
				String line,line2;
				int pointer = 0;
				if (filelist.equals("Sale.txt"))
				{
					try
					{
						f = new File(filelist);
						
					}
					
					catch (NullPointerException evt)
					{
						 JOptionPane.showMessageDialog(null,"File not found.");
					}
					JOptionPane.showMessageDialog(null,"You choose " + filelist);
				}
				else
				{
					JOptionPane.showMessageDialog(null,"Wrong File");
				}
				try
				{
					reader = new BufferedReader( new FileReader(f) );
					reader2 = new BufferedReader ( new FileReader(f) );
				}
				catch (FileNotFoundException evt) 
				{
					JOptionPane.showMessageDialog(null,"Error opening file!");
				}
				try 
				{
					line = reader.readLine();
					line2 = reader2.readLine();
					if (!line.trim().equals(" ")) 
						{
							if (line.trim().equalsIgnoreCase("Saleslist")) 
							{
								line = reader.readLine();
								line2 = reader2.readLine();
								if (line != null && line2 !=null ) 
									{
										if (line.trim().equals("{")) 
										{
											while (line != null) 
											{
												line = reader.readLine();
												line2 = reader2.readLine();
												if (line != null && line2 !=null) 
												{
													if (line.trim().equalsIgnoreCase("Sale"))
													{
														line = reader.readLine();
														line2 = reader2.readLine();
														if (line != null && line2 !=null)
														if (line.trim().equals("{"))
														{
															line2 = reader2.readLine();
															
															while (!line2.trim().equals("}") )
															{
																if ( line2.trim().toLowerCase().startsWith("ITeM_tYpe".toLowerCase()) )
																{
																	if ( line2.trim().substring(9).trim().equalsIgnoreCase("tv") )
																	{
																		if ( availability.get(0) > 0 )
																		{
																			Mysale = new Sales(); 
																		}
																		else
																		{
																			pointer = 1;
																		}																	
																	}
																	else if ( line2.trim().substring(9).trim().equalsIgnoreCase("brdvd") )
																	{
																		if ( availability.get(1) > 0 )
																		{
																			Mysale = new Sales();
																		}
																		else
																		{
																			pointer = 1;
																		}				
																	}
																	else if ( line2.trim().substring(9).trim().equalsIgnoreCase("camera") )
																	{
																		if ( availability.get(2) > 0 )
																		{
																			Mysale = new Sales();
																		}
																		else
																		{
																			pointer = 1;
																		}				
																	}
																	else if ( line2.trim().substring(9).trim().equalsIgnoreCase("Gaming") )
																	{
																		if ( availability.get(3) > 0 )
																		{
																			Mysale = new Sales();
																		}
																		else
																		{
																			pointer = 1;
																		}				
																	}
																	else if ( line2.trim().substring(9).trim().equalsIgnoreCase("Washingmachine") )
																	{
																		if ( availability.get(4) > 0 )
																		{
																			Mysale = new Sales();
																		}
																		else
																		{
																			pointer = 1;
																		}				
																	}
																	else if ( line2.trim().substring(9).trim().equalsIgnoreCase("refrigerator") )
																	{
																		if ( availability.get(5) > 0 )
																		{
																			Mysale = new Sales();
																		}
																		else
																		{
																			pointer = 1;
																		}				
																	}
																}
																line2 = reader2.readLine();
															}
															line = reader.readLine();
															if ( pointer == 0 )
															{
																while (!line.trim().equals("}"))
																		{
																			if ( line.trim().toLowerCase().startsWith("Item_tYPe".toLowerCase()) )
																			{
																				Mysale.setProductSale(line.trim().substring(9).trim());
																			}
																			else if ( line.trim().toLowerCase().startsWith("Sales_NumbeR".toLowerCase()) )
																			{
																				
																				Mysale.setScode(line.trim().substring(13).trim());
																			}
																			else if ( line.trim().toLowerCase().startsWith("Name".toLowerCase()) )
																			{
																				
																				Mysale.setFullName(line.trim().substring(5).trim());
																			}
																			else if ( line.trim().toLowerCase().startsWith("Phone".toLowerCase()) )
																			{
																				
																				Mysale.setPhone(line.trim().substring(6).trim());
																			}
																			else if ( line.trim().toLowerCase().startsWith("Sale_Date".toLowerCase()) )
																			{
																				
																				Mysale.setDate(line.trim().substring(10).trim());
																			}
																			else if ( line.trim().toLowerCase().startsWith("Price".toLowerCase()) )
																			{
																				
																				Mysale.setTotalCost(line.trim().substring(6).trim());
																			
																			}
																			
																			line = reader.readLine();
																		}
																		sales.add(Mysale);
																		productsSaleCode++;
																		listModel4.addElement(Mysale.getScode());
																		
															}
															else 
															{
																JOptionPane.showMessageDialog(null,"No available items");
															}
														}
													}
												}
											}
										}
									}
							}
						line = reader.readLine();
						}
				}
				catch (IOException evt)
				{
					JOptionPane.showMessageDialog(null,"Error reading line!");
				}	
				try 
				{
					reader.close();
				} 
				catch (IOException evt) 
				{
					JOptionPane.showMessageDialog(null,"Error closing file.");
				}
			}
			else 
			{
				JOptionPane.showMessageDialog(null,"You cancelled");
			}
		}
		else if (e.getSource() == OpenFile[1] )
		{
			JFileChooser chooser = new JFileChooser();
			chooser.setMultiSelectionEnabled(true);
			int option = chooser.showOpenDialog(mainApp.this);
		if (option == JFileChooser.APPROVE_OPTION) 
		{
				File[] sf = chooser.getSelectedFiles();
				String filelist = "nothing";
				if (sf.length > 0) filelist = sf[0].getName();
				for (int ip = 1; ip < sf.length; ip++) 
				{
					filelist += ", " + sf[ip].getName();
				}
	
				File f = null;
				BufferedReader reader = null;
				BufferedReader reader2 = null;
				Order Myorder = null;
				String line,line2,temp;
				int pointer = 0;
				if (filelist.equals("Order.txt"))
				{
					try
					{
						f = new File(filelist);
						
					}
					
					catch (NullPointerException ev)
					{
						JOptionPane.showMessageDialog(null,"File not Found");
					}
					JOptionPane.showMessageDialog(null,"You chose " + filelist);
				}
				else
				{
					JOptionPane.showMessageDialog(null,"Wrong File");
				}
				try
				{
					reader = new BufferedReader( new FileReader(f) );
					reader2 = new BufferedReader ( new FileReader(f) );
				}
				catch (FileNotFoundException ev) 
				{
					JOptionPane.showMessageDialog(null,"Error opening file!");
				}
				try 
				{
					line = reader.readLine();
					line2 = reader2.readLine();
					if (!line.trim().equals(" ")) 
						{
							if (line.trim().equals("Orderlist")) 
							{
								line = reader.readLine();
								line2 = reader2.readLine();
								if (line != null && line2 !=null ) 
									{
										if (line.trim().equals("{")) 
										{
											while (line != null) 
											{
												line = reader.readLine();
												line2 = reader2.readLine();
												if (line != null && line2 !=null) 
												{
													if (line.trim().equals("Order"))
													{
														line = reader.readLine();
														line2 = reader2.readLine();
														if (line != null && line2 !=null)
														if (line.trim().equals("{"))
														{
															line2 = reader2.readLine();
															
															while (!line2.trim().equals("}") )
															{
																if ( line2.trim().toLowerCase().startsWith("ITeM_tYpe".toLowerCase()) )
																{
																	if ( line2.trim().substring(9).trim().equalsIgnoreCase("tv") )
																	{
																		if ( availability.get(0) > 0 )
																		{
																			Myorder = new Order();
																		}
																		else
																		{
																			pointer = 1;
																		}																	
																	}
																	else if ( line2.trim().substring(9).trim().equalsIgnoreCase("brdvd") )
																	{
																		if ( availability.get(1) > 0 )
																		{
																			Myorder = new Order();
																		}
																		else
																		{
																			pointer = 1;
																		}				
																	}
																	else if ( line2.trim().substring(9).trim().equalsIgnoreCase("camera") )
																	{
																		if ( availability.get(2) > 0 )
																		{
																			Myorder = new Order();
																		}
																		else
																		{
																			pointer = 1;
																		}				
																	}
																	else if ( line2.trim().substring(9).trim().equalsIgnoreCase("gaming") )
																	{
																		if ( availability.get(3) > 0 )
																		{
																			Myorder = new Order();
																		}
																		else
																		{
																			pointer = 1;
																		}				
																	}
																	else if ( line2.trim().substring(9).trim().equalsIgnoreCase("Washingmachine") )
																	{
																		if ( availability.get(4) > 0 )
																		{
																			Myorder = new Order();
																		}
																		else
																		{
																			pointer = 1;
																		}				
																	}
																	else if ( line2.trim().substring(9).trim().equalsIgnoreCase("refrigerator") )
																	{
																		if ( availability.get(5) > 0 )
																		{
																			Myorder = new Order();
																		}
																		else
																		{
																			pointer = 1;
																		}				
																	}
																}
																line2 = reader2.readLine();
															}
															line = reader.readLine();
															
															if ( pointer == 0 )
															{
																while (!line.trim().equals("}"))
																		{
																			if ( line.trim().toLowerCase().startsWith("Item_tYPe".toLowerCase()) )
																			{
																				
																				 Myorder.setProductor(line.trim().substring(9).trim());
																			}
																			else if ( line.trim().toLowerCase().startsWith("Order_number".toLowerCase()) )
																			{
																				
																				Myorder.setOcode(line.trim().substring(13).trim());
																			}
																			else if ( line.trim().toLowerCase().startsWith("NAME".toLowerCase()) )
																			{
																				
																				Myorder.setFullname(line.trim().substring(5).trim());
																			}
																			else if ( line.trim().toLowerCase().startsWith("PhonE".toLowerCase()) )
																			{
																				
																				Myorder.setPhone(line.trim().substring(6).trim());
																			}
																			else if ( line.trim().toLowerCase().startsWith("Order_datE".toLowerCase()) )
																			{
																				
																				Myorder.setOrderdate(line.trim().substring(11).trim());
																			}
																			else if ( line.trim().toLowerCase().startsWith("Delivery_date".toLowerCase()) )
																			{
																				
																				Myorder.setArrivaldate(line.trim().substring(14).trim());
																			}
																			else if ( line.trim().toLowerCase().startsWith("Price".toLowerCase()) )
																			{
																				
																				Myorder.setCost(line.trim().substring(6).trim());
																			}
																			else if ( line.trim().toLowerCase().startsWith("Status".toLowerCase()) )
																			{
																				temp = line.trim().substring(7).trim();
																				if (temp.equalsIgnoreCase("Expected"))
																				{
																					Myorder.setFlag(false);
																				}
																				else
																				{
																					Myorder.setFlag(true);
																				}
																			}
																			line = reader.readLine();
																		}
																		orders.add(Myorder);
																		productsOrderCode++;
																		listModel3.addElement(Myorder.getOcode());
															}
															else 
															{
																JOptionPane.showMessageDialog(null,"No available items");
															}
														}
													}
												}
											}
										}
									}
							}
						line = reader.readLine();
						}
				}
				catch (IOException ev)
				{
					JOptionPane.showMessageDialog(null,"Error reading line!");
				}	
				try 
				{
					reader.close();
				} 
				catch (IOException ev) 
				{
					JOptionPane.showMessageDialog(null,"Error closing file.");
				}
		}
		else 
			{
				  JOptionPane.showMessageDialog(null,"You canceled.");
			}
		}
		else if (e.getSource() == OpenFile[2] )
		{
			JFileChooser chooser = new JFileChooser();
			chooser.setMultiSelectionEnabled(true);
			int option = chooser.showOpenDialog(mainApp.this);
			if (option == JFileChooser.APPROVE_OPTION) 
			{
				File[] sf = chooser.getSelectedFiles();
				String filelist = "nothing";
				if (sf.length > 0) filelist = sf[0].getName();
				for (int ip = 1; ip < sf.length; ip++) 
				{
					filelist += ", " + sf[ip].getName();
				}
				 f = null;
				 reader = null;
				 reader2 = null;
				 Myproduct = null;
				 pointer = 0;
				 a1=0;
				 a2=0;
				 a3=0;
				 a4=0;
				 a5=0;
				 a6=0;
				if (filelist.equals("Products.txt"))
				{
					try
					{
						f = new File(filelist);
					}
					catch (NullPointerException et)
					{
						 JOptionPane.showMessageDialog(null,"File not found.");
					}
				}	
				else
				{
					JOptionPane.showMessageDialog(null,"Wrong File");
				}
				}
				try
				{
					reader = new BufferedReader( new FileReader(f) );
					reader2 = new BufferedReader ( new FileReader(f) );
				}
				catch (FileNotFoundException et) 
				{
					JOptionPane.showMessageDialog(null,"Error opening file!");
				}
				try 
				{
					line = reader.readLine();
					line2 = reader2.readLine();
					if (!line.trim().equals(" ")) 
						{
							if (line.trim().equals("Itemlist")) 
							{
								line = reader.readLine();
								line2 = reader2.readLine();
								if (line != null && line2 !=null ) 
									{
										if (line.trim().equals("{")) 
										{
											while (line != null) 
											{
												line = reader.readLine();
												line2 = reader2.readLine();
												if (line != null && line2 !=null) 
												{
													if (line.trim().equals("Item"))
													{
														line = reader.readLine();
														line2 = reader2.readLine();
														if (line != null && line2 !=null)
														if (line.trim().equals("{"))
														{
															line2 = reader2.readLine();
															
															while (!line2.trim().equals("}") )
															{
																if ( line2.trim().toLowerCase().startsWith("ITeM_tYpe".toLowerCase()) )
																{
																	if ( line2.trim().substring(9).trim().equalsIgnoreCase("tv") )
																	{
																		Myproduct = new TV();
																		pointer = 1;
																		a1 = a1+1;
																		availability.set(0,a1);
																	}
																	else if ( line2.trim().substring(9).trim().equalsIgnoreCase("brdvd") )
																	{
																		Myproduct = new BRDVD();
																		pointer = 2;
																		a2 = a2+1;
																		availability.set(1,a2);
																	}
																	else if ( line2.trim().substring(9).trim().equalsIgnoreCase("camera") )
																	{
																		Myproduct = new Camera();
																		pointer = 3;
																		a3 = a3 +1;
																		availability.set(2,a3);
																	}
																	else if ( line2.trim().substring(9).trim().equalsIgnoreCase("gaming") )
																	{
																		Myproduct = new Gaming();
																		pointer = 4;
																		a4 = a4 + 1;
																		availability.set(3,a4);
																	}
																	else if ( line2.trim().substring(9).trim().equalsIgnoreCase("Washingmachine") )
																	{
																		Myproduct = new Washingmachine();
																		pointer = 5;
																		a5 = a5 + 1;
																		availability.set(4,a5);
																	}
																	else if ( line2.trim().substring(9).trim().equalsIgnoreCase("refrigerator") )
																	{
																		Myproduct = new Friges();
																		pointer = 6;
																		a6 = a6 + 1;
																		availability.set(5,a6);
																	}
																}
																line2 = reader2.readLine();
															}
															line = reader.readLine();
															if ( pointer == 1 )
															{
																while (!line.trim().equals("}"))
																		{
																			if ( line.trim().toLowerCase().startsWith("CoDE".toLowerCase()) )
																			{
																				Myproduct.setCode(line.trim().substring(5).trim());
																			}
																			else if ( line.trim().toLowerCase().startsWith("MoDeL".toLowerCase()) )
																			{
																				Myproduct.setName(line.trim().substring(6).trim());
																			}
																			else if ( line.trim().toLowerCase().startsWith("YEAR".toLowerCase()) )
																			{
																				Myproduct.setYear(line.trim().substring(5).trim());
																			}
																			else if ( line.trim().toLowerCase().startsWith("MANUFACTURER".toLowerCase()) )
																			{
																				
																				Myproduct.setManufacturer(line.trim().substring(13).trim());
																			}
																			else if ( line.trim().toLowerCase().startsWith("PriCe".toLowerCase()) )
																			{
																				
																				Myproduct.setPrice(Double.parseDouble(line.trim().substring(6).trim()));
																			}
																			else if ( line.trim().toLowerCase().startsWith("PanEl_typE".toLowerCase()) )
																			{
																				
																				((TV) Myproduct).setType(line.trim().substring(11).trim());
																			}
																			else if ( line.trim().toLowerCase().startsWith("Dimensions".toLowerCase()) )
																			{
																				
																				((TV) Myproduct).setDimension(line.trim().substring(11).trim());
																			}
																			else if ( line.trim().toLowerCase().startsWith("Resolution".toLowerCase()) )
																			{
																				
																				((TV) Myproduct).setResolution(line.trim().substring(11).trim());
																			}
																			else if ( line.trim().toLowerCase().startsWith("pOrT".toLowerCase()) )
																			{
																				
																				((TV) Myproduct).setPort(line.trim().substring(5).trim());
																			}
																			line = reader.readLine();
																		}
																		if ( ((TV) Myproduct).getYear() == null )
																		{
																			((TV) Myproduct).setYear("2010");
																		}
																		if ( ((TV) Myproduct).getManufacturer() == null )
																		{
																			((TV) Myproduct).setManufacturer("Sony");
																		}
																		if ( ((TV) Myproduct).getPrice() == 0 )
																		{
																			((TV) Myproduct).setPrice(199.99);
																		}
																		if ( ((TV) Myproduct).getType() == null )
																		{
																			((TV) Myproduct).setType("LCD");
																		}
																		if ( ((TV) Myproduct).getDimension() == null )
																		{
																			((TV) Myproduct).setDimension("40");
																		}
																		if ( ((TV) Myproduct).getResolution() == null )
																		{
																			((TV) Myproduct).setResolution("1920x1080");
																		}
																		if ( ((TV) Myproduct).getPort() == null )
																		{
																			((TV) Myproduct).setPort("HDMI Component Composite USB Ethernet Wifi");
																		}
																		products.add(Myproduct);
																		Myproduct.setImagePath("Images/1.gif");
																		
															}
															else if ( pointer == 2 )
															{
																while (!line.trim().equals("}"))
																{
																	if ( line.trim().toLowerCase().startsWith("CoDE".toLowerCase()) )
																	{
																			
																		Myproduct.setCode(line.trim().substring(5).trim());
																	}
																	else if ( line.trim().toLowerCase().startsWith("MoDeL".toLowerCase()) )
																	{
																			
																		Myproduct.setName(line.trim().substring(6).trim());
																	}
																	else if ( line.trim().toLowerCase().startsWith("YEAR".toLowerCase()) )
																	{
																			
																		Myproduct.setYear(line.trim().substring(5).trim());
																	}
																	else if ( line.trim().toLowerCase().startsWith("MANUFACTURER".toLowerCase()) )
																	{
																			
																		Myproduct.setManufacturer(line.trim().substring(13).trim());
																	}
																	else if ( line.trim().toLowerCase().startsWith("PriCe".toLowerCase()) )
																	{
																			
																		Myproduct.setPrice(Double.parseDouble(line.trim().substring(6).trim()));
																	}
																	else if ( line.trim().toLowerCase().startsWith("tyPe".toLowerCase()) )
																	{
																			
																		((BRDVD) Myproduct).setType(line.trim().substring(5).trim());
																	}
																	else if ( line.trim().toLowerCase().startsWith("Resolution".toLowerCase()) )
																	{
																			
																		((ImageSound) Myproduct).setResolution(line.trim().substring(11).trim());
																	}
																	else if ( line.trim().toLowerCase().startsWith("foRmaT".toLowerCase()) )
																	{
																			
																		((BRDVD) Myproduct).setFormat(line.trim().substring(7).trim());
																	}
																	line = reader.readLine();
																}
																if ( ((BRDVD) Myproduct).getYear() == null )
																{
																	((BRDVD) Myproduct).setYear("2012");
																}
																if ( ((BRDVD) Myproduct).getManufacturer() == null )
																{
																	((BRDVD) Myproduct).setManufacturer("Phillips");
																}
																if ( ((BRDVD) Myproduct).getPrice() == 0 )
																{
																	((BRDVD) Myproduct).setPrice(159.99);
																}
																if ( ((BRDVD) Myproduct).getType() == null )
																{
																	((BRDVD) Myproduct).setType("DVD");
																}
																if ( ((ImageSound) Myproduct).getResolution() == null )
																{
																	((ImageSound) Myproduct).setResolution("1920x1080");
																}
																if ( ((BRDVD) Myproduct).getFormat() == null )
																{
																	((BRDVD) Myproduct).setFormat("DVD+RW");
																}
																products.add(Myproduct);
																Myproduct.setImagePath("Images/2.jpg");
																
															}
															 else if (pointer == 3)
															{
																while (!line.trim().equals("}"))
																{
																	if ( line.trim().toLowerCase().startsWith("CoDE".toLowerCase()) )
																	{
																		
																		 Myproduct.setCode(line.trim().substring(5).trim());
																		
																	}
																	else if ( line.trim().toLowerCase().startsWith("MoDeL".toLowerCase()) )
																	{
																		
																		Myproduct.setName(line.trim().substring(6).trim());
																		
																	}
																	else if ( line.trim().toLowerCase().startsWith("YEAR".toLowerCase()) )
																	{
																		
																		Myproduct.setYear(line.trim().substring(5).trim());
																		
																	}
																	else if ( line.trim().toLowerCase().startsWith("MANUFACTURER".toLowerCase()) )
																	{
																		
																		Myproduct.setManufacturer(line.trim().substring(13).trim());
																		
																	}
																	else if ( line.trim().toLowerCase().startsWith("PriCe".toLowerCase()) )
																	{
																		
																		Myproduct.setPrice(Double.parseDouble(line.trim().substring(6).trim()));
																		
																	}
																	else if ( line.trim().toLowerCase().startsWith("tyPe".toLowerCase()) )
																	{
																		
																		((Camera) Myproduct).setType(line.trim().substring(5).trim());
																		
																	}
																	else if ( line.trim().toLowerCase().startsWith("meGaPIXEL".toLowerCase()) )
																	{
																		
																		((Camera) Myproduct).setMegapixel(line.trim().substring(10).trim());
																		
																	}
																	else if ( line.trim().toLowerCase().startsWith("opticALZOOM".toLowerCase()) )
																	{
																		
																		((Camera) Myproduct).setOpticalzoom(line.trim().substring(12).trim());
																		
																	}
																	else if ( line.trim().toLowerCase().startsWith("digitalZOOM".toLowerCase()) )
																	{
																		
																		((Camera) Myproduct).setDigitalzoom(line.trim().substring(12).trim());
																		
																	}
																	else if ( line.trim().toLowerCase().startsWith("screenSIZE".toLowerCase()) )
																	{
																		
																		((Camera) Myproduct).setScreensize(line.trim().substring(11).trim());
																		
																	}
																	line = reader.readLine();
																}
																if ( ((Camera) Myproduct).getYear() == null )
																{
																	((Camera) Myproduct).setYear("2015");
																}
																if ( ((Camera) Myproduct).getManufacturer() == null )
																{
																	((Camera) Myproduct).setManufacturer("Nikon");
																}
																if ( ((Camera) Myproduct).getPrice() == 0 )
																{
																	((Camera) Myproduct).setPrice(409.99);
																}
																if ( ((Camera) Myproduct).getType() == null )
																{
																	((Camera) Myproduct).setType("DSLR");
																}
																if ( ((Camera) Myproduct).getMegapixel() == null )
																{
																	((Camera) Myproduct).setMegapixel("15");
																}
																if ( ((Camera) Myproduct).getOpticalzoom() == null )
																{
																	((Camera) Myproduct).setOpticalzoom("x200");
																}
																if ( ((Camera) Myproduct).getDigitalzoom() == null )
																{
																	((Camera) Myproduct).setDigitalzoom("x300");
																}
																if ( ((Camera) Myproduct).getScreensize() == null )
																{
																	((Camera) Myproduct).setScreensize("10");
																}
																products.add(Myproduct);
																Myproduct.setImagePath("Images/3.gif");
																
															}
															else if ( pointer == 4 )
															{
																 while (!line.trim().equals("}"))
																{
																	if ( line.trim().toLowerCase().startsWith("CoDE".toLowerCase()) )
																	{
																		
																		 Myproduct.setCode(line.trim().substring(5).trim());
																	}
																	else if ( line.trim().toLowerCase().startsWith("MoDeL".toLowerCase()) )
																	{
																		
																		Myproduct.setName(line.trim().substring(6).trim());
																	}
																	else if ( line.trim().toLowerCase().startsWith("YEAR".toLowerCase()) )
																	{
																		
																		Myproduct.setYear(line.trim().substring(5).trim());
																	}
																	else if ( line.trim().toLowerCase().startsWith("MANUFACTURER".toLowerCase()) )
																	{
																		
																		Myproduct.setManufacturer(line.trim().substring(13).trim());
																	}
																	else if ( line.trim().toLowerCase().startsWith("PriCe".toLowerCase()) )
																	{
																		
																		Myproduct.setPrice(Double.parseDouble(line.trim().substring(6).trim()));
																	}
																	else if ( line.trim().toLowerCase().startsWith("Console_typE".toLowerCase()) )
																	{
																		
																		((Gaming) Myproduct).setType(line.trim().substring(13).trim());
																	}
																	else if ( line.trim().toLowerCase().startsWith("ProcceSSor".toLowerCase()) )
																	{
																		
																		((Gaming) Myproduct).setProccessor(line.trim().substring(11).trim());
																	}
																	else if ( line.trim().toLowerCase().startsWith("GraPhiCS".toLowerCase()) )
																	{
																		
																		((Gaming) Myproduct).setGraphics(line.trim().substring(9).trim());
																	}
																	else if ( line.trim().toLowerCase().startsWith("Sound".toLowerCase()) )
																	{
																		
																		((Gaming) Myproduct).setSound(line.trim().substring(6).trim());
																	}
																	else if ( line.trim().toLowerCase().startsWith("Capacity".toLowerCase()) )
																	{
																		
																		((Gaming) Myproduct).setCapacity(line.trim().substring(9).toLowerCase());
																	}
																	line = reader.readLine();
																}
																if ( ((Gaming) Myproduct).getYear() == null )
																{
																	((Gaming) Myproduct).setYear("2016");
																}
																if ( ((Gaming) Myproduct).getManufacturer() == null )
																{
																	((Gaming) Myproduct).setManufacturer("Sony");
																}
																if ( ((Gaming) Myproduct).getPrice() == 0 )
																{
																	((Gaming) Myproduct).setPrice(119.99);
																}
																if ( ((Gaming) Myproduct).getType() == null )
																{
																	((Gaming) Myproduct).setType("PS3");
																}
																if ( ((Gaming) Myproduct).getProccessor() == null )
																{
																	((Gaming) Myproduct).setProccessor("Intel");
																}
																if ( ((Gaming) Myproduct).getGraphics() == null )
																{
																	((Gaming) Myproduct).setGraphics("Nvidia");
																}
																if ( ((Gaming) Myproduct).getSound() == null )
																{
																	((Gaming) Myproduct).setSound("Realtek");
																}
																if ( ((Gaming) Myproduct).getCapacity() == null )
																{
																	((Gaming) Myproduct).setCapacity("500GB");
																}
																products.add(Myproduct);
																Myproduct.setImagePath("Images/4.gif");
																
															}
															else if ( pointer == 5 )
															{
																 while (!line.trim().equals("}"))
																{
																	if ( line.trim().toLowerCase().startsWith("CoDE".toLowerCase()) )
																	{
																		
																		 Myproduct.setCode(line.trim().substring(5).trim());
																	}
																	else if ( line.trim().toLowerCase().startsWith("MoDeL".toLowerCase()) )
																	{
																		
																		Myproduct.setName(line.trim().substring(6).trim());
																	}
																	else if ( line.trim().toLowerCase().startsWith("YEAR".toLowerCase()) )
																	{
																		
																		Myproduct.setYear(line.trim().substring(5).trim());
																	}
																	else if ( line.trim().toLowerCase().startsWith("MANUFACTURER".toLowerCase()) )
																	{
																		
																		Myproduct.setManufacturer(line.trim().substring(13).trim());
																	}
																	else if ( line.trim().toLowerCase().startsWith("PriCe".toLowerCase()) )
																	{
																		
																		Myproduct.setPrice(Double.parseDouble(line.trim().substring(6).trim()));
																	}
																	else if ( line.trim().toLowerCase().startsWith("EnergySave".toLowerCase()) )
																	{
																		
																		((Washingmachine) Myproduct).setEnergySave(line.trim().substring(11).trim());
																	}
																	else if ( line.trim().toLowerCase().startsWith("WASHING_SPACE".toLowerCase()) )
																	{
																		
																		((Washingmachine) Myproduct).setWashingSpace(line.trim().substring(14).trim());
																	}
																	else if ( line.trim().toLowerCase().startsWith("Bends".toLowerCase()) )
																	{
																		
																		((Washingmachine) Myproduct).setBends(Integer.parseInt(line.trim().substring(6).trim()));
																	}
																	line = reader.readLine();
																}
																if ( ((Washingmachine) Myproduct).getYear() == null )
																{
																	((Washingmachine) Myproduct).setYear("2013");
																}
																if ( ((Washingmachine) Myproduct).getManufacturer() == null )
																{
																	((Washingmachine) Myproduct).setManufacturer("AEG");
																}
																if ( ((Washingmachine) Myproduct).getPrice() == 0 )
																{
																	((Washingmachine) Myproduct).setPrice(249.99);
																}
																if ( ((Washingmachine) Myproduct).getEnergySave() == null )
																{
																	((Washingmachine) Myproduct).setEnergySave("250W");
																}
																if ( ((Washingmachine) Myproduct).getWashingSpace() == null )
																{
																	((Washingmachine) Myproduct).setWashingSpace("150");
																}
																if ( ((Washingmachine) Myproduct).getBends() == 0 )
																{
																	((Washingmachine) Myproduct).setBends(400);
																}
																products.add(Myproduct);
																Myproduct.setImagePath("Images/5.gif");
																
															}
															 else if ( pointer == 6 )
															{
																 while (!line.trim().equals("}"))
																{
																	if ( line.trim().toLowerCase().startsWith("CoDE".toLowerCase()) )
																	{
																		
																		 Myproduct.setCode(line.trim().substring(5).trim());
																	}
																	else if ( line.trim().toLowerCase().startsWith("MoDeL".toLowerCase()) )
																	{
																		
																		Myproduct.setName(line.trim().substring(6).trim());
																	}
																	else if ( line.trim().toLowerCase().startsWith("YEAR".toLowerCase()) )
																	{
																		
																		Myproduct.setYear(line.trim().substring(5).trim());
																	}
																	else if ( line.trim().toLowerCase().startsWith("MANUFACTURER".toLowerCase()) )
																	{
																		
																		Myproduct.setManufacturer(line.trim().substring(13).trim());
																	}
																	else if ( line.trim().toLowerCase().startsWith("PriCe".toLowerCase()) )
																	{
																		
																		Myproduct.setPrice(Double.parseDouble(line.trim().substring(6).trim()));
																	}
																	else if ( line.trim().toLowerCase().startsWith("EnergySave".toLowerCase()) )
																	{
																		
																		((Friges) Myproduct).setEnergySave(line.trim().substring(11).trim());
																	}
																	else if ( line.trim().toLowerCase().startsWith("DoorType".toLowerCase()) )
																	{
																		
																		((Friges) Myproduct).setType(line.trim().substring(9).trim());
																	}
																	else if ( line.trim().toLowerCase().startsWith("Space_UP".toLowerCase()) )
																	{
																		
																		((Friges) Myproduct).setFrigeSpaceUp(Float.parseFloat(line.trim().substring(9).trim()));
																	}
																	else if ( line.trim().toLowerCase().startsWith("Space_Down".toLowerCase()) )
																	{
																		
																		((Friges) Myproduct).setFrigeSpaceDown(Float.parseFloat(line.trim().substring(11).trim()));
																	}
																	line = reader.readLine();
																}
																if ( ((Friges) Myproduct).getYear() == null )
																{
																	((Friges) Myproduct).setYear("2014");
																}
																if ( ((Friges) Myproduct).getManufacturer() == null )
																{
																	((Friges) Myproduct).setManufacturer("Siemens");
																}
																if ( ((Friges) Myproduct).getPrice() == 0 )
																{
																	((Friges) Myproduct).setPrice(399.99);
																}
																if ( ((Friges) Myproduct).getEnergySave() == null )
																{
																	((Friges) Myproduct).setEnergySave("200W");
																}
																if ( ((Friges) Myproduct).getType() == null )
																{
																	((Friges) Myproduct).setType("Two door");
																}
																if ( ((Friges) Myproduct).getFrigeSpaceUp() == 0 )
																{
																	((Friges) Myproduct).setFrigeSpaceUp(200);
																}
																if ( ((Friges) Myproduct).getFrigeSpaceDown() == 0 )
																{
																	((Friges) Myproduct).setFrigeSpaceDown(200);
																}
																products.add(Myproduct);
																Myproduct.setImagePath("Images/6.gif");
																
															}
															
											}
										}
									}
								}
							}
						}
						line = reader.readLine();
					}
				}
		}
		catch (IOException et)
		{
			JOptionPane.showMessageDialog(null,"Error reading line!");
		}	
		try 
		{
			reader.close();
		} 
		catch (IOException et) 
		{
			JOptionPane.showMessageDialog(null,"Error closing file.");
		}
		for (int k = 0; k<6; k++)
		{
			position[k] = availability.get(k);
		}	
		
	
	}
	}
	
	public void valueChanged(ListSelectionEvent event) // Bazw stoixeia sto list2 (twn antikeimenwn diladi) analoga me tin epilogi tis 1is listas
	{
		int i;
		listModel2.removeAllElements();
			if ( event.getSource() == list1 )
			{
				
				i = list1.getSelectedIndex();
				if ( i == 0 )
				{
					for ( Products1 j : products )
					{
						if ( j instanceof TV )
						{
							listModel2.addElement(j.getName());
						}	
					}
					listScroller2.revalidate();
					listScroller2.repaint();
				}
				else if ( i == 1 )
				{
					for ( Products1 j : products )
					{
						if ( j instanceof BRDVD )
						{
							listModel2.addElement(j.getName());
						}	
					}
					listScroller2.revalidate();
					listScroller2.repaint();
				}
				else if ( i == 2 )
				{
					for ( Products1 j : products )
					{
						if ( j instanceof Camera )
						{
							listModel2.addElement(j.getName());
						}	
					}
					listScroller2.revalidate();
					listScroller2.repaint(); 
				}
				else if ( i == 3 )
				{
					for ( Products1 j : products )
					{
						if ( j instanceof Gaming )
						{
							listModel2.addElement(j.getName());
						}
					}
					listScroller2.revalidate();
					listScroller2.repaint(); 
				}
				else if ( i == 4 )
				{
					for ( Products1 j : products )
					{
						if ( j instanceof Washingmachine )
						{
							listModel2.addElement(j.getName());
						}
					}
					listScroller2.revalidate();
					listScroller2.repaint(); 
				}
				else if ( i == 5 )
				{
					for ( Products1 j : products )
					{
						if ( j instanceof Friges )
						{
							listModel2.addElement(j.getName());
						}
					}
					listScroller2.revalidate();
					listScroller2.repaint(); 
				}
			}
	}
	
	public void mouseClicked(MouseEvent event) 
	{
		int i;
		String outputText;
		if ( event.getClickCount() != 2 )
		{
			if (list1.getSelectedIndex() == 0)
			{
				i = list2.getSelectedIndex();
				number = -1;
				number1 = -1;
				number2 = 0;
				for (Products1 k11 : products)
				{
					if ( k11 instanceof TV )
					{
						number = number+1;
					}
					if ( k11 instanceof TV && number==i )
					{
						number2 = number1 + 1;
					
					}
					number1 = number1 + 1;
				}
				outputText = products.get(number2).getContents();
				resultArea.setText(outputText); 
			}
			else if (list1.getSelectedIndex() == 1)
			{
				i = list2.getSelectedIndex();
				number = -1;
				number1 = -1;
				number2 = 0;
				for (Products1 k11 : products)
				{
					if ( k11 instanceof BRDVD )
					{
						number = number+1;
					}
					if ( k11 instanceof BRDVD && number==i )
					{
						number2 = number1 + 1;
					
					}
					number1 = number1 + 1;
				}
				outputText = products.get(number2).getContents();
				resultArea.setText(outputText); 
			}
			else if (list1.getSelectedIndex() == 2)
			{
				i = list2.getSelectedIndex();
				number = -1;
				number1 = -1;
				number2 = 0;
				for (Products1 k11 : products)
				{
					if ( k11 instanceof Camera )
					{
						number = number+1;
					}
					if ( k11 instanceof Camera && number==i )
					{
						number2 = number1 + 1;
					
					}
					number1 = number1 + 1;
				}
				outputText = products.get(number2).getContents();
				resultArea.setText(outputText);  
			}
			else if (list1.getSelectedIndex() == 3)
			{
				i = list2.getSelectedIndex();
				number = -1;
				number1 = -1;
				number2 = 0;
				for (Products1 k11 : products)
				{
					if ( k11 instanceof Gaming )
					{
						number = number+1;
					}
					if ( k11 instanceof Gaming && number==i )
					{
						number2 = number1 + 1;
					
					}
					number1 = number1 + 1;
				}
				outputText = products.get(number2).getContents();
				resultArea.setText(outputText); 
			}
			else if (list1.getSelectedIndex() == 4)
			{
				i = list2.getSelectedIndex();
				number = -1;
				number1 = -1;
				number2 = 0;
				for (Products1 k11 : products)
				{
					if ( k11 instanceof Washingmachine )
					{
						number = number+1;
					}
					if ( k11 instanceof Washingmachine && number==i )
					{
						number2 = number1 + 1;
					
					}
					number1 = number1 + 1;
				}
				outputText = products.get(number2).getContents();
				resultArea.setText(outputText); 
			}
			else if (list1.getSelectedIndex() == 5)
			{
				i = list2.getSelectedIndex();
				number = -1;
				number1 = -1;
				number2 = 0;
				for (Products1 k11 : products)
				{
					if ( k11 instanceof Friges )
					{
						number = number+1;
					}
					if ( k11 instanceof Friges && number==i )
					{
						number2 = number1 + 1;
					
					}
					number1 = number1 + 1;
				}
				outputText = products.get(number2).getContents();
				resultArea.setText(outputText);  
			}
			
			
		}
		else 
		{
			int available;
			double discount,temp;
			String pathimage;
			String temp1,temp2;
			ImageIcon icon;
			Object[] options = {"Buy","Don't Buy"};
			Object[] options1 = {"Order","Don't Order"};
			int n = 1;
			int m = 1;
			
			
			
			if (list1.getSelectedIndex() == 0)
			{
				available = availability.get(0);
				i = list2.getSelectedIndex();
				number = -1;
				number1 = -1;
				number2 = 0;
				for (Products1 k11 : products)
				{
					if ( k11 instanceof TV )
					{
						number = number+1;
					}
					if ( k11 instanceof TV && number==i )
					{
						number2 = number1 + 1;
					
					}
					number1 = number1 + 1;
				}
				pathimage = products.get(number2).getImagePath();
				icon = new ImageIcon(pathimage);
				
				if (pathimage != null) 
				{
					n = JOptionPane.showOptionDialog(null,products.get(number2),"Product Details",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE ,icon,options,options[0]);
				}
				else 
				{
					JOptionPane.showMessageDialog(null,products.get(number2));
				}
				if ( n == 0 && availability.get(0) != 0 )
				{
					fullname = (String)JOptionPane.showInputDialog(this, "Please type the name of the client");
					phone = (String)JOptionPane.showInputDialog(this, "Please type the phone of the client");
					date = (String)JOptionPane.showInputDialog(this, "Please type the date of the Sale",Calendar.getInstance().getTime());
					if ( fullname == null || phone == null || date == null )
					{
						JOptionPane.showMessageDialog(null,"Sale not completed");
					}
					else
					{
						discount = products.get(number2).getPrice()*products.get(number2).getDiscount();
						temp = products.get(number2).getPrice() - discount;
						totalcost = "" + temp;
						availability.set(0,available-1);
						JOptionPane.showMessageDialog(this,"Total Cost: " + totalcost);
						productsSaleCode = productsSaleCode + 1; 
						temp1 = "" + productsSaleCode;
						Sales s = new Sales(temp1,products.get(number2).getName(),fullname,phone,date,totalcost);
						sales.add(s);
						listModel4.addElement(s.getScode());
						
						
	//////////////////////////////////////////////////////////////////
						File f = null;
						BufferedWriter writer = null;
						
						try	
						{
							f = new File("Sale.txt");
						}
						catch (NullPointerException e) 
						{
							JOptionPane.showMessageDialog(null,"File not found."); 
						}
			
						try	
						{
							writer = new BufferedWriter(new OutputStreamWriter
								(new FileOutputStream(f,true)));
						}
						catch (FileNotFoundException e) 
						{
							JOptionPane.showMessageDialog(null,"Error opening file for writing!");
						}
						////////////////////////////////////////////////////////////////////
						try
						{
							writer.write("\r\n"+"\t"+"Sale"+"\r\n"+"\t"+"{"+"\r\n"+"\t\t"+"Item_type TV"+"\r\n"+"\t\t"+"Sales_number "+temp1+"\r\n"+"\t\t"+"Name "+fullname+"\r\n"+"\t\t"+"Phone "+phone+"\r\n"+"\t\t"+"Sale_date "+date+"\r\n"+"\t\t"+"Price "+totalcost+"\r\n"+"\t"+"}");
						}
						catch (IOException e) 
						{
							JOptionPane.showMessageDialog(null,"Write error!");
						}
						try 
						{
							writer.close();
						}
						catch (IOException e) 
						{
							JOptionPane.showMessageDialog(null,"Error closing file.");
						}
					}
				}
				else if ( n==0 && availability.get(0) == 0 )
				{
					m = JOptionPane.showOptionDialog(null,products.get(number2),"Not available in the store",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE ,icon,options1,options1[0]);
					if ( m == 0 )
					{
						Calendar now = Calendar.getInstance();
						now.add(Calendar.DAY_OF_YEAR,14);
						fullname = (String)JOptionPane.showInputDialog(this, "Please type the name of the client");
						phone = (String)JOptionPane.showInputDialog(this, "Please type the phone of the client");
						date = (String)JOptionPane.showInputDialog(this, "Please type the date of the Order",Calendar.getInstance().getTime());
						ddate = (String)JOptionPane.showInputDialog(this, "Please type the date of delivery",now.getTime());
						if ( fullname == null || phone == null || date == null || ddate == null )
						{
							JOptionPane.showMessageDialog(null,"Order not completed");
						}
						else
						{
							discount = products.get(number2).getPrice()*products.get(number2).getDiscount();
							temp = products.get(number2).getPrice() - discount;
							totalcost = "" + temp;
							availability.set(0,available-1);
							JOptionPane.showMessageDialog(this,"Total Cost: " + totalcost);
							productsOrderCode = productsOrderCode + 1; 
							temp2 = "" + productsOrderCode;
							Order o = new Order(temp2,products.get(number2).getName(),fullname,phone,date,ddate,totalcost,false);
							orders.add(o);
							listModel3.addElement(o.getOcode());
							//////////////////////////////////////////////////////////////
							File f1 = null;
							BufferedWriter writer1 = null;
							try	
							{
								f1 = new File("Order.txt");
							}
							catch (NullPointerException e1) 
							{
								JOptionPane.showMessageDialog(null,"File not found.");
							}
							try	
							{
								writer1 = new BufferedWriter(new OutputStreamWriter
									(new FileOutputStream(f1,true)));
							}
							catch (FileNotFoundException e1) 
							{
								JOptionPane.showMessageDialog(null,"Error opening file for writing!");
							}
							/////////////////////////////////////////////////////////////////
							try
							{
								writer1.write("\r\n"+"\t"+"Order"+"\r\n"+"\t"+"{"+"\r\n"+"\t\t"+"Item_type TV"+"\r\n"+"\t\t"+"Order_number "+temp2+"\r\n"+"\t\t"+"Name "+fullname+"\r\n"+"\t\t"+"Phone "+phone+"\r\n"+"\t\t"+"Order_date "+date+"\r\n"+"\t\t"+"Delivery_date "+ddate+"\r\n"+"\t\t"+"Price "+totalcost+"\r\n"+"\t\t"+"Status Expected"+"\r\n"+"\t"+"}");
							}
							catch (IOException e1) 
							{
								JOptionPane.showMessageDialog(null,"Write error!");
							}
							try 
							{
								writer1.close();
				
							}
							catch (IOException e1) 
							{
								JOptionPane.showMessageDialog(null,"Error closing file.");
							}
								
						}
					}
					
				}
			}
		
			else if (list1.getSelectedIndex() == 1)
			{
				available = availability.get(1);
				i = list2.getSelectedIndex();
				number = -1;
				number1 = -1;
				number2 = 0;
				for (Products1 k11 : products)
				{
					if ( k11 instanceof BRDVD )
					{
						number = number+1;
					}
					if ( k11 instanceof BRDVD && number==i )
					{
						number2 = number1 + 1;
					
					}
					number1 = number1 + 1;
				}
				pathimage = products.get(number2).getImagePath();
				icon = new ImageIcon(pathimage);
				if (pathimage != null) 
				{
					n = JOptionPane.showOptionDialog(null,products.get(number2),"Product Details",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE ,icon,options,options[0]);
				}
				else 
				{
					JOptionPane.showMessageDialog(null,products.get(number2));
				}
				if ( n == 0 && availability.get(1) != 0 )
				{
					fullname = (String)JOptionPane.showInputDialog(this, "Please type the name of the client");
					phone = (String)JOptionPane.showInputDialog(this, "Please type the phone of the client");
					date = (String)JOptionPane.showInputDialog(this, "Please type the date of the Sale",Calendar.getInstance().getTime());
					if ( fullname == null || phone == null || date == null )
					{
						JOptionPane.showMessageDialog(null,"Sale not completed");
					}
					else
					{
						discount = products.get(number2).getPrice()*products.get(number2).getDiscount();
						temp = products.get(number2).getPrice() - discount;
						totalcost = "" + temp;
						availability.set(1,available-1);
						JOptionPane.showMessageDialog(this,"Total Cost: " + totalcost);
						productsSaleCode = productsSaleCode + 1; 
						temp1 = "" + productsSaleCode;
						Sales s = new Sales(temp1,products.get(number2).getName(),fullname,phone,date,totalcost);
						sales.add(s);
						listModel4.addElement(s.getScode());
						/////////////////////////////////////////////////////////////
						File f = null;
						BufferedWriter writer = null;
						
						try	
						{
							f = new File("Sale.txt");
						}
						catch (NullPointerException e) 
						{
							JOptionPane.showMessageDialog(null,"File not found."); 
						}
			
						try	
						{
							writer = new BufferedWriter(new OutputStreamWriter
								(new FileOutputStream(f,true)));
						}
						catch (FileNotFoundException e) 
						{
							JOptionPane.showMessageDialog(null,"Error opening file for writing!");
						}
						////////////////////////////////////////////////////////////////////
						try
						{
							writer.write("\r\n"+"\t"+"Sale"+"\r\n"+"\t"+"{"+"\r\n"+"\t\t"+"Item_type BRDVD"+"\r\n"+"\t\t"+"Sales_number "+temp1+"\r\n"+"\t\t"+"Name "+fullname+"\r\n"+"\t\t"+"Phone "+phone+"\r\n"+"\t\t"+"Sale_date "+date+
							"\r\n"+"\t\t"+"Price "+totalcost+"\r\n"+"\t"+"}");
						}
						catch (IOException e) 
						{
							JOptionPane.showMessageDialog(null,"Write error!");
						}	
						try 
						{
							writer.close();
						}
						catch (IOException e) 
						{
							JOptionPane.showMessageDialog(null,"Error closing file.");
						}
					}
				}
				else if ( n==0 && availability.get(1) == 0 )
				{
					m = JOptionPane.showOptionDialog(null,products.get(number2),"Not available in the store",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE ,icon,options1,options1[0]);
					if ( m == 0 )
					{
						Calendar now = Calendar.getInstance();
						now.add(Calendar.DAY_OF_YEAR,14);
						fullname = (String)JOptionPane.showInputDialog(this, "Please type the name of the client");
						phone = (String)JOptionPane.showInputDialog(this, "Please type the phone of the client");
						date = (String)JOptionPane.showInputDialog(this, "Please type the date of the Order",Calendar.getInstance().getTime());
						ddate = (String)JOptionPane.showInputDialog(this, "Please type the date of delivery",now.getTime());
						if ( fullname == null || phone == null || date == null || ddate == null )
						{
							JOptionPane.showMessageDialog(null,"Order not completed");
						}
						else
						{
							discount = products.get(number2).getPrice()*products.get(number2).getDiscount();
							temp = products.get(number2).getPrice() - discount;
							totalcost = "" + temp;
							availability.set(1,available-1);
							JOptionPane.showMessageDialog(this,"Total Cost: " + totalcost);
							///////////////
							productsOrderCode = productsOrderCode + 1; 
							temp2 = "" + productsOrderCode;
							Order o = new Order(temp2,products.get(number2).getName(),fullname,phone,date,ddate,totalcost,false);
							orders.add(o);
							listModel3.addElement(o.getOcode());
							File f1 = null;
							BufferedWriter writer1 = null;
							try	
							{
								f1 = new File("Order.txt");
							}
							catch (NullPointerException e1) 
							{
								JOptionPane.showMessageDialog(null,"File not found.");
							}
							try	
							{
								writer1 = new BufferedWriter(new OutputStreamWriter
									(new FileOutputStream(f1,true)));
							}
							catch (FileNotFoundException e1) 
							{
								JOptionPane.showMessageDialog(null,"Error opening file for writing!");
							}
							///////////////////////
							try
							{
								writer1.write("\r\n"+"\t"+"Order"+"\r\n"+"\t"+"{"+"\r\n"+"\t\t"+"Item_type BRDVD"+"\r\n"+"\t\t"+"Order_number "+temp2+"\r\n"+"\t\t"+"Name "+fullname+"\r\n"+"\t\t"+"Phone "+phone+"\r\n"+"\t\t"+"Order_date "+date+
								"\r\n"+"\t\t"+"Delivery_date "+ddate+"\r\n"+"\t\t"+"Price "+totalcost+"\r\n"+"\t\t"+"Status Expected"+"\r\n"+"\t"+"}");
							}
							catch (IOException e1) 
							{
								JOptionPane.showMessageDialog(null,"Write error!");
							}
							try 
							{
								writer1.close();
				
							}
							catch (IOException e1) 
							{
								JOptionPane.showMessageDialog(null,"Error closing file.");
							}
						}
					}
				}
			}
			else if (list1.getSelectedIndex() == 2)
			{
				available = availability.get(2);
				i = list2.getSelectedIndex();
				number = -1;
				number1 = -1;
				number2 = 0;
				for (Products1 k11 : products)
				{
					if ( k11 instanceof Camera )
					{
						number = number+1;
					}
					if ( k11 instanceof Camera && number==i )
					{
						number2 = number1 + 1;
					
					}
					number1 = number1 + 1;
				}
				pathimage = products.get(number2).getImagePath();
				icon = new ImageIcon(pathimage);
				if (pathimage != null) 
				{
					n = JOptionPane.showOptionDialog(null,products.get(number2),"Product Details",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE ,icon,options,options[0]);
				}
				else 
				{
					JOptionPane.showMessageDialog(null,products.get(number2));
				}
				if ( n == 0 && availability.get(2) != 0 )
				{
					fullname = (String)JOptionPane.showInputDialog(this, "Please type the name of the client");
					phone = (String)JOptionPane.showInputDialog(this, "Please type the phone of the client");
					date = (String)JOptionPane.showInputDialog(this, "Please type the date of the Sale",Calendar.getInstance().getTime());
					if ( fullname == null || phone == null || date == null )
					{
						JOptionPane.showMessageDialog(null,"Sale not completed");
					}
					else
					{
						discount = products.get(number2).getPrice()*products.get(number2).getDiscount();
						temp = products.get(number2).getPrice() - discount;
						totalcost = "" + temp;
						availability.set(2,available-1);
						JOptionPane.showMessageDialog(this,"Total Cost: " + totalcost);
						productsSaleCode = productsSaleCode + 1; 
						temp1 = "" + productsSaleCode;
						Sales s = new Sales(temp1,products.get(number2).getName(),fullname,phone,date,totalcost);
						sales.add(s);
						listModel4.addElement(s.getScode());
						File f = null;
						BufferedWriter writer = null;
						
						try	
						{
							f = new File("Sale.txt");
						}
						catch (NullPointerException e) 
						{
							JOptionPane.showMessageDialog(null,"File not found."); 
						}
			
						try	
						{
							writer = new BufferedWriter(new OutputStreamWriter
								(new FileOutputStream(f,true)));
						}
						catch (FileNotFoundException e) 
						{
							JOptionPane.showMessageDialog(null,"Error opening file for writing!");
						}
						try
						{
							writer.write("\r\n"+"\t"+"Sale"+"\r\n"+"\t"+"{"+"\r\n"+"\t\t"+"Item_type Camera"+"\r\n"+"\t\t"+"Sales_number "+temp1+"\r\n"+"\t\t"+"Name "+fullname+"\r\n"+"\t\t"+"Phone "+phone+"\r\n"+"\t\t"+"Sale_date "+date+
							"\r\n"+"\t\t"+"Price "+totalcost+"\r\n"+"\t"+"}");
						}
						catch (IOException e) 
						{
							JOptionPane.showMessageDialog(null,"Write error!");
						}
						try 
						{
							writer.close();
						}
						catch (IOException e) 
						{
							JOptionPane.showMessageDialog(null,"Error closing file.");
						}
						
					}
				}
				else if ( n==0 && availability.get(2) == 0 )
				{
					m = JOptionPane.showOptionDialog(null,products.get(number2),"Not available in the store",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE ,icon,options1,options1[0]);
					if ( m == 0 )
					{
						Calendar now = Calendar.getInstance();
						now.add(Calendar.DAY_OF_YEAR,14);
						fullname = (String)JOptionPane.showInputDialog(this, "Please type the name of the client");
						phone = (String)JOptionPane.showInputDialog(this, "Please type the phone of the client");
						date = (String)JOptionPane.showInputDialog(this, "Please type the date of the Order",Calendar.getInstance().getTime());
						ddate = (String)JOptionPane.showInputDialog(this, "Please type the date of delivery",now.getTime());
						if ( fullname == null || phone == null || date == null || ddate == null )
						{
							JOptionPane.showMessageDialog(null,"Order not completed");
						}
						else
						{
							discount = products.get(number2).getPrice()*products.get(number2).getDiscount();
							temp = products.get(number2).getPrice() - discount;
							totalcost = "" + temp;
							availability.set(2,available-1);
							JOptionPane.showMessageDialog(this,"Total Cost: " + totalcost);
							productsOrderCode = productsOrderCode + 1; 
							temp2 = "" + productsOrderCode;
							Order o = new Order(temp2,products.get(number2).getName(),fullname,phone,date,ddate,totalcost,false);
							orders.add(o);
							listModel3.addElement(o.getOcode());
							//////////////////////////////////////////////////////////////
							File f1 = null;
							BufferedWriter writer1 = null;
							try	
							{
								f1 = new File("Order.txt");
							}
							catch (NullPointerException e1) 
							{
								JOptionPane.showMessageDialog(null,"File not found.");
							}
							try	
							{
								writer1 = new BufferedWriter(new OutputStreamWriter
									(new FileOutputStream(f1,true)));
							}
							catch (FileNotFoundException e1) 
							{
								JOptionPane.showMessageDialog(null,"Error opening file for writing!");
							}
							/////////////////////////////////////////////////////////////////
							try
							{
								writer1.write("\r\n"+"\t"+"Order"+"\r\n"+"\t"+"{"+"\r\n"+"\t\t"+"Item_type Camera"+"\r\n"+"\t\t"+"Order_number "+temp2+"\r\n"+"\t\t"+"Name "+fullname+"\r\n"+"\t\t"+"Phone "+phone+"\r\n"+"\t\t"+"Order_date "+date+
								"\r\n"+"\t\t"+"Delivery_date "+ddate+"\r\n"+"\t\t"+"Price "+totalcost+"\r\n"+"\t\t"+"Status Expected"+"\r\n"+"\t"+"}");
							}
							catch (IOException e1) 
							{
								JOptionPane.showMessageDialog(null,"Write error!");
							}
							try 
							{
								writer1.close();
				
							}
							catch (IOException e1) 
							{
								JOptionPane.showMessageDialog(null,"Error closing file.");
							}
						}
					}
					
				}
				
			}
			else if (list1.getSelectedIndex() == 3)
			{
				available = availability.get(3);
				i = list2.getSelectedIndex();
				number = -1;
				number1 = -1;
				number2 = 0;
				for (Products1 k11 : products)
				{
					if ( k11 instanceof Gaming )
					{
						number = number+1;
					}
					if ( k11 instanceof Gaming && number==i )
					{
						number2 = number1 + 1;
					
					}
					number1 = number1 + 1;
				}
				pathimage = products.get(number2).getImagePath();
				icon = new ImageIcon(pathimage);
				if (pathimage != null) 
				{
					n = JOptionPane.showOptionDialog(null,products.get(number2),"Product Details",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE ,icon,options,options[0]);
				}
				else 
				{
					JOptionPane.showMessageDialog(null,products.get(number2));
				}
				if ( n == 0 && availability.get(3) != 0 )
				{
					fullname = (String)JOptionPane.showInputDialog(this, "Please type the name of the client");
					phone = (String)JOptionPane.showInputDialog(this, "Please type the phone of the client");
					date = (String)JOptionPane.showInputDialog(this, "Please type the date of the Sale",Calendar.getInstance().getTime());
					if ( fullname == null || phone == null || date == null )
					{
						JOptionPane.showMessageDialog(null,"Sale not completed");
					}
					else
					{
						discount = products.get(number2).getPrice()*products.get(number2).getDiscount();
						temp = products.get(number2).getPrice() - discount;
						totalcost = "" + temp;
						availability.set(3,available-1);
						JOptionPane.showMessageDialog(this,"Total Cost: " + totalcost);
						productsSaleCode = productsSaleCode + 1; 
						temp1 = "" + productsSaleCode;
						Sales s = new Sales(temp1,products.get(number2).getName(),fullname,phone,date,totalcost);
						sales.add(s);
						listModel4.addElement(s.getScode());
						
						File f = null;
						BufferedWriter writer = null;
						
						try	
						{
							f = new File("Sale.txt");
						}
						catch (NullPointerException e) 
						{
							JOptionPane.showMessageDialog(null,"File not found."); 
						}
			
						try	
						{
							writer = new BufferedWriter(new OutputStreamWriter
								(new FileOutputStream(f,true)));
						}
						catch (FileNotFoundException e) 
						{
							JOptionPane.showMessageDialog(null,"Error opening file for writing!");
						}
						
						try
						{
							writer.write("\r\n"+"\t"+"Sale"+"\r\n"+"\t"+"{"+"\r\n"+"\t\t"+"Item_type Gaming"+"\r\n"+"\t\t"+"Sales_number "+temp1+"\r\n"+"\t\t"+"Name "+fullname+"\r\n"+"\t\t"+"Phone "+phone+"\r\n"+"\t\t"+"Sale_date "+date+
							"\r\n"+"\t\t"+"Price "+totalcost+"\r\n"+"\t"+"}");
						}
						catch (IOException e) 
						{
							JOptionPane.showMessageDialog(null,"Write error!");
						}
						try 
						{
							writer.close();
						}
						catch (IOException e) 
						{
							JOptionPane.showMessageDialog(null,"Error closing file.");
						}
					}
				}
				else if ( n==0 && availability.get(3) == 0 )
				{
					m = JOptionPane.showOptionDialog(null,products.get(i),"Not available in the store",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE ,icon,options1,options1[0]);
					if ( m == 0 )
					{
						Calendar now = Calendar.getInstance();
						now.add(Calendar.DAY_OF_YEAR,14);
						fullname = (String)JOptionPane.showInputDialog(this, "Please type the name of the client");
						phone = (String)JOptionPane.showInputDialog(this, "Please type the phone of the client");
						date = (String)JOptionPane.showInputDialog(this, "Please type the date of the Order",Calendar.getInstance().getTime());
						ddate = (String)JOptionPane.showInputDialog(this, "Please type the date of delivery",now.getTime());
						if ( fullname == null || phone == null || date == null || ddate == null )
						{
							JOptionPane.showMessageDialog(null,"Order not completed");
						}
						else
						{
							discount = products.get(number2).getPrice()*products.get(number2).getDiscount();
							temp = products.get(number2).getPrice() - discount;
							totalcost = "" + temp;
							availability.set(3,available-1);
							JOptionPane.showMessageDialog(this,"Total Cost: " + totalcost);
							productsOrderCode = productsOrderCode + 1; 
							temp2 = "" + productsOrderCode;
							Order o = new Order(temp2,products.get(number2).getName(),fullname,phone,date,ddate,totalcost,false);
							orders.add(o);
							listModel3.addElement(o.getOcode());
							//////////////////////////////////////////////////////////////
							File f1 = null;
							BufferedWriter writer1 = null;
							try	
							{
								f1 = new File("Order.txt");
							}
							catch (NullPointerException e1) 
							{
								JOptionPane.showMessageDialog(null,"File not found.");
							}
							try	
							{
								writer1 = new BufferedWriter(new OutputStreamWriter
									(new FileOutputStream(f1,true)));
							}
							catch (FileNotFoundException e1) 
							{
								JOptionPane.showMessageDialog(null,"Error opening file for writing!");
							}
							/////////////////////////////////////////////////////////////////
							try
							{
								writer1.write("\r\n"+"\t"+"Order"+"\r\n"+"\t"+"{"+"\r\n"+"\t\t"+"Item_type Gaming"+"\r\n"+"\t\t"+"Order_number "+temp2+"\r\n"+"\t\t"+"Name "+fullname+"\r\n"+"\t\t"+"Phone "+phone+"\r\n"+"\t\t"+"Order_date "+date+
								"\r\n"+"\t\t"+"Delivery_date "+ddate+"\r\n"+"\t\t"+"Price "+totalcost+"\r\n"+"\t\t"+"Status Expected"+"\r\n"+"\t"+"}");
							}
							catch (IOException e1) 
							{
								JOptionPane.showMessageDialog(null,"Write error!");
							}
							try 
							{
								writer1.close();
				
							}
							catch (IOException e1) 
							{
								JOptionPane.showMessageDialog(null,"Error closing file.");
							}
						}
					}
					
				}
			}
			else if ( list1.getSelectedIndex() == 4)
			{
				available = availability.get(4);
				i = list2.getSelectedIndex();
				number = -1;
				number1 = -1;
				number2 = 0;
				for (Products1 k11 : products)
				{
					if ( k11 instanceof Washingmachine )
					{
						number = number+1;
					}
					if ( k11 instanceof Washingmachine && number==i )
					{
						number2 = number1 + 1;
					
					}
					number1 = number1 + 1;
				}
				pathimage = products.get(number2).getImagePath();
				icon = new ImageIcon(pathimage);
				if (pathimage != null) 
				{
					n = JOptionPane.showOptionDialog(null,products.get(number2),"Product Details",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE ,icon,options,options[0]);
				}
				else 
				{
					JOptionPane.showMessageDialog(null,products.get(number2));
				}
				if ( n == 0 && availability.get(4) != 0 )
				{
					fullname = (String)JOptionPane.showInputDialog(this, "Please type the name of the client");
					phone = (String)JOptionPane.showInputDialog(this, "Please type the phone of the client");
					date = (String)JOptionPane.showInputDialog(this, "Please type the date of the Sale",Calendar.getInstance().getTime());
					if ( fullname == null || phone == null || date == null )
					{
						JOptionPane.showMessageDialog(null,"Sale not completed");
					}
					else
					{
						discount = products.get(number2).getPrice()*products.get(number2).getDiscount();
						temp = products.get(number2).getPrice() - discount;
						totalcost = "" + temp;
						availability.set(4,available-1);
						JOptionPane.showMessageDialog(this,"Total Cost: " + totalcost);
						productsSaleCode = productsSaleCode + 1; 
						temp1 = "" + productsSaleCode;
						Sales s = new Sales(temp1,products.get(number2).getName(),fullname,phone,date,totalcost);
						sales.add(s);
						listModel4.addElement(s.getScode());
						
						File f = null;
						BufferedWriter writer = null;
						
						try	
						{
							f = new File("Sale.txt");
						}
						catch (NullPointerException e) 
						{
							JOptionPane.showMessageDialog(null,"File not found."); 
						}
			
						try	
						{
							writer = new BufferedWriter(new OutputStreamWriter
								(new FileOutputStream(f,true)));
						}
						catch (FileNotFoundException e) 
						{
							JOptionPane.showMessageDialog(null,"Error opening file for writing!");
						}
						
						try
						{
							writer.write("\r\n"+"\t"+"Sale"+"\r\n"+"\t"+"{"+"\r\n"+"\t\t"+"Item_type Fridges"+"\r\n"+"\t\t"+"Sales_number "+temp1+"\r\n"+"\t\t"+"Name "+fullname+"\r\n"+"\t\t"+"Phone "+phone+"\r\n"+"\t\t"+"Sale_date "+date+
							"\r\n"+"\t\t"+"Price "+totalcost+"\r\n"+"\t"+"}");
						}
						catch (IOException e) 
						{
							JOptionPane.showMessageDialog(null,"Write error!");
						}
						try 
						{
							writer.close();
						}
						catch (IOException e) 
						{
							JOptionPane.showMessageDialog(null,"Error closing file.");
						}
					}
				}
				else if ( n==0 && availability.get(4) == 0 )
				{
					m = JOptionPane.showOptionDialog(null,products.get(number2),"Not available in the store",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE ,icon,options1,options1[0]);
					if ( m == 0 )
					{
						Calendar now = Calendar.getInstance();
						now.add(Calendar.DAY_OF_YEAR,14);
						fullname = (String)JOptionPane.showInputDialog(this, "Please type the name of the client");
						phone = (String)JOptionPane.showInputDialog(this, "Please type the phone of the client");
						date = (String)JOptionPane.showInputDialog(this, "Please type the date of the Order",Calendar.getInstance().getTime());
						ddate = (String)JOptionPane.showInputDialog(this, "Please type the date of delivery",now.getTime());
						if ( fullname == null || phone == null || date == null || ddate == null )
						{
							JOptionPane.showMessageDialog(null,"Order not completed");
						}
						else
						{
							discount = products.get(number2).getPrice()*products.get(number2).getDiscount();
							temp = products.get(number2).getPrice() - discount;
							totalcost = "" + temp;
							availability.set(4,available-1);
							JOptionPane.showMessageDialog(this,"Total Cost: " + totalcost);
							productsOrderCode = productsOrderCode + 1; 
							temp2 = "" + productsOrderCode;
							Order o = new Order(temp2,products.get(number2).getName(),fullname,phone,date,ddate,totalcost,false);
							orders.add(o);
							listModel3.addElement(o.getOcode());
							//////////////////////////////////////////////////////////////
							File f1 = null;
							BufferedWriter writer1 = null;
							try	
							{
								f1 = new File("Order.txt");
							}
							catch (NullPointerException e1) 
							{
								JOptionPane.showMessageDialog(null,"File not found.");
							}
							try	
							{
								writer1 = new BufferedWriter(new OutputStreamWriter
									(new FileOutputStream(f1,true)));
							}
							catch (FileNotFoundException e1) 
							{
								JOptionPane.showMessageDialog(null,"Error opening file for writing!");
							}
							/////////////////////////////////////////////////////////////////
							try
							{
								writer1.write("\r\n"+"\t"+"Order"+"\r\n"+"\t"+"{"+"\r\n"+"\t\t"+"Item_type Fridges"+"\r\n"+"\t\t"+"Order_number "+temp2+"\r\n"+"\t\t"+"Name "+fullname+"\r\n"+"\t\t"+"Phone "+phone+"\r\n"+"\t\t"+"Order_date "+date+
								"\r\n"+"\t\t"+"Delivery_date "+ddate+"\r\n"+"\t\t"+"Price "+totalcost+"\r\n"+"\t\t"+"Status Expected"+"\r\n"+"\t"+"}");
							}
							catch (IOException e1) 
							{
								JOptionPane.showMessageDialog(null,"Write error!");
							}
							try 
							{
								writer1.close();
				
							}
							catch (IOException e1) 
							{
								JOptionPane.showMessageDialog(null,"Error closing file.");
							}
						}
					}
					
				}
			}
			else if ( list1.getSelectedIndex() == 5)
			{
				available = availability.get(5);
				i = list2.getSelectedIndex();
				number = -1;
				number1 = -1;
				number2 = 0;
				for (Products1 k11 : products)
				{
					if ( k11 instanceof Friges )
					{
						number = number+1;
					}
					if ( k11 instanceof Friges && number==i )
					{
						number2 = number1 + 1;
					
					}
					number1 = number1 + 1;
				}
				pathimage = products.get(number2).getImagePath();
				icon = new ImageIcon(pathimage);
				if (pathimage != null) 
				{
					n = JOptionPane.showOptionDialog(null,products.get(number2),"Product Details",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE ,icon,options,options[0]);
				}
				else 
				{
					JOptionPane.showMessageDialog(null,products.get(number2));
				}
				if ( n == 0 && availability.get(5) != 0 )
				{
					fullname = (String)JOptionPane.showInputDialog(this, "Please type the name of the client");
					phone = (String)JOptionPane.showInputDialog(this, "Please type the phone of the client");
					date = (String)JOptionPane.showInputDialog(this, "Please type the date of the Sale",Calendar.getInstance().getTime());
					if ( fullname == null || phone == null || date == null )
					{
						JOptionPane.showMessageDialog(null,"Sale not completed");
					}
					else
					{
						discount = products.get(number2).getPrice()*products.get(number2).getDiscount();
						temp = products.get(number2).getPrice() - discount;
						totalcost = "" + temp;
						availability.set(5,available-1);
						JOptionPane.showMessageDialog(this,"Total Cost: " + totalcost);
						productsSaleCode = productsSaleCode + 1; 
						temp1 = "" + productsSaleCode;
						Sales s = new Sales(temp1,products.get(number2).getName(),fullname,phone,date,totalcost);
						sales.add(s);
						listModel4.addElement(s.getScode());
						
						File f = null;
						BufferedWriter writer = null;
						
						try	
						{
							f = new File("Sale.txt");
						}
						catch (NullPointerException e) 
						{
							JOptionPane.showMessageDialog(null,"File not found."); 
						}
			
						try	
						{
							writer = new BufferedWriter(new OutputStreamWriter
								(new FileOutputStream(f,true)));
						}
						catch (FileNotFoundException e) 
						{
							JOptionPane.showMessageDialog(null,"Error opening file for writing!");
						}
						
						try
						{
							writer.write("\r\n"+"\t"+"Sale"+"\r\n"+"\t"+"{"+"\r\n"+"\t\t"+"Item_type Washingmachine"+"\r\n"+"\t\t"+"Sales_number "+temp1+"\r\n"+"\t\t"+"Name "+fullname+"\r\n"+"\t\t"+"Phone "+phone+"\r\n"+"\t\t"+"Sale_date "+date+
							"\r\n"+"\t\t"+"Price "+totalcost+"\r\n"+"\t"+"}");
						}
						catch (IOException e) 
						{
							JOptionPane.showMessageDialog(null,"Write error!");
						}
						try 
						{
							writer.close();
						}
						catch (IOException e) 
						{
							JOptionPane.showMessageDialog(null,"Error closing file.");
						}
					}
				}
				else if ( n==0 && availability.get(5) == 0 )
				{
					m = JOptionPane.showOptionDialog(null,products.get(number2),"Not available in the store",JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE ,icon,options1,options1[0]);
					if ( m == 0 )
					{
						Calendar now = Calendar.getInstance();
						now.add(Calendar.DAY_OF_YEAR,14);
						fullname = (String)JOptionPane.showInputDialog(this, "Please type the name of the client");
						phone = (String)JOptionPane.showInputDialog(this, "Please type the phone of the client");
						date = (String)JOptionPane.showInputDialog(this, "Please type the date of the Order",Calendar.getInstance().getTime());
						ddate = (String)JOptionPane.showInputDialog(this, "Please type the date of delivery",now.getTime());
						if ( fullname == null || phone == null || date == null || ddate == null )
						{
							JOptionPane.showMessageDialog(null,"Order not completed");
						}
						else
						{
							discount = products.get(number2).getPrice()*products.get(number2).getDiscount();
							temp = products.get(number2).getPrice() - discount;
							totalcost = "" + temp;
							availability.set(5,available-1);
							JOptionPane.showMessageDialog(this,"Total Cost: " + totalcost);
							productsOrderCode = productsOrderCode + 1; 
							temp2 = "" + productsOrderCode;
							Order o = new Order(temp2,products.get(number2).getName(),fullname,phone,date,ddate,totalcost,false);
							orders.add(o);
							listModel3.addElement(o.getOcode());
							//////////////////////////////////////////////////////////////
							File f1 = null;
							BufferedWriter writer1 = null;
							try	
							{
								f1 = new File("Order.txt");
							}
							catch (NullPointerException e1) 
							{
								JOptionPane.showMessageDialog(null,"File not found.");
							}
							try	
							{
								writer1 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(f1,true)));
							}
							catch (FileNotFoundException e1) 
							{
								JOptionPane.showMessageDialog(null,"Error opening file for writing!");
							}
							/////////////////////////////////////////////////////////////////
							try
							{
								writer1.write("\r\n"+"\t"+"Order"+"\r\n"+"\t"+"{"+"\r\n"+"\t\t"+"Item_type Washingmachine"+"\r\n"+"\t\t"+"Order_number "+temp2+"\r\n"+"\t\t"+"Name "+fullname+"\r\n"+"\t\t"+"Phone "+phone+"\r\n"+"\t\t"+"Order_date "+date+
								"\r\n"+"\t\t"+"Delivery_date "+ddate+"\r\n"+"\t\t"+"Price "+totalcost+"\r\n"+"\t\t"+"Status Expected"+"\r\n"+"\t"+"}");
							}
							catch (IOException e1) 
							{
								JOptionPane.showMessageDialog(null,"Write error!");
							}
							try 
							{
								writer1.close();
				
							}
							catch (IOException e1) 
							{
								JOptionPane.showMessageDialog(null,"Error closing file.");
							}
						}
					}
					
				}
			}
			
			
		}
	}
	
	
	
	public void mouseExited(MouseEvent event){}
	public void mouseEntered(MouseEvent event){}
	public void mouseReleased(MouseEvent event){}
	public void mousePressed(MouseEvent event){}

	
	
	
    public static void main(String[] args) 
	{
		for ( int k10=0;k10<=6;k10++)
		{
			availability.add(0);
		}

		mainApp tp = new mainApp();
        tp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tp.setVisible(true);
         
    }
}


