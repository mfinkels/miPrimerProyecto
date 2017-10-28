using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace ApiEjemplo.BranchInfo
{
    public class ServiceBranch
    {
        public int idServiceBranch { get; set; }
        public int idBranchRestaurant { get; set; }
        public int idService { get; set; }
        public Service service { get; set; }
    }
}