﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace ApiEjemplo.RestaurantInfo
{
    public class SocialNetworkRestaurant
    {
        public int idSocialNetworkRestaurant { get; set; }
        public int idRestaurant { get; set; }
        public int idTypeSocialNetwork { get; set; }
        public string value { get; set; }
        public TypeSocialNetwork type {get; set;}
    }
}