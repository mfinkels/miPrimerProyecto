using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace ApiEjemplo.BranchInfo
{
    public class FilterBranch
    {
        public int idFilterBranch { get; set; }
        public int idBranchRestaurant { get; set; }
        public int idTypeFilter { get; set; }
        public TypeFilter  filter {get; set;}
    }
}