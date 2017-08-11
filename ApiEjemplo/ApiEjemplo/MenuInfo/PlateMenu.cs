using ApiEjemplo.Models;
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
        public double averageCalification { get; set; }
        public string name { get; set; }
        public double price { get; set; }
        public string description { get; set; }
        public List<PhotoPlate> photo { get; set; }
        public CategoryPlate category { get; set; }
        public List<CalificationPlate> calification { get; set; }
    }
}