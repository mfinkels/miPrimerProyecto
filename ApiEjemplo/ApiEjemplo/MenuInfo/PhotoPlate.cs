using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace ApiEjemplo.MenuInfo
{
    public class PhotoPlate
    {
        public int idPhotoPlate { get; set; }
        public int idPlateMenu { get; set; }
        public int idUser { get; set; }
        public string photo { get; set; }
    }
}