package MCI;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;

public class UI extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextArea textField_1;
	private int is_full;

	ArrayList<String> body = new ArrayList<>();;
	String text = "";
	private final JPanel panel_1 = new JPanel();
	/**
	 * Launch the application.
	 */

	public void run() {
		try {
			
			UI frame = new UI();
			frame.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the frame.
	 */
	public UI() {
		
		this.setTitle("Recommendation system based on stackoverflow");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 685, 512);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 47, 29);
		
//		contentPane.add(menuBar);
		
		JMenu mnNewMenu = new JMenu("File");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Open");
		mntmNewMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.ALT_MASK));
		mnNewMenu.add(mntmNewMenuItem);
		mntmNewMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
				fileChooser.setDialogTitle("Select fsa file");
				fileChooser.setAcceptAllFileFilterUsed(false);
				try{
					FileNameExtensionFilter filter = new FileNameExtensionFilter( ".txt", "txt","*.txt");
					fileChooser.addChoosableFileFilter(filter);
					int returnValue = fileChooser.showOpenDialog(UI.this);

					if (returnValue == JFileChooser.APPROVE_OPTION) {
						System.out.println(fileChooser.getSelectedFile().getPath());

					}else{
						return ;
					}
				}catch(Exception ex){
					System.out.println(ex.getMessage());
					
				}
				try 
		        {
		            read_File r = new read_File();
		            body = r.readFile(fileChooser.getSelectedFile().getPath());
		        }
		        catch (Exception ex)
		        {
		            JDialog d = new JDialog(UI.this, "dialog Box"); 
  					d.getContentPane().setLayout(new FlowLayout());
		            // create a label 
		            JLabel l = new JLabel("Something is wrong " + ex.getMessage()); 
		  			JButton b = new JButton(" OK ");
		  			
		  			b.addActionListener(new ActionListener(){
		  				public void actionPerformed(ActionEvent e)
			            {
			                d.setVisible(false);
							d.dispose();
			            }
			        });				
		            d.getContentPane().add(l);
		  			d.getContentPane().add(b);
		            d.setBounds(800, 600, 200, 100); 
		            d.setVisible(true); 
		        }
            }
        });
		
		JMenuItem mntmSaveAs = new JMenuItem("Save url as...");
		mnNewMenu.add(mntmSaveAs);
		mntmSaveAs.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.ALT_MASK));
			        
		mntmSaveAs.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
			        JFileChooser fileChooser = new JFileChooser();
					fileChooser.setDialogTitle("save");   
					fileChooser.setAcceptAllFileFilterUsed(false);

					FileNameExtensionFilter filter2 = new FileNameExtensionFilter( ".txt", "txt","*.txt");
					fileChooser.addChoosableFileFilter(filter2);
					// fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));

					int userSelection = fileChooser.showSaveDialog(UI.this);
					if (userSelection == JFileChooser.APPROVE_OPTION) {
						System.out.println(fileChooser.getSelectedFile().getPath());
				
					}else{
						return ;
					}
					try
			        {
						run r = new run();
						ArrayList<String> ans = r.sort_output(body);
						String path = fileChooser.getSelectedFile().getPath()+".txt";
			        	write_url w = new write_url();
			        	w.output(ans,path);
			    
			            System.out.println(fileChooser.getSelectedFile().getName());
			        }
			        catch (Exception ex)
			        {
			            JDialog d = new JDialog(UI.this, "dialog Box"); 
	  					d.getContentPane().setLayout(new FlowLayout());
			            JLabel l = new JLabel("Something is wrong " + ex.getMessage()); 
			  			JButton b = new JButton(" OK ");
			  			b.addActionListener(new ActionListener(){
			  				public void actionPerformed(ActionEvent e)
				            {
				                d.setVisible(false);
								d.dispose();
				            }
				        });				
			            d.getContentPane().add(l);
			  			d.getContentPane().add(b);
			            d.setBounds(800, 600, 200, 100); 
			            d.setVisible(true); 
			        }
			        
			    }
        });
		
		JMenuItem mntmSaveAs2 = new JMenuItem("Save body as...");
		mnNewMenu.add(mntmSaveAs2);
		mntmSaveAs2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.ALT_MASK));
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 32, 649, 104);
		contentPane.add(panel);
		panel.setLayout(null);
		panel.add(menuBar);
		
		JLabel lblPleaseEnteringThe = new JLabel("Please entering the website");
		lblPleaseEnteringThe.setBounds(0, 0, 324, 29);
		panel.add(lblPleaseEnteringThe);
		
		textField = new JTextField();
		textField.setBounds(0, 31, 371, 35);
		panel.add(textField);
		textField.setColumns(100);
		
		JButton btnStartSearching = new JButton("Start searching");
		btnStartSearching.setBounds(372, 30, 129, 37);
		panel.add(btnStartSearching);
		
		JButton btnNewButton_1 = new JButton("clear input");
		btnNewButton_1.setBounds(504, 30, 134, 37);
		panel.add(btnNewButton_1);
		
		JToggleButton tglbtnNewToggleButton = new JToggleButton("match only");
		tglbtnNewToggleButton.setBounds(0, 69, 100, 35);
		panel.add(tglbtnNewToggleButton);
		panel_1.setBounds(20, 145, 639, 232);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		textField_1 = new JTextArea();
		textField_1.setBounds(0, 0, 277, 227);
		panel_1.add(textField_1);
		textField_1.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(309, 0, 330, 232);
		panel_1.add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.setLineWrap(true);
//		JScrollPane scrollableTextArea = new JScrollPane(textArea);  
//		  
//        scrollableTextArea.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);  
//        scrollableTextArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);  
        
        
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 388, 279, 36);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JButton btnPieChart = new JButton("pie chart");
		btnPieChart.setBounds(0, 0, 141, 37);
		panel_2.add(btnPieChart);
		
		
//https://i.stack.imgur.com/9Ab9i.png
		JButton btnBarChart = new JButton("Bar chart");
		btnBarChart.setBounds(154, 0, 125, 37);
		panel_2.add(btnBarChart);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(329, 388, 330, 45);
		contentPane.add(panel_3);
		panel_3.setLayout(null);
//      this.getContentPane().setLayout(new FlowLayout());  
//      contentPane.add(scrollableTextArea);

		JButton btnNewButton = new JButton("url of the answer");
		btnNewButton.setBounds(0, -1, 149, 36);
		panel_3.add(btnNewButton);
		btnNewButton.setFont(new Font("Arial", Font.PLAIN, 12));
				
		
		
		JButton btnOriginalAnswer = new JButton("whole answer");
		btnOriginalAnswer.setBounds(194, -1, 129, 36);
		panel_3.add(btnOriginalAnswer);
		btnOriginalAnswer.setFont(new Font("Arial", Font.PLAIN, 12));
		btnOriginalAnswer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textArea.setText("");
				if(body.size()==0&&text.equals("")) {
					textField_1.append("enter the question first\n");
				}else if(body.size()==0&&!text.equals("")){
					textField_1.append("enter the question first\n");
				}else {
					for(String url:body) {
						textArea.append(url+"\n");
						textArea.append("------------\n");
					}
					
				}
			}
		});
				btnNewButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						textArea.setText("");
						run r = new run();
						if(body.size()==0&&text.equals("")) {
							textField_1.append("enter the question first\n");
						}else if(body.size()==0&&!text.equals("")){
							textField_1.append("enter the question first\n");
						}else {
							ArrayList<String> ans = r.sort_output(body);
							for(String url:ans) {
								textArea.append(url+"\n");
							}
							
						}
					}
				});
		btnBarChart.addActionListener(new ActionListener(){
			   public void actionPerformed(ActionEvent ae){
				   List<String> file_data_output = new ArrayList<String>();
				   
				   run r = new run();
				   textField_1.append("reading result...\n");
				   ArrayList<String> ans = r.sort_output_nosum(body);

				   textField_1.append("result reading finish...\n");
				   editor2 ed = new editor2();
				   textField_1.append("creating bar chart...\n");
				   String  bar_result= ed.read_output(ans);
				   if(bar_result.equals("no answer for your question")||bar_result=="") {
					   textField_1.append(bar_result);
				   }else {
					   editor2_UI p = new editor2_UI();
					   p.run();
					   textField_1.append(bar_result);
				   }
			   }
			});
		btnPieChart.addActionListener(new ActionListener(){
			   public void actionPerformed(ActionEvent ae){
				   List<String> file_data_output = new ArrayList<String>();
				   
				   run r = new run();
				   textField_1.append("reading result...\n");
				   ArrayList<String> ans = r.sort_output_nosum(body);

				   textField_1.append("result reading finish...\n");
				   editor ed = new editor();
				   textField_1.append("creating pie chart...\n");
				   String  pie_result= ed.read_output(ans);
				   if(pie_result.equals("no answer for your question")||pie_result=="") {
					   textField_1.append(pie_result);
				   }else {
					   System.out.println("here");
					   editor_UI p = new editor_UI();
					   p.run();
					   textField_1.append(pie_result);
				   }
			   }
			});
		
				
		//		JScrollPane scrollPane = new JScrollPane(
		//				textArea,
		//                ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
		//                ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS
		//                );
		//	
		//		
		//		scrollPane.setBounds(612, 126, 26, 232);
		//		contentPane.add(scrollPane);
				
				tglbtnNewToggleButton.addActionListener(new ActionListener() {
			        public void actionPerformed(ActionEvent e) {
			        	if(is_full == 1) {
			        		is_full = 0;
			        	}else {
			        		is_full = 1;
			        	}
		
			        }});
		
		
		
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				textField.selectAll();
				textField.replaceSelection("");
			}
		});
		btnStartSearching.addActionListener(new ActionListener(){
		   public void actionPerformed(ActionEvent ae){
			  textField_1.append("searching...\n");
		      String getValue = textField.getText();
		      if(!getValue.contains("http")) {
		    	  textField_1.append("wrong url, please check it\n");
		      }else {
		    	  MySQL sq = new MySQL();
		    	  body = sq.connect_sql(getValue,is_full);
		    	  if(body.size()==1&&body.get(0).equals("-1")) {
		    		  textField_1.append("Can not find your question");
		    	  }else if(body.size()==1&&body.get(0).equals("0")) {
		    		  textField_1.append("Can not find your answer");
		    	  } else {
		    	
		    			  run r = new run();	    	  
						  text = r.extract(body);
						  textField_1.append(text+"\n");  
						  textField_1.append("--------------------");
		    	  }
		      }
		     
		   }
		});
			        
		mntmSaveAs2.addActionListener(new ActionListener() {
		        public void actionPerformed(ActionEvent e) {
			        JFileChooser fileChooser = new JFileChooser();
					fileChooser.setDialogTitle("save body as");   
					fileChooser.setAcceptAllFileFilterUsed(false);

					FileNameExtensionFilter filter3 = new FileNameExtensionFilter( ".txt", "txt","*.txt");
					fileChooser.addChoosableFileFilter(filter3);

					int userSelection = fileChooser.showSaveDialog(UI.this);
					if (userSelection == JFileChooser.APPROVE_OPTION) {
						System.out.println(fileChooser.getSelectedFile().getPath());
				
					}else{
						return ;
					}
					try
			        {
						String path = fileChooser.getSelectedFile().getPath();
			        	write_Body w = new write_Body();
			        	w.output(body,path);
			    
			            System.out.println(fileChooser.getSelectedFile().getName());
			        }
			        catch (Exception ex)
			        {
			            JDialog d = new JDialog(UI.this, "dialog Box"); 
	  					d.getContentPane().setLayout(new FlowLayout());
			            JLabel l = new JLabel("Something is wrong " + ex.getMessage()); 
			  			JButton b = new JButton(" OK ");
			  			b.addActionListener(new ActionListener(){
			  				public void actionPerformed(ActionEvent e)
				            {
				                d.setVisible(false);
								d.dispose();
				            }
				        });				
			            d.getContentPane().add(l);
			  			d.getContentPane().add(b);
			            d.setBounds(800, 600, 200, 100); 
			            d.setVisible(true); 
			        }
			        
			    }
	        });
	}
}
