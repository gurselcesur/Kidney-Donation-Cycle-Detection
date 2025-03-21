import java.util.*;

public class DonorCycle {
    
    static final int minHLA = 60;

    /**
     * creates edges in the graph
     * adds an edge from donor friend to that recipient
     */
	private static void addEdge(int[][] graph, int[][] matchScores, int[] donorFriends, int n, int m){
		for(int i=0; i<m; i++){
			for (int j=0; j<n; j++){
				if(matchScores[i][j] >= minHLA){
					graph[donorFriends[i]][j] = 1;
				}
			}
		}
	}

	/**
	 * depth-first search algorithm to find if target recipient is in a cycle
	 * @visited to track all visited nodes
	 * @recStack to track nodes in current path
	 * @return true if query recipient is part of a cycle, false otherwise
	 */
	private static boolean DFS(int graph[][], boolean visited[], boolean recStack[], int current, int query) {
		visited[current] = true;
		recStack[current] = true;

		for (int next = 0; next < graph.length; next++) {
			if (graph[current][next] == 1) {
				if (!visited[next]) {
					if (DFS(graph, visited, recStack, next, query))
						return true;
				} else if (recStack[next] && next == query) {
					return true;
				}
			}
		}
		recStack[current] = false;
		return false;
	}

	/**
	 * to check if a recipient is part of a donor cycle
	 * 
	 * @param matchScores Matrix of HLA compatibility scores between donors and recipients
	 * @param donorFriends Array showing which recipient each donor wants to help
	 * @param query The recipient we're checking for cycle membership
	 * @return true if query recipient is part of a cycle, false otherwise
	 */
	public static boolean isInCycle(int[][] matchScores, int[] donorFriends, int query){
		int m = matchScores.length; // number of donors
		int n = matchScores[0].length; // number of recipients
		int[][] graph = new int[n][n];
		addEdge(graph, matchScores, donorFriends, n, m);
		boolean[] visited = new boolean[n];
		boolean[] recStack = new boolean[n];
		return DFS(graph, visited, recStack, query, query);
	}

	public static void main(String[] args){

		Scanner s = new Scanner(System.in);

		int n = s.nextInt();
		int m = s.nextInt();

		String donorsLine = s.next();
		String[] donorsArray = donorsLine.split(",",0);
		int[] donorsFriends = new int[donorsArray.length];
		for(int i=0; i<m; i++){
			donorsFriends[i] = Integer.parseInt(donorsArray[i]);
		}

		int[][] matchScores = new int[m][n];
		for(int i=0; i<m; i++){
			String matchscoreLine = s.next();
			String[] matchscoreArray = matchscoreLine.split(",",0);
			for(int j=0; j<n; j++){
				matchScores[i][j] = Integer.parseInt(matchscoreArray[j]);
			}
		}
		int query = s.nextInt();
		System.out.println(isInCycle(matchScores, donorsFriends, query));
	}


}
