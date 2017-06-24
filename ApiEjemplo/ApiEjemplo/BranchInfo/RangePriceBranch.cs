using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace ApiEjemplo.BranchInfo
{
    public class RangePriceBranch
    {
        public int idRangePriceBranch { get; set; }
        public int idBranchRestaurant { get; set; }
        public int minimum { get; set; }
        public int maximum { get; set; }
    }
}