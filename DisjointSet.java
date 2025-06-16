import java.util.Arrays;

public class DisjointSet {
    //int[] arr = {-6,1,1,3,1,4,-2,7};
    public static int findUltPar(int[] par,int n){
         if (par[n] < 0) return n;
        return par[n] = findUltPar(par, par[n]); // Path compression
        //Returns index of the parent.
    }

    static class DisjointSetDS{
        int[] par;
        int[] size;

         private int findUltPar(int n){
            if(par[n] < 0) return n;
            return par[n] = findUltPar(par[n]);
         }
        
        //In this question, we'll use this to detect whether we should include a particular edge
        //in the DSU of alice and bob or not, if cycle will form then not, otherwise yes, as we're
        //trying to get the spanning tree for both, we don't have to include extra edges.
         public boolean Union(int a, int b){
            int uParA = findUltPar(a);
            int uParB = findUltPar(b);
   
            //If parent of both are the same, means cycle exists.
            if(uParA == uParB){
                return false; //Don't include.
            }
            else{
                if(size[uParA] > size[uParB]){
                    par[uParB] = uParA;
                    size[uParA] += size[uParB];
                }
                else{
                    par[uParA] = uParB;
                    size[uParB] += size[uParA];
                }
                return true;
            }

            
         }

         public DisjointSetDS(int[] par,int[] size){
            this.par = par;
            this.size = size;
         }

    }

    public static int maxNumEdgesToRemove(int n, int[][] edges) {
      //We'll create our arrays of n+1, as here the node's values start from 1 and go till n
      //so we'll keep the first index empty and everything else is same like before.
        
        int[] par1 = new int[n+1];
        Arrays.fill(par1,-1);
        int[] par2 = new int[n+1];
        Arrays.fill(par2,-1);
        int[] size1 = new int[n+1];
        Arrays.fill(size1,1);
        int[] size2 = new int[n+1];
        Arrays.fill(size2,1);
       
       //Create two disjoint sets for both Alice and Bob.
        DisjointSetDS dsu_bob = new DisjointSetDS(par1,size1);
        DisjointSetDS dsu_alice = new DisjointSetDS(par2,size2);
        
        int bob_edges = 0;
        int alice_edges = 0;
        int removed_edges = 0;

        //Loop over all the edges and create our disjoint sets of alice & bob.
        for(int i=0;i<edges.length;i++){
            int[] edge = edges[i];

            int edgeType = edge[0]; //1 for Alice, 2 for Bob, 3 for both.
            
            //Alice only.
            if(edgeType == 1){
                //Check if we should add the current edge to dsu of alice or not.
                if(dsu_alice.Union(edge[1],edge[2])){
                    alice_edges++;
                }
                else{
                    removed_edges++;
                }
            }

            //Bob only.
            if(edgeType == 2){
                 if(dsu_bob.Union(edge[1],edge[2])){
                     bob_edges++;
                 }
                 else{
                    removed_edges++;
                 }
            }

            //Both of them.Means include for both of them.
            if(edgeType == 3){
                alice_edges++;
                bob_edges++;
            }
        }
        
         System.out.println(bob_edges + "," + alice_edges);
        
        //If no. of edges of bob and alice are same & equal to n-1, as that's the minimun no. of 
        //edges that are required to connect n nodes together.
        if(bob_edges == n-1 && alice_edges == n-1){
            return removed_edges;
        }
        return -1;
    }
    //Detecting cycle using Disjoint set data structure.
    //Understand the procedure from the notebook and you'll understand it very easily.
    public static void detectCycle(int[][] graph,int[] par,int[] size){
        for(int i=0;i<graph.length;i++){
            int[] curr = graph[i];
            int u = curr[0];
            int v = curr[1];
            int uParU = findUltPar(par, u);
            int uParV = findUltPar(par, v);
            
            //If both have same ultimate parents.
            if(uParU == uParV){
                System.out.println("Cycle exists in graph!");
                System.out.println("Because of the edge " + u + "->" + v);
                return;
            }
            //Do the union, if not.
            else{
                if(size[uParU] > size[uParV]){
                    par[uParV] = uParU;
                    size[uParU] += size[uParV];
                }
                else{
                    //Update the parents and increment the size.
                    par[uParU] = uParV;
                    size[uParV] += size[uParU];
                }
            }
             
        }
    }
    public static void main(String[] args) {
        System.out.println("Hello everyone!");

        int[][] graph = {{0,1},{0,2},{2,3},{1,3},{1,4}};

        int[] par = new int[5];
        Arrays.fill(par,-1);

        int[] size = new int[5];
        Arrays.fill(size,1);

          int[][] edges = {
            {3, 1, 2},
                {3, 2, 3},
            {1, 1, 3},
        {1, 2, 4},
            {1, 1, 2},
            {2, 3, 4}
       };


        System.out.println(maxNumEdgesToRemove(4, edges));
    }
}
