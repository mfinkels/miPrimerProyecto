using ApiEjemplo.UserInfo;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace ApiEjemplo.Models
{
    public class User
    {
        public int idUser { get; set; }
        public int idPromotion { get; set; }
        public string name { get; set; }
        public string lastName { get; set; }
        public string password { get; set; }
        public string latitude { get; set; }
        public string longitude { get; set; }
        public string photo { get; set; }
        public string phone { get; set; }
        public string email { get; set; }
        public PromotionUser promotion { get; set; }
    }
}