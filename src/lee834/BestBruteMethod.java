import java.util.HashSet;
import java.util.Set;

class BestBruteMethod {
    private ArrayList<Set<Integer>> tree = new ArrayList<>();
    private int N = 0;
    private int[] result;
    // countSubTreeNodesNum[i] 表示 i子树的节点数量
    private int[] countSubTreeNodesNum;
    public int[] sumOfDistancesInTree(int N, int[][] edges) {
        this.N = N;
        this.result = new int[N];
        this.countSubTreeNodesNum = new int[N];
        // 初始化countSubTreeNodesNum数量
        for (int i = 0; i < N; i++)
            countSubTreeNodesNum[i] = 1;
        for (int i = 0; i < N; ++i)
            tree.add(new HashSet<Integer>());
        for (int[] edge: edges){   
            tree.get(edge[1]).add(edge[0]);
            tree.get(edge[0]).add(edge[1]);
        }
        // 第一步计算result[0]
        computeResult(0,0,0,-1);
        
        // 第二步，计算 count
        computeCountSubTreeNodesNum(0,-1);
        print(countSubTreeNodesNum, N);
        // 第三步
        computeOtherNodeResult(0,-1);
        
        // for (int i = 0; i < N; i++){
        //     dfs(i,i,0,i);
        // }
        return this.result;
        
    }
    private void print(int[] array,int N){
        for (int i = 0; i < N; i++)
            System.out.print(array[i] + ",");
        System.out.println();
    }
    private void computeResult(int countid,int node,int floors,int parent){
        for(int child :tree.get(node)){
            if(parent != child){
                computeResult(countid,child, floors + 1, node);
                result[countid] += floors + 1;
            }
        }
    }
    /**
    *  计算当前节点的数据值
    *  @node 表示当前需要计算的节点
    *  @parent 表示当计算的父节点
    **/
    private void computeCountSubTreeNodesNum(int node, int parent){
        for (int child: tree.get(node))
            if(parent != child){
                computeCountSubTreeNodesNum(child, node);
                countSubTreeNodesNum[node] += countSubTreeNodesNum[child];
            }
    }
    /**
    *  利用 result[x] = subtreeResult[x] + subtreeResult[y] + N（y）
    *  利用 result[y] = subtreeResult[x] + subtreeResult[y] + N（x）
    *  result[y] = result[x] + N(x) - N (y)
    *  @node 表示当前需要计算的节点
    *  @parent 表示当计算的父节点
    **/
    private void computeOtherNodeResult(int node, int parent){
        for (int child: tree.get(node))
            if(parent != child){
                result[child] = result[node] + this.N - countSubTreeNodesNum[child] - countSubTreeNodesNum[child];
                computeOtherNodeResult(child, node);
            }
    }
}
