using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace ApiEjemplo.BranchInfo
{
    public class CuisineBranch
    {
        public int idCuisineBranch { get; set; }
        public int idCousine { get; set; }
        public int idBranchRestaurant { get; set; }
        public Cuisine cuisine { get; set; }
    }
}