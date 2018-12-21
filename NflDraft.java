/* Oscar Rodas
  
   NflDraft.java */

// Simulates drafting 32 players to 8 teams for the NFL

import java.util.*;
import java.text.*;

public class NflDraft 
{
	public static String teams[] = {"team1", "team2", "team3", "team4", "team5", "team6", "team7", "team8"}; //data stored in arrays
	public static String players[] = { "p1","p2","p3","p4","p5","p6","p7","p8","p9","p10","p11","p12","p13","p14","p15","p16","p17","p18","p19","p20","p21","p22","p23","p24","p25","p26","p27","p28","p29","p30","p31","p32" };
	public static double percent[] = new double [8];
	public static int rounds[] = new int [32];
	public static String sortedTeams[] = new String [8];
	public static String sortedPlayers[] = new String [32];

    public static void main(String[] args) 
	{
		percent = percentRandomizer(percent, teams); //stores the percents
		sortedTeams = sortTeams(percent, teams); //stores the sorted teams
		rounds = roundRandomizer(rounds); //stores the random rounds
		sortedPlayers = sortPlayers(rounds, players); //stores the sorted players
		printresults(sortedTeams, sortedPlayers); //prints the final results
	}
	
	public static double[] percentRandomizer(double b[], String c[]) //creates random percentages for the teams and prints the results
	{
        DecimalFormat df = new DecimalFormat(".##");
        for(int i = 0; i < 8; i++)
        {
			b[i] = (Math.random() * 100);
        }
		for(int i = 0; i < 8; i++)
        {
			System.out.println(c[i] + " won  " + df.format(b[i]) + " of their games last season!");
		}
		System.out.println("");
		return b; // returns the percents as an array of doubles to main
    }
	
	public static int[] roundRandomizer(int c[]) //creates the rounds at random
	{
		int num = 0; //to keep track of the rounds
		int counter[][] = { {0,0}, {0,0} }; // 2-dimensional array
		Random randomGenerator = new Random();
		for(int i = 0; i < 32; i++)
        {
			c[i] = randomGenerator.nextInt(4) +1;
			num = c[i];
			switch(num)
			{
				case 1: 
					counter[0][0]++; //if a 1 is made, increment the counter for 1
					if(counter[0][0] > 8) 
					{
						counter[0][0]--; // if the same number is created more than 8 times, decrement counter
						i--; //and decrement i (to "dispose" of the number)
					}
					break;
				case 2:
					counter[0][1]++;
					if(counter[0][1] > 8)
					{
						counter[0][1]--;
						i--;
					}
					break;
				case 3:
					counter[1][0]++;
					if(counter[1][0] > 8)
					{
						counter[1][0]--;
						i--;
					}
					break;
				case 4:
					counter[1][1]++;
					if(counter[1][1] > 8)
					{
						counter[1][1]--;
						i--;
					}
					break;
				default:
					break;
			}
        }	
		return c; // returns data to main
    }
	
	public static String[] sortTeams(double array[], String a[]) // sorts the teams and displays the results
	{
		for (int i = (array.length - 1); i >= 0; i--)
		{
			for (int j = 0; j < i; j++)
			{
				if (array[j] < array[j+1])
				{
					double temp = array[j]; // sorting the percents
					array[j] = array[j+1];
					array[j+1] = temp;
					String tempS = a[j]; // sorting the teams along with their corresponding percents
					a[j] = a[j+1];
					a[j+1] = tempS;
				}	 
			} 
		} 
		for (int i = (0); i < 8; i++)
		{
			System.out.println(a[7-i] + " is ranked " + (8-i) ); // rank 1 is the highest percentage; rank 8 is the lowest percentage 
		}
		System.out.println("");
		return a; //returns the sorted teams
	}
	
	public static String[] sortPlayers(int r[], String p[]) //same purpose as sortTeams
	{
		for (int i = 0; i < 32; i++)
		{
			for (int j = 0; j < 32; j++)
			{
				if (r[i] < r[j])
				{
					int temp = r[i];
					r[i] = r[j];
					r[j] = temp;
					String tempS = p[i];
					p[i] = p[j];
					p[j] = tempS;
				}
			}
		}
		for (int i = (0); i < 32; i++)
		{
			System.out.println(p[i] + " will be drafted in round " + r[i] );
		}
		System.out.println("");
		return p; // returns the sorted players
	}
	
	public static void printresults(String t[], String p[]) //prints the final results
	{
		int chosenPlayers[] = new int [8];
		chosenPlayers = pickRandomPlayer(); //stores the randomly chosen players
		for(int i = 0;  i < 8; i++)
			System.out.println("round 1 "  + ": " + t[7-i] + " has chosen " + p[chosenPlayers[i]-1]);
			System.out.println("");
		chosenPlayers = pickRandomPlayer(); //stores 8 of the remaining players chosen at random
		for(int j = 0;  j < 8; j++)
			System.out.println("round 2 "  + ": " + t[7-j] + " has chosen " + p[chosenPlayers[j]-1+8]); 
			System.out.println("");
		chosenPlayers = pickRandomPlayer(); //stores 8 of the remaining players chosen at random
		for(int k = 0;  k < 8; k++)
			System.out.println("round 3 "  + ": " + t[7-k] + " has chosen " + p[chosenPlayers[k]-1+16]); 
			System.out.println("");
		chosenPlayers = pickRandomPlayer(); //stores the last 8 randomly chosen players
		for(int l = 0;  l < 8; l++)
			System.out.println("round 4 "  + ": " + t[7-l] + " has chosen " + p[chosenPlayers[l]-1+24]); 
	}
	
	public static int[] pickRandomPlayer()
	{
		int index = 0, count1s = 0, count2s = 0, count3s = 0, count4s = 0, count5s = 0, count6s = 0, count7s = 0, count8s = 0; //counts the players already chosen
		int num[] = new int [8];
		Random randomGenerator = new Random();
		for(int i = 0; i < 8; i++)
		{
			num[i] = randomGenerator.nextInt(8) + 1;
			index = num[i];
			switch(index)
			{
				case 1:
					count1s++; 
					if(count1s > 1)
					{
						count1s--; // if the player is chosen, prevent him from being chosen again
						i--;
					}
					break;
				case 2:
					count2s++;
					if(count2s > 1)
					{
						count2s--;
						i--;
					}
					break;
				case 3:
					count3s++;
					if(count3s > 1)
					{
						count3s--;
						i--;
					}
					break;
				case 4:
					count4s++;
					if(count4s > 1)
					{
						count4s--;
						i--;
					}
					break;
				case 5:
					count5s++;
					if(count5s > 1)
					{
						count5s--;
						i--;
					}
					break;
				case 6:
					count6s++;
					if(count6s > 1)
					{
						count6s--;
						i--;
					}
					break;
				case 7:
					count7s++;
					if(count7s > 1)
					{
						count7s--;
						i--;
					}
					break;
				case 8:
					count8s++;
					if(count8s > 1)
					{
						count8s--;
						i--;
					}
					break;
				default:
					break;
			}
		}
		return num; //returns the players in the randomly chosen order
	}
} 