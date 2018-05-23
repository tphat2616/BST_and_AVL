package BST;
import java.util.*;

public class BST {
	Node root; //khỡi tạo nút root.
	Node parent;      //Khỡi tạo nút cha.
	//-----------------------------------------------------------------------------------------------------------------
	//Dựng cây rỗng
	//dựng cây với thông tin ngẫu nhiên
	//Hàm dựng cây BST lệch trái.
	//-----------------------------------------------------------------------------------------------------------------
	//duyệt cây theo left node right.
	public void LNR(Node focusNodeNode) {
		if(focusNodeNode != null) {
			LNR(focusNodeNode.leftChild);
			System.out.println(focusNodeNode);
			LNR(focusNodeNode.rightChild);
		}
	}
	//duyệt cây theo left right node.
	public void LRN(Node focusNodeNode) {
		if(focusNodeNode != null) {
			LRN(focusNodeNode.leftChild);
			LRN(focusNodeNode.rightChild);
			System.out.println(focusNodeNode);
		}
	}
	//duyệt theo node left right.
	public void NLR(Node focusNodeNode) {
		if(focusNodeNode != null) {
			System.out.println(focusNodeNode);
			NLR(focusNodeNode.leftChild);
			NLR(focusNodeNode.rightChild);
		}
	}
	//duyệt theo right node left.
	public void RNL(Node focusNodeNode) {
		if(focusNodeNode != null) {
			RNL(focusNodeNode.rightChild);
			System.out.println(focusNodeNode);
			RNL(focusNodeNode.leftChild);		
		}
	}
	//duyệt theo node right left.
	public void NRL(Node focusNodeNode) {
		if(focusNodeNode != null) {
			System.out.println(focusNodeNode);
			NRL(focusNodeNode.rightChild);
			NRL(focusNodeNode.leftChild);		
		}
	}
	//duyệt theo right left node.
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
	public void addNode(int mssv, String hoTen, int ngaySinh, double diemTBTL, int stc) {
		Node New = new Node(mssv, hoTen, ngaySinh, diemTBTL, stc);
		if (root == null) {
			//System.out.println("Cây đang rỗng hiện đang load dữ liệu.\n");
			root = New;
		}else {
			Node focusNodeNode = root; 
			while(true) {
				parent = focusNodeNode;
				if(mssv < focusNodeNode.mssv) {
					focusNodeNode = focusNodeNode.leftChild;
					if(focusNodeNode == null) {
						parent.leftChild = New;
						return;
					}
				}else {
					focusNodeNode = focusNodeNode.rightChild;
					if(focusNodeNode == null) {
						parent.rightChild = New;
						return;
					}
				}
			}
		}
	}
	//-----------------------------------------------------------------------------------------------------------------
	//xoá từng node
	public boolean Xoa(int mssv) {
		Node focusNodeXoa = root;
		Node parent = root;
		
		boolean isItALeftChild = true;
		
		while(focusNodeXoa.mssv != mssv) {
			parent = focusNodeXoa;
			
			if(mssv < focusNodeXoa.mssv) {
				isItALeftChild = true;
				focusNodeXoa = focusNodeXoa.leftChild;
			} else {
				isItALeftChild = false;
				focusNodeXoa = focusNodeXoa.rightChild;
			}
			if (focusNodeXoa == null) {
				return false;
			}
		}
		if(focusNodeXoa.leftChild == null && focusNodeXoa.rightChild == null) {
			if(focusNodeXoa == root) {
				root = null;
			} else if (isItALeftChild) {
				parent.leftChild = null;
			} else {
				parent.rightChild = null;
			}
		} else if(focusNodeXoa.rightChild == null) {
			if(focusNodeXoa == root) {
				root = focusNodeXoa.leftChild;
			} else if(isItALeftChild) {
				parent.leftChild = focusNodeXoa.leftChild;
			} else {
				parent.rightChild = focusNodeXoa.rightChild;
			}
		} else if(focusNodeXoa.leftChild == null) {
			if(focusNodeXoa == root)
				root = focusNodeXoa.rightChild;
			else if(isItALeftChild)
				parent.leftChild = focusNodeXoa.rightChild;
			else
				parent.rightChild = focusNodeXoa.rightChild;
		} else {
			Node replacement = getReplacementNode(focusNodeXoa);
			
			if(focusNodeXoa == root) {
				root = replacement;
			} else if(isItALeftChild) {
				parent.leftChild = replacement;
			} else {
				parent.rightChild = replacement;
			}
			replacement.leftChild = focusNodeXoa.leftChild;
		}
		return true;	
	}
	public Node getReplacementNode(Node replacedNode) {
		Node replacementParent = replacedNode;
		Node replacement = replacedNode;
		
		Node focusNodeXoa = replacedNode.rightChild;
		
		while(focusNodeXoa != null) {
			replacementParent = replacement;
			replacement = focusNodeXoa;
			
			focusNodeXoa = focusNodeXoa.leftChild;
		}
		
		if(replacement != replacedNode.rightChild) {
			replacementParent.leftChild = replacement.rightChild;
			replacementParent.rightChild = replacement.rightChild;
		}
		return replacement;
	}
	//-----------------------------------------------------------------------------------------------------------------
	//Tìm phần tử liền trước(predecessor).
	public Node predecessor(Node root, Node pre) {
		if(pre.leftChild != null) {
			while(pre.leftChild != null) {
				return pre.leftChild;
			}
			return pre;
		}
		Node prede = null;
		while(root != null) {
			if(pre.mssv == root.mssv) {
				break;
			}else if(pre.mssv < root.mssv) {
				root = root.leftChild;
			}else if(pre.mssv > root.mssv) {
				prede = root;
				root = root.rightChild;
			}
		}
		return prede;
		
	}
	//Tìm phần tử liền sau(Successor).
	public Node successor(Node root, Node suc) {
		if(suc.rightChild != null) {
			while(suc.rightChild != null) {
				return suc.rightChild;
			}
			return suc;
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
		System.out.println("Nhập tín chỉ: ");
		focus.stc = Integer.parseInt(sc.nextLine());
		RLN(root);
	}
	}
	public static void main(String[] args) {
		BST bst = new BST();
		bst.addNode(7, "Nguyen van A", 1997, 9.0, 3);
		bst.addNode(15, "Nguyen van A", 1997, 9.0, 3);
		bst.addNode(23, "Nguyen van A", 1998, 6.5, 3);
		bst.addNode(73, "Nguyen van B", 1997, 9.0, 3);
		bst.addNode(4, "Nguyen van C", 1998, 6.5, 3);
		bst.addNode(5, "Nguyen van D", 1998, 7.2, 4);
		bst.addNode(6, "Nguyen van E", 1998, 7.2, 4);
		bst.addNode(50, "Nguyen van A", 1998, 7.2, 4);
		bst.addNode(70, "Nguyen van G", 1998, 7.2, 4);
		bst.addNode(51, "Nguyen van H", 1998, 7.2, 4);
		
//		bst.Xoa(70);
//		bst.RLN(bst.root);
//		System.out.println(bst.findMSSV(3105));
//		bst.findNS(bst.root,1998);
//		bst.findTen(bst.root,"Nguyen Van A");
//		bst.findDTB(bst.root,7.2);
//		bst.findTC(bst.root,4);
//		System.out.println(bst.Max(bst.root));
//		System.out.println(bst.Min(bst.root));
//		System.out.println(bst.findNgaySinh(1998));
//		System.out.println(bst.predecessor(5));
//		System.out.println(bst.predecessor(bst.root, bst.findMSSV(15)));
//		System.out.println(bst.successor(bst.root, bst.findMSSV(7)));
		bst.Sua();
		
	}
}
class Node{
	int mssv;
	String hoTen;
	int ngaySinh;
	double diemTBTL;
	int stc;
	
	Node leftChild;
	Node rightChild;
	Node Parent;
	
	Node(int mssv, String hoTen, int ngaySinh, double diemTBTL, int stc){
		this.mssv = mssv;
		this.hoTen = hoTen;
		this.ngaySinh = ngaySinh;
		this.diemTBTL = diemTBTL;
		this.stc = stc;
		
	}
	public String toString() {
		return "Họ và tên: "+hoTen+"\nMã số sinh viên: "+mssv+"\nNgày sinh: "+ngaySinh+"\nĐiểm trung bình tích lũy: "+diemTBTL+"\nSố tính chỉ: "+stc+"\n------------------------";
	}
}
