using ApiEjemplo.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace ApiEjemplo.UserInfo
{
    public class PromotionUser
    {
        public int idPromotionUser { get; set; }
        public int idUser { get; set; }
        public int idPromotion { get; set; }
        public Promotion promotion { get; set; }
    }
}