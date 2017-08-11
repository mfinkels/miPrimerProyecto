using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace ApiEjemplo.MenuInfo
{
    public class MenuPlate
    {
        public int idMenuPlate { get; set; }
        public int idPlateMenu { get; set; }
        public int idTypeMenu { get; set; }
        public PlateMenu plate { get; set; }

    }
}