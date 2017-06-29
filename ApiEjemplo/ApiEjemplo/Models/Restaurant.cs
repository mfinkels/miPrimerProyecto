using ApiEjemplo.RestaurantInfo;
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
        public string description { get; set; }
        public List<SocialNetworkRestaurant> socialNetwork { get; set; }
    }
}