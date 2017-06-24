using ApiEjemplo.PromotionInfo;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace ApiEjemplo.Models
{
    public class Promotion
    {

        public int idPromotion { get; set; }
        public int idTypePromotion { get; set; }
        public string code { get; set; }
        public string name { get; set; }
        public DateTime startDate { get; set; }
        public DateTime expireDate { get; set; }
        public string description { get; set; }
        public int value { get; set; }
        public TypePromotion type { get; set; }
    }
}