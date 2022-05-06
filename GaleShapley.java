/**  Cette classe utilise l'algorithme de Gale Shapley
 * Cette classe contient 3 methodes. La methode 
 * initialize (File filename) qui lit le fichier et
 * initialise toutes les variables
 * La methode execute() qui execute l'algorithme
 * La methode save(File filename) qui sauvegarde les
 * nouvelles donn�es dans un nouveau fichier
 *  
 *  @author Wilfried SANKARA, Univeristy of Ottawa
 */



import java.io.*;
import java.util.Scanner;
import java.util.Stack;

public class GaleShapley {
	
	/**
	 * Ce tableau stocke le nom de tous les employeurs lus dans le fichier
	 */
	 private static  String [] employerList;
	 
	 
	 /**
	  * Ce tableau stocke le nom de tous les �tudiants lus dans le fichier
	  */
	 private static String [] studentList;
	 
	 
	 /**
	  * Cette matrice contient les differents rangs affect�s par les etudiants aux employeurs
	  * Et vice versa.
	  * Cette matrice contient des couples (i,j) correspondant aux rangs
	  * Par exemple la Pair[e][s]=(i,j):
	  * L'employeur "e" classse l'�tudiant "s" � la position "i"
	  * L'�tudiant  "s" classe l'employeur "e" � la position "j"
	  */
	 private static Pair[][] ranking ; //Matrice contenant le classement des employ�s et des employeurs
	 
	 
	 /**
	  * Size represente le nombre d'�tudiant=nombre d'employeurs
	  */
	 private static int size;
	 
	 
	 /**
	  * Cette pile permettra de selectionner les employeurs
	  */
	 private static Stack sue;
	 
	 
	 /**
	  * Ce tableau est rempli de sorte que students[s]=e
	  * Signifie que l'�tudiant num�ro "s" est affili� � l'employeur numero "e"
	  * Il est initialis� � -1
	  */
	 private static int [] students;
	 
	 /**
	  * Ce tableau est rempli de sorte que employeur[e]=s
	  * Signifie que l'employeur numero "e" est affili� � l'�tudiant "s"
	  */
	 private static int [] employers;
	 
	 
	 /**
	  * Cette matrice contient est impl�ment�e dde sorte que
	  * A[s][e] contient le rang affect� par l'�tudiant "s" � l'employeur "e"
	  */
	 private static int [][] A;
	 
	 
	 /**
	  *Cette liste contient les liste de priorit� de chaque employeur.
	  *Elle est implement�e en utilisant le TAD du monceau
	  *PQ[e]=(employer_Rankind, student);
	  *La cl� est le rang attribu� � l'�tudiant par l'employeur e; 
	  */
	 private static HeapPriorityQueue [] PQ_list;
	
	 
	/**
	 * Cette methode initialise les toutes les varibale
	 * A partir de la lecture du fichier mis en parametre
	 * Elle utilise un bufferedReader
	 * 
	 * @param filename
	 */
	@SuppressWarnings("unchecked")
	public static void initialize(File filename) {
		
		BufferedReader br=null;
		
		 try {
			 
			br= new BufferedReader(new FileReader(filename));
			
			//R�cuperons la taille de la liste des employeurs et des �tudiants
			String size_String= br.readLine();
			size=Integer.parseInt(size_String);
			
			//Recuperons la liste des employeur
			employerList= new String[size];
			int counter=0;
			String line="";
			while(counter<size) {
				line=br.readLine();
				employerList[counter]=line;
				counter++;
			}
			
			//Recuperons la liste des �tudiant;
			studentList= new String[size];
			counter=0;
			while(counter<size) {
				line=br.readLine();
				studentList[counter]=line;
				counter++;
			}
			
			//R�cuperons la matrice des classements des �tudiants et des employeurs
			ranking= new Pair[size][size];
			int counter_row=0;
			while(counter_row<size) {
				line=br.readLine();
				int counter_column=0;
				String [] temp =line.split(" ");
				for(int i=0;i<temp.length;i++) {
					String [] tp=temp[i].split(",");
					Pair pair= new Pair(Integer.parseInt(tp[0]),Integer.parseInt(tp[1]));
					ranking[counter_row][counter_column]=pair;
					counter_column++;
				}
				counter_row++;
			}
			
			
					
		} catch (FileNotFoundException e) {
			System.out.println("Le fichier n'a pas �t� trouv�");
			System.exit(1);
		} catch (IOException e) {
			System.out.println("Impossible de lire le fichier");
			System.exit(1);
		}
		 
		try {
			br.close();
		} catch (IOException e) {
			System.out.println("Le fichier n'a pas pu �tre ferm�");
			System.exit(1);
		}
		catch (NullPointerException e) {
				System.out.println("Impossible de lire le fichier");
				System.exit(1);
		}
		
		//Inistialisation de la pile sue
		sue=new Stack<>();
		for(int i=0; i<size; i++) {
			sue.push(i);
		}
		
		//Initialisation des tableaux students et employers
		students=new int [size];
		employers=new int [size];
		for(int i=0; i<size; i++) {
			students[i]=-1;
			employers[i]=-1;
		}
		
		//Initialisation de la matrice A
		A= new int[size][size];
		for(int i=0; i<size; i++) {
			for(int j=0; j<size; j++) {
				A[i][j]=ranking[j][i].y;
			}
		}
		
		//Initialisation des files � priorit�
		PQ_list= new HeapPriorityQueue [size];
		for(int i=0; i<size; i++) {
			PQ_list[i]= new HeapPriorityQueue();
		}
		for(int s=0; s<size; s++) {
			for(int e=0; e<size; e++) {
				Integer key=ranking[e][s].x;
				Integer value=s;
				PQ_list[e].insert(key, value);
			}
		}
	}
	
	/**
	 * Cette methode execute l'algorithme de GaleShapley en question
	 * En utilisant les varibales initialis�es avec la methode
	 * initialize(filename)
	 * 
	 * @return
	 * Un tableau de type "Pair" contenant toutes les affiliations (employer,student);
	 */
	@SuppressWarnings("unchecked")
	public static Pair [] execute() {
		
		//Cr�ation et initialisation de tableau de type "Pair" de taille "size"
		Pair [] sets= new Pair[size];
		
		while (!sue.isEmpty()) {
			int e= (int) sue.pop();		//Selection de l'employeur
			int s=PQ_list[e].removeMin().y;//L'�tudiant pr�f�r� de l'employeur
			int e_1=students[s];
			
			if(students[s]==-1) {	//Si l'�tudiant est libre
				students[s]=e;
				employers[e]=s;		//Nous les affilions ensemble
			}
			else if(A[s][e]<A[s][e_1]) {	//Si l'�tudiant est d�j� pris par un employeur
				students[s]=e;				// Nous comparons les deux employeurs pour choisir celui que l'�tudiant pr�f�re
				employers[e]=s;
				employers[e_1]=-1;
				sue.push(e_1);			//On remet l'employeur rejet� dans la pile pour lui trouver un autre �tudiant
			}
			else {
				sue.push(e);			// Autrement l'employeur est remis dans la pile pour �tre affili� � un autre �tudiant
			}
			
		}
		
		for(int i=0; i<size; i++) {		//Les affiliations sont rang�es dans un tableau de type Pair
			int s=employers[i];
			int e=students[s];
			Pair set= new Pair (e, s);
			sets[i]=set;
		}
		
		 return sets;
	}
	
	/**
	 * Cette methode enregistre les differents marriages dans un nouveau fichier
	 * 
	 * @param filename
	 */
	public static void  save(File filename) {
		Pair [] sets= execute();
		try (BufferedWriter bw=new BufferedWriter(new FileWriter(filename))){
			for(int i=0; i< size; i++) {
				bw.write("Match "+i+": "+employerList[(sets[i].x)]+" - "+studentList[(sets[i].y)]);
				bw.newLine();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	
	public static void main(String[] args) {
		
		try (Scanner input = new Scanner(System.in)) {
			System.out.println("Entrez le nom de votre ficher: ");
			
			String filename=input.nextLine();
			
			File file= new File(filename);
			
			initialize(file);
		}
		
		try (Scanner input_2 = new Scanner(System.in)) {
			System.out.println("Entrez le nom du fichier que vous voulez cr�er: ");
			
			String filename_2=input_2.nextLine();
			
			File file_2= new File(filename_2);
			
			save(file_2);
		}
	}
	
}
