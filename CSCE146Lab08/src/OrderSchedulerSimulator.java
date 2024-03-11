/*
 * Written by JJ Shepherd
 */
import java.util.*;
public class OrderSchedulerSimulator {

	public static final String[] NAMES = {"Alicja Ulderico","Philomel Olivette","Ines Marleen","Frauke George","Goodwin Kendra",
											"Onesiphoros Davorka", "Albin Bao", "Nirmala Erna", "Imtiyaz Umar","Marita Adel"};
	public static final String[] FOODS = {"Raisin and strawberry yoghurt","Roquefort salad","Mussels","Cuttlefish with rice","Sausage with berry salad",
											"Mulberry and broccoli salad","Feta and apricot salad","Chestnut and chorizo soup","Spaghetti squash","Cheddar and ham sandwich"};
	public static final int MAX_COOK_TIME = 20;
	public static final int MIN_COOK_TIME = 5;
	public static final int TOTAL_ORDERS = 10;
	public static final String DEC = "-------------------------------------------";
	public static void main(String[] args) {
		Random r = new Random(100);//Fixing the random seed to 100
		OrderScheduler s = new OrderScheduler();
		//Creating a sequence of orders
		Order[] orders = new Order[10];
		for(int i=0;i<TOTAL_ORDERS;i++)
		{
			orders[i] = new Order(NAMES[i],FOODS[i],r.nextInt(MAX_COOK_TIME)+MIN_COOK_TIME,i*5);
		}
		System.out.println(DEC+"\nStarting Order Scheduler Simulation\n"+DEC);
		//Starting the simulation
		int count = 0;
		int currOrder = 0;
		while(true)
		{
			System.out.println(DEC+"Minute "+count+DEC);
			if(currOrder < TOTAL_ORDERS && count == orders[currOrder].getArrivalTime())
			{
				System.out.println("ORDER ADDED: "+orders[currOrder]);
				s.addOrder(orders[currOrder]);
				currOrder++;
			}
			if(s.isDone())
			{
				System.out.println("ALL ORDERS COMPLETE");
				break;
			}
			System.out.println("Currently Cooking Order:\n"+s.getCurrentOrder());
			s.advanceOneMinute();
			count++;
		}
		System.out.println("The average Waiting Time is: "+s.getAverageWaitingTime()+" minutes");
	}

}
