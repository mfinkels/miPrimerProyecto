using ApiEjemplo.AddressInfo;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace ApiEjemplo.BranchInfo
{
    public class TimetableBranch
    {
        public int idTimetableBranch { get; set; }
        public int idBranchRestaurant { get; set; }
        public int idDay { get; set; }
        public string openingHour { get; set; }
        public string closingHour { get; set; }
        public Day day { get; set; }
    }
}