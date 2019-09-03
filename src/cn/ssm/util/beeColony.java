package cn.ssm.util;

import java.lang.Math;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public  class beeColony {



	/* Control Parameters of ABC algorithm*/
	int NP=50; /* The number of colony size (employed bees+onlooker bees)*/
	int FoodNumber = NP/2; /*The number of food sources equals the half of the colony size*/	
	int maxCycle = 500; /*循环次数The number of cycles for foraging {a stopping criteria}*/
	int limit = maxCycle/10;  /*A food source which could not be improved through "limit" trials is abandoned by its employed bee*/
	int n = 10;
	int m = 5;
	int maxMutationNum = 16;
	/* Problem specific variables*/
	int D = n*m; /*染色体长度 The number of parameters of the problem to be optimized*/
	//int lb = 1; /*最少工件数lower bound of the parameters. */
	//int ub = 6; /*最多工件数upper bound of the parameters. lb and ub can be defined as arrays for the problems of which parameters have different bounds*/


	int runtime = 100;  /*Algorithm can be run many times in order to see its robustness*/

	int dizi1[]=new int[10];
	int Foods[][]=new int[FoodNumber][D];        /*Foods is the population of food sources. Each row of Foods matrix is a vector holding D parameters to be optimized. The number of rows of Foods matrix equals to the FoodNumber*/
	int f[]=new int[FoodNumber];        /*f is a vector holding objective function values associated with food sources */
	double fitness[]=new double[FoodNumber];      /*fitness is a vector holding fitness (quality) values associated with food sources*/
	double trial[]=new double[FoodNumber];         /*trial is a vector holding trial numbers through which solutions can not be improved*/
	double prob[]=new double[FoodNumber];          /*prob is a vector holding probabilities of food sources (solutions) to be chosen*/
	int solution[]=new int[D];            /*New solution (neighbour) produced by v_{ij}=x_{ij}+\phi_{ij}*(x_{kj}-x_{ij}) j is a randomly chosen parameter and k is a randomlu chosen solution different from i*/
	
                   
	int ObjValSol;              /*Objective function value of new solution*/
	double FitnessSol;              /*Fitness value of new solution*/
	int neighbour, param2change;                   /*param2change corrresponds to j, neighbour corresponds to k in equation v_{ij}=x_{ij}+\phi_{ij}*(x_{kj}-x_{ij})*/

	int GlobalMin;                       /*Optimum solution obtained by ABC algorithm*/
	int GlobalParams[]=new int[D];                   /*Parameters of the optimum solution*/
	double GlobalMins[]=new double[runtime];            
	         /*GlobalMins holds the GlobalMin of each run in multiple runs*/
	double r; /*a random number in the range [0,1)*/
	/*ft06*/
	/*private int[][] time={{1,3,6,7,3,6},{8,5,10,10,10,4},{5,4,8,9,1,7},{5,5,5,3,8,9},{9,3,5,4,3,1},{3,3,9,10,4,1}};
    private int[][] asset={{2,3,1,4,6,5},{5,1,2,6,3,4},{4,5,1,2,6,3},{2,1,3,4,5,6},{5,2,1,6,3,4},{4,1,6,2,5,3}};*/
	/*10*5 la01*/
	private int[][] time={{21,53,95,55,34},{21,52,16,26,71},{39,98,42,31,12},{77,55,79,66,77},{83,34,64,19,37},{54,43,79,92,62},{69,77,87,87,93},{38,60,41,24,83},{17,49,25,44,98},{77,79,43,75,96}};
    private int[][] asset={{2,1,5,4,3},{1,4,5,3,2},{4,5,2,3,1},{2,1,5,3,4},{1,4,3,2,5},{2,3,5,1,4},{4,5,2,3,1},{3,1,2,4,5},{4,2,5,1,3},{5,4,3,2,1}};
    
    /*a function pointer returning double and taking a D-dimensional array as argument */
	/*If your function takes additional arguments then change function pointer definition and lines calling "...=function(solution);" in the code*/


//	typedef double (*FunctionCallback)(double sol[D]);  

	/*benchmark functions */

//	double sphere(double sol[D]);
//	double Rosenbrock(double sol[D]);
//	double Griewank(double sol[D]);
//	double Rastrigin(double sol[D]);

	/*Write your own objective function name instead of sphere*/
//	FunctionCallback function = &sphere;

	/*Fitness function*/
	double CalculateFitness(double fun) 
	 {
		 double result=0;
		 if(fun>=0)
		 {
			 result=1/(fun+1);
		 }
		 else
		 {
			 
			 result=0;
		 }
		 return result;
	 }

	/*The best food source is memorized*/
	void MemorizeBestSource() 
	{
	   int i,j;
	    
		for(i=0;i<FoodNumber;i++)
		{
		if (f[i]<GlobalMin)
			{
	        GlobalMin=f[i];
	        for(j=0;j<D;j++)
	           GlobalParams[j]=Foods[i][j];
	        }
		}
	 }

	/*Variables are initialized in the range [lb,ub]. If each parameter has different range, use arrays lb[j], ub[j] instead of lb and ub */
	/* Counters of food sources are also initialized in this function*/

   //随机产生一条食物源，相当于染色体
	void init(int index)
	{
	   int j;
	   Random random=new Random();
	   int solution[]=null;//声明数组
	   solution=new int[D];//开辟空间，大小为n*m
	   for (j=0;j<D;j++)
			{
		        int ran=random.nextInt(n)+1;
		        int ranSum=0;
		        
		        for(int i: solution){
		        	if(i==ran)
		        		ranSum++;
		        }
		        if(ranSum<m){
		        	solution[j] = ran; 
		        }else{
		        	j--;
		        }	        
			}
	   Foods[index]=solution;
	   //f相当于len[]
		f[index]=calculateFunction(solution);
		fitness[index]=CalculateFitness(f[index]);
		trial[index]=0;
	}


	/*All food sources are initialized */
	void initial()
	{
		int i;
		for(i=0;i<FoodNumber;i++)
		{
		init(i);
		}
		GlobalMin=f[0];
	    for(i=0;i<D;i++)
	    GlobalParams[i]=Foods[0][i];


	}

	void SendEmployedBees()
	{
	  int i,j;
	  /*Employed Bee Phase*/
	   for (i=0;i<FoodNumber;i++)
	        {
	        /*The parameter to be changed is determined randomly*/
	        r = ((double) Math.random()*32767 / ((double)(32767)+(double)(1)) );
	        param2change=(int)(r*D);
	        
	        /*A randomly chosen solution is used in producing a mutant solution of the solution i*/
	        r = (   (double)Math.random()*32767 / ((double)(32767)+(double)(1)) );
	        neighbour=(int)(r*FoodNumber);

	        /*Randomly selected solution must be different from the solution i*/        
	       // while(neighbour==i)
	       // {
	       // r = (   (double)Math.random()*32767 / ((double)(32767)+(double)(1)) );
	       // neighbour=(int)(r*FoodNumber);
	       // }
	        for(j=0;j<D;j++)
	        solution[j]=Foods[i][j];
	       
	        /*v_{ij}=x_{ij}+\phi_{ij}*(x_{kj}-x_{ij}) */
	        //r = (   (double)Math.random()*32767 / ((double)(32767)+(double)(1)) );
	        int mutationNum = (int) (Math.random() * maxMutationNum); 
	        mutation(n,mutationNum,solution);

	        /*if generated parameter value is out of boundaries, it is shifted onto the boundaries*/
	        /*if (solution[param2change]<lb)
	           solution[param2change]=lb;
	        if (solution[param2change]>ub)
	           solution[param2change]=ub;*/
	        ObjValSol=calculateFunction(solution);
	        FitnessSol=CalculateFitness(ObjValSol);
	        
	        /*a greedy selection is applied between the current solution i and its mutant*/
	        if (FitnessSol>fitness[i])
	        {
	        
	        /*If the mutant solution is better than the current solution i, replace the solution with the mutant and reset the trial counter of solution i*/
	        trial[i]=0;
	        for(j=0;j<D;j++)
	        Foods[i][j]=solution[j];
	        f[i]=ObjValSol;
	        fitness[i]=FitnessSol;
	        }
	        else
	        {   /*if the solution i can not be improved, increase its trial counter*/
	            trial[i]=trial[i]+1;
	        }


	        }

	        /*end of employed bee phase*/

	}

	/* A food source is chosen with the probability which is proportioal to its quality*/
	/*Different schemes can be used to calculate the probability values*/
	/*For example prob(i)=fitness(i)/sum(fitness)*/
	/*or in a way used in the metot below prob(i)=a*fitness(i)/max(fitness)+b*/
	/*probability values are calculated by using fitness values and normalized by dividing maximum fitness value*/
	void CalculateProbabilities()
	{
	     int i;
	     double maxfit;
	     maxfit=fitness[0];
	  for (i=1;i<FoodNumber;i++)
	        {
	           if (fitness[i]>maxfit)
	           maxfit=fitness[i];
	        }

	 for (i=0;i<FoodNumber;i++)
	        {
	         prob[i]=(0.9*(fitness[i]/maxfit))+0.1;
	        }

	}

	void SendOnlookerBees()
	{

	  int i,j,t;
	  i=0;
	  t=0;
	  /*onlooker Bee Phase*/
	  while(t<FoodNumber)
	        {

	        r = (   (double)Math.random()*32767 / ((double)(32767)+(double)(1)) );
	        if(r<prob[i]) /*choose a food source depending on its probability to be chosen*/
	        {        
	        t++;
	        
	        /*The parameter to be changed is determined randomly*/
	        r = ((double)Math.random()*32767 / ((double)(32767)+(double)(1)) );
	        param2change=(int)(r*D);
	        
	        /*A randomly chosen solution is used in producing a mutant solution of the solution i*/
	        r = (   (double)Math.random()*32767 / ((double)(32767)+(double)(1)) );
	        neighbour=(int)(r*FoodNumber);

	        /*Randomly selected solution must be different from the solution i*/        
	        while(neighbour == i)
	        {
	        	//System.out.println(Math.random()*32767+"  "+32767);
	        r = (   (double)Math.random()*32767 / ((double)(32767)+(double)(1)) );
	        neighbour=(int)(r*FoodNumber);
	        }
	        for(j=0;j<D;j++)
	        solution[j]=Foods[i][j];

	        /*v_{ij}=x_{ij}+\phi_{ij}*(x_{kj}-x_{ij}) */
	        r = (   (double)Math.random()*32767 / ((double)(32767)+(double)(1)) );
	        int maxtrialindex=0;
	        for (int b=1;b<FoodNumber;b++)
	        {
	         if (trial[b]>trial[maxtrialindex])
	         maxtrialindex=b;
	        }
	        int solution2[]=new int[D];
	        for(j=0;j<D;j++)
		        solution2[j]=Foods[maxtrialindex][j];	       
		    	 genetic(n,m,solution,solution2);		             
	        /*if generated parameter value is out of boundaries, it is shifted onto the boundaries*/
	        /*if (solution[param2change]<lb)
	           solution[param2change]=lb;
	        if (solution[param2change]>ub)
	           solution[param2change]=ub;*/
	        ObjValSol=calculateFunction(solution);
	        FitnessSol=CalculateFitness(ObjValSol);
	        
	        /*a greedy selection is applied between the current solution i and its mutant*/
	        if (FitnessSol>fitness[i])
	        {
	        /*If the mutant solution is better than the current solution i, replace the solution with the mutant and reset the trial counter of solution i*/
	        trial[i]=0;
	        for(j=0;j<D;j++)
	        Foods[i][j]=solution[j];
	        f[i]=ObjValSol;
	        fitness[i]=FitnessSol;
	        }
	        else
	        {   /*if the solution i can not be improved, increase its trial counter*/
	            trial[i]=trial[i]+1;
	        }
	        } /*if */
	        i++;
	        if (i==FoodNumber)
	        i=0;
	        }/*while*/

	        /*end of onlooker bee phase     */
	}

	/*determine the food sources whose trial counter exceeds the "limit" value. In Basic ABC, only one scout is allowed to occur in each cycle*/
	void SendScoutBees()
	{
	int maxtrialindex,i;
	maxtrialindex=0;
	for (i=1;i<FoodNumber;i++)
	        {
	         if (trial[i]>trial[maxtrialindex])
	         maxtrialindex=i;
	        }
	if(trial[maxtrialindex]>=limit)
	{
		init(maxtrialindex);
	}
	}


	
	


	double sphere(double sol[])
	{
	int j;
	double top=0;
	for(j=0;j<D;j++)
	{
	top=top+sol[j]*sol[j];
	}
	return top;
	}

	double Rosenbrock(double sol[])
	{
	int j;
	double top=0;
	for(j=0;j<D-1;j++)
	{
	top=top+100*Math.pow((sol[j+1]-Math.pow((sol[j]),(double)2)),(double)2)+Math.pow((sol[j]-1),(double)2);
	}
	return top;
	}

	 double Griewank(double sol[])
	 {
		 int j;
		 double top1,top2,top;
		 top=0;
		 top1=0;
		 top2=1;
		 for(j=0;j<D;j++)
		 {
			 top1=top1+Math.pow((sol[j]),(double)2);
			 top2=top2*Math.cos((((sol[j])/Math.sqrt((double)(j+1)))*Math.PI)/180);

		 }	
		 top=(1/(double)4000)*top1-top2+1;
		 return top;
	 }
//适应度函数
	 int calculateFunction(int[] solution2)
	 {
	 return Rastrigin (solution2);	
	 }
	 int Rastrigin(int[] solution2)
	 {
	  	    int len = 0;
		  	int tempAsset[]=null;//各工件时间
		    tempAsset=new int[solution2.length];//开辟空间，大小为size
	  	    int temp1[]=null;//声明数组
	        temp1=new int[2*solution2.length+1];//开辟空间，大小为size
	        int temp2[]=null;//各工件时间
	        temp2=new int[n+1];//开辟空间，大小为size
	        int temp3[]=null;//设备
	        temp3=new int[solution2.length];//开辟空间，大小为size
	        int temp4[]=null;//时间
	        temp4=new int[solution2.length];//开辟空间，大小为size
	        for(int i=0;i<solution2.length;i++){
	        	int j=1; 
	        	temp1[i*2]=solution2[i];      	
	        	for(int l=0;l<i;l++)
	        		if(solution2[l]==solution2[i])
	        		j++; 
	        		temp1[2*i+1]=j;   	
	        }
	        for (int i = 0; i < solution2.length ; i++) {
	        	temp2[temp1[i*2]]+=time[temp1[i*2]-1][temp1[2*i+1]-1];
	  	        temp3[i]=asset[temp1[i*2]-1][temp1[2*i+1]-1];
	  	        temp4[i]=time[temp1[i*2]-1][temp1[2*i+1]-1];
	        	int g=2*solution2.length;
	  	        for(int j=0;j<i;j++){      
	  	        	if(temp3[j]==asset[temp1[i*2]-1][temp1[2*i+1]-1])
	  	        		g=j;  	        	
	  	        }
	  	        if(g!=2*solution2.length){
	  	        	int sum1=temp2[temp1[i*2]]-time[temp1[i*2]-1][temp1[2*i+1]-1];		  	      			
					if(tempAsset[temp3[i]]>sum1){ 	        	
		  	    		temp2[temp1[i*2]]+=tempAsset[temp3[i]]-sum1;
		  	    		 tempAsset[temp3[i]]=temp2[temp1[i*2]];
		  	        }
	  	        }
	  	      tempAsset[temp3[i]]=temp2[temp1[i*2]];
	  	     }
	        len=temp2[1];
	        for(int i=1;i<=n;i++){       	
	        	if(temp2[i]>len)
	        		len=temp2[i];
	        }
		 return len;
	 }
	 public void mutation(int n,int num,int[] solution2) {  
	        //允许变异  
	        int size = solution2.length;  
	        
	            //寻找变异位置  
	            int at = ((int) (Math.random() * size)) % size; 
	            //简单倒位变异后的值 
	           /* for (int j = at; j <= num/2+at-1; j++) {  
	                int t = solution2[j%size];  
	                solution2[j%size] = solution2[(num+at*2-1-j)%size];  
	                solution2[(num+at*2-1-j)%size] = t;  
	            }*/
	          //倒位变异后的值
	            int temp[]=null;//声明数组
	            temp=new int[size];//开辟空间，大小为size
	            int temp2[]=null;//声明数组
	            temp2=new int[size];//开辟空间，大小为size
	            int i=0,l=0;
	            for (int j = at; j <= num+at-1; j++) {
	            	
	            	 temp[i++]=solution2[j%size];
	            }
	            //记录未选取的片段
	            for(int j=(at+num)%size;j<(at+num)%size+size-num;j++){
	            	temp2[l++]=solution2[j%size];
	            }
	            l=0;
	            //寻找插入位置  
	            int at2 = ((int) (Math.random() * size)) % size;
	            //变异后的值 
	            for (int j = at2; j <= num+at2-1; j++) {  
	            	solution2[j%size] =temp[--i];
	            	
	            } 
	          //将未选取的片段插入
	            for(int j=(at2+num)%size;j<(at2+num)%size+size-num;j++){
	            	solution2[j%size]=temp2[l++];
	            } 
	           
	    }
	 public static List<int []> genetic(int n,int m,int[] p1, int[] p2) {  
	        if (p1 == null || p2 == null) { //染色体有一个为空，不产生下一代  
	            return null;  
	        }  
	        if (p1 == null || p2 == null) { //染色体有一个没有基因序列，不产生下一代  
	            return null;  
	        }  
	        if (p1.length != p2.length) { //染色体基因序列长度不同，不产生下一代  
	            return null;  
	        }  
	        int[] c1 = p1;  
	        int[] c2 = p2;  
	        //随机产生交叉互换位置  
	        int size = c1.length;  
	        /* int a = ((int) (Math.random() * size)) % size;  
	        int b = ((int) (Math.random() * size)) % size;  
	        int min = a > b ? b : a;  
	        int max = a > b ? a : b;  
	        //对位置上的基因进行交叉互换  
	        for (int i = min; i <= max; i++) {  
	            int t = c1.getGene()[i];  
	            c1.getGene()[i] = c2.getGene()[i];  
	            c2.getGene()[i] = t;  
	        }*/
	        Random random=new Random();
	        int temp1[]=null;//声明数组
	        temp1=new int[n/2];//开辟空间，大小为size
	        int temp2[]=null;//声明数组
	        temp2=new int[size];//开辟空间，大小为size
	        int temp3[]=null;//声明数组
	        temp3=new int[size];//开辟空间，大小为size
	        for(int i=0;i<n/2;i++){
	        	int ran = random.nextInt(n)+1;
	        	temp1[i]=ran;
	        }
	        int l=0, v=0;
	        for(int i=0;i<size;i++){
	        	int j=p1[i];
	        	Boolean flag=false;
	        	for(int p:temp1){
	        		if(j==p)
	        			flag=true;     	
	        	}
	        	if(!flag){
	        		temp2[l++]=p1[i];
	        		c1[i]=0;        		
	        	}
	        	int k=p2[i];
	        	flag=false;
	        	for(int p:temp1){
	        		if(k==p)
	        			flag=true; 
	        	}
	        	if(!flag){
	            	temp3[v++]=p2[i];
	            	c2[i]=0;        		           
	        	}
	        	
	        }
	        l=0;v=0;
	        for(int i=0;i<size;i++){
	    		if(c2[i]==0){
	    			c1[i]=temp2[l++];
	    		}else{
	    			c1[i]=c2[i];
	    		}
	    	}
	        for(int i=0;i<size;i++){
	    		if(c1[i]==0){
	    			c2[i]=temp3[v++];
	    		}else{
	    			c2[i]=c1[i];
	    		}
	    	}
	        List<int []> list = new ArrayList<int []>();  
	        list.add(c1);  
	        list.add(c2);  
	        return list; 
	        
	    }		
}
