import java.util.ArrayList;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;
import java.util.HashSet;

public class GraphDS {
    //Create edge class to create edge instances.
    static class Edge{
         int src;
         int dest;
         int weight;

         public Edge(int s,int d,int wt){
             this.src = s;
             this.dest = d;
             this.weight = wt;
         }
    }

    public static class Pair implements Comparable<Pair>  {
        int node;
        int cost; //Weight.

        public Pair(int node, int cost){
            this.node = node;
            this.cost = cost;
        }
        
        //Through this, our priority queue will know to which value we've to prioritize the pari, as the pair
        //contains both node and dist.
        @Override
        public int compareTo(Pair p2){
            return this.cost - p2.cost; //Ascending.
            //p2.dist - this.dist; //Descending. 
            //Explore this comparable interface yourself further.
        }
    }

    public static void createGraph(ArrayList<Edge>[] graph){
         //First of all, store arrayList at all places of the array, so that we can start storing the values
         //at those places.

         for(int i=0;i<graph.length;i++){
             graph[i] = new ArrayList<>();
         }

         graph[1].add(new Edge(1, 2, 3));
         graph[1].add(new Edge(1, 4, 7));
         graph[2].add(new Edge(2, 1, 8));
         graph[2].add(new Edge(2, 3, 2));
         graph[3].add(new Edge(3, 1, 5));
         graph[3].add(new Edge(3, 4, 1));
         graph[4].add(new Edge(4, 1, 2));
    }

    //Graph Traversals.

    public static void breadthFirstSearch(ArrayList<Edge>[] graph,boolean[] vis,int start){
        Queue<Integer> q = new LinkedList<Integer>();
        q.add(start);

         while(!q.isEmpty()){
            int curr = q.remove();

             if(vis[curr] == false ){
                //Print the current node.
                System.out.println(curr);
            
                //Mark visited as true in the visited array.
                vis[curr] = true;
   
                //Add the curr node/ vertex neighbours in the queue.
                for(int i=0;i<graph[curr].size();i++){
                    Edge e = graph[curr].get(i);
                    q.add(e.dest);
                }
             }
         }
    }

    public static void depthFirstSearch(ArrayList<Edge>[] graph, boolean[] vis, int curr){
          if(vis[curr] == true){
            return;
          }
          
          //Print the current vertex.
             System.out.print(curr);
             //Set visited to true.
             vis[curr] = true;
             //Call for all its neighbours.
             for(int i=0;i<graph[curr].size();i++){
                Edge e = graph[curr].get(i);
                depthFirstSearch(graph, vis, e.dest);
             }
    }

    public static void allPaths(ArrayList<Edge>[] graph,boolean[] vis ,String path,int curr,int tar){
        if(curr == tar){
             System.out.println(path);
             return;
        }

        for(int i=0;i<graph[curr].size();i++){
             Edge e = graph[curr].get(i);

             if(vis[e.dest] == false ){
                vis[curr] = true;
                allPaths(graph, vis, path+e.dest, e.dest, tar);
                 //Unvisit the vertex while returning as there can be multiple paths to the targer, if we just
                 //permanently set vis to true, then we won't be able to travel all paths.
                 vis[curr] = false;
             }
        }
    }


    public static boolean detectCycleDirected(ArrayList<Edge>[] graph,boolean[] vis,boolean[] rec,int start){
           int curr = start;

           vis[curr] = true;
           rec[curr] = true;

           //Loop over neighbours of curr node.
           for(int i=0;i<graph[curr].size();i++){
               Edge e = graph[curr].get(i);
               //If any neighbour already exists in the recStack arrary, then return true.
               if(rec[e.dest] == true){
                 return true;
               }
               //If any neighbour is unvisited, visit it.
               else if(!vis[e.dest]){
                //If cycle exists in any neighbour, then cycle also exists in the whole graph, so return true.
                 if( detectCycleDirected(graph, vis, rec, e.dest)){
                    return true;
                 }
                 
               }
           }
            //Remove it from the recStack on the way back.
           rec[curr] = false;

           return false;
    }

    public static void topologicalSort(ArrayList<Edge>[] graph,boolean[] vis,Stack<Integer> s,int start){
         int curr = start;
        
         vis[curr] = true;
         //Loop over all neighbours of current node.
         for(int i=0;i<graph[curr].size();i++){
             Edge e = graph[curr].get(i);
             //visit the node that's not visited yet.
             if(!vis[e.dest]){
                 topologicalSort(graph, vis,s, e.dest);
             }
         }
         //Push the current node to the stack after topological sorting is done for all the neighnours.
         s.push(curr);

    }
    
    public static boolean detectCycleUndirected(ArrayList<Edge>[] graph,boolean[] vis,int par,int curr){
         vis[curr] = true;

         //Loop over all neighbours of current node.
         for(int i=0;i<graph[curr].size();i++){
             Edge e = graph[curr].get(i);
             
            //If neighbour is visited,but not our parent.
             if(vis[e.dest] == true && par != e.dest){
                 return true;
             }
             //Visit the neighbour, if unvisited.
             if(!vis[e.dest]){
                 if(detectCycleUndirected(graph,vis,curr,e.dest)){
                    return true;
                 }
             }
         }

         return false;
    }
    //Shortest path alogorithms.

    public static void dijkstraAlgo(ArrayList<Edge>[] graph,int src,int V){
         //Create your priority queue.
         PriorityQueue<Pair> pq = new PriorityQueue<>();
         
         //Create distance array.
         int[] dist = new int[V];

         for(int i=0;i<V;i++){
             if(i != src){
                dist[i] = Integer.MAX_VALUE;
             }
             else{
                dist[i] = 0;
             }
         }

         //create your visited array.
         boolean[] vis = new boolean[V];

         pq.add(new Pair(0, 0));

         while(!pq.isEmpty()){ //O(V)
            //Pull out a unvisited node that has shortest distacne.
            Pair curr = pq.remove();

           //If node is unvisited.
           if(!vis[curr.node]){
               vis[curr.node] = true;

               //Loop over all the neighbours.
               for(int i=0;i<graph[curr.node].size();i++){
                  Edge e = graph[curr.node].get(i);

                  int u = e.src;
                  int v = e.dest;
                  int wt = e.weight;

                  //Apply relaxation, to get the shortest distance.
                  if(dist[u] + wt < dist[v]){
                      dist[v] = dist[u] + wt;
                  }

                  //Also, add the neighbours to the priority queue.
                  pq.add(new Pair(v, dist[v])); //log(n)
               }
           }

          
         }
         
         //Print the shortest distances of all the vertices from the source.
         for(int i=0;i<V;i++){
            System.out.print(dist[i] + " ");
         }
    }

    public static void bellManFord(ArrayList<Edge>[] graph, int src,int V, int[] dist){
         for(int k=0;k<V-1;k++){
            //Loop over all edges of the graph.
            
            for(int i=0;i<V;i++){
                //Find neighbours of all vertices.
                for(int j=0;j<graph[i].size();j++){
                    Edge e = graph[i].get(j);

                    int u = e.src;
                    int v = e.dest;
                    
                    //If dist[u] is infinity then the condition won't work as expected, as dist[u] will always
                    //be greater.
                    if(dist[u] != Integer.MAX_VALUE && dist[u] + e.weight < dist[v]){
                         dist[v] = dist[u] + e.weight;
                    }
                }
            }
         }

         //Detect -ve weight cycles.
         for(int i=0;i<V;i++){
                //Find neighbours of all vertices.
                for(int j=0;j<graph[i].size();j++){
                    Edge e = graph[i].get(j);

                    int u = e.src;
                    int v = e.dest;
                    
                    //If dist[u] is infinity then the condition won't work as expected, as dist[u] will always
                    //be greater.
                    if(dist[u] != Integer.MAX_VALUE && dist[u] + e.weight < dist[v]){
                        System.out.println("-ve weight cycle exists.");
                    }
                }
            }

         //Print the distances.
         for(int i=0;i<dist.length;i++){
            System.out.print(dist[i] + " ");
         }
    }

    //Prim's algorithm.
    //T.C. = ElogE as in worst case, all the edges would be added to the PQ & to sort one edge is logE.
    public static void MST(ArrayList<Edge>[] graph, int src,int V,ArrayList<Integer> al){ 
         //Create priority queue.
         PriorityQueue<Pair> pq = new PriorityQueue<>();

         pq.add(new Pair(0,0));

         int cost = 0;

         //Create a visited array.
         boolean[] visited = new boolean[V];

         while(!pq.isEmpty()){
             //Select the minimun weight edge from the pq.
             Pair curr = pq.remove();
             
             //If current node is unvisited, then visit it.
             if(!visited[curr.node]){
                 visited[curr.node] = true;
                 al.add(curr.node);
                 //Add it's cost.
                 cost += curr.cost;

                 //Now loop over all it's neighbours and repeat the process for them.
                 for(int i=0;i<graph[curr.node].size();i++){
                    Edge e = graph[curr.node].get(i);

                    //Only do the process for the neighbours, if they're unvisited yet.
                    if(!visited[e.dest]){
                        //Add to the pq.
                        pq.add(new Pair(e.dest, e.weight));
                    }
                 }
             }

         }
         System.out.println(cost);
    }
    
    //T.C. = O(V+E); Used to find SCC(Strongly connected components)
    public static void kosarajuAlog(ArrayList<Edge>[] graph,int src, int V){
         //Step 1. Do topological sorting.
         Stack<Integer> s = new Stack<>();
         boolean[] visited = new boolean[V];

         //Loop over all vertices.
         for(int i=0;i<V;i++){
            if(!visited[i] ){
                topologicalSort(graph, visited, s, i);
            }
         }

         //Step 2. Transpose the graph.
         ArrayList<Edge>[] transpose = new ArrayList[V];

         for(int i=0;i<transpose.length;i++){
            //Also unvisit the nodes for the new graph.
             visited[i] = false;
             transpose[i] = new ArrayList<Edge>();
         }

         for(int i=0;i<V;i++){
             for(int j=0;j<graph[i].size();j++){
                Edge e = graph[i].get(j);
                //Interchange src and dest.
                transpose[e.dest].add(new Edge(e.dest, e.src,e.weight));
             }
         }

         //Step 3. Do dfs on nodes of the stack.
         while(!s.empty()){
            //Get the top element and perform dfs, if not already done.
             int curr = s.pop();

             if(!visited[curr]){
                 depthFirstSearch(transpose, visited, curr);
                 System.out.println();
             }

         }


    }
    
    public static void dfsForBridge(ArrayList<Edge>[] graph,int curr,int par, boolean[] visited,int[] dt,int[] low,int time){
         //Mark the current as visited.

         visited[curr] = true;
         dt[curr] = low[curr] = ++time;

        //Loop over all the neighbours.
        for(int i=0;i<graph[curr].size();i++){
            Edge e = graph[curr].get(i);

             //If neighbour is our parent, do nothing, continue.
             if(e.dest == par){
                continue;
             }

             //If it's unvisited, visit it.
            if(!visited[e.dest]){
                 dfsForBridge(graph, e.dest, curr, visited, dt, low, time);
                 //Update the values of dt and low.
                 low[curr] = Math.min(low[curr], low[e.dest]);
                 //Check for bridge condition.
                 if(dt[e.src] < low[e.dest]){
                     System.out.println(e.src + "---" + e.dest);
                 }
            }

            if(visited[e.dest]){
                low[curr] = Math.min(low[curr], dt[e.dest]);
            }
        }
        
    }
    //Uses Tarjan's algorithm.
    public static void findBridge(ArrayList<Edge>[] graph,int V){
         //Create arrays to track discovery time & loweest discovery time of all neighbours.
        int time = 0;
        int[] dt = new int[V];
        int[] low = new int[V];
        boolean[] visited = new boolean[V];
       
        //Perform dfs for every vertex.
       for(int i=0;i<V;i++){
           if(!visited[i]){
               dfsForBridge(graph, i, -1, visited, dt, low, time);
           }
       }
    }
 
    public static void dfsForAP(ArrayList<Edge>[] graph,int curr,int par, boolean[] visited,int[] dt,int[] low,int time,boolean[] ap){
        visited[curr] = true;
        dt[curr] = low[curr] = ++time;
        int children = 0;

        //Loop over all the neighbours now.
        for(int i=0;i<graph[curr].size();i++){
            Edge e = graph[curr].get(i);

            int neigh = e.dest;

            //If neighbour is our parent, do nothing.
            if(neigh == par){
                continue;
            } //If neigh is already visited, update the values of dt and low.
            else if(visited[neigh]){
                low[curr] = Math.min(low[curr],dt[neigh]);
            } //If neighbour is unvisited, then it must be our disconnected children..
            else{
                //First visit it.
                dfsForAP(graph, neigh, curr, visited, dt, low, time, ap);
                //Update the values of dt and low.
                low[curr] = Math.min(low[curr],low[neigh]);
                //Check for articulation point condition.
                if(dt[curr] <= low[neigh] && par != -1){
                  ap[curr] = true;
                }
                children++; //Update the children count of current.
            }
        }

        //Check for the corner case also, it the curr node is the start of the dfs with more than one disconnected children.
        if(par == -1 && children > 1){
            ap[curr] = true;
        }
    }

    public static void getArticulaionPoint(ArrayList<Edge>[] graph,int V){
        int[] dt = new int[V];
        int[] low = new int[V];
        int time = 0;
        boolean[] vis = new boolean[V];
        boolean[] ap = new boolean[V];

        for(int i=0;i<V;i++){
            if(!vis[i]){
                dfsForAP( graph,0,-1,vis,dt,low,time,ap);
            }
        }

        for(int i=0;i<V;i++){
            if(ap[i]){
                System.out.println("A.P. -> " + i);
            }
        }
    }
    
    //Bipartite graph(using DFS).
    public static boolean checkBipartiteDFS(ArrayList<Edge>[] graph,int[] colors,int curr,int color){
          //First of all fill the colors to the current node. We'll color with 0 & 1.
          colors[curr] = color;

          //Loop over all the neighbours.
          for(int i=0;i<graph[curr].size();i++){
              Edge e = graph[curr].get(i);

              //Check if the neighbour is colored or not, if colored then which color.
              if(colors[e.dest] != -1){
                //Adjacent node has the same color.
                  if(colors[e.dest] == colors[curr] ){
                     return false;
                  }
              }
              //If neighbour is not colored, color it with the opposiste color of current and proceed futher.
              else if(checkBipartiteDFS(graph, colors, e.dest,1 - color) == false){
                  return false;
              }
          }

          return true;
    }
    
    public static boolean checkBipartiteBFS(ArrayList<Edge>[] graph,int start,int[] colors,int color){
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        colors[start] = 0;

        while(!q.isEmpty()){
            int curr = q.remove();

            //Loop over the neighbours and add them to the queue also.
            for(int i=0;i<graph[curr].size();i++){
                Edge e = graph[curr].get(i);
                
                //If neighbour is uncolored, color it.
                if(colors[e.dest] == -1){
                    colors[e.dest] = 1 - colors[curr];
                    q.add(e.dest);
                }
                else if(colors[e.dest] != -1){
                    //If adjacnet nodes have same color, return false.
                    if(colors[e.dest] == colors[curr]){
                        return false;
                    }
                }
                
            }
        }

        return true;
    }
    
    public static void floydWarshall(ArrayList<Edge>[] graph,int[][] matrix){

        //Now let's find the matrices for all values of K. K also equels to no. of vertices of the graph, so size
        //of the matric id K x K.

        int n = matrix.length;
        int inf = (int)1e9;

        System.out.println(n);

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                        matrix[i][j] = Math.min(matrix[i][j], matrix[i][k] + matrix[k][j]);
                }
            }
        }

        //Detect -ve weight cycles.
          for (int i = 0; i < n; i++) {
               if(matrix[i][i] < 0){
                  System.out.println("Negative weight cycle exists!");
               }
            }

           
      }
    }
    public static void main(String[] args) {
        System.out.println("Hello everyone!");

        int V = 5;

        //We'll create our graph using array of arraylist.
        ArrayList<Edge>[] graph = new ArrayList[V];

        createGraph(graph);

        //Print all the neighbours of a vertex in a graph.
        // for(int i=0;i<graph[3].size();i++){
        //      Edge e = graph[3].get(i);
        //      System.out.println(e.dest + " weight " + e.weight);
        // }
         
        
         //Add the first starting node in the queue.
         
         boolean[] visited = new boolean[V];

         for(int i=0;i<visited.length;i++){
             visited[i] = false;
         }

         
         
         //Loop over the graph and do BFS. By looping over like this, we also ensure that all vertexes are 
         //traversed even when if the graph is disconnected.
         
        //  for(int i=0;i<graph.length;i++){
        //      if(visited[i] == false){
        //          //Make it the starting point and do BFS over it.
        //          breadthFirstSearch(graph, visited, i);
        //      }
        //  }
        
        //Depth first search.
        // for(int i=0;i<graph.length;i++){
        //      if(visited[i] == false){
        //          //Make it the starting point and do BFS over it.
        //          depthFirstSearch(graph, visited, 0);
        //      }
        //  }
        
        //int src = 0;
        //int tar = 3;
        //allPaths(graph, visited, String.valueOf(src),src, tar);  

        //Detect cycle in directed graphs.
        // boolean[] recStack = new boolean[V];
        //  for(int i=0;i<recStack.length;i++){
        //      recStack[i] = false;
        //  }

         //For disconnected graphs.
        //  for(int i=0;i<graph.length;i++){
        //      boolean isCycle = detectCycleDirected(graph, visited, recStack, 0);
        //      if(isCycle){
        //         System.out.println(isCycle);
        //         break;
        //      }
        //  }


    //    Stack<Integer> ourStack = new Stack<>();

    //    //To ensure all nodes are being traversed.
    //    for(int i=0;i<graph.length;i++){
    //     //If the node isn't visited, then visit it and do the work.
    //          if(!visited[i]){
    //              topologicalSort(graph, visited,ourStack,i);
    //          }
            
    //    }
       
    //    while (!ourStack.isEmpty()) {
    //      System.out.print(ourStack.pop() + " ");
    //    }

   // System.out.println(detectCycleUndirected(graph, visited, -1, 0));;

  
    
   //dijkstraAlgo(graph, 0 , V );
   
//    int[] dist = new int[V];
    
//    for(int i=0;i<dist.length;i++){
//        if(i != 0 ){
//           dist[i] = Integer.MAX_VALUE;
//        }else{
//         dist[i] = 0;
//        }
//    }

   //bellManFord(graph, 0, V,dist);
//    ArrayList<Integer> al = new ArrayList<>(); 
//    MST(graph, 0, V,al);

//    for(int i=0;i<al.size();i++){
//       System.out.print(al.get(i) + " ");
//    }

    //kosarajuAlog(graph, 0, V);

   

    //findBridge(graph,V);

   // getArticulaionPoint(graph, V);
   int[] colors = new int[V];
   for(int i=0;i<colors.length;i++){
        colors[i] = -1;
   }

   //System.out.println(checkBipartite(graph, colors,1,0));

  //Apply a loop for all nodes of graph to ensure disconnected parts also gets covered.
//   for(int i=0;i<V;i++){
//      if(colors[i] == -1){
//          System.out.println(checkBipartiteBFS(graph,1, colors, V));
//      }
//   }

    //int[][] matrix = new int[V][V];

    // for(int i=1;i<V;i++){
    //     for(int j=1;j<V;j++){
    //         matrix[i][j] = Integer.MAX_VALUE;
    //     }
    // }
    
    int inf = (int)1e9;
    int[][] matrix = {{0,2,inf,inf},{1,0,3,inf},{inf,inf,0, inf},{3,5,4,0}};

    
    floydWarshall(graph, matrix);
    
    int n = matrix.length;
   
 
   
    }
}
