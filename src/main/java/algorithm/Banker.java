package algorithm;

import java.util.Scanner;

public class Banker {
    Scanner sc = new Scanner(System.in);
    int m;
    int n=3;
    char[] zy = new char[100];    //存放资源的名字
    int[][] Max = new int[100][100];    //p[i]对j类资源的最大需求量
    int[][] Allocation =new int[100][100];    //p[i]已分配到的j类资源数量
    int[][] Need =new int[100][100];    //p[i]尚需的j类资源数量
    int[] Available =new int[100];    //某类资源的可用量
    int[] Request =new int[100];    //进程对某类资源的申请量
    int[] Work =new int[100];    //存放某一时刻系统可提供的资源量
    int[] Finish =new int[100];    //标记系统是否有足够的资源分配给各个进程
    int[] flag = new int[100];    //P[i]进程是否已经满足全部所需资源
    int[] Security =new int[100];    //存放安全序列


    //初始化资源数和每个进程的最大需求量
    public void init() {
        System.out.print("请输入进程数：");
        m = sc.nextInt();
        char x = 'A';
        for(int j=0;j<n;j++) {
            zy[j] = x;
            x++;
        }
        System.out.print("请输入"+n+"类资源初始化的资源数：");
        for(int i=0;i<n;i++) {
            Available[i] = sc.nextInt();
        }
        System.out.println("请输入"+m+"个进程的:");
        System.out.println("进程名       最大需求量:");
        System.out.print("          ");
        for(int i=0;i<n;i++) {
            System.out.print(zy[i] + " ");
        }
        System.out.println();
        for(int i=1;i<=m;i++) {
            System.out.print("进程p["+i+"]" + "   ");
            for(int j=0;j<n;j++) {
                Max[i-1][j] = sc.nextInt();
            }
        }
    }

    //尝试分配资源
    public void test(int i) {
        for(int j=0;j<n;j++) {
            //尝试分配资源
            Available[j]=Available[j]-Request[j];
            Allocation[i][j]=Allocation[i][j]+Request[j];
            Need[i][j]=Need[i][j]-Request[j];
        }
    }

    //试探性分配资源作废，与test操作相反
    public void retest(int i){
        for(int j=0;j<n;j++){
            Available[j]=Available[j]+Request[j];
            Allocation[i][j]=Allocation[i][j]-Request[j];
            Need[i][j]=Need[i][j]+Request[j];
        }
    }

    //安全性算法
    public int safe() {
        int l = 0;
        //设置work
        if (n >= 0) System.arraycopy(Available, 0, Work, 0, n);
        //初始化finish
        for(int i=0;i<m;i++) {
            Finish[i]=0;
        }
        //将已满足的进程标记为1
        for(int i=0;i<m;i++) {
            int tem = 0;
            for(int j=0;j<n;j++) {
                if(Need[i][j]==0) {
                    tem++;
                }
                if(tem == n) {
                    flag[i] = 1;
                }
            }
        }
        for(int i=0;i<m;i++){
            int apply=0; //用于记录已经全部满足的资源数
            for(int j=0;j<n;j++){
                if(Finish[i]==0 && Need[i][j]<=Work[j]){
                    apply++; //直到每类资源尚需数都小于当前系统可利用资源数才可分配
                    if(apply == n){ //apply等于资源类数，说明可以满足该进程的所有资源
                        for(int k=0;k<n;k++){
                            Work[k]=Work[k]+Allocation[i][k]; //更改当前可分配资源
                        }
                        Finish[i]=1;
                        if(flag[i] == 0) {
                            Security[l++]=i; //尚未完成的进程序号存入安全序列
                        }else if(flag[i] == 1) {
                            for(int k=0;k<n;k++) {
                                Allocation[i][k] = 0;
                            }
                        }
                        i=-1; //保证每次查询均从第一个进程开始
                    }
                }
            }
        }
        for(int i=0;i<m;i++){
            if(Finish[i]==0){ //当前情况只要有一个进程不能得到执行完成说明系统不安全
                return 0;
            }
        }
        return 1;
    }

    //打印申请资源成功后的相关信息
    public void print(int z) {
        //如果该进程结束了，就回收它释放的资源
        if(flag[z]==1) {
            for(int j=0;j<n;j++) {
                Available[j] = Available[j] + Max[z][j];
            }
        }
        System.out.print("申请成功！安全序列为：");
        int m1 = 0;//记录尚未完成的进程
        for(int i=0;i<m;i++) {
            if(flag[i]==0) {
                m1++;
            }
        }
        for(int k=0;k<m1;k++) {
            Security[k] = Security[k] + 1;
        }
        for(int k=0;k<m1;k++){
            System.out.print("p["+Security[k]+"]");
            if(k<m1-1){
                System.out.print("->");
            }
        }
        System.out.println();
        System.out.println("进程名      最大需求量      尚需求量      已分配量      执行结束否");
        int t1=0;
        System.out.print("          ");
        while(t1!=3) {
            for(int j=0;j<n;j++) {
                System.out.print(zy[j] + " ");
            }
            System.out.print("       ");
            t1++;
        }
        System.out.println();
        for(int k=0;k<m;k++) {
            int t2 = k+1;
            System.out.print("进程p["+t2+"]" + "   ");
            for(int j=0;j<n;j++) {
                System.out.print(Max[k][j] + " ");
            }
            System.out.print("       ");
            for(int j=0;j<n;j++) {
                System.out.print(Need[k][j] + " ");
            }
            System.out.print("       ");
            for(int j=0;j<n;j++) {
                System.out.print(Allocation[k][j] + " ");
            }
            System.out.print("       ");
            if(flag[k] == 0) {
                System.out.print("working");
            }else {
                System.out.print("finished");
            }
            System.out.println();
        }
        System.out.print("剩余资源数：" + " ");
        for(int j=0;j<n;j++) {
            System.out.print(Available[j] + " ");
        }
    }

    //第一次申请
    public void first() {
        for(int i=0;i<m;i++) {
            //未申请之前尚需求量等于最大需求量
            if (n >= 0) System.arraycopy(Max[i], 0, Need[i], 0, n);
        }
        System.out.println("请输入"+m+"个进程的:");
        System.out.println("进程名       第一次的申请量:");
        System.out.print("          ");
        for(int j=0;j<n;j++) {
            System.out.print(zy[j] + " ");
        }
        System.out.println();
        for(int i=0;i<m;i++) {
            int t=i+1;
            System.out.print("进程p["+t+"]" + "   ");
            for(int j=0;j<n;j++) {
                Request[j] = sc.nextInt();
            }
            test(i);//试探性分配
            if(safe()==1) {
                print(i);
            }else {
                retest(i);//不安全则试探性分配作废
                System.out.println("无安全序列，申请不成功");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {      
        Scanner sc = new Scanner(System.in);
        int s = 1;
        Banker banker = new Banker();
        banker.init();
        banker.first();
        while(s != 0) {
            System.out.println();
            System.out.print("是否需要再申请资源？(1/0)：");
            s = sc.nextInt();
            if(s == 1) {
                System.out.print("请输入进程编号(1-5)p：");
                int z = sc.nextInt();
                int z1 = z-1; //z1充当数组下标，比进程名少1
                System.out.print("请输入进程p["+z+"]对"+banker.n+"类资源的申请量：");
                for(int j=0;j<banker.n;j++) {
                    banker.Request[j] = sc.nextInt();
                }
                banker.test(z1);    //尝试分配
                if(banker.safe()==1) {
                    banker.print(z1);
                }else {
                    banker.retest(z1); //不安全则尝试分配作废
                    for(int j=0;j<banker.n;j++) {
                        if(banker.Request[j]>banker.Available[j]) {
                            System.out.println("申请资源超过系统可用资源，您的"+banker.zy[j]+"类可用资源为"+banker.Available[j]);
                            break;
                        }
                    }
                    System.out.print("请重新输入这个进程的申请信息！：");
                    for(int j=0;j<banker.n;j++) {
                        banker.Request[j] = sc.nextInt();
                    }
                    banker.test(z1);    //尝试分配
                    if(banker.safe()==1) {
                        banker.print(z1);
                    }else {
                        banker.retest(z1);
                        System.out.println("无安全序列，申请不成功");
                    }
                }
            }
        }
    }
}

