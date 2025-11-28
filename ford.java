import java.util.Scanner; 

public class ford 
{  
    private int D[];  
    private int num_ver;  
    public static final int MAX_VALUE = 99999; 
    public ford(int num_ver) 
    {  
        this.num_ver = num_ver;  
        D = new int[num_ver + 1]; 
    }  
    
    public void BellmanFordEvaluation(int source, int A[][]) 
    { 
        for (int node = 1; node <= num_ver; node++)  
        {  
            D[node] = MAX_VALUE; 
        } 
        D[source] = 0; 

        for (int iteration = 1; iteration <= num_ver - 1; iteration++)  
        { 
    
            for (int sn = 1; sn <= num_ver; sn++) 
            { 
                for (int dn = 1; dn <= num_ver; dn++) 
                { 
                    if (A[sn][dn] != MAX_VALUE)  
                    {  
                        if (D[sn] != MAX_VALUE && D[dn] > D[sn] + A[sn][dn])  
                        {
                            D[dn] = D[sn] + A[sn][dn]; 
                        }
                    }  
                }  
            } 
        }  
        boolean hasNegativeCycle = false;
        for (int sn = 1; sn <= num_ver; sn++) 
        { 
            for (int dn = 1; dn <= num_ver; dn++)  
            {  
                if (A[sn][dn] != MAX_VALUE) 
                { 
                    if (D[sn] != MAX_VALUE && D[dn] > D[sn] + A[sn][dn]) 
                    {
                        System.out.println("\n*** The Graph contains a negative edge cycle! ***");
                        hasNegativeCycle = true;
                        break; 
                    }
                }  
            }
            if (hasNegativeCycle) break; 
        }
        if (!hasNegativeCycle) {
            System.out.println("\n--- Shortest Path Distances (Bellman-Ford) ---");
            for (int vertex = 1; vertex <= num_ver; vertex++) 
            {  
                if (D[vertex] == MAX_VALUE) {
                    System.out.println("Distance from source " + source + " to vertex " + vertex + " is: INFINITY");
                } else {
                    System.out.println("Distance from source " + source + " to vertex " + vertex + " is: " + D[vertex]);
                }
            }  
        }
    } 
    
    public static void main(String[] args) 
    { 
        int num_ver = 0;  
        int source; 
        Scanner scanner = new Scanner(System.in); 
        
        System.out.print("Enter the number of vertices: ");  
        num_ver = scanner.nextInt();  
        int A[][] = new int[num_ver + 1][num_ver + 1];  
        
        System.out.println("Enter the adjacency matrix (use 0 for no edge/infinity):");  
        for (int sn = 1; sn <= num_ver; sn++)  
        {  
            for (int dn = 1; dn <= num_ver; dn++)  
            { 
                A[sn][dn] = scanner.nextInt();  
                if (A[sn][dn] == 0 && sn != dn) 
                { 
                    A[sn][dn] = MAX_VALUE;
                }
            }  
        }  

        System.out.print("Enter the source vertex (1 to " + num_ver + "): "); 
        source = scanner.nextInt(); 

        ford b = new ford (num_ver); 
        b.BellmanFordEvaluation(source, A); 
        
        scanner.close(); 
    } 
}