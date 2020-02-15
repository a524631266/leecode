package lee834;

class BruteMethod {
    private ArrayList<Set<Integer>> tree = new ArrayList<>();
    private int N = 0;
    private int[] result;
    public int[] sumOfDistancesInTree(int N, int[][] edges) {
        this.N = 0;
        this.result = new int[N];
        for (int i = 0; i < N; ++i)
            tree.add(new HashSet<Integer>());
        for (int[] edge: edges){   
            tree.get(edge[1]).add(edge[0]);
            tree.get(edge[0]).add(edge[1]);
        }
        for (int i = 0; i < N; i++){
            dfs(i,i,0,i);
        }
        return this.result;
        
    }
    private void dfs(int countid,int parent,int floors,int pre){
        for(int child :tree.get(parent)){
            if(pre != child){
                dfs(countid,child, floors + 1, parent);
                result[countid] += floors + 1;
            }
        }
    }
}
