using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace ApiEjemplo.Models
{
    public class Restaurant
    {
        public int idRestaurant { get; set; }
        public string name { get; set; }
        public string information { get; set; }
        public string photo { get; set; }
        public int rangeOfPriceMin { get; set; }
        public int rangeOfPriceMax { get; set; }
        public int promotion { get; set; }
        public string service { get; set; }
        public int menu { get; set; }
        public int calificationRestaurant { get; set; }
        public string address { get; set; }
        public int reservation { get; set; }

    }
}