using ApiEjemplo.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace ApiEjemplo.PromotionInfo
{
    public class BranchPromotion
    {
        public int idBranchPromotion { get; set; }
        public int idRestaurant { get; set; }
        public int idBranchRestaurant { get; set; }
        public int idPromotion { get; set; }
        public Promotion promotion { get; set; }
    }
}