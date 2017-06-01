using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace ApiEjemplo.Models
{
    public class User
    {
        public int idUser { get; set; }
        public string name { get; set; }
        public string lastName { get; set; }
        public string photo { get; set; }
        public int phone { get; set; }
        public string address { get; set; }
        public int promotions { get; set; }
        public string email { get; set; }
        public string password { get; set; }
        public int payments { get; set; }

    }
}