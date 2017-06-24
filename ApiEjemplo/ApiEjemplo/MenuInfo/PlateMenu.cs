using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace ApiEjemplo.MenuInfo
{
    public class PlateMenu
    {
        public int idPlateMenu { get; set; }
        public int idCategoryPlate { get; set; }
        public int averageCalification { get; set; }
        public string name { get; set; }
        public int price { get; set; }
        public string description { get; set; }
        public List<PhotoPlate> photo { get; set; }
        public CategoryPlate category { get; set; }
    }
}