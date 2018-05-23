package AVL;

import java.util.Scanner;

public class AVL {
	 
    private Node root;
    
    //-----------------------------------------------------------------------------------------------------------------
  	//duyệt cây theo leftChild node rightChild.
  	public void LNR(Node focusNodeNode) {
  		if(focusNodeNode != null) {
  			LNR(focusNodeNode.leftChild);
  			System.out.println(focusNodeNode);
  			LNR(focusNodeNode.rightChild);
  		}
  	}
  	//duyệt cây theo leftChild rightChild node.
  	public void LRN(Node focusNodeNode) {
  		if(focusNodeNode != null) {
  			LRN(focusNodeNode.leftChild);
  			LRN(focusNodeNode.rightChild);
  			System.out.println(focusNodeNode);
  		}
  	}
  	//duyệt theo node leftChild rightChild.
  	public void NLR(Node focusNodeNode) {
  		if(focusNodeNode != null) {
  			System.out.println(focusNodeNode);
  			NLR(focusNodeNode.leftChild);
  			NLR(focusNodeNode.rightChild);
  		}
  	}
  	//duyệt theo rightChild node leftChild.
  	public void RNL(Node focusNodeNode) {
  		if(focusNodeNode != null) {
  			RNL(focusNodeNode.rightChild);
  			System.out.println(focusNodeNode);
  			RNL(focusNodeNode.leftChild);		
  		}
  	}
  	//duyệt theo node rightChild leftChild.
  	public void NRL(Node focusNodeNode) {
  		if(focusNodeNode != null) {
  			System.out.println(focusNodeNode);
  			NRL(focusNodeNode.rightChild);
  			NRL(focusNodeNode.leftChild);		
  		}
  	}
  	//duyệt theo rightChild leftChild node.
  	public void RLN(Node focusNodeNode) {
  		if(focusNodeNode != null) {
  			RLN(focusNodeNode.rightChild);
  			RLN(focusNodeNode.leftChild);
  			System.out.println(focusNodeNode);
  		}
  	}
	//-----------------------------------------------------------------------------------------------------------------
  	//Tìm kiếm theo mã sinh viên.
  	public Node findMSSV(int mssv) {
  		Node focusNode = root;
  		while(focusNode.mssv != mssv) {
  			if(mssv < focusNode.mssv) {
  				focusNode = focusNode.leftChild;
  			}else{
  				focusNode = focusNode.rightChild;
  			}
  			if(focusNode == null) {
  				return null;
  			}
  		}
  		return focusNode;
  	}
  	//tìm kiếm sinh viên theo tên.
  	public void findTen(Node focusNode,String ten) {
  		String t = ten;
  		if(focusNode != null) {
  			if(ten.equalsIgnoreCase(focusNode.hoTen) == true) {
  				System.out.println(focusNode);
  			}
  			findTen(focusNode.leftChild,t);
  			findTen(focusNode.rightChild,t);
  		}
  	}
  	//tìm kiếm theo ngày sinh
  	public void findNS(Node focusNode,int ngaySinh) {
  		int ns = ngaySinh;
  		if(focusNode != null) {
  			if(focusNode.ngaySinh == ns) {
  				System.out.println(focusNode);
  			}
  			findNS(focusNode.rightChild,ns);
  			findNS(focusNode.leftChild,ns);		
  		}
  	}
  	//tìm kiếm theo điểm trung bình.
  	public void findDTB(Node focusNode,double diemTBTL) {
  		double dtb = diemTBTL;
  		if(focusNode != null) {
  			if(focusNode.diemTBTL == dtb) {
  				System.out.println(focusNode);
  			}
  			findDTB(focusNode.rightChild,dtb);
  			findDTB(focusNode.leftChild,dtb);		
  		}
  	}
  	//tìm kiếm theo tín chỉ.
  	public void findTC(Node focusNode,int sotinchi) {
  		int tc = sotinchi;
  		if(focusNode != null) {
  			if(focusNode.stc == tc) {
  				System.out.println(focusNode);
  			}
  			findTC(focusNode.rightChild,tc);
  			findTC(focusNode.leftChild,tc);		
  		}
  	}
  	//tìm kiếm phần tử lớn nhất.
  	public Node Max(Node a) {
  		Node focusNode = a;
  		Node max;
  		max = focusNode;
  		while(max.rightChild != null) {
  			return Max(max.rightChild);
  		}
  		return max;
  	}
  	//tìm phần tử nhỏ nhất.
  	public Node Min(Node a) {
  		Node focusNode = a;
  		Node min;
  		min = focusNode;
  		while(min.leftChild != null) {
  			return Min(min.leftChild);
  		}
  		return min;
  	}
  	//-----------------------------------------------------------------------------------------------------------------
    public boolean addNode(int mssv, String hoTen,int ngaySinh, double diemTBTL, int stc) {
        if (root == null) {
            root = new Node(mssv, hoTen, ngaySinh, diemTBTL, stc, null);
            return true;
        }
 
        Node n = root;
        while (true) {
            if (n.mssv == mssv)
                return false;
 
            Node parent = n;
 
            boolean goleftChild = n.mssv > mssv;
            n = goleftChild ? n.leftChild : n.rightChild;
 
            if (n == null) {
                if (goleftChild) {
                    parent.leftChild = new Node(mssv, hoTen, ngaySinh, diemTBTL, stc, parent);
                } else {
                    parent.rightChild = new Node(mssv, hoTen, ngaySinh, diemTBTL, stc, parent);
                }
                rebalance(parent);
                break;
            }
        }
        return true;
    }
  //-----------------------------------------------------------------------------------------------------------------
    private void delete(Node node) {
        if (node.leftChild == null && node.rightChild == null) {
            if (node.parent == null) {
                root = null;
            } else {
                Node parent = node.parent;
                if (parent.leftChild == node) {
                    parent.leftChild = null;
                } else {
                    parent.rightChild = null;
                }
                rebalance(parent);
            }
            return;
        }
 
        if (node.leftChild != null) {
            Node child = node.leftChild;
            while (child.rightChild != null) child = child.rightChild;
            node.mssv = child.mssv;
            delete(child);
        } else {
            Node child = node.rightChild;
            while (child.leftChild != null) child = child.leftChild;
            node.mssv = child.mssv;
            delete(child);
        }
    }
 
    public void delete(int delmssv) {
        if (root == null)
            return;
 
        Node child = root;
        while (child != null) {
            Node node = child;
            child = delmssv >= node.mssv ? node.rightChild : node.leftChild;
            if (delmssv == node.mssv) {
                delete(node);
                return;
            }
        }
    }
 
    private void rebalance(Node n) {
        setBalance(n);
 
        if (n.balance == -2) {
            if (height(n.leftChild.leftChild) >= height(n.leftChild.rightChild))
                n = rotaterightChild(n);
            else
                n = rotateleftChildThenrightChild(n);
 
        } else if (n.balance == 2) {
            if (height(n.rightChild.rightChild) >= height(n.rightChild.leftChild))
                n = rotateleftChild(n);
            else
                n = rotaterightChildThenleftChild(n);
        }
 
        if (n.parent != null) {
            rebalance(n.parent);
        } else {
            root = n;
        }
    }
 
    private Node rotateleftChild(Node a) {
 
        Node b = a.rightChild;
        b.parent = a.parent;
 
        a.rightChild = b.leftChild;
 
        if (a.rightChild != null)
            a.rightChild.parent = a;
 
        b.leftChild = a;
        a.parent = b;
 
        if (b.parent != null) {
            if (b.parent.rightChild == a) {
                b.parent.rightChild = b;
            } else {
                b.parent.leftChild = b;
            }
        }
 
        setBalance(a, b);
 
        return b;
    }
 
    private Node rotaterightChild(Node a) {
 
        Node b = a.leftChild;
        b.parent = a.parent;
 
        a.leftChild = b.rightChild;
 
        if (a.leftChild != null)
            a.leftChild.parent = a;
 
        b.rightChild = a;
        a.parent = b;
 
        if (b.parent != null) {
            if (b.parent.rightChild == a) {
                b.parent.rightChild = b;
            } else {
                b.parent.leftChild = b;
            }
        }
 
        setBalance(a, b);
 
        return b;
    }
 
    private Node rotateleftChildThenrightChild(Node n) {
        n.leftChild = rotateleftChild(n.leftChild);
        return rotaterightChild(n);
    }
 
    private Node rotaterightChildThenleftChild(Node n) {
        n.rightChild = rotaterightChild(n.rightChild);
        return rotateleftChild(n);
    }
 
    private int height(Node n) {
        if (n == null)
            return -1;
        return n.height;
    }
 
    private void setBalance(Node... nodes) {
        for (Node n : nodes) {
            reheight(n);
            n.balance = height(n.rightChild) - height(n.leftChild);
        }
    }
 
    public void printBalance() {
        printBalance(root);
    }
 
    private void printBalance(Node n) {
        if (n != null) {
            printBalance(n.leftChild);
            System.out.printf("%s ", n.balance);
            printBalance(n.rightChild);
        }
    }
 
    private void reheight(Node node) {
        if (node != null) {
            node.height = 1 + Math.max(height(node.leftChild), height(node.rightChild));
        }
    }
  //-----------------------------------------------------------------------------------------------------------------
  	//Tìm phần tử liền trước(predecessor).
  	public Node predecessor(Node root, Node pre) {
  		if(pre.leftChild != null) {
  			while(pre.leftChild != null) {
  				Node focusNode = pre.leftChild;
  		  		Node max;
  		  		max = focusNode;
  		  		while(max.rightChild != null) {
  		  			return Max(max.rightChild);
  		  		}
  		  		return max;
  	  		}
  		}
  		Node prede = pre.parent;
  		while(prede != null && pre == prede.leftChild) {
  			pre = prede;
  			prede = pre.parent;
  		}
  		if (prede == null) {
  		}else {
  			return prede;
  		}
  		return prede;
  	}
  	//Tìm phần tử liền sau(Successor).
  	public Node successor(Node root, Node suc) {
  		if(suc.rightChild != null) {
  			while(suc.rightChild != null) {
  				Node focusNode = suc.rightChild;
  		  		Node min;
  		  		min = focusNode;
  		  		while(min.leftChild != null) {
  		  			return Min(min.leftChild);
  		  		}
  		  		return min;
  			}
  		}
  		Node succ = null;
  		while(root != null) {
  			if(suc.mssv == root.mssv)
  				break;
  			else if(suc.mssv < root.mssv) {
  				succ = root;
  				root = root.leftChild;
  			}
  			else if(suc.mssv > root.mssv)
  				root = root.rightChild;
  		}
  		return succ;
  	}
  	//-----------------------------------------------------------------------------------------------------------------
  //Nhập Node
  	int n;
  	public void Sua() {
  	Scanner sc = new Scanner(System.in);
  	try {
  	System.out.print("Nhập mã số sinh viên: ");
  	n = Integer.parseInt(sc.nextLine());
  	}catch(NumberFormatException e){
  		System.out.println("Sai định dạng!!!Mời nhập dạng số nguyên dương.");
  	}
  	Node focus = root;
  	if(findMSSV(n) == null) {
  		System.out.println("Not looking for this id!!!");
  	}else {
  		focus.mssv = n;
  		System.out.print("Nhập tên: ");
  		focus.hoTen = sc.nextLine();
  		System.out.print("Nhập ngày sinh: ");
  		focus.ngaySinh = Integer.parseInt(sc.nextLine());
  		System.out.print("Nhập điểm trung bình: ");
  		focus.diemTBTL = Double.parseDouble(sc.nextLine());
  		System.out.print("Nhập tín chỉ: ");
  		focus.stc = Integer.parseInt(sc.nextLine());
  		RLN(root);
  	}
  	}
 	//-----------------------------------------------------------------------------------------------------------------
    public static void main(String[] args) {
        AVL avl = new AVL();
        avl.addNode(7, "Nguyen van A", 1997, 9.0, 3);
        avl.addNode(15, "Nguyen van A", 1997, 9.0, 3);
        avl.addNode(23, "Nguyen van A", 1998, 6.5, 3);
        avl.addNode(73, "Nguyen van B", 1997, 9.0, 3);
        avl.addNode(4, "Nguyen van C", 1998, 6.5, 3);
        avl.addNode(5, "Nguyen van D", 1998, 7.2, 4);
        avl.addNode(6, "Nguyen van E", 1998, 7.2, 4);
        avl.addNode(50, "Nguyen van A", 1998, 7.2, 4);
        avl.addNode(70, "Nguyen van G", 1998, 7.2, 4);
        avl.addNode(51, "Nguyen van H", 1998, 7.2, 4);
        //avl.LRN(avl.root);
        //System.out.println(avl.findMSSV(6));
        //avl.findNS(avl.root,1998);
        //avl.findTen(avl.root,"Nguyen Van A");
        //avl.findDTB(avl.root,7.2);
        //avl.findTC(avl.root,4);
        //System.out.println(avl.Min(avl.root));
        //System.out.println(avl.Max(avl.root));
        //avl.delete(51);
        //System.out.println(avl.predecessor(avl.root, avl.findMSSV(73)));
        //System.out.println(avl.successor(avl.root, avl.findMSSV(4)));
        avl.Sua();
    }
    
    private static class Node {
        private int mssv;
        private int balance;
        private int height;
        String hoTen;
    	int ngaySinh;
    	double diemTBTL;
    	int stc;
        
        private Node leftChild;
        private Node rightChild;
        private Node parent;
 
        Node(int mssv, String hoTen, int ngaySinh, double diemTBTL, int stc, Node parent) {
        	this.mssv = mssv;
    		this.hoTen = hoTen;
    		this.ngaySinh = ngaySinh;
    		this.diemTBTL = diemTBTL;
    		this.stc = stc;
            this.parent = parent;
        }
        
        public String toString() {
        	return "Họ và tên: "+hoTen+"\nMã số sinh viên: "+mssv+"\nNgày sinh: "+ngaySinh+"\nĐiểm trung bình tích lũy: "+diemTBTL+"\nSố tính chỉ: "+stc+"\n------------------------";
    	}
    }
    
}