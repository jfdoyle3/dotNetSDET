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

              Console.WriteLine(count);
        }

        [Test]
        public void LinqFilter()
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
            Console.WriteLine(count);

            int listCount = new List<string> { "Albert", "Jim", "Melissa", "Adam", "Andrea" }
                                             .Where(list => list.StartsWith("A"))
                                             .Count();

            Console.WriteLine(listCount);

            //   Output all names in ArrayList greater than 4 letters

            names.Where(list => list.Count() > 4).ToList().ForEach(list => Console.WriteLine(list));


            // Limit output on the ArrayList greater than 4 letters
            names.Where(list => list.Count() > 4).Take(1).ToList().ForEach(list => Console.WriteLine(list));
        }
    }
}