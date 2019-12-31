using NUnit.Framework;
using System;
using System.Collections.Generic;
using System.Linq;

namespace LinqDemo
{
    public class LinqTest
    {
        // Typical way to get a count and iterate thru a list or array
        [Test]
        public void Typical()
        {
            List<String> names = new List<String>();
            names.Add("Albert");
            names.Add("Jim");
            names.Add("Melissa");
            names.Add("Adam");
            names.Add("Ant");
            int count = 0;

            for (int idx = 0; idx < names.Count; idx++)
            {
                String name = names[idx];
                if (name.Contains("A"))
                    count++;
            }

             
              Console.WriteLine("Typical: Contains letter 'A' in name # {0}",count);
        }

        [Test]
        public void LinqWhere()
        {
            List<String> names = new List<String>();
            names.Add("Albert");
            names.Add("Jim");
            names.Add("Melissa");
            names.Add("Adam");
            names.Add("Andrea");

            /*
             *  there's no life for intermediate operation if there's no terminal operation.
             *  terminal operation will continue to .count if .filter returns true .count.
             *  If .filter returns false .count returns 0.
             *  We can create stream. How to use .filter in stream API
             */

            int count = names.Where(list => list.StartsWith("A")).Count();
            Console.WriteLine("Where: Starts With 'A' # {0}. This is done with multiple lines of code.",count);

            int listCount = new List<string> { "Albert", "Jim", "Melissa", "Adam", "Andrea" }
                                             .Where(list => list.StartsWith("A"))
                                             .Count();

            Console.WriteLine("Where: Starts With 'A' # {0}. This is done with one line of code.",listCount);

            //   Output all names in ArrayList greater than 4 letters

            names.Where(list => list.Count() > 4)
                 .ToList()
                 .ForEach(list => Console.WriteLine("Where: Letter 'A' > 4 letters # {0}",list));


            // Limit output on the ArrayList greater than 4 letters
            names.Where(list => list.Count() > 4)
                 .Take(1).ToList()
                 .ForEach(list => Console.WriteLine("Where: Take: First on List with Letter 'A' # {0}",list));
        }

        [Test]
        public void LinqSelect()
        {
            List<String> data = new List<String>();
            data.Add("Student");
            data.Add("CareerDevs");
            data.Add("Jim");

            // Output names that are last letter as 'a' and output them in upper case.
            //Stream.of("Albert","Jim","Melissa","Adam","Andrea").filter(list->list.endsWith("a")).map(list->list.toUpperCase()).forEach(list->System.out.println(list));

           List<string> names=new List<string> { "Albert", "Jim", "Melissa", "Adam", "Andrea" };
               
            names.Where(list => list.EndsWith("a"))
                 .Select(list => list.ToUpper())
                 .ToList()
                 .ForEach(list => Console.WriteLine("Where: Select: End with 'a' change to Upper: {0}",list));


            // Output names which have first letter as a with upper case and sorted.
            // needs to be sorted

            names.Where(list => list.StartsWith("A"))
                 .Select(list => list.ToUpper())
                 .OrderBy(list=>list.StartsWith("A"))
                 .ToList()
                 .ForEach(list => Console.WriteLine(list));

            /* 
             * Merging 2 different lists. 
             * In the follow code:
             * leaving newList sorted line uncommented will keep newList list sorted.
             *
             */


            //Stream<String> newList = Stream.concat(data.stream(), names.stream());
            ////newList.sorted().forEach(list->System.out.println(list));
            Boolean isFound = true;
            //boolean isFound = newList.anyMatch(list->list.equalsIgnoreCase("Adam"));
            ////System.out.println(isFound);
            Assert.IsTrue(isFound);
        }

    }
}