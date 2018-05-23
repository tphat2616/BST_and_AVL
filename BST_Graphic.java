package BST_Graphic;

import java.util.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.TitledBorder;







class Node
{
        Node leftChild;
        Node rightChild;
        int mssv;

        Node() {
                leftChild = null;
                rightChild = null;
                mssv = 0;
        }
        
        Node(int a) {
                leftChild = null;
                rightChild = null;
                mssv = a;
        }
}
public class BST_Graphic extends JFrame implements ActionListener{
	 
	Node parent;      //Khỡi tạo nút cha.
	//-----------------------------------------------------------------------------------------------------------------
	//Dựng cây rỗng
	//dựng cây với thông tin ngẫu nhiên
	//Hàm dựng cây BST_Graphic lệch trái.
	
	String lnr;                                                              	//Duyệt cây LNR
    String nlr;                                                              	//Duyệt cây NLR
    String lrn;																	//Duyệt cây LRN 
    String lNodes;                                                              //các Node là lá
    String nlNodes;
    
    Node root = null;                                                           //Khỡi tạo root
    Node imageNode = null;                                                      //
    int numberOfNodes = 0;
    int[][] nodeCoords = new int[500][3];                                       //array to store the coodinates of the nodes of the tree
    static int counter = 0;
    
    boolean treePainted = false;                                                //boolean variable to indicate that the tree has been painted
    boolean imagePainted = false;
    
    private JButton tree = new JButton("Tree");                                 
    private JButton Lnr = new JButton("Lnr");
    private JButton Nlr = new JButton("Nlr");
    private JButton Lrn = new JButton("Lrn");
    private JButton nodeMax = new JButton("nodeMax");
    private JButton nodeMin = new JButton("nodeMin");
    private JButton image = new JButton("Image");
    private JButton search = new JButton("Search");      
    private JButton reset = new JButton("Reset");
    private JButton exit = new JButton("Exit");
    
    private JTextField f1 = new JTextField(15);
    private JTextField f2 = new JTextField(15);
    private JTextField f3 = new JTextField(15);
    private JTextField f4 = new JTextField(15);
    private JTextField f5 = new JTextField(15);
    private JTextField f6 = new JTextField(15);
    private JTextField f7 = new JTextField(15);
    private JTextField f8 = new JTextField(15);
    
  //Panel contains the buttons
    private JPanel buttonPanel = new JPanel();
    
  //Panel contains the text fields
    private JPanel fieldPanel = new JPanel();
    Font font = new Font("Verdana", Font.BOLD, 12);
    
  //Panel to display the tree
    private Tree view = new Tree(); 
    
  //Panel to display the tree image
    private Tree imageView = new Tree();
    
    public BST_Graphic() {
        //Constructor to tree the GUI of the class
        setTitle("Binary Tree");
        setBackground(Color.white);
        
        //Adding the tree view panel to the frame, setting border and size
        add(view);
        view.setBorder(new TitledBorder("Tree View"));
        view.setPreferredSize(new Dimension(400,400));
        view.setVisible(true);
        view.setBackground(Color.white);
        
        //Adding the tree image panel to the frame, setting border and size
        add(imageView);
        imageView.setBorder(new TitledBorder("Tree Image View"));
        imageView.setPreferredSize(new Dimension(400,400));
        imageView.setVisible(true);
        imageView.setBackground(Color.white);
        
        //Adding panels containing the buttons and textfields
        add(buttonPanel);
        add(fieldPanel);
        
        //Setting layouts for the button panel and field panel
        buttonPanel.setLayout(new GridLayout(9,1));
        fieldPanel.setLayout(new GridLayout(9,1));
        
        //GridLayout for the whole frame
        setLayout(new GridLayout(2,2));
        
        //Setting the color scheme for the buttons and adding the text fields and buttons
        tree.setBackground(Color.LIGHT_GRAY);
        buttonPanel.add(tree);        
        fieldPanel.add(f1);
        
        Lnr.setBackground(Color.LIGHT_GRAY);
        buttonPanel.add(Lnr);       
        fieldPanel.add(f2);
        
        Nlr.setBackground(Color.LIGHT_GRAY);
        buttonPanel.add(Nlr);        
        fieldPanel.add(f3);
        
        Lrn.setBackground(Color.LIGHT_GRAY);
        buttonPanel.add(Lrn);       
        fieldPanel.add(f4);
                
        nodeMin.setBackground(Color.LIGHT_GRAY);
        buttonPanel.add(nodeMin);        
        fieldPanel.add(f5);
        
        nodeMax.setBackground(Color.LIGHT_GRAY);
        buttonPanel.add(nodeMax);        
        fieldPanel.add(f6);
        
        image.setBackground(Color.LIGHT_GRAY);
        buttonPanel.add(image);
        fieldPanel.add(f7);
        
        
        search.setBackground(Color.LIGHT_GRAY);
        buttonPanel.add(search);       
        fieldPanel.add(f8);
        
        buttonPanel.add(reset);     
        reset.setBackground(Color.black);
        reset.setForeground(Color.white);
        
        fieldPanel.add(exit);
        exit.setBackground(Color.black);
        exit.setForeground(Color.white);
        
        //Adding ActionListeners to all the buttons
        tree.addActionListener(this);
        Lnr.addActionListener(this);
        Nlr.addActionListener(this);
        Lrn.addActionListener(this);
        image.addActionListener(this);
        search.addActionListener(this);
        nodeMax.addActionListener(this);
        nodeMin.addActionListener(this);
        reset.addActionListener(this);
        exit.addActionListener(this);
        
        //All buttons other than Tree, Reset and Exit are disabled by default
        Lnr.setEnabled(false);
        Nlr.setEnabled(false);
        Lrn.setEnabled(false);
        nodeMax.setEnabled(false);
        image.setEnabled(false);
        nodeMin.setEnabled(false);
        search.setEnabled(false);
        f2.setEditable(false);
        f3.setEditable(false);
        f4.setEditable(false);
        f5.setEditable(false);
        f6.setEditable(false);
        f7.setEditable(false);
        
        //Finishing up
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }
	public static void main(String[] args) {
		new BST_Graphic();		
	}
	//-----------------------------------------------------------------------------------------------------------------
	private static Node insert(Node node, int mssv)   {
        //Function to insert given mssv into the tree
        //according to the Binary Tree property
        if (node == null)
            node = new Node(mssv);
        else
        {
            if (mssv <= node.mssv)
                node.leftChild = insert(node.leftChild, mssv);
            else
                node.rightChild = insert(node.rightChild, mssv);
        }
        return node;
    }
	//-----------------------------------------------------------------------------------------------------------------
	//duyệt cây theo left node right.
	public void Lnr(Node root) {
		if(root != null) {
			Lnr(root.leftChild);
			lnr += root.mssv + " ";
			Lnr(root.rightChild);
		}
	}
	//duyệt cây theo left right node.
	public void Lrn(Node root) {
		if(root != null) {
			Lrn(root.leftChild);
			Lrn(root.rightChild);
			lrn += root.mssv + " ";
		}
	}
	//duyệt theo node left right.
	public void Nlr(Node root) {
		if(root != null) {
			nlr += root.mssv + " ";
			Nlr(root.leftChild);
			Nlr(root.rightChild);
		}
	}
    private static void Image(Node node) {
        //Function to create an image of passed tree (pass the root)
        if (node != null) { 
        Image(node.leftChild); 
        Image(node.rightChild);
        Node temp = node.leftChild; 
        node.leftChild = node.rightChild; 
        node.rightChild = temp; 
        }
    }
	//tìm kiếm phần tử lớn nhất.
	public boolean Max(Node root) {
		 Graphics g = getGraphics();
	        try {
	                   Thread.sleep(500);
	               } catch (InterruptedException ex) {
	                   Logger.getLogger(BST_Graphic.class.getName()).log(Level.SEVERE, null, ex);
	               }
	        if (root == null) { 
	            return false; 
	        }   
		Node focusNode = root;
		Node max;
		max = focusNode;
		while(max.rightChild != null) {
			return Max(max.rightChild);
		}
        int[] temp = searchInCoords(max.mssv);
            if(temp[0] != 0 && temp[1] != 0) {
                 g.setColor(Color.green);               
                 g.fillOval(temp[0] + 8, temp[1] + 31, 30, 30);
                 g.setColor(Color.black);
                 g.drawString(max.mssv + "", temp[0] + 20, temp[1] + 50);   
            }
            return true;       
	}
	//tìm phần tử nhỏ nhất.
	public boolean Min(Node root) {
		Graphics g = getGraphics();
        try {
                   Thread.sleep(500);
               } catch (InterruptedException ex) {
                   Logger.getLogger(BST_Graphic.class.getName()).log(Level.SEVERE, null, ex);
               }
        if (root == null) { 
            return false; 
        }   
		Node focusNode = root;
		Node min;
		min = focusNode;
		while(min.leftChild != null) {
			return Min(min.leftChild);
		}
		int[] temp = searchInCoords(min.mssv);
        if(temp[0] != 0 && temp[1] != 0) {
             g.setColor(Color.green);               
             g.fillOval(temp[0] + 8, temp[1] + 31, 30, 30);
             g.setColor(Color.black);
             g.drawString(min.mssv + "", temp[0] + 20, temp[1] + 50);   
        }
        return true;  
	}
	//-----------------------------------------------------------------------------------------------------------------
		//Tìm kiếm theo mã sinh viên.
	 private boolean searchTree(Node node, int mssv) {
	        //Recursive function to perform search of passed mssv on a Binary Tree
	        //If mssv is found, returns true else false
	        Graphics g = getGraphics();
	        try {
	                   Thread.sleep(500);
	               } catch (InterruptedException ex) {
	                   Logger.getLogger(BST_Graphic.class.getName()).log(Level.SEVERE, null, ex);
	               }
	        if (node == null) { 
	            return false; 
	        }
	        
	        //if mssv is found, its node is found and is changed to green color
	        if (mssv == node.mssv) { 
	           int[] temp = searchInCoords(node.mssv);
	           if(temp[0] != 0 && temp[1] != 0) {
	                g.setColor(Color.green);               
	                g.fillOval(temp[0] + 8, temp[1] + 31, 30, 30);
	                g.setColor(Color.black);
	                g.drawString(node.mssv + "", temp[0] + 20, temp[1] + 50);   
	           }
	           return true; 
	        } 
	        
	        //if mssv is not found, the current node is changed to yellow color and the search is called recursively
	        //on the leftChild subtree since mssv is smaller than current node mssv
	        else if (mssv<node.mssv) { 
	            int[] temp = searchInCoords(node.mssv);
	            if(temp[0] != 0 && temp[1] != 0) {
	                g.setColor(Color.yellow);                
	                g.fillOval(temp[0] + 8, temp[1] + 31, 30, 30);
	                g.setColor(Color.black);
	                g.drawString(node.mssv + "", temp[0] + 20, temp[1] + 50);   
	            }
	            return searchTree(node.leftChild, mssv); 
	        } 
	        
	        //if mssv is not found, the current node is changed to yellow color and the search is called recursively
	        //on the rightChild subtree since mssv is greater than current node mssv
	        else { 
	            int[] temp = searchInCoords(node.mssv);
	            if(temp[0] != 0 && temp[1] != 0) {
	                g.setColor(Color.yellow);
	                g.fillOval(temp[0] + 8, temp[1] + 31, 30, 30);
	                g.setColor(Color.black);
	                g.drawString(node.mssv + "", temp[0] + 20, temp[1] + 50);   
	           }
	            return searchTree(node.rightChild, mssv); 
	        } 
	    } 
	@Override
    public void actionPerformed(ActionEvent e) {
       //ActionListener 
       if(e.getSource() == tree) {
            String str = f1.getText();
            try {
                //Input is split using "," and is parsed
                String[] treeSplit = str.split(",");
                numberOfNodes = treeSplit.length;
                
                //Root node treed
                Node temp = new Node(Integer.parseInt(treeSplit[0]));
                f2.setText("");
                f3.setText("");
                f4.setText("");
                f5.setText("");
                f6.setText("");
                f7.setText("");
                f8.setText("");

                //Iterate through the split input and add nodes to the tree
                for(int i=1; i<treeSplit.length;i++) {
                    temp = insert(temp,Integer.parseInt(treeSplit[i]));            
                }
                
                //Set root to temp
                root = temp;
                imageNode = null;
                
                //Paint the tree on the panel
                view.paintTree();
                
                //Indicate that the tree has been painted
                treePainted = true;
                imagePainted = false;
                
                //Enable binary tree function buttons
                Lnr.setEnabled(true);
                Nlr.setEnabled(true);
                Lrn.setEnabled(true);
                nodeMax.setEnabled(true);
                nodeMin.setEnabled(true);
                image.setEnabled(true);
                search.setEnabled(true);

            } catch (Exception exception) { 
                JOptionPane.showMessageDialog(null, "Cây rỗng!!!Vui lòng nhập dữ liệu cho cây.");
            f1.setText("");}
       }
       else if(e.getSource() == Lnr) {
           //Perform inorder traversal and display it
           lnr = "";
           Lnr(root);
           f2.setText(lnr);
       }
       else if(e.getSource() == Nlr) {
           //Perform postorder traversal and display it
           nlr = "";
           Nlr(root);
           f3.setText(nlr);
       }
       else if(e.getSource() == Lrn) {
           //Perform preorder traversal and display it
           lrn = "";
           Lrn(root);
           f4.setText(lrn);
       }
       else if(e.getSource() == nodeMin) {
           if(f5.getText() != null) {
               try {
                //Parse search value
                int x = Integer.parseInt(f5.getText()); 
                view.paintTree();
                
                //Search parsed value in the tree
                if(Min(root)) {
                    //if value is found, inform user
                    f5.setFont(font);
                    f5.setForeground(Color.green);
                    f5.setText(x + " ở trong cây");
                }
                else {
                    //if value is not found, inform user
                    f5.setFont(font);
                    f5.setForeground(Color.red);
                    f5.setText(x + " Không tìm được min");
                }
               } catch(Exception ex) { JOptionPane.showMessageDialog(null, "Nhập không đúng định dạng."); }
           }
           
       }
       else if(e.getSource() == nodeMax) {
           if(f6.getText() != null) {
               try {
                //Parse search value
                int x = Integer.parseInt(f6.getText()); 
                view.paintTree();
                
                //Search parsed value in the tree
                if(Max(root)) {
                    //if value is found, inform user
                    f6.setFont(font);
                    f6.setForeground(Color.green);
                }
                else {
                    //if value is not found, inform user
                    f6.setFont(font);
                    f6.setForeground(Color.red);
                    f6.setText(x + " Không tìm được max");
                }
               } catch(Exception ex) { JOptionPane.showMessageDialog(null, "Nhập không đúng định dạng."); }
           }
           
       }
       else if(e.getSource() == image) {
           String str = f1.getText();
           String[] treeSplit = str.split(",");
           
           //Creating image Binary Tree
           Node temp = new Node(Integer.parseInt(treeSplit[0]));
           for(int i=1; i<treeSplit.length;i++) {
               temp= insert(temp,Integer.parseInt(treeSplit[i]));            
           }
           f7.setText("Tree Image displayed");
           
           //Compute the Image
           Image(temp);
           imageNode = temp;
           
           //Display the image Tree
           imageView.paintImage();
           
           //Indicate image has been displayed
           imagePainted = true;
      }
       else if(e.getSource() == search) {
           if(f8.getText() != null) {
               try {
                //Parse search value
                int x = Integer.parseInt(f8.getText()); 
                view.paintTree();
                
                //Search parsed value in the tree
                if(searchTree(root,x)) {
                    //if value is found, inform user
                    f8.setFont(font);
                    f8.setForeground(Color.green);
                    f8.setText(x + " is in the Tree");
                }
                else {
                    //if value is not found, inform user
                    f8.setFont(font);
                    f8.setForeground(Color.red);
                    f8.setText(x + " is not in the Tree");
                }
               } catch(Exception ex) { JOptionPane.showMessageDialog(null, "Nhập không đúng định dạng."); }
           }
           
       }
       else if(e.getSource() == reset) {
           //Reset the program
           dispose();
           new BST_Graphic();
       }
       else if(e.getSource() == exit) {
           //Exit from the program
           dispose();
       }
     }
	
	private int[] searchInCoords(int x) {
        //Search for the coordinates of the passed value
        //used to find the node coordinates for passed mssv
        for(int i=0; i<nodeCoords.length; i++) {
            if(x == nodeCoords[i][2]) {
                int[] temp = {nodeCoords[i][0], nodeCoords[i][1]};
                return temp;
            }
        }
        return new int[2];
    }
		//---------------------------------------------------------------------------------------------------------------------------
	//Tìm phần tử liền trước(predecessor).
//	public boolean predecessor(Node root, int x) {
//		Graphics g = getGraphics();
//        try {
//                   Thread.sleep(500);
//               } catch (InterruptedException ex) {
//                   Logger.getLogger(BST_Graphic.class.getName()).log(Level.SEVERE, null, ex);
//               }
//        Node pre;
//        pre.mssv = x;
//        if (pre == null) { 
//            return false; 
//        }
//		if(pre.leftChild != null) {
//			while(pre.leftChild != null) {
//				pre =  pre.leftChild;
//			}
//		}
//		Node prede = null;
//		while(root != null) {
//			if(pre.mssv == root.mssv) {
//				break;
//			}else if(pre.mssv < root.mssv) {
//				root = root.leftChild;
//			}else if(pre.mssv > root.mssv) {
//				prede = root;
//				root = root.rightChild;
//			}
//		}
//		int[] temp = searchInCoords(prede.mssv);
//        if(temp[0] != 0 && temp[1] != 0) {
//             g.setColor(Color.green);               
//             g.fillOval(temp[0] + 8, temp[1] + 31, 30, 30);
//             g.setColor(Color.black);
//             g.drawString(prede.mssv + "", temp[0] + 20, temp[1] + 50);   
//        }
//        return true; 
//		
//	}
//	//Tìm phần tử liền sau(Successor).
//	public boolean successor(Node root, int x) {
//		Graphics g = getGraphics();
//        try {
//                   Thread.sleep(500);
//               } catch (InterruptedException ex) {
//                   Logger.getLogger(BST_Graphic.class.getName()).log(Level.SEVERE, null, ex);
//               }
//        Node suc;
//        suc.mssv = x;
//        if (suc == null) { 
//            return false; 
//        }
//		if(suc.rightChild != null) {
//			while(suc.rightChild != null) {
//				suc = suc.rightChild;
//			}
//		}
//		Node succ = null;
//		while(root != null) {
//			if(suc.mssv == root.mssv)
//				break;
//			else if(suc.mssv < root.mssv) {
//				succ = root;
//				root = root.leftChild;
//			}
//			else if(suc.mssv > root.mssv)
//				root = root.rightChild;
//		}
//		int[] temp = searchInCoords(succ.mssv);
//        if(temp[0] != 0 && temp[1] != 0) {
//             g.setColor(Color.green);               
//             g.fillOval(temp[0] + 8, temp[1] + 31, 30, 30);
//             g.setColor(Color.black);
//             g.drawString(succ.mssv + "", temp[0] + 20, temp[1] + 50);   
//        }
//        return true; 
//	}
	class Tree extends JPanel {   
	    private int circleRadius = 15;                                                    //node radius
	    private int verticalSeperation = 30;                                                      //Vertical Gap between two nodes in a Binary Tree
	       
	    protected void paintTree() {
	      Graphics g = getGraphics();
	      if(root != null) {                
	            displayTree(g, root, getWidth()/2, 35, getWidth()/4);        
	      }
	    }
	    
	    protected void paintImage() {
	        Graphics g = getGraphics();
	        if(imageNode != null) {              
	            displayTree(g, imageNode, getWidth()/2, 35, getWidth()/4);        
	      }
	    }
	        
	    
	    private void displayTree(Graphics g, Node node, int x, int y, int horizatalSeperation) {
	      //Function to display a subtree with root as x,y        
	      g.setColor(Color.CYAN);
	      g.fillOval(x - circleRadius, y - circleRadius, 2 * circleRadius, 2 * circleRadius);
	      
	      //Store the coordinates of the node, to be used for searching
	      nodeCoords[counter][0] = x - circleRadius;
	      nodeCoords[counter][1] = y - circleRadius;
	      nodeCoords[counter][2] = node.mssv;
	      counter++;
	      
	      //Write the mssv value on the node
	      g.setColor(Color.black);
	      g.drawString(node.mssv + "", x - 6, y + 4);            
	      
	      if (node.leftChild != null) {
	        //using drawLine to draw line to the leftChild node
	          if(!treePainted) {
	          try {              
	              Thread.sleep(500);
	          } catch (InterruptedException ex) {
	              Logger.getLogger(BST_Graphic.class.getName()).log(Level.SEVERE, null, ex);
	            }
	         }
	          
	        //draw line
	        drawLineBetween2Circles(g, x - horizatalSeperation, y + verticalSeperation, x, y);     
	        
	        //recursively draw the leftChild subtree
	        //decrease the horizontal and vertical gaps
	        displayTree(g, node.leftChild, x - horizatalSeperation, y + verticalSeperation, horizatalSeperation/2);        
	      }          
	      if (node.rightChild != null) {
	          //using drawLine to draw line to the rightChild node
	          if(!treePainted) {
	          try {
	          
	              Thread.sleep(500);
	          } catch (InterruptedException ex) {
	              Logger.getLogger(BST_Graphic.class.getName()).log(Level.SEVERE, null, ex);
	            }
	          }
	          
	        //draw line
	        drawLineBetween2Circles(g, x + horizatalSeperation, y + verticalSeperation, x, y);         
	        //recursively draw the rightChild subtree
	        //decrease the horizontal and vertical gaps
	        displayTree(g, node.rightChild, x + horizatalSeperation, y + verticalSeperation, horizatalSeperation/2);          
	      }
	    }        
	    
	    private void drawLineBetween2Circles(Graphics g, int x1, int y1, int x2, int y2) {
	        //Function to draw a line between two circles centered at x1,y1 and x2,y2
	        
	        //Computing adjusted coordinates
	        double d = Math.sqrt(verticalSeperation * verticalSeperation + (x2 - x1) * (x2 - x1));
	        int xAdjusted = (int)(x1 - circleRadius * (x1 - x2) / d);
	        int yAdjusted = (int)(y1 - circleRadius * (y1 - y2) / d);
	        int xAdjusted1 = (int)(x2 + circleRadius * (x1 - x2) / d);
	        int yAdjusted1 = (int)(y2 + circleRadius * (y1 - y2) / d);
	        
	        //Draw line between adjusted coordinates
	        g.drawLine(xAdjusted, yAdjusted, xAdjusted1, yAdjusted1);
	    }    
	  }
	}

