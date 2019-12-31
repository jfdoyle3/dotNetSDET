	import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.testng.Assert;
import org.testng.annotations.Test;

	public class StreamTest {
		
		// Count the number of names starting with the letter A in the list
		@Test
		public void Regular()
		{
			ArrayList<String> names=new ArrayList<String>();
			names.add("Albert");
			names.add("Jim");
			names.add("Melissa");
			names.add("Adam");
			names.add("Ant");
			int count=0;
		
			for (int idx=0; idx<names.size(); idx++)
			{
				String name=names.get(idx);
				if(name.startsWith("A"))
				{
					count++;
				}
			}
			
		//	System.out.println(count);
	    }
		
		@Test
		public void StreamFilter()
{
			ArrayList<String> names=new ArrayList<String>();
			names.add("Albert");
			names.add("Jim");
			names.add("Melissa");
			names.add("Adam");
			names.add("Andrea");
			
		/*
		 *  there's no life for intermediate operation if there's no terminal operation.
		 *  terminal operation will continue to .count if .filter returns true .count.
		 *  If .filter returns false .count returns 0.
		 *  We can create stream. How to use .filter in stream API
		 */			
			Long count=names.stream().filter(list->list.startsWith("A")).count();
			//System.out.println(count);
			
			Long streamCount=Stream.of("Albert","Jim","Melissa","Adam","Andrea").filter(list->
			{
				list.startsWith("A");
				return true;
			}).count();
			//System.out.println(streamCount);
			
			// Output all names in ArrayList greater than 4 letters
			
			//names.stream().filter(list->list.length()>4).forEach(list->System.out.println(list));
			
			// Limit output on the ArrayList greater than 4 letters
			//names.stream().filter(list->list.length()>4).limit(1).forEach(list->System.out.println(list));
		}

		@Test
		public void StreamMap() 
		{
			ArrayList<String> data=new ArrayList<String>();
			data.add("Student");
			data.add("CareerDevs");
			data.add("Jim");

			// Output names that are last letter as 'a' and output them in upper case.
			//Stream.of("Albert","Jim","Melissa","Adam","Andrea").filter(list->list.endsWith("a")).map(list->list.toUpperCase()).forEach(list->System.out.println(list));
			
			// Output names which have fire letter as a with upper case and sorted.
			List<String> names=Arrays.asList("Albert","Jim","Melissa","Adam","Andrea");
			//names.stream().filter(list->list.startsWith("A")).sorted().map(list->list.toUpperCase()).forEach(list->System.out.println(list));
			
			/* 
			 * Merging 2 different lists. 
			 * In the follow code:
			 * leaving newList sorted line uncommented will keep newList list sorted.
			 *
			 */			
			Stream <String> newList=Stream.concat(data.stream(), names.stream());
			//newList.sorted().forEach(list->System.out.println(list));
			
			boolean isFound=newList.anyMatch(list->list.equalsIgnoreCase("Adam"));
			//System.out.println(isFound);
			Assert.assertTrue(isFound);
		}
		
		@Test
		public void StreamCollect()
		{
			// You could have used .limit(1) instead of collections to obtain the same output.
			// This was to show how collections work.
			
			List<String> collectionList=Stream.of("Albert","Jim","Melissa","Adam","Andrea").filter(list->list.endsWith("a")).map(list->list.toUpperCase()).collect(Collectors.toList());
			//System.out.println(collectionList.get(0));
			
			List<Integer> numberList=Arrays.asList(3,2,2,7,5,1,9,7);
			
			// Output unique number from this array and sort
			// get the 3rd Index from the sorted Array and output it. the sorted array is (1,2,3,5,7,9)
			//numberList.stream().distinct().forEach(list->System.out.println(list));
			List<Integer> sortedNumberList=numberList.stream().distinct().sorted().collect(Collectors.toList());
			System.out.println(sortedNumberList.get(2)); // outputs 3
		}
	}