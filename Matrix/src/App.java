import java.util.Random;

public class App {
    public static void main(String[] args) {
        Random r = new Random();
        int n = 4;
        int[][] a = new int[n][n];
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                a[i][j] = (int) (Math.pow(-1, r.nextInt(2))*r.nextInt(10));
            }
        }
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                System.out.printf(" %2d", a[i][j]);
            }
            System.out.println();
        }
        double[][] bb = inv(a, n);
        System.out.println(det(a, n));
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                System.out.printf(" %.2f\t", bb[i][j]);
            }
            System.out.println();
        }
        
    }

    public static double det(int a[][], int n){
        double s=0;
        int[][] aa = new int[n-1][n-1];
        for(int i=0; i<n; i++){
            if(n == 1){
                return a[0][0];
            }else{
                int ll = 0;
                for(int j=1; j<n; j++, ll++){
                    int l = 0;
                    for(int k=0; k<n; k++){
                        if(k != i){
                            aa[ll][l] = a[j][k];
                            l++;
                        }
                    }
                }
                s += Math.pow(-1, i)*a[0][i]*det(aa, n-1);
            }
        }
        return s;
    }

    public static double[][] adj(int[][] a, int n){
        double[][] adjA = new double[n][n];
        double[][] temp = new double[n][n];
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                int[][] aa = new int[n-1][n-1];
                int l = 0;
                for(int k=0; k<n; k++){
                    int ll = 0;
                    if(k != i){
                        for(int kk=0; kk<n; kk++){
                            if(kk != j){
                                aa[l][ll] = a[k][kk];
                                ll++;
                            }
                        }
                        l++;
                    }
                }
                temp[i][j] = Math.pow(-1, i+j)*det(aa, n-1);
            }
        }
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                adjA[j][i] = temp[i][j];
            }
        }
        return adjA;
    }

    public static double[][] inv(int[][] a, int n){
        double[][] invA = new double[n][n];
        double[][] temp = new double[n][n];
        temp = adj(a, n);
        double d = det(a, n);
        
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                invA[i][j] = temp[i][j]/d;
            }
        }
        return invA;
    }
}
