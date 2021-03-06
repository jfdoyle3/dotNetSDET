﻿using NUnit.Framework;
using System;
using System.Collections.Generic;
using System.Linq;

namespace LinqDemo
{
    class Names
    {
        public string Name { get; set; }
    }
    public class LinqTest
    {
        // Typical way to get a count and iterate thru a list or array
        [Test]
        public void Typical()
        {
            List<string> names = new List<string>();
            names.Add("Albert");
            names.Add("Jim");
            names.Add("Melissa");
            names.Add("Adam");
            names.Add("Ant");
            int count = 0;

            for (int idx = 0; idx < names.Count; idx++)
            {
                string name = names[idx];
                if (name.Contains("A"))
                    count++;
            }

          Console.WriteLine("Typical: Contains letter 'A' in name # {0}",count);
          Console.WriteLine("-----------------------------");
        }
        
        [Test]
        public void LinqWhere()
        {
            List<string> names = new List<string>();
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
            ;
            int count = names.Where(list => list.StartsWith("A")).Count();
            Console.WriteLine("Where: Starts With 'A' # {0}. This is done with multiple lines of code.", count);
            Console.WriteLine("-----------------------------");
            int listCount = new List<string> { "Albert", "Jim", "Melissa", "Adam", "Andrea" }
                                             .Where(list => list.StartsWith("A"))
                                             .Count();

            Console.WriteLine("Where: Starts With 'A' # {0}. This is done with one line of code.",listCount);
            Console.WriteLine("-----------------------------");
            //   Output all names in ArrayList greater than 4 letters
            Console.WriteLine("Where: Letter 'A' > 4 letters");
            names.Where(list => list.Count() > 4)
                 .ToList()
                 .ForEach(list => Console.WriteLine(list));

            Console.WriteLine("-----------------------------");
            // Limit output on the ArrayList greater than 4 letters
            Console.WriteLine("Where: Take: First on List with Letter 'A'");
            names.Where(list => list.Count() > 4)
                 .Take(1).ToList()
                 .ForEach(list => Console.WriteLine(list));
            Console.WriteLine("-----------------------------");
        }

        [Test]
        public void LinqSelect()
        {
            List<string> data = new List<string>();
            data.Add("Student");
            data.Add("CareerDevs");
            data.Add("Jim");

            // Output names that are last letter as 'a' and output them in upper case.
            //Stream.of("Albert","Jim","Melissa","Adam","Andrea").filter(list->list.endsWith("a")).map(list->list.toUpperCase()).forEach(list->System.out.println(list));

            List<string> names = new List<string>() { "Albert", "Jim", "Melissa", "Adam", "Andrea" };
            //List<Names> names = new List<Names>() { 
            // new Names() {Name= "Albert" },
            // new Names() {Name= "Jim" },
            // new Names() {Name= "Melissa" },
            // new Names() {Name= "Adam" },
            // new Names() {Name= "Andrea" }};
            Console.WriteLine("Where: Select: End with 'a' change to Upper");
            names.Where(list => list.EndsWith("a"))
                 .Select(list => list.ToUpper())
                 .ToList()
                 .ForEach(list => Console.WriteLine(list));

            Console.WriteLine("-----------------------------");
            // Output names which have first letter as a with upper case and sorted.
            // needs to be sorted
            Console.WriteLine("Where: Select: Sorted: Names which have first letter as A with upper case and sorted.");
            names.Where(list => list.StartsWith("A"))
                 .Select(list => list.ToUpper())
                 .ToList()
                 .Sort();
                
                 
           //   .ForEach(list => Console.WriteLine(list));
            names.Sort();
            foreach(var name in names)
                Console.WriteLine(name);
             

            Console.WriteLine("-----------------------------");

            /* 
             * Merging 2 different lists. 
             * In the follow code:
             * leaving newList sorted line uncommented will keep newList list sorted.
             *
             */

            Console.WriteLine("Merge: 2 Lists and find an item n the new list");
           // List<string> newList = data.Concat(names);
            IEnumerable<string> newList = data.Union(names);
          //  newList.ToList().ForEach(list => Console.WriteLine(list));
           // Boolean isFound = true;
            //Boolean isFound = newList.Where(list => list.StartsWith("Adam")).ToList();
            //Console.WriteLine(isFound);
            //Assert.IsTrue(isFound);
        }

    }
}