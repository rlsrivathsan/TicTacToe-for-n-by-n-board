
public class Solution {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arInt = {1,1,2,0,3,3,3};
		int  test = solution(6, arInt) ;
		System.out.println(test);
	}
	public static int solution(int M, int[] A) {
	        int N = A.length;
	        int[] count = new int[M + 1];
	        for (int i = 0; i <= M; i++)
	            count[i] = 0;
	        int maxOccurence = 1;
	        int index = -1;
	        for (int i = 0; i < N; i++) {
	        	int test = count[A[i]];
	        	if (count[A[i]] > 0) {
	                int tmp = count[A[i]];
	                if (tmp > maxOccurence) {
	                    maxOccurence = tmp;
	                    index = i;
	                }
	                count[A[i]] = tmp + 1;
	            } else {
	                count[A[i]] = 1;
	            }
	        }
	        return A[index];
	    }
	}


