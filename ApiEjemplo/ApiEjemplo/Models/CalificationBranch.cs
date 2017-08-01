using ApiEjemplo.BranchInfo;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace ApiEjemplo.Models
{
    public class CalificationBranch
    {
        public int idCalificationBranch { get; set; }
        public int idBranchRestaurant { get; set; }
        public int idUser { get; set; }
        public int idTypeDining { get; set; }
        public int food { get; set; }
        public int service { get; set; }
        public int ambience { get; set; }
        public string message { get; set; }
        public User user { get; set; }
        public TypeDiningCalificationBranch typeDining { get; set; }
        public List<PhotoBranch> photo { get; set; }
        public DateTime date { get; set; }

    }
}