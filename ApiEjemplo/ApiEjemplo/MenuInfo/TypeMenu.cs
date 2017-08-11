using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace ApiEjemplo.MenuInfo
{
    public class TypeMenu
    {
        public int idTypeMenu { get; set; }
        public string name { get; set; }
        public int idBranchRestaurant { get; set; }
        public MenuPlate menuPlate { get; set; }
        public IList<PlateMenu> plates { get; set; }
    }
}