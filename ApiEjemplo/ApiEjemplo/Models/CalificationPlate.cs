using ApiEjemplo.MenuInfo;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace ApiEjemplo.Models
{
    public class CalificationPlate
    {
        public int idCalificationPlate { get; set; }
        public int idBranchRestaurant { get; set; }
        public int idUser { get; set; }
        public int idPlateMenu { get; set; }
        public int value { get; set; }
        public string message { get; set; }
        public PhotoPlate photo { get; set; }
    }
}