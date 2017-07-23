using ApiEjemplo.Models;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace ApiEjemplo.BranchInfo
{
    public class PhotoBranch
    {
        public int idBranchPhoto { get; set; }
        public int idBranchRestaurant { get; set; }
        public BranchRestaurant branch { get; set; }
        public int idUser { get; set; }
        public User user { get; set; } 
        public string photo { get; set; }
    }
}